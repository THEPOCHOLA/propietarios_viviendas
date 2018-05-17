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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLController implements Initializable {

    int contador = 0;
    Casa c;
    Propietario p;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDNI;
    @FXML
    private Button btnAnhadirProp;
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
    private ListView<ArrayList<Propietario>> lvVincularPropietarios;
    @FXML
    private ListView<?> lvVincularViviendas;
    @FXML
    private Button btnVincular;
    @FXML
    private TextField txtBuscarPropietario;
    @FXML
    private TextField txtBuscarVivienda;
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
    ArrayList<Dato> datosViviendas = new ArrayList<Dato>();
    @FXML
    private Label lblResultadoPropietario;
    @FXML
    private Button btnModificarVivienda;
    @FXML
    private Button btnBorrarVivienda;
    @FXML
    private TableColumn<Dato, Integer> columnaIDVivienda;
    @FXML
    private TableColumn<Dato, String> columnaDireccion;
    @FXML
    private TableView<Dato> tablaViviendas;
    @FXML
    private Label lblResultadoVivienda;
    @FXML
    private Label lblResultadoVincular;

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
        c = new Casa();
        p = new Propietario();
        mostrarEnTablaPropietario();
        mostrarEnTablaVivienda();
        // ForEach que añade al ArrayList los valores de la Tabla Propietarios.
        for (Dato dato : datos) {
            Almacen.agregarPropietario(new Propietario(dato.getNombre(), dato.getApellidos(), dato.getDni()));
        }
        for (Dato datosVivienda : datosViviendas) {
            Almacen.agregarCasa(new Casa(datosVivienda.getIdentificador(), datosVivienda.getDireccion()));
            
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
        txtDNI.setDisable(true);
        btnBorrarPropietario.setVisible(true);
        btnModificarPropietario.setVisible(true);
        mostrarEnTablaPropietario();
        return p;
    }

    @FXML
    private void btnModificarPropietarioOnClick(MouseEvent event) {
        p = tableViewPropietariosOnClick(event);
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        
        boolean insert = GestionDatos.modificarPopietario(dni, nombre, apellidos);
        Propietario aModificar = Almacen.propietarios.get(Almacen.buscarPropietario(dni));
        aModificar.apellidos = apellidos;
        aModificar.nombre = nombre;
        if (insert) {
            lblResultadoPropietario.setText("Propietario modificado con éxito");
        } else {
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido modificar el usuario en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
        }
        mostrarEnTablaPropietario();
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
        int ascensor;
        int garaje;
        String direccion = txtDireccion.getText();
        int metrosCuadrados = Integer.parseInt(txtMetros.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        // Se hace un if para saber si esta seleccionado o no y así saber si es true o false.
        if (cbxAscensor.isSelected()) {
            ascensor = 1;
        } else {
            ascensor = 0;
        }
        if (cbxGaraje.isSelected()) {
            garaje = 1;
        } else {
            garaje = 0;
        }
        // La variable insert obtentra true si se reailza el insert, o false si no se realiza.
        boolean insert = GestionDatos.anhadirVivienda(direccion, metrosCuadrados, precio, ascensor, garaje);
        int identificador = GestionDatos.idCasa(direccion, metrosCuadrados, precio, ascensor, garaje);
        // Se le añaden los valores a la Clase Casa, falta el identificador, que se realiza por consulta.
        Almacen.agregarCasa(new Casa(identificador, direccion, metrosCuadrados, garaje, ascensor, precio));
        if (insert) {
            //Añadir el ID en la el label, debatir, porque sale en la tabla
            lblResultadoPropietario.setText("Propietario insertado con éxito");
        } else {
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido insertar la vivienda en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
        }
        mostrarEnTablaVivienda();
        
    }

    private void mostrarEnTablaVivienda() {
        columnaIDVivienda.setCellValueFactory(new PropertyValueFactory<Dato, Integer>("identificador"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<Dato, String>("direccion"));
        datosViviendas = GestionDatos.mostrarDatosVivienda();
        tablaViviendas.setItems(FXCollections.observableArrayList(datosViviendas));

    }

    @FXML
    /**
     * Método para modificar datos de la vivienda, en principio solo en la Base
     * de Datos.
     */
    private void btnModificarViviendaOnClick(ActionEvent event) {
    }

    @FXML
    /**
     * Método para borrar viviendas, en principio solo en la Base de Datos.
     */
    private Casa tableViewVivendasOnClick(MouseEvent event) {
        Dato d = tablaViviendas.getSelectionModel().getSelectedItem();
        int posicion = Almacen.buscarCasa(d.getIdentificador());
        //Si la posición es -1 es que no se han añadido datos
        if (posicion != -1) {
            c = Almacen.casas.get(posicion);
        } else {
            System.out.println("No está en el arrayList");
        }
        txtID.setText(c.identificador+"");
        txtDireccion.setText(c.direccion);
        btnBorrarVivienda.setVisible(true);
        btnModificarVivienda.setVisible(true);
        return c;
    }

    @FXML
    private void btnBorrarViviendaOnClick(MouseEvent event) {
        c = tableViewVivendasOnClick(event);
        GestionDatos.borrarVivienda(c);
        mostrarEnTablaVivienda();
        btnBorrarVivienda.setVisible(false);
        btnModificarVivienda.setVisible(false);
    }

    @FXML
    private void buscarPropietario(KeyEvent event) {
        ArrayList<Propietario> propBuscar = new ArrayList<Propietario>();
        if (event.getCode().toString().equals("ENTER")){
            String propietarioBuscar = txtBuscarPropietario.getText();
            propBuscar = GestionDatos.buscarPropietario(propietarioBuscar);
            lvVincularPropietarios.getItems().add(propBuscar);
            
        }
    }

    @FXML
    private void listViewOnClick(MouseEvent event) {
        ArrayList<Propietario> pList = lvVincularPropietarios.getSelectionModel().getSelectedItem();
        Alert dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setContentText("Datos: \n"+ pList.toString());
        dialog.showAndWait();
    }       
}