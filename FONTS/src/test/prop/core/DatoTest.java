package test.prop.core;

import junit.framework.TestCase;
import prop.domain.core.Celda;
import prop.domain.core.Hoja;
import prop.domain.core.dato.Dato;
import static prop.domain.core.Estado.*;
public class DatoTest extends TestCase {
    Hoja h;
    public static void main(String[] args) {
        DatoTest t = new DatoTest();
        t.creaHoja();
        t.testGetValor();
        t.testGetValorProcesadoInmediate();
        t.testGetValorProcesadoSuma();
        t.testGetValorProcesadoResta();
        t.testGetValorProcesadoRestaNeg();
        t.testGetValorProcesadoMult();
        t.testGetValorProcesadoDiv();
        t.testGetValorProcesadoDivErr();
        t.testGetValorProcesadoExp();
        t.testGetValorProcesadoParentesis();
        t.testGetValorProcesadoString();
        t.testGetValorProcesadoStringEmpty();
        t.testGetValorProcesadoFuncSqrt();
        t.testGetValorProcesadoFuncSqrtErr();
        t.testGetValorProcesadoFuncLog();
        t.testGetValorProcesadoFuncRound();
        t.testGetValorProcesadoFuncTrunc();
        t.testGetValorProcesadoFuncInc();
        t.testGetValorProcesadoFuncDec();
        t.testGetValorProcesadoFuncAbs();
        t.testGetValorProcesadoFuncDate();
        t.testGetValorProcesadoFuncDateInc();
        t.testGetValorProcesadoRef();
        t.testGetValorProcesadoAVG();
        t.testGetValorProcesadoRefInAFunc();
        t.testGetValorProcesadoFuncErr();
        t.testGetValorProcesadoCov();
        t.testGetValorProcesadoComplexExpr();
        t.testIsRefTrue();
        t.testIsRefFalse();
        t.testIsNumTrue();
        t.testIsNumFalse();
        System.out.println("All tests passed!");
    }
    private void creaHoja(){
        h = new Hoja(3,3,0,"test");
        setHojaActual(h);
    }
    public void testGetValor() {
        Dato tester = new Dato("5.0+2.0");
        assertEquals("5.0+2.0",tester.getValor());
    }

    public void testGetValorProcesadoInmediate() {
        Dato tester = new Dato("5.0");
        assertEquals("5.0",tester.getValorProcesado());
    }
    public void testGetValorProcesadoSuma() {
        Dato tester = new Dato("5.0+2.0");
        assertEquals("7.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoResta() {
        Dato tester = new Dato("5.0-2.0");
        assertEquals("3.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoRestaNeg() {
        Dato tester = new Dato("2.0-3.0");
        assertEquals("-1.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoMult() {
        Dato tester = new Dato("2.0*3.0");
        assertEquals("6.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoDiv() {
        Dato tester = new Dato("4.0/2.0");
        assertEquals("2.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoDivErr() {
        Dato tester = new Dato("4.0/0.0");
        assertEquals("ERR",tester.getValorProcesado());
    }

    public void testGetValorProcesadoExp() {
        Dato tester = new Dato("2.0^2.0");
        assertEquals("4.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoParentesis() {
        Dato tester = new Dato("2.0*(2.0+2.0)");
        assertEquals("8.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoString() {
        Dato tester = new Dato("\"Hello\"");
        assertEquals("\"Hello\"",tester.getValorProcesado());
    }

    public void testGetValorProcesadoStringEmpty() {
        Dato tester = new Dato("\"\"");
        assertEquals("\"\"",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncSqrt() {
        Dato tester = new Dato("SQRT(4.0)");
        assertEquals("2.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncSqrtErr() {
        Dato tester = new Dato("SQRT(-4.0)");
        assertEquals("ERR",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncLog() {
        Dato tester = new Dato("LN(4.0)");
        assertEquals("1.3862943611198906",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncRound() {
        Dato tester = new Dato("ROUND(4.5)");
        assertEquals("5.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncTrunc() {
        Dato tester = new Dato("TRUNC(4.5)");
        assertEquals("4.0",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncInc() {
        Dato tester = new Dato("INC(4.5)");
        assertEquals("5.5",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncDec() {
        Dato tester = new Dato("DEC(4.5)");
        assertEquals("3.5",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncAbs() {
        Dato tester = new Dato("ABS(-4.5)");
        assertEquals("4.5",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncDate() {
        Dato tester = new Dato("DATE(20220603)");
        assertEquals("2022/06/03",tester.getValorProcesado());
    }

    public void testGetValorProcesadoFuncDateInc() {
        Dato tester = new Dato("DATEINC(20220331)");
        assertEquals("2022/04/01",tester.getValorProcesado());
    }

    public void testGetValorProcesadoRef() {
        creaHoja();
        h.setContenidoCelda(0,0,"1.0");//A:1
        h.setContenidoCelda(0,1,"2.0");
        h.setContenidoCelda(1,0,"2.0");
        h.setContenidoCelda(1,1,"~A:1");
        assertEquals("1.0",h.getCelda(1,1).getValor());
    }

    public void testGetValorProcesadoAVG() {
        creaHoja();
        h.setContenidoCelda(0,0,"1.0");//A:1
        h.setContenidoCelda(0,1,"2.0");
        h.setContenidoCelda(1,0,"2.0");
        h.setContenidoCelda(1,1,"5.0");
        h.setContenidoCelda(2,2,"AVG(~A:1,~B:2)");
        assertEquals("2.5",h.getCelda(2,2).getValor());
    }

    public void testGetValorProcesadoRefInAFunc() {
        creaHoja();
        h.setContenidoCelda(0,0,"4.0");//A:1
        h.setContenidoCelda(2,2,"SQRT(~A:1)");
        assertEquals("2.0",h.getCelda(2,2).getValor());
    }

    public void testGetValorProcesadoFuncErr() {
        creaHoja();
        h.setContenidoCelda(2,2,"SQRT(-4.0)");
        assertEquals("ERR",h.getCelda(2,2).getValor());
    }


    public void testGetValorProcesadoCov() {
        creaHoja();
        h.setContenidoCelda(0,0,"1.0");//A:1
        h.setContenidoCelda(0,1,"2.0");
        h.setContenidoCelda(1,0,"2.0");
        h.setContenidoCelda(1,1,"5.0");
        h.setContenidoCelda(2,2,"COV(~A:1,~A:2,~B:1,~B:2)");
        assertEquals("0.75",h.getCelda(2,2).getValor());
    }

    public void testGetValorProcesadoComplexExpr() {
        creaHoja();
        h.setContenidoCelda(0,0,"1.0");//A:1
        h.setContenidoCelda(0,1,"2.0");
        h.setContenidoCelda(1,0,"6.0");
        h.setContenidoCelda(1,1,"5.0");
        h.setContenidoCelda(2,2,"AVG(~A:1,~A:2)+4.0*(SQRT(4.0)^~A:1)");
        assertEquals("7.5",h.getCelda(2,2).getValor());
    }

    public void testIsRefTrue() {
        Dato tester = new Dato("~B:4");
        assertTrue(tester.isRef());
    }

    public void testIsRefFalse() {
        Dato tester = new Dato("5.0+4.0");
        assertFalse(tester.isRef());
    }

    public void testIsNumTrue() {
        Dato tester = new Dato("5.0");
        assertTrue(tester.isNum());
    }

    public void testIsNumFalse() {
        Dato tester = new Dato("DATE(20220304.0)");
        assertFalse(tester.isNum());
    }

}