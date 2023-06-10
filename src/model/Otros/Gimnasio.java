package model.Otros;

import model.ActivYrutina.Actividad;
import model.Genericos.GestionadorMapa;
import model.Genericos.GestionadorLinkedHashSet;
import model.Persona.Cliente;
import model.Personal.Instructor;
import model.Personal.Encargado;
import model.Personal.Usuario;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;


public class Gimnasio {
    private String responsable;
    private String direccion;
    /**Ver Bloc de notas. Se puede pensar en que el encargado contenga los datos del gimnasio, tales como mail, direccion, etc.*/
    private GestionadorMapa<String,Cliente> mapaCliente;
    private GestionadorMapa<String, Instructor> mapaInstructor;
    private GestionadorLinkedHashSet<Factura> listaFacturas;
    private TreeSet<Actividad>arbolActividades;/**GestionadorTreeSet creado. Ver su implementacion.*/

    private final String usuario_admin="admin2023";
    private final String usuario_encargado="encargado2023";
    private final String contrasenia_admin="admin123";
    private final String contrasenia_encargado="encargado123";
    public Gimnasio(String responsable, String direccion) {
        this.responsable = responsable;
        this.direccion = direccion;
        mapaCliente = new GestionadorMapa<>();
        mapaInstructor = new GestionadorMapa<>();
        listaFacturas = new GestionadorLinkedHashSet<>();
        arbolActividades=new TreeSet<>();
    }

    public Gimnasio()
    {
        responsable=" ";
        direccion=" ";
        mapaCliente=new GestionadorMapa<>();
        mapaInstructor=new GestionadorMapa<>();
        listaFacturas=new GestionadorLinkedHashSet<>();
        arbolActividades=new TreeSet<>();
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
}
