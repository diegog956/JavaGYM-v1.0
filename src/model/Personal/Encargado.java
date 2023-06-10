package model.Personal;

import java.io.Serializable;

public class Encargado extends Usuario implements Serializable
{
    public Encargado(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public Encargado(){
        this("","");
    }


    /**Pensar el rol del encargado a nivel metodos.*/
    /**Podemos agregar atributos que se pueden tomar de la factura.
     Tomar a encargado como gimnasio. EJ: El domicilio del encargado es el domicilio del gym.
     Lo mismo que el CUIL del mismo, el mail, etc.*/
}
