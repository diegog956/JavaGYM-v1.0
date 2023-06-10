package model.Genericos;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class GestionadorLinkedHashSet<T> extends LinkedHashSet{

    LinkedHashSet<T> hashset;

    public GestionadorLinkedHashSet() {
        hashset = new LinkedHashSet<>();
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

    public int contador(){
        return hashset.size();
    }

    public T devolverElemento(int index){
        T elemento = null;
        Iterator<T> it = hashset.iterator();
        int i=0;
        while(it.hasNext() && index!=i){
        i++;
        it.next();
        }
        if(index == i){
        elemento = it.next();
        }

        return elemento;
    }

}
