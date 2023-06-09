package model.Personal;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Persona.Persona;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Personal extends Persona implements Serializable
{
    private String CUIL;

    public Personal(String nombre, String dni, String telefono,Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL) {
        super(nombre,dni,telefono,estado,grupo_sanguineo,contacto_emergencia,obra_social,fecha_nacimiento,comentario);
        this.CUIL = CUIL;
    }

    public Personal()
    {
        super();


    }

    @Override
    public String toString() {
        return "Personal{" +
                "CUIL='" + CUIL + '\'' +
                "} " + super.toString();
    }
}
