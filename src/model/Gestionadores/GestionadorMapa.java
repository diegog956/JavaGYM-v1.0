package model.Gestionadores;

import java.util.HashMap;

public class GestionadorMapa<K extends String,V>{
    private HashMap<K,V> mapaGeneral;

    public GestionadorMapa(){
        mapaGeneral = new HashMap<K,V>();
    }

    public boolean Agregar(K key,V elemento){
        boolean rta = false;
        if (!mapaGeneral.containsKey(key)) {
            mapaGeneral.put(key, elemento);
            rta = true;
        }

        return rta;
    }

    public boolean Eliminar(K key,V elemento)
    {
        boolean rta=false;
        if (mapaGeneral.containsKey(key))
        {
            mapaGeneral.remove(key);
            rta=true;
        }
        return rta;
    }
}
