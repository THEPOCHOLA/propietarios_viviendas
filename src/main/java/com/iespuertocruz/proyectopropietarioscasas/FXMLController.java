package com.iespuertocruz.proyectopropietarioscasas;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public TableView<Dato> tablaPropietarios;
    @FXML
    public TableColumn<Dato, String> columnaDNI;
    @FXML
    public TableColumn<Dato, String> columnaNombre;
    @FXML
    public TableColumn<Dato, String> columnaApellidos;
    @FXML
    private Button btnBorrarPropietario;
    @FXML
    private Button btnModificarPropietario;
    ArrayList<Dato> datos = new ArrayList<Dato>();
    private int posicionPropietarioEnTabla;
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
        mostrarEnTablaPropietario();
    }

    @FXML
    private void btnAnhadirPropietarioOnClick(ActionEvent event) {
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        
        GestionDatos.insertarPropietario(dni, nombre, apellidos);
        mostrarEnTablaPropietario();
        
    }
    
    private void mostrarEnTablaPropietario(){
        columnaDNI.setCellValueFactory(new PropertyValueFactory<Dato, String>("dni"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Dato, String>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<Dato, String>("apellidos"));
        datos = GestionDatos.mostrarDatos();
        tablaPropietarios.setItems(FXCollections.observableArrayList(datos));
    }

    @FXML
    private void btnBorrarPropietarioOnClick(ActionEvent event) {
        datos.remove(posicionPropietarioEnTabla);
    }

    @FXML
    private void btnModificarPropietarioOnClick(ActionEvent event) {
        
    }
}
