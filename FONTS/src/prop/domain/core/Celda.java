package prop.domain.core;

import prop.domain.core.dato.Dato;

import java.util.*;

/**
 * @author Javier Abella Nieto
 */

public class Celda {
    private int fil;
    private int col;
    private Hoja hoja;
    private Dato d;
    private List<Celda> observadas = new ArrayList<>();
    private List<Celda> observadores = new ArrayList<>();
    private boolean ini;
    private final static int BASENUMERICA = 26;

    //creadoras
    /**
     * crea una celda vacía
     * @param fil fila de la nueva celda
     * @param col columna de la nueva celda
     * @param hoja hoja a la que pertenece la nueva celda
     */
    public Celda(int fil, int col, Hoja hoja){
        this.fil = fil;
        this.col = col;
        this.hoja = hoja;
        this.d = new Dato("0.0");
        this.ini = false;
    }
    /**
     * Crea una celda vacía sin parámetros
     */
    public Celda() {
    }
    /**
     * crea una celda con contenido
     * @param fil fila de la nueva celda
     * @param col columna de la nueva celda
     * @param hoja hoja a la que pertenece la nueva celda
     * @param contenido contenido que habrá en la nueva celda
     */
    public Celda(int fil, int col, Hoja hoja, String contenido) {
        this.fil = fil;
        this.col = col;
        this.hoja = hoja;
        this.d = new Dato(contenido);
        this.ini = true;
        if (d.isRef()) addObservados();

        //añadir patron observador si es necesario
    }

    /**
     * crea una copia de la celda
     * @param c celda a copiar
     */
    public Celda(Celda c) {
        this.fil = c.fil;
        this.col = c.col;
        this.hoja = c.hoja;
        this.d = c.d;
        this.observadas = c.observadas;
        this.observadores = c.observadores;
        this.ini = c.ini;
    }

    //Getters
    /**
     * @return fila a la que pertenece la celda actual
     */
    public int getFil() {
        return fil;
    }

    /**
     * @return columna a la que pertenece la celda actual
     */
    public int getCol() {
        return col;
    }

    /**
     * @return hoja a la que pertenece la celda actual
     */
    public Hoja getHoja() {
        return hoja;
    }

    /**
     * @return contenido de la celda sin ser procesado
     */
    public String getContenido() {
        return d.getValor();
    }

    /**
     * @return contenido de la celda ya procesado
     */
    public String getValor() {
        return d.getValorProcesado();
    }

    /**
     * @return devuelve el Dato de la celda actual
     */
    public Dato getDato(){
        return this.d;
    }

    /**
     *
     * @return true si la celda ha sido inicializada, false en caso contrario
     */
    public boolean getIni(){
        return this.ini;
    }


    //Setters

    /**
     * @param col nueva columna de la celda
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @param fil nueva fila de la celda
     */
    public void setFil(int fil) {
        this.fil = fil;
    }

    /**
     * cambia el contenido de la celda al nuevo, en caso de error se restaura el antiguo contenido sin errores
     * @param s nuevo contenido de la celda
     */
    public void setContenido(String s) {
        Dato tmp = this.d;
        this.d = new Dato(s);
        ini = !Objects.equals(s, "");
        if ( d.isRef()) {
            if (!addObservados()){
                rmObservados();
                this.d = tmp;
            }
        }
        if (!this.observadores.isEmpty()) {
            notification();
        }
    }

    /**
     * añade a lista de observados todas las celdas que están siendo observadas por la actual
     * @return true si se han podido añadir las celdas sin errores false en caso contrario
     */
    private boolean addObservados () {
        List<Celda> l = mirarRef(); //
        if (l.isEmpty())
            //throw new E ("autorference");
            return false;
            //excepcion
        if (refCircular(l)) {
            return false;
        }
        observadas.addAll(l);
        addObservadores(l);
        return true;
    }
    /**
     * elimina las celdas que ya no están siendo observadas por la actual de su lista de observados
     */
    private void rmObservados(){
        List<Celda> l = diferenciaObservadas();
        for (Celda celda : l) {
            observadas.remove(celda);
            rmObservadores(l);
        }
    }

    /**
     * añade la celda actual a la lista de observadores de la celda a las que mira
     * @param l lista de las celdas a las que está mirando la celda actual
     */
    private void addObservadores(List<Celda> l){
        for (Celda s : l) {
            s.observadores.add(hoja.getCelda(fil, col));
        }
    }

    /**
     * elimina la celda actual de la lista de observadores de la celda a las que miraba
     * @param l lista de las celdas a las que miraba la actual pero ya no
     */
    private void rmObservadores(List<Celda> l){
        for (Celda s : l) {
            s.observadores.remove(hoja.getCelda(fil, col));
        }
    }

    //notificar

    /**
     * notifica a las celdas observadoras que su observada ha cambiado de valor
     */
    private void notification(){
        for (Celda c : observadores) {
            c.getValor();
        }
    }

