package model.ActivYrutina;

import model.Enum.EdiaSemana;
import model.Enum.Eestado;

import java.util.ArrayList;
import java.util.Objects;

public class Actividad {
    private int id;  //DEPENDE LA ACTIVIDAD QUE TENGAN UN NUMERO
    private String nombre;
    private ArrayList<EdiaSemana>listaDias;

    private String horario;
    private String nombre_instructor;
    private int cupo;
    private int inscriptos;
    private boolean disponible;
    private String comentario;
    private double precio_mensual;

    public Actividad(int id, String nombre, String horario, ArrayList<EdiaSemana> listaDias, String nombre_instructor, int cupo, int inscriptos, boolean disponible, String comentario, double precio_mensual) {
        this.id = id;
        this.nombre = nombre;
        this.listaDias = listaDias;
        this.nombre_instructor = nombre_instructor;
        this.cupo = cupo;
        this.inscriptos = inscriptos;
        this.disponible = disponible;
        this.comentario = comentario;
        this.precio_mensual = precio_mensual;
        this.horario=horario;
    }

    public Actividad ()
    {
        id=0;
        nombre=" ";
        listaDias=new ArrayList<EdiaSemana>();
        nombre_instructor=" ";
        cupo=0;
        inscriptos=0;
        disponible=true;
        comentario=" ";
        precio_mensual=0;
        horario=" ";
    }

    public String getHorario() {
        return horario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<EdiaSemana> getListaDias() {
        return listaDias;
    }

    public String getNombre_instructor() {
        return nombre_instructor;
    }

    public int getCupo() {
        return cupo;
    }

    public int getInscriptos() {
        return inscriptos;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getComentario() {
        return comentario;
    }

    public double getPrecio_mensual() {
        return precio_mensual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return id == actividad.id && cupo == actividad.cupo && inscriptos == actividad.inscriptos && disponible == actividad.disponible && Double.compare(actividad.precio_mensual, precio_mensual) == 0 && Objects.equals(nombre, actividad.nombre) && Objects.equals(listaDias, actividad.listaDias) && Objects.equals(horario, actividad.horario) && Objects.equals(nombre_instructor, actividad.nombre_instructor) && Objects.equals(comentario, actividad.comentario);
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", listaDias=" + listaDias +
                ", horario='" + horario + '\'' +
                ", nombre_instructor='" + nombre_instructor + '\'' +
                ", cupo=" + cupo +
                ", inscriptos=" + inscriptos +
                ", disponible=" + disponible +
                ", comentario='" + comentario + '\'' +
                ", precio_mensual=" + precio_mensual +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, listaDias, horario, nombre_instructor, cupo, inscriptos, disponible, comentario, precio_mensual);
    }
}


