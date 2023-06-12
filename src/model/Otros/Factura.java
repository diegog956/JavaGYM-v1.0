package model.Otros;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Factura implements I_toJson, Serializable {
    private String mes;
    private String anio;
    private String dni_cliente;
    private String nombre_cliente;
    private LocalDate fecha_de_emision;
    private double monto;

    public Factura(String mes, String anio, String dni_cliente, String nomobre_cliente, LocalDate fecha_de_emision, double monto) {

        this.anio=anio;
        this.mes=mes;
        this.dni_cliente = dni_cliente;
        this.nombre_cliente = nomobre_cliente;
        this.fecha_de_emision = fecha_de_emision;
        this.monto = monto;
    }

    public Factura ()
    {
        anio="";
        mes="";
        nombre_cliente = "";
        dni_cliente = "";
        fecha_de_emision=null; ///DUDA ACA.
        monto=0;
    }

    public String getMes() {
        return mes;
    }

    public String getAnio() {
        return anio;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public LocalDate getFecha_de_emision() {
        return fecha_de_emision;
    }

    public double getMonto() {
        return monto;
    }


    @Override
    public String toString() {
        return "Factura{" +
                "mes='" + mes + '\'' +
                ", anio='" + anio + '\'' +
                ", dni_cliente='" + dni_cliente + '\'' +
                ", nombre_cliente='" + nombre_cliente + '\'' +
                ", fecha_de_emision=" + fecha_de_emision +
                ", monto=" + monto +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
       boolean rta = false;
        if(o!=null){
           if(o instanceof Factura){
               Factura aux = (Factura) o;
               if(aux.getMes().equals(getMes()) && aux.getAnio().equals(getAnio()) && aux.getDni_cliente().equals(getDni_cliente())){
                   rta = true;
               }
           }
       }
    return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public void fromJson(JSONObject jo) throws JSONException {

    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
       JSONObject jsonObject = new JSONObject();

       jsonObject.put("Mes", getMes());
       jsonObject.put("Año", getAnio());
       jsonObject.put("Nombre", getNombre_cliente());
       jsonObject.put("Dni", getDni_cliente());
       jsonObject.put("Fecha de emision", getFecha_de_emision());
       jsonObject.put("Monto", getMonto());

        return jsonObject;
    }
}

