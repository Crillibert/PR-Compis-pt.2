package antlr.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiscVCodeGenerator extends AlgebraBaseVisitor<String> {
    
    // Lista de instrucciones de código intermedio
    private List<String> codigo = new ArrayList<>();
    
    // Contador para generar temporales únicos
    private int contadorTemp = 0;
    
    // Contador para generar etiquetas únicas
    private int contadorEtiqueta = 0;
    
    // Mapa de variables para el manejo de memoria
    private Map<String, String> tablaVariables = new HashMap<>();
    
    // Registro base para variables (simulando memoria)
    private static final String REG_BASE = "s0";
    private int offsetVariable = 0;

    /**
     * Genera un nuevo temporal
     */
    private String nuevoTemporal() {
        return "t" + (contadorTemp++);
    }
    
    /**
     * Genera una nueva etiqueta
     */
    private String nuevaEtiqueta() {
        return "L" + (contadorEtiqueta++);
    }
    
    /**
     * Obtiene el código generado
     */
    public List<String> getCodigo() {
        return codigo;
    }
    
    /**
     * Imprime el código generado
     */
    public void imprimirCodigo() {
        System.out.println("=== CÓDIGO INTERMEDIO RISC-V ===");
        for (int i = 0; i < codigo.size(); i++) {
            System.out.println(String.format("%3d: %s", i, codigo.get(i)));
        }
        System.out.println("===============================");
    }

    @Override
    public String visitPrograma(AlgebraParser.ProgramaContext ctx) {
        // Inicialización del programa
        codigo.add("# Inicio del programa");
        codigo.add("addi sp, sp, -256    # Reservar espacio en stack");
        codigo.add("addi " + REG_BASE + ", sp, 0    # Inicializar registro base");
        
        String ultimoResultado = null;
        
        // Procesar cada ecuación
        for (AlgebraParser.EcuacionContext ecuacion : ctx.ecuacion()) {
            ultimoResultado = visit(ecuacion);
        }
        
        // Finalización del programa
        codigo.add("# Fin del programa");
        codigo.add("addi sp, sp, 256     # Liberar espacio en stack");
        codigo.add("li a7, 10            # Código de salida");
        codigo.add("ecall                # Llamada al sistema");
        
        return ultimoResultado;
    }

    @Override
    public String visitAssignOp(AlgebraParser.AssignOpContext ctx) {
        AlgebraParser.EcuacionContext ecuacionCtx = (AlgebraParser.EcuacionContext) ctx.getParent();
        
        // Obtener el nombre de la variable (lado izquierdo)
        String nombreVariable = ecuacionCtx.expresion(0).getText();
        
        // Evaluar la expresión del lado derecho
        String valorTemp = visit(ecuacionCtx.expresion(1));
        
        // Asignar offset a la variable si no existe
        if (!tablaVariables.containsKey(nombreVariable)) {
            tablaVariables.put(nombreVariable, String.valueOf(offsetVariable));
            offsetVariable += 8; // 8 bytes para double
            codigo.add("# Declaración de variable " + nombreVariable);
        }
        
        String offset = tablaVariables.get(nombreVariable);
        
        // Generar código para la asignación
        codigo.add("# Asignación: " + nombreVariable + " => " + valorTemp);
        codigo.add("fsd " + valorTemp + ", " + offset + "(" + REG_BASE + ")");
        codigo.add("# Variable " + nombreVariable + " guardada en offset " + offset);
        
        return valorTemp;
    }

    @Override
    public String visitAddSubExpr(AlgebraParser.AddSubExprContext ctx) {
        String izq = visit(ctx.expresion(0));
        String der = visit(ctx.expresion(1));
        String resultado = nuevoTemporal();
        
        if (ctx.MAS() != null) {
            codigo.add("fadd.d " + resultado + ", " + izq + ", " + der + "    # " + resultado + " = " + izq + " + " + der);
        } else {
            codigo.add("fsub.d " + resultado + ", " + izq + ", " + der + "    # " + resultado + " = " + izq + " - " + der);
        }
        
        return resultado;
    }

    @Override
    public String visitMulDivExpr(AlgebraParser.MulDivExprContext ctx) {
        String izq = visit(ctx.expresion(0));
        String der = visit(ctx.expresion(1));
        String resultado = nuevoTemporal();
        
        if (ctx.POR() != null) {
            codigo.add("fmul.d " + resultado + ", " + izq + ", " + der + "    # " + resultado + " = " + izq + " * " + der);
        } else {
            // División con verificación de cero (simplificada)
            codigo.add("fdiv.d " + resultado + ", " + izq + ", " + der + "    # " + resultado + " = " + izq + " / " + der);
        }
        
        return resultado;
    }

    @Override
    public String visitPowExpr(AlgebraParser.PowExprContext ctx) {
        String base = visit(ctx.expresion(0));
        String exponente = visit(ctx.expresion(1));
        String resultado = nuevoTemporal();
        
        // Para potencias, usamos una llamada a función (simulada)
        codigo.add("# Operación de potencia");
        codigo.add("fmv.d fa0, " + base + "       # Cargar base en fa0");
        codigo.add("fmv.d fa1, " + exponente + "       # Cargar exponente en fa1");
        codigo.add("call pow_function         # Llamar función pow");
        codigo.add("fmv.d " + resultado + ", fa0       # Guardar resultado");
        
        return resultado;
    }

    @Override
    public String visitParenExpr(AlgebraParser.ParenExprContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public String visitAtomExpr(AlgebraParser.AtomExprContext ctx) {
        String valor = visit(ctx.atom());
        
        // Manejar operadores unarios
        if (ctx.MENOS() != null) {
            int cantidadMenos = ctx.MENOS().size();
            if (cantidadMenos % 2 != 0) {
                String temp = nuevoTemporal();
                codigo.add("fneg.d " + temp + ", " + valor + "    # " + temp + " = -" + valor);
                return temp;
            }
        }
        
        return valor;
    }

    @Override
    public String visitNumberAtom(AlgebraParser.NumberAtomContext ctx) {
        String valor = ctx.reales().getText();
        String temp = nuevoTemporal();
        
        // Cargar constante flotante
        codigo.add("# Cargar constante " + valor);
        codigo.add("li t0, " + convertirAEntero(valor) + "    # Cargar bits de " + valor);
        codigo.add("fmv.d.x " + temp + ", t0    # Convertir a punto flotante");
        
        return temp;
    }

    @Override
    public String visitVarAtom(AlgebraParser.VarAtomContext ctx) {
        String nombreVariable = ctx.variable().getText();
        String temp = nuevoTemporal();
        
        if (tablaVariables.containsKey(nombreVariable)) {
            String offset = tablaVariables.get(nombreVariable);
            codigo.add("# Cargar variable " + nombreVariable);
            codigo.add("fld " + temp + ", " + offset + "(" + REG_BASE + ")    # " + temp + " = " + nombreVariable);
        } else {
            // Variable no inicializada, cargar 0.0
            codigo.add("# Variable " + nombreVariable + " no inicializada, usar 0.0");
            codigo.add("fcvt.d.w " + temp + ", zero    # " + temp + " = 0.0");
        }
        
        return temp;
    }

    @Override
    public String visitEqOp(AlgebraParser.EqOpContext ctx) {
        AlgebraParser.EcuacionContext ecuacionCtx = (AlgebraParser.EcuacionContext) ctx.getParent();
        
        String izq = visit(ecuacionCtx.expresion(0));
        String der = visit(ecuacionCtx.expresion(1));
        String etiquetaVerdad = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();
        
        codigo.add("# Comparación de igualdad");
        codigo.add("feq.d t0, " + izq + ", " + der + "    # Comparar " + izq + " == " + der);
        codigo.add("bnez t0, " + etiquetaVerdad + "    # Saltar si son iguales");
        codigo.add("li a0, 0                # Cargar falso");
        codigo.add("j " + etiquetaFin + "                # Saltar al final");
        codigo.add(etiquetaVerdad + ":");
        codigo.add("li a0, 1                # Cargar verdadero");
        codigo.add(etiquetaFin + ":");
        
        return "a0";
    }

    @Override
    public String visitGtOp(AlgebraParser.GtOpContext ctx) {
        AlgebraParser.EcuacionContext ecuacionCtx = (AlgebraParser.EcuacionContext) ctx.getParent();
        
        String izq = visit(ecuacionCtx.expresion(0));
        String der = visit(ecuacionCtx.expresion(1));
        String etiquetaVerdad = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();
        
        codigo.add("# Comparación mayor que");
        codigo.add("flt.d t0, " + der + ", " + izq + "    # Comparar " + izq + " > " + der);
        codigo.add("bnez t0, " + etiquetaVerdad + "    # Saltar si es mayor");
        codigo.add("li a0, 0                # Cargar falso");
        codigo.add("j " + etiquetaFin + "                # Saltar al final");
        codigo.add(etiquetaVerdad + ":");
        codigo.add("li a0, 1                # Cargar verdadero");
        codigo.add(etiquetaFin + ":");
        
        return "a0";
    }

    @Override
    public String visitLtOp(AlgebraParser.LtOpContext ctx) {
        AlgebraParser.EcuacionContext ecuacionCtx = (AlgebraParser.EcuacionContext) ctx.getParent();
        
        String izq = visit(ecuacionCtx.expresion(0));
        String der = visit(ecuacionCtx.expresion(1));
        String etiquetaVerdad = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();
        
        codigo.add("# Comparación menor que");
        codigo.add("flt.d t0, " + izq + ", " + der + "    # Comparar " + izq + " < " + der);
        codigo.add("bnez t0, " + etiquetaVerdad + "    # Saltar si es menor");
        codigo.add("li a0, 0                # Cargar falso");
        codigo.add("j " + etiquetaFin + "                # Saltar al final");
        codigo.add(etiquetaVerdad + ":");
        codigo.add("li a0, 1                # Cargar verdadero");
        codigo.add(etiquetaFin + ":");
        
        return "a0";
    }

    /**
     * Convierte un número decimal a su representación en bits para RISC-V
     * (Simplificado - en implementación real usarías IEEE 754)
     */
    private String convertirAEntero(String numeroDecimal) {
        try {
            double valor = Double.parseDouble(numeroDecimal);
            long bits = Double.doubleToLongBits(valor);
            return String.valueOf(bits);
        } catch (NumberFormatException e) {
            return "0";
        }
    }
    
    /**
     * Método para generar función auxiliar de potencia
     */
    public void generarFuncionPow() {
        codigo.add("");
        codigo.add("# Función auxiliar para potencia");
        codigo.add("pow_function:");
        codigo.add("    # Implementación simplificada de pow(fa0, fa1)");
        codigo.add("    # En implementación real, usar algoritmo de exponenciación");
        codigo.add("    fmv.d fa0, fa0    # Por ahora, retorna la base");
        codigo.add("    ret               # Retornar");
    }
}