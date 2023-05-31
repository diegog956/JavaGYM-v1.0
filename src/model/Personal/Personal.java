package model.Personal;

import model.ActivYrutina.Actividad;
import model.Otros.Factura;
import model.Persona.Cliente;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Personal {
    private String usuario;
    private String contrasenia;

    public Personal()
    {
        usuario=" ";
        contrasenia=" ";
    }

    public Personal(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personal personal = (Personal) o;
        return Objects.equals(usuario, personal.usuario) && Objects.equals(contrasenia, personal.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }

    public boolean verificarContrasenia(String viejaContrasenia, String nuevaContrasenia)
    {
        boolean rta=false;
        if (viejaContrasenia.equals(getContrasenia()))
        {
            contrasenia=nuevaContrasenia;
            rta=true;
        }
        return rta;
    }


    public double calcularCuota (HashSet<Actividad> setActividades) {
        double cuota = 0;
        int cantidad_actividades = 0;

        Iterator<Actividad> it = setActividades.iterator();

        while (it.hasNext()) {
            cantidad_actividades++;
            cuota += it.next().getPrecio_mensual();
        }

        if (cantidad_actividades == 1) {
            cuota = cuota * 0.95;
        } else if (cantidad_actividades == 2) {
            cuota = cuota * 0.90;
        } else if (cantidad_actividades >= 3) {
            cuota = cuota * 0.8;
        }
        return cuota;
    }

    public Factura cobrarCuota(Cliente cliente){
        LocalDate mes=null;
        LocalDate anio=null;

        double cuota = calcularCuota(cliente.getHashDeActividades());
        String dato_cliente = cliente.getNombre() + "\n" + cliente.getDni() + "\n" + cliente.getTelefono();
        Factura factura = new Factura(mes.getMonthValue(),anio.getYear(), dato_cliente, LocalDate.now(), cuota);
        cliente.agregarFactura(factura);

        return factura;
    }

}
