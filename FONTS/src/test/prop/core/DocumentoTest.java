package test.prop.core;

import junit.framework.TestCase;
import prop.domain.core.Documento;
import prop.domain.core.Hoja;

import java.util.*;


public class DocumentoTest extends TestCase {

    Documento d;

    public void testGetNombre() {
        d = new Documento("nombre", 10, 10);
        assertEquals("nombre", d.getNombre());
    }

    public void testGetNumHojas() {
        d = new Documento("nombre", 10, 10);
        assertEquals(1, d.getNumHojas());
        d.addHoja(10,10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        assertEquals(5, d.getNumHojas());
    }

    public void testGetHojas() {
        d = new Documento("nombre", 10, 10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        List <Hoja> vec = d.getHojas();
        assertEquals(5, vec.size());
    }

    public void testGetHoja() {
        d = new Documento("nombre", 10, 10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        d.addHoja(10,10);
        Hoja h = d.getHoja(0);
        assertEquals("Hoja 1",h.getNombre());
        h = d.getHoja(1);
        assertEquals("Hoja 2",h.getNombre());
        h = d.getHoja(2);
        assertEquals("Hoja 3",h.getNombre());
        h = d.getHoja(3);
        assertEquals("Hoja 4",h.getNombre());
        h = d.getHoja(4);
        assertEquals("Hoja 5",h.getNombre());

        }

    public void testSetNombre() {
        d = new Documento("nombre", 10, 10);
        d.setNombre("test");
        assertEquals("test", d.getNombre());
        d.setNombre("test2");
        assertEquals("test2", d.getNombre());
    }

    public void testAddHoja() {
        d = new Documento("nombre", 10, 10);
        int x =10;
        int y = 5;
        for (int i = 0; i < 9; ++i){
            d.addHoja(x, y);
            assertEquals(i+2,d.getNumHojas());
            y += 5;
            y += 10;
        }
    }

    public void testRemoveHoja() {
        d = new Documento("nombre", 10, 5);
        int x =20;
        int y = 10;
        for (int i = 0; i < 9; ++i){
            d.addHoja(x, y);
            assertEquals(i+2,d.getNumHojas());
            x += 10;
            y += 5;
        }
        for (int i = 0; i < 9; ++i){
            d.removeHoja(0);
            assertEquals(9-i,d.getNumHojas());
            assertEquals(10+((i+1)*10),d.getHoja(0).getAltura());
            assertEquals(5+((i+1)*5),d.getHoja(0).getAnchura());
        }
        d = new Documento("nombre", 10, 5);
        x =20;
        y = 10;
        for (int i = 0; i < 9; ++i){
            d.addHoja(x, y);
            assertEquals(i+2,d.getNumHojas());
            x += 10;
            y += 5;
        }
        d.removeHoja( 5);
        assertEquals( 70,d.getHoja(5).getAltura());
        assertEquals(35,d.getHoja(5).getAnchura());
        assertEquals( 50,d.getHoja(4).getAltura());
        assertEquals(25,d.getHoja(4).getAnchura());

    }

    public void testModificarNombreHoja() {
        d = new Documento("nombre", 10, 5);
        d.modificarNombreHoja(0, "nombrecambiado");
        assertEquals( "nombrecambiado",d.getHoja(0).getNombre());
        int x =20;
        int y = 10;
        for (int i = 0; i < 9; ++i){
            d.addHoja(x, y);
            assertEquals(i+2,d.getNumHojas());
            x += 10;
            y += 5;
        }
        d.modificarNombreHoja(5, "nombrecambiado2");
        assertEquals( "nombrecambiado2",d.getHoja(5).getNombre());
    }

    public void testPrintHoja() {
    }

    public static void main(String[] args) {
        DocumentoTest t = new DocumentoTest();
        System.out.println("All tests passed!");
    }
}