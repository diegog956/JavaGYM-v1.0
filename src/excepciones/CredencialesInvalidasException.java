package excepciones;
/**Excepcion que se lanza si el Usario y/o contraseña no son validas.*/
public class CredencialesInvalidasException extends Exception{
    //Excepcion lanzada cuando el ingreso al sistema es fallido, x usuario o password erroneos

    public CredencialesInvalidasException() {
        super("Usuario y/o contraseña invalidos");
    }
}
