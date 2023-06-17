package model.Personal;


import com.sun.source.tree.Tree;
import model.ActivYrutina.Actividad;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Instructor extends Personal implements Serializable, I_toJson {
private ArrayList<Actividad> actividades;
private String imagenPerfil;

    public Instructor(String nombre, String dni, String telefono,String domicilio,Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL, ArrayList<Actividad> actividades, String imagenPerfil) {
        super(nombre, dni, telefono,domicilio,estado, grupo_sanguineo, contacto_emergencia, obra_social, alta_medica, fecha_nacimiento, comentario, CUIL);
        this.actividades = actividades;
        this.imagenPerfil = imagenPerfil;
    }

    public Instructor()
    {
        super();
        actividades=new ArrayList<>();
        imagenPerfil=" ";
    }

    @Override
    public String toString() {
        return super.toString() + "Instructor{" +
                "actividades=" + actividades.toString() +
                "\n";
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(actividades, that.actividades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actividades);
    }


    @Override
    public Instructor fromJson(JSONObject jo) throws JSONException {
       Instructor instructor = (Instructor) super.fromJson(jo);
       instructor.setImagenPerfil(jo.getString("Imagen de Perfil"));
       JSONArray jsonArray = jo.getJSONArray("Actividades");
       for(int i=0; i<jsonArray.length(); i++){
           Actividad actividad = new Actividad();
           instructor.actividades.add(actividad.fromJson(jsonArray.getJSONObject(i)));
        }
       return instructor;
    }

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
}
