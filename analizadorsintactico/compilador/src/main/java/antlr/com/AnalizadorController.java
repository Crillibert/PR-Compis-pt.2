package antlr.com;

import org.springframework.web.bind.annotation.*;
import org.antlr.v4.runtime.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class AnalizadorController {

    @PostMapping("/analizar")
    public Map<String, Object> analizar(@RequestBody Map<String, String> request) {
        String codigo = request.get("codigoFuente");
        App.AnalysisResult result = App.analyzeInput(codigo);
        
        if (result.getError() != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", result.getError());
            return response;
        }

        // Procesamiento de tokens
        List<Map<String, String>> tokenList = new ArrayList<>();
        for (Token token : result.getTokens()) {
            if (token.getType() != Token.EOF) {
                tokenList.add(createTokenInfo(token));
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("tokens", tokenList);
        response.put("arbol", result.getParseTree());
        response.put("status", "success");
        return response;
    }

    private Map<String, String> createTokenInfo(Token token) {
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("type", AlgebraLexer.VOCABULARY.getDisplayName(token.getType()));
        tokenInfo.put("text", token.getText());
        tokenInfo.put("line", String.valueOf(token.getLine()));
        tokenInfo.put("column", String.valueOf(token.getCharPositionInLine()));
        return tokenInfo;
    }
}