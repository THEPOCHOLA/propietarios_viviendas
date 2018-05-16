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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author daw
 */
public class GestionDatos {
    
    public static boolean insertarPropietario( String DNI, String NOMBRE, String APELLIDOS ){
        boolean resultado = false;
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
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("Ya existe ese usuario en la base de datos.");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
            
        }
        return resultado;
    }
    

    public static ArrayList<Dato> mostrarDatosPropietarios(){
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
    
    public static boolean borrarPropietario (Propietario p){
        String dni = "'"+p.dni+"'";
        boolean resultado = false;
        try (Connection con = AccederDatos.mysql(null,null,null)){
            Statement st = con.createStatement();
//            String sql = "DELETE FROM PropietariosCasas"
//                    + "WHERE ref_propietario = "
//                    + dni;
//            st.executeUpdate(sql);
//            st.close();
            
            
//            st = con.createStatement();
            String sql = "DELETE FROM Propietarios " +
                            "WHERE id_propietario_dni = " +
                            dni;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }
    
//    public static boolean modificarPopietario (String identificador, String DNI, String NOMBRE, String APELLIDOS){
//        String ident = "'" + identificador + "'";
//        String dni = "'" + DNI + "'";
//        String nombre = "'" + NOMBRE + "'";
//        String apellidos = "'" + APELLIDOS + "'";
//        
//        boolean resultado = false;
//        try (Connection con = AccederDatos.mysql(null, null, null)){
//            Statement st = con.createStatement();
//            String sql = "UPDATE Propietarios " +
//                            "SET " +
//                            "id_propietario_dni = "+dni+","+
//                            "nombre = "+nombre+","+
//                            "apellidos = "+apellidos+ 
//                            " WHERE Propietarios . id_propietario_dni = "+ident;
//            st.executeUpdate(sql);
//            st.close();
//            resultado = true;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return resultado;
//    }
    
    public static boolean anhadirVivienda(String direccion, int metrosCuadrados, double precio, boolean ascensor, boolean garaje){
        
//        try (Connection con = AccederDatos.mysql(null,null,null) ){
//            Statement st = con.createStatement();
//            
//            //se pone entre  comilla simple para que lo pille como string
//            
//            String dni = "'"+DNI+"'";
//            String nombre = "'"+NOMBRE+"'";
//            String apellidos = "'"+APELLIDOS+"'";
//            
//            String sql = "INSERT INTO Propietarios" +
//                            "(" +
//                            "   id_propietario_dni, " +
//                            "   nombre, " +
//                            "   apellidos " + 
//                            ") " +
//                            "VALUES (" +
//                                dni + ", " +
//                                nombre + ", " +
//                                apellidos  +
//                            ")" ; 
//
//            st.executeUpdate(sql);  //devuelve boolean
//            st.close();
//            resultado = true;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
//            dialogoAyuda.setTitle("ERROR");
//            dialogoAyuda.setHeaderText(null);
//            dialogoAyuda.setContentText("Ya existe ese usuario en la base de datos.");
//            dialogoAyuda.initStyle(StageStyle.UTILITY);
//            dialogoAyuda.showAndWait();
//            
//        }
//        return resultado;
        
        boolean resultado = false;
        
        try (Connection con = AccederDatos.mysql(null, null, null)){
            Statement st = con.createStatement();
            
            
        } catch (SQLException ex) {
            
        }
        
        
        return resultado;
    }
}
