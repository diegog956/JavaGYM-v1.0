
import AccesoDatos.ArchivoColeccionUtiles;
import AccesoDatos.ArchivoMapaUtiles;
import model.ActivYrutina.*;
import model.Otros.*;
import model.Personal.*;
import model.Enum.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {

        Gimnasio gimnasio = new Gimnasio();



        TreeSet<Actividad> setActividades = new TreeSet<>();
        ArrayList<EdiaSemana> listaDias = new ArrayList<>();

        listaDias.add(EdiaSemana.LUNES);
        listaDias.add(EdiaSemana.MARTES);
        listaDias.add(EdiaSemana.VIERNES);

        Actividad actividad1 = new Actividad(EtipoActividad.BOXEO, "10:00 - 11:00", listaDias, "Instructor 1", 20, 0, true, "", 50.0);
        setActividades.add(actividad1);
        listaDias.clear();
        listaDias.add(EdiaSemana.MIERCOLES);
        Actividad actividad2 = new Actividad(EtipoActividad.DANZA, "10:00 - 11:00", listaDias, "Instructor 2", 15, 0, true, "", 60.0);
        setActividades.add(actividad1);
        listaDias.clear();
        listaDias.add(EdiaSemana.VIERNES);

                Actividad actividad4 = new Actividad(EtipoActividad.LIBRE, "19:30 - 20:30", listaDias, "Instructor 4", 25, 0, true, "¡Diviértete y mantente en forma con nuestros ritmos latinos!", 55.0);

                setActividades.add(actividad1);
                setActividades.add(actividad2);

                setActividades.add(actividad4);


        Actividad actividad7 = new Actividad(EtipoActividad.LIBRE, "17:00 - 18:00", listaDias, "Instructor 7", 20, 0, true, "", 55.0);


        Actividad actividad9 = new Actividad(EtipoActividad.FUNCIONAL, "09:00 - 10:00", listaDias, "Instructor 9", 15, 0, true, "", 35.0);


        Actividad actividad11 = new Actividad(EtipoActividad.LIBRE, "18:30 - 19:30", listaDias, "Instructor 11", 20, 0, true, "", 60.0);


        Actividad actividad13 = new Actividad(EtipoActividad.FUNCIONAL, "12:00 - 13:00", listaDias, "Instructor 13", 15, 0, true, "", 40.0);

        Actividad actividad15 = new Actividad(EtipoActividad.LIBRE, "15:30 - 16:30", listaDias, "Instructor 15", 25, 0, true, "", 50.0);

        setActividades.add(actividad7);

        setActividades.add(actividad9);

        setActividades.add(actividad11);

        setActividades.add(actividad13);

        setActividades.add(actividad15);


        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        archivoColeccionUtiles.guardarColeccion(setActividades, "actividades.dat");

        TreeSet<Actividad> nuevoset = new TreeSet<>(archivoColeccionUtiles.leerColeccion("actividades.dat"));


        ArrayList<Actividad> listaAct = new ArrayList<>(setActividades);

        Instructor instructor1 = new Instructor("Juan Perez", "123456789", "555-1234", "Calle 123", Eestado.ACTIVO, EGrupoSanguineo.A_POSITIVO, "555-5678", "Obra Social A", true, LocalDate.of(1990, 5, 10), "Comentario 1", "12345678901",listaAct, "imagen1.jpg");

        // Ejemplo 2
        Instructor instructor2 = new Instructor("María López", "987654321", "555-5678", "Avenida XYZ", Eestado.INACTIVO, EGrupoSanguineo.O_NEGATIVO, "555-4321", "Obra Social B", false, LocalDate.of(1985, 8, 20), "Comentario 2", "09876543210", new ArrayList<Actividad>(), "imagen2.jpg");

        // Ejemplo 3
        Instructor instructor3 = new Instructor("Pedro González", "456789123", "555-9999", "Plaza ABC", Eestado.ACTIVO, EGrupoSanguineo.B_POSITIVO, "555-1111", "Obra Social C", true, LocalDate.of(1988, 12, 5), "Comentario 3", "45678912345", new ArrayList<Actividad>(), "imagen3.jpg");

        // Ejemplo 4
        Instructor instructor4 = new Instructor("Ana Rodríguez", "789123456", "555-4444", "Calle 456", Eestado.ACTIVO, EGrupoSanguineo.A_NEGATIVO, "555-2222", "Obra Social D", true, LocalDate.of(1993, 3, 15), "Comentario 4", "78912345678", new ArrayList<Actividad>(), "imagen4.jpg");


        HashMap<String, Instructor> mapaInstructores = new HashMap<>();
        mapaInstructores.put(instructor1.getDni(),instructor1);
        mapaInstructores.put(instructor2.getDni(),instructor2);
        mapaInstructores.put(instructor3.getDni(),instructor3);
        mapaInstructores.put(instructor4.getDni(),instructor4);

        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();
        archivoMapaUtiles.guardarMapa(mapaInstructores, "instructores.dat");

        HashMap<String, Instructor> mapanuevo = new HashMap<>(archivoMapaUtiles.leerMapa("instructores.dat"));
        System.out.println(mapanuevo);



    }
        }
