import java.io.IOException;

public class FormeNonEmpilable extends Exception{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public FormeNonEmpilable(){}

    public FormeNonEmpilable(String _message){
        message = _message;
    }

    public String toString(){
        return "FormeNonEmpilable : " + message;
    }
}