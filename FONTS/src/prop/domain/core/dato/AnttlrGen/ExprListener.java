// Generated from C:/Users/Luis/Desktop/subgrup-prop2-3-master-0d932889cad1e938ad8dd5a3c335e76b9e5e509c/src/prop/core/dato/dev\Expr.g4 by ANTLR 4.9.2
package prop.domain.core.dato.AnttlrGen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code functionDBExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDBExpr(ExprParser.FunctionDBExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionDBExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDBExpr(ExprParser.FunctionDBExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exponentExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExponentExpr(ExprParser.ExponentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exponentExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExponentExpr(ExprParser.ExponentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumericExpr(ExprParser.NumericExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumericExpr(ExprParser.NumericExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addMinusExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddMinusExpr(ExprParser.AddMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addMinusExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddMinusExpr(ExprParser.AddMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionDExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDExpr(ExprParser.FunctionDExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionDExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDExpr(ExprParser.FunctionDExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericNegExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumericNegExpr(ExprParser.NumericNegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericNegExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumericNegExpr(ExprParser.NumericNegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multDivExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDivExpr(ExprParser.MultDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multDivExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDivExpr(ExprParser.MultDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParentExpr(ExprParser.ParentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParentExpr(ExprParser.ParentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpr(ExprParser.FunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpr(ExprParser.FunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(ExprParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(ExprParser.IdExprContext ctx);
}