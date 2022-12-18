package test.prop.core;

import junit.framework.TestCase;
import org.junit.Test;
import prop.domain.core.Bloque;
import prop.domain.core.Celda;
import prop.domain.core.Hoja;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BloqueTest  extends TestCase {

        Hoja h;
        Bloque b;
        Bloque b2;

        public static void main(String[] args) {
            BloqueTest t = new BloqueTest();
            t.test_getNumberOfCells();
            t.test_copiar_Contenido();
            t.test_copiar_Valor();
            t.test_copiar_Valor();
            t.test_cortar_pegar();
            t.test_searchExact();
            t.test_replace();
            t.test_colSort();
            t.test_media();
            t.test_mediana();
            t.test_varianza();
            t.test_desvio();
            t.test_covarianza();
            t.test_pearson();
            System.out.println("All tests passed!");
        }

        private void hoja5x6_vacia(){
            int i = 5;
            int j = 6;
            h = new Hoja(i,j,0, "test");
            for (int ii = 0; ii < i; ++ii) {
                for (int jj = 0; jj < j; ++jj) {
                    h.setContenidoCelda(ii, jj, "\"\"");
                }
            }
        }
        private void hoja5x6_todo_a_1(){
            int i = 5;
            int j = 6;
            h = new Hoja(i,j,0, "test");
            for (int ii = 0; ii < i; ++ii) {
                for (int jj = 0; jj < j; ++jj) {
                    h.setContenidoCelda(ii, jj, "1.0");
                }
            }
        }
        private void hoja5x6_alphanumericos() {
            int i = 5;
            int j = 6;
            h = new Hoja(i, j, 0, "test");
            for (int ii = 0; ii < i; ++ii) {
                for (int jj = 0; jj < j; ++jj) {
                    if ((jj + ii) % 2 == 0) h.setContenidoCelda(ii, jj, "\"hola\"");
                    else h.setContenidoCelda(ii, jj, "2.0");
                }
            }
        }
        private void hoja5x6_conreferencias() {
            int i = 5;
            int j = 6;
            h = new Hoja(i, j, 0, "test");
            for (int ii = 0; ii < i; ++ii) {
                for (int jj = 0; jj < j; ++jj) {
                    if ((jj + ii) % 2 == 0 & ii != 0 ) h.setContenidoCelda(ii, jj, "A:1");
                    else h.setContenidoCelda(ii, jj, "2.0");
                }
            }
            h.setContenidoCelda(0, 0, "0.1");
        }

        private void inicializar_Test_numericos1(){
            hoja5x6_todo_a_1();
            b = new Bloque(h.getCelda(1,1),h.getCelda(4,2));
            b.setContenidoCelda(0,0,"2.0");
            b.setContenidoCelda(0,1,"3.0");
            b.setContenidoCelda(1,0,"4.0");
            b.setContenidoCelda(1,1,"5.0");
            b.setContenidoCelda(2,0,"25.0");
            b.setContenidoCelda(2,1,"26.0");
            b.setContenidoCelda(3,0,"27.0");
            b.setContenidoCelda(3,1,"28.0");
            b2 = new Bloque(h.getCelda(0,4),h.getCelda(3,5));
            b2.setContenidoCelda(0,0,"12.5");
            b2.setContenidoCelda(0,1,"33.6");
            b2.setContenidoCelda(1,0,"34.0");
            b2.setContenidoCelda(1,1,"55.0");
            b2.setContenidoCelda(2,0,"225.0");
            b2.setContenidoCelda(2,1,"236.0");
            b2.setContenidoCelda(3,0,"217.0");
            b2.setContenidoCelda(3,1,"248.0");
        }
        private void inicializar_Test_numericos2(){
            hoja5x6_todo_a_1();
            b = new Bloque(h.getCelda(1,0),h.getCelda(3,2));
            b.setContenidoCelda(0,0,"2.0");
            b.setContenidoCelda(0,1,"3.0");
            b.setContenidoCelda(0,2,"4.0");
            b.setContenidoCelda(1,0,"22.0");
            b.setContenidoCelda(1,1,"23.0");
            b.setContenidoCelda(1,2,"24.0");
            b.setContenidoCelda(2,0,"12.0");
            b.setContenidoCelda(2,1,"13.0");
            b.setContenidoCelda(2,2,"14.0");
            b2 = new Bloque(h.getCelda(0,3),h.getCelda(2,5));
            b2.setContenidoCelda(0,0,"52.0");
            b2.setContenidoCelda(0,1,"3.0");
            b2.setContenidoCelda(0,2,"64.9");
            b2.setContenidoCelda(1,0,"21.7");
            b2.setContenidoCelda(1,1,"32.6");
            b2.setContenidoCelda(1,2,"255.0");
            b2.setContenidoCelda(2,0,"120.1");
            b2.setContenidoCelda(2,1,"131.3");
            b2.setContenidoCelda(2,2,"144.4");
        }
        private void inicializar_Test_alphanumericos(){
            hoja5x6_alphanumericos();
            b = new Bloque(h.getCelda(1,0),h.getCelda(3,2));
            b2 = new Bloque(h.getCelda(0,3),h.getCelda(2,5));
        }

        private void inicializar_Test_con_referencias(){
            hoja5x6_conreferencias();
            b = new Bloque(h.getCelda(1,0),h.getCelda(3,2));
            b2 = new Bloque(h.getCelda(0,3),h.getCelda(2,5));
        }


        @Test
        public void test_getNumberOfCells() {
            inicializar_Test_alphanumericos();
            assertEquals(9, b2.getNumberOfCells());
            inicializar_Test_numericos1();
            assertEquals(8, b.getNumberOfCells());
        }

        @Test
        public void test_copiar_Contenido() {
            inicializar_Test_numericos1();
            b.copiar_Contenido(b2);
            assertEquals(h.getCelda(1,1).getValor(), h.getCelda(0,4).getValor());
            assertEquals(h.getCelda(1,2).getValor(), h.getCelda(0,5).getValor());
            assertEquals(h.getCelda(2,1).getValor(), h.getCelda(1,4).getValor());
            assertEquals(h.getCelda(2,2).getValor(), h.getCelda(1,5).getValor());
            assertEquals(h.getCelda(3,1).getValor(), h.getCelda(2,4).getValor());
            assertEquals(h.getCelda(3,2).getValor(), h.getCelda(2,5).getValor());
            assertEquals(h.getCelda(4,1).getValor(), h.getCelda(3,4).getValor());
            assertEquals(h.getCelda(4,2).getValor(), h.getCelda(3,5).getValor());
            inicializar_Test_numericos2();
            b2.copiar_Contenido(b);
            assertEquals(h.getCelda(1,0).getValor(), h.getCelda(0,3).getValor());
            assertEquals(h.getCelda(1,1).getValor(), h.getCelda(0,4).getValor());
            assertEquals(h.getCelda(1,2).getValor(), h.getCelda(0,5).getValor());
            assertEquals(h.getCelda(2,0).getValor(), h.getCelda(1,3).getValor());
            assertEquals(h.getCelda(2,1).getValor(), h.getCelda(1,4).getValor());
            assertEquals(h.getCelda(2,2).getValor(), h.getCelda(1,5).getValor());
            assertEquals(h.getCelda(3,0).getValor(), h.getCelda(2,3).getValor());
            assertEquals(h.getCelda(3,1).getValor(), h.getCelda(2,4).getValor());
            assertEquals(h.getCelda(3,2).getValor(), h.getCelda(2,5).getValor());
            inicializar_Test_alphanumericos();
            b2.copiar_Contenido(b);
            assertEquals(h.getCelda(1,0).getValor(), h.getCelda(0,3).getValor());
            assertEquals(h.getCelda(1,1).getValor(), h.getCelda(0,4).getValor());
            assertEquals(h.getCelda(1,2).getValor(), h.getCelda(0,5).getValor());
            assertEquals(h.getCelda(2,0).getValor(), h.getCelda(1,3).getValor());
            assertEquals(h.getCelda(2,1).getValor(), h.getCelda(1,4).getValor());
            assertEquals(h.getCelda(2,2).getValor(), h.getCelda(1,5).getValor());
            assertEquals(h.getCelda(3,0).getValor(), h.getCelda(2,3).getValor());
            assertEquals(h.getCelda(3,1).getValor(), h.getCelda(2,4).getValor());
            assertEquals(h.getCelda(3,2).getValor(), h.getCelda(2,5).getValor());
        }

        @Test
        public void test_copiar_Valor() {
            inicializar_Test_numericos1();
            b.copiar_Valor(b2);
            assertEquals(h.getCelda(1,1).getValor(), h.getCelda(0,4).getValor());
            assertEquals(h.getCelda(1,2).getValor(), h.getCelda(0,5).getValor());
            assertEquals(h.getCelda(2,1).getValor(), h.getCelda(1,4).getValor());
            assertEquals(h.getCelda(2,2).getValor(), h.getCelda(1,5).getValor());
            assertEquals(h.getCelda(3,1).getValor(), h.getCelda(2,4).getValor());
            assertEquals(h.getCelda(3,2).getValor(), h.getCelda(2,5).getValor());
            assertEquals(h.getCelda(4,1).getValor(), h.getCelda(3,4).getValor());
            assertEquals(h.getCelda(4,2).getValor(), h.getCelda(3,5).getValor());
            inicializar_Test_numericos2();
            b2.copiar_Valor(b);
            assertEquals(h.getCelda(1,0).getValor(), h.getCelda(0,3).getValor());
            assertEquals(h.getCelda(1,1).getValor(), h.getCelda(0,4).getValor());
            assertEquals(h.getCelda(1,2).getValor(), h.getCelda(0,5).getValor());
            assertEquals(h.getCelda(2,0).getValor(), h.getCelda(1,3).getValor());
            assertEquals(h.getCelda(2,1).getValor(), h.getCelda(1,4).getValor());
            assertEquals(h.getCelda(2,2).getValor(), h.getCelda(1,5).getValor());
            assertEquals(h.getCelda(3,0).getValor(), h.getCelda(2,3).getValor());
            assertEquals(h.getCelda(3,1).getValor(), h.getCelda(2,4).getValor());
            assertEquals(h.getCelda(3,2).getValor(), h.getCelda(2,5).getValor());
            inicializar_Test_alphanumericos();
            b2.copiar_Valor(b);
            assertEquals(h.getCelda(1,0).getValor(), h.getCelda(0,3).getValor());
            assertEquals(h.getCelda(1,1).getValor(), h.getCelda(0,4).getValor());
            assertEquals(h.getCelda(1,2).getValor(), h.getCelda(0,5).getValor());
            assertEquals(h.getCelda(2,0).getValor(), h.getCelda(1,3).getValor());
            assertEquals(h.getCelda(2,1).getValor(), h.getCelda(1,4).getValor());
            assertEquals(h.getCelda(2,2).getValor(), h.getCelda(1,5).getValor());
            assertEquals(h.getCelda(3,0).getValor(), h.getCelda(2,3).getValor());
            assertEquals(h.getCelda(3,1).getValor(), h.getCelda(2,4).getValor());
            assertEquals(h.getCelda(3,2).getValor(), h.getCelda(2,5).getValor());
        }

        @Test
        public void test_cortar_pegar() {
            inicializar_Test_numericos1();
            b.cortar_pegar(b2);
            assertEquals("2.0", h.getCelda(0,4).getValor());
            assertEquals("3.0", h.getCelda(0,5).getValor());
            assertEquals("4.0", h.getCelda(1,4).getValor());
            assertEquals("5.0", h.getCelda(1,5).getValor());
            assertEquals("\"\"", h.getCelda(1,1).getValor());
            assertEquals("\"\"", h.getCelda(1,2).getValor());
            assertEquals("\"\"", h.getCelda(2,1).getValor());
            assertEquals("\"\"", h.getCelda(2,2).getValor());
            inicializar_Test_numericos2();
            b2.cortar_pegar(b);
            assertEquals("\"\"", h.getCelda(0,3).getValor());
            assertEquals("\"\"", h.getCelda(0,4).getValor());
            assertEquals("\"\"", h.getCelda(0,5).getValor());
            assertEquals("\"\"", h.getCelda(1,3).getValor());
            assertEquals("32.6",h.getCelda(2,1).getValor());
            assertEquals("255.0",h.getCelda(2,2).getValor());
            assertEquals("120.1",h.getCelda(3,0).getValor());
            assertEquals("131.3",h.getCelda(3,1).getValor());
            assertEquals("144.4",h.getCelda(3,2).getValor());
            inicializar_Test_alphanumericos();
            b2.cortar_pegar(b);
            assertEquals("\"\"", h.getCelda(0,3).getValor());
            assertEquals("\"\"", h.getCelda(0,4).getValor());
            assertEquals("\"\"", h.getCelda(0,5).getValor());
            assertEquals("\"\"", h.getCelda(1,3).getValor());
            assertEquals("2.0",h.getCelda(2,1).getValor());
            assertEquals("\"hola\"",h.getCelda(2,2).getValor());
            assertEquals("2.0",h.getCelda(3,0).getValor());
            assertEquals("\"hola\"",h.getCelda(3,1).getValor());
            assertEquals("2.0",h.getCelda(3,2).getValor());
        }

        @Test
        public void test_searchExact() {
            inicializar_Test_alphanumericos();
            ArrayList<Celda> a;
            a = b.searchExact("2.0");
            assertEquals(a.get(0).getValor(), h.getCelda(1,0).getValor());
            assertEquals(a.get(1).getValor(),h.getCelda(1,2).getValor());
            assertEquals(a.get(2).getValor(),h.getCelda(2,1).getValor());
            assertEquals(a.get(3).getValor(),h.getCelda(3,0).getValor());
            assertEquals(a.get(3).getValor(),h.getCelda(3,2).getValor());
            assertEquals(5, a.size());
            a = b.searchExact("\"hola\"");
            assertEquals(a.get(0).getValor(), h.getCelda(1,1).getValor());
            assertEquals(a.get(1).getValor(),h.getCelda(2,0).getValor());
            assertEquals(a.get(2).getValor(),h.getCelda(2,2).getValor());
            assertEquals(a.get(3).getValor(),h.getCelda(3,1).getValor());
            assertEquals(4,a.size());
        }

        @Test
        public void test_replace() {
            inicializar_Test_alphanumericos();
            b.replace("\"hola\"","\"hello\"");
            assertEquals("\"hola\"", h.getCelda(0,0).getValor()); //not within the block
            assertEquals("\"hello\"", h.getCelda(1,1).getValor()); // within the block
            assertEquals("\"hello\"", h.getCelda(2,0).getValor()); // within the block
            assertEquals("\"hello\"", h.getCelda(3,1).getValor()); // within the block
            assertEquals("\"hello\"", h.getCelda(2,2).getValor()); // within the block
            assertEquals("\"hola\"", h.getCelda(0,2).getValor()); //not within the block
            assertEquals("2.0", h.getCelda(3,2).getValor());
            b.replace("2.0","150.0");
            assertEquals("\"hola\"", h.getCelda(0,0).getValor());
            assertEquals("150.0", h.getCelda(1,0).getValor());
            assertEquals("150.0", h.getCelda(1,2).getValor());
            assertEquals("150.0", h.getCelda(2,1).getValor());
            assertEquals("150.0", h.getCelda(3,0).getValor());
            assertEquals("150.0", h.getCelda(3,2).getValor());
            assertEquals("2.0", h.getCelda(3,4).getValor());
        }

        @Test
        public void test_colSort() {
            inicializar_Test_alphanumericos();
            b2.colSort(1);
            //h.printHoja();
        }

        @Test
        public void test_media() {
            inicializar_Test_numericos1();  //numero par de celdas
            assertEquals(15, b.media(), 0.0001);
            inicializar_Test_numericos2();  //numero impar de celdas
            assertEquals(13, b.media(), 0.0001);
        }

        @Test
        public void test_mediana() {
            inicializar_Test_numericos1();  //numero par de celdas
            assertEquals(15, b.mediana(), 0.0001);
            inicializar_Test_numericos2();  //numero impar de celdas
            assertEquals(13, b.mediana(), 0.0001);
        }

        @Test
        public void test_varianza() {
            inicializar_Test_numericos1();  //numero par de celdas
            assertEquals(152.5714286, b.varianza(), 0.00001);
            inicializar_Test_numericos2();  //numero impar de celdas
            assertEquals(75.75, b.varianza(), 0.00001);
        }

        @Test
        public void test_desvio() {
            inicializar_Test_numericos1();  //numero par de celdas
            assertEquals(12.35198075, b.desvio(), 0.00001);
            inicializar_Test_numericos2();  //numero impar de celdas
            assertEquals(8.703447593, b.desvio(), 0.00001);
        }

        @Test
        public void test_covarianza() {
            inicializar_Test_numericos1();  //numero par de celdas
            assertEquals(1148.0375, b.covarianza(b2), 0.00001);
            inicializar_Test_numericos2();  //numero par de celdas
            assertEquals(240.5, b.covarianza(b2), 0.00001);
        }

        @Test
        public void test_pearson() {
            inicializar_Test_numericos2();  //numero par de celdas=
            assertEquals(0.3917592559, b.pearson(b2), 0.00001);
        }

}