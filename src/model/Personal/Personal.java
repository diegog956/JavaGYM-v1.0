package model.Personal;

import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Persona.Persona;
import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Personal extends Persona implements Serializable, I_toJson
{
    private String CUIL;

    public Personal(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL) {
        super(nombre,dni,genero,telefono,domicilio,email,estado,grupo_sanguineo,contacto_emergencia,obra_social,fecha_nacimiento,comentario);
        this.CUIL = CUIL;
    }

    public Personal()
    {
        super();
    }

    public Personal(JSONObject jo) throws JSONException {
        super(jo);
    }

    public String getCUIL() {
        return CUIL;
    }

    private void setCUIL(String CUIL) {
        this.CUIL = CUIL;
    }


    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email,
    Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,
    LocalDate fecha_nacimiento,String comentario, String cuil){
        super.modificar(nombre, dni, genero, telefono, domicilio, email,
                estado, grupo_sanguineo, contacto_emergencia,obra_social,
                fecha_nacimiento,comentario);
        setCUIL(cuil);
    }
    @Override
    public String toString() {
        return "Personal{" +
                "CUIL='" + CUIL + '\'' +
                "} " + super.toString();
    }

    @Override
    public Personal fromJson(JSONObject jo) throws JSONException {
       Personal personal = new Personal(jo);
       personal.setCUIL(jo.getString("CUIL"));
       return personal;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("CUIL", getCUIL());
        return jsonObject;
    }


}
