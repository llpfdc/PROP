package prop.domain.core;

/**
 * @author Alejandro Durán Saborido
 *
 */
public class Estado {
    private static Hoja HojaActual = new Hoja();
    private static Celda CeldaCopiada = new Celda();
    private static Bloque BloqueActual = new Bloque();
    private static Bloque BloqueCopiado = new Bloque();
    private static boolean hayCeldaCopiada = false;
    private static boolean hayBloqueCopiado = false;

    public static Hoja getHojaActual() {
        return HojaActual;
    }

    public static Celda getCeldaCopiada() {
        return CeldaCopiada;
    }

    public static Bloque getBloqueActual() {
        return BloqueActual;
    }

    public static Bloque getBloqueCopiado() {
        return BloqueCopiado;
    }

    public static boolean getHayCeldaCopiada() {
        return hayCeldaCopiada;
    }

    public static boolean getHayBloqueCopiado() {
        return hayBloqueCopiado;
    }

    public static void setHojaActual(Hoja h) {
        HojaActual = h;
    }

    public static void setCeldaCopiada(Celda c) {
        CeldaCopiada = c;
        hayCeldaCopiada = true;
    }

    public static void setBloqueActual(Bloque c) {
        BloqueActual = c;
    }

    public static void setBloqueCopiado(Bloque c) {
        BloqueCopiado = c;
        hayBloqueCopiado = true;
    }
}
