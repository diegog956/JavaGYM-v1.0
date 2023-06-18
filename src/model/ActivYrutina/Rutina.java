package model.ActivYrutina;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Rutina implements Serializable, I_toJson {
    private String instructor;
    private String mes;
    private String descripcion;

    public Rutina(String instructor, String mes, String descripcion) {
        this.instructor = instructor;
        this.mes = mes;
        this.descripcion = descripcion;

    }

    public Rutina()
    {
        instructor=" - ";
        mes = " - ";
        descripcion=" - ";

    }

    /**Bloque get -----------------------------------------------------------------------------------------*/
    public String getInstructor() {
        return instructor;
    }
    public String getMes() {
        return mes;
    }
    public String getDescripcion() {
        return descripcion;
    }

    /**Bloque set -----------------------------------------------------------------------------------------*/
    private void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    private void setMes(String mes) {
        this.mes = mes;
    }
    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**----------------------------------------------------------------------------------------------------*/
    @Override
    public String toString() {
        return "Rutina{" +
                "instructor='" + instructor + '\'' +
                ", mes='" + mes + '\'' +
                ", descripcion='" + descripcion + '\''  +
                '}';
    }
    @Override
    public Rutina fromJson(JSONObject jo) throws JSONException {
        Rutina rutina = new Rutina();

        rutina.setMes(jo.getString("Mes"));
        rutina.setInstructor(jo.getString("Instructor"));
        rutina.setDescripcion(jo.getString("Descripcion"));

        return rutina;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Mes", getMes());
            jsonObject.put("Instructor", getInstructor());
            jsonObject.put("Descripcion", getDescripcion());

            return jsonObject;
    }
}


