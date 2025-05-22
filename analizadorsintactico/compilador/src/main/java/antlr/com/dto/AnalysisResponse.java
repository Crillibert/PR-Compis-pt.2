package antlr.com.dto;

import java.util.List;


public class AnalysisResponse {
    private boolean success = true;
    private String error;
    private List<TokenInfo> tokens;
    private String syntaxTree;
    private int tokenCount;
    private int characterCount;
    private boolean hasErrors;
    private String analysisMessage;

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<TokenInfo> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenInfo> tokens) {
        this.tokens = tokens;
    }

    public String getSyntaxTree() {
        return syntaxTree;
    }

    public void setSyntaxTree(String syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public int getTokenCount() {
        return tokenCount;
    }

    public void setTokenCount(int tokenCount) {
        this.tokenCount = tokenCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(int characterCount) {
        this.characterCount = characterCount;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public String getAnalysisMessage() {
        return analysisMessage;
    }

    public void setAnalysisMessage(String analysisMessage) {
        this.analysisMessage = analysisMessage;
    }
}

