package antlr.com;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class codigointermedio {
        public static void generarCodigo(String entrada) throws Exception {
        // Crear el lexer
        ANTLRInputStream input = new ANTLRInputStream(entrada);
        AlgebraLexer lexer = new AlgebraLexer(input);
        
        // Crear el stream de tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // Crear el parser
        AlgebraParser parser = new AlgebraParser(tokens);
        
        // Analizar desde la regla 'programa'
        ParseTree tree = parser.programa();
        
        // Crear el generador de código
        RiscVCodeGenerator codeGen = new RiscVCodeGenerator();
        
        // Generar código intermedio
        String resultado = codeGen.visit(tree);
        
        // Imprimir el código generado
        codeGen.imprimirCodigo();
        
        // Generar funciones auxiliares si es necesario
        codeGen.generarFuncionPow();
        
        System.out.println("\nResultado del análisis: " + resultado);
    }
}

/**
 * Clase adicional para análisis y optimización del código generado
 */
class CodeAnalyzer {
    
    public static void generarCodigo(String entrada) throws Exception {
        // Crear el lexer
        ANTLRInputStream input = new ANTLRInputStream(entrada);
        AlgebraLexer lexer = new AlgebraLexer(input);
        
        // Crear el stream de tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // Crear el parser
        AlgebraParser parser = new AlgebraParser(tokens);
        
        // Analizar desde la regla 'programa'
        ParseTree tree = parser.programa();
        
        // Crear el generador de código
        RiscVCodeGenerator codeGen = new RiscVCodeGenerator();
        
        // Generar código intermedio
        String resultado = codeGen.visit(tree);
        
        // Imprimir el código generado
        codeGen.imprimirCodigo();
        
        // Generar funciones auxiliares si es necesario
        codeGen.generarFuncionPow();
        
        System.out.println("\nResultado del análisis: " + resultado);
    }


    /**
     * Analiza el código generado y proporciona estadísticas
     */
    public static void analizarCodigo(RiscVCodeGenerator generator) {
        var codigo = generator.getCodigo();
        
        int instruccionesAritmeticas = 0;
        int instruccionesMemoria = 0;
        int instruccionesSalto = 0;
        int instruccionesComparacion = 0;
        
        System.out.println("\n=== ANÁLISIS DEL CÓDIGO ===");
        
        for (String instruccion : codigo) {
            if (instruccion.contains("fadd") || instruccion.contains("fsub") || 
                instruccion.contains("fmul") || instruccion.contains("fdiv") ||
                instruccion.contains("fneg")) {
                instruccionesAritmeticas++;
            } else if (instruccion.contains("fld") || instruccion.contains("fsd")) {
                instruccionesMemoria++;
            } else if (instruccion.contains("j ") || instruccion.contains("bnez") ||
                      instruccion.contains("call")) {
                instruccionesSalto++;
            } else if (instruccion.contains("feq") || instruccion.contains("flt")) {
                instruccionesComparacion++;
            }
        }
        
        System.out.println("Total de instrucciones: " + codigo.size());
        System.out.println("Instrucciones aritméticas: " + instruccionesAritmeticas);
        System.out.println("Instrucciones de memoria: " + instruccionesMemoria);
        System.out.println("Instrucciones de salto: " + instruccionesSalto);
        System.out.println("Instrucciones de comparación: " + instruccionesComparacion);
        System.out.println("==========================");
    }
    
    /**
     * Optimiza el código eliminando instrucciones redundantes
     */
    public static void optimizarCodigo(RiscVCodeGenerator generator) {
        var codigo = generator.getCodigo();
        
        System.out.println("\n=== OPTIMIZACIONES SUGERIDAS ===");
        
        // Detectar temporales no utilizados
        for (int i = 0; i < codigo.size(); i++) {
            String instruccion = codigo.get(i);
            if (instruccion.contains("fmv.d") && instruccion.contains("fa0")) {
                System.out.println("Línea " + i + ": Posible optimización de movimiento de registro");
            }
        }
        
        // Detectar cargas/almacenamientos consecutivos
        for (int i = 0; i < codigo.size() - 1; i++) {
            if (codigo.get(i).contains("fld") && codigo.get(i + 1).contains("fsd")) {
                System.out.println("Línea " + i + ": Posible optimización load-store");
            }
        }
        
        System.out.println("================================");
    }
    
    public static void main(String[] args) throws Exception {
        // Ejemplos de código de álgebra
        String[] ejemplos = {
            "x => 5.0 + 3.0;",
            "y => x * 2.0;",
            "z => x + y;",
            "result => z ^ 2.0;",
            "x > y;",
            "a => 10.0 / 2.0;",
            "b => -a + 15.0;"
        };
        
        for (int i = 0; i < ejemplos.length; i++) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("EJEMPLO " + (i + 1) + ": " + ejemplos[i]);
            System.out.println("=".repeat(50));
            
            generarCodigo(ejemplos[i]);
        }
    }
}

