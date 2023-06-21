package model.Persona;
import excepciones.ExistenteException;
import excepciones.NoEncontradoException;
import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGenero;
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
import java.time.format.TextStyle;
import java.util.*;
/**Clase que hereda de persona. Pensada para aquel que asiste al gimnasio y realiza las actividades del mismo.*/
public class Cliente extends Persona implements I_toJson, Serializable {
    private boolean alta_medica;
    private boolean solicito_rutina;
    private boolean debe;
    private Rutina rutina;
    private LocalDate fechaUltimoPago;
    private LocalDate fecha_de_inscripcion;
    private LinkedHashSet<Factura> listaFacturas;
    private TreeSet<Actividad> actividades_cliente;

/**Constructor vacio del mismo.*/
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

/**Constructor con todos los atributos del Cliente. Excepto coleccion de actividades y facturas.*/
    public Cliente(String nombre, String dni, EGenero genero, String telefono, String domicilio, String email, LocalDate fecha_de_inscripcion, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, boolean solicito_rutina, boolean debe) {
        super(nombre, dni,genero, telefono, domicilio,email ,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        rutina = null;
        fechaUltimoPago = fecha_de_inscripcion;/**La fecha de ultimo pago cuando se crea el cliente es la de inscripcion(paga al instante)*/
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }

/**Constructor utilizado para el metodo fromJson. Invoca al metodo fromJson de la clase padre.
 * @param jo JSONObject que tiene la informacion de un cliente.*/
    public Cliente(JSONObject jo) throws JSONException {
        super(jo);
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }

 /** Constructor que recibe la lista de actividades por parametro. La fecha de ultimo pago
  *  se considera que cuando se crea el cliente es la de inscripcion. Se considera el pago al instante.*/
    public Cliente(String nombre, String dni,EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, LocalDate fecha_de_inscripcion, TreeSet<Actividad> actividades_cliente) {
        super(nombre, dni,genero, telefono, domicilio, email,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        this.rutina = rutina;
        fechaUltimoPago = fecha_de_inscripcion;
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        this.actividades_cliente = actividades_cliente;
    }

    /**Agrega factura a la lista de facturas.
     * @param factura Factura a agregar.
     * @return true si fue agregado exitosamente.
     * @throws ExistenteException Si ya se
     * encontraba la factura en la base de datos*/
    public boolean agregarFactura(Factura factura) throws ExistenteException {
        if(!listaFacturas.add(factura)){
            throw new ExistenteException(factura.getClass());
        }
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
    public LocalDate getFechaUltimoPago() {
        return fechaUltimoPago;
    }
    public LocalDate getFecha_de_inscripcion() {
        return fecha_de_inscripcion;
    }
    public Rutina getRutina() {
        return rutina;
    }

    private void setAlta_medica(boolean alta_medica) {
        this.alta_medica = alta_medica;
    }
    private void setSolicito_rutina(boolean solicito_rutina) {
        this.solicito_rutina = solicito_rutina;
    }
    public void setDebe(boolean debe) {
        this.debe = debe;
    }
    private void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }
    private void setFecha_de_inscripcion(LocalDate fecha_de_inscripcion) {
        this.fecha_de_inscripcion = fecha_de_inscripcion;
    }
    public void setActividades_cliente(TreeSet<Actividad> actividades_cliente) {
        this.actividades_cliente = actividades_cliente;
    }
    public void setFechaUltimoPago(LocalDate fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    /**Metodo que devuelve el cliente debe, en un formato de texto legible para cualquier usuario.
    @return true si el cliente debe, false si no lo hace. */
    public String getDebe(){
        String rta = "No";
        if(isDebe()){
            rta = "Si";
        }
        return rta;
    }
    /**Metodo que devuelve el cliente tiene el alta medica, en un formato de texto legible para cualquier usuario.
     @return true si la posee debe, false si no. */
    public String getAltaMedica(){
        String rta = "No";
        if(isAlta_medica()){
            rta = "Si";
        }
        return rta;
    }
    /**Metodo que devuelve si el cliente solicito rutina, en un formato de texto legible para cualquier usuario.
     @return true si la ha solicitado, false si no. */
    public String getSolicitoRutina(){
        String rta = "No";
        if(isSolicito_rutina()){
            rta = "Si";
        }
        return rta;
    }

    /**Muestra la informacion del cliente.
     * @return La informacion del cliente en formato String.*/
    @Override
    public String toString() {
        if(rutina==null){
            rutina = new Rutina();
        }
        if(listaFacturas==null){
            listaFacturas = new LinkedHashSet<>();
        }
        return  super.toString() + "Cliente{" +
                "alta_medica=" + alta_medica +
                ", solicito_rutina=" + solicito_rutina +
                ", debe=" + debe +
                ", fecha de ultimo pago=" + fechaUltimoPago +
                ", fecha de inscripcion=" + fecha_de_inscripcion +
                ", Rutinas=" + rutina.toString() +
                ", listaFacturas=" + listaFacturas.toString() +
                ", hashDeActividades=" + actividades_cliente.toString() +
                "\n";
    }

    /**Permite crear un JSONObject con la informacion de un cliente.
     * @return JSONObject con la informacion del cliente en él.
     * @throws JSONException si se coloca clave nula en el metodo 'put'.*/
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();

        jsonObject.put("Solicita rutina", isSolicito_rutina());
        jsonObject.put("Alta Medica", isAlta_medica());
        jsonObject.put("Debe", isDebe());
        jsonObject.put("Fecha de Inscripcion", getFecha_de_inscripcion().toString());
        jsonObject.put("Fecha de ultimo pago", getFechaUltimoPago().toString());

        if(rutina !=null) {
            jsonObject.put("Rutina", rutina.toJsonObj());
        }

        if(listaFacturas==null){
            listaFacturas = new LinkedHashSet<>();
        }

        JSONArray jsonArray = new JSONArray();
        int i =0;
        Iterator<Factura> it = listaFacturas.iterator();
        while(it.hasNext()){
            jsonArray.put(i,it.next().toJsonObj());
            i++;
        }
        jsonObject.put("Facturas", jsonArray);


        JSONArray jsonArray1 = new JSONArray();
        Iterator<Actividad> it2 = actividades_cliente.iterator();
        i=0;
        while(it2.hasNext()){
            jsonArray1.put(i, it2.next().toJsonObj());
            i++;
        }
        jsonObject.put("Actividades", jsonArray1);

        return jsonObject;
    }

    /**Permite crear un cliente a partir de la informacion contenida en un JSONObject.
     * @return Un objecto de la clase Cliente.*/
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
        /**Agregue esto ---------------------------------------------------------------------------*/
        String fecha_string2 = jo.getString("Fecha de ultimo pago");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha2 = LocalDate.parse(fecha_string2, formatter2);

        cliente.setFechaUltimoPago(fecha2);
        /**------------------------------------------------------------------------------------------*/

        if(jo.has("Rutina")) {
            Rutina rutina = new Rutina();
            cliente.setRutina(rutina.fromJson(jo.getJSONObject("Rutina")));
        }
        JSONArray jsonArray = jo.getJSONArray("Facturas");
        for(int i=0; i<jsonArray.length();i++){
            Factura factura = new Factura();
            cliente.listaFacturas.add(factura.fromJson(jsonArray.getJSONObject(i)));
        }

        JSONArray jsonArray2 = jo.getJSONArray("Actividades");
        for(int i=0; i<jsonArray2.length();i++){
            Actividad actividad = new Actividad();
            cliente.actividades_cliente.add(actividad.fromJson(jsonArray2.getJSONObject(i)));
        }
        return cliente;
    }


    /**Agrega actividad a el set de actividades del cliente.
     * @param actividad Actividad a agregar.
     * @return true si fue agregado exitosamente
     * @throws ExistenteException Si ya se
     * encontraba la actividad en la lista de actividades del cliente.*/
    public boolean agregarActividad(Actividad actividad) throws ExistenteException {
        if(!actividades_cliente.add(actividad)){
            throw new ExistenteException(actividad.getClass());
        }
        return true;
    }

    /**Permite obtener una cadena de texto que incluya informacion adicional del cliente
     * @return Informacion del cliente en formato String.*/
    public String MostrarInformacionAdicional(){
        String rta = "";
        if(getEstado().toString().equals("INACTIVO")){
            rta += "CLIENTE INACTIVO\n";}

        if(getCantidadApercibimientos()>0){

            rta += "\nApercibimientos:\n" + DescripcionApercibimientos() + "\n";
        }

        if (!isAlta_medica()){
            rta += "\nAlta Médica Pendiente" + "\n";
        }

        if(!getComentario().equals("")){
            rta += "\nComentario/s:" + getComentario() + "\n";
        }

        if(actividades_cliente.size()>0){ //si tiene actividades las listamos
            rta+= "Inscripto en:" + listarActividades();
        }

        rta+= "\n\nContacto de Emergencia: " + getContacto_emergencia() + "\n" +
                "Obra Social: " + getObra_social() + "\n" +
                "Grupo Sanguíneo: " + getGrupo_sanguineo().toString() + "\n" +
                "Fecha de Inscripción: " + getFecha_de_inscripcion().toString();

        return rta;
    }

    /**Lista actividades del cliente.
     * @return Las actividades del cliente en formato String.*/
    public String listarActividades(){
        String rta = "";
        for(Actividad actividad:actividades_cliente){
            rta += actividad.MostrarActividadBasica() + "\n";
        }
        return rta;
    }


    /**Metodo encargado del pago de la cuota segun actividades que realice el cliente.
     * Agrega la factura a la lista de facturas individual y cambia el estado deudor asi como tambien
     * la fecha del ultimo pago.
     * @return Factura de la operacion.*/
    public Factura pagar(){
        double monto = 0;

        Iterator<Actividad> it = actividades_cliente.iterator();

        while(it.hasNext()){
            monto += it.next().getPrecio_mensual();
        }

        LocalDate fecha = LocalDate.now();
        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);

        Factura factura = new Factura(mes, String.valueOf(LocalDate.now().getYear()), getDni(), getNombre(), LocalDate.now(),monto);
        setDebe(false);
        setFechaUltimoPago(LocalDate.now());
        listaFacturas.add(factura);

        return factura;
    }

