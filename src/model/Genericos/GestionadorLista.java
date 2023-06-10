package model.Genericos;

import java.io.Serializable;
import java.util.ArrayList;

public class GestionadorLista <T> implements Serializable {

    ArrayList<T>listaElementos;
    public GestionadorLista() {
        listaElementos=new ArrayList<>();
    }

   public void Agregar(T elemento)
   {
       listaElementos.add(elemento);
   }

   public void Eliminar(T elemento)
   {
       listaElementos.remove(elemento);
   }

   public String Listar()
   {
       String rta=" ";
       for (T elemento:listaElementos) {
           rta=rta+elemento.toString();
       }
       return rta;
   }

   public int contador(){
        return listaElementos.size();
   }

   public T devolverElemento(int index){
        return listaElementos.get(index);
   }


}
