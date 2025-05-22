package antlr.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.List;

@SpringBootApplication
public class App {
    // Array de nombres de tokens para la serialización
    public static String[] tokenNames;

    static {
        // Inicializar los nombres de tokens
        tokenNames = AlgebraLexer.ruleNames;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        runConsoleMode(args);
    }

    // ==================== MODO CONSOLA ====================
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
            e.printStackTrace();
        }
    }

    private static void analyzeFile(String filePath) throws IOException {
        try {
            CharStream input = CharStreams.fromFileName(filePath);
            AlgebraLexer lexer = configureLexer(new AlgebraLexer(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgebraParser parser = configureParser(new AlgebraParser(tokens));
            
            ParseTree tree = parser.programa(); 
            VisitOp visitor = new VisitOp();
            Double resultado = visitor.visit(tree);
            
            System.out.println("Árbol sintáctico:\n" + tree.toStringTree(parser));
            System.out.println("Resultado: " + (resultado != null ? resultado.toString() : "N/A"));
        } catch (AnalysisException e) {
            System.err.println("Error en análisis:");
            e.printStackTrace();
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
                AnalysisResult result = analyzeInput(input);
                System.out.println("Tokens: " + result.getTokens());
                System.out.println("Árbol:\n" + result.getParseTree());
                System.out.println("Resultado: " + (result.getResultado() != null ? result.getResultado() : "N/A"));
            } catch (Exception e) {
                System.err.println("Error:");
                e.printStackTrace();
            }
        }
    }

    // ==================== MÉTODOS DE ANÁLISIS ====================
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
            
            // Análisis semántico y cálculo
            VisitOp visitor = new VisitOp();
            Double resultado = visitor.visit(tree);
            
            return new AnalysisResult(
                tokens.getTokens(),
                tree.toStringTree(parser),
                resultado != null ? resultado.toString() : null,
                null
            );
        } catch (AnalysisException e) {
            return new AnalysisResult(null, null, null, e.getMessage());
        }
    }

    // ==================== CONFIGURACIONES ====================
    private static AlgebraLexer configureLexer(AlgebraLexer lexer) {
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ThrowingErrorListener());
        return lexer;
    }

    private static AlgebraParser configureParser(AlgebraParser parser) {
        parser.removeErrorListeners();
        parser.addErrorListener(new ThrowingErrorListener());
        return parser;
    }

    // ==================== CLASES AUXILIARES ====================
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

    public static class AnalysisException extends RuntimeException {
        public AnalysisException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class AnalysisResult {
        private final List<Token> tokens;
        private final String parseTree;
        private final String resultado;
        private final String error;
            
        public AnalysisResult(List<Token> tokens, String parseTree, String resultado, String error) {
            this.tokens = tokens;
            this.parseTree = parseTree;
            this.resultado = resultado;
            this.error = error;
        }
        
        public List<Token> getTokens() { return tokens; }
        public String getParseTree() { return parseTree; }
        public String getResultado() { return resultado; }
        public String getError() { return error; }
    }
}