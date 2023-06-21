package excepciones;
/**Excepcion que se lanza cuando se encuentra que el cliente posee deuda*/
public class ClienteDeudorException extends Exception{

    public ClienteDeudorException()
    {
        super("Cliente posee deuda.");
    }

    public ClienteDeudorException(String message) {
        super(message);
    }
}
