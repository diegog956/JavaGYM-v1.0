package model.ActivYrutina;

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
}
