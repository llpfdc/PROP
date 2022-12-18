package prop.domain.core;

import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;

public class Bloque {

    //Atributos
    private Celda first;
    private Celda last;
    private ArrayList<ArrayList<Celda>> matrix = new ArrayList<>();
    private Hoja sheet;
    //Constructoras
    public Bloque() {};
    private Bloque(Celda first, Celda last, ArrayList<ArrayList<Celda>> matrix, Hoja sheet) {
        this.first = first;
        this.last = last;
        this.matrix = matrix;
        this.sheet = sheet;
    };
    private Bloque(Celda a) {
        ArrayList<Celda> aux = new ArrayList<>();

        this.first = a;
        this.last = a;
        aux.add(a);
        this.matrix.add(aux);
        this.sheet = a.getHoja();
    };
    public Bloque(Celda a, Celda b) {

        if (a == b) new Bloque(a);
        else {
            int a_i = a.getFil();
            int a_j = a.getCol();
            int b_i = b.getFil();
            int b_j = b.getCol();

            if ((a_i < b_i) & (a_j < b_j)) {
                this.first = a;
                this.last = b;
            } else if ((a_i > b_i) & (a_j > b_j)) {
                this.first = b;
                this.last = a;
            } else if ((a_i < b_i) & (a_j > b_j)) {
                Celda aux, aux2;
                aux = sheet.getCelda(a_i, b_j);
                aux2 = sheet.getCelda(b_i, a_j);
                this.first = aux;
                this.last = aux2;
            } else if ((a_i > b_i) & (a_j < b_j)) {
                Celda aux, aux2;
                aux = sheet.getCelda(a_i, b_j);
                aux2 = sheet.getCelda(b_i, a_j);
                this.first = aux2;
                this.last = aux;
            } else if (a_i == b_i) {
                if (a_j < b_j) {
                    this.first = a;
                    this.last = b;
                } else if (a_j > b_j) {
                    this.first = b;
                    this.last = a;
                }
            } else { //(a_j == b_j)
                if (a_i < b_i) {
                    this.first = a;
                    this.last = b;
                } else {  //a_i > b_i) or (a_i == b_i son la misma)
                    this.first = b;
                    this.last = a;
                }
            }

            a_i = first.getFil();
            a_j = first.getCol();
            b_i = last.getFil();
            b_j = last.getCol();

            this.sheet = a.getHoja();

            for (int i = a_i; i <= b_i; ++i) {
                ArrayList<Celda> aux = new ArrayList<>();
                for (int j = a_j; j <= b_j; ++j) {
                    aux.add(sheet.getCelda(i, j));
                }
                this.matrix.add(aux);
            }
        }
    }
    //Getters
    public Celda getFirst() {
        return first;
    }
    public Celda getLast() {
        return last;
    }
    public ArrayList<ArrayList<Celda>> getMatrix() {
        return matrix;
    }
    public ArrayList<Celda> getCells() {
        ArrayList <Celda> all_cells = new ArrayList<>();
        for (ArrayList<Celda> aux : matrix) {
            all_cells.addAll(aux);
        }
        return all_cells;
    }
    public int getNumberOfCells() {
        return this.matrix.size() * this.matrix.get(0).size();
    }
    //Setters
    public void setContenidoCelda(int i, int j, String x){
        this.matrix.get(i).get(j).setContenido(x);
    }
    //funtions
    public void copiar_Contenido(Bloque b){
        if (this.matrix.size() != b.matrix.size()) {
            //throw new IllegalArgumentException("Block of different sizes");
            System.out.println("Error: Block of different sizes");
        }
        else if (b.matrix.get(0).size() != this.matrix.get(0).size()){
            //throw new IllegalArgumentException("Block of different sizes");
            System.out.println("Error: Block of different sizes");
        }
        else {
            String contenido;
            for(int i = 0; i < this.matrix.size(); ++i) {
                for (int j = 0; j < this.matrix.get(0).size(); ++j) {
                    contenido = this.matrix.get(i).get(j).getContenido();
                    b.matrix.get(i).get(j).setContenido(contenido);
                }
            }
        }
    }
    public void copiar_Valor(Bloque b){
        if (this.matrix.size() != b.matrix.size()) {
            //throw new IllegalArgumentException("Block of different sizes");
            System.out.println("Error: Block of different sizes");
        }
        else if (b.matrix.get(0).size() != this.matrix.get(0).size()){
            //throw new IllegalArgumentException("Block of different sizes");
            System.out.println("Error: Block of different sizes");
        }
        else {
            String val;
            for (int i = 0; i < this.matrix.size(); ++i) {
                for (int j = 0; j < this.matrix.get(0).size(); ++j) {
                    val = this.matrix.get(i).get(j).getValor();
                    b.matrix.get(i).get(j).setContenido(val);
                }
            }
        }
    }
    public void cortar_pegar(Bloque b) {
        if (this.matrix.size() != b.matrix.size()) {
            //throw new IllegalArgumentException("Block of different sizes");
            System.out.println("Error: Block of different sizes");
        }
        else if (b.matrix.get(0).size() != this.matrix.get(0).size()){
            //throw new IllegalArgumentException("Block of different sizes");
            System.out.println("Error: Block of different sizes");
        }
        else {
            String contenido;
            for (int i = 0; i < this.matrix.size(); ++i) {
                for (int j = 0; j < this.matrix.get(0).size(); ++j) {
                    contenido = this.matrix.get(i).get(j).getValor();
                    b.matrix.get(i).get(j).setContenido(contenido);
                    this.matrix.get(i).get(j).setContenido("\"\"");
                }
            }
        }
    }
    public ArrayList<Celda> searchExact(String a) {
        ArrayList<Celda> solution = new ArrayList<>();
        for (ArrayList<Celda> aux : matrix) {
            for (Celda c : aux) {
                if (c.getValor().equals(a)) solution.add(c);
            }
        }
        return solution;
    }
    public ArrayList<Celda> replace(String a, String b) {

        ArrayList<Celda> solution = new ArrayList<>();
        for (ArrayList<Celda> aux : matrix) {
            for (Celda c : aux) {
                if (c.getContenido().equals(a)) {
                    solution.add(c);
                    c.setContenido(b);
                    sheet.setContenidoCelda(c.getFil(), c.getCol(), b);
                } else if (c.getValor().equals(a)) {
                    solution.add(c);
                    c.setContenido(b);
                    sheet.setContenidoCelda(c.getFil(), c.getCol(), b);
                }
            }
        }
        return solution;
    }
    public void colSort(int col) {
        if (matrix.get(0).size() < col) {
            System.out.println("Error: Column not included on block");
        }
        else{
            for(int i = 0; i < this.matrix.size(); ++i) {
                ArrayList<Celda> min = this.matrix.get(i);
                int pos = i;
                for (int j = i; j < this.matrix.size(); ++j) {
                    if (compareStrings(min.get(col).getValor(), this.matrix.get(j).get(col).getValor()) >= 0) {
                        min = this.matrix.get(j);
                        pos = j;
                    }
                }
                swap_filas_in_matrix(i, pos);
            }
        }
        restoreHoja();
    }
    public double media(){
        double sum = 0;
        for (ArrayList<Celda> aux : matrix) {
            for (Celda c : aux) {
                if (!c.getDato().isNum()) {
                    System.out.println("Error: Block with at least one no numeric data");
                    return 0.0;
                }
                sum = sum + Double.parseDouble(c.getValor());
            }
        }
        return sum/this.getNumberOfCells();
    }
    public double mediana(){
        ArrayList <Double> vec = new ArrayList<>();
        for (ArrayList<Celda> aux : matrix) {
            for (Celda c : aux) {
                if (!c.getDato().isNum()) {
                    System.out.println("Error: Block with at least one no numeric data");
                    return 0.0;
                }
                vec.add(Double.parseDouble(c.getValor()));
            }
        }
        Collections.sort(vec);
        if (vec.size()%2 != 0) {
            return vec.get(this.getNumberOfCells() / 2);
        }
        else{
            return (vec.get(this.getNumberOfCells() / 2) + vec.get((this.getNumberOfCells()/2)-1) )/2;
        }
    }
    public double varianza(){
        ArrayList <Double> vec = new ArrayList<>();
        for (ArrayList<Celda> aux : matrix) {
            for (Celda c : aux) {
                if (!c.getDato().isNum()) {
                    System.out.println("Error: Block with at least one no numeric data");
                    return 0.0;
                }
                vec.add(Double.parseDouble(c.getValor()));
            }
        }
        double med = this.media();
        double sum = 0;
        for (int i = 0; i < vec.size(); ++i){
            sum = sum + Math.pow(vec.get(i) - med, 2);
        }
        return sum/(this.getNumberOfCells()-1);
    }
    public double desvio(){
        return Math.sqrt(this.varianza());
    }
    public double covarianza(Bloque b){
        if (this.matrix.size() != b.matrix.size()) {
            System.out.println("Blocks of different sizes");
            return 0.0;
        }
        else {
            ArrayList<Double> vec = new ArrayList<>();
            for (ArrayList<Celda> aux : this.matrix) {
                for (Celda c : aux) {
                    if (!c.getDato().isNum()) {
                        System.out.println("Error: Block with at least one no numeric data");
                        return 0.0;
                    }
                    vec.add(Double.parseDouble(c.getValor()));
                }
            }
            ArrayList<Double> vec2 = new ArrayList<>();
            for (ArrayList<Celda> aux : b.matrix) {
                for (Celda c : aux) {
                    if (!c.getDato().isNum()) {
                        System.out.println("Error: Block with at least one no numeric data");
                        return 0.0;
                    }
                    else {
                        vec2.add(Double.parseDouble(c.getValor()));
                    }
                }
            }
            double med1 = this.media();
            double med2 = b.media();
            double sum = 0;
            for (int i = 0; i < vec.size(); ++i) {
                sum = sum + ((vec.get(i) - med1) * (vec2.get(i) - med2));
            }
            return sum / this.getNumberOfCells();
        }
    }
    public double pearson(Bloque b){
        if (this.matrix.size() != b.matrix.size()) {
            System.out.println("Blocks of different sizes");
            return 0.0;
        }
        else {
            ArrayList<Double> muestra1 = new ArrayList<>();
            for (ArrayList<Celda> aux : this.matrix) {
                for (Celda c : aux) {
                    if (!c.getDato().isNum()) {
                        System.out.println("Error: Block with at least one no numeric data");
                        return 0.0;
                    }
                    else {
                        muestra1.add(Double.parseDouble(c.getValor()));
                    }
                }
            }
            ArrayList<Double> muestra2 = new ArrayList<>();
            for (ArrayList<Celda> aux : b.matrix) {
                for (Celda c : aux) {
                    if (!c.getDato().isNum()) {
                        System.out.println("Error: Block with at least one no numeric data");
                        return 0.0;
                    }
                    muestra2.add(Double.parseDouble(c.getValor()));
                }
            }
            double somatorio1 = 0;
            double somatorio2 = 0;
            double somatorio3 = 0;
            double med1 = this.media();
            double med2 = b.media();
            for (int i = 0; i < muestra1.size(); ++i) {
                somatorio1 = somatorio1 + ((muestra1.get(i) - med1) * (muestra2.get(i) - med2));
                somatorio2 = somatorio2 + Math.pow((muestra1.get(i) - med1), 2);
                somatorio3 = somatorio3 + Math.pow((muestra2.get(i) - med2), 2);
            }
            return somatorio1 / (Math.sqrt(somatorio2 * somatorio3));
        }
    }
    public void printBloque() {
        String temp = "";
        for(int i = 0; i < this.matrix.size(); ++i){
            for(int j = 0; j < this.matrix.get(0).size(); ++j){
                String s = this.matrix.get(i).get(j).getValor();
                if (s.equals("")) s = "-empty-";
                temp += s + "  ";
            }
            temp += "\n";
        }
        System.out.println(temp);
    }
    public void printBloque_Contenido() {
        String temp = "";
        for(int i = 0; i < this.matrix.size(); ++i){
            for(int j = 0; j < this.matrix.get(0).size(); ++j){
                String s = this.matrix.get(i).get(j).getContenido();
                if (s.equals("")) s = "-empty-";
                temp += s + "  ";
            }
            temp += "\n";
        }
        System.out.println(temp);
    }
    private void swap_filas_in_matrix(int a, int b){
        ArrayList <Celda> aux = this.matrix.get(a);
        this.matrix.set(a, this.matrix.get(b));
        this.matrix.set(b, aux);
    }
    private static int compareStrings(String s1, String s2) {

        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if ((int) s1.charAt(i) == (int) s2.charAt(i)) {
                continue;
            } else {
                return (int) s1.charAt(i) - (int) s2.charAt(i);
            }
        }

        if (s1.length() < s2.length()) {
            return (s1.length() - s2.length());
        } else if (s1.length() > s2.length()) {
            return (s1.length() - s2.length());
        } else {
            return 0;
        }
    }
    private void restoreHoja() {
        int i = this.first.getFil();
        int j = this.first.getCol();
        ArrayList<ArrayList<String>> contenidos = new ArrayList<>();
        for (ArrayList<Celda> celdas : this.matrix) {
            ArrayList <String> aux = new ArrayList<>();
            for (int jj = 0; jj < this.matrix.get(0).size(); ++jj) {
                String cont = celdas.get(jj).getContenido();
                aux.add(cont);
            }
            contenidos.add(aux);
        }
        for (ArrayList<String> contenido : contenidos) {
            for (int jj = 0; jj < contenidos.get(0).size(); ++jj) {
                this.sheet.setContenidoCelda(i, j, contenido.get(jj));
                ++j;
            }
            j -= this.matrix.get(0).size();
            ++i;
        }
    }
}