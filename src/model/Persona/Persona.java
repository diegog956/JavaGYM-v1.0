package model.Persona;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Persona implements Serializable, I_toJson {
    private String nombre;
    private String dni;
    private String telefono;
    private String domicilio;

    /**Pensar en la incorporacion de correo electronico como atributo de las personas.*/
    private ArrayList<Apercibimiento> listaApercibimientos;
    private Eestado estado;
    private EGrupoSanguineo grupo_sanguineo;
    private String contacto_emergencia;
    private String obra_social;
    private LocalDate fecha_nacimiento;
    private String comentario;

    public Persona(String nombre, String dni, String telefono, String domicilio, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,LocalDate fecha_nacimiento,String comentario) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.domicilio = domicilio;
        listaApercibimientos = new ArrayList<>();
        this.estado = estado;
        this.grupo_sanguineo = grupo_sanguineo;
        this.contacto_emergencia = contacto_emergencia;
        this.obra_social = obra_social;
        this.fecha_nacimiento = fecha_nacimiento;
        this.comentario=comentario;

    }

    public Persona ()
    {
        nombre=" ";
        dni=" ";
        telefono=" ";
        domicilio = "";
        listaApercibimientos=new ArrayList<>();
        estado=Eestado.ACTIVO;
        grupo_sanguineo=EGrupoSanguineo.NINGUNO;
        contacto_emergencia=" ";
        obra_social=" ";
        fecha_nacimiento=null;
        comentario=" ";
    }

    /**Constructor para fromJson*/
    public Persona(JSONObject jo) throws JSONException {
        nombre = jo.getString("Nombre");
        dni = jo.getString("DNI");
        telefono = jo.getString("Telefono");
        domicilio = jo.getString("Domicilio");
       // estado = (Eestado) jo.get("Estado");
        estado = Eestado.valueOf(jo.getString("Estado")) ;
        //grupo_sanguineo = (EGrupoSanguineo)
        grupo_sanguineo = EGrupoSanguineo.valueOf(jo.getString("Grupo Sanguineo"));
        contacto_emergencia = jo.getString("Contacto de Emergencia");
        obra_social = jo.getString("Obra Social");
        //fecha_nacimiento = (LocalDate) jo.get("Fecha de Nacimiento");

        String fecha_string = jo.getString("Fecha de Nacimiento");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha_string, formatter);

        fecha_nacimiento = fecha;
        comentario = jo.getString("Comentario");
        listaApercibimientos = new ArrayList<>();
        JSONArray ja = null;
        ja = jo.getJSONArray("Apercibimientos");
        for(int i=0; i< ja.length();i++){
            Apercibimiento apercibimiento = null;
            apercibimiento = apercibimiento.fromJson(ja.getJSONObject(i));
            listaApercibimientos.add(apercibimiento);
        }
    }
    public String getComentario() {
        return comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    /**Verificar muestra de los apercibimientos. Formato.*/
    public String mostarApercibimietos(){
        return listaApercibimientos.toString();
    }

    public Eestado getEstado() {
        return estado;
    }

    public EGrupoSanguineo getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public String getContacto_emergencia() {
        return contacto_emergencia;
    }

    public String getObra_social() {
        return obra_social;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' + ", domicilio='" + domicilio +
                ", listaApercibimientos=" + listaApercibimientos.toString() +
                ", estado=" + estado +
                ", grupo_sanguineo=" + grupo_sanguineo +
                ", contacto_emergencia='" + contacto_emergencia + '\'' +
                ", obra_social='" + obra_social + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", comentario='" + comentario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
       boolean rta= false;
        if(o!=null){
        if(o instanceof Persona aux){
            if(aux.getDni().equals(getDni())){
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
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = new JSONObject();

            jsonObject.put("Nombre", getNombre());
            jsonObject.put("DNI", getDni());
            jsonObject.put("Telefono", getTelefono());
            jsonObject.put("Estado", getEstado().name()); //name agregado a prosopito
            jsonObject.put("Grupo Sanguineo", getGrupo_sanguineo().name());//name agregado a prosopito
            jsonObject.put("Contacto de Emergencia", getContacto_emergencia());
            jsonObject.put("Obra Social", getObra_social());
            jsonObject.put("Fecha de Nacimiento", getFecha_nacimiento().toString());
            jsonObject.put("Comentario", getComentario());
            jsonObject.put("Domicilio", getDomicilio());

           JSONArray jsonArray = new JSONArray();

           for(int i=0; i<listaApercibimientos.size();i++){
                JSONObject aux = listaApercibimientos.get(i).toJsonObj();
                jsonArray.put(i, aux);
           }
           jsonObject.put("Apercibimientos", jsonArray);
        return jsonObject;
    }
}

