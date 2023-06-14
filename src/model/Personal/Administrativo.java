package model.Personal;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Administrativo extends Usuario implements Serializable {

    public Administrativo(String nombre, String dni, String telefono,String domicilio ,EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre, dni, telefono,  domicilio, grupo_sanguineo,  contacto_emergencia,  obra_social, fecha_nacimiento,  comentario,  CUIL,  usuario,  contrasenia);
    }

    public Administrativo()
    {
        super();
    }
}
