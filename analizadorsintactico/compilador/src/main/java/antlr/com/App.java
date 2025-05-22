package antlr.com;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Punto de entrada principal del compilador/analizador sintáctico
 */
public final class App {
    private App() {
    }

    /**
     * Método principal que inicia el análisis del código fuente.
     * @param args Argumentos del programa. Puede recibir la ruta de un archivo a analizar.
     */
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                // Analizar archivo proporcionado como argumento
                analyzeFile(args[0]);
            } else {
                // Modo interactivo
                System.out.println("Modo interactivo. Escribe expresiones para analizar (Ctrl+D para salir):");
                interactiveMode();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error durante el análisis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Analiza un archivo de entrada
     * @param filePath Ruta del archivo a analizar
     * @throws IOException Si hay error al leer el archivo
     */
    private static void analyzeFile(String filePath) throws IOException {
        // Configurar el analizador léxico
        CharStream input = CharStreams.fromFileName(filePath);
        AlgebraLexer lexer = new AlgebraLexer(input);
        
        // Crear tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // Configurar el analizador sintáctico
        AlgebraParser parser = new AlgebraParser(tokens);
        
        // Iniciar el análisis desde la regla inicial (ajusta según tu gramática)
        ParseTree tree = parser.programa(); 
        
        // Imprimir el árbol de análisis sintáctico
        System.out.println(tree.toStringTree(parser));
        
        // Aquí podrías agregar visitantes o listeners para procesar el árbol
    }

    /**
     * Modo interactivo para analizar expresiones ingresadas por consola
     * @throws IOException Si hay error al leer la entrada
     */
    private static void interactiveMode() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Modo interactivo. Escribe una expresión y presiona Enter:");
        System.out.println("Ejemplo: x => 2+3;");
        System.out.println("Escribe 'salir' para terminar.");
        VisitOp vistador = new VisitOp();
        while (true) {
            try {
                System.out.print("> ");
                String input = reader.readLine();
                if (input == null || input.equalsIgnoreCase("salir")) break;
                
                // Procesar la entrada
                CharStream stream = CharStreams.fromString(input);
                AlgebraLexer lexer = new AlgebraLexer(stream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                AlgebraParser parser = new AlgebraParser(tokens);

                // Mostrar tokens (opcional, para depuración)
                tokens.fill();
                System.out.println("Tokens: " + tokens.getTokens());
                
                // Parsear y mostrar árbol
                ParseTree tree = parser.programa();
                vistador.visit(tree);
                System.out.println("Árbol sintáctico:");
                System.out.println(tree.toStringTree(parser));
                
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}