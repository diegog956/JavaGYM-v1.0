package model.Personal;


import com.sun.source.tree.Tree;
import model.ActivYrutina.Actividad;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Persona.Persona;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
/**Clase que hereda de Persona y hace referencia al trabajador que dirige una actividad.*/
public class Instructor extends Personal implements Serializable, I_toJson {
private ArrayList<Actividad> actividades;
private String imagenPerfil;
/**Constructor de la clase Instructor con todos sus atributos e inicializacion de sus actividades.*/
    public Instructor(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL,String imagenPerfil) {
        super(nombre, dni, genero, telefono,domicilio,email,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario, CUIL);
        actividades = new ArrayList<>();
        this.imagenPerfil = imagenPerfil;
    }
/**Constructor vacio de la clase*/
    public Instructor()
    {
        super();
        actividades=new ArrayList<>();
        imagenPerfil=" ";
    }
/**Constructor utilizado para la creacion de un Instructor a partir de un JSONObject
 * @param jo JSONObject con la informacion del Instructor.
 * @see Persona(JSONObject) para mas detalle
 * */
    public Instructor (JSONObject jo) throws JSONException {
        super(jo);
    }

    /**Muestra actividades a cargo del instructor
     * @return Actividades a cargo en formato String.*/
    public String getActividadesACargo(){
        return "===================\nACTIVIDADES A CARGO\n===================\n" + actividades.toString();
    }

    /**Muestra el instructor.
     * @return Informacion del instructor en formato String.*/
    @Override
    public String toString() {
        if(actividades == null){
            actividades = new ArrayList<>();
        }
        return super.toString() + "Instructor{" +
                "actividades=" + actividades.toString() +
                "\n";
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    private void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

     private void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**Compara instructores
     * @param o Cualquier objeto que herede de object
     * @return true si es igual. false si no lo es.*/
    @Override
    public boolean equals(Object o) {
        boolean rta = false;
        if (o != null) {
            if (o instanceof Instructor){
                if(getDni().equals(((Instructor) o).getDni())){
                    rta = true;
                }
            }
        }
        return rta;
    }
    @Override
    public int hashCode() {
        return Objects.hash(actividades);
    }

    /**Metodo que permite crear un Instructor a partir de un JSONObject
     * @param jo JSONObject con la informacion del instructor.
     * @return Instructor con datos cargados.
     * @throws JSONException si el tipo de dato obtenido no coincide con el solicitado por el metodo get.*/
    @Override
    public Instructor fromJson(JSONObject jo) throws JSONException {
        Instructor instructor = new Instructor(jo);
        JSONArray jsonArray = jo.getJSONArray("Actividades");
            for (int i = 0; i < jsonArray.length(); i++) {
                Actividad actividad = new Actividad();
                instructor.actividades.add(actividad.fromJson(jsonArray.getJSONObject(i)));
            }
        instructor.setImagenPerfil(jo.getString("Imagen de Perfil"));
       return instructor;
    }

    /**Permite la creacion de un JSONObject con la informacion del instructor.
     * @return JSONObject del instructor.
     * @throws JSONException si se coloca una clave nula en al invocar al metodo put.*/
    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("Imagen de Perfil", getImagenPerfil());
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<actividades.size();i++){
            jsonArray.put(actividades.get(i).toJsonObj());
        }
        jsonObject.put("Actividades", jsonArray);
        return jsonObject;
    }

    /**Modifica los atributos del instructor.*/
    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento,String comentario, String cuil, String imagen_de_perfil, ArrayList<Actividad> listaActividades){
    super.modificar(nombre, dni, genero, telefono, domicilio, email,
            estado, grupo_sanguineo, contacto_emergencia,obra_social,
            fecha_nacimiento,comentario, cuil);
    setImagenPerfil(imagen_de_perfil);
    setActividades(listaActividades);
    }

}
