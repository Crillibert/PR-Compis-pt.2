package antlr.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        runConsoleMode(args);
    }

    // ==================== MODO CONSOLA (existente) ====================
    private static void runConsoleMode(String[] args) {
        try {
            if (args.length > 0 && args[0].equals("--console")) {
                System.out.println("Modo consola activado");
                if (args.length > 1) {
                    analyzeFile(args[1]);
                } else {
                    interactiveMode();
                }
            }
        } catch (Exception e) {
            System.err.println("Error en modo consola:");
            e.printStackTrace(); // Nuevo: Stack trace detallado
        }
    }

    private static void analyzeFile(String filePath) throws IOException {
        try {
            CharStream input = CharStreams.fromFileName(filePath);
            AlgebraLexer lexer = configureLexer(new AlgebraLexer(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgebraParser parser = configureParser(new AlgebraParser(tokens));
            
            ParseTree tree = parser.programa(); 
            System.out.println("Árbol sintáctico:\n" + tree.toStringTree(parser));
        } catch (AnalysisException e) {
            System.err.println("Error en análisis:");
            e.printStackTrace(); // Nuevo: Stack trace detallado
        }
    }

    private static void interactiveMode() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Modo interactivo. Escribe expresiones (o 'salir'):");

        while (true) {
            System.out.print("> ");
            String input = reader.readLine();
            if (input == null || input.equalsIgnoreCase("salir")) break;
            
            try {
                AnalysisResult result = analyzeInput(input); // Reutiliza el nuevo método
                System.out.println("Tokens: " + result.getTokens());
                System.out.println("Árbol:\n" + result.getParseTree());
            } catch (Exception e) {
                System.err.println("Error:");
                e.printStackTrace(); // Nuevo: Stack trace detallado
            }
        }
    }

    // ==================== NUEVOS MÉTODOS PARA MANEJO DE ERRORES ====================
    
    /**
     * Método público para análisis desde el Controller
     */
    public static AnalysisResult analyzeInput(String input) {
        try {
            CharStream stream = CharStreams.fromString(input);
            AlgebraLexer lexer = configureLexer(new AlgebraLexer(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgebraParser parser = configureParser(new AlgebraParser(tokens));
            
            // Análisis léxico
            tokens.fill();
            
            // Análisis sintáctico
            ParseTree tree = parser.programa();
            
            return new AnalysisResult(
                tokens.getTokens(),
                tree.toStringTree(parser),
                null
            );
        } catch (AnalysisException e) {
            return new AnalysisResult(null, null, e.getMessage());
        }
    }

    // Configuración común del lexer
    private static AlgebraLexer configureLexer(AlgebraLexer lexer) {
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ThrowingErrorListener());
        return lexer;
    }

    // Configuración común del parser
    private static AlgebraParser configureParser(AlgebraParser parser) {
        parser.removeErrorListeners();
        parser.addErrorListener(new ThrowingErrorListener());
        return parser;
    }

    // ==================== CLASES AUXILIARES ====================
    
    // Manejador de errores mejorado
    private static class ThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol,
                int line,
                int charPositionInLine,
                String msg,
                RecognitionException e) {
            
            String errorMsg = String.format("[Línea %d:%d] %s", line, charPositionInLine, msg);
            throw new AnalysisException(errorMsg, e);
        }
    }

    // Excepción personalizada
    public static class AnalysisException extends RuntimeException {
        public AnalysisException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Contenedor de resultados
    public static class AnalysisResult {
        private final List<Token> tokens; // Cambiado de List<? extends Token> a List<Token>
        private final String parseTree;
        private final String error;
            
        public AnalysisResult(List<Token> tokens, String parseTree, String error) {
            this.tokens = tokens;
            this.parseTree = parseTree;
            this.error = error;
        }
        
        // Getters
        public List<Token> getTokens() { return tokens; }
        public String getParseTree() { return parseTree; }
        public String getError() { return error; }
    }
}