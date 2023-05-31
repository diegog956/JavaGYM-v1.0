package model.ActivYrutina;

import java.util.Objects;

public class Rutina {
    private String instructor;
    private String titulo;
    private String descripcion;
    private double tiempoEstimado;

    public Rutina(String instructor, String titulo, String descripcion, double tiempoEstimado) {
        this.instructor = instructor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Rutina()
    {
        instructor=" ";
        titulo=" ";
        descripcion=" ";
        tiempoEstimado=0;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "instructor='" + instructor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tiempoEstimado=" + tiempoEstimado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rutina rutina = (Rutina) o;
        return Double.compare(rutina.tiempoEstimado, tiempoEstimado) == 0 && Objects.equals(instructor, rutina.instructor) && Objects.equals(titulo, rutina.titulo) && Objects.equals(descripcion, rutina.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructor, titulo, descripcion, tiempoEstimado);
    }
}
