package model.Personal;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;

import java.io.Serializable;
import java.time.LocalDate;

public class Encargado extends Usuario implements Serializable
{
    public Encargado(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public Encargado(){
        this("","");
    }

    public Encargado(String nombre, String dni, String telefono, String domicilio, String comentario, String CUIL) {
        super(nombre, dni, telefono, domicilio, null, null, "", "", true, null, comentario, CUIL, "encargado2023","encargado123");
    }

    /**Pensar el rol del encargado a nivel metodos.*/
    /**Podemos agregar atributos que se pueden tomar de la factura.
     Tomar a encargado como gimnasio. EJ: El domicilio del encargado es el domicilio del gym.
     Lo mismo que el CUIL del mismo, el mail, etc.*/


}
