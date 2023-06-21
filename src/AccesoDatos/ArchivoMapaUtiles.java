package AccesoDatos;

import java.io.*;
import java.util.Map;
/**Clase generica que permite el guardado y lectura de cualquier coleccion que implemente la interfaz Map*/
public class ArchivoMapaUtiles <T extends Map> implements Serializable {
    /**Metodo que permite guardar un mapa en un archivo.
     *@param coleccion Mapa a guardar.
     *@param nombre_archivo Nombre del archivo donde se guarda el mapa.
     **/
    public void guardarMapa(T coleccion, String nombre_archivo){
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

    } catch (
    FileNotFoundException e) {

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

    /**Metodo que permite leer un mapa de un archivo.
     * @param nombre_archivo nombre del archivo donde se encuentra el mapa a leer.
     * @return Mapa leido de archivo.*/
    public T leerMapa(String nombre_archivo) {
        T mapa = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(nombre_archivo);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fis);

                mapa = (T) ois.readObject();

            } catch (EOFException e) {

            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally{
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
        return mapa;
    }
}
