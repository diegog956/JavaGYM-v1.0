package excepciones;

public class NoEncontradoException extends Exception{

    private Class clase;

    public NoEncontradoException(Class clase) {
        this.clase = clase;
    }
    @Override
    public String getMessage() {
        return "ERROR. "+ clase.getSimpleName() + " no se encuenta en sistema.";
    }
}