package antlr.com;

import java.util.HashMap;
import java.util.Map;

public class VisitOp extends AlgebraBaseVisitor<Double>{
    Map<String, Double> memoria = new HashMap<String, Double>();

    @Override
    public Double visitAssignOp(AlgebraParser.AssignOpContext ctx) {
        // Este método se llama cuando se procesa un operador de asignación '=>'
        // Aquí se implementaría la lógica para asignar valores a variables
        AlgebraParser.EcuacionContext ecuacionCtx = (AlgebraParser.EcuacionContext) ctx.getParent();
        
        // Verificar que el lado izquierdo es una variable
        if (ecuacionCtx.expresion(0) instanceof AlgebraParser.AtomExprContext) {
            AlgebraParser.AtomExprContext atomExpr = (AlgebraParser.AtomExprContext) ecuacionCtx.expresion(0);
            if (atomExpr.atom() instanceof AlgebraParser.VarAtomContext) {
                String nombreVariable = atomExpr.atom().getText();
                Double valor = visit(ecuacionCtx.expresion(1));
                memoria.put(nombreVariable, valor);
                System.out.println("Variable " + nombreVariable + " asignada con valor " + valor);
                return valor;
            }
        }
        
        throw new RuntimeException("Error de asignación: el lado izquierdo debe ser una variable");
    }

    @Override
    public Double visitReales(AlgebraParser.RealesContext ctx) {
        return Double.parseDouble(ctx.NUMERO_REAL().getText());
    }

    @Override
    public Double visitAddSubExpr(AlgebraParser.AddSubExprContext ctx) {

        
        Double izquierda = visit(ctx.expresion(0));
        Double derecha = visit(ctx.expresion(1));
        
        if (ctx.MAS() != null) {
            return izquierda + derecha;
        } else {
            return izquierda - derecha;
        }
    }

    @Override
    public Double visitPrograma(AlgebraParser.ProgramaContext ctx) {
        Double resultado = null;
        for (AlgebraParser.EcuacionContext ecuacion : ctx.ecuacion()) {
            resultado = visit(ecuacion);
        }
        return resultado;
    }

    @Override
    public Double visitPowExpr(AlgebraParser.PowExprContext ctx) {
        Double base = visit(ctx.expresion(0));
        Double exponente = visit(ctx.expresion(1));
        return Math.pow(base, exponente);
    }
    @Override
    public Double visitMulDivExpr(AlgebraParser.MulDivExprContext ctx) {
        Double izquierda = visit(ctx.expresion(0));
        Double derecha = visit(ctx.expresion(1));
        
        if (ctx.POR() != null) {
            return izquierda * derecha;
        } else {
            // Comprobar división por cero
            if (derecha == 0) {
                throw new ArithmeticException("División por cero");
            }
            return izquierda / derecha;
        }
    }
    @Override
    public Double visitParenExpr(AlgebraParser.ParenExprContext ctx) {
        return visit(ctx.expresion());
    }
    @Override
    public Double visitAtomExpr(AlgebraParser.AtomExprContext ctx) {
        Double valor = visit(ctx.atom());
        
        // Aplicar operadores unarios (+ o -)
        if (ctx.MENOS() != null) {
            // Contar cuántos operadores MENOS hay
            int cantidadMenos = ctx.MENOS().size();
            // Si el número de MENOS es impar, cambiamos el signo
            if (cantidadMenos % 2 != 0) {
                valor = -valor;
            }
        }
        
        return valor;
    }
    @Override
    public Double visitNumberAtom(AlgebraParser.NumberAtomContext ctx) {
        return visit(ctx.reales());
    }

    @Override
    public Double visitVarAtom(AlgebraParser.VarAtomContext ctx) {
        String nombreVariable = ctx.variable().getText();
        if (memoria.containsKey(nombreVariable)) {
            return memoria.get(nombreVariable);
        } else {
            return 0.0;
        }
    }

    @Override
    public Double visitVariable(AlgebraParser.VariableContext ctx) {
        String nombreVariable = ctx.VARIABLE().getText();
        return memoria.getOrDefault(nombreVariable, 0.0);
    }

    @Override
    public Double visitEqOp(AlgebraParser.EqOpContext ctx) {
        // Este método se llama cuando se procesa un operador de igualdad '='
        // La lógica para manejar la igualdad se implementaría aquí
        System.out.println("Operador de igualdad encontrado");
        return 0.0;
    }

    @Override
    public Double visitGtOp(AlgebraParser.GtOpContext ctx) {
        // Este método se llama cuando se procesa un operador mayor que '>'
        System.out.println("Operador mayor que encontrado");
        return 0.0;
    }

    @Override
    public Double visitLtOp(AlgebraParser.LtOpContext ctx) {
        // Este método se llama cuando se procesa un operador menor que '<'
        System.out.println("Operador menor que encontrado");
        return 0.0;
    }
}

