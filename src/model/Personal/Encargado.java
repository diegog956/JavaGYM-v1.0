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
}
