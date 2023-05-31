package model.Enum;

public enum EdiaSemana {
    LUNES(1),
    MARTES(2),
    MIERCOLES(3),
    JUEVES(4),
    VIERNES(5),
    SABADO(6),
    DOMINGO(7);

    private int id;

    EdiaSemana(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
