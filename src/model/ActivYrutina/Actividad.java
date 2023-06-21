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
/**Clase que hace referencia las distintas actividades que se pueden realizar en el gimnasio.*/
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
/**Constructor de la clase con todos sus atributos enviados por parametro*/
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
/**Constructor vacio*/
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

    /**Permite informar si una actividad esta disponible, en una cadena de texto Si/No.
    @return si la actividad se encuentra disponible, en formato de una cadena legible para el usuario.*/
    public String EstaDisponible(){
        String rta = "No";
        if(disponible){
            rta = "Si";}
        return rta;}

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


    /**Compara Actvidades por nombre, luego por horario, luego por si coincide en al menos un dia.*/
    /** Pensado en base a que el GYM tiene un solo espacio para cada actividad.
     * @param o Cualquier objecto que herede de Object.
     * @return true si encontro superposicion de actividades del mismo nombre en al menos un dia y horario.*/
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

    /**Metodo auxiliar que recorre y compara la lista de dias entrante con la lista de dias, esto aplicable
     * en el caso que coincidan actividad y horario.
     * @see Actividad#equals(Object) para observar su aplicacion.
     * @return true si encuenta coincidencia entre la lista de dias de una actividad con la que se desea agregar.*/

    private boolean compararDias(ArrayList<EdiaSemana> lista){
        boolean rta = false;
        for(int i=0;i<lista.size();i++){
            if(getListaDias().contains(lista.get(i))){
                rta = true;
            }
        }
        return rta;
    }

    /**Metodo que muestra la informacion de la actividad.
     * @return informacion en formato String.
     * */
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

    /**Metodo que permite ordenar el set de actividades con el criterio del enum EtipodeActividad. En caso de ser
     * el mismo tipo, por horario.
     * @see Comparable para mas detalle.
     * @return resta que indica si el resultado de la comparacion entre actividad en sistema y la actividad a agregar.*/
    @Override
    public int compareTo(Object o) {
        int resta = getNombre().ordinal() - ((Actividad)o).getNombre().ordinal();
        if(resta==0){
            resta = getHorario().compareTo((((Actividad)o).getHorario()));
        }
        return resta;
    }

    /**Metodo que permite crear una actividad a partir de un JSONObject
     * @param jo JSONObject que contiene la informacion de la actividad.
     * @return Un objeto de la clase actividad con los respectivos datos en el.
     * @throws JSONException si el tipo de dato obtenido no coincide con el solicitado por el metodo get.*/
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

    /**Metodo que permite crear un JSONObject a partir de los datos de una actividad.
     * @return Objecto del tipo JSON con los datos de la actividad.*/
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

    /**Metodo modificar que establece los nuevos datos de una actividad.*/
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
    /**Suma inscripto a una clase*/
    public void sumarInscripto(){
        inscriptos++;
    }
    /**Resta inscripto a una clase*/
    public void restarInscripto(){
        inscriptos--;
    }
}


