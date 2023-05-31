package model.Persona;

import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Otros.Factura;
import model.Persona.Persona;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Cliente extends Persona {
    private boolean alta_medica;
    private boolean solicito_rutina;
    private boolean debe;
    private HashSet<Rutina> hashSetRutinas;
    private ArrayList<Factura>listaFacturas;
    private TreeMap<Integer,Actividad>mapaDeActividades; ///ID concatenado para q se ordenen por actividades

    public Cliente()
    {
        super();
        alta_medica=true;
        solicito_rutina=true;
        debe=true;
        hashSetRutinas=new HashSet<>();
        listaFacturas=new ArrayList<>();
        mapaDeActividades=new TreeMap<>();
    }

    public Cliente(String nombre, String dni, String telefono, ArrayList<Apercibimiento> listaApercibimientos, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, boolean alta_medica1, boolean solicito_rutina, boolean debe) {
        super(nombre, dni, telefono, listaApercibimientos, estado, grupo_sanguineo, contacto_emergencia, obra_social, alta_medica, fecha_nacimiento);
        this.alta_medica = alta_medica1;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        hashSetRutinas=new HashSet<>();
        listaFacturas=new ArrayList<>();
        mapaDeActividades=new TreeMap<>();
    }
}
