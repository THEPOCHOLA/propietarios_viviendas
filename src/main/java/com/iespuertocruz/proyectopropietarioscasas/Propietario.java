/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Beatriz García
 */
@XmlRootElement(name = "propietario")
public class Propietario {

    @XmlElementWrapper
    @XmlElement(name = "casa")
    public ArrayList<Casa> misCasas;

    
    @XmlElement(name = "nombre")
    String nombre;
    @XmlElement(name = "apellidos")
    String apellidos;
    @XmlElement(name = "dni")
    String dni;
    

    
    
    public ArrayList<Casa> getCasas() {
        return misCasas;
    }
    
    public void agregarCasa (Casa c){
        misCasas.add(c);
    }
    
    
//la librería xml precisa de un constructor sin parámetros

    public Propietario() {
    }

    public Propietario(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.misCasas = new ArrayList<Casa>();
    }

    @Override
    public String toString() {
        return dni;
    }
    
    
}

