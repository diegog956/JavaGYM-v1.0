package excepciones;

public class RutinaYaPedida extends Exception {

    public RutinaYaPedida()
    {
        super("ESTE CLIENTE YA PIDIO RUTINA");
    }
}
