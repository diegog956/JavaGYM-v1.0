
import AccesoDatos.ArchivoColeccionUtiles;
import AccesoDatos.ArchivoMapaUtiles;
import AccesoDatos.JsonUtiles;
import excepciones.CredencialesInvalidasException;
import excepciones.UsuarioExistenteException;
import model.ActivYrutina.*;
import model.Otros.*;
import model.Persona.Cliente;
import model.Personal.*;
import model.Enum.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {

        Gimnasio gimnasio = new Gimnasio();

        /**------------------------------------ usuarios (Sergio Martes 13-06) --------------------------------------------*/

        Administrativo administrativo1 = new Administrativo("Mauricio Macri","111111","111111","Calle 1 111",EGrupoSanguineo.A_POSITIVO,"","",LocalDate.of(2201,01,01),"","111111","mauriciomacri","mauriciomacri");
        Administrativo administrativo2 = new Administrativo("Alberto Fernandez","222222","222222","Calle 2 222",EGrupoSanguineo.A_POSITIVO,"","",LocalDate.of(2002,02,02),"","565156","albertofernandez","albertofernandez");

        try {
            gimnasio.agregar(administrativo1);
            gimnasio.agregar(administrativo2);
        } catch (UsuarioExistenteException e) {
            //System.out.println(e.getMessage());
        }


        /**------------------------------------ ACTIVIDADES --------------------------------------------*/
        ArrayList<EdiaSemana> listaDias = new ArrayList<>();

        listaDias.add(EdiaSemana.LUNES);
        listaDias.add(EdiaSemana.MARTES);
        listaDias.add(EdiaSemana.VIERNES);

        Actividad actividad1 = new Actividad(EtipoActividad.BOXEO, "10:00 - 11:00", listaDias, "Instructor 1", 20, 0, true, "", 50.0);
        gimnasio.agregar(actividad1);
        listaDias.clear();
        listaDias.add(EdiaSemana.MIERCOLES);
        Actividad actividad2 = new Actividad(EtipoActividad.DANZA, "10:00 - 11:00", listaDias, "Instructor 2", 15, 0, true, "", 60.0);
        gimnasio.agregar(actividad1);
        listaDias.clear();
        listaDias.add(EdiaSemana.VIERNES);

        Actividad actividad4 = new Actividad(EtipoActividad.LIBRE, "19:30 - 20:30", listaDias, "Instructor 4", 25, 0, true, "¡Diviértete y mantente en forma con nuestros ritmos latinos!", 55.0);

        gimnasio.agregar(actividad1);
        gimnasio.agregar(actividad2);

        gimnasio.agregar(actividad4);


        Actividad actividad7 = new Actividad(EtipoActividad.LIBRE, "17:00 - 18:00", listaDias, "Instructor 7", 20, 0, true, "", 55.0);


        Actividad actividad9 = new Actividad(EtipoActividad.FUNCIONAL, "09:00 - 10:00", listaDias, "Instructor 9", 15, 0, true, "", 35.0);


        Actividad actividad11 = new Actividad(EtipoActividad.LIBRE, "18:30 - 19:30", listaDias, "Instructor 11", 20, 0, true, "", 60.0);


        Actividad actividad13 = new Actividad(EtipoActividad.FUNCIONAL, "12:00 - 13:00", listaDias, "Instructor 13", 15, 0, true, "", 40.0);

        Actividad actividad15 = new Actividad(EtipoActividad.LIBRE, "15:30 - 16:30", listaDias, "Instructor 15", 25, 0, true, "", 50.0);

        gimnasio.agregar(actividad7);

        gimnasio.agregar(actividad9);

        gimnasio.agregar(actividad11);

        gimnasio.agregar(actividad13);

        gimnasio.agregar(actividad15);

        /**------------------------------------ INSTRUCTORES --------------------------------------------*/


        Instructor instructor1 = new Instructor("Juan Perez", "123456789", "555-1234", "Calle 123", Eestado.ACTIVO, EGrupoSanguineo.A_POSITIVO, "555-5678", "Obra Social A", LocalDate.of(1990, 5, 10), "Comentario 1", "1234-5678901", new ArrayList<Actividad>(), "imagen1.jpg");

        // Ejemplo 2
        Instructor instructor2 = new Instructor("María López", "987654321", "555-5678", "Avenida XYZ", Eestado.INACTIVO, EGrupoSanguineo.O_NEGATIVO, "555-4321", "Obra Social B", LocalDate.of(1985, 8, 20), "Comentario 2", "09876543210", new ArrayList<Actividad>(), "imagen2.jpg");

        // Ejemplo 3
        Instructor instructor3 = new Instructor("Pedro González", "456789123", "555-9999", "Plaza ABC", Eestado.ACTIVO, EGrupoSanguineo.B_POSITIVO, "555-1111", "Obra Social C", LocalDate.of(1988, 12, 5), "Comentario 3", "45678912345", new ArrayList<Actividad>(), "imagen3.jpg");

        // Ejemplo 4
        Instructor instructor4 = new Instructor("Ana Rodríguez", "789123456", "555-4444", "Calle 456", Eestado.ACTIVO, EGrupoSanguineo.A_NEGATIVO, "555-2222", "Obra Social D", LocalDate.of(1993, 3, 15), "Comentario 4", "78912345678", new ArrayList<Actividad>(), "imagen4.jpg");


        gimnasio.agregar(instructor1);
        gimnasio.agregar(instructor2);
        gimnasio.agregar(instructor3);
        gimnasio.agregar(instructor4);

        /**------------------------------------ FACTURAS --------------------------------------------*/

        Factura factura1 = new Factura("Enero", "2023", "123456789", "Juan Pérez", LocalDate.now(), 1000.0);
        Factura factura2 = new Factura("Febrero", "2023", "987654321", "María González", LocalDate.now(), 1500.0);
        Factura factura3 = new Factura("Marzo", "2023", "456789123", "Pedro López", LocalDate.now(), 2000.0);
        Factura factura4 = new Factura("Abril", "2023", "789123456", "Ana Rodríguez", LocalDate.now(), 1200.0);

        gimnasio.agregar(factura1);
        gimnasio.agregar(factura2);
        gimnasio.agregar(factura3);
        gimnasio.agregar(factura4);
        /**------------------------------------ CLIENTES --------------------------------------------*/

        Cliente cliente1 = new Cliente("Juan Pérez", "123456789", "1234567890", "Calle 123", LocalDate.of(2023, 3, 5), Eestado.ACTIVO, EGrupoSanguineo.A_POSITIVO, "Contacto 1", "Obra Social 1", true, LocalDate.of(1990, 5, 15), "Comentario 1", true, false);
        Cliente cliente2 = new Cliente("María González", "987654321", "0987654321", "Avenida 456", LocalDate.of(2023, 10, 9), Eestado.INACTIVO, EGrupoSanguineo.B_NEGATIVO, "Contacto 2", "Obra Social 2", false, LocalDate.of(1985, 10, 20), "Comentario 2", false, true);
        Cliente cliente3 = new Cliente("Pedro López", "456789123", "1112223333", "Plaza 789", LocalDate.of(2023, 4, 3), Eestado.ACTIVO, EGrupoSanguineo.O_POSITIVO, "Contacto 3", "Obra Social 3", true, LocalDate.of(1995, 3, 8), "Comentario 3", true, false);
        Cliente cliente4 = new Cliente("Ana Rodríguez", "789123456", "4445556666", "Callejón 987", LocalDate.of(2023, 12, 22), Eestado.ACTIVO, EGrupoSanguineo.A_NEGATIVO, "Contacto 4", "Obra Social 4", true, LocalDate.of(2000, 12, 25), "Comentario 4", false, false);

        gimnasio.agregar(cliente1);
        gimnasio.agregar(cliente2);
        gimnasio.agregar(cliente3);
        gimnasio.agregar(cliente4);

        /**------------------------------------ A ARCHIVO --------------------------------------------*/

        gimnasio.guardarEnArchivo();


        /**------------------------------------ DESDE ARCHIVO --------------------------------------------*/

        /*Gimnasio nuevo_gim = new Gimnasio();
        nuevo_gim.listarTodo();*/

        /**Hacia y desde JSON ----------------------------------------------------------------------------*/
        JSONObject jsonObject = new JSONObject();
        try {
                jsonObject = gimnasio.actualizarJson();
            }catch (JSONException e){
                e.printStackTrace();
            }
        JsonUtiles.grabar(jsonObject, "json");
        HashMap<String, Cliente> mapaCliente = new HashMap<>();
        HashMap<String, Instructor> mapaInstructor= new HashMap<>();
        LinkedHashSet<Factura> listaFacturas= new LinkedHashSet<>();
        TreeSet<Actividad> arbolActividades= new TreeSet<>();
        HashMap<String, Usuario> mapaUsuarios = new HashMap<>();

        try{
            gimnasio.desgrabarJson(jsonObject, mapaCliente, mapaInstructor, listaFacturas, arbolActividades, mapaUsuarios);
        }catch (JSONException e){
            e.printStackTrace();
        }
        System.out.println("CLIENTES \n\n" + mapaCliente);
        System.out.println("INSTRUCTORES \n\n" + mapaInstructor);
        System.out.println("FACTURAS \n\n" + listaFacturas);
        System.out.println("ACTIVIDADES \n\n" + arbolActividades);
        System.out.println("USUARIOS \n\n" + mapaUsuarios);


        //Sergio 17-06 (perdon por spamear tanto el main) lo que sigue son pruebas de json

/*
        Gimnasio javagym = new Gimnasio();
        try {
            System.out.println(javagym.IngresarAlSistema("encargado2023","encargado2023"));
        } catch (CredencialesInvalidasException e) {
            System.out.println(e.getMessage());
        }
        javagym.listarTodo();




        //Generando Lista Java apd JSON:
        ArrayList <Cliente> clientes = new ArrayList<>();


        try {
            JSONArray jsonArray_clientes = new JSONArray(javagym.CompartirDatosClientes());
            for(int i=0; i<jsonArray_clientes.length();i++){
                Cliente cliente = new Cliente();
                cliente = cliente.fromJson(jsonArray_clientes.getJSONObject(i));
                clientes.add(cliente);
            }





           *//* for(int i=0; i<jsonArray_clientes.length();i++){
                JSONObject jsonObject_cliente = jsonArray_clientes.getJSONObject(i);
                Cliente cliente = new Cliente();
                cliente = cliente.fromJson(jsonObject_cliente);
                clientes.add(cliente);
            }*//*
            System.out.println("Clientes tomados del json: \n " + clientes );
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        //Json clientes
        System.out.println("JSON CLIENTES : \n");
        System.out.println(javagym.CompartirDatosClientes());

        //Json actividades
        System.out.println("JSON ACTIVIDADES : \n");
        System.out.println(javagym.CompartirDatosActividades());


        //PROBANDO DE JSON ACTIVIDADES A LISTA DE ACTIVIDADES
        ArrayList<Actividad> actividades = new ArrayList<>();
        System.out.println("Actividades tomadas desde el json:\n");

        try{
            JSONArray jsonArray_actividades = new JSONArray(javagym.CompartirDatosActividades());
            for(int i=0; i<jsonArray_actividades.length();i++){
                Actividad actividad = new Actividad();
                actividad = actividad.fromJson(jsonArray_actividades.getJSONObject(i));
                actividades.add(actividad);
            }
            System.out.println(actividades);
        }catch(JSONException e){
            System.out.println(e.getMessage());
        }*/






    }
}
