package model.Persona;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Persona implements Serializable {
    private String nombre;
    private String dni;
    private String telefono;
    private ArrayList<Apercibimiento> listaApercibimientos;
    private Eestado estado;
    private EGrupoSanguineo grupo_sanguineo;
    private String contacto_emergencia;
    private String obra_social;
    private LocalDate fecha_nacimiento;

    private String comentario;

    public Persona(String nombre, String dni, String telefono,Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,LocalDate fecha_nacimiento,String comentario) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        listaApercibimientos = new ArrayList<>();
        this.estado = estado;
        this.grupo_sanguineo = grupo_sanguineo;
        this.contacto_emergencia = contacto_emergencia;
        this.obra_social = obra_social;
        this.fecha_nacimiento = fecha_nacimiento;
        this.comentario=comentario;
    }

    public Persona ()
    {
        nombre=" ";
        dni=" ";
        telefono=" ";
        listaApercibimientos=new ArrayList<Apercibimiento>();
        estado=Eestado.ACTIVO;
        grupo_sanguineo=EGrupoSanguineo.NINGUNO;
        contacto_emergencia=" ";
        obra_social=" ";
        fecha_nacimiento=null;
        comentario=" ";
    }

    public String getComentario() {
        return comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public ArrayList<Apercibimiento> getListaApercibimientos() {
        return listaApercibimientos;
    }

    public Eestado getEstado() {
        return estado;
    }

    public EGrupoSanguineo getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public String getContacto_emergencia() {
        return contacto_emergencia;
    }

    public String getObra_social() {
        return obra_social;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaApercibimientos=" + listaApercibimientos +
                ", estado=" + estado +
                ", grupo_sanguineo=" + grupo_sanguineo +
                ", contacto_emergencia='" + contacto_emergencia + '\'' +
                ", obra_social='" + obra_social + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}

