/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author daw
 */
public class GestionDatos {
    
    public static boolean insertarPropietario( String DNI, String NOMBRE, String APELLIDOS ){
        boolean resultado = true;
        try (Connection con = AccederDatos.mysql(null,null,null) ){
            Statement st = con.createStatement();
            
            //se pone entre  comilla simple para que lo pille como string
            
            String dni = "'"+DNI+"'";
            String nombre = "'"+NOMBRE+"'";
            String apellidos = "'"+APELLIDOS+"'";
            
            String sql = "INSERT INTO Propietarios" +
                            "(" +
                            "   id_propietario_dni, " +
                            "   nombre, " +
                            "   apellidos " + 
                            ") " +
                            "VALUES (" +
                                dni + ", " +
                                nombre + ", " +
                                apellidos  +
                            ")" ; 

            st.executeUpdate(sql);  //devuelve boolean
            st.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public static ArrayList<Dato> mostrarDatos(){
        String dni = "";
        String nombre = "";
        String apellidos = "";
        ArrayList<Dato> salida = new ArrayList<Dato>();
        try (Connection con = AccederDatos.mysql(null,null,null) ){
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM Propietarios");
            while(res.next()){
                dni = res.getString("id_propietario_dni");
                nombre = res.getString("nombre");
                apellidos = res.getString("apellidos");
                salida.add(new Dato(dni, nombre, apellidos));
            }
            st.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salida;
    }
}
