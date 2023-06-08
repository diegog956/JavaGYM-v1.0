package model.Personal;


import model.ActivYrutina.Actividad;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Instructor extends Personal {
private HashSet<Actividad> actividades;
private String imagenPerfil;

    public Instructor(String nombre, String dni, String telefono, ArrayList<Apercibimiento> listaApercibimientos, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL, ArrayList<Actividad> actividades, String imagenPerfil) {
        super(nombre, dni, telefono, listaApercibimientos, estado, grupo_sanguineo, contacto_emergencia, obra_social, alta_medica, fecha_nacimiento, comentario, CUIL);
        actividades = actividades;
        this.imagenPerfil = imagenPerfil;
    }

    public Instructor()
    {
        super();
        actividades=new ArrayList<>();
        imagenPerfil=" ";
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "actividades=" + actividades +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(actividades, that.actividades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividades);
    }
}