    /**
     * descubre que celdas están siendo referenciadas por la actual
     * @return una lista con todas las celdas referenciadas por la actual
     */
    private List<Celda> mirarRef () {
        List<Celda> res = new ArrayList<>();
        String s = d.valor;
        if (isFunction(s))
            res = refBloque();
        else {
            for (int i = 1; i < s.length() - 1; ++i) {
                if (s.charAt(i) == ':') {
                    Celda temp = getCeldaAt(i,s);
                    if (temp == hoja.getCelda(fil, col)) {
                        return new ArrayList<>();
                    } else res.add(temp);
                }
            }
        }
        return res;
    }

    /**
     * descubre que bloques están siendo referenciados por la celda actual
     * @return una lista con todas las referencias a bloques por la actual
     */
    private List<Celda> refBloque(){
        String a = d.getValor();
        List<Celda> res = new ArrayList<>();
        int func_start;
        int func_end = 0;
        while (isFunction(a)) {
            if (a.contains("AVG")) {
                func_start = a.indexOf("AVG");
                ArrayList<Celda> block_index = new ArrayList<>();
                for (int i = func_start; a.charAt(i) != ')'; ++i)
                {
                    if (a.charAt(i) == ':') {
                        Celda temp = getCeldaAt(i,a);
                        if (temp == hoja.getCelda(fil, col)) {
                            return new ArrayList<>();
                        }
                        block_index.add(temp);
                    }
                    func_end = i;
                }

                Bloque b = new Bloque(block_index.get(0), block_index.get(1));
                res.addAll(b.getCells());

                StringBuilder sb = new StringBuilder(a);
                for (int i = func_start; i <= func_end + 1; ++i)
                    sb.deleteCharAt(func_start);
                a = sb.toString();
            }

            if (a.contains("MED")) {
                func_start = a.indexOf("MED");
                ArrayList<Celda> block_index = new ArrayList<>();
                for (int i = func_start; i != ')'; ++i) {
                    if (a.charAt(i) == ':') {
                        Celda temp = getCeldaAt(i,a);
                        if (temp == hoja.getCelda(fil, col)) {
                            return new ArrayList<>();
                        }
                        block_index.add(temp);
                    }
                    func_end = i;
                }
                Bloque b = new Bloque(block_index.get(0), block_index.get(1));
                res.addAll(b.getCells());

                StringBuilder sb = new StringBuilder(a);
                for (int i = func_start; i <= func_end + 1; ++i)
                    sb.deleteCharAt(func_start);
                a = sb.toString();
            }

            if (a.contains("VAR")) {
                func_start = a.indexOf("VAR");
                ArrayList<Celda> block_index = new ArrayList<>();
                for (int i = func_start; i != ')'; ++i) {
                    if (a.charAt(i) == ':') {
                        Celda temp = getCeldaAt(i,a);
                        if (temp == hoja.getCelda(fil, col)) {
                            return new ArrayList<>();
                        }
                        block_index.add(temp);
                    }
                    func_end = i;
                }
                Bloque b = new Bloque(block_index.get(0),block_index.get(1));
                res.addAll(b.getCells());

                StringBuilder sb = new StringBuilder(a);
                for (int i = func_start; i <= func_end + 1; ++i)
                    sb.deleteCharAt(func_start);
                a = sb.toString();
            }

            if (a.contains("DESV")) {
                func_start = a.indexOf("DESV");
                ArrayList<Celda> block_index = new ArrayList<>();
                for (int i = func_start; i != ')'; ++i) {
                    if (a.charAt(i) == ':') {
                        Celda temp = getCeldaAt(i,a);
                        if (temp == hoja.getCelda(fil, col)) {
                            return new ArrayList<>();
                        }
                        block_index.add(temp);
                    }
                    func_end = i;
                }
                Bloque b = new Bloque(block_index.get(0),block_index.get(1));
                res.addAll(b.getCells());

                StringBuilder sb = new StringBuilder(a);
                for (int i = func_start; i <= func_end + 1; ++i)
                    sb.deleteCharAt(func_start);
                a = sb.toString();
            }

            if (a.contains("COV")) {
                func_start = a.indexOf("COV");
                ArrayList<Celda> block_index = new ArrayList<>();
                for (int i = func_start; i != ')'; ++i) {
                    if (a.charAt(i) == ':') {
                        Celda temp = getCeldaAt(i,a);
                        if (temp == hoja.getCelda(fil, col)) {
                            return new ArrayList<>();
                        }
                        block_index.add(temp);
                    }
                    func_end = i;
                }
                Bloque b = new Bloque(block_index.get(0),block_index.get(1));
                Bloque b2 = new Bloque(block_index.get(2),block_index.get(3));
                res.addAll(b.getCells());
                res.addAll(b2.getCells());

                StringBuilder sb = new StringBuilder(a);
                for (int i = func_start; i <= func_end + 1; ++i)
                    sb.deleteCharAt(func_start);
                a = sb.toString();
            }

            if (a.contains("PEARS")) {
                func_start = a.indexOf("AVG");
                ArrayList<Celda> block_index = new ArrayList<>();
                for (int i = func_start; i != ')'; ++i) {
                    if (a.charAt(i) == ':') {
                        Celda temp = getCeldaAt(i,a);
                        if (temp == hoja.getCelda(fil, col)) {
                            return new ArrayList<>();
                        }
                        block_index.add(temp);
                    }
                    func_end = i;
                }
                Bloque b = new Bloque(block_index.get(0),block_index.get(1));
                Bloque b2 = new Bloque(block_index.get(2), block_index.get(3));
                res.addAll(b.getCells());
                res.addAll(b2.getCells());

                StringBuilder sb = new StringBuilder(a);
                for (int i = func_start; i <= func_end + 1; ++i)
                    sb.deleteCharAt(func_start);
                a = sb.toString();
            }
        }

        Dato tmp = new Dato(a);
        if (tmp.isRef()) {
            for (int i = 1; i < a.length() - 1; ++i) {
                if (a.charAt(i) == ':') {
                    Celda temp = getCeldaAt(i,a);
                    if (temp == hoja.getCelda(fil, col)) {
                        return new ArrayList<>();
                    } else res.add(temp);
                }
            }
        }
        return res;
    }

