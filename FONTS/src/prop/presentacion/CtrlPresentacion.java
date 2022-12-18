package prop.presentacion;

import prop.domain.*;
import prop.persistence.CtrlPersistencia;

import javax.swing.*;
import java.io.IOException;

/**
 * @author Javier Abella Nieto
 */
public class CtrlPresentacion {

    private static CtrlDominio dom;
    private static CtrlPersistencia per;
    private  VistaPrincipal vistaPrincipal;
    private VistaBienvenida vistaBienvenida;
    public CtrlPresentacion(){
        dom = new CtrlDominio();
        per = new CtrlPersistencia(dom);
        //dom.crear_documento("Nuevo",100,100);
        vistaPrincipal = new VistaPrincipal(this);
        vistaBienvenida = new VistaBienvenida(this);
    }

    public void newDoc(String nombre,int filas, int columnas) {
        dom.crear_documento(nombre,columnas,filas);
        vistaPrincipal = new VistaPrincipal(this);
    }

    //constructor e inicialización
    public void inicializarBienvenida(){
        vistaBienvenida.hacerVisible();
        vistaPrincipal.hacerInvisible();
    }

    public void inicializarPresentacion() {
        vistaBienvenida.hacerInvisible();
        vistaPrincipal.hacerVisible();
    }
    public JTabbedPane getTabbedPane() {
        return vistaPrincipal.getTabbedPane();
    }

