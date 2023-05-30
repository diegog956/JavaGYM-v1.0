package model.Enum;

public enum Eestado {
    ACTIVO(1),
    INACTIVO(2),
    DEUDOR(3),
    BANEADO(4);

    private int id;

    private Eestado(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
