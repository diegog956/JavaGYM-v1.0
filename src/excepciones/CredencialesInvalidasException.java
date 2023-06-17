package excepciones;

public class CredencialesInvalidasException extends Exception{
    //Excepcion lanzada cuando el ingreso al sistema es fallido, x usuario o password erroneos

    public CredencialesInvalidasException() {
        super("Usuario y/o contraseña invalidos");
    }
}
