package antlr.com;

import org.springframework.web.bind.annotation.*;
import org.antlr.v4.runtime.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class AnalizadorController {

    @PostMapping("/analizar")
    public ResponseEntity<Map<String, Object>> analizar(@RequestBody Map<String, String> request) {
        String codigo = request.get("codigoFuente");
        App.AnalysisResult result = App.analyzeInput(codigo);
        
        Map<String, Object> response = new HashMap<>();
        
        if (result.getError() != null) {
            response.put("status", "error");
            response.put("message", result.getError());
            return ResponseEntity.badRequest().body(response);
        }

        // Procesamiento de tokens (versión mejorada)
        List<Map<String, Object>> tokenList = result.getTokens().stream()
            .filter(token -> token.getType() != Token.EOF)
            .map(token -> {
                Map<String, Object> tokenInfo = new HashMap<>();
                tokenInfo.put("type", AlgebraLexer.VOCABULARY.getDisplayName(token.getType()));
                tokenInfo.put("text", token.getText());
                tokenInfo.put("line", token.getLine());
                tokenInfo.put("column", token.getCharPositionInLine());
                return tokenInfo;
            })
            .collect(Collectors.toList());

        response.put("status", "success");
        response.put("tokens", tokenList);
        response.put("arbol", result.getParseTree());
        response.put("resultado", result.getResultado());  // Nuevo campo
        
        return ResponseEntity.ok(response);
    }

    // Se mantiene el método createTokenInfo por compatibilidad (si se usa en otras partes)
    private Map<String, String> createTokenInfo(Token token) {
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("type", AlgebraLexer.VOCABULARY.getDisplayName(token.getType()));
        tokenInfo.put("text", token.getText());
        tokenInfo.put("line", String.valueOf(token.getLine()));
        tokenInfo.put("column", String.valueOf(token.getCharPositionInLine()));
        return tokenInfo;
    }
}