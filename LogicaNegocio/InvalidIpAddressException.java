package LogicaNegocio;

public class InvalidIpAddressException extends Exception{
    private final String message;
    /**
     * 
     * @param message, el mensaje personalizado de la excepción.
     */
    public InvalidIpAddressException(String message){
        this.message = message;
    }
    /**
     * 
     * @return message, regresa el mensaje de la excepción.
     */
    @Override
    public String getMessage(){
        return this.message;
    }
}
