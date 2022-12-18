package test.prop.core;

import junit.framework.TestCase;
import prop.domain.core.Celda;
import prop.domain.core.Hoja;
import prop.domain.core.Estado;

import java.util.ArrayList;

public class HojaTest extends TestCase {

    Hoja h;

    public static void main(String[] args){
        HojaTest t = new HojaTest();
        t.testGetCelda();
        t.testGetAltura();
        t.testGetAnchura();
        t.testGetNombre();
        t.testGetId();
        t.testGetCellContent();
        t.testGetUnprocessedCellContent();
        t.testSetId();
        t.testTestSetName();
        t.testSetContenidoCelda();
        t.testAddFila();
        t.testRemoveFila();
        t.testAddColumna();
        t.testRemoveColumna();
        t.testSearchAndReplaceNotMatchLowerNorUpperCase();
        t.testSearchAndReplace();
        t.testSearchAndReplaceExactNotMatchLowerNorUpperCase();
        t.testSearchAndReplaceExact() ;
        t.testSearch();
        t.testSearchExact();
        System.out.println("All tests passed!");
    }

    public void testGetCelda() {
        h = new Hoja(10, 10,0,"tester");
        Celda c = h.getCelda(9,9);
        assertEquals(h,c.getHoja());
        assertEquals(9,c.getCol());
        assertEquals(9,c.getFil());
    }

    public void testGetAltura() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals(10, h.getAltura());
        h = new Hoja(100, 20,0,"tester");
        assertEquals(100, h.getAltura());
    }

    public void testGetAnchura() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals(10, h.getAltura());
        h = new Hoja(100, 200,0,"tester");
        assertEquals(100, h.getAltura());
    }

    public void testGetNombre() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals("tester", h.getNombre());
        h.setName("cambio");
        assertEquals("cambio", h.getNombre());
    }

    public void testGetId() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals(0, h.getId());
        h = new Hoja(10, 20,55,"tester");
        assertEquals(55, h.getId());
    }

    public void testGetCellContent() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals("0.0", h.getCellContent(2,2));
        h.setContenidoCelda(4,4, "\"cambio\"");
        assertEquals("\"cambio\"", h.getCellContent(4,4));
        h.setContenidoCelda(4,4, "2.0+2.0");
        assertEquals("4.0", h.getCellContent(4,4));
    }

    public void testGetUnprocessedCellContent() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals("0.0", h.getCellContent(2,2));
        h.setContenidoCelda(4,4, "\"cambio\"");
        assertEquals("\"cambio\"", h.getUnprocessedCellContent(4,4));
        h.setContenidoCelda(4,4, "2.0+2.0");
        assertEquals("2.0+2.0", h.getUnprocessedCellContent(4,4));
    }

    public void testSetId() {
        h = new Hoja(10, 20,0,"tester");
        h.setId(5);
        assertEquals(5,h.getId());
    }

    public void testTestSetName() {
        h = new Hoja(10, 20,0,"tester");
        assertEquals("tester",h.getNombre());
        h.setName("newname");
        assertEquals("newname",h.getNombre());
    }

    public void testSetContenidoCelda() {
        h = new Hoja(10, 20,0,"tester");
        h.setContenidoCelda(5,5, "2.0");
        assertEquals("2.0",h.getCellContent(5,5));
        h.setContenidoCelda(5,5, "2.0+2.0");
        assertEquals("4.0",h.getCellContent(5,5));
    }

    public void testAddFila() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "2.0");
            }
        }
        h.addFila();
        assertEquals(11, h.getAltura());
        h.addFila(5);
        assertEquals(12, h.getAltura());
        assertEquals("2.0", h.getCellContent(4,1));
        assertEquals("0.0", h.getCellContent(5,1));
        assertEquals("2.0", h.getCellContent(4,1));

    }

    public void testRemoveFila() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "1.0");
            }
        }
        for (int i = 0; i < 20; ++i){
                h.setContenidoCelda(5,i, "2.0");
        }
        h.removeFila(5);
        assertEquals(9, h.getAltura());
        assertEquals("1.0", h.getCellContent(4,1));
        assertEquals("1.0", h.getCellContent(5,1));
    }

    public void testAddColumna() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "\"vieja\"");
            }
        }
        h.addColumna();
        assertEquals(21, h.getAnchura());
        h.addColumna(5);
        assertEquals(22, h.getAnchura());
        assertEquals("\"vieja\"", h.getCellContent(1,4));
        assertEquals("0.0", h.getCellContent(1,5));
        assertEquals("\"vieja\"", h.getCellContent(1,6));
    }

    public void testRemoveColumna() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "1.0");
            }
        }
        for (int i = 0; i < 10; ++i){
            h.setContenidoCelda(i,10, "2.0");
        }
        h.removeColumna(10);
        assertEquals(19, h.getAnchura());
        assertEquals("1.0", h.getCellContent(1,9));
        assertEquals("1.0", h.getCellContent(1,10));
        assertEquals("1.0", h.getCellContent(1,11));
    }

    public void testSearchAndReplaceNotMatchLowerNorUpperCase() {
    }

    public void testSearchAndReplace() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "\"hola\"");
            }
        }
        ArrayList <Celda> vec = h.searchAndReplace("hola","hello");
        assertEquals(vec.size(),h.getAnchura()*h.getAltura());
        assertEquals("\"hello\"", h.getCellContent(2,2));

       /*h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                if(j%2 == 0) {
                    h.setContenidoCelda(i, j, "2.2+2.2");
                }
            }
        }
        vec = h.search("4.4");
        assertEquals(vec.size(),(h.getAnchura()*h.getAltura())/2);*/
    }

    public void testSearchAndReplaceExactNotMatchLowerNorUpperCase() {
    }

    public void testSearchAndReplaceExact() {
        h = new Hoja(10, 20,0,"tester");
        Estado.setHojaActual(h);

        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "\"hola\"");
            }
        }
        ArrayList <Celda> vec = h.searchAndReplaceExact("\"hola\"","\"hello\"");
        assertEquals(vec.size(),h.getAnchura()*h.getAltura());
        assertEquals("\"hello\"", h.getCellContent(2,2));

        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                if(j%2 == 0) {
                    h.setContenidoCelda(i, j, "2.2+2.2");
                }
            }
        }
        vec = h.search("4.4");
        assertEquals(vec.size(),(h.getAnchura()*h.getAltura())/2);
    }


    public void testSearch() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "\"hola\"");
            }
        }
        ArrayList <Celda> vec = h.search("hola");
        assertEquals(vec.size(),h.getAnchura()*h.getAltura());

        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                if(j%2 == 0) {
                    h.setContenidoCelda(i, j, "2.2+2.2");
                }
            }
        }
        vec = h.search("4.4");
        assertEquals(vec.size(),(h.getAnchura()*h.getAltura())/2);
    }


    public void testSearchExact() {
        h = new Hoja(10, 20,0,"tester");
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                h.setContenidoCelda(i,j, "\"hola\"");
            }
        }
        ArrayList <Celda> vec = h.searchExact("\"hola\"");
        ArrayList <Celda> vec2 = h.searchExact("\"Hola\"");
        assertEquals(vec2.size(),0);
        assertEquals(vec.size(),h.getAnchura()*h.getAltura());

        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 20; ++j){
                if(j%2 == 0) {
                    h.setContenidoCelda(i, j, "2.2+2.2");
                }
            }
        }
        vec = h.search("4.4");
        assertEquals(vec.size(),(h.getAnchura()*h.getAltura())/2);
    }


}