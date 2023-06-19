package excepciones;

public class ClienteDeudorException extends Exception{

    public ClienteDeudorException()
    {
        super("ESTE CLIENTE ESTA ADEUDANDO.");
    }

    public ClienteDeudorException(String message) {
        super(message);
    }
}
