package model.Personal;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;

import java.time.LocalDate;
import java.util.ArrayList;

public class Administrativo extends Usuario {

    public Administrativo(String nombre, String dni, String telefono, ArrayList<Apercibimiento> listaApercibimientos, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre, dni, telefono, listaApercibimientos, estado, grupo_sanguineo, contacto_emergencia, obra_social, alta_medica, fecha_nacimiento, comentario, CUIL, usuario, contrasenia);
    }

    public Administrativo()
    {
        super();
    }



}
