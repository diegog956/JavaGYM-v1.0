package model.Personal;

import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;

import java.io.Serializable;
import java.time.LocalDate;
/**Clase que referencia al gerente o persona a cargo del Gimnasio. Funciona como credencial para validar metodos
 * que solo tendra acceso el mismo.*/
public class Encargado extends Usuario implements Serializable
{
    /**Constructor de la clase Encargado.*/
    public Encargado(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, String comentario, String CUIL) {
        super(nombre, dni,genero, telefono, domicilio,email,null,"","",null,"","", "encargado2023","encargado2023");
    }


}
