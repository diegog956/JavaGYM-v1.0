package model.Otros;

import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/**Clase que crea y realiza metodos sobre el objeto Factura. Contiene la informacion del pago mensual.
 * @see I_toJson para mas detalle sobre los metodos que debe implementar.
 * @see Serializable para mas detalle sobre la persistencia de archivos.*/
public class Factura implements I_toJson, Serializable {
    private String mes;
    private String anio;
    private String dni_cliente;
    private String nombre_cliente;
    private LocalDate fecha_de_emision;
    private double monto;

/**Constructor con todos los datos que debe tener una factura*/
    public Factura(String mes, String anio, String dni_cliente, String nomobre_cliente, LocalDate fecha_de_emision, double monto) {

        this.anio=anio;
        this.mes=mes;
        this.dni_cliente = dni_cliente;
        this.nombre_cliente = nomobre_cliente;
        this.fecha_de_emision = fecha_de_emision;
        this.monto = monto;
    }
/**Constructor vacio del objeto Factura*/
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


    /**Retorna informacion de la factura para mostrarla en el front.
     * @return Atributos de factura en formato String.*/
    @Override
    public String toString() {
        return "\n======================\nFactura " + getMes() + " " + getAnio() + "\n======================\n" +
                "Cliente: " + getNombre_cliente() +
                "\nDNI: " + getDni_cliente() +
                "\nFecha emision: "+ getFecha_de_emision() +
                "\n-------------------\nMonto: " + getMonto() + "\n-------------------\n";
    }

    /**Compara Facturas segun el mes, el anio y el dni del cliente.
     * @param o Recibe cualquier objeto que herede de Object.
     * @return true si la factura a comparar es igual a la comparada. False si no lo es.*/
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


    /**Permite a partir de un jsonObject retornar una factura con los datos que el primero poseia.
     * @param jo JSONObject con los datos de la factura
     * @return La factura creada.
     * @throws JSONException si el value no coincide con el tipo de dato asociado al metodo get.*/
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


    /**Permite crear un objeto que contenga la informacion de la factura invocada.
     * @return El JSONObject creado a partir de la informacion.
     * @throws JSONException si la claves es nula al invocar al metodo put.*/
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

