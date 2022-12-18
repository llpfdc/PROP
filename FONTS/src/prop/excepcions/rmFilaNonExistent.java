package prop.excepcions;

public class rmFilaNonExistent extends Exception{
    public rmFilaNonExistent(String errorMessage){
        super(errorMessage);
    }
}
