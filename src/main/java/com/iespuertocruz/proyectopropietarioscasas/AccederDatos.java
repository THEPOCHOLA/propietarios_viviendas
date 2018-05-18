/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuertocruz.proyectopropietarioscasas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daw
 */
public class AccederDatos {
    
    static Connection conexion=null;
    
    static public Connection mysql(String url, String user, String pass) throws SQLException{

        conexion=null;
        //url que corresponda con el nombre de la base de datos
//        Clase
        String jdbcUrl = "jdbc:mysql://172.19.99.30/PropietariosViviendasDB?serverTimezone=UTC";
//        Casa Iván
//        String jdbcUrl = "jdbc:mysql://192.168.1.147/PropietariosViviendasDB?serverTimezone=UTC";
//        Casa Bea
//        String jdbcUrl = "jdbc:mysql://192.168.56.101/PropietariosViviendasDB?serverTimezone=UTC";
        String usuario = "root";
        String clave = "1q2w3e4r";
        
        if(url != null && !url.isEmpty())
            jdbcUrl = url;

        if(user != null && !user.isEmpty()){
            usuario = user;
        }


        if(pass != null && !pass.isEmpty()){
            clave = pass;
        }
      
        Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);

        System.out.println("Conectado a mysql!");
        conexion = con;


        return conexion;

    }
}
