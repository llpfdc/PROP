package prop.domain.core;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.String;
import java.util.Locale;

/**
 * @author Alejandro Durán Saborido
 *
 */
public class Hoja {
    private LinkedList<LinkedList<Celda>> celdas = new LinkedList<>();
    private int id;
    private String name = "";
    private int width;
    private int height;

    public Hoja(){}

    public Hoja(int height, int width, int id, String name) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.name = name;
        for(int i = 0; i < height; ++i) {
            celdas.add(new LinkedList<>());
            for(int j = 0; j < width; ++j) {
                celdas.getLast().add(new Celda(i, j, this));
            }
        }
    }

    //Getters
    public Celda getCelda(int x, int y){
        return celdas.get(x).get(y);
    }

    public int getAltura(){
        return height;
    }

    public int getAnchura() {
        return width;
    }

    public String getNombre() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCellContent(int x, int y){
        return celdas.get(x).get(y).getValor();
    }

    public String getUnprocessedCellContent(int x, int y){
        return celdas.get(x).get(y).getContenido();
    }

    public boolean getCellIni(int x, int y){
        return celdas.get(x).get(y).getIni();
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContenidoCelda(int x, int y, String newData) {
        celdas.get(x).get(y).setContenido(newData);
    }


    public void addFila() {
        celdas.addLast(new LinkedList<>());
        ++height;
        for (int i = 0; i < width; i++) {
            celdas.getLast().add(new Celda(width-1, i, this));
        }
    }

    public void addFila(int index) {
        celdas.add(index, new LinkedList<>());
        ++height;
        for (int i = 0; i < width; i++) {
            celdas.get(index).add(new Celda(index, i, this));
        }
        for(int i = index+1; i < height; ++i) {
            for(int j = 0; j < width; ++j) celdas.get(i).get(j).setFil(i);
        }
    }

    public void removeFila(int index) {
        --height;
        celdas.remove(index);
        for(int i = index; i < height; ++i) {
            for(int j = 0; j < width; ++j) celdas.get(i).get(j).setFil(i);
        }
    }

    public void addColumna() {
        ++width;
        int i = 0;
        for (LinkedList<Celda> c : celdas) {
            c.addLast(new Celda(i, width-1, this));
            ++i;
        }
    }

    public void addColumna(int index) {
        ++width;
        int i = 0;
        for (LinkedList<Celda> c : celdas) {
            c.add(index, new Celda(i,index,this));
            ++i;
        }
        for(int k = 0; i < height; ++i) {
            for(int j = index+1; j < width; ++j) celdas.get(k).get(j).setCol(j);
        }
    }

    public void removeColumna(int index) {
        --width;
        for (LinkedList<Celda> c : celdas) {
            c.remove(index);
        }
        for(int i = 0; i < height; ++i) {
            for(int j = index; j < width; ++j) celdas.get(i).get(j).setCol(j);
        }
    }

    public ArrayList<Celda> searchAndReplaceNotMatchLowerNorUpperCase(String search, String replace) {
        ArrayList<Celda> replaced = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                String s = c.getValor();
                s = s.replaceAll("(?i)"+search, replace);
                if (!s.equals(c.getValor()))  {
                    c.setContenido(s);
                    replaced.add(c);
                }
            }
        }
        return replaced;
    }

    public ArrayList<Celda> searchAndReplace(String search, String replace) {
        ArrayList<Celda> replaced = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                String s = c.getValor().replaceAll(search, replace);
                if (!s.equals(c.getValor()))  {
                    c.setContenido(s);
                    replaced.add(c);
                }
            }
        }
        return replaced;
    }

    public ArrayList<Celda> searchAndReplaceExactNotMatchLowerNorUpperCase(String search, String replace) {
        ArrayList<Celda> replaced = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                if (c.getValor().toLowerCase(Locale.ROOT).equals(search.toLowerCase(Locale.ROOT))) {
                    c.setContenido(replace);
                    replaced.add(c);
                }
            }
        }
        return replaced;
    }

    public ArrayList<Celda> searchAndReplaceExact(String search, String replace) {
        ArrayList<Celda> replaced = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                String s = c.getValor();
                if (s.equals(search)) {
                    c.setContenido(replace);
                    replaced.add(c);
                }
            }
        }
        return replaced;
    }

    public ArrayList<Celda> searchNotMatchLowerNorUpperCase(String search) {
        ArrayList<Celda> found = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                String s = c.getValor();
                s = s.replaceAll("(?i)"+search, "");
                if (!s.equals(c.getValor()))  found.add(c);
            }
        }
        return found;
    }

    public ArrayList<Celda> search(String search) {
        ArrayList<Celda> found = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                String s = c.getValor().replaceAll(search, "");
                if (!s.equals(c.getValor())) found.add(c);
            }
        }
        return found;
    }

    public ArrayList<Celda> searchExactNotMatchLowerNorUpperCase(String search) {
        ArrayList<Celda> found = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                if (c.getValor().toLowerCase(Locale.ROOT).equals(search.toLowerCase(Locale.ROOT))) {
                    found.add(c);
                }
            }
        }
        return found;
    }

    public ArrayList<Celda> searchExact(String search) {
        ArrayList<Celda> found = new ArrayList<>();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                if (c.getValor().equals(search)) found.add(c);
            }
        }
        return found;
    }

    public void printHoja() {
        StringBuilder temp = new StringBuilder();
        for (LinkedList<Celda> l : celdas) {
            for (Celda c : l) {
                String s = c.getValor();
                if (s.equals("\"\"")) s = "-vacía-";
                temp.append(s).append("  ");
            }
            temp.append("\n");
        }
        System.out.println(temp);
    }

    public String crearStringHoja() {
        StringBuilder temp = new StringBuilder();
        for (LinkedList<Celda> l : celdas) {
            boolean first = true;
            for (Celda c : l) {
                if (first) {
                    first = false;
                    temp.append(c.getContenido());
                }
                else {
                    temp.append(",");
                    temp.append(c.getValor());
                }
            }
            temp.append("\n");
        }
        return temp.toString();
    }

}