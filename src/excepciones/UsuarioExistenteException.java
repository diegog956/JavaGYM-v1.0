package excepciones;

public class UsuarioExistenteException extends Exception{
    public UsuarioExistenteException() {
        super("El nombre de usuario elegido no esta disponible. Debera elegir otro"); //significa que esta en uso, pero no es necesario tan explicito segun entiendo
    }
}
