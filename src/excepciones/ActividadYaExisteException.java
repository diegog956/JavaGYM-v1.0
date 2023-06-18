package excepciones;

/**
 Permite dar un feedback de lo que ocurre cuando una actividad que se pretende a agregar ya se encuentra en sistema**/
public class ActividadYaExisteException extends Exception{
    //utilidad en UI
    public ActividadYaExisteException(String message) {
        super(message);
    }
}