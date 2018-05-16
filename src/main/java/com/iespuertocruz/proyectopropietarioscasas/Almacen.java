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
    
    public static Propietario agregarPropietario (Propietario p){
        propietarios.add(p);
        return p;
    }
    
    public static int buscarPropietario(String dni){
        
        int i = 0;
        int resultado = -1;
        for (Propietario propietario : propietarios) {
            //El equals es lo que hace que funcione!!!!!!!!!!!!!!!!!!!!
            if(propietario.dni.equals(dni)){
                resultado = i;
            }
            System.out.println(propietarios.get(i));
            i++;
        }
        return resultado;
        
//        int posicion = propietarios.indexOf(dni);
//        return propietarios.get(posicion);
    }
    
    
    public static ArrayList<Casa> getCasas() {
        return casas;
    }
    
    public static Casa agregarCasa(Casa c){
        casas.add(c);
        return c;
    }
    
    public static int buscarCasa(int id){
        int i = 0;
        int resultado = -1;
        for (Propietario propietario : propietarios) {
            //El equals es lo que hace que funcione!!!!!!!!!!!!!!!!!!!!
            if(propietario.dni.equals(id)){
                resultado = i;
            }
            System.out.println(propietarios.get(i));
            i++;
        }
        return resultado;
//        int posicion = casas.indexOf(id);
//        return casas.get(posicion);
    }
    
    
    
    
}
