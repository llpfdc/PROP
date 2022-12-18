package prop.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaSecundariaBuscarYRemplazar {

    private CtrlPresentacion iCtrlPresentacion;

    private JFrame frameVista = new JFrame("Buscar Y Reemplazar");
    private JPanel panelContenidos = new JPanel();

    private JButton ok = new JButton("OK");
    private JButton cancel = new JButton("Cancel");
    private JTextField buscarResp = new JTextField(50);
    private JTextField rempResp = new JTextField(50);
    private JLabel pregunta = new JLabel("¿Que quieres buscar y que quieres reemplazar?");

    private JLabel buscar = new JLabel("Buscar:");

    private JLabel reemplazar = new JLabel("Reemplazar:");


    public VistaSecundariaBuscarYRemplazar(CtrlPresentacion pCtrlPresentacion) {
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
        frameVista.setMinimumSize(new Dimension(350, 220));
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
        panelContenidos.setLayout(null);

        pregunta.setBounds(10,10,340,25);
        panelContenidos.add(pregunta);
        buscar.setBounds(10,50,100,25);
        panelContenidos.add(buscar);
        buscarResp.setBounds(170,50,70,25);
        panelContenidos.add(buscarResp);

        reemplazar.setBounds(10,100,100,25);
        panelContenidos.add(reemplazar);
        rempResp.setBounds(170,100,70,25);
        panelContenidos.add(rempResp);

        ok.setBounds(10,140,100,25);
        panelContenidos.add(ok);
        cancel.setBounds(170,140,100,25);
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
        String buscar = buscarResp.getText();
        String reemplazar = rempResp.getText();
        iCtrlPresentacion.reemplazo(buscar,reemplazar);
        iCtrlPresentacion.actualizarHoja();
        frameVista.dispose();
    }
    public void actionPerformed_buttoncancel (ActionEvent event) {

        System.out.println("boton cancel confimacion");
        frameVista.dispose();
    }
}