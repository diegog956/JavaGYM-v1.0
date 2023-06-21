package excepciones;
/**Excepcion que es lanzada cuando no se encuentra determinado objecto en una determinada clase*/
public class NoEncontradoException extends Exception{

    private Class clase;

    public NoEncontradoException(Class clase) {
        this.clase = clase;
    }
    @Override
    public String getMessage() {
        return "ERROR. "+ clase.getSimpleName() + " no se encuentra en sistema.";
    }
}