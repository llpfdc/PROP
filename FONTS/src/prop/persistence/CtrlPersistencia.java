package prop.persistence;

import prop.domain.CtrlDominio;


import java.io.*;

import java.util.ArrayList;


public class CtrlPersistencia {
    CtrlDominio dom;

    public CtrlPersistencia(CtrlDominio dom) {
        this.dom = dom;
    }

    public void ImportFileCSV(String path)  {
        BufferedReader reader = null;
        String line = "";
        boolean first = true;
        int filas = 0;
        int columnas = 0;
        int filaAct = 0;
        int hojaAct = -1;
        String nameHoja = "";
        try {
            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine())!= null) {
                if ((line.charAt(0) == '@')&&(line.charAt(1) == '@')&&(line.charAt(line.length() - 1) == '@')&&(line.charAt(line.length() - 2) == '@')){
                    ++hojaAct;
                    nameHoja = line.replaceAll("@","");
                    filaAct = 0;
                } else if ((line.charAt(0) == '#')&&(line.charAt(1) == '#')&&(line.charAt(line.length() - 1) == '#')&&(line.charAt(line.length() - 2) == '#')) {
                        String number = line.replaceAll("#","");
                        if(first) {
                            filas = Integer.parseInt(number);
                            first = false;
                        } else {
                            columnas = Integer.parseInt(number);
                            if(hojaAct == 0) {
                                String partes[] = path.split("/");
                                String fileName = partes[partes.length - 1];
                                dom.crear_documento(fileName, filas, columnas);
                                dom.rename_hoja(0,nameHoja);
                                first = true;
                            } else {
                                dom.add_hoja(filas,columnas);
                                dom.rename_hoja(hojaAct,nameHoja);
                                first = true;
                            }
                        }
                } else {
                    String[] values = line.split(",");
                    for(int i = 0; i < columnas; ++i) {
                        dom.modify_contenido_celda_hoja_persistencia(filaAct,i,hojaAct,values[i]);
                    }
                    ++filaAct;

                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            try{
                reader.close();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    public void exportFileCSV(String path) {
        String name = dom.get_doc_name().replaceAll(" ","_")+".csv";
        try (PrintStream out = new PrintStream(new FileOutputStream(path+"/"+name))) {
            out.print(dom.createCSVstring());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}


