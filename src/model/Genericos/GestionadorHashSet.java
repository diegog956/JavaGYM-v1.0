package model.Genericos;

import java.util.HashSet;

public class GestionadorHashSet <T> {

    HashSet<T> hashset;

    public GestionadorHashSet() {
        hashset = new HashSet<>();
    }

    public void Agregar(T elemento)
    {
        hashset.add(elemento);
    }

    public void Eliminar(T elemento)
    {
        hashset.remove(elemento);
    }

    public String Listar()
    {
        String rta=" ";
        for (T elemento:hashset) {
            rta=rta+elemento.toString();
        }
        return rta;
    }
}
