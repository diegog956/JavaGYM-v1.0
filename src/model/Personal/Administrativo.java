package model.Personal;

import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Persona.Cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**Clase que referencia al administrativo que hara del software objeto de uso. */
public class Administrativo extends Usuario implements Serializable {
    /**Constructor de la clase Administrativo*/
    public Administrativo(String nombre, String dni, EGenero genero, String telefono, String domicilio, String email , EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre, dni,genero, telefono, domicilio,email, grupo_sanguineo,  contacto_emergencia,  obra_social, fecha_nacimiento,  comentario,  CUIL,  usuario,  contrasenia);
    }
    /**Constructor vacio de la clase Administrativo.*/
    public Administrativo()
    {
        super();
    }

}
