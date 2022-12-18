package prop.excepcions;

public class referenciaCircular extends Exception {
    public referenciaCircular(String errorMessage){
        super(errorMessage);
    }
}
