package test.prop.core;

import junit.framework.TestCase;
import prop.domain.core.Celda;
import prop.domain.core.Hoja;

import static org.junit.Assert.*;
import static prop.domain.core.Estado.*;

public class CeldaTest extends TestCase {
    Hoja h;
    Celda c,c2,c3;

    public static void main(String[] args){
        CeldaTest t = new CeldaTest();
        t.setCol();
        t.setFil();
        t.setContenido();
        t.testcol1();
        t.testcol2();
        t.testfil1();
        t.testfil2();
        t.testref1();
        t.testref2();
        t.testref3();
        t.testref4();
        t.testref5();
        System.out.println("All tests passed!");
    }

    public void creahoja(){
        h = new Hoja(10,10,0,"test");
        setHojaActual(h);
    }

    public void testcol1(){
        creahoja();
        c = new Celda(5,5,h);
        c.setCol(3);
    }

    public void testcol2(){
        creahoja();
        c = new Celda(5,5,h);
        c2 = c;
        c2.setCol(4);
    }

    public void testfil1(){
        creahoja();
        c = new Celda(5,5,h);
        c.setFil(3);
    }

    public void testfil2(){
        creahoja();
        c = new Celda(5,5,h);
        c2 = c;
        c2.setFil(4);
    }

    //referencia simple
    public void testref1(){
        creahoja();
        h.getCelda(0,0).setContenido("10.0");
        h.getCelda(4,4).setContenido("~A:1");
        h.getCelda(2,2).setContenido("~A:1");
        h.getCelda(0,0).setContenido("12.0");
        h.getCelda(0,0).setContenido("10.0");
        c = h.getCelda(0,0);
        c2 = h.getCelda( 4,4);
    }

    //autoreferencia
    public void testref2(){
        creahoja();
        h.getCelda(0,0).setContenido("20.0");
        h.getCelda(2,2).setContenido("~A:1");
        h.getCelda(2,2).setContenido("~C:3");
        c = h.getCelda(0,0);
        c2 = h.getCelda( 2,2);
    }

    //referencia circular
    public void testref3(){
        creahoja();
        h.getCelda(0,0).setContenido("20.0");
        h.getCelda(1,1).setContenido("~A:1");
        h.getCelda(2,2).setContenido("~B:2");

        h.getCelda(0,1).setContenido("12.0");

        h.setContenidoCelda(0,0,"~B:1");

        c = h.getCelda(0,0);

        h.setContenidoCelda(0,0,"~B:2");
        c = h.getCelda(0,0);
        c2 = h.getCelda( 2,2);
    }

    //operaciones con referencia
    public void testref4(){
        creahoja();

        h.setContenidoCelda(0,0,"12.0");
        h.setContenidoCelda(1,1, "8.0 + ~A:1");

        c = h.getCelda(1,1);

        h.setContenidoCelda(0,1,"3.0");
        h.setContenidoCelda(1,0,"~B:1 * 5.0");

        c2 = h.getCelda(1,0);

        h.setContenidoCelda(3,3,"~B:1 + ~A:1");
        c3 = h.getCelda(3,3);
    }

    //funciones con 1 bloque
    public void testref5(){
        creahoja();

        h.getCelda(0,1).setContenido("100.0");
        h.getCelda(1,0).setContenido("20.0");

        h.getCelda(2,2).setContenido("AVG(~A:1,~B:2) + AVG(~A:1,B:2) - AVG(~A:1,B:2)");

        c = h.getCelda(2,2);
        h.getCelda(0,0).setContenido("30.0");
    }

    @org.junit.Test //unitario
    public void setCol() {
        testcol1();
        assertEquals(3,c.getCol());
        testcol2();
        assertEquals(4,c2.getCol());
    }

    @org.junit.Test  //unitario
    public void setFil() {
        testfil1();
        assertEquals(3,c.getFil());
        testfil2();
        assertEquals(4,c2.getFil());
    }

    @org.junit.Test //integrado
    public void setContenido() {
        testref1();
        assertEquals("10.0", c2.getValor());

        testref2();
        assertEquals("20.0", c2.getValor());

        testref3();
        assertEquals("12.0", c2.getValor());

        testref4();
        assertEquals("20.0",c.getValor());
        assertEquals("15.0",c2.getValor());
        assertEquals("15.0",c3.getValor());

        testref5();
        assertEquals("37.5",c.getValor());

    }
}
