package model.Otros;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Apercibimiento implements Serializable,I_toJson {
    private String descripcion;
    private LocalDate fecha;

    public Apercibimiento()
    {
        descripcion=" ";
        fecha=null;
    }

    public Apercibimiento(String descripcion, LocalDate fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Apercibimiento{" +
                "descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apercibimiento that = (Apercibimiento) o;
        return Objects.equals(descripcion, that.descripcion) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, fecha);
    }


    @Override
    public Apercibimiento fromJson(JSONObject jo) throws JSONException {
        Apercibimiento apercibimiento = new Apercibimiento();

        apercibimiento.setFecha(LocalDate.parse(jo.getString("Fecha")));
        apercibimiento.setDescripcion(jo.getString("Descripcion"));

        return apercibimiento;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Descripcion", getDescripcion());
        jsonObject.put("Fecha", getFecha());

        return  jsonObject;
    }
}
