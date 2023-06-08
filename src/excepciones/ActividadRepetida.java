package excepciones;

public class ActividadRepetida extends Exception {
    private String nombre;

    public ActividadRepetida(String message, String nombre) {
        super(message);
        this.nombre = nombre;
    }
}
