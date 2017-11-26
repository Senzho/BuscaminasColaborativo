package LogicaNegocio;

public class InvalidIpAddressException extends Exception{
    private String message;
    
    public InvalidIpAddressException(String message){
        this.message = message;
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }
}
