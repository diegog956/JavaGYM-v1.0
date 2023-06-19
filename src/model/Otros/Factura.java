package model.Otros;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
/**Bloque get ------------------------------------------------------------------------------------------*/
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

    /**Bloque set privados ---------------------------------------------------------------------------------*/

    private void setMes(String mes) {
        this.mes = mes;
    }

    private void setAnio(String anio) {
        this.anio = anio;
    }

    private void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    private void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    private void setFecha_de_emision(LocalDate fecha_de_emision) {
        this.fecha_de_emision = fecha_de_emision;
    }

    private void setMonto(double monto) {
        this.monto = monto;
    }
    /**----------------------------------------------------------------------------------------------------*/


    @Override
    public String toString() {
        return "\n======================\nFactura " + getMes() + " " + getAnio() + "\n======================\n" +
                "Cliente: " + getNombre_cliente() +
                "\nDNI: " + getDni_cliente() +
                "\nFecha emision: "+ getFecha_de_emision() +
                "\n-------------------\nMonto: " + getMonto() + "\n-------------------\n";
    }

    /**
     * NO SE PUEDE COBRARLE DOS VECES EL MISMO MES A UN CLIENTE. EL EQUALS SE ENCARGA DE COMPRONARLO.
     */
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
    public Factura fromJson(JSONObject jo) throws JSONException {
        Factura factura = new Factura();

        factura.setMes(jo.getString("Mes"));
        factura.setAnio(jo.getString("Año"));
        factura.setNombre_cliente(jo.getString("Nombre"));
        factura.setDni_cliente(jo.getString("DNI"));

        String fecha_string = jo.getString("Fecha de Emision");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha_string, formatter);
        factura.setFecha_de_emision(fecha);

        factura.setMonto(jo.getDouble("Monto"));

        return factura;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
       JSONObject jsonObject = new JSONObject();

       jsonObject.put("Mes", getMes());
       jsonObject.put("Año", getAnio());
       jsonObject.put("Nombre", getNombre_cliente());
       jsonObject.put("DNI", getDni_cliente());
       jsonObject.put("Fecha de Emision", getFecha_de_emision().toString());
       jsonObject.put("Monto", getMonto());

        return jsonObject;
    }
}

