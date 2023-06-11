package model.Personal;

import model.ActivYrutina.Actividad;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Otros.Factura;
import model.Persona.Cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Usuario extends Personal implements Serializable {
    private String usuario;
    private String contrasenia;



    public Usuario()
    {
        /*usuario=" ";
        contrasenia=" ";*/
        this("","","","",null,null,"","",false,null,"","","","");
    }

    public Usuario(String nombre, String dni, String telefono, String domicilio, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre,dni,telefono,domicilio,estado,grupo_sanguineo,contacto_emergencia,obra_social,alta_medica,fecha_nacimiento,comentario,CUIL);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String user, String contra) {
        this("","","","",null,null,"","",false,null,"","",user,contra);
    }

    private String getUsuario() {
        return usuario;
    }

    private String getContrasenia() {
        return contrasenia;
    }

    public boolean verificarContraseniaYusuario(String user, String contra)
    {
        return (usuario.equals(user) && contrasenia.equals(contra));
    }


    /**To string del usuario, donde muesetra usuario y contraseña???*/
    @Override
    public String toString() {
        return super.toString() +"Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                "} " ;
    }

    @Override
    public boolean equals(Object o) {
        boolean rta = false;
        if(o!=null){
            if(o instanceof Usuario){
                Usuario aux = (Usuario) o;
                if(aux.getUsuario().equals(getUsuario()) && aux.getContrasenia().equals(getContrasenia())){
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

    public boolean cambiarContrasenia(String viejaContrasenia, String nuevaContrasenia)
    {
        boolean rta=false;
        if (viejaContrasenia.equals(getContrasenia()))
        {
            contrasenia=nuevaContrasenia;
            rta=true;
        }
        return rta;
    }

    /** Ver el boton "aplicar descuento". Se puede solicitar el descuento y facturar desde alli. */
    public double calcularCuota (HashSet<Actividad> setActividades) {
        double cuota = 0;
        int cantidad_actividades = 0;

        Iterator<Actividad> it = setActividades.iterator();

        while (it.hasNext()) {
            cantidad_actividades++;
            cuota += it.next().getPrecio_mensual();
        }

        return cuota;
    }

    /*public Factura cobrarCuota(Cliente cliente, String mes, String anio){

        double cuota = calcularCuota(cliente.getHashDeActividades());
        String dato_cliente = cliente.getNombre() + "\n" + cliente.getDni() + "\n" + cliente.getTelefono();
        Factura factura = new Factura(mes,anio, dato_cliente, LocalDate.now(), cuota);
        cliente.agregarFactura(factura);

        return factura;
    }*/

}
