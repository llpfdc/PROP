package prop.presentacion;

import prop.domain.core.Hoja;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.ArrayList;


public class VistaPrincipal {

    private static final int MIN_ROW_HEIGHT = 15;

    private CtrlPresentacion iCtrlPresentacion;
    private JFrame frameVista = new JFrame();
    private JMenuBar menu = new JMenuBar();
    private JMenu file = new JMenu();
    private JMenuItem importar = new JMenuItem();
    private JMenuItem exportar = new JMenuItem();
    private JMenuItem Cambiar_Nombre_Doc = new JMenuItem();
    private JMenuItem lucky = new JMenuItem();

    private JMenu edit = new JMenu();
    private JMenuItem Agregar_hoja = new JMenuItem();
    private JMenuItem Remover_hoja = new JMenuItem();
    private JMenuItem Renombrar_hoja = new JMenuItem();
    private JMenuItem Agregar_fila = new JMenuItem();
    private JMenuItem Remover_fila = new JMenuItem();
    private JMenuItem Agregar_Columna = new JMenuItem();
    private JMenuItem Remover_Columna = new JMenuItem();

    private JMenu select = new JMenu();

    private JMenu funtions = new JMenu();
    private JMenuItem copiar = new JMenuItem();
    private JMenuItem pegar = new JMenuItem();
    private JMenuItem Busqueda = new JMenuItem();

    private JMenuItem BusquedaYReemp = new JMenuItem();

    private JMenuItem Ordenar = new JMenuItem();

    private JTabbedPane Tabbedpane = new JTabbedPane();
    private ArrayList<JTextField> barras_de_contenido = new ArrayList<>();
    private ArrayList<JPanel> panels_tablas = new ArrayList<>();
    private ArrayList<JScrollPane> scrolls_tablas = new ArrayList<>();
    private ArrayList<JTable> tablas = new ArrayList<>();
    private JPopupMenu popup = new JPopupMenu();
    private JMenuItem copiar2 = new JMenuItem();
    private JMenuItem pegar2 = new JMenuItem();
    ArrayList<String[][]> data;
    //ArrayList<boolean[][]> used;
    int hoja = 0;
    boolean controlcambio = false;
    int Block1Cell1Row;
    int Block1Cell1Col;
    int Block1Cell2Row;
    int Block1Cell2Col;
    boolean copiando = true;
    int Block2Cell1Row;
    int Block2Cell1Col;
    int Block2Cell2Row;
    int Block2Cell2Col;

    boolean actualizando;

