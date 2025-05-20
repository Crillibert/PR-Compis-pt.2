package antlr.com;
// Generated from Algebra.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AlgebraParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AlgebraVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AlgebraParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(AlgebraParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgebraParser#ecuacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEcuacion(AlgebraParser.EcuacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(AlgebraParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(AlgebraParser.AtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(AlgebraParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(AlgebraParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link AlgebraParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(AlgebraParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link AlgebraParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberAtom(AlgebraParser.NumberAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAtom}
	 * labeled alternative in {@link AlgebraParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAtom(AlgebraParser.VarAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgebraParser#reales}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReales(AlgebraParser.RealesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgebraParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(AlgebraParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOp(AlgebraParser.EqOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gtOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtOp(AlgebraParser.GtOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ltOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtOp(AlgebraParser.LtOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignOp}
	 * labeled alternative in {@link AlgebraParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOp(AlgebraParser.AssignOpContext ctx);
}