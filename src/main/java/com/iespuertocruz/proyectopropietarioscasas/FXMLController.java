package com.iespuertocruz.proyectopropietarioscasas;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLController implements Initializable {

    Propietario p;
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
        p = new Propietario();
        mostrarEnTablaPropietario();
        // ForEach que añade al ArrayList los valores de la Base de Datos.
        for (Dato dato : datos) {
            Almacen.agregarPropietario(new Propietario(dato.getNombre(), dato.getApellidos(), dato.getDni()));
        }
    }

    @FXML
    private void btnAnhadirPropietarioOnClick(ActionEvent event) {
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        boolean insert = GestionDatos.insertarPropietario(dni, nombre, apellidos);
        Almacen.agregarPropietario(new Propietario(nombre, apellidos, dni));
        if (insert) {
            //Crear mensaje de resultado de insert
            lblResultadoPropietario.setText("Propietario insertado con éxito");
        } else {
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido insertar el usuario en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();

        }
        mostrarEnTablaPropietario();
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
    }

    private void mostrarEnTablaPropietario() {
        columnaDNI.setCellValueFactory(new PropertyValueFactory<Dato, String>("dni"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Dato, String>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<Dato, String>("apellidos"));
        datos = GestionDatos.mostrarDatosPropietarios();
        tablaPropietarios.setItems(FXCollections.observableArrayList(datos));

    }

    @FXML
    private Propietario tableViewPropietariosOnClick(MouseEvent event) {
        Dato d = tablaPropietarios.getSelectionModel().getSelectedItem();
        int posicion = Almacen.buscarPropietario(d.getDni());
        //Si la posición es -1 es que no se han añadido datos
        if (posicion != -1) {
            p = Almacen.propietarios.get(posicion);
        } else {
            System.out.println("No está en el arrayList");
        }
        txtDNI.setText(p.dni);
        txtNombre.setText(p.nombre);
        txtApellidos.setText(p.apellidos);
        btnBorrarPropietario.setVisible(true);
        btnModificarPropietario.setVisible(true);
        return p;
    }

    @FXML
    private void btnModificarPropietarioOnClick(MouseEvent event) {
//        p = tableViewPropietariosOnClick(event);
//        String identificador = p.dni;
//        String dni = txtDNI.getText();
//        String nombre = txtNombre.getText();
//        String apellidos = txtApellidos.getText();
//        
//        boolean insert = GestionDatos.modificarPopietario(identificador, dni, nombre, apellidos);
//        if (insert) {
//            //Crear mensaje de resultado de insert
//            lblResultadoPropietario.setText("Propietario modificado con éxito");
//        } else {
//            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
//            dialogoAyuda.setTitle("ERROR");
//            dialogoAyuda.setHeaderText(null);
//            dialogoAyuda.setContentText("No se ha podido modificar el usuario en la base de datos");
//            dialogoAyuda.initStyle(StageStyle.UTILITY);
//            dialogoAyuda.showAndWait();
//
//        }
//        mostrarEnTablaPropietario();
    }

    @FXML
    private void btnBorrarPropietarioOnClick(MouseEvent event) {
        p = tableViewPropietariosOnClick(event);
        txtDNI.disableProperty();
        txtNombre.disableProperty();
        txtApellidos.disableProperty();
        GestionDatos.borrarPropietario(p);
        mostrarEnTablaPropietario();
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------

    @FXML
    /**
     * Método para añadir Viviendas, en principio solo en la Base de Datos.
     */
    private void btnAnhadirVivienda(ActionEvent event) {
        boolean ascensor;
        boolean garaje;
        String direccion = txtDireccion.getText();
        int metrosCuadrados = Integer.parseInt(txtMetros.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        // Se hace un if para saber si esta seleccionado o no y así saber si es true o false.
        if(cbxAscensor.isSelected()){
            ascensor = true;
        }else {
            ascensor = false;
        }
        if(cbxGaraje.isSelected()){
            garaje = true;
        }else{
            garaje = false;
        }
        
        boolean insert = GestionDatos.insertarPropietario(dni, nombre, apellidos);
        Almacen.agregarPropietario(new Propietario(nombre, apellidos, dni));
        if (insert) {
            //Crear mensaje de resultado de insert
            lblResultadoPropietario.setText("Propietario insertado con éxito");
        } else {
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido insertar el usuario en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();

        }
        mostrarEnTablaPropietario();
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
    }
    
    
}
