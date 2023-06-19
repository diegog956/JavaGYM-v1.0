package model.ActivYrutina;

import model.Enum.EGrupoSanguineo;
import model.Enum.EdiaSemana;
import model.Enum.EtipoActividad;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Actividad implements Serializable,Comparable, I_toJson {
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

    /**Bloque de get -------------------------------------------------------------------------------------*/
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
    /**Permite informar si una actividad esta disponible, en una cadena de texto Si/No.
    @return si la actividad se encuentra disponible, en formato de una cadena legible para el usuario.*/
    public String EstaDisponible(){
        String rta = "No";
        if(disponible){
            rta = "Si";}
        return rta;}

    /**Bloque de set -------------------------------------------------------------------------------------*/
    private void setNombre(EtipoActividad nombre) {
        this.nombre = nombre;
    }

    private void setHorario(String horario) {
        this.horario = horario;
    }

    private void setNombre_instructor(String nombre_instructor) {
        this.nombre_instructor = nombre_instructor;
    }

    private void setCupo(int cupo) {
        this.cupo = cupo;
    }

    private void setInscriptos(int inscriptos) {
        this.inscriptos = inscriptos;
    }

    private void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    private void setComentario(String comentario) {
        this.comentario = comentario;
    }

    private void setPrecio_mensual(double precio_mensual) {
        this.precio_mensual = precio_mensual;
    }

    private void setListaDias(ArrayList<EdiaSemana> listaDias) {
        this.listaDias = listaDias;
    }
    /**---------------------------------------------------------------------------------------------------*/

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

    @Override
    public Actividad fromJson(JSONObject jo) throws JSONException {
       Actividad actividad = new Actividad();

       //actividad.setNombre((EtipoActividad) jo.get("Nombre"));// modificado en la linea de abajo
       actividad.setNombre(EtipoActividad.valueOf(jo.getString("Nombre")));
       actividad.setNombre_instructor(jo.getString("Nombre Instructor"));
       actividad.setCupo(jo.getInt("Cupo"));
       actividad.setInscriptos(jo.getInt("Inscriptos"));
       actividad.setDisponible(jo.getBoolean("Disponible"));
       actividad.setComentario(jo.getString("Comentario"));
       actividad.setPrecio_mensual(jo.getDouble("Precio Mensual"));
       actividad.setHorario(jo.getString("Horario"));

       JSONArray jsonArray = jo.getJSONArray("Dias");

       /*for(int i=0; i<jsonArray.length(); i++){
           actividad.listaDias.add((EdiaSemana)(jsonArray.get(i)));
       }*/
        for(int i=0; i<jsonArray.length(); i++){
            EdiaSemana enum_dia_semana = EdiaSemana.valueOf(jsonArray.getString(i));
            actividad.listaDias.add(enum_dia_semana);
        }

        return actividad;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Nombre",getNombre().toString());
            jsonObject.put("Nombre Instructor",getNombre_instructor());
            jsonObject.put("Cupo",getCupo());
            jsonObject.put("Inscriptos",getInscriptos());
            jsonObject.put("Disponible",isDisponible());
            jsonObject.put("Comentario",getComentario());
            jsonObject.put("Precio Mensual",getPrecio_mensual());
            jsonObject.put("Horario", getHorario());

            JSONArray jsonArray = new JSONArray();
            for(int i=0;i<listaDias.size();i++){
                jsonArray.put(i, getListaDias().get(i).toString());
            }
            jsonObject.put("Dias", jsonArray);
            return jsonObject;
    }

    public void modificar(EtipoActividad nombre, String horario, ArrayList<EdiaSemana> listaDias1, String nombre_instructor,
                          int cupo, int inscriptos, boolean disponible, String comentario, double precio_mensual){
        setNombre(nombre);
        setHorario(horario);
        setListaDias(listaDias1);
        setNombre_instructor(nombre_instructor);
        setCupo(cupo);
        setInscriptos(inscriptos);
        setDisponible(disponible);
        setComentario(comentario);
        setPrecio_mensual(precio_mensual);

    }
}


