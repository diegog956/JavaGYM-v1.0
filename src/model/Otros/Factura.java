package model.Otros;

import java.time.LocalDate;

public class Factura {
    private String mes_y_anio;
    private String datos_cliente;
    private LocalDate fecha_de_emision;
    private double monto;

    public Factura(String mes_y_anio, String datos_cliente, LocalDate fecha_de_emision, double monto) {
        this.mes_y_anio = mes_y_anio;
        this.datos_cliente = datos_cliente;
        this.fecha_de_emision = fecha_de_emision;
        this.monto = monto;
    }

    public Factura ()
    {
        mes_y_anio=" ";
        datos_cliente=" ";
        fecha_de_emision=null; ///DUDA ACA.
        monto=0;
    }

    public String getMes_y_anio() {
        return mes_y_anio;
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
}