    /**
     * devuelve lista con las que hay que eliminar de observadas
     */
    private List<Celda> diferenciaObservadas() {
        List<Celda> res = new ArrayList<>();
        List<Celda> nuevo = mirarRef();
        for (Celda observada : observadas) {
            boolean found = false;
            ListIterator<Celda> it2 = nuevo.listIterator();
            while (it2.hasNext() && !found)
                if (observada == it2.next()) found = true;
            if (!found) res.add(observada);
        }
        return res;
    }

    /**
     * @param l lista de las celdas nuevas que están siendo referencias
     * @return true si se detecta una referencia circular false en caso contrario
     */
    private boolean refCircular(List<Celda> l){
        boolean isCirc = false;
        for (Celda c: l) {
            isCirc = (isCirc || refCircularRec(this, c));
        }
        if (isCirc) {
            System.out.println("Error de referencia circular");
        }
        return isCirc;
    }

    /**
     * @param ori celda original a buscar por si se produce una referencia circular
     * @param actual celda por la que estamos iterando actualmente
     * @return true si se ha detectado mediante la recursion una referencia circular false en caso contrario
     */
    private boolean refCircularRec(Celda ori, Celda actual){
        for (Celda c : actual.observadas) {
            if (c == ori)
                return true;
            else {
                return refCircularRec(ori,c);
            }
        }
        return false;
    }

    /**
     * @param s string contenido de la celda
     * @param i Iterador que apunta a la posición más baja de la coordenada y de la ref ej: AB:12 apunta a B
     * @return string con la coordenada x en número
     */
    private String lookRight(String s, int i){
        StringBuilder res = new StringBuilder(String.valueOf(s.charAt(i)));
        ++i;
        boolean out_of_range = false;
        while (!out_of_range && i <= s.length() - 1){
            if (s.charAt(i) < '0' || s.charAt(i) > '9') out_of_range = true;
            else res.append(s.charAt(i));
            ++i;
        }
        return res.toString();
    }

    /**
     * @param s string contenido de la celda
     * @param i iterador que apunta a la posición más alta de la coordenada x de la ref ej: AB:12 apunta a 1
     * @return string con la coordenada y en formato abecedario, base26
     */
    private String lookLeft(String s, int i){
        StringBuilder res = new StringBuilder(String.valueOf(s.charAt(i)));
        --i;
        boolean out_of_range = false;
        while (!out_of_range && i >= 0 ){
            if (s.charAt(i) < 'A' || s.charAt(i) > 'Z') out_of_range = true;
            else res.append(s.charAt(i));
            --i;
        }
        return res.toString();
    }

    /**
     * traduce de formato abecedario base 26 a formato decimal
     */
    private int translateNumBase (String s){
        int res = 0;
        for (int i = 0; i < s.length(); ++i){
            res += (s.charAt(i) - 'A') * Math.pow(BASENUMERICA,s.length()-i-1) ;
        }
        return res;
    }

    /**
     *
     * @param i apuntador al carácter que denota referencia ':'
     * @param a string contenido de la celda
     * @return celda de la referencia
     */
    private Celda getCeldaAt(int i, String a){
        String right = lookRight(a, i + 1);
        String left = lookLeft(a, i - 1);
        return hoja.getCelda(Integer.parseInt(right) - 1, translateNumBase(left));
    }

    /**
     * detecta si hay una función en un string dado
     * @param s String a analizar
     * @return true si contiene una función de bloque false en caso contrario
     */
    private boolean isFunction(String s){
        return s.contains("AVG") || s.contains("MED") || s.contains("VAR") || s.contains("COV") || s.contains("PEARS") || s.contains("DESV");
    }

}
