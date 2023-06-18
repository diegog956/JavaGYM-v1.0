package AccesoDatos;

import java.io.*;
import java.util.Collection;

public class ArchivoColeccionUtiles<T extends Collection> {

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
