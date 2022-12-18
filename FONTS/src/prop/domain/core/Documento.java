package prop.domain.core;

import java.util.*;
import java.lang.*;


/**
 * @author Alejandro Durán Saborido
 *
 */
public class Documento {
    private String nombre;
    private ArrayList<Hoja> hojas = new ArrayList<>();
    private int name_counter = 0;

    public Documento(){}

    public Documento(String nombre, int height, int width) {
        this.nombre = nombre;
        addHoja(height,width);
        addHoja(height,width);
        addHoja(height,width);
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public int getNumHojas(){
        return hojas.size();
    }

    public List<Hoja> getHojas() {
        return hojas;
    }

    public Hoja getHoja(int id) {
        return hojas.get(id);
    }


    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void addHoja(int height, int width){
        int id = hojas.size();
        ++name_counter;
        String s = "Hoja " + name_counter;
        hojas.add(new Hoja(height, width, id, s));
    }

    public void removeHoja(int index) {
        hojas.remove(index);
        for (int i = index; i < hojas.size(); ++i) hojas.get(i).setId(i);
    }

    public void modificarNombreHoja(int index, String nombre) {
        hojas.get(index).setName(nombre);
    }

    public String crearStringDocumento() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hojas.size(); ++i) {
            s.append("@@");
            s.append(hojas.get(i).getNombre());
            s.append("@@\n");
            s.append("##");
            s.append(hojas.get(i).getAltura());
            s.append("##\n");
            s.append("##");
            s.append(hojas.get(i).getAnchura());
            s.append("##\n");
            s.append(hojas.get(i).crearStringHoja());
        }
        return s.toString();
    }

}