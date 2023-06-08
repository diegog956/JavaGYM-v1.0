package excepciones;

public class ClienteExistenteException extends Exception {
    private String dni;

    public ClienteExistenteException(String message, String dni) {
        super(message);
        this.dni = dni;
    }
}
