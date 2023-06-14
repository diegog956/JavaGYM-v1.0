package model.Otros;

import AccesoDatos.ArchivoColeccionUtiles;
import AccesoDatos.ArchivoMapaUtiles;
import excepciones.UsuarioExistenteException;
import model.ActivYrutina.Actividad;
import model.Persona.Cliente;
import model.Personal.Administrativo;
import model.Personal.Instructor;
import model.Personal.Encargado;
import model.Personal.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.*;


public class Gimnasio {

    private Encargado encargado;
    private String mail;
    private String CUIL;
    private String responsable;
    private String direccion;
    /**
     * Ver Bloc de notas. Se puede pensar en que el encargado contenga los datos del gimnasio, tales como mail, direccion, etc.
     */
    private HashMap<String, Cliente> mapaCliente;
    private HashMap<String, Instructor> mapaInstructor;
    private LinkedHashSet<Factura> listaFacturas;
    private TreeSet<Actividad> arbolActividades;

    private HashMap<String, Usuario> mapaUsuarios;

    public Gimnasio(String responsable, String direccion) {

        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        encargado = new Encargado("JavaGym", "21541044", "474-5698", "Avenida de los trabajadores 1005", "Veni capo", "24-21541044-3");
        responsable = encargado.getNombre();
        direccion = encargado.getDomicilio();
        CUIL = encargado.getCUIL();
        //mail = encargado.get

        mapaCliente = new HashMap<>();
        mapaCliente = (HashMap<String, Cliente>) archivoMapaUtiles.leerMapa("clientes.dat");
        mapaInstructor = new HashMap<>();
        mapaInstructor = (HashMap<String, Instructor>) archivoMapaUtiles.leerMapa("instructores.dat");
        listaFacturas = new LinkedHashSet<>();
        listaFacturas = (LinkedHashSet<Factura>) archivoColeccionUtiles.leerColeccion("facturas.dat");
        arbolActividades = new TreeSet<>();
        arbolActividades = (TreeSet<Actividad>) archivoColeccionUtiles.leerColeccion("actividades.dat");


    }

    public Gimnasio() {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        responsable = " ";
        direccion = " ";

        encargado = new Encargado("JavaGym", "21541044", "474-5698", "Avenida de los trabajadores 1005", "Veni capo", "24-21541044-3");
        responsable = encargado.getNombre();
        direccion = encargado.getDomicilio();
        CUIL = encargado.getCUIL();
        //mail = encargado.get


        if (archivoMapaUtiles.leerMapa("clientes.dat") == null) {
            mapaCliente = new HashMap<>();
        } else {
            mapaCliente = new HashMap<>(archivoMapaUtiles.leerMapa("clientes.dat"));
        }

        if (archivoMapaUtiles.leerMapa("instructores.dat") == null) {
            mapaInstructor = new HashMap<>();
        } else {
            mapaInstructor = new HashMap<>(archivoMapaUtiles.leerMapa("instructores.dat"));
        }

        if (archivoColeccionUtiles.leerColeccion("facturas.dat") == null) {
            listaFacturas = new LinkedHashSet<>();
        } else {
            listaFacturas = new LinkedHashSet<>(archivoColeccionUtiles.leerColeccion("facturas.dat"));
        }
        if (archivoColeccionUtiles.leerColeccion("actividades.dat") == null) {
            arbolActividades = new TreeSet<>();
        } else {
            arbolActividades = new TreeSet<>(archivoColeccionUtiles.leerColeccion("actividades.dat"));
        }

        //agregado sergio 14-06
       if (archivoMapaUtiles.leerMapa("usuarios.dat") == null) {
            mapaUsuarios = new HashMap<>();
        } else {
            mapaUsuarios = new HashMap<>(archivoMapaUtiles.leerMapa("usuarios.dat"));
        }

    }


    public String getResponsable() {
        return responsable;
    }

    public String getDireccion() {
        return direccion;
    }


    public String agregarFactura(Factura factura) {
        listaFacturas.add(factura);
        return factura.toString();
    }

    /**
     * Queremos tener este metodo?
     */
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


    public String ListarClientes() {
        String rta = "Clientes Presentes en Sistema (mapaCliente):\n";
        //Iterator it = mapaCliente.GetRecorredor();
        Iterator it = mapaCliente.entrySet().iterator();//diegoprueba
        while (it.hasNext()) {
            Map.Entry<String, Cliente> entradaDelMapa = (Map.Entry<String, Cliente>) it.next();
            Cliente cliente = entradaDelMapa.getValue();
            rta += cliente.toString() + "\n";
        }

        return rta += "\n Fin mapaCliente";
    }

    /**
     * Preguntar que se quiere lograr con esto:
     */
    public String CompartirDatosClientes() {
        //metodo que desde clase Gimnasio, proporciona a Interfaz los datos de sus clientes,en formato JSON
        String json = "";
        // Iterator it = mapaCliente.GetRecorredor();
        Iterator it = mapaCliente.entrySet().iterator();
        JSONArray jsonArray_clientes = new JSONArray();
        while (it.hasNext()) {
            Map.Entry<String, Cliente> entradaDelMapa = (Map.Entry<String, Cliente>) it.next();
            Cliente cliente = entradaDelMapa.getValue();
            try {
                jsonArray_clientes.put(cliente.toJsonObj());
            } catch (JSONException e) {
                json = e.getMessage();
            }
        }
        return jsonArray_clientes.toString();
    }


