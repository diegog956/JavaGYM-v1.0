package model.Persona;

import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Otros.Factura;
import model.Persona.Persona;

import java.time.LocalDate;
import java.util.*;

public class Cliente extends Persona {
    private boolean alta_medica;
    private boolean solicito_rutina;
    private boolean debe;
    private HashSet<Rutina> hashSetRutinas;
    private ArrayList<Factura>listaFacturas;
    private HashSet<Actividad>hashDeActividades; ///ID concatenado para q se ordenen por actividades

    public Cliente()
    {
        super();
        alta_medica=true;
        solicito_rutina=true;
        debe=true;
        hashSetRutinas=new HashSet<>();
        listaFacturas=new ArrayList<>();
        hashDeActividades=new HashSet<>();
    }

    public Cliente(String nombre, String dni, String telefono, ArrayList<Apercibimiento> listaApercibimientos, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, boolean alta_medica1, boolean solicito_rutina, boolean debe) {
        super(nombre, dni, telefono, listaApercibimientos, estado, grupo_sanguineo, contacto_emergencia, obra_social, alta_medica, fecha_nacimiento);
        this.alta_medica = alta_medica1;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        hashSetRutinas=new HashSet<>();
        listaFacturas=new ArrayList<>();
        hashDeActividades=new HashSet<>();
    }

    public boolean agregarFactura(Factura factura){
        listaFacturas.add(factura);
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

    public HashSet<Rutina> getHashSetRutinas() {
        return hashSetRutinas;
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public HashSet<Actividad> getHashDeActividades() {
        return hashDeActividades;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "alta_medica=" + alta_medica +
                ", solicito_rutina=" + solicito_rutina +
                ", debe=" + debe +
                ", hashSetRutinas=" + hashSetRutinas +
                ", listaFacturas=" + listaFacturas +
                ", hashDeActividades=" + hashDeActividades +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return alta_medica == cliente.alta_medica && solicito_rutina == cliente.solicito_rutina && debe == cliente.debe && Objects.equals(hashSetRutinas, cliente.hashSetRutinas) && Objects.equals(listaFacturas, cliente.listaFacturas) && Objects.equals(hashDeActividades, cliente.hashDeActividades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alta_medica, solicito_rutina, debe, hashSetRutinas, listaFacturas, hashDeActividades);
    }
}
