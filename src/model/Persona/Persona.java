package model.Persona;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;

import java.time.LocalDate;
import java.util.ArrayList;

public class Persona {
    private String nombre;
    private String dni;
    private String telefono;
    private ArrayList<Apercibimiento> listaApercibimientos;
    private Eestado estado;
    private EGrupoSanguineo grupo_sanguineo;
    private String contacto_emergencia;
    private String obra_social;
    private boolean alta_medica;
    private LocalDate fecha_nacimiento;

    public Persona(String nombre, String dni, String telefono, ArrayList<Apercibimiento> listaApercibimientos, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.listaApercibimientos = listaApercibimientos;
        this.estado = estado;
        this.grupo_sanguineo = grupo_sanguineo;
        this.contacto_emergencia = contacto_emergencia;
        this.obra_social = obra_social;
        this.alta_medica = alta_medica;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Persona ()
    {
        nombre=" ";
        dni=" ";
        telefono=" ";
        listaApercibimientos=new ArrayList<Apercibimiento>();
        estado=null;              //DUDA
        grupo_sanguineo=null;
        contacto_emergencia=" ";
        obra_social=" ";
        alta_medica=true;
        fecha_nacimiento=null;
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

    public boolean isAlta_medica() {
        return alta_medica;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
}

