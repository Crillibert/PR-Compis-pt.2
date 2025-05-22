package antlr.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgebraAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgebraAnalyzerApplication.class, args);
        System.out.println("Servidor del Analizador de Álgebra iniciado en http://localhost:8080");
    }
}