
import model.ActivYrutina.Actividad;
import model.Enum.EdiaSemana;
import model.Enum.EtipoActividad;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

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

        System.out.println(setActividades);
    }
        }
