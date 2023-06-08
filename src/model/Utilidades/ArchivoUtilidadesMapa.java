package model.Utilidades;

import java.io.*;
import java.util.Map;

    public class ArchivoUtilidadesMapa<T extends Map> {
        public void grabar(T mapa, String nombre_archivo) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(nombre_archivo);
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(fos);

                    oos.writeObject(mapa);

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

        public T leer(String nombre_archivo) {
            T mapa = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(nombre_archivo);
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(fis);

                    mapa = (T) ois.readObject();

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
            return mapa;
        }
    }

