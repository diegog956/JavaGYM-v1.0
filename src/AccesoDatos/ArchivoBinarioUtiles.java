package AccesoDatos;

import java.io.*;
/**Clase que permite la persistencia en archivos de datos binarios.*/
public class ArchivoBinarioUtiles<T extends Object>{
/**Guarda un objeto en un archivo
 * @param objeto Objeto a guardar.
 * @param nombre_archivo nombre del archivo donde se quiere almacenar.
 * */
    public void guardarObjeto(T objeto, String nombre_archivo){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(nombre_archivo);
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);

                oos.writeObject(objeto);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**Obtener objeto de un archivo
     * @param nombre_archivo nombre del archivo donde buscar el objeto.
     * @return Objecto leido.*/
    public T leerObjeto(String nombre_archivo) {
        T objeto = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(nombre_archivo);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fis);

                objeto = (T) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objeto;
    }

}
