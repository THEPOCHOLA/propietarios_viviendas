/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ivan
 */
public class Dato {
    public SimpleStringProperty dni;
    public SimpleStringProperty nombre;
    public SimpleStringProperty apellidos;

    public Dato(String dni, String nombre, String apellidos) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
//        this.importeCuota = importeCuota;
//        this.intereses = intereses;
//        this.amortizado = amortizado;
//        this.pendienteDePago = pendienteDePago;
    }

    public String getDni() {
        return dni.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    
}
