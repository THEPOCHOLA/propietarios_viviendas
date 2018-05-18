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
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author daw
 */
public class GestionDatos {

    public static boolean insertarPropietario(String DNI, String NOMBRE, String APELLIDOS) {
        boolean resultado = false;
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();

            //se pone entre  comilla simple para que lo pille como string
            String dni = "'" + DNI + "'";
            String nombre = "'" + NOMBRE + "'";
            String apellidos = "'" + APELLIDOS + "'";

            String sql = "INSERT INTO Propietarios"
                    + "("
                    + "   id_propietario_dni, "
                    + "   nombre, "
                    + "   apellidos "
                    + ") "
                    + "VALUES ("
                    + dni + ", "
                    + nombre + ", "
                    + apellidos
                    + ")";

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

    public static ArrayList<Dato> mostrarDatosPropietarios() {
        String dni = "";
        String nombre = "";
        String apellidos = "";
        ArrayList<Dato> salida = new ArrayList<Dato>();
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM Propietarios");
            while (res.next()) {
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
        
    public static boolean borrarPropietario(Propietario p) {
        String dni = "'" + p.dni + "'";
        String ref_propietario = "";
        String sql;
        boolean resultado = false;
        try(Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            try {
                ResultSet res = st.executeQuery("SELECT ref_propietario FROM PropietariosCasas WHERE ref_propietario = " + dni);
                res.next();
                ref_propietario = "'"+res.getString("ref_propietario")+"'";
                try{
                    if(ref_propietario.equals(dni)){
                        st = con.createStatement();
                        sql = "DELETE FROM PropietariosCasas"
                        + " WHERE ref_propietario = "
                        + dni;
                        System.out.println(sql);
                        st.executeUpdate(sql);
                    }
                }catch(SQLException ex){
                    System.out.println("No se ha eliminado de PropietariosCasas");
                }finally{
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No está en propietarioscasas");
            }finally{
                st.close();
            }
            st = con.createStatement();
            sql = "DELETE FROM Propietarios"
                    + " WHERE id_propietario_dni = "
                    + dni;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;

//        String dni = "'" + p.dni + "'";
//        boolean resultado = false;
//        try (Connection con = AccederDatos.mysql(null, null, null)) {
//            Statement st = con.createStatement();
//            String sql = "DELETE FROM PropietariosCasas"
//                    + " WHERE ref_propietario = "
//                    + ;
//            st.executeUpdate(sql);
//            st.close();
//
//            st = con.createStatement();
//            sql = "DELETE FROM Propietarios"
//                    + " WHERE id_propietario_dni = "
//                    + dni;
//            System.out.println(sql);
//            st.executeUpdate(sql);
//            st.close();
//            resultado = true;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return resultado;
    }

    public static boolean modificarPopietario(String DNI, String NOMBRE, String APELLIDOS) {
        String dni = "'" + DNI + "'";
        String nombre = "'" + NOMBRE + "'";
        String apellidos = "'" + APELLIDOS + "'";

        boolean resultado = false;
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            String sql = "UPDATE Propietarios "
                    + "SET "
                    + "nombre = " + nombre + ", "
                    + "apellidos = " + apellidos
                    + " WHERE id_propietario_dni = " + dni;
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public static boolean anhadirVivienda(String direccion, int metrosCuadrados, double precio, int ascensor, int garaje) {
        boolean resultado = false;

        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            String direccio = "'" + direccion + "'";
            String sql = "INSERT INTO Casas "
                    + "("
                    + "direccion, "
                    + "garaje, "
                    + "ascensor, "
                    + "m2, "
                    + "precio "
                    + ") "
                    + "VALUES ("
                    + direccio + ", "
                    + garaje + ", "
                    + ascensor + ", "
                    + metrosCuadrados + ", "
                    + precio + " "
                    + ")";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("Ya existe esa vivienda en la base de datos.");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
        }

        return resultado;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    public static ArrayList<Dato> mostrarDatosVivienda() {
        int identificador;
        String direccion = "";
        int metrosCuadrados;
        double precio;
        int garaje;
        int ascensor;
        ArrayList<Dato> salidaViviendas = new ArrayList<Dato>();
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT id_casa, direccion, m2, precio, garaje, ascensor FROM Casas");
            while (res.next()) {
                identificador = res.getInt("id_casa");
                direccion = res.getString("direccion");
                metrosCuadrados = res.getInt("m2");
                precio = res.getDouble("precio");
                garaje = res.getInt("garaje");
                ascensor = res.getInt("ascensor");
                salidaViviendas.add(new Dato(identificador, direccion, metrosCuadrados, precio, garaje, ascensor));
            }
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salidaViviendas;
    }

    public static boolean modificarVivienda(String DIRECCION, int metrosCuadrados, double precio, int ascensor, int garaje, Casa c) {
        String direccion = "'" + DIRECCION + "'";

        boolean resultado = false;
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            String sql = "UPDATE Casas "
                    + "SET "
                    + "direccion = " + direccion + ", "
                    + "m2 = " + metrosCuadrados + ", "
                    + "precio = " + precio + ", "
                    + "ascensor = " + ascensor + ", "
                    + "garaje = " + garaje
                    + " WHERE id_casa = " + c.identificador;
//                            "m2 = "+metrosCuadrados+" and "+
//                            "precio = "+precio+" and "+
//                            "ascensor = "+ascensor;
//                            "garaje = "+garaje;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public static boolean borrarVivienda(Casa c) {
        int ref_casa;
        String sql;
        boolean resultado = false;
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            try {
                ResultSet res = st.executeQuery("SELECT ref_vivienda FROM PropietariosCasas WHERE ref_vivienda = " + c.identificador);
                res.next();
                ref_casa = res.getInt("ref_vivienda");
                try {
                    if (ref_casa == c.identificador) {
                        st = con.createStatement();
                        sql = "DELETE FROM PropietariosCasas"
                                + " WHERE ref_vivienda = "
                                + c.identificador;
                        System.out.println(sql);
                        st.executeUpdate(sql);
                        
                    }
                }catch(SQLException ex){
                    System.out.println("No se ha eliminado de propietariosCasas");
                }finally{
                    st.close();
                }

            } catch (SQLException ex) {
                System.out.println("No está en propietarioscasas");
            } finally {
                st.close();
            }

            st = con.createStatement();
            sql = "DELETE FROM Casas"
                    + " WHERE id_casa = "
                    + c.identificador;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
//        boolean resultado = false;
//        try (Connection con = AccederDatos.mysql(null, null, null)) {
//            Statement st = con.createStatement();
//            String sql = "DELETE FROM PropietariosCasas"
//                    + " WHERE ref_propietario = "
//                    + c.identificador;
//            st.executeUpdate(sql);
//            st.close();
//
//            st = con.createStatement();
//            sql = "DELETE FROM Casas"
//                    + " WHERE id_casa = "
//                    + c.identificador;
//            System.out.println(sql);
//            st.executeUpdate(sql);
//            st.close();
//            resultado = true;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return resultado;
    }

    public static int idCasa(String direccion, int metrosCuadrados, double precio, int ascensor, int garaje) {
        int identificador = 0;
        String direcci = "'" + direccion + "'";
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT id_casa FROM Casas WHERE direccion = " + direcci
                    + " and m2 = " + metrosCuadrados);
            while (res.next()) {
                identificador = res.getInt("id_casa");
            }
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return identificador;
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Propietario> buscarPropietario(String dni) {
        ArrayList<Propietario> salidaPropietario = new ArrayList<Propietario>();
        String identificador = "'%" + dni + "%'";
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT id_propietario_dni, nombre, apellidos FROM Propietarios WHERE id_propietario_dni like " + identificador);
            while (res.next()) {
                String dniEncontrado = res.getString("id_propietario_dni");
                String nomEncontrado = res.getString("nombre");
                String apelEncotrado = res.getString("apellidos");
                salidaPropietario.add(new Propietario(nomEncontrado, apelEncotrado, dniEncontrado));
            }
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salidaPropietario;
    }

    public static ArrayList<Casa> buscarCasa(String ident) {
        ArrayList<Casa> salidaPropietario = new ArrayList<Casa>();
        String identificador = "'%" + ident + "%'";
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT id_casa, direccion, m2, precio, garaje, ascensor FROM Casas WHERE id_casa like " + identificador);
            while (res.next()) {
                int idEncontrado = res.getInt("id_casa");
                String direcEncontrado = res.getString("direccion");
                int m2Encontrado = res.getInt("m2");
                double precioEncontrado = res.getDouble("precio");
                int garaje = res.getInt("garaje");
                int ascensor = res.getInt("ascensor");
                salidaPropietario.add(new Casa());
            }
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salidaPropietario;
    }

    public static boolean vincularPropietarioVivienda(Propietario p, Casa v) {
        boolean resultado = false;
        try (Connection con = AccederDatos.mysql(null, null, null)) {
            Statement st = con.createStatement();
            String ref_propietario = "'" + p.dni + "'";
            String ref_vivienda = "'" + v.identificador + "'";
            String sql = "INSERT INTO PropietariosCasas "
                    + "("
                    + "ref_propietario, "
                    + "ref_vivienda"
                    + ") "
                    + "VALUES ("
                    + ref_propietario + ", "
                    + ref_vivienda + " "
                    + ")";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido vincular.");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
        }

        return resultado;
    }
}
