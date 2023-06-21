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
/**Clase que hereda de persona y de ella Instructor y Usuario*/
public class Personal extends Persona implements Serializable, I_toJson
{
    private String CUIL;
/**Constructor de la clase Personal*/
    public Personal(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL) {
        super(nombre,dni,genero,telefono,domicilio,email,estado,grupo_sanguineo,contacto_emergencia,obra_social,fecha_nacimiento,comentario);
        this.CUIL = CUIL;
    }
/**Constructor vacio de la clase Personal*/
    public Personal()
    {
        super();
    }
/**Constructor que permite crear Personal a partir de un JSONObject
 * @param jo JSONObject con la infomacion del personal.*/
    public Personal(JSONObject jo) throws JSONException {
        super(jo);
    }
    public String getCUIL() {
        return CUIL;
    }
    private void setCUIL(String CUIL) {
        this.CUIL = CUIL;
    }
    /**Permite modificar la informacion de un Personal*/
    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email,
    Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,
    LocalDate fecha_nacimiento,String comentario, String cuil){
        super.modificar(nombre, dni, genero, telefono, domicilio, email,
                estado, grupo_sanguineo, contacto_emergencia,obra_social,
                fecha_nacimiento,comentario);
        setCUIL(cuil);
    }
    /**
     *@return Informacion del personal en formato String.*/
    @Override
    public String toString() {
        return "Personal{" +
                "CUIL='" + CUIL + '\'' +
                "} " + super.toString();
    }

    /**Permite crear un objeto de tipo Personal a partir de un JSONObject
     * @param jo JSONObject con la informacion del personal.
     * @return Objecto de la clase Personal.
     * @throws JSONException si el tipo de dato no coincide con el tipo asociado al metodo 'get'*/
    @Override
    public Personal fromJson(JSONObject jo) throws JSONException {
       Personal personal = new Personal(jo);
       personal.setCUIL(jo.getString("CUIL"));
       return personal;
    }

    /**Permite crear un JSONObject con la informacion de un Personal
     * @return JSONOBject con informacion del personal*/
    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("CUIL", getCUIL());
        return jsonObject;
    }


}
