package prop.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaSecundariaBusquedaHoja {

    private CtrlPresentacion iCtrlPresentacion;

    private JFrame frameVista = new JFrame("Busqueda Hoja");
    private JPanel panelContenidos = new JPanel();

    private JButton ok = new JButton("OK");
    private JButton cancel = new JButton("Cancel");
    private JTextField textField = new JTextField(50);
    private JLabel pregunta = new JLabel("¿Que quiere buscar en la hoja?");

    private JLabel result = new JLabel("");



    public VistaSecundariaBusquedaHoja(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        hacerVisible();
    }


    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void hacerInvisible() {
        frameVista.setVisible(false);
    }

    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_panelContenidos();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        // Tamanyo
        frameVista.setMinimumSize(new Dimension(350, 200));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(true);
        // Posicion y operaciones por defecto
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Se agrega panelContenidos al contentPane (el panelContenidos se
        // podria ahorrar y trabajar directamente sobre el contentPane)
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContenidos);
    }
    private void inicializar_panelContenidos() {
        // Layout
        panelContenidos.setLayout(null);

        pregunta.setBounds(10,10,340,25);
        panelContenidos.add(pregunta);
        textField.setBounds(10,40,260,25);
        panelContenidos.add(textField);
        result.setBounds(10,80,1000,25);
        panelContenidos.add(result);
        ok.setBounds(10,120,100,25);
        panelContenidos.add(ok);
        cancel.setBounds(170,120,100,25);
        panelContenidos.add(cancel);
    }


    private void asignar_listenersComponentes() {

        // Listeners para los botones

        ok.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_buttonOK(event);
                    }
                });

        cancel.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        actionPerformed_buttoncancel(event);
                    }
                });
    }
    public void actionPerformed_buttonOK (ActionEvent event) {

        System.out.println("boton ok confirmacion");
        String textoBox = textField.getText();
        String resultSet = iCtrlPresentacion.busqueda(textoBox);
        result.setText(resultSet);
        iCtrlPresentacion.actualizarHoja();
    }
    public void actionPerformed_buttoncancel (ActionEvent event) {

        System.out.println("boton cancel confimacion");
        frameVista.dispose();
    }
}