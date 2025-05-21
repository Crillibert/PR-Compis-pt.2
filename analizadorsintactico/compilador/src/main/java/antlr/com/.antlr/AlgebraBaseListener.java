// Generated from c:/Users/Gabriel/Documents/GitHub/PR-Compis-pt.2/analizadorsintactico/compilador/src/main/java/antlr/com/Algebra.g4 by ANTLR 4.13.1

package antlr.com;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class provides an implementation of {@link AlgebraListener},
 * which handles the evaluation of algebraic expressions and equations.
 */
@SuppressWarnings("CheckReturnValue")
public class AlgebraBaseListener implements AlgebraListener {
    // Mapa para almacenar las variables y sus valores
    private Map<String, Double> memoria = new HashMap<>();
    
    // Pila para almacenar resultados intermedios durante la evaluación
    private Stack<Double> pilaValores = new Stack<>();
    
    // Pila para operadores
    private Stack<String> pilaOperadores = new Stack<>();
    
    // Variable para almacenar el último resultado calculado
    private Double ultimoResultado = 0.0;
    
    /**
     * Obtiene el resultado de la última evaluación
     * @return El último valor calculado
     */
    public Double getResultado() {
        return ultimoResultado;
    }
    
    /**
     * Obtiene el valor de una variable
     * @param nombre Nombre de la variable
     * @return Valor de la variable, o null si no existe
     */
    public Double getVariable(String nombre) {
        return memoria.get(nombre);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Inicializa el procesamiento del programa.</p>
     */
    @Override 
    public void enterPrograma(AlgebraParser.ProgramaContext ctx) {
        // Limpiar pilas y resultados al comenzar un nuevo programa
        pilaValores.clear();
        pilaOperadores.clear();
        ultimoResultado = 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Finaliza el procesamiento del programa.</p>
     */
    @Override 
    public void exitPrograma(AlgebraParser.ProgramaContext ctx) {
        // Si hay un valor final en la pila, guardarlo como último resultado
        if (!pilaValores.isEmpty()) {
            ultimoResultado = pilaValores.pop();
        }
        System.out.println("Programa finalizado. Último resultado: " + ultimoResultado);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Inicializa el procesamiento de una ecuación.</p>
     */
    @Override 
    public void enterEcuacion(AlgebraParser.EcuacionContext ctx) {
        // Limpiar pilas para la nueva ecuación
        pilaValores.clear();
        pilaOperadores.clear();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Finaliza el procesamiento de una ecuación, aplicando el operador relacional.</p>
     */
    @Override 
    public void exitEcuacion(AlgebraParser.EcuacionContext ctx) {
        // Al salir de una ecuación, deberían haber dos valores en la pila
        if (pilaValores.size() >= 2) {
            Double derecha = pilaValores.pop();
            Double izquierda = pilaValores.pop();
            
            // El tipo de operador relacional se trata en los métodos exit de los operadores específicos
            ultimoResultado = izquierda; // Por defecto, devolvemos el lado izquierdo
            pilaValores.push(ultimoResultado);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa expresiones de suma y resta.</p>
     */
    @Override 
    public void exitAddSubExpr(AlgebraParser.AddSubExprContext ctx) {
        if (pilaValores.size() >= 2) {
            Double derecha = pilaValores.pop();
            Double izquierda = pilaValores.pop();
            
            if (ctx.MAS() != null) {
                pilaValores.push(izquierda + derecha);
            } else if (ctx.MENOS() != null) {
                pilaValores.push(izquierda - derecha);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa una expresión atómica con posibles operadores unarios.</p>
     */
    @Override 
    public void exitAtomExpr(AlgebraParser.AtomExprContext ctx) {
        // El valor del átomo ya debería estar en la pila desde exitNumberAtom o exitVarAtom
        if (!pilaValores.isEmpty()) {
            Double valor = pilaValores.pop();
            
            // Aplicar operadores unarios (+ o -)
            if (ctx.MENOS() != null) {
                // Contar cuántos operadores MENOS hay
                int cantidadMenos = ctx.MENOS().size();
                // Si el número de MENOS es impar, cambiamos el signo
                if (cantidadMenos % 2 != 0) {
                    valor = -valor;
                }
            }
            
            pilaValores.push(valor);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa expresiones de potencia.</p>
     */
    @Override 
    public void exitPowExpr(AlgebraParser.PowExprContext ctx) {
        if (pilaValores.size() >= 2) {
            Double exponente = pilaValores.pop();
            Double base = pilaValores.pop();
            pilaValores.push(Math.pow(base, exponente));
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa expresiones de multiplicación y división.</p>
     */
    @Override 
    public void exitMulDivExpr(AlgebraParser.MulDivExprContext ctx) {
        if (pilaValores.size() >= 2) {
            Double derecha = pilaValores.pop();
            Double izquierda = pilaValores.pop();
            
            if (ctx.POR() != null) {
                pilaValores.push(izquierda * derecha);
            } else if (ctx.DIV() != null) {
                if (derecha == 0) {
                    System.err.println("Error: División por cero");
                    pilaValores.push(Double.NaN); // Representamos el error con NaN
                } else {
                    pilaValores.push(izquierda / derecha);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa expresiones entre paréntesis.</p>
     */
    @Override 
    public void enterParenExpr(AlgebraParser.ParenExprContext ctx) {
        // Marcar el inicio de una expresión parentizada
        pilaOperadores.push("(");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Finaliza el procesamiento de expresiones entre paréntesis.</p>
     */
    @Override 
    public void exitParenExpr(AlgebraParser.ParenExprContext ctx) {
        // El valor de la expresión ya estará en la pila
        // Solo necesitamos quitar la marca de paréntesis si la usamos
        if (!pilaOperadores.isEmpty() && pilaOperadores.peek().equals("(")) {
            pilaOperadores.pop();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un átomo numérico.</p>
     */
    @Override 
    public void exitNumberAtom(AlgebraParser.NumberAtomContext ctx) {
        // El valor ya debería estar en la pila desde exitReales
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un átomo variable.</p>
     */
    @Override 
    public void exitVarAtom(AlgebraParser.VarAtomContext ctx) {
        // El valor ya debería estar en la pila desde exitVariable
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un número real.</p>
     */
    @Override 
    public void exitReales(AlgebraParser.RealesContext ctx) {
        try {
            Double valor = Double.parseDouble(ctx.NUMERO_REAL().getText());
            pilaValores.push(valor);
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear número: " + ctx.NUMERO_REAL().getText());
            pilaValores.push(Double.NaN);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa una variable.</p>
     */
    @Override 
    public void exitVariable(AlgebraParser.VariableContext ctx) {
        String nombreVariable = ctx.VARIABLE().getText();
        Double valor = memoria.getOrDefault(nombreVariable, 0.0);
        pilaValores.push(valor);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un operador de igualdad.</p>
     */
    @Override 
    public void exitEqOp(AlgebraParser.EqOpContext ctx) {
        // Este método se llama después de procesar una ecuación completa
        if (pilaValores.size() >= 2) {
            Double derecha = pilaValores.pop();
            Double izquierda = pilaValores.pop();
            
            // Verificar igualdad
            Boolean esIgual = Math.abs(izquierda - derecha) < 0.0001; // Pequeña tolerancia para números de punto flotante
            System.out.println("Ecuación: " + izquierda + " = " + derecha + " -> " + (esIgual ? "Verdadero" : "Falso"));
            
            // Colocar el resultado de la comparación como último resultado
            ultimoResultado = esIgual ? 1.0 : 0.0;
            pilaValores.push(ultimoResultado);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un operador mayor que.</p>
     */
    @Override 
    public void exitGtOp(AlgebraParser.GtOpContext ctx) {
        if (pilaValores.size() >= 2) {
            Double derecha = pilaValores.pop();
            Double izquierda = pilaValores.pop();
            
            // Verificar si izquierda es mayor que derecha
            Boolean esMayor = izquierda > derecha;
            System.out.println("Comparación: " + izquierda + " > " + derecha + " -> " + (esMayor ? "Verdadero" : "Falso"));
            
            // Colocar el resultado de la comparación como último resultado
            ultimoResultado = esMayor ? 1.0 : 0.0;
            pilaValores.push(ultimoResultado);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un operador menor que.</p>
     */
    @Override 
    public void exitLtOp(AlgebraParser.LtOpContext ctx) {
        if (pilaValores.size() >= 2) {
            Double derecha = pilaValores.pop();
            Double izquierda = pilaValores.pop();
            
            // Verificar si izquierda es menor que derecha
            Boolean esMenor = izquierda < derecha;
            System.out.println("Comparación: " + izquierda + " < " + derecha + " -> " + (esMenor ? "Verdadero" : "Falso"));
            
            // Colocar el resultado de la comparación como último resultado
            ultimoResultado = esMenor ? 1.0 : 0.0;
            pilaValores.push(ultimoResultado);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Procesa un operador de asignación.</p>
     */
    @Override 
    public void exitAssignOp(AlgebraParser.AssignOpContext ctx) {
        if (pilaValores.size() >= 2) {
            Double valorAsignar = pilaValores.pop();
            Double dummy = pilaValores.pop(); // Ignoramos este valor
            
            // Obtenemos el contexto padre (la ecuación)
            AlgebraParser.EcuacionContext ecuacionCtx = (AlgebraParser.EcuacionContext) ctx.getParent();
            
            // Verificar que el lado izquierdo es una variable
            if (ecuacionCtx.expresion(0) instanceof AlgebraParser.AtomExprContext) {
                AlgebraParser.AtomExprContext atomExpr = (AlgebraParser.AtomExprContext) ecuacionCtx.expresion(0);
                if (atomExpr.atom() instanceof AlgebraParser.VarAtomContext) {
                    String nombreVariable = atomExpr.atom().getText();
                    memoria.put(nombreVariable, valorAsignar);
                    System.out.println("Variable " + nombreVariable + " asignada con valor " + valorAsignar);
                    
                    // El resultado de la asignación es el valor asignado
                    ultimoResultado = valorAsignar;
                    pilaValores.push(ultimoResultado);
                } else {
                    System.err.println("Error: el lado izquierdo debe ser una variable");
                    pilaValores.push(Double.NaN);
                }
            } else {
                System.err.println("Error: el lado izquierdo debe ser una variable");
                pilaValores.push(Double.NaN);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAddSubExpr(AlgebraParser.AddSubExprContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAtomExpr(AlgebraParser.AtomExprContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterPowExpr(AlgebraParser.PowExprContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterMulDivExpr(AlgebraParser.MulDivExprContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterNumberAtom(AlgebraParser.NumberAtomContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterVarAtom(AlgebraParser.VarAtomContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterReales(AlgebraParser.RealesContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterVariable(AlgebraParser.VariableContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterEqOp(AlgebraParser.EqOpContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterGtOp(AlgebraParser.GtOpContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterLtOp(AlgebraParser.LtOpContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAssignOp(AlgebraParser.AssignOpContext ctx) { }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitTerminal(TerminalNode node) { }
    
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitErrorNode(ErrorNode node) {
        System.err.println("Error en nodo: " + node.getText());
    }
}