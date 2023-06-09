package model.Persona;

import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Genericos.GestionadorHashSet;
import model.Genericos.GestionadorLista;
import model.Genericos.GestionarLinkedHashSet;
import model.Otros.Factura;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

public class Cliente extends Persona implements I_toJson {
    private boolean alta_medica;
    private boolean solicito_rutina;
    private boolean debe;
    private Rutina rutina;
    private GestionarLinkedHashSet<Factura> listaFacturas;

    private GestionadorHashSet<Actividad> hashDeActividades; ///ID concatenado para q se ordenen por actividades

    public Cliente()
    {
        super();
        alta_medica=true;
        solicito_rutina=true;
        debe=true;
        rutina = null;
        listaFacturas=new GestionarLinkedHashSet<>();
        hashDeActividades=new GestionadorHashSet<>();
    }

    public Cliente(String nombre, String dni, String telefono, String domicilio, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, boolean solicito_rutina, boolean debe) {
        super(nombre, dni, telefono, domicilio ,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        rutina = null;
        this.listaFacturas = new GestionarLinkedHashSet<>();
        this.hashDeActividades = new GestionadorHashSet<>();
    }

    public boolean agregarFactura(Factura factura){
        listaFacturas.Agregar(factura);
        return true;
    }

    public boolean isAlta_medica() {
        return alta_medica;
    }

    public boolean isSolicito_rutina() {
        return solicito_rutina;
    }

    public boolean isDebe() {
        return debe;
    }


    @Override
    public String toString() {
        return  "Cliente{" +
                "alta_medica=" + alta_medica +
                ", solicito_rutina=" + solicito_rutina +
                ", debe=" + debe +
                ", Rutinas=" + rutina.toString() +
                ", listaFacturas=" + listaFacturas.toString() +
                ", hashDeActividades=" + hashDeActividades +
                '}';
    }


    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();

        jsonObject.put("Solicita rutina", isSolicito_rutina());
        jsonObject.put("Alta Medica", isAlta_medica());
        jsonObject.put("Debe", isDebe());
        if(rutina !=null) {
            jsonObject.put("Rutina", rutina.toJsonObj());
        }

        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<listaFacturas.contador();i++){
            JSONObject aux = listaFacturas.devolverElemento(i).toJsonObj();
        }
        jsonObject.put("Facturas", jsonArray);




        return jsonObject;
    }

}
