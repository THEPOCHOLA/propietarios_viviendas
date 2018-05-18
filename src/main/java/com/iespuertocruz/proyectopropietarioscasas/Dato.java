/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    //--------------------------------------------------------------------------------------------------------------------------------------------------.
    //                                                                      VIVIENDAS
    public SimpleIntegerProperty identificador;
    public SimpleStringProperty direccion;
    public SimpleIntegerProperty metrosCuadrados;
    public SimpleDoubleProperty precio;
    public SimpleIntegerProperty garaje;
    public SimpleIntegerProperty ascensor;

    public Dato(int identificador, String direccion, int metrosCuadrados, double precio, int garaje, int ascensor) {
        this.identificador = new SimpleIntegerProperty(identificador);
        this.direccion = new SimpleStringProperty(direccion);
        this.metrosCuadrados = new SimpleIntegerProperty(metrosCuadrados);
        this.precio = new SimpleDoubleProperty(precio) ;
        this.garaje = new SimpleIntegerProperty(garaje);
        this.ascensor = new SimpleIntegerProperty(ascensor);
    }
    
    
    
    public int getIdentificador() {
        return identificador.get();
    }

    public String getDireccion() {
        return direccion.get();
    }
    
    public void setIdentificador(int identificador) {
        this.identificador.set(identificador);
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }
    
    public int getMetrosCuadrados() {
        return metrosCuadrados.get();
    }
    
    public double getPrecio() {
        return precio.get();
    }
    
    public int getGaraje() {
        return garaje.get();
    }
    
    public int getAscensor() {
        return ascensor.get();
    }
    
    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados.set(metrosCuadrados);
    }
    
    public void setPrecio(double precio) {
        this.precio.set(precio);
    }
    
    public void setGaraje(int garaje) {
        this.garaje.set(garaje);
    }
    
    public void setAscensor(int ascensor) {
        this.ascensor.set(ascensor);
    }
}
