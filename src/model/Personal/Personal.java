package model.Personal;

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

    public Personal(String nombre, String dni, String telefono, String domicilio,Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL) {
        super(nombre,dni,telefono,domicilio,estado,grupo_sanguineo,contacto_emergencia,obra_social,fecha_nacimiento,comentario);
        this.CUIL = CUIL;
    }

    public Personal()
    {
        super();


    }

    public String getCUIL() {
        return CUIL;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "CUIL='" + CUIL + '\'' +
                "} " + super.toString();
    }

    @Override
    public void fromJson(JSONObject jo) throws JSONException {

    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("CUIL", getCUIL());
        return jsonObject;
    }
}
