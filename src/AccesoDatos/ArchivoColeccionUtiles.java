package AccesoDatos;

import java.io.*;
import java.util.Collection;
/**Clase generica que permite el guardado y lectura de cualquier coleccion que implemente la interfaz Collection
 * @see Collection para mas detalle.
 */
public class ArchivoColeccionUtiles<T extends Collection> {

    /**Metodo que permite guardar una coleccion en un archivo.
     *@param coleccion Coleccion a guardar.
     *@param nombre_archivo Nombre del archivo donde se guarda la coleccion.
     **/
    public void guardarColeccion(T coleccion, String nombre_archivo){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(nombre_archivo);
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);

                oos.writeObject(coleccion);

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
    /**Metodo que permite leer una coleccion de un archivo.
     * @param nombre_archivo nombre del archivo donde se encuentra la coleccion a leer.
     * @return Coleccion leida de archivo.*/
    public T leerColeccion(String nombre_archivo) {
        T coleccion = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(nombre_archivo);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fis);

                coleccion = (T) ois.readObject();

            }catch (EOFException e){

            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {

            //e.printStackTrace();

        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return coleccion;
    }

}
