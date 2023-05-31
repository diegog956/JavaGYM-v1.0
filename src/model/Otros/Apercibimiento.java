package model.Otros;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Apercibimiento{" +
                "descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apercibimiento that = (Apercibimiento) o;
        return Objects.equals(descripcion, that.descripcion) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, fecha);
    }
}
