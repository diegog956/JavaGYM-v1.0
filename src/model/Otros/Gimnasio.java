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
import java.time.format.TextStyle;
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

    public Gimnasio() {
        ArchivoColeccionUtiles archivoColeccionUtiles = new ArchivoColeccionUtiles();
        ArchivoMapaUtiles archivoMapaUtiles = new ArchivoMapaUtiles();

        responsable = " ";
        direccion = " ";

        encargado = new Encargado("JavaGym", "21541044",EGenero.OTRO,"474-5698", "Avenida de los trabajadores 1005", "javaGym@gmail.com","Veni capo", "24-21541044-3");
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

    public HashMap<String, Cliente> getMapaCliente() {
        return mapaCliente;
    }

    public HashMap<String, Instructor> getMapaInstructor() {
        return mapaInstructor;
    }

    public TreeSet<Actividad> getArbolActividades() {
        return arbolActividades;
    }

    public String agregarFactura(Factura factura) {
        listaFacturas.add(factura);
        return factura.toString();
    }

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

    public String listarActividades() {
        return arbolActividades.toString();
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

    /**BLOQUE ABM*/
    /**AGREGAR  ==================================================================================*/
    public boolean agregar(Cliente cliente) throws ExistenteException {
        if(mapaCliente.containsKey(cliente.getDni())){
            throw new ExistenteException(cliente.getClass());
        }
        mapaCliente.put(cliente.getDni(), cliente);
        return true;
    }
    public boolean agregar(Actividad actividad) throws ExistenteException {
        if(!arbolActividades.add(actividad)){
            throw new ExistenteException(actividad.getClass());
        }
        return true;
    }
    public boolean agregar(Instructor instructor) throws ExistenteException {
        if(mapaInstructor.containsKey(instructor.getDni())){
            throw new ExistenteException(instructor.getClass());
        }
        mapaInstructor.put(instructor.getDni(), instructor);
        return true;
    }
    public boolean agregar(Factura factura) throws ExistenteException {
        if(!listaFacturas.add(factura)){
            throw new ExistenteException(factura.getClass());
        }
        return true;
    }

    /**MODIFICAR  ==================================================================================*/
    public boolean modificarCliente(String DNI, String nombre, String dni, EGenero genero, String telefono, String domicilio, String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento,
                                    String comentario, boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, TreeSet<Actividad> actividades_cliente ) throws NoEncontradoException {
        if(!mapaCliente.containsKey(DNI)){
            throw new NoEncontradoException(Cliente.class);
        }else{
            mapaCliente.get(DNI).modificar(nombre, dni,genero, telefono, domicilio, email,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario, alta_medica, solicito_rutina, debe, rutina, actividades_cliente);

            return true;
        }

    }

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
   /* public boolean modificarActividad(Actividad actividad, EtipoActividad nombre, String horario, ArrayList<EdiaSemana> listaDias1, String nombre_instructor,
                                      int cupo, int inscriptos, boolean disponible, String comentario, double precio_mensual) {
        Iterator<Actividad> it = arbolActividades.iterator();
        while (it.hasNext()) {
            Actividad actividad1 = it.next();
            if (actividad1.equals(actividad)) {
                Actividad actividad2 = new Actividad(nombre, horario, listaDias1, nombre_instructor,
                        cupo, inscriptos, disponible, comentario, precio_mensual);
                if (arbolActividades.contains(actividad2)) {
                    return false;
                } else {
                    actividad.modificar(nombre, horario, listaDias1, nombre_instructor,
                            cupo, inscriptos, disponible, comentario, precio_mensual);
                    return true;
                }
            }
        }
        return false;
    }*/
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


    /**DAR DE BAJA/ALTA  ==================================================================================*/

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

    public boolean banear(String dni) throws NoEncontradoException { ///Esto es banear/Desbanear
        if(mapaCliente.containsKey(dni)){
            mapaCliente.get(dni).setEstado(Eestado.BANEADO);
            return true;
        }else{
            throw new NoEncontradoException(Cliente.class);
        }
    }

    /**AGREGAR APERCIBIMIENTO  ==================================================================================*/

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

    public boolean agregarComentario(String DNI, String descripcion, LocalDate fecha) throws NoEncontradoException {
        String texto ="";
        if(mapaCliente.containsKey(DNI)){

           texto =  mapaCliente.get(DNI).getComentario();
           mapaCliente.get(DNI).setComentario(texto.concat("\n" + descripcion + "\n" + fecha.toString()));
            return true;

        } else if (mapaInstructor.containsKey(DNI)) {

            mapaInstructor.get(DNI).setComentario(descripcion);
            return true;
        }else{
            throw new NoEncontradoException(Persona.class);
        }
    }
    /**BORRAR ACTIVIDAD ======================================================================================*/
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
    /**========================================================================================================*/
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

    /**========================================================================================================*/
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
    public boolean agregar(Usuario usuario) throws ExistenteException {
        //metodo que agrega un usuario a la lista de usuarios. La excepcion es atrapada por la UI
        if (mapaUsuarios.containsKey(usuario.getUsuario())) {
            throw new ExistenteException(usuario.getClass());
        }
        mapaUsuarios.put(usuario.getUsuario(), usuario); //no es necesario el else ya que el throw rompe ejecucion
        return true;
    }

    //Sergio - Miercoles 14 de Junio
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
    //Mateo - Viernes 31 de Febrero
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

    /** Agrega Sergio 18/6

     Permite obtener informacion adicional del cliente
     @param dni del cliente a localizar
     @return una cadena de texto que contiene informacion adicional del cliente*/
    public String InformacionAdicionalCliente(String dni) throws NoEncontradoException {
        return LocalizarCliente(dni).MostrarInformacionAdicional();

    }
    /**
     Permite localizar un cliente a partir de su dni
     @param dni
     @return El cliente localizado a partir de su Dni*/
    public Cliente LocalizarCliente (String dni) throws NoEncontradoException {
        if (!mapaCliente.containsKey(dni))
        {
            throw new NoEncontradoException(Cliente.class);
        }
        return mapaCliente.get(dni);
    }

    /**
     Permite reconocer una actividad presente en el sistema(el metodo es llamado desde la interfaz para ubicar una actividad)
     @param actividad buscada
     @return actividad encontrada
     @throws ExistenteException, si no fue encontrada la actividad.
     */
    public Actividad buscarActividad(Actividad actividad) throws NoEncontradoException {
        Actividad actividad_encontrada = null;
        Iterator iterator = arbolActividades.iterator();
        while(iterator.hasNext()){
            Actividad actividad_en_arbol = (Actividad) iterator.next();
            if(actividad_en_arbol.equals(actividad)){
                actividad_encontrada = actividad_en_arbol;
            }

        }
        if(actividad_encontrada==null){
            throw new NoEncontradoException(Actividad.class);
        }
        return actividad_encontrada;
    }
    public double CalcularPrecio(ArrayList<Actividad> actividades_a_calcular){
        double precio_base = 2000; /*suponiendo que el precio base del gym es este. ver luego si esto sera un atributo del gym*/
        double precio_actividades=0;
        for(Actividad actividad : actividades_a_calcular){
            precio_actividades += actividad.getPrecio_mensual();
        }
        return precio_base + precio_actividades;//el precio que el cliente abonara en total
    }
    public void verificarPagos() {
        LocalDate fechaActual = LocalDate.now();

        for (Map.Entry<String, Cliente> entry : mapaCliente.entrySet()) {

            Cliente cliente = entry.getValue();

            LocalDate fechaPago = cliente.getFechaUltimoPago().plusMonths(1);

            boolean debePagar = fechaActual.isAfter(fechaPago) || fechaActual.isEqual(fechaPago);

            cliente.setDebe(debePagar);
        }
    }
    public void cobrar(Cliente cliente){
        Factura factura = cliente.pagar();
        listaFacturas.add(factura);
    }

    public boolean cobrar(String dni) throws ClienteDeudorException, NoEncontradoException{
        Cliente cliente = LocalizarCliente(dni);
        if(cliente!=null){ //si es nulo, se arroja excepcion
            if(!cliente.isDebe()){
                throw new ClienteDeudorException("El cliente no posee deuda");
            }
            else{
                //efectuar cobro
                Factura factura = cliente.pagar();
                listaFacturas.add(factura);
            }
        }

            return true;
    }



    /**Apartado estadisticas (Diego's playroom)*/

    /**Devuelve estadisticas por edad y genero*/
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
    /**Se envia un mes y devuelve ganancia del mismo. Se puede ampliar y hacer un prorrateo.*/
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

    public boolean agregarRutina(String dniCliente, String nombreInstructor, String descripcion) throws NoEncontradoException, ClienteDeudorException {
        Cliente cliente=LocalizarCliente(dniCliente);
        if (cliente.isDebe())
        {
            throw new ClienteDeudorException();
        }
            LocalDate fecha = LocalDate.now();
            String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);

            Rutina rutina=new Rutina(nombreInstructor,mes,descripcion);
            cliente.cambiarEstadoRutina();
            cliente.agregarRutina(rutina);

        return true;
    }

    public boolean pedidoDeRutina(String dni) throws RutinaYaPedida, NoEncontradoException {
        Cliente cliente=LocalizarCliente(dni);

        if (cliente.isSolicito_rutina())
        {
            throw new RutinaYaPedida();
        }
        else
        {
            cliente.pedidoRutina();
        }
        return true;
    }

    public boolean ConsultarClienteActivo(String dni) throws NoEncontradoException {
        boolean rta = false;
        Cliente cliente = LocalizarCliente(dni);
        if(cliente.getEstado()==Eestado.ACTIVO){
            rta = true;
        }
        return rta;
    }

    public String VerActividadesInstructor(String dni_instructor){
        String rta = "";
        if(mapaInstructor.containsKey(dni_instructor)){
            rta = mapaInstructor.get(dni_instructor).getActividadesACargo();
        }
        return rta;
    }




}
