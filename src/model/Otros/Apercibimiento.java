package model.Otros;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**Esta clase crea objetos y realiza metodos correspondientes a los posibles apercibimientos a recibir por Clientes
 * o Instructores*/

public class Apercibimiento implements Serializable,I_toJson {
    private String descripcion;
    private LocalDate fecha;
/**Constructor vacio de la clase.*/
    public Apercibimiento()
    {
        descripcion=" ";
        fecha=null;
    }
/**Constructor con todos los atributos de un apercibimiento.*/
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

    /**Muestra apercbimiento
     *@return Apercibimiento en forma de String.*/
    @Override
    public String toString() {
        return "Apercibimiento{" +
                "descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    /**Compara apercibimientos
     * @deprecated No tiene sustento su uso practico para la finalidad del programa.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apercibimiento that = (Apercibimiento) o;
        return Objects.equals(descripcion, that.descripcion) && Objects.equals(fecha, that.fecha);
    }
    /**Compara apercibimientos
     * @deprecated No tiene sustento su uso practico para la finalidad del programa.*/
    @Override
    public int hashCode() {
        return Objects.hash(descripcion, fecha);
    }

    /**Permite crear un apercibimiento a partir de los datos de un JSONObject
     * @param jo El json object con la informacion del apercibimiento.
     * @return El apercibmiento cargado.*/
    @Override
    public Apercibimiento fromJson(JSONObject jo) throws JSONException {
        Apercibimiento apercibimiento = new Apercibimiento();

        apercibimiento.setFecha(LocalDate.parse(jo.getString("Fecha")));
        apercibimiento.setDescripcion(jo.getString("Descripcion"));

        return apercibimiento;
    }
    /**Permite guarda la informacion del apercibimiento en un objeto de tipo JSON.
     * @return El JSONObject con la informacion.*/
    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Descripcion", getDescripcion());
        jsonObject.put("Fecha", getFecha());

        return  jsonObject;
    }
}
