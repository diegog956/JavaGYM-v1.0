package excepciones;

public class ExistenteException extends Exception{
    private Class clase;

    public ExistenteException(Class clase) {
        this.clase = clase;
    }
    @Override
    public String getMessage() {
        return "No se pudo agregar " + clase.getSimpleName() + ". Ya se encuentra en sistema.";
    }
}