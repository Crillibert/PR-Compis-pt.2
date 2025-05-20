// Generated from c:/Users/Gabriel/Documents/GitHub/PR-Compis-pt.2/analizadorsintactico/compilador/src/main/java/antlr/com/Algebra.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AlgebraParser}.
 */
public interface AlgebraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AlgebraParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(AlgebraParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgebraParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(AlgebraParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgebraParser#ecuacion}.
	 * @param ctx the parse tree
	 */
	void enterEcuacion(AlgebraParser.EcuacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgebraParser#ecuacion}.
	 * @param ctx the parse tree
	 */
	void exitEcuacion(AlgebraParser.EcuacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(AlgebraParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(AlgebraParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(AlgebraParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(AlgebraParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(AlgebraParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(AlgebraParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(AlgebraParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(AlgebraParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(AlgebraParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(AlgebraParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link AlgebraParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(AlgebraParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link AlgebraParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(AlgebraParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varAtom}
	 * labeled alternative in {@link AlgebraParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterVarAtom(AlgebraParser.VarAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varAtom}
	 * labeled alternative in {@link AlgebraParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitVarAtom(AlgebraParser.VarAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgebraParser#reales}.
	 * @param ctx the parse tree
	 */
	void enterReales(AlgebraParser.RealesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgebraParser#reales}.
	 * @param ctx the parse tree
	 */
	void exitReales(AlgebraParser.RealesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgebraParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(AlgebraParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgebraParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(AlgebraParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterEqOp(AlgebraParser.EqOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitEqOp(AlgebraParser.EqOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gtOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterGtOp(AlgebraParser.GtOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gtOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitGtOp(AlgebraParser.GtOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ltOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterLtOp(AlgebraParser.LtOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ltOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitLtOp(AlgebraParser.LtOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterAssignOp(AlgebraParser.AssignOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitAssignOp(AlgebraParser.AssignOpContext ctx);
}