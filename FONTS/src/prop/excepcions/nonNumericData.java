package prop.excepcions;

public class nonNumericData extends Exception{
    public nonNumericData(String errorMessage){
        super(errorMessage);
    }
}
