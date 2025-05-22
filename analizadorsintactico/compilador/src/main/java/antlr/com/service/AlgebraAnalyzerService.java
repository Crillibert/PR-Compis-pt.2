package antlr.com.service;

import antlr.com.dto.AnalysisResponse;
import antlr.com.dto.TokenInfo;
import antlr.com.AlgebraLexer;
import antlr.com.AlgebraParser;
import antlr.com.VisitOp;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgebraAnalyzerService {

    public AnalysisResponse analyzeExpression(String expression) {
        AnalysisResponse response = new AnalysisResponse();
        
        try {
            // Configurar el analizador léxico
            CharStream input = CharStreams.fromString(expression);
            AlgebraLexer lexer = new AlgebraLexer(input);
            
            // Crear tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill();
            
            // Extraer información de tokens
            List<TokenInfo> tokenInfos = new ArrayList<>();
            for (Token token : tokens.getTokens()) {
                if (token.getType() != Token.EOF) {
                    TokenInfo tokenInfo = new TokenInfo();
                    tokenInfo.setType(lexer.getVocabulary().getDisplayName(token.getType()));
                    tokenInfo.setText(token.getText());
                    tokenInfo.setLine(token.getLine());
                    tokenInfo.setColumn(token.getCharPositionInLine());
                    tokenInfos.add(tokenInfo);
                }
            }
            
            // Configurar el analizador sintáctico
            AlgebraParser parser = new AlgebraParser(tokens);
            
            // Configurar manejo de errores
            parser.removeErrorListeners();
            ANTLRErrorListener errorListener = new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                      int line, int charPositionInLine, String msg, 
                                      RecognitionException e) {
                    response.setSuccess(false);
                    response.setError("Error de sintaxis en línea " + line + ":" + charPositionInLine + " - " + msg);
                }
            };
            parser.addErrorListener(errorListener);
            
            // Resetear tokens para el parser
            tokens.seek(0);
            
            // Iniciar el análisis desde la regla inicial
            ParseTree tree = parser.programa();
            
            if (response.isSuccess()) {
                // Obtener representación del árbol
                String treeString = tree.toStringTree(parser);
                
                // Usar el visitador para obtener resultados
                VisitOp visitor = new VisitOp();
                visitor.visit(tree);
                
                // Llenar la respuesta
                response.setSuccess(true);
                response.setTokens(tokenInfos);
                response.setSyntaxTree(treeString);
                response.setTokenCount(tokenInfos.size());
                response.setCharacterCount(expression.length());
                
                // Análisis adicional
                response.setHasErrors(false);
                response.setAnalysisMessage("Expresión analizada correctamente");
            }
            
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Error durante el análisis: " + e.getMessage());
            response.setHasErrors(true);
        }
        
        return response;
    }
}