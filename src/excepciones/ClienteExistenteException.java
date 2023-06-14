package excepciones;

public class ClienteExistenteException extends Exception {
    //Excepcion arrojada cuando un nuevo usuario intenta registrarse con un nombre de usuario ya presente en sistema
    private String dni;

    public ClienteExistenteException(String message, String dni) {
        super(message);
        this.dni = dni;
    }
}
