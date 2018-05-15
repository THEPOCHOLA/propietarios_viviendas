package com.iespuertocruz.proyectopropietarioscasas;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDNI;
    @FXML
    private Button btnAnhadirProp;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtMetros;
    @FXML
    private Button btnAnhadirVivienda;
    @FXML
    private CheckBox cbxAscensor;
    @FXML
    private CheckBox cbxGaraje;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ListView<?> lvVincularPropietarios;
    @FXML
    private ListView<?> lvVincularViviendas;
    @FXML
    private Button btnVincular;
    @FXML
    private TextField txtBuscarPropietario;
    @FXML
    private TextField txtBuscarVivienda;
    @FXML
    private Label lblResultado;
    @FXML
    private Label lblResultadoPropietario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            AccederDatos.mysql("", "", "op");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
//            dialogoAyuda.setTitle("ERROR");
//            dialogoAyuda.setHeaderText(null);
//            dialogoAyuda.setContentText("No se ha podido conectar a la base de datos");
//            dialogoAyuda.initStyle(StageStyle.UTILITY);
//            dialogoAyuda.showAndWait();
//            System.exit(0);
//        }
    }

    @FXML
    private void btnAnhadirPropietarioOnClick(ActionEvent event) {
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        
        boolean insert = GestionDatos.insertarPropietario(dni, nombre, apellidos);
        
        if (insert){
            //Crear mensaje de resultado de insert
            lblResultadoPropietario.setText("Propietario insertado con éxito");
        }else{
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido insertar el usuario en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
            
        }
    }

}
