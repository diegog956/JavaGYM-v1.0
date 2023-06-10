package model.ActivYrutina;

import model.Enum.EdiaSemana;
import model.Enum.EtipoActividad;

import java.util.ArrayList;

public class Actividad implements Comparable {
    private EtipoActividad nombre;
    private ArrayList<EdiaSemana>listaDias;
    private String horario;
    private String nombre_instructor;
    private int cupo;
    private int inscriptos;
    private boolean disponible;
    private String comentario;
    private double precio_mensual;

    public Actividad(EtipoActividad nombre, String horario, ArrayList<EdiaSemana> listaDias1, String nombre_instructor, int cupo, int inscriptos, boolean disponible, String comentario, double precio_mensual) {
        this.nombre = nombre;
        listaDias = new ArrayList<>(listaDias1);
        this.nombre_instructor = nombre_instructor;
        this.cupo = cupo;
        this.inscriptos = inscriptos;
        this.disponible = disponible;
        this.comentario = comentario;
        this.precio_mensual = precio_mensual;
        this.horario=horario;
    }

    public Actividad ()
    {
        listaDias=new ArrayList<EdiaSemana>();
        nombre_instructor=" ";
        cupo=0;
        inscriptos=0;
        disponible=true;
        comentario=" ";
        precio_mensual=0;
        horario=" ";
    }

    public String getHorario() {
        return horario;
    }



    public EtipoActividad getNombre() {
        return nombre;
    }

    public ArrayList<EdiaSemana> getListaDias() {
        return listaDias;
    }

    public String getNombre_instructor() {
        return nombre_instructor;
    }

    public int getCupo() {
        return cupo;
    }

    public int getInscriptos() {
        return inscriptos;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getComentario() {
        return comentario;
    }

    public double getPrecio_mensual() {
        return precio_mensual;
    }

    /**Compara por nombre, luego por horario, luego por si coincide en al menos un dia.*/
    /** Esto esta pensado en base a que el gym tiene un solo espacio para cada actividad.*/
    @Override
    public boolean equals(Object o) {
    boolean rta = false;
        if(o != null){
            if(o instanceof Actividad){
                Actividad aux = (Actividad) o;
                if(aux.getNombre().equals(getNombre())){
                    if(aux.getHorario().equals(getHorario())){
                        rta = compararDias(aux.getListaDias());
                    }
                }
            }
        }
    return rta;
    }

    /**Compara la lista de dias entrante con la lista de dias, esto aplicable en el caso que coincidan actividad y horario.*/
    private boolean compararDias(ArrayList<EdiaSemana> lista){
        boolean rta = false;
        for(int i=0;i<lista.size();i++){
            if(getListaDias().contains(lista.get(i))){
                rta = true;
            }
        }
        return rta;
    }
    @Override
    public String toString() {
        return "\nActividad{" +
                ", nombre='" + nombre + '\'' +
                ", listaDias=" + listaDias +
                ", horario='" + horario + '\'' +
                ", nombre_instructor='" + nombre_instructor + '\'' +
                ", cupo=" + cupo +
                ", inscriptos=" + inscriptos +
                ", disponible=" + disponible +
                ", comentario='" + comentario + '\'' +
                ", precio_mensual=" + precio_mensual +
                '}';
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public int compareTo(Object o) {
        int resta = getNombre().ordinal() - ((Actividad)o).getNombre().ordinal();
        if(resta==0){
            resta = getHorario().compareTo((((Actividad)o).getHorario()));
        }
        return resta;
    }
}


