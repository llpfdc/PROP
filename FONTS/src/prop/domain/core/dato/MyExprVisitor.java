package prop.domain.core.dato;
import static java.lang.Math. *;

import org.antlr.v4.runtime.*;
import prop.domain.core.Bloque;
import prop.domain.core.Celda;
import prop.domain.core.Estado;
import prop.domain.core.Hoja;
import prop.domain.core.dato.AnttlrGen.ExprBaseVisitor;
import prop.domain.core.dato.AnttlrGen.ExprLexer;
import prop.domain.core.dato.AnttlrGen.ExprParser;

/**
 * @author Lluís Pujalte Feliu De Cabrera
 *
 */
public class MyExprVisitor extends ExprBaseVisitor<Double> {
    private int getCol(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i){
            res += (s.charAt(i) - 'A') * Math.pow(26,s.length()-i-1) ;
        }
        return res;
    }

    @Override
    public Double visitExponentExpr(ExprParser.ExponentExprContext ctx) {
        double exponente = visit(ctx.getChild(2));
        double base = visit(ctx.getChild(0));
        return pow(exponente,base);
    }
//AVG(~B:1,~C:2)
    @Override
    public Double visitFunctionDBExpr(ExprParser.FunctionDBExprContext ctx) {

        String name = ctx.NAME(0).toString();
        double result = 0.0;
        //AVG(~B:5,~F:6)
        String num1 = ctx.ID(0).toString();
        String num2 = ctx.ID(1).toString();
        String num3 = ctx.ID(2).toString();
        String num4 = ctx.ID(3).toString();
        int fila1 = Integer.parseInt(num1) - 1;
        int fila2 = Integer.parseInt(num2) - 1;
        int fila3 = Integer.parseInt(num3) - 1;
        int fila4 = Integer.parseInt(num4) - 1;
        int col1 = getCol(ctx.NAME(1).getText());
        int col2 = getCol(ctx.NAME(2).getText());
        int col3 = getCol(ctx.NAME(3).getText());
        int col4 = getCol(ctx.NAME(4).getText());

        Hoja h = Estado.getHojaActual();
        Celda c1 = h.getCelda(fila1,col1);
        Celda c2 = h.getCelda(fila2,col2);
        Bloque b = new Bloque(c1,c2);
        Celda c3 = h.getCelda(fila3,col3);
        Celda c4 = h.getCelda(fila4,col4);
        Bloque b2 = new Bloque(c3,c4);
        double res = 0.0;
        if(name.equals("COV") ) {
            res = b.covarianza(b2);
        } else if (name.equals("PEARS") ) {
            res = b.pearson(b2);
        }
        return res;

    }

    @Override
    public Double visitNumericExpr(ExprParser.NumericExprContext ctx) {
        return Double.parseDouble(ctx.NUM().getText());
    }

    @Override
    public Double visitAddMinusExpr(ExprParser.AddMinusExprContext ctx) {
        double left = visit(ctx.getChild(0));
        double right = visit(ctx.getChild(2));
        double result = 0;
        if(ctx.MINUS() != null) {
            result = left - right;
        } else if(ctx.PLUS() != null) {
            result = left + right;
        }
        return result;
    }

    @Override
    public Double visitMultDivExpr(ExprParser.MultDivExprContext ctx) {
        double left = visit(ctx.getChild(0));
        double right = visit(ctx.getChild(2));
        if(right == 0.0) throw new IllegalArgumentException("Can not divide by 0");
        double result = 0;
        if(ctx.MULT() != null) {
            result = left * right;
        } else if(ctx.DIV() != null) {
            result = left / right;
        }
        return result;
    }


    @Override
    public Double visitParentExpr(ExprParser.ParentExprContext ctx) {
        return visit(ctx.getChild(1));
    }

    @Override
    public Double visitFunctionExpr(ExprParser.FunctionExprContext ctx) {
        String name = ctx.NAME().getText();
        double result = 0.0;
        double ex = visit(ctx.getChild(2));

        switch (name) {
            case "SQRT":
                if(ex < 0.0) throw new IllegalArgumentException("sqrt of a negative number");
                else result = Math.sqrt(ex);
                break;
            case "LN":
                result = Math.log(ex);
                break;
            case "ROUND":
                result = Math.round(ex);
                break;
            case "TRUNC":
                result = (int)ex;
                break;
            case "INC":
                result = ex + 1;
                break;
            case "DEC":
                result = ex - 1;
                break;
            case "ABS":
                result = Math.abs(ex);
                break;
            default:
                break;
        }
        return result;

    }
    private Double resolve(String v) {
        Dato d = new Dato(v);
        double res = Double.parseDouble(d.getValorProcesado());
        return  res;
    }

    @Override
    public Double visitNumericNegExpr(ExprParser.NumericNegExprContext ctx) {
        return resolve(ctx.getChild(1).getText())*(-1.0);
    }

    @Override
    public Double visitFunctionDExpr(ExprParser.FunctionDExprContext ctx) {
        String name = ctx.NAME(0).toString();
        double result = 0.0;
        //AVG(~B:5,~F:6)
        String num1 = ctx.ID(0).toString();
        String num2 = ctx.ID(1).toString();
        int fila1 = Integer.parseInt(num1) - 1;
        int fila2 = Integer.parseInt(num2) - 1;
        int col1 = getCol(ctx.NAME(1).getText());
        int col2 = getCol(ctx.NAME(2).getText());

        Hoja h = Estado.getHojaActual();
        Celda c1 = h.getCelda(fila1,col1);
        Celda c2 = h.getCelda(fila2,col2);
        Bloque b = new Bloque(c1,c2);
        switch (name) {
            case "AVG":
                result = b.media();
                break;
            case "MED":
                result = b.mediana();
                break;
            case "VAR":
                result = b.varianza();
                break;
            case "DESV":
                result =  b.desvio();
                break;
        }
        return result;
    }

    @Override
    public Double visitIdExpr(ExprParser.IdExprContext ctx) {
        String n = ctx.NAME().getText();
        String s = ctx.ID().getText();
        int fila = Integer.parseInt(s) -1;
        int col = 0;
        for(int i = 0; i < n.length(); ++i) {
            col += (n.charAt(i) - 'A')* Math.pow(26,s.length() - i - 1);
        }
        Hoja h = Estado.getHojaActual();
        Celda c = h.getCelda(fila,col);
        return resolve(h.getCellContent(fila,col));
    }
}
