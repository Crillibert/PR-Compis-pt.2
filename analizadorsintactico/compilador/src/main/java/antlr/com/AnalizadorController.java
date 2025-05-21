package antlr.com;
import org.springframework.web.bind.annotation.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

@RestController
@RequestMapping("/api")
public class AnalizadorController {

    @PostMapping("/analizar")
    public AnalisisResultado analizar(@RequestBody String codigoFuente) {
        try {
            
            CharStream input = CharStreams.fromString(codigoFuente);
            AlgebraLexer lexer = new AlgebraLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgebraParser parser = new AlgebraParser(tokens);

            // Obtener tokens
            tokens.fill();
            String listaTokens = tokens.getTokens().toString();

            // Obtener árbol sintáctico
            ParseTree tree = parser.programa();
            String arbolSintactico = tree.toStringTree(parser);

            return new AnalisisResultado(listaTokens, arbolSintactico);
        } catch (Exception e) {
            throw new RuntimeException("Error al analizar: " + e.getMessage());
        }
    }

    // Clase para la respuesta JSON
    static class AnalisisResultado {
        private String tokens;
        private String arbol;

        public AnalisisResultado(String tokens, String arbol) {
            this.tokens = tokens;
            this.arbol = arbol;
        }

        // Getters (necesarios para la serialización JSON)
        public String getTokens() { return tokens; }
        public String getArbol() { return arbol; }
    }
}
