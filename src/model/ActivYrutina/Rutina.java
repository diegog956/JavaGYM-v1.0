package model.ActivYrutina;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
/**Clase que hace referencia a la rutina de un cliente*/
public class Rutina implements Serializable, I_toJson {
    private String instructor;
    private String mes;
    private String descripcion;
/**Constructor con la totalidad de atributos de la clase Rutina.*/
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

    public String getInstructor() {
        return instructor;
    }
    public String getMes() {
        return mes;
    }
    public String getDescripcion() {
        return descripcion;
    }

    private void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    private void setMes(String mes) {
        this.mes = mes;
    }
    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**Metodo que devuelve la informacion de la rutina.
     * @return Informacion de la rutina en formato String.*/
    @Override
    public String toString() {
        return "======================\nRutina " + getMes() +  "\nAutor: "  + getInstructor()  + "\n======================\n"
                + "\n" + getDescripcion() + "\n";
    }

    /**Metodo que permite crear una rutina a partir de un JSONObject
     * @param jo JSONObject con la informacion de la rutina.
     * @return la rutina creada con la informacion del JSONObject.
     * @throws JSONException Arroja excepcion si el tipo de retorno no coincide con el tipo de dato asociado al metodo 'get'.   */
    @Override
    public Rutina fromJson(JSONObject jo) throws JSONException {
        Rutina rutina = new Rutina();

        rutina.setMes(jo.getString("Mes"));
        rutina.setInstructor(jo.getString("Instructor"));
        rutina.setDescripcion(jo.getString("Descripcion"));

        return rutina;
    }

    /**Metodo que permite guardar la informacion de la rutina en un objeto de tipo JSON.
     * @return JSONObject con informaciond de rutina.
     * @throws JSONException si se coloca una clave nula en el metodo 'put'*/
    @Override
    public JSONObject toJsonObj() throws JSONException {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Mes", getMes());
            jsonObject.put("Instructor", getInstructor());
            jsonObject.put("Descripcion", getDescripcion());

            return jsonObject;
    }
}


