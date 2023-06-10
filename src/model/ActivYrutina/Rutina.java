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
        instructor=" ";
        mes = " ";
        descripcion=" ";

    }

    public String getInstructor() {
        return instructor;
    }

    public String getMes() {
        return mes;
    }

    public String getDescripcion() {
        return descripcion;
    }


    @Override
    public String toString() {
        return "Rutina{" +
                "instructor='" + instructor + '\'' +
                ", mes='" + mes + '\'' +
                ", descripcion='" + descripcion + '\''  +
                '}';
    }


    @Override
    public void fromJson(JSONObject jo) throws JSONException {

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


