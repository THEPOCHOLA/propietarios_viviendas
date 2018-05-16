/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement(name = "casa")
public class Casa {
    
    @XmlElement(name = "ID")
    int identificador;
    @XmlElement(name = "direccion")
    String direccion;
    @XmlElement(name = "metros")
    int metros;
    @XmlElement(name = "planta")
    int planta;
    @XmlElement(name = "ascensor")
    boolean ascensor;
    @XmlElement(name = "garaje")
    boolean garaje;
    @XmlElement(name = "precio")
    double precio;
    
    private ArrayList<Propietario> misPropietarios;

    

    public ArrayList<Propietario> getPropietarios() {
        return misPropietarios;
    }
    
    public void agregarPropietario(Propietario p){
        misPropietarios.add(p);
    }
    
    public Casa() {
    } //necesitamos un constructor por defecto para marshall

    public Casa(int identificador, String direccion, int metros, int planta, boolean ascensor,
        double precio) {
        this.identificador = identificador;
        this.direccion = direccion;
        this.metros = metros;
        this.planta = planta;
        this.ascensor = ascensor;
        this.precio = precio;
    }
}
