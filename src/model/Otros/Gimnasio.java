package model.Otros;

import AccesoDatos.ArchivoColeccionUtiles;
import AccesoDatos.ArchivoMapaUtiles;
import model.Genericos.GestionadorLinkedHashSet;
import model.Persona.Cliente;
import model.Personal.Instructor;
import model.Personal.Encargado;
import model.Personal.Usuario;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.*;


public class Gimnasio {
    private String responsable;
    private String direccion;
    /**Ver Bloc de notas. Se puede pensar en que el encargado contenga los datos del gimnasio, tales como mail, direccion, etc.*/
    private HashMap<String,Cliente> mapaCliente;
    private HashMap mapaInstructor;
    private GestionadorLinkedHashSet<Factura> listaFacturas;
    private TreeSet arbolActividades;/**GestionadorTreeSet creado. Ver su implementacion.*/

    private final String usuario_admin="admin2023";
    private final String usuario_encargado="encargado2023";
    private final String contrasenia_admin="admin123";
    private final String contrasenia_encargado="encargado123";
    public Gimnasio(String responsable, String direccion) {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        this.responsable = responsable;
        this.direccion = direccion;
        mapaCliente = new HashMap<>();
        mapaInstructor = new HashMap<>(archivoMapaUtiles.leerMapa("instructores.dat"));
        listaFacturas = new GestionadorLinkedHashSet<>();
        arbolActividades=new TreeSet<>(archivoColeccionUtiles.leerColeccion("actividades.dat"));
    }

    public Gimnasio()
    {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        responsable=" ";
        direccion=" ";
        mapaCliente = new HashMap<>();
        mapaCliente = (HashMap<String, Cliente>) archivoMapaUtiles.leerMapa("Archivo clientes.dat");
        mapaInstructor=new HashMap<>();
        mapaInstructor = (HashMap<String, Instructor>) archivoMapaUtiles.leerMapa("instructores.dat");
        System.out.println("Hi");
        listaFacturas=new GestionadorLinkedHashSet<>();
        //listaFacturas = (GestionadorLinkedHashSet<Factura>) archivoColeccionUtiles.leerColeccion("facturas.dat");
        arbolActividades=new TreeSet<>(archivoColeccionUtiles.leerColeccion("actividades.dat"));
    }

    public HashMap<String, Cliente> getMapaCliente() {
        return mapaCliente;
    }

    public HashMap<String, Instructor> getMapaInstructor() {
        return mapaInstructor;
    }

    public GestionadorLinkedHashSet<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getDireccion() {
        return direccion;
    }

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
        listaFacturas.Agregar(factura);
        return factura.toString();
    }


    @Override
    public String toString() { ///Querriamos tener este metodo?
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

}

