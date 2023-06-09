package model.Persona;

import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Genericos.GestionadorLista;
import model.Otros.Apercibimiento;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Persona implements Serializable, I_toJson {
    private String nombre;
    private String dni;
    private String telefono;
    private GestionadorLista<Apercibimiento> listaApercibimientos;
    private Eestado estado;
    private EGrupoSanguineo grupo_sanguineo;
    private String contacto_emergencia;
    private String obra_social;
    private LocalDate fecha_nacimiento;

    private String comentario;

    public Persona(String nombre, String dni, String telefono,Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,LocalDate fecha_nacimiento,String comentario) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        listaApercibimientos = new GestionadorLista<>();
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
        listaApercibimientos=new GestionadorLista<>();
        estado=Eestado.ACTIVO;
        grupo_sanguineo=EGrupoSanguineo.NINGUNO;
        contacto_emergencia=" ";
        obra_social=" ";
        fecha_nacimiento=null;
        comentario=" ";
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
    public String mostarApercibimieto(){
        return listaApercibimientos.Listar();
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


    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", listaApercibimientos=" + listaApercibimientos.Listar() +
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
        if(o instanceof Persona){
            Persona aux = (Persona) o;
            if(aux.getDni().equals(getDni())){
                rta = true;
                }
            }
       }
    return rta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono, listaApercibimientos, estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
    }

    /**Agregar parte desde y hacia json si corresponde*/

    @Override
    public void fromJson(JSONObject jo) throws JSONException {
        /**CREAR LEIDA DE JSON*/
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = new JSONObject();

            jsonObject.put("nombre", getNombre());
            jsonObject.put("DNI", getDni());
            jsonObject.put("telefono", getTelefono());
            jsonObject.put("Estado", getEstado());
            jsonObject.put("Grupo Sanguineo", getGrupo_sanguineo());
            jsonObject.put("Contacto de emergencia", getContacto_emergencia());
            jsonObject.put("Obra Social", getObra_social());
            jsonObject.put("Fecha de Nacimiento", getFecha_nacimiento());
            jsonObject.put("Comentario", getComentario());

           JSONArray jsonArray = new JSONArray();
           for(int i=0; i<listaApercibimientos.contador();i++){
                JSONObject aux = listaApercibimientos.devolverElemento(i).toJsonObj();
                jsonArray.put(i, aux);
           }

           jsonObject.put("Apercibimientos", jsonArray);


        return jsonObject;
    }
}

