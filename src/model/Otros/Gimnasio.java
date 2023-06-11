package model.Otros;

import AccesoDatos.ArchivoColeccionUtiles;
import AccesoDatos.ArchivoMapaUtiles;
import model.ActivYrutina.Actividad;
import model.Persona.Cliente;
import model.Personal.Instructor;
import model.Personal.Encargado;
import model.Personal.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.*;


public class Gimnasio {


    private String responsable;
    private String direccion;
    /**Ver Bloc de notas. Se puede pensar en que el encargado contenga los datos del gimnasio, tales como mail, direccion, etc.*/
    private HashMap<String,Cliente> mapaCliente;
    private HashMap<String, Instructor> mapaInstructor;
    private LinkedHashSet<Factura> listaFacturas;
    private TreeSet<Actividad> arbolActividades;

    private final String usuario_admin="admin2023";
    private final String usuario_encargado="encargado2023";
    private final String contrasenia_admin="admin123";
    private final String contrasenia_encargado="encargado123";
    public Gimnasio(String responsable, String direccion) {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        this.responsable = responsable;
        this.direccion = direccion;
        mapaCliente = new HashMap<>(archivoMapaUtiles.leerMapa("Archivo clientes.dat"));
        //mapaCliente = (HashMap<String, Cliente>) archivoMapaUtiles.leerMapa("Archivo clientes.dat");
        mapaInstructor=new HashMap<>(archivoMapaUtiles.leerMapa("instructores.dat"));
        //mapaInstructor = (HashMap<String, Instructor>) archivoMapaUtiles.leerMapa("instructores.dat");
        listaFacturas=new LinkedHashSet<>(archivoColeccionUtiles.leerColeccion("facturas.dat"));
        //listaFacturas = (LinkedHashSet<Factura>) archivoColeccionUtiles.leerColeccion("facturas.dat");
        arbolActividades=new TreeSet<>(archivoColeccionUtiles.leerColeccion("actividades.dat"));
    }

    public Gimnasio()
    {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        responsable=" ";
        direccion=" ";
        mapaCliente = new HashMap<>(archivoMapaUtiles.leerMapa("Archivo clientes.dat"));
        //mapaCliente = (HashMap<String, Cliente>) archivoMapaUtiles.leerMapa("Archivo clientes.dat");
        mapaInstructor=new HashMap<>(archivoMapaUtiles.leerMapa("instructores.dat"));
        //mapaInstructor = (HashMap<String, Instructor>) archivoMapaUtiles.leerMapa("instructores.dat");
        listaFacturas=new LinkedHashSet<>(archivoColeccionUtiles.leerColeccion("facturas.dat"));
        //listaFacturas = (LinkedHashSet<Factura>) archivoColeccionUtiles.leerColeccion("facturas.dat");
        arbolActividades=new TreeSet<>(archivoColeccionUtiles.leerColeccion("actividades.dat"));
    }


    public String getResponsable() {
        return responsable;
    }

    public String getDireccion() {
        return direccion;
    }

    /**Preguntar funcion de este metodo*/
    public Usuario DamePersonal (String usuario, String contrasenia){
        Usuario a=null;
        Encargado e=null;
        Usuario p= null;
        if (usuario.equals(usuario_admin) && contrasenia.equals(contrasenia_admin))
        {
            a = new Usuario(usuario,contrasenia);
            p = a;
        }
        else if (usuario.equals(usuario_encargado) && contrasenia.equals(contrasenia_encargado)) {
            e = new Encargado(usuario, contrasenia);
            p = e;
        }
        return p;
    }

    public String agregarFactura(Factura factura){
        listaFacturas.add(factura);
        return factura.toString();
    }

