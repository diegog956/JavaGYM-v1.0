package excepciones;

public class RutinaSinAvisoException extends Exception {
    public RutinaSinAvisoException() {
        super("ESTE CLIENTE NO PIDIO RUTINA");
    }
}
