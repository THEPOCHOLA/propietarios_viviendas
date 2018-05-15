/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import java.util.ArrayList;

/**
 *
 * @author daw
 */
public class Almacen {
    static ArrayList<Propietario> propietarios = new ArrayList<>();
    static ArrayList<Casa> casas = new ArrayList<>();
    
    public static ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }
    
    public static void agregarPropietario (Propietario p){
        propietarios.add(p);
    }
    
    public static Propietario buscarPropietario(String dni){
        int posicion = propietarios.indexOf(dni);
        return propietarios.get(posicion);
    }
    
    
    public static ArrayList<Casa> getCasas() {
        return casas;
    }
    
    public static void agregarCasa(Casa c){
        casas.add(c);
    }
    
    public static Casa buscarCasa(String id){
        int posicion = casas.indexOf(id);
        return casas.get(posicion);
    }
    
    
    
    
}