    /**Queremos tener este metodo?*/
    @Override
    public String toString() {
        return "Gimnasio{" +
                "responsable='" + responsable + '\'' +
                ", direccion='" + direccion + '\'' +
                ", mapaCliente=" + mapaCliente +
                ", mapaInstructor=" + mapaInstructor +
                ", listaFacturas=" + listaFacturas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gimnasio gimnasio = (Gimnasio) o;
        return Objects.equals(responsable, gimnasio.responsable) && Objects.equals(direccion, gimnasio.direccion) && Objects.equals(mapaCliente, gimnasio.mapaCliente) && Objects.equals(mapaInstructor, gimnasio.mapaInstructor) && Objects.equals(listaFacturas, gimnasio.listaFacturas);
    }

    @Override
    public int hashCode() {
        return 1;
    }


    public String ListarClientes(){
        String rta = "Clientes Presentes en Sistema (mapaCliente):\n";
        //Iterator it = mapaCliente.GetRecorredor();
        Iterator it = mapaCliente.entrySet().iterator();//diegoprueba
        while(it.hasNext()){
            Map.Entry<String,Cliente>entradaDelMapa = (Map.Entry<String,Cliente>)it.next();
            Cliente cliente = entradaDelMapa.getValue();
            rta += cliente.toString() + "\n";
        }
        return rta += "\n Fin mapaCliente";
    }
    /**Preguntar que se quiere lograr con esto:*/
    public String CompartirDatosClientes(){
        //metodo que desde clase Gimnasio, proporciona a Interfaz los datos de sus clientes,en formato JSON
        String json="";
        // Iterator it = mapaCliente.GetRecorredor();
        Iterator it = mapaCliente.entrySet().iterator();
        JSONArray jsonArray_clientes = new JSONArray();
        while(it.hasNext()){
            Map.Entry<String,Cliente> entradaDelMapa = (Map.Entry<String,Cliente>)it.next();
            Cliente cliente = entradaDelMapa.getValue();
            try{
                jsonArray_clientes.put(cliente.toJsonObj());
            }catch(JSONException e){
                json = e.getMessage();
            }
        }
        return jsonArray_clientes.toString();
    }

    public void GuardarEnArchivo(){
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        archivoColeccionUtiles.guardarColeccion(listaFacturas, "facturas.dat");
        archivoColeccionUtiles.guardarColeccion(arbolActividades, "actividades.dat");
        archivoMapaUtiles.guardarMapa(mapaCliente, "clientes.dat");
        archivoMapaUtiles.guardarMapa(mapaInstructor, "instructores.dat");

    }

    public JSONObject actualizarJson() throws JSONException {
        /**No pongo Responsable y Direccion*/
        JSONObject jsonObject = new JSONObject();

        Iterator<Actividad> it = arbolActividades.iterator();
        JSONArray jsonArrayActividades = new JSONArray();
        int i = 0;
        while(it.hasNext()){
            jsonArrayActividades.put(i, it.next());
            i++;
        }
        jsonObject.put("Actividades", jsonArrayActividades);


        Iterator<Factura> it2 = listaFacturas.iterator();
        JSONArray jsonArrayFacturas = new JSONArray();
        i = 0;
        while(it2.hasNext()){
            jsonArrayFacturas.put(i, it2.next());
            i++;
        }
        jsonObject.put("Facturas", jsonArrayFacturas);



        Iterator<Map.Entry<String, Cliente>> it3 = mapaCliente.entrySet().iterator();
        JSONArray jsonArrayClientes = new JSONArray();
        i = 0;
        while(it3.hasNext()){
            jsonArrayClientes.put(i, it3.next());
            i++;
        }
        jsonObject.put("Clientes", jsonArrayClientes);

        Iterator<Map.Entry<String, Cliente>> it4 = mapaCliente.entrySet().iterator();
        JSONArray jsonArrayInstructor = new JSONArray();
        i = 0;
        while(it4.hasNext()){
            jsonArrayInstructor.put(i, it4.next());
            i++;
        }
        jsonObject.put("Instructores", jsonArrayInstructor);

        return jsonObject;

    }

    public void agregar(Object elemento){
        /**Pensar que es lo correcto:
         *
         * 1) Un solo metodo que agregue un Object y crear los 4 instance of
         * 2) 4 metodos usando polimorfismo.
         * */

    }

}