    public String listarActividades() {
        return arbolActividades.toString();
    }

    public String ListarFacturas() {

        /**Preguntar como se planteara esto a nivel proyecto.*/

        return "a";
    }

    public void guardarEnArchivo() {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        archivoColeccionUtiles.guardarColeccion(listaFacturas, "facturas.dat");
        archivoColeccionUtiles.guardarColeccion(arbolActividades, "actividades.dat");
        archivoMapaUtiles.guardarMapa(mapaCliente, "clientes.dat");
        archivoMapaUtiles.guardarMapa(mapaInstructor, "instructores.dat");

        //agregado 14-06
        archivoMapaUtiles.guardarMapa(mapaUsuarios,"usuarios.dat");

    }

    public JSONObject actualizarJson() throws JSONException {
        /**No pongo Responsable y Direccion*/
        JSONObject jsonObject = new JSONObject();

        Iterator<Actividad> it = arbolActividades.iterator();
        JSONArray jsonArrayActividades = new JSONArray();
        int i = 0;
        while (it.hasNext()) {
            jsonArrayActividades.put(i, it.next());
            i++;
        }
        jsonObject.put("Actividades", jsonArrayActividades);


        Iterator<Factura> it2 = listaFacturas.iterator();
        JSONArray jsonArrayFacturas = new JSONArray();
        i = 0;
        while (it2.hasNext()) {
            jsonArrayFacturas.put(i, it2.next());
            i++;
        }
        jsonObject.put("Facturas", jsonArrayFacturas);


        Iterator<Map.Entry<String, Cliente>> it3 = mapaCliente.entrySet().iterator();
        JSONArray jsonArrayClientes = new JSONArray();
        i = 0;
        while (it3.hasNext()) {
            jsonArrayClientes.put(i, it3.next());
            i++;
        }
        jsonObject.put("Clientes", jsonArrayClientes);

        Iterator<Map.Entry<String, Cliente>> it4 = mapaCliente.entrySet().iterator();
        JSONArray jsonArrayInstructor = new JSONArray();
        i = 0;
        while (it4.hasNext()) {
            jsonArrayInstructor.put(i, it4.next());
            i++;
        }
        jsonObject.put("Instructores", jsonArrayInstructor);

        return jsonObject;

    }

    public void agregar(Cliente cliente) {
        mapaCliente.put(cliente.getDni(), cliente);
    }

    public void agregar(Actividad actividad) {
        arbolActividades.add(actividad);
    }

    public void agregar(Instructor instructor) {
        mapaInstructor.put(instructor.getDni(), instructor);
    }

    public void agregar(Factura factura) {
        listaFacturas.add(factura);
    }

    public void remover(Cliente cliente) {


    }

    public void listarTodo() {
        System.out.println("Clientes\n\n\n");
        System.out.println(mapaCliente);
        System.out.println("INSTRUCTORES\n\n\n");
        System.out.println(mapaInstructor);
        System.out.println("Facturas\n\n\n");
        System.out.println(listaFacturas);
        System.out.println("ACTIVIDADES\n\n\n");
        System.out.println(arbolActividades);

        System.out.println("USUARIOS\n\n\n");
        System.out.println(mapaUsuarios);


    }

    //Sergio - Martes 13 de Junio
    public boolean agregar(Usuario usuario) throws UsuarioExistenteException {
        //metodo que agrega un usuario a la lista de usuarios. La excepcion es atrapada por la UI
        if (mapaUsuarios.containsKey(usuario.getUsuario())) {
            throw new UsuarioExistenteException();
        }
        mapaUsuarios.put(usuario.getUsuario(), usuario); //no es necesario el else ya que el throw rompe ejecucion
        return true;
    }

    //Sergio - Miercoles 14 de Junio

    public Usuario IngresarAlSistema(String usuario_ingresado, String contrasenia_ingresada) {
        //metodo del que se sirve la interfaz para reconocer quien es el usuario que esta ingresando
        //este usuario puede ser uno de los administrativos, o bien el encargado (unico)

        Usuario usuario_encontrado = null; //este sera el usuario retornado (puede ser null si no existe coincidencia con nadie
        Usuario usuario = null; //variable utilizada para busqueda si coincide nombre de usuario, lo cual no implica necesariametne que tmb coincida contrasenia
        if(usuario_ingresado.equals(encargado.getUsuario()) && contrasenia_ingresada.equals(encargado.getContrasenia())){
            usuario_encontrado = encargado;
        }
        else{
            if (mapaUsuarios.containsKey(usuario_ingresado)){ //si esta dentro del mapa el nombre de usuario, verifico contrasenia
                usuario = mapaUsuarios.get(usuario_ingresado);
                if(usuario.getContrasenia().equals(contrasenia_ingresada)){
                    usuario_encontrado = usuario;
                }
            }
        }
        return usuario_encontrado;
    }

    public String getTipoDeUsuario(Usuario usuario){
        //metodo que devuelve el tipo de usuario (adm o encargado) segun el usuario recibido
        //Utilidad: es llamado por UI para saber el tipo de Usuario
        String rta = "";
        if(usuario instanceof Administrativo){
            rta = "Administrativo";
        }
        else {
            rta = "Encargado";
        }
        return rta;
    }

}

