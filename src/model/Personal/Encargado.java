package model.Personal;

import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;

import java.io.Serializable;
import java.time.LocalDate;

public class Encargado extends Usuario implements Serializable
{
    public Encargado(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, String comentario, String CUIL) {
        super(nombre, dni,genero, telefono, domicilio,email,null,"","",null,"","", "encargado2023","encargado2023");
    }

    /**Pensar el rol del encargado a nivel metodos.*/
    /**Podemos agregar atributos que se pueden tomar de la factura.
     Tomar a encargado como gimnasio. EJ: El domicilio del encargado es el domicilio del gym.
     Lo mismo que el CUIL del mismo, el mail, etc.*/


}
