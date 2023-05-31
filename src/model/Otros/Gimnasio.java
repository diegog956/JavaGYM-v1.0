package model.Otros;

import model.Persona.Cliente;
import model.Persona.Instructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Gimnasio {
    private String responsable;
    private String direccion;
    private Conjunto<String,Cliente> mapaCliente;
    private Conjunto<String, Instructor> mapaInstructor;
    private ArrayList<Factura>listaFacturas;

    public Gimnasio(String responsable, String direccion) {
        this.responsable = responsable;
        this.direccion = direccion;
        mapaCliente = new Conjunto<>();
        mapaInstructor = new Conjunto<>();
        listaFacturas = new ArrayList<>();
    }

    public Gimnasio()
    {
        responsable=" ";
        direccion=" ";
        mapaCliente=new Conjunto<>();
        mapaInstructor=new Conjunto<>();
        listaFacturas=new ArrayList<>();
    }

    public String getResponsable() {
        return responsable;
    }

    public String getDireccion() {
        return direccion;
    }
}