    public VistaPrincipal(CtrlPresentacion pCtrlPresentacion) {
        System.out.println
                ("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    public void hacerInvisible() {
        frameVista.setVisible(false);
    }
    public void hacerVisible() {
        System.out.println
                ("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
        frameVista.pack();
        frameVista.setVisible(true);
    }
    public void activar() {
        frameVista.setEnabled(true);
    }
    public void desactivar() {
        frameVista.setEnabled(false);
    }
    public void inicializarComponentes() {
        inicializar_data();
        int num_hojas = iCtrlPresentacion.getNumHojas();
        for (int i = 0; i < num_hojas; ++i) {
            createTable(i,iCtrlPresentacion.getNumColumnas());
            createTextField(i);
            createPanel(i);
        }
        createPopUpMenu();
        createTabbedpane();
        createMenu();
        createFrame();
        asignar_listenersComponentes();
        for (int i = 0; i < num_hojas; ++i) {
            asignar_listenersComponentes(i);
        }
    }
    public void  actualizarhoja() {
        int num_col = iCtrlPresentacion.getNumColumnas();
        int num_fil = iCtrlPresentacion.getNumFilas();
        actualizando = true;
        if (actualizando) {
            for (int i = 0; i < num_fil; ++i) {
                for (int j = 0; j < num_col; ++j) {
                    if (iCtrlPresentacion.getCellini(i, j)) {
                        String x = iCtrlPresentacion.getValorCelda(i, j);
                        tablas.get(hoja).getModel().setValueAt(x, i, j);
                    }
                    else{
                        String x = "";
                        tablas.get(hoja).getModel().setValueAt(x, i, j);
                    }
                }
            }
            actualizando = false;
        }
    }
    public void agregarHoja(){
        int num_hojas = iCtrlPresentacion.getNumHojas();
        System.out.println(num_hojas);
        System.out.println(num_hojas);
        inicializar_data();
        createTextField(num_hojas-1);
        createTable(num_hojas-1,iCtrlPresentacion.getNumColumnasId(num_hojas - 1));
        createPanel(num_hojas-1);
        System.out.println(tablas.size());
        System.out.println(barras_de_contenido.size());
        System.out.println(panels_tablas.size());
        String nom_hoja = iCtrlPresentacion.getNombreHoja(num_hojas-1);
        Tabbedpane.addTab(nom_hoja, panels_tablas.get(num_hojas-1));

    }

    public void removeHoja(){
        Tabbedpane.removeTabAt(hoja);
    }

    public JTabbedPane getTabbedPane() {
        return Tabbedpane;
    }

    public JFrame getFrame() {
        return frameVista;
    }
    private void asignar_listenersComponentes(int i) {
        tablas.get(i).getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if(!actualizando) {
                    int row = tablas.get(i).getSelectedRow();
                    int column = tablas.get(i).getSelectedColumn();
                    System.out.println(row);
                    System.out.println(column);
                    //used.get(i)[row][column] = true;
                    String a;
                    //if (!equals(String.valueOf(tablas.get(i).getModel().getValueAt(row, column)),null)) {
                    a = String.valueOf(tablas.get(i).getModel().getValueAt(row, column));
                    System.out.println("entra1");
                    //}
                    System.out.println(a + " a");
                    String viejo = iCtrlPresentacion.getContenidoCelda(row, column);
                    System.out.println(viejo + "viejo");
                    if (!controlcambio) {
                        iCtrlPresentacion.setContenidoCelda(row, column, a);
                        System.out.println("entra");
                        controlcambio = true;
                        tablas.get(i).setValueAt(iCtrlPresentacion.getValorCelda(row, column), row, column);
                    }
                    controlcambio = false;
                    System.out.println(iCtrlPresentacion.getContenidoCelda(row, column));
                    System.out.println(iCtrlPresentacion.getValorCelda(row, column));
                    if (row < iCtrlPresentacion.getNumFilas()) {
                        System.out.println("true");
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row, column));
                    }
                }
            }
        });

        tablas.get(i).addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Left Click!");
                    int rows = iCtrlPresentacion.getNumFilas();
                    int cols = iCtrlPresentacion.getNumColumnas();
                    int row = tablas.get(hoja).rowAtPoint(evt.getPoint());
                    int col = tablas.get(hoja).columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0 && row < rows && col < cols && getCellIni(row,col)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row, col));
                    } else {
                        barras_de_contenido.get(i).setText(" ");
                    }
                } else if (evt.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("right Click!");
                    popup.show(tablas.get(i) , evt.getX(), evt.getY());
                }
            }


            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("Left pressed!");
                    int rows = iCtrlPresentacion.getNumFilas();
                    int cols = iCtrlPresentacion.getNumColumnas();
                    if (!copiando) {
                        Block1Cell1Row = tablas.get(hoja).rowAtPoint(evt.getPoint());
                        Block1Cell1Col = tablas.get(hoja).columnAtPoint(evt.getPoint());
                        System.out.println(Block1Cell1Col + "col");
                        System.out.println(Block1Cell1Row + "row");
                    } else {
                        Block2Cell1Row = tablas.get(hoja).rowAtPoint(evt.getPoint());
                        Block2Cell1Col = tablas.get(hoja).columnAtPoint(evt.getPoint());
                        System.out.println(Block2Cell1Col + "col");
                        System.out.println(Block2Cell1Row + "row");
                    }
                    if (Block1Cell1Row >= 0 && Block1Cell1Col >= 0 && getCellIni(Block1Cell1Row, Block1Cell1Col)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(Block1Cell1Row, Block1Cell1Col));
                    } else {
                        barras_de_contenido.get(hoja).setText(" ");
                    }
                }
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    if (!copiando) {
                        System.out.println("Left released!");
                        Block1Cell2Row = tablas.get(i).rowAtPoint(evt.getPoint());
                        Block1Cell2Col = tablas.get(i).columnAtPoint(evt.getPoint());
                        System.out.println(Block1Cell2Col + "col");
                        System.out.println(Block1Cell2Row + "row");
                    } else {
                        Block2Cell2Row = tablas.get(hoja).rowAtPoint(evt.getPoint());
                        Block2Cell2Col = tablas.get(hoja).columnAtPoint(evt.getPoint());
                        System.out.println(Block2Cell2Col + "col");
                        System.out.println(Block2Cell2Row + "row");
                        copiando = false;
                    }
                }
            }
        });

        tablas.get(i).addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {

                int row = tablas.get(i).getSelectedRow();
                int col = tablas.get(i).getSelectedColumn();
                if (e.getKeyCode() == e.VK_ENTER) {
                    System.out.println("pressed enter");
                    //if (row >= 0 && col >= 0 && used.get(i)[row][col]) {
                    if (row >= 0 && col >= 0 && getCellIni(row,col)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row, col));
                    }
                    else {
                        barras_de_contenido.get(i).setText(" ");
                    }
                    actualizarhoja();
                } else if (e.getKeyCode() == e.VK_UP) {
                    System.out.println("pressed up");
                    //if (row >= 0 && col >= 0 && used.get(i)[row - 1][col]) {
                    if (row >= 0 && col >= 0 && getCellIni(row-1,col)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row - 1, col));
                    } else {
                        barras_de_contenido.get(i).setText(" ");
                    }
                } else if (e.getKeyCode() == e.VK_DOWN) {
                    System.out.println("pressed down");
                    //if (row >= 0 && col >= 0 && used.get(i)[row + 1][col]) {
                    if (row >= 0 && col >= 0 && getCellIni(row+1,col)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row + 1, col));
                    } else {
                        barras_de_contenido.get(i).setText(" ");
                    }
                } else if (e.getKeyCode() == e.VK_RIGHT) {
                    System.out.println("pressed right");
                    //if (row >= 0 && col >= 0 && used.get(i)[row][col + 1]) {
                    if (row >= 0 && col >= 0 && getCellIni(row,col+1)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row, col + 1));
                    } else {
                        barras_de_contenido.get(i).setText(" ");
                    }
                } else if (e.getKeyCode() == e.VK_LEFT) {
                    System.out.println("pressed left");
                    //if (row >= 0 && col >= 0 && used.get(i)[row][col - 1]) {
                    if (row >= 0 && col >= 0 && getCellIni(row,col-1)) {
                        barras_de_contenido.get(i).setText(iCtrlPresentacion.getContenidoCelda(row, col - 1));
                    } else {
                        barras_de_contenido.get(i).setText(" ");
                    }
                }
            }
        });

        barras_de_contenido.get(i).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablas.get(i).getSelectedRow();
                int column = tablas.get(i).getSelectedColumn();
                String x = barras_de_contenido.get(i).getText();
                tablas.get(i).setValueAt(x, row, column);
            }
        });
    }

    private void asignar_listenersComponentes() {
        importar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado importar");
                iCtrlPresentacion.visImportCSV();
                actualizarhoja();
            }
        });
        exportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado exportar");
                iCtrlPresentacion.visExportCSV();
            }
        });
        Cambiar_Nombre_Doc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado cambiar nombre");
                iCtrlPresentacion.visRenameDocument();
            }
        });
        Agregar_hoja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado Agregar hoja");
                iCtrlPresentacion.visAddHoja();
                actualizarhoja();
            }
        });
        Remover_hoja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado remover hoja");
                iCtrlPresentacion.rmHoja(hoja);
                removeHoja();
                actualizarhoja();
            }
        });

        Renombrar_hoja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado renombrar hoja");
                iCtrlPresentacion.visRenameHoja();
                actualizarhoja();
            }
        });
        Agregar_fila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado Agregar fila");
                iCtrlPresentacion.visAddFila();
                JTable tab = tablas.get(hoja);

                DefaultTableModel tableModel = (DefaultTableModel)tab.getModel();
                tableModel.insertRow(0,new Object[tableModel.getColumnCount()]);
                actualizarhoja();
            }
        });
        Remover_fila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                System.out.println("Has seleccionado remover fila");
                iCtrlPresentacion.visRemoveFila();
                JTable tab = tablas.get(hoja);
                ((DefaultTableModel)tab.getModel()).removeRow(1);
                actualizarhoja();

            }
        });
        Agregar_Columna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                System.out.println("Has seleccionado Agregar columna");
                iCtrlPresentacion.visAddCol();

                actualizarhoja();
            }
        });
        Remover_Columna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                System.out.println("Has seleccionado remover columna");
                iCtrlPresentacion.visRemoveCol();
                JTable tab = tablas.get(hoja);
                tab.removeColumn(tab.getColumnModel().getColumn(0));
                actualizarhoja();

            }
        });
        copiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado copiar");
                accionCopiar();
            }
        });
        pegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado pegar");
                accionpegar();
            }
        });
        Busqueda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado Buscar");
                iCtrlPresentacion.vistaBuscar();
            }
        });

        BusquedaYReemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado Buscar y Reemplazar");
                iCtrlPresentacion.vistaBuscarYReemplazar();
                actualizarhoja();
            }
        });
        Ordenar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado Ordenar");

    //prubea
            }
        });
        lucky.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                System.out.println("Saludos del subgrupo 2.3");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀");
                System.out.println("⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀");
                System.out.println("⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀");
                System.out.println("⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀");
                System.out.println("⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀");
                System.out.println("⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀⠀");
                System.out.println("⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀");
                System.out.println("⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀");
                System.out.println("⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀");
                System.out.println("⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀");
                System.out.println("⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀");


            }
        });
        copiar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Has seleccionado copiar2");
                accionCopiar();
            }
        });
        pegar2.addActionListener(new ActionListener() {       
            public void actionPerformed(ActionEvent event) { 
                System.out.println("Has seleccionado pegar2");
                accionpegar();
            }
        });
        Tabbedpane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
                //int i = Idhojas,get(index)
                sethoja(index); //preciso hoja id lo unico que tengo es el nombre...
            }
        });
    }
    private void accionCopiar(){
        copiando = true;
    }
    private void accionpegar(){

        System.out.println(Block1Cell1Row);
        System.out.println(Block1Cell1Col);
        System.out.println(Block1Cell2Row);
        System.out.println(Block1Cell2Col);
        System.out.println(Block2Cell1Row);
        System.out.println(Block2Cell1Col);
        System.out.println(Block2Cell2Row);
        System.out.println(Block2Cell2Col);
        iCtrlPresentacion.copiar(Block1Cell1Row,Block1Cell1Col,Block1Cell2Row,Block1Cell2Col,Block2Cell1Row,Block2Cell1Col,Block2Cell2Row,Block2Cell2Col);
        actualizarhoja();
    }
    private boolean getCellIni(int x, int y){
        return iCtrlPresentacion.getCellini(x,y);
    }
    private void createFrame() {
        String nombredoc = iCtrlPresentacion.getNombreDoc();
        frameVista = new JFrame(nombredoc);
        frameVista.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameVista.add(BorderLayout.NORTH, menu); //add menubar
        frameVista.add(Tabbedpane);
    }
    private void createMenu() {
        menu = new JMenuBar();

        file = new JMenu("File");
        Cambiar_Nombre_Doc = new JMenuItem("Cambiar Nombre Doc");
        importar = new JMenuItem("Importar");
        exportar = new JMenuItem("Exportar");
        lucky = new JMenuItem("I´m feeling lucky");


        edit = new JMenu("Edit");
        Agregar_hoja = new JMenuItem("Agregar Hoja");
        Remover_hoja = new JMenuItem("Remover Hoja");
        Renombrar_hoja = new JMenuItem("Reenombrar Hoja");
        Agregar_fila = new JMenuItem("Agregar Fila");
        Remover_fila = new JMenuItem("Remover Fila");
        Agregar_Columna = new JMenuItem("Agregar Columna");
        Remover_Columna = new JMenuItem("Remover Columna");

        select = new JMenu("Select");

        funtions = new JMenu("Functions");
        copiar = new JMenuItem("Copiar");
        pegar = new JMenuItem("Pegar");
        Busqueda = new JMenuItem("Busqueda");
        BusquedaYReemp = new JMenuItem("BusquedaYReemp");
        Ordenar = new JMenuItem("Ordenar");

        file.add(importar);
        file.add(exportar);
        file.add(Cambiar_Nombre_Doc);
        file.add(lucky);

        edit.add(Agregar_hoja);
        edit.add(Remover_hoja);
        edit.add(Renombrar_hoja);
        edit.add(Agregar_fila);
        edit.add(Remover_fila);
        edit.add(Agregar_Columna);
        edit.add(Remover_Columna);

        funtions.add(copiar);
        funtions.add(pegar);
        funtions.add(Busqueda);
        funtions.add(BusquedaYReemp);
        funtions.add(Ordenar);

        menu.add(file);
        menu.add(edit);
        menu.add(select);
        menu.add(funtions);

    }
    private void createTabbedpane() {
        // Creates a JTabbedPane with tabs at the bottom.
        Tabbedpane = new JTabbedPane(JTabbedPane.BOTTOM);
        int num_hojas = iCtrlPresentacion.getNumHojas();
        for (int i = 0; i < num_hojas; ++i) {
            String nom_hoja = iCtrlPresentacion.getNombreHoja(i);
            Tabbedpane.addTab(nom_hoja, panels_tablas.get(i));
        }
    }
    private void createPopUpMenu() {
        popup = new JPopupMenu();
        copiar2 = new JMenuItem("Copiar");
        pegar2 = new JMenuItem("Pegar");

        popup.add(copiar2);
        popup.add(pegar2);
    }
    private void createPanel(int i) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(barras_de_contenido.get(i), BorderLayout.NORTH);
        panel.add(scrolls_tablas.get(i), BorderLayout.CENTER);   //jtable creada junto con jscroll para tener la rowheader
        panels_tablas.add(i,panel);
    }
    private void createTextField(int i) {
        JTextField barra_contenido = new JTextField(25);
        barras_de_contenido.add(i, barra_contenido);
    }
    private void createTable(int i,int cl){
        int num_col = cl;

        String[] col = new String[num_col];
        char a = 'A';
        for (int j = 0; j < num_col; j++) {
            col[j] = String.valueOf(a);
            ++a;
        }

        DefaultTableModel modelo = new DefaultTableModel(data.get(i), col);

        JTable table = new JTable (modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setCellSelectionEnabled(true);
        tablas.add(i, table);
        JScrollPane scrollPane = new JScrollPane(tablas.get(i));
        scrollPane.setRowHeaderView(buildRowHeader(tablas.get(i)));
        scrolls_tablas.add(i, scrollPane);
    }
    private void inicializar_data(){
        int hoja_aux = hoja;
        int num_hojas = iCtrlPresentacion.getNumHojas();
        data = new ArrayList<>(num_hojas);
        //used = new ArrayList<>(num_hojas);
        for (int i = 0; i < num_hojas; ++i){

            sethoja(i);
            int num_col = iCtrlPresentacion.getNumColumnas();
            int num_fil = iCtrlPresentacion.getNumFilas();
            String[][] data_aux = new String[num_fil][num_col];

            for (int k = 0; k < num_fil; ++k) {
                for (int l = 0; l < num_col; ++l) {
                    if (!getCellIni(k,l)) data_aux[k][l] = null;
                    else data_aux[k][l] = iCtrlPresentacion.getContenidoCelda(k,l);
                    //used_aux[k][l] = false;
                    //data_aux[k][l] = iCtrlPresentacion.getValorCelda(k, l);
                }
            }
            data.add(i, data_aux);
            //used.add(i, used_aux);
        }
        sethoja(hoja_aux);
    }
    private void sethoja (int i){
        iCtrlPresentacion.setHojaActual(i);
        hoja = i;
    }
    private static JList buildRowHeader(final JTable table) {
        final Vector<String> headers = new Vector<String>();
        for (int i = 0; i < table.getRowCount(); i++) {
            headers.add(String.valueOf(i+1));
        }
        ListModel lm = new AbstractListModel() {

            public int getSize() {
                return headers.size();
            }

            public Object getElementAt(int index) {
                return headers.get(index);
            }
        };

        final JList rowHeader = new JList(lm);
        rowHeader.setOpaque(false);
        rowHeader.setFixedCellWidth(50);


        MouseInputAdapter mouseAdapter = new MouseInputAdapter() {
            Cursor oldCursor;
            Cursor RESIZE_CURSOR = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
            int index = -1;
            int oldY = -1;

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int previ = getLocationToIndex(new Point(e.getX(), e.getY() - 3));
                int nexti = getLocationToIndex(new Point(e.getX(), e.getY() + 3));
                if (previ != -1 && previ != nexti) {
                    if (!isResizeCursor()) {
                        oldCursor = rowHeader.getCursor();
                        rowHeader.setCursor(RESIZE_CURSOR);
                        index = previ;
                    }
                } else if (isResizeCursor()) {
                    rowHeader.setCursor(oldCursor);
                }
            }

            private int getLocationToIndex(Point point) {
                int i = rowHeader.locationToIndex(point);
                if (!rowHeader.getCellBounds(i, i).contains(point)) {
                    i = -1;
                }
                return i;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (isResizeCursor()) {
                    rowHeader.setCursor(oldCursor);
                    index = -1;
                    oldY = -1;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (isResizeCursor() && index != -1) {
                    int y = e.getY();
                    if (oldY != -1) {
                        int inc = y - oldY;
                        int oldRowHeight = table.getRowHeight(index);
                        if (oldRowHeight > 12 || inc > 0) {
                            int rowHeight = Math.max(MIN_ROW_HEIGHT, oldRowHeight + inc);
                            table.setRowHeight(index, rowHeight);
                            if (rowHeader.getModel().getSize() > index + 1) {
                                int rowHeight1 = table.getRowHeight(index + 1) - inc;
                                rowHeight1 = Math.max(12, rowHeight1);
                                table.setRowHeight(index + 1, rowHeight1);
                            }
                        }
                    }
                    oldY = y;
                }
            }

            private boolean isResizeCursor() {
                return rowHeader.getCursor() == RESIZE_CURSOR;
            }
        };
        rowHeader.addMouseListener(mouseAdapter);
        rowHeader.addMouseMotionListener(mouseAdapter);
        rowHeader.addMouseWheelListener(mouseAdapter);

        rowHeader.setCellRenderer(new RowHeaderRenderer(table));
        rowHeader.setBackground(table.getBackground());
        rowHeader.setForeground(table.getForeground());
        return rowHeader;
    }
    static class RowHeaderRenderer extends JLabel implements ListCellRenderer {

        private JTable table;

        RowHeaderRenderer(JTable table) {
            this.table = table;
            JTableHeader header = this.table.getTableHeader();
            setOpaque(true);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(CENTER);
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
            setDoubleBuffered(true);
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText((value == null) ? "" : value.toString());
            setPreferredSize(null);
            setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), table.getRowHeight(index)));
            //trick to force repaint on JList (set updateLayoutStateNeeded = true) on BasicListUI
            list.firePropertyChange("cellRenderer", 0, 1);
            return this;
        }
    }
}