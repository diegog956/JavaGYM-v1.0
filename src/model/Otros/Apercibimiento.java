package model.Otros;

import java.time.LocalDate;

public class Apercibimiento {
    private String descripcion;
    private LocalDate fecha;

    public Apercibimiento()
    {
        descripcion=" ";
        fecha=null;
    }

    public Apercibimiento(String descripcion, LocalDate fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
