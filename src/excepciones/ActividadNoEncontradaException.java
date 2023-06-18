package excepciones;

public class ActividadNoEncontradaException extends Exception{
    public ActividadNoEncontradaException() {
        super("La actividad no fue encontrada");
    }
}