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
}