    /**Permite la modificacion de los datos de un cliente.*/
    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio, String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario,  boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, TreeSet<Actividad> actividades_cliente) {
        super.modificar(nombre, dni, genero, telefono, domicilio, email, estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        setAlta_medica(alta_medica);
        setSolicito_rutina(solicito_rutina);
        setDebe(debe);
        setRutina(rutina);
        setActividades_cliente(actividades_cliente);
    }

    /**Elimina actividad que realiza un cliente de su lista de actividades.
     * @param actividad Actividad a remover.
     * @return true si la operacion fue exitosa.
     * @throws NoEncontradoException si no encuentra la actividad en la lista de actividades
     * del cliente.*/
    public boolean borrarActividad(Actividad actividad) throws NoEncontradoException {
        if(!actividades_cliente.contains(actividad)){
            throw new NoEncontradoException(actividad.getClass());
        }
        actividades_cliente.remove(actividad);
        return true;
    }

    /**Cambia el estado de la solicitud de rutina a false.*/
    public void cambiarEstadoRutina()
    {
        solicito_rutina=false;
    }

    /**Redefine la rutina existente del cliente.*/
    public void agregarRutina(Rutina rutinaNueva)
    {
        rutina=rutinaNueva;
    }

    /**Cambia el estado de la solicitud de rutina a true.*/
    public void pedidoRutina()
    {
        solicito_rutina=true;
    }

}
