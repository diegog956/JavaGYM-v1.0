
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Persona.Cliente;
import model.Personal.Administrativo;
import model.Personal.Encargado;
import model.Personal.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {


        //Generando Archivo de Usuarios
        ArrayList <Usuario> usuarios_harcodeados = new ArrayList<>();

        //vamos a instanciar 2 usuarios : Juan Perez y Pablo Garcia

        /**Juan Perez**/
        LocalDate fecha1 = LocalDate.of(1991,01,01);
        // usu  y contrasenia = juan.perez
        Usuario usuario1 = new Administrativo("Juan Perez","123456","011-154356789", Eestado.ACTIVO, EGrupoSanguineo.A_NEGATIVO,"011-153247896","Medicus",true,fecha1,"","20-123456-0","juan.perez","juan.perez");

        /**Pablo Garcia**/
        LocalDate fecha2 = LocalDate.of(1992,02,02);
        // usu  y contrasenia = pablo.garcia
        Usuario usuario2 = new Administrativo("Pablo Garcia","7891011","011-154356789", Eestado.ACTIVO, EGrupoSanguineo.A_NEGATIVO,"011-153247896","Medicus",true,fecha1,"","20-7891011-0","pablo.garcia","pablo.garcia");

        /**Encargado**/
        // usu y contrasenia = encargado
        Usuario usuario3 = new Encargado("encargado","encargado");



        usuarios_harcodeados.add(usuario1);
        usuarios_harcodeados.add(usuario2);
        usuarios_harcodeados.add(usuario3);

       // System.out.print("Usuarios en Lista");
       /* for(Usuario usu:usuarios_harcodeados){
            System.out.println("\n" + usu.toString());
        }*/
        //System.out.println(usuarios_harcodeados);
        //personal - persona - usuario



/*
        //Generando archivo
        System.out.println(GenerarArchivoUsuarios(usuarios_harcodeados));


        //Prueba lectura de archivo
        ArrayList<Administrativo>administrativos_leidos = new ArrayList<>();
        ArrayList<Encargado> encargados_leidos = new ArrayList<>();
        System.out.println(LeerArchivo("usuarios.bin",administrativos_leidos,encargados_leidos));

        System.out.print("Leido desde archivo:");
        System.out.print("encargados");
        for(Encargado enc :encargados_leidos){
            System.out.println("\n" + enc.toString());
        }
        System.out.print("administrativos");
        for(Administrativo adm :administrativos_leidos){
            System.out.println("\n" + adm.toString());
        }*/


        /**a esta altura el archivo ya esta generado ok
         * ya comprobe que puedo escribir un archivo de usuarios y al leerlo se discrimina entre adm y enc
         *
         * Lo que vamos a hacer a continuacion es ir probando cada metodo
         *
         * 1er metodo: Aquel que a partir de un hashmap y un archivo de usuarios carga dicho hashmap de usuarios (AccesoDatos)
         */
        //Instanciando el hashmap de usuarios, que formaria parte de el gimnasio(envoltorio)

        //LeerUsuariosDeArchivo("usuarios.bin",usuarios_GimnasioEnvoltorio);
        //Para probar que se cargaron, tengo que recorrer el mapa
       // System.out.print("Usuarios que formarian parte de la clase envoltorio gimnasio");
       // MostrarHashMap(usuarios_GimnasioEnvoltorio);

        //el metodo que muestra el hashmap del main funca bien
        /**CONCLUSION: los usuarios no son ingresados al hashmap
         * 1) el archivo de usuarios se genera correctamente
         * 2) ya probe que a partir de ese archivo, se puede discriminar entre quien es un administrativo y quien un encargado
         * 3) problema actual : no logro que , a partir de un archivo de usuarios, yo pueda cargar un hashmap
         * **/
        HashMap <String,Usuario> usuarios_GimnasioEnvoltorio = new HashMap<>();
        System.out.println(GenerarArchivoUsuarios(usuarios_harcodeados));

        //lleer archivo
        LeerUsuariosDeArchivo("usuarios.bin",usuarios_GimnasioEnvoltorio);
        MostrarHashMap(usuarios_GimnasioEnvoltorio);


        /**Generar archivo de Clientes **/

        /*
        ArrayList <Cliente> clientes_harcodeados = new ArrayList<>();
        Cliente cliente1 = new Cliente("Alberto A","111","111-111",Eestado.ACTIVO,EGrupoSanguineo.A_NEGATIVO,"111-111","A Salud",true,LocalDate.of(2001,01,01),"",false,false);
        Cliente cliente2 = new Cliente ("Benito B","222","222-222",Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"222-222","B Salud",true,LocalDate.of(2002,02,02),"",false,false);
        Cliente cliente3 = new Cliente("Carla C","333","333-333",Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"333-333","C Salud",true,LocalDate.of(2003,03,03),"",true,false);
        Cliente cliente4 = new Cliente ("David D","444","444-444", Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"444-444","D Salud",true,LocalDate.of(2004,04,04),"Es epileptico",false,false);
        Cliente cliente5 = new Cliente("Eugenia E","555","555-555",Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"555-555","E Salud",false,LocalDate.of(2005,05,05),"",false,false);

        clientes_harcodeados.add(cliente1);
        clientes_harcodeados.add(cliente2);
        clientes_harcodeados.add(cliente3);
        clientes_harcodeados.add(cliente4);
        clientes_harcodeados.add(cliente5);

        System.out.println("estamos aca");

        System.out.println(cliente1.toString());*/

       /* System.out.println(GenerarArchivoClientes("clientes.bin",clientes_harcodeados));

        //Prueba de que los Clientes estan bien leidos, ya que estamos los cargamos en un mapa
        HashMap <String,Cliente> clientes_leidos = new HashMap<>();
        DeArchivoToHashMapClientes("clientes.bin",clientes_leidos);
        System.out.print("Clientes traidos desde el archivo de clientes:");
        MostrarHashMap(clientes_leidos);*/










    }

    //Clientes
    public static String GenerarArchivoClientes(String nombre_archivo,ArrayList<Cliente> clientes){
        String rta = "Archivo de Clientes Generado Exitosamente";
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
            fos = new FileOutputStream(nombre_archivo);
            oos = new ObjectOutputStream(fos);

            for(Cliente cliente : clientes){
                oos.writeObject(cliente);
            }

        } catch (FileNotFoundException e) {
            rta = e.getMessage();
        }catch(IOException e){
            rta = e.getMessage();
        }finally{
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                rta = e.getMessage();
            }

        }
        return rta;

    }
    public static void DeArchivoToHashMapClientes (String nombre_archivo,HashMap<String,Cliente> clientes){
        String rta = "";
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        int lectura =1;

        try {
            fis = new FileInputStream(nombre_archivo);
            ois = new ObjectInputStream(fis);

            while(lectura==1){
                Cliente cliente = (Cliente)ois.readObject();
                clientes.put(cliente.getDni(),cliente);
            }
        } catch(EOFException e){
            rta += "Fin de Archivo Leido";
        } catch(FileNotFoundException e) {
            rta = e.toString();
        }catch(ClassNotFoundException e){
            rta = e.toString();
        }catch(IOException e){
            rta = e.toString();
        }finally{
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                rta = e.toString();
            }

        }
    }


    //Fin Clientes





    //Usuarios

    public static <K,V> void MostrarHashMap(HashMap<K,V> hm){
        Iterator it = hm.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<K,V> entradaDelMapa = (Map.Entry<K,V>)it.next();
            System.out.println(entradaDelMapa.getValue().toString() + "\n");
           // System.out.println("entre al mapa");
        }
       // System.out.println("pase por el metodo mostrarhashmap");
    }
    public static void LeerUsuariosDeArchivo (String archivo, HashMap<String,Usuario> usuarios){
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        int lectura =1;

        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            while(lectura==1){
                Usuario usu = (Usuario)ois.readObject();
                usuarios.put(usu.getUsuario(), usu);

            }
        } catch(EOFException e){
            System.out.println("hola");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally
        {
            try {
                fis.close();
                ois.close();

            } catch (IOException e) {
                e.getMessage();
            }
        }
    }


    public static String GenerarArchivoUsuarios(ArrayList<Usuario> usuarios){
        String rta = "Archivo de Usuarios Generado Exitosamente";
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try {
            fos = new FileOutputStream("usuarios.bin");
            oos = new ObjectOutputStream(fos);

            for(Usuario usu : usuarios){
                oos.writeObject(usu);
            }

        } catch (FileNotFoundException e) {
            rta = e.getMessage();
        }catch(IOException e){
            rta = e.getMessage();
        }finally{
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                rta = e.getMessage();
            }

        }
        return rta;
    }

    public static String LeerArchivo(String nombre_archivo, ArrayList<Administrativo> administrativos, ArrayList<Encargado>encargados){
        /**con este metodo vamos a probar si tras generar un archivo de USUARIOS, luego al leerlos se puede discriminar
         * que uno sea un administrativo y el otro encargado
         */
        String rta = "";
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        int lectura =1;

        try {
            fis = new FileInputStream(nombre_archivo);
            ois = new ObjectInputStream(fis);

            while(lectura==1){
                Usuario usu = (Usuario)ois.readObject();
                if(usu instanceof Encargado){
                    encargados.add((Encargado) usu);
                }
                else{
                    administrativos.add((Administrativo) usu);
                }
            }
        } catch(EOFException e){
            rta += "Fin de Archivo Leido";
        } catch(FileNotFoundException e) {
            rta = e.toString();
        }catch(ClassNotFoundException e){
            rta = e.toString();
        }catch(IOException e){
            rta = e.toString();
        }finally{
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                rta = e.toString();
            }

        }
        return rta;
    }
}