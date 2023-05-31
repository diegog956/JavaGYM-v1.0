package model.Otros;

import java.time.LocalDate;
import java.util.Objects;

public class Factura {
    private int mes;

    private int anio;
    private String datos_cliente;
    private LocalDate fecha_de_emision;
    private double monto;

    public Factura(int mes, int anio, String datos_cliente, LocalDate fecha_de_emision, double monto) {
        this.anio=anio;
        this.mes=mes;
        this.datos_cliente = datos_cliente;
        this.fecha_de_emision = fecha_de_emision;
        this.monto = monto;
    }

    public Factura ()
    {
        anio=0;
        mes=0;
        datos_cliente=" ";
        fecha_de_emision=null; ///DUDA ACA.
        monto=0;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public String getDatos_cliente() {
        return datos_cliente;
    }

    public LocalDate getFecha_de_emision() {
        return fecha_de_emision;
    }

    public double getMonto() {
        return monto;
    }


    @Override
    public String toString() {
        return "Factura{" +
                "mes=" + mes +
                ", anio=" + anio +
                ", datos_cliente='" + datos_cliente + '\'' +
                ", fecha_de_emision=" + fecha_de_emision +
                ", monto=" + monto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return mes == factura.mes && anio == factura.anio && Double.compare(factura.monto, monto) == 0 && Objects.equals(datos_cliente, factura.datos_cliente) && Objects.equals(fecha_de_emision, factura.fecha_de_emision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mes, anio, datos_cliente, fecha_de_emision, monto);
    }
}

