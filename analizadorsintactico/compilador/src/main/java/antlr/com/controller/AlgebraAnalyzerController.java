package antlr.com.controller;

import antlr.com.service.AlgebraAnalyzerService;
import antlr.com.dto.AnalysisRequest;
import antlr.com.dto.AnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyzer")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"}) // Para desarrollo con React
public class AlgebraAnalyzerController {

    @Autowired
    private AlgebraAnalyzerService analyzerService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResponse> analyzeExpression(@RequestBody AnalysisRequest request) {
        try {
            AnalysisResponse response = analyzerService.analyzeExpression(request.getExpression());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AnalysisResponse errorResponse = new AnalysisResponse();
            errorResponse.setSuccess(false);
            errorResponse.setError("Error al analizar la expresión: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/analyze")
    public ResponseEntity<AnalysisResponse> analyzeExpressionGet(@RequestParam(required = false) String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            AnalysisResponse errorResponse = new AnalysisResponse();
            errorResponse.setSuccess(false);
            errorResponse.setError("Parámetro 'expression' es requerido");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        try {
            AnalysisResponse response = analyzerService.analyzeExpression(expression);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AnalysisResponse errorResponse = new AnalysisResponse();
            errorResponse.setSuccess(false);
            errorResponse.setError("Error al analizar la expresión: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Analizador funcionando correctamente");
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("API del Analizador de Álgebra - Endpoints disponibles: /analyze, /health");
    }
}