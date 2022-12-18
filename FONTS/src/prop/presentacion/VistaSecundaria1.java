package prop.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaSecundaria1 {

    private CtrlPresentacion iCtrlPresentacion;

    private JFrame frameVista = new JFrame("Vista Secundaria");
    private JPanel panelContenidos = new JPanel();
    private JPanel panelRespuesta = new JPanel();
    private JPanel panelBotones = new JPanel();
    private JPanel panelInformacion = new JPanel();
    private JButton ok = new JButton("OK");
    private JButton cancel = new JButton("Cancel");
    private JTextField textField = new JTextField(5);
    private JLabel pregunta = new JLabel("¿la Pregunta?");


    public VistaSecundaria1(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
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
        inicializar_panelBotones();
        inicializar_panelInformacion();
        inicializar_panelRespuesta();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        // Tamanyo
        frameVista.setMinimumSize(new Dimension(700, 400));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
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
        panelContenidos.setLayout(new BorderLayout());
        // Paneles
        panelContenidos.add(panelBotones, BorderLayout.SOUTH);
        panelContenidos.add(panelInformacion, BorderLayout.NORTH);
        panelContenidos.add(panelRespuesta, BorderLayout.CENTER);
    }
    private void inicializar_panelBotones() {
        // Layout
        panelBotones.setLayout(new FlowLayout());
        // Botones
        panelBotones.add(ok);
        panelBotones.add(cancel);
    }
    private void inicializar_panelInformacion() {
        panelInformacion.add(pregunta, BorderLayout.NORTH);
    }

    private void inicializar_panelRespuesta() {
        panelInformacion.add(textField,BorderLayout.CENTER);
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
    }
    public void actionPerformed_buttoncancel (ActionEvent event) {
        System.out.println("boton cancel confimacion");
    }
}