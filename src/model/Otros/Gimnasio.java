package model.Otros;

import AccesoDatos.ArchivoColeccionUtiles;
import AccesoDatos.ArchivoMapaUtiles;
import excepciones.*;
import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.*;
import model.Persona.Cliente;
import model.Persona.Persona;
import model.Personal.Administrativo;
import model.Personal.Instructor;
import model.Personal.Encargado;
import model.Personal.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.time.LocalDate;
import java.util.*;

/**
 * Clase envoltorio encargada de llamar a los metodos propios de cada clase
 * */
public class Gimnasio {
    private Encargado encargado;
    private HashMap<String, Cliente> mapaCliente;
    private HashMap<String, Instructor> mapaInstructor;
    private LinkedHashSet<Factura> listaFacturas;
    private TreeSet<Actividad> arbolActividades;
    private HashMap<String, Usuario> mapaUsuarios;
/**
 * Constructor de la clase gimnasio. Instancia el atributo Encargado(objeto unico en su clase) con los datos de la sede.
 * Asi como tambien leer los datos de los 5 archivos en formato .dat principales para volcarlos en las respectivas listas de Clientes,
 * Instructores, Actividades, Facturas y Usuarios. Si no se haya el archivo, se instancia la respectiva lista con la sentencia new.
 *
 * */
    public Gimnasio() {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        encargado = new Encargado("JavaGym", "21541044",EGenero.OTRO,"474-5698", "Avenida de los trabajadores 1005", "javaGym@gmail.com","Veni capo", "24-21541044-3");

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

    /**Devuelve en formato String el mapa de clientes con sus respectivos values.
     * @return values del mapa clientes en formato String.*/
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
     * Permite obtener la coleccion de clientes en Formato JSON
     * @return La coleccion de clientes en formato JSON
     */
    public String CompartirDatosClientes() {
        String json = "";
        Iterator it = mapaCliente.entrySet().iterator();
        JSONArray jsonArray_clientes = new JSONArray();
        try{
            while (it.hasNext()) {
                Map.Entry<String, Cliente> entradaDelMapa = (Map.Entry<String, Cliente>) it.next();
                Cliente cliente = entradaDelMapa.getValue();
                jsonArray_clientes.put(cliente.toJsonObj());
            }
            json = jsonArray_clientes.toString();
        }catch(JSONException e){
            json = e.toString();
        }
        return json;
    }
    /**
     * Permite obtener la coleccion de actividades en Formato JSON
     * @return La coleccion de actividades en formato JSON
     */
    public String CompartirDatosActividades(){
        String texto_json = "";
        Iterator<Actividad> it = arbolActividades.iterator();
        JSONArray jsonArrayActividades = new JSONArray();
        int i = 0;
        try{
            while (it.hasNext()) {
                Actividad actividad = (Actividad) it.next();
                jsonArrayActividades.put(i, actividad.toJsonObj());
                i++;
            }
            texto_json = jsonArrayActividades.toString();
        }catch(JSONException e){
            texto_json = e.getMessage();
        }
            return texto_json;
    }

    /**
     * Permite obtener la coleccion de instructores en Formato JSON
     * @return La coleccion de actividades en formato JSON
     */
    public String CompartirDatosInstructores(){
        String json = "";
        Iterator it = mapaInstructor.entrySet().iterator();
        JSONArray jsonArray_clientes = new JSONArray();
        try{
            while (it.hasNext()) {
                Map.Entry<String, Instructor> entradaDelMapa = (Map.Entry<String, Instructor>) it.next();
                Instructor instructor = entradaDelMapa.getValue();
                jsonArray_clientes.put(instructor.toJsonObj());
            }
            json = jsonArray_clientes.toString();
        }catch(JSONException e){
            json = e.toString();
        }

        return json;
    }

    /**
     * Permite obtener la lista de actividades en formato String.
     * @return La coleccion de actividades en formato String.
     */
    public String listarActividades() {
        return arbolActividades.toString();
    }

    /**Función encargada de la persistencia en archivos del tipo '.dat' de las 5 colecciones
     * presentes en la clase Gimnasio
     * */
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

    /**Funcion que crea un json con los datos de las 5
     * listas pertencientes a la clase Gimnasio
     * @return JSONObject con la infomación.
     * @throws JSONException En el metodo 'put' del array si el indice es negativo o
     * en el metodo 'put' del JSONObject si la clave es nula*/

    public JSONObject actualizarJson() throws JSONException {
        /**No pongo Responsable y Direccion*/
        JSONObject jsonObject = new JSONObject();

        Iterator<Actividad> it = arbolActividades.iterator();
        JSONArray jsonArrayActividades = new JSONArray();
        int i = 0;
        while (it.hasNext()) {
            jsonArrayActividades.put(i, it.next().toJsonObj());
            i++;
        }
        jsonObject.put("Actividades", jsonArrayActividades);


        Iterator<Factura> it2 = listaFacturas.iterator();
        JSONArray jsonArrayFacturas = new JSONArray();
        i = 0;
        while (it2.hasNext()) {
            jsonArrayFacturas.put(i, it2.next().toJsonObj());
            i++;
        }
        jsonObject.put("Facturas", jsonArrayFacturas);


        Iterator<Map.Entry<String, Cliente>> it3 = mapaCliente.entrySet().iterator();
        JSONArray jsonArrayClientes = new JSONArray();
        i = 0;
        while (it3.hasNext()) {
            jsonArrayClientes.put(i, it3.next().getValue().toJsonObj());
            i++;
        }
        jsonObject.put("Clientes", jsonArrayClientes);

        Iterator<Map.Entry<String, Instructor>> it4 = mapaInstructor.entrySet().iterator();
        JSONArray jsonArrayInstructor = new JSONArray();
        i = 0;
        while (it4.hasNext()) {
            jsonArrayInstructor.put(i, it4.next().getValue().toJsonObj());
            i++;
        }
        jsonObject.put("Instructores", jsonArrayInstructor);

        Iterator<Map.Entry<String, Usuario>> it5 = mapaUsuarios.entrySet().iterator();
        JSONArray jsonArrayUsuario = new JSONArray();
        i = 0;
        while (it5.hasNext()) {
            jsonArrayUsuario.put(i, it5.next().getValue().toJsonObj());
            i++;
        }
        jsonObject.put("Usuarios", jsonArrayUsuario);

        return jsonObject;

    }

   /**Agrega cliente a el mapa de clientes.
    * @param cliente El cliente que desea ingresar.
    * @return true si fue agregado exitosamente
    * @throws ExistenteException Si ya se
    * encontraba el cliente en la base de datos*/
    public boolean agregar(Cliente cliente) throws ExistenteException {
        if(mapaCliente.containsKey(cliente.getDni())){
            throw new ExistenteException(cliente.getClass());
        }
        mapaCliente.put(cliente.getDni(), cliente);
        return true;
    }

    /**Agrega actividad a el set de actividades.
     * @param actividad Actividad a agregar.
     * @return true si fue agregado exitosamente
     * @throws ExistenteException Si ya se
     * encontraba la actividad en la base de datos*/
    public boolean agregar(Actividad actividad) throws ExistenteException {
        if(!arbolActividades.add(actividad)){
            throw new ExistenteException(actividad.getClass());
        }
        return true;
    }

    /**Agrega instructor a el mapa de instructores.
     * @param instructor (Instructor a agregar)
     * @return true si fue agregado exitosamente
     * @throws ExistenteException Si ya se
     * encontraba el instructor en la base de datos*/
    public boolean agregar(Instructor instructor) throws ExistenteException {
        if(mapaInstructor.containsKey(instructor.getDni())){
            throw new ExistenteException(instructor.getClass());
        }
        mapaInstructor.put(instructor.getDni(), instructor);
        return true;
    }

    /**Agrega factura a la lista de facturas.
     * @param factura (Factura a agregar)
     * @return true si fue agregado exitosamente.
     * @throws ExistenteException Si ya se
     * encontraba la factura en la base de datos*/
    public boolean agregar(Factura factura) throws ExistenteException {
        if(!listaFacturas.add(factura)){
            throw new ExistenteException(factura.getClass());
        }
        return true;
    }

    /**Permite la modificacion del cliente
     * @param nombre, dni, genero,telefono, domicilio, email, estado, grupo_sanguineo,
     * contacto_emergencia, obra_social, fecha_nacimiento, comentario,
     * alta_medica, solicito_rutina, debe, rutina, actividades_cliente
     * @return true si fue correctamente modificado.
     * @throws NoEncontradoException Si no se encontro el cliente a modificar
     *  en la base de datos.*/

    public boolean modificarCliente(String DNI, String nombre, String dni, EGenero genero, String telefono, String domicilio, String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento,
                                    String comentario, boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, TreeSet<Actividad> actividades_cliente ) throws NoEncontradoException {
        if(!mapaCliente.containsKey(DNI)){
            throw new NoEncontradoException(Cliente.class);
        }else{
            mapaCliente.get(DNI).modificar(nombre, dni,genero, telefono, domicilio, email,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario, alta_medica, solicito_rutina, debe, rutina, actividades_cliente);

            return true;
        }

    }

    /**Permite la modificacion del instructor
     * @param nombre, dni, genero, telefono, domicilio, email,
     * estado, grupo_sanguineo, contacto_emergencia,obra_social,
     * fecha_nacimiento,comentario, cuil, imagen_de_perfil, listaActividades.
     * @return true si fue correctamente modificado.
     * @throws NoEncontradoException si no se encontro el instructor a modificar
     * en la base de datos.*/

    public boolean modificarInstructor (String DNI, String nombre, String dni, EGenero genero,
                                        String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo,
                                        String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento,String comentario,
                                        String cuil, String imagen_de_perfil, ArrayList<Actividad> listaActividades) throws NoEncontradoException {

        if(!mapaInstructor.containsKey(DNI)){
            throw new NoEncontradoException(Instructor.class);
        }else{
        mapaInstructor.get(DNI).modificar(nombre, dni, genero, telefono, domicilio, email,
                estado, grupo_sanguineo, contacto_emergencia,obra_social,
                fecha_nacimiento,comentario, cuil, imagen_de_perfil, listaActividades);
            return true;
        }
    }

    /**Permite la modificacion de la actividad.
     * @param actividad_anterior Actividad a modificar
     * @param actividad_modificada Actividad modificada
     * @return true si fue correctamente modificado.
     * @throws NoEncontradoException si no se encontro la actividad a modificar
     * en la base de datos.
     * @throws ExistenteException si la actividad modificada que se desea ingresar coincide con otra preexistente en
     * la base de datos.*/
    public boolean modificarActividad(Actividad actividad_anterior, Actividad actividad_modificada) throws NoEncontradoException, ExistenteException {
        boolean rta = true;
        boolean agregado;
        Actividad actividad_encontrada = buscarActividad(actividad_anterior);
        arbolActividades.remove(actividad_encontrada);
        agregado = arbolActividades.add(actividad_modificada);
        if(!agregado){
            agregar(actividad_encontrada);
            rta = false;
        }
        return rta;
    }

    /**Permite cambiar el estado del cliente Activo a Inactivo y viceversa
     * @param dni Dni necesario para busqueda de cliente a cambiar estado.
     * @return true si la operacion fue exitosa.
     * @throws NoEncontradoException Si no encuentra
     * el cliente en la base de datos*/

    public boolean cambiarEstado(String dni) throws NoEncontradoException { ///Esto es activar/inactivar
        if(mapaCliente.containsKey(dni)){
            if(mapaCliente.get(dni).getEstado() == Eestado.ACTIVO){
            mapaCliente.get(dni).setEstado(Eestado.INACTIVO);
            }else{
                mapaCliente.get(dni).setEstado(Eestado.ACTIVO);
            }
            return true;
        }else{
            throw new NoEncontradoException(Cliente.class);
        }
    }

    /**Permite cambiar banear (inhabilitar) un cliente.
     * @param dni Dni necesario para busqueda de cliente a banear.
     * @return true si la operacion fue exitosa.
     * @throws NoEncontradoException Si no encuentra
     * el cliente en la base de datos*/
    public boolean banear(String dni) throws NoEncontradoException {
        if(!mapaCliente.containsKey(dni)){
            throw new NoEncontradoException(Cliente.class);
            }

            mapaCliente.get(dni).setEstado(Eestado.BANEADO);
            return true;
    }


    /**Permite agregar un apercibimiento a un cliente o a un instructor.
     * @param DNI Dni del cliente o del instructor a apercibir
     * @param descripcion Comentario/Motivo del apercimiento
     * @param fecha Fecha del momento en que se apercibe.
     * @return true si la operacion fue exitosa
     * @throws NoEncontradoException Si no encuentra ningun cliente ni instructor con ese DNI.*/
    public boolean agregarApercibimiento(String DNI, String descripcion, LocalDate fecha) throws NoEncontradoException {
        if(mapaCliente.containsKey(DNI)){

            mapaCliente.get(DNI).agregarApercibimiento(descripcion,fecha);
            return true;

        } else if (mapaInstructor.containsKey(DNI)) {

            mapaInstructor.get(DNI).agregarApercibimiento(descripcion, fecha);
            return true;
        }else{
            throw new NoEncontradoException(Persona.class);
        }
    }

    /**Permite borrar una actividad tanto de la lista total de actividades como la presente en la coleccion de
     * aquellos clientes que asistian a ella.
     * @param actividad La actividad a eliminar.
     * @return true si la operacion fue exitosa.
     * @throws NoEncontradoException si no encontro la actividad a borrar en la base de datos.*/
    public boolean borrarActividad(Actividad actividad) throws NoEncontradoException {
        if(!arbolActividades.contains(actividad)){
            throw new NoEncontradoException(actividad.getClass());
        }
        arbolActividades.remove(actividad);

        Iterator<Map.Entry<String, Cliente>> it = mapaCliente.entrySet().iterator();
        while(it.hasNext()){
            Cliente cliente = it.next().getValue();
                cliente.borrarActividad(actividad);
        }
        return true;
    }

    /**Suma inscriptos cuando se agrega un cliente a ellas al ingresar o al modificar.
     * @param setActividades Actividades que realizara el cliente.
     */
    public void sumarInscripto(TreeSet<Actividad> setActividades){

            Iterator<Actividad> itExterior = setActividades.iterator();

            while(itExterior.hasNext()){
                Actividad actividad = itExterior.next();
                Iterator<Actividad> itInterior = arbolActividades.iterator();
                while(itInterior.hasNext()){
                    Actividad actividadArbol = itInterior.next();
                        if(actividad.equals(actividadArbol)){
                               actividadArbol.sumarInscripto();
                        }
                    }
                }
            }

    /**Resta inscriptos cuando se quita un cliente de ellas/
     * @param setActividades Actividades que el cliente realizaba previamente a su modificacion.*/
    public void restarInscripto(TreeSet<Actividad> setActividades){

        Iterator<Actividad> itExterior = setActividades.iterator();

        while(itExterior.hasNext()){
            Actividad actividad = itExterior.next();
            Iterator<Actividad> itInterior = arbolActividades.iterator();
            while(itInterior.hasNext()){
                Actividad actividadArbol = itInterior.next();
                if(actividad.equals(actividadArbol)){
                    actividadArbol.restarInscripto();
                }
            }
        }
    }

    /**Agrega cliente a el mapa de clientes.
     * @param usuario El usuario que desea registrar.
     * @return true si fue agregado exitosamente
     * @throws ExistenteException Si ya se
     * encontraba el usuario en la base de datos*/
    public boolean agregar(Usuario usuario) throws ExistenteException {
        if (mapaUsuarios.containsKey(usuario.getUsuario())) {
            throw new ExistenteException(usuario.getClass());
        }
        mapaUsuarios.put(usuario.getUsuario(), usuario);
        return true;
    }

    /**Metodo del que se sirve la interfaz para reconocer quien es el usuario que esta ingresando
    @param usuario_ingresado
    @param contrasenia_ingresada
    @return Usuario que puede ser uno de los administrativos o el encargado
    @throws CredencialesInvalidasException cuando el ingreso el usuario o contrasenia no coinciden con la base de datos*/
    public Usuario IngresarAlSistema(String usuario_ingresado, String contrasenia_ingresada) throws CredencialesInvalidasException {
        //metodo del que se sirve la interfaz para reconocer quien es el usuario que esta ingresando
        //este usuario puede ser uno de los administrativos, o bien el encargado (unico)

        Usuario usuario_encontrado = null; //este sera el usuario retornado (puede ser null si no existe coincidencia con nadie
        Usuario usuario = null; //variable utilizada para busqueda si coincide nombre de usuario, lo cual no implica necesariametne que tmb coincida contrasenia
        if(encargado.ValidarCredenciales(usuario_ingresado,contrasenia_ingresada)){
            usuario_encontrado = encargado;
        }
        else{
            if (mapaUsuarios.containsKey(usuario_ingresado)){ //si esta dentro del mapa el nombre de usuario, verifico contrasenia
                usuario = mapaUsuarios.get(usuario_ingresado);
                if(usuario.ValidarCredenciales(usuario_ingresado,contrasenia_ingresada)){
                    usuario_encontrado = usuario;
                }
            }
        }
        if(usuario_encontrado==null){
            throw new CredencialesInvalidasException();
        }
        return usuario_encontrado;
    }

    /**Metodo que retorna el tipo de usuario (adm o encargado) segun el usuario recibido.
    @param usuario Usuario a consultar.
    @return El tipo usuario en formato String.
     */
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

    /**Carga a partir de un objeto tipo json las 5 listas principales de la clase Gimnasio
     * @param jsonObject El jsonobject con la informacion.
     * @param arbolActividades Inicializado.
     * @param mapaCliente Inicializado.
     * @param mapaUsuarios Inicializado.
     * @param mapaInstructor Iicializado.
     * @param listaFacturas Inicializada.
     */
    public void desgrabarJson(JSONObject jsonObject, HashMap<String, Cliente> mapaCliente, HashMap<String, Instructor> mapaInstructor,LinkedHashSet<Factura> listaFacturas, TreeSet<Actividad> arbolActividades, HashMap<String, Usuario> mapaUsuarios ) throws JSONException {

        JSONArray jsonArray;
        jsonArray = jsonObject.getJSONArray("Clientes");
        for(int i=0; i<jsonArray.length();i++){
            Cliente cliente = new Cliente();
            cliente = cliente.fromJson(jsonArray.getJSONObject(i));
            mapaCliente.put(cliente.getDni(), cliente);
        }

        JSONArray jsonArray2;
        jsonArray2 = jsonObject.getJSONArray("Instructores");
        for(int i=0; i<jsonArray2.length();i++){
           Instructor instructor = new Instructor();
            instructor = instructor.fromJson(jsonArray2.getJSONObject(i));
            mapaInstructor.put(instructor.getDni(), instructor);
        }

        JSONArray jsonArray3;
        jsonArray3 = jsonObject.getJSONArray("Facturas");
        for(int i=0; i<jsonArray3.length();i++){
            Factura factura = new Factura();
            factura = factura.fromJson(jsonArray3.getJSONObject(i));
            listaFacturas.add(factura);
        }

        JSONArray jsonArray4;
        jsonArray4 = jsonObject.getJSONArray("Actividades");
        for(int i=0; i<jsonArray4.length();i++){
            Actividad actividad = new Actividad();
            actividad = actividad.fromJson(jsonArray4.getJSONObject(i));
            arbolActividades.add(actividad);
        }


        JSONArray jsonArray5;
        jsonArray5 = jsonObject.getJSONArray("Usuarios");
        for(int i=0; i<jsonArray5.length();i++){
            Usuario usuario = new Usuario();
            usuario = usuario.fromJson(jsonArray5.getJSONObject(i));
            mapaUsuarios.put(usuario.getUsuario(), usuario);
        }
    }

    /** Permite obtener informacion adicional del cliente
     @param dni del cliente a localizar
     @return una cadena de texto que contiene informacion adicional del cliente*/
    private String InformacionAdicionalCliente(String dni){
        return LocalizarCliente(dni).MostrarInformacionAdicional();
    }
    /**
     Permite localizar un cliente a partir de su dni
     @param dni
     @return El cliente localizado a partir de su Dni*/
    public Cliente LocalizarCliente (String dni){
        return mapaCliente.get(dni);}
    /**
     Permite reconocer una actividad presente en el sistema(el metodo es llamado desde la interfaz para ubicar una actividad)
     @param actividad buscada
     @return actividad encontrada
     @throws ExistenteException, si no fue encontrada la actividad.
     */

    /**Busca actividad en arbol de actividades
     * @param actividad La actividad a buscar.
     * @return La actividad encontrada.
     * @throws NoEncontradoException si no la encuentra.*/

    public Actividad buscarActividad(Actividad actividad) throws NoEncontradoException {
        Actividad actividad_encontrada = null;
        Iterator it = arbolActividades.iterator();
        while(it.hasNext()){
            Actividad actividad_en_arbol = (Actividad) it.next();
            if(actividad_en_arbol.equals(actividad)){
                actividad_encontrada = actividad_en_arbol;}
        }
        if(actividad_encontrada==null){
            throw new NoEncontradoException(Actividad.class);
        }
        return actividad_encontrada;
    }

    /**Calcula precio de las actividades de un cliente a partir de un precio base por ser socio.
     * @param actividades_a_calcular Actividades que realizara un cliente.
     * @return Monto que debiera de abonar.*/
    public double CalcularPrecio(ArrayList<Actividad> actividades_a_calcular){
        double precio_base = 2000;
        double precio_actividades=0;
        for(Actividad actividad : actividades_a_calcular){
            precio_actividades += actividad.getPrecio_mensual();
        }
        return precio_base + precio_actividades;//el precio que el cliente abonara en total
    }

    /**Metodo que permite cambiar el estado a deudor de aquellos clientes cuyo ultimo pago ha sido hace mas de un mes
     */
    public void verificarPagos() {
        LocalDate fechaActual = LocalDate.now();

        for (Map.Entry<String, Cliente> entry : mapaCliente.entrySet()) {

            Cliente cliente = entry.getValue();

            LocalDate fechaPago = cliente.getFechaUltimoPago().plusMonths(1);

            boolean debePagar = fechaActual.isAfter(fechaPago) || fechaActual.isEqual(fechaPago);

            cliente.setDebe(debePagar);
        }
    }

    /**Permite cobrar al cliente
     *@see Cliente#pagar()
     *@param cliente a cobrar.
     *@throws ExistenteException si al generar la factura ya existe otra del mismo cliente abonada por el mismo mes.*/
    public void cobrar(Cliente cliente) throws ExistenteException {
        Factura factura = cliente.pagar();
        agregar(factura);
    }


    /**Permite obtener estadisticas sobre los clientes que asisten al gimnasio divido en Hombres y Mujeres,
     * y dentro de ellos, entre edades tales como: edad<30, 30<edad<45, 45<edad<60, edad>60
     * @return La estadistica es formato String.
     * */
    public String estadisticas(){
        String string = "";
        int cantidadMujeresmenora30 = 0;
        int cantidadHombresmenora30 = 0;
        int cantidadMujeres30a45 = 0;
        int cantidadHombres30a45 = 0;
        int cantidadMujeres45a60 = 0;
        int cantidadHombres45a60 = 0;
        int cantidadMujeresmayora60 = 0;
        int cantidadHombresmayora60 = 0;


        Iterator<Map.Entry<String, Cliente>> it = mapaCliente.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Cliente> entrada =  it.next();
            Cliente cliente = entrada.getValue();
            EGenero genero = cliente.getGenero();
            int edad = LocalDate.now().getYear() - cliente.getFecha_nacimiento().getYear();
            if(genero == EGenero.MASCULINO){
                if(edad<30){
                   cantidadHombresmenora30++;
                } else if (edad>=30 && edad<=45) {
                    cantidadHombres30a45++;
                } else if (edad>=45 && edad<=60) {
                   cantidadHombres45a60++;
                } else if (edad>=60) {
                    cantidadHombresmayora60++;
                }
            } else if (genero == EGenero.FEMENINO) {
                if(edad<30){
                    cantidadMujeresmenora30++;
                } else if (edad>=30 && edad<=45) {
                    cantidadMujeres30a45++;
                } else if (edad>=45 && edad<=60) {
                    cantidadMujeres45a60++;
                } else if (edad>=60) {
                    cantidadMujeresmayora60++;
                }
            }
        }
        int sumaHombres = cantidadHombresmenora30 + cantidadHombres30a45 + cantidadHombres45a60 + cantidadHombresmayora60;
        int sumaMujeres = cantidadMujeresmenora30 + cantidadMujeres30a45 + cantidadMujeres45a60 + cantidadMujeresmayora60;
        string = "ESTADISTICAS:\n\n MUJERES" + "\nCantidad total de Mujeres: " + sumaMujeres
                + "\n   Menores de 30 años: " + cantidadMujeresmenora30 + "\n   Entre 30 y 45 años: " + cantidadMujeres30a45
                + "\n   Entre 45 y 60 años: " + cantidadMujeres45a60 + "\n   Mayores de 60 años: " + cantidadMujeresmayora60 +
                "\n\nHOMBRES\nCantidad de total Hombres: " + sumaHombres
                + "\n   Menores de 30 años: " + cantidadHombresmenora30 + "\n   Entre 30 y 45 años: " + cantidadHombres30a45
                + "\n   Entre 45 y 60 años: " + cantidadHombres45a60 + "\n   Mayores de 60 años: " + cantidadHombresmayora60;

        return string;
    }

    /**Permite saber la recaudacion total que tuvo el gimnasio en el mes.
     * @param mes Mes sobre el que se desea calcular la recaudacion.
     * */
    public double GananciaMensual(String mes){
        double ganancia = 0;
        Iterator<Factura> it = listaFacturas.iterator();
        while(it.hasNext()){
            Factura factura = it.next();
            if(factura.getMes().equals(mes)){
                ganancia += factura.getMonto();
            }
        }
        return ganancia;
    }



}