    public JFrame getFrame() {
        return vistaPrincipal.getFrame();
    }
    //métodos de sincronización entre vistas
    public void visAddCol(){
        vistaPrincipal.desactivar();
        VistaSecundariaAddCol vistaAddCol = new VistaSecundariaAddCol(this);
        vistaAddCol.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visAddFila(){
        vistaPrincipal.desactivar();
        VistaSecundariaAddFila vistaAddFila = new VistaSecundariaAddFila(this);
        vistaAddFila.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visAddHoja(){
        vistaPrincipal.desactivar();
        VistaSecundariaAddHoja vistaAddHoja = new VistaSecundariaAddHoja(this);
        vistaAddHoja.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visRemoveFila(){
        vistaPrincipal.desactivar();
        VistaSecundariaRemoveFil vistaRmFil = new VistaSecundariaRemoveFil(this);
        vistaRmFil.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visRemoveCol(){
        vistaPrincipal.desactivar();
        VistaSecundariaRemoveCol vistaRmCol = new VistaSecundariaRemoveCol(this);
        vistaRmCol.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visRenameDocument(){
        vistaPrincipal.desactivar();
        VistaSecundariaRenameDoc vistaRenameDoc = new VistaSecundariaRenameDoc(this);
        vistaRenameDoc.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visImportCSV(){
        vistaPrincipal.desactivar();
        VistaSecundariaImportCSV vistaImportCSV = new VistaSecundariaImportCSV(this);
        vistaImportCSV.hacerVisible();


    }

    public void visExportCSV(){
        vistaPrincipal.desactivar();
        VistaSecundariaExportCSV exportCSV = new VistaSecundariaExportCSV(this);
        exportCSV.hacerVisible();
    }

    public void setVistaPrincipal(VistaPrincipal a) {
        vistaPrincipal = a;
        inicializarPresentacion();
    }
    public void vistaBuscarYReemplazar(){
        vistaPrincipal.desactivar();
        VistaSecundariaBuscarYRemplazar vistaSearchAndReplace = new VistaSecundariaBuscarYRemplazar(this);
        vistaSearchAndReplace.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void vistaBuscar(){
        vistaPrincipal.desactivar();
        VistaSecundariaBusquedaHoja vistaBusqueda = new VistaSecundariaBusquedaHoja(this);
        vistaBusqueda.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public void visRenameHoja(){
        vistaPrincipal.desactivar();
        VistaSecundariaRenameHoja vistaRenameHoja = new VistaSecundariaRenameHoja(this);
        vistaRenameHoja.hacerVisible();

        vistaPrincipal.actualizarhoja();
        vistaPrincipal.activar();
    }

    public String getNameHojaAct() {
        return dom.get_nombre_hoja_actual();
    }

    //getters
    /**
     * @return nombre del documento
     */
    public String getNombreDoc(){
        return dom.get_doc_name();
    }
    /**
     * @return numero de hojas en el documento
     */
    public int getNumHojas(){return dom.get_doc_size();}
    /**
     * @param id identificador de la hoja
     * @return el nombre de la hoja
     */
    public String getNombreHoja(int id){
        return dom.get_nombre_hoja(id);
    }
    /**
     * @return número de columnas de la hoja actual
     */
    public int getNumColumnas(){
        int id = dom.get_id_hoja_actual();
        return dom.get_num_columnas_hoja(id);
    }
    /**
     * @return numero de filas de la hoja
     */
    public int getNumFilas(){
        int id = dom.get_id_hoja_actual();
        return dom.get_num_filas_hoja(id);
    }

    public boolean getCellini(int x, int y) {
        return dom.getcellIni(x,y);
    }

    /**
     * @return valor del contenido que hay en la celda x, y de la hoja actual
     */
    public String getValorCelda(int x, int y) {
        return dom.get_contenido_celda(x,y);
    }

    /**
     * @return contenido de la celda x, y de la hoja actual
     */
    public String getContenidoCelda (int x, int y) {
        return dom.get_contenido_no_procesado_celda(x,y);
    }

    public int getHojaActual(){
        return dom.get_id_hoja_actual();
    }
    //setters
    /**
     * @param x coordenada x de la celda
     * @param y coordenada y de la celda
     * @param s nuevo contenido de la celda
     */
    public void setContenidoCelda(int x, int y, String s){
        dom.modify_contenido_celda(x,y,s);
        /*try {
            dom.modify_contenido_celda(x,y,s);
        }catch (referenciaCircular e) {

        }catch (autoReferencia e){

        }*/
    }
    public void setNombreHoja(String s, int id){
        dom.rename_hoja(id,s);
    }
    public void setNombreDoc(String s){
        dom.rename_doc(s);
    }
    public void setHojaActual(int id){
        dom.select_hoja(id);
    }

    // add operations
    /**
     * @param height altura de la nueva hoja a añadir
     * @param width anchura de la nueva hoja a añadir
     */
    public void addHoja(int height, int width){
        dom.add_hoja( height, width);
        vistaPrincipal.agregarHoja();
    }
    public int getNumColumnasId(int id) {
        return dom.get_num_columnas_hoja(id);
    }
    /**
     * @param idFil identificador de la nueva fila a añadir
     */
    public void addFila(int idFil) {
        dom.add_fila(idFil);
    }
    /**
     * añade una fila al final de la hoja
     */
    public void addFilaExtra(){dom.add_columna();}
    /**
     * @param idCol identificador de la nueva columna a añadir
     */
    public void addColumna(int idCol){
        dom.add_columna(idCol);
    }
    /**
     * añade columna al final de la hoja
     */
    public void addColumnaExtra(){dom.add_columna();}

    //remove operations
    /**
     * @param id identificador de la hoja a eliminar
     */
    public void rmHoja(int id){ dom.remove_hoja(id);}
    /**
     * @param idFil identificador de la fila a eliminar
     */
    public void rmFila(int idFil){dom.remove_fila(idFil);}
    /**
      * @param idCol identificador de la columna a eliminar
     */
    public void rmColumna(int idCol){dom.remove_columna(idCol);}

    //otras
    public void copiar(int fil1, int col1, int fil2, int col2, int x1, int y1, int x2, int y2){
        dom.bloque_copiar_valor(fil1,col1,fil2,col2,x1,y1,x2,y2);
    }

    public void reemplazo(String buscar, String reemplazar){
        dom.hoja_reeemplazar_contenido_exacto_sin_mayus_minus(buscar, reemplazar);
    }
    public void actualizarHoja() {
        vistaPrincipal.actualizarhoja();
    }
    public String busqueda(String cont){
        return dom.hoja_buscar_contenido_exacto_sin_mayus_minus(cont);
    }
    public void importDoc(String s) {
        per.ImportFileCSV   (s);
    }
    public void exportDoc(String s){
        per.exportFileCSV(s);
    }
}
