package model.Persona;
import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Otros.Factura;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Cliente extends Persona implements I_toJson, Serializable {
    private boolean alta_medica;
    private boolean solicito_rutina;
    private boolean debe;
    private Rutina rutina;

    private LocalDate fecha_de_inscripcion;
    private LinkedHashSet<Factura> listaFacturas;

    private TreeSet<Actividad> actividades_cliente;

    public Cliente()
    {
        super();
        alta_medica=true;
        solicito_rutina=true;
        debe=true;
        fecha_de_inscripcion = null;
        rutina = null;
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }

    public Cliente(String nombre, String dni, String telefono, String domicilio,LocalDate fecha_de_inscripcion, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, boolean solicito_rutina, boolean debe) {
        super(nombre, dni, telefono, domicilio ,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        rutina = null;
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }
    /**Este constructor es para el fromJson*/
    public Cliente(JSONObject jo) throws JSONException {
        super(jo);
    }

    //el constructor de aca abajo recibe la lista de actividades por parametro, lo cual sera necesario al momento de realizar inscripcion (no borrar)
    public Cliente(String nombre, String dni, String telefono, String domicilio, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, LocalDate fecha_de_inscripcion, TreeSet<Actividad> actividades_cliente) {
        super(nombre, dni, telefono, domicilio, estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        this.rutina = rutina;
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        this.actividades_cliente = actividades_cliente;
    }

    public Cliente(boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, LocalDate fecha_de_inscripcion, TreeSet<Actividad> actividades_cliente) {
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        this.rutina = rutina;
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        this.actividades_cliente = actividades_cliente;
    }

    public boolean agregarFactura(Factura factura){
        listaFacturas.add(factura);
        return true;
    }

    /**Bloque get ---------------------------------------------------------------------------------------*/
    public boolean isAlta_medica() {
        return alta_medica;
    }
    public boolean isSolicito_rutina() {
        return solicito_rutina;
    }
    public boolean isDebe() {
        return debe;
    }

    public LocalDate getFecha_de_inscripcion() {
        return fecha_de_inscripcion;
    }

    /**Bloque set ---------------------------------------------------------------------------------------*/
    public void setAlta_medica(boolean alta_medica) {
        this.alta_medica = alta_medica;
    }
    public void setSolicito_rutina(boolean solicito_rutina) {
        this.solicito_rutina = solicito_rutina;
    }
    public void setDebe(boolean debe) {
        this.debe = debe;
    }
    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }
    public void setFecha_de_inscripcion(LocalDate fecha_de_inscripcion) {
        this.fecha_de_inscripcion = fecha_de_inscripcion;
    }

    /**--------------------------------------------------------------------------------------------------*/

    public String getDebe(){
        //metodo que devuelve si debe o no el cliente, en un formato de texto legible para cualquier usuario
        //Utilidad : mostrar datos en un formato amigable, y que no se visualice true/false
        String rta = "No";
        if(isDebe()){
            rta = "Si";
        }
        return rta;
    }



    @Override
    public String toString() {
        if(rutina==null){
            rutina = new Rutina();
        }
        if(listaFacturas==null){
            listaFacturas = new LinkedHashSet<>();
        }
        if(actividades_cliente==null){
            actividades_cliente = new TreeSet<>();
        }
        return  super.toString() + "Cliente{" +
                "alta_medica=" + alta_medica +
                ", solicito_rutina=" + solicito_rutina +
                ", debe=" + debe +
                ", fecha de inscripcion=" + fecha_de_inscripcion +
                ", Rutinas=" + rutina.toString() +
                ", listaFacturas=" + listaFacturas.toString() +
                ", hashDeActividades=" + actividades_cliente.toString() +
                "\n";
    }

    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();

        jsonObject.put("Solicita rutina", isSolicito_rutina());
        jsonObject.put("Alta Medica", isAlta_medica());
        jsonObject.put("Debe", isDebe());
        jsonObject.put("Fecha de Inscripcion", getFecha_de_inscripcion().toString());
        if(rutina !=null) {
            jsonObject.put("Rutina", rutina.toJsonObj());
        }

        JSONArray jsonArray = new JSONArray();

        Iterator<Factura> it = listaFacturas.iterator();
        while(it.hasNext()){
            JSONObject aux = it.next().toJsonObj();
        }
        jsonObject.put("Facturas", jsonArray);

        return jsonObject;
    }

    @Override
    public Cliente fromJson(JSONObject jo) throws JSONException {
        Cliente cliente = new Cliente(jo);
        cliente.setDebe(jo.getBoolean("Debe"));
        cliente.setAlta_medica(jo.getBoolean("Alta Medica"));
        cliente.setSolicito_rutina(jo.getBoolean("Solicita rutina"));
       // cliente.setFecha_de_inscripcion((LocalDate) jo.get("Fecha de Inscripcion"));

        String fecha_string = jo.getString("Fecha de Inscripcion");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha_string, formatter);

        cliente.setFecha_de_inscripcion(fecha);

        if(jo.has("Rutina")) {
            Rutina rutina = new Rutina();
            cliente.setRutina(rutina.fromJson(jo.getJSONObject("Rutina")));
        }
        JSONArray jsonArray = jo.getJSONArray("Facturas");
        for(int i=0; i<jsonArray.length();i++){
            Factura factura = new Factura();
            cliente.listaFacturas.add(factura.fromJson(jsonArray.getJSONObject(i)));
        }
        return cliente;
    }

    public void agregarActividad(Actividad actividad){
        actividades_cliente.add(actividad);
    }
    public void agregarActividad(TreeSet<Actividad> actividades){
        actividades_cliente.addAll(actividades);
    }

}
