package prop.domain.core.dato;

import org.antlr.runtime.UnwantedTokenException;
import prop.domain.core.dato.AnttlrGen.ExprLexer;
import prop.domain.core.dato.AnttlrGen.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * @author Lluís Pujalte Feliu De Cabrera
 *
 */
public class Dato {
    public String valor;


    public Dato( String valor) {
        this.valor = valor;
    }

    public Dato() {
        this.valor = "0.0";
    }

    public String getValor() {
        return valor;
    }

    public static String insertString(String originalString,String stringToBeInserted,int index)
    {
        String newString = new String();
        for (int i = 0; i < originalString.length(); i++) {
            newString += originalString.charAt(i);
            if (i == index) {
                newString += stringToBeInserted;
            }
        }
        return newString;
    }
    private Boolean validDate(int fecha){
        int dia = fecha % 100;
        int mes = (fecha % 10000 - dia)/100;
        int year = (fecha - dia - mes*100)/10000;
        Boolean validDate = true;
        if(mes == 1 || mes == 3 || mes == 5 || mes == 7|| mes == 8|| mes == 10|| mes == 12) {//31 dias
            if(dia > 31) {
                validDate = false;
            }
        } else if(mes == 2) {
            if(((year % 4 == 0) && (year % 100 != 0)) | (year % 400 == 0)) {
                if(dia > 29) {
                    validDate = false;
                }
            } else {
                if(dia > 28) {
                    validDate = false;
                }
            }
        } else {//30 dias
            if(dia > 30) {
                validDate = false;
            }
        }
        if(mes > 12) {
            validDate = false;
        }
        return validDate;
    }
    private int incDate(int fecha) {
        int result;
        int dia = fecha % 100;
        int mes = (fecha % 10000 - dia)/100;
        int year = (fecha - dia - mes*100)/10000;
        ++dia;
        if(mes == 1 || mes == 3 || mes == 5 || mes == 7|| mes == 8|| mes == 10|| mes == 12) {//31 dias
            if(dia > 31) {
                dia = 1;
                ++mes;
            }
        } else if(mes == 2) {
            if(((year % 4 == 0) && (year % 100 != 0)) | (year % 400 == 0)) {
                if(dia > 29) {
                    dia = 1;
                    ++mes;
                }
            } else {
                if(dia > 28) {
                    dia = 1;
                    ++mes;
                }
            }
        } else {//30 dias
            if(dia > 30) {
                dia = 1;
                ++mes;
            }
        }
        if(mes > 12) {
            mes = 1;
            dia = 1;
            ++year;
        }
        result = year * 10000 + mes * 100 + dia;
        return result;
    }
    //DATEINC(20220304)
    //DATE(20220304)
    public String getValorProcesado() {
        String res = valor;
        try {
            if(valor.contains("DATE") && !valor.contains("INC")) {
                char[] ch = new char[8];
                valor.getChars(5, 13, ch, 0);
                valor = new String(ch);
                int d= Integer.parseInt(valor);
                if(!validDate(d))throw new IllegalArgumentException("Date Error");
                valor = insertString(valor, "/", 3);
                valor = insertString(valor, "/", 6);
                res = valor;
            }else if (valor.contains("DATEINC")) {
                char[] ch = new char[8];
                valor.getChars(8, 16, ch, 0);
                valor = new String(ch);
                int d= Integer.parseInt(valor);
                if(!validDate(d))throw new IllegalArgumentException("Date Error");
                valor = String.valueOf(incDate(d));
                valor = insertString(valor, "/", 3);
                valor = insertString(valor, "/", 6);
                res = valor;
            } else if (valor.charAt(0) != '"' && valor.charAt(valor.length() - 1) != '"') {
                CharStream inputStream = CharStreams.fromString(valor);
                ExprLexer exprLexer = new ExprLexer(inputStream);
                exprLexer.removeErrorListeners();
                CommonTokenStream commonTokenStream = new CommonTokenStream(exprLexer);
                ExprParser parser = new ExprParser(commonTokenStream);
                ExprParser.ExprContext exprContext = parser.expr();
                MyExprVisitor visitor = new MyExprVisitor();
                double result = visitor.visit(exprContext);
                res = String.valueOf(result);
            }
        }catch (IllegalArgumentException e) {
            res = "ERR";
        }
        return res;
    }


    public boolean isRef() {
        boolean res = false;
        if(valor.contains(":") && !valor.contains("\"")) res = true;
        return res;
    }

    public boolean isNum() {
        return !((valor.contains("\"")) || (valor.contains("DATE")) || (valor.contains("DATEINC")));
    }
}
