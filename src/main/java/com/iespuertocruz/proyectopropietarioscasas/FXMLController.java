package com.iespuertocruz.proyectopropietarioscasas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLController implements Initializable {

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
    private ListView<Propietario> lvVincularPropietarios;
    @FXML
    private ListView<Casa> lvVincularViviendas;
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
    @FXML
    private Button btnReiniciarAnhiadir;
    @FXML
    private Button btnNuevaVivienda;

    private Propietario propietarioAVincular;
    private Casa viviendaAVincular;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarEnTablaPropietario();
        mostrarEnTablaVivienda();
        // ForEach que añade al ArrayList los valores de la Tabla Propietarios.
        actualizarArrayListAlmacenPropietarios();
        // ForEach que añade al ArrayList los valores de la Tabla Viviendas.
        actualizarArrayListAlmacenViviendas();

        mostrarTodoEnListViewViviendaVincular();
        mostrarTodoEnListViewPropietarioVincular();
    }

    private void mostrarTodoEnListViewViviendaVincular() {
        lvVincularViviendas.getItems().clear();
        for (Casa casa : Almacen.casas) {
            lvVincularViviendas.getItems().add(casa);
        }
    }

    private void mostrarTodoEnListViewPropietarioVincular() {
        lvVincularPropietarios.getItems().clear();
        for (Propietario prop : Almacen.propietarios) {
            lvVincularPropietarios.getItems().add(prop);
        }
    }

    private void actualizarArrayListAlmacenViviendas() {
        Almacen.casas.clear();
        for (Dato datosVivienda : datosViviendas) {
            Almacen.agregarCasa(new Casa(datosVivienda.getIdentificador(), datosVivienda.getDireccion(), datosVivienda.getMetrosCuadrados(), datosVivienda.getGaraje(), datosVivienda.getAscensor(), datosVivienda.getPrecio()));
        }
    }

    private void actualizarArrayListAlmacenPropietarios() {
        Almacen.propietarios.clear();
        for (Dato dato : datos) {
            Almacen.agregarPropietario(new Propietario(dato.getNombre(), dato.getApellidos(), dato.getDni()));
        }
    }

    private void mostrarEnTablaPropietario() {
        datos = GestionDatos.mostrarDatosPropietarios();
        columnaDNI.setCellValueFactory(new PropertyValueFactory<Dato, String>("dni"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Dato, String>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<Dato, String>("apellidos"));
        tablaPropietarios.setItems(FXCollections.observableArrayList(datos));

    }

    @FXML
    private void btnAnhadirPropietarioOnClick(ActionEvent event) {
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        boolean insert = GestionDatos.insertarPropietario(dni, nombre, apellidos);
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
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
        actualizarArrayListAlmacenPropietarios();
        mostrarEnTablaPropietario();
    }

    @FXML
    private void btnModificarPropietarioOnClick(MouseEvent event) {

        actualizarArrayListAlmacenPropietarios();
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        boolean insert = GestionDatos.modificarPopietario(dni, nombre, apellidos);
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
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDNI.setText("");
        txtDNI.setDisable(false);
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
        btnReiniciarAnhiadir.setVisible(false);
        mostrarEnTablaPropietario();
        actualizarArrayListAlmacenPropietarios();
        mostrarTodoEnListViewPropietarioVincular();
    }

    @FXML
    private void btnBorrarPropietarioOnClick(MouseEvent event) throws IOException {
        actualizarArrayListAlmacenPropietarios();
        p = tableViewPropietariosOnClick(event);
        GestionDatos.borrarPropietario(p);
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
        btnReiniciarAnhiadir.setVisible(false);
        btnAnhadirProp.setVisible(true);
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        mostrarEnTablaPropietario();
        actualizarArrayListAlmacenPropietarios();
        txtDNI.setDisable(false);
    }

    @FXML
    private Propietario tableViewPropietariosOnClick(MouseEvent event) throws IOException {
        actualizarArrayListAlmacenPropietarios();
        Dato d = (Dato) tablaPropietarios.getSelectionModel().getSelectedItem();
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
        btnAnhadirProp.setVisible(false);
        btnReiniciarAnhiadir.setVisible(true);
        //_____________________________________
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")); 
        Scene scene = new Scene(root); 
        Stage stage = new Stage(); 
        scene.getStylesheets().add("/styles/Styles.css"); 
        stage.setTitle("Propietarios y Casas con BBDD"); 
        stage.centerOnScreen(); 
        stage.setScene(scene); 
        stage.show(); 
        //-------------------------------------
        return p;
    }

    @FXML
    private void btnReiniciarAnhiadirOnClick(ActionEvent event) {
        txtDNI.setText("");
        txtDNI.setDisable(false);
        txtNombre.setText("");
        txtApellidos.setText("");
        btnBorrarPropietario.setVisible(false);
        btnModificarPropietario.setVisible(false);
        btnAnhadirProp.setVisible(true);
        btnReiniciarAnhiadir.setVisible(false);
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
//        int identificador = GestionDatos.idCasa(direccion, metrosCuadrados, precio, ascensor, garaje);
        // Se le añaden los valores a la Clase Casa, falta el identificador, que se realiza por consulta.
//        Almacen.agregarCasa(new Casa(identificador, direccion, metrosCuadrados, garaje, ascensor, precio));
        if (insert) {
            //Añadir el ID en la el label, debatir, porque sale en la tabla
            lblResultadoVivienda.setText("Vivienda insertada con éxito");
        } else {
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido insertar la vivienda en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
        }
        mostrarEnTablaVivienda();
        actualizarArrayListAlmacenViviendas();

    }

    private void mostrarEnTablaVivienda() {
        columnaIDVivienda.setCellValueFactory(new PropertyValueFactory<Dato, Integer>("identificador"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<Dato, String>("direccion"));
        datosViviendas = GestionDatos.mostrarDatosVivienda();
        tablaViviendas.setItems(FXCollections.observableArrayList(datosViviendas));

    }

    @FXML
    private void btnNuevaViviendaOnClick(ActionEvent event) {
        txtDireccion.setText("");
        txtMetros.setText("");
        txtPrecio.setText("");
        cbxAscensor.setSelected(false);
        cbxGaraje.setSelected(false);
        btnBorrarVivienda.setVisible(false);
        btnModificarVivienda.setVisible(false);
        btnAnhadirVivienda.setVisible(true);
        btnNuevaVivienda.setVisible(false);
    }

    @FXML
    /**
     * Método para modificar datos de la vivienda, en principio solo en la Base
     * de Datos.
     */
    private void btnModificarViviendaOnClick(MouseEvent event) {
        actualizarArrayListAlmacenViviendas();
        
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
        System.out.println(direccion + ",   " + metrosCuadrados + ",   " + precio + ",   " + ascensor + ",   " + garaje);
        c = tableViewVivendasOnClick(event);
        boolean insert = GestionDatos.modificarVivienda(direccion, metrosCuadrados, precio, ascensor, garaje, c);

        if (insert) {
            lblResultadoVivienda.setText("Vivienda modificada con éxito");
        } else {
            Alert dialogoAyuda = new Alert(Alert.AlertType.WARNING);
            dialogoAyuda.setTitle("ERROR");
            dialogoAyuda.setHeaderText(null);
            dialogoAyuda.setContentText("No se ha podido modificar la vivienda en la base de datos");
            dialogoAyuda.initStyle(StageStyle.UTILITY);
            dialogoAyuda.showAndWait();
        }
        txtDireccion.setText("");
        txtMetros.setText("");
        txtPrecio.setText("");
        cbxAscensor.setSelected(false);
        cbxGaraje.setSelected(false);
        mostrarEnTablaVivienda();
        actualizarArrayListAlmacenViviendas();
        mostrarTodoEnListViewViviendaVincular();
    }

    @FXML
    private Casa tableViewVivendasOnClick(MouseEvent event) {
        actualizarArrayListAlmacenViviendas();
        Dato d = (Dato) tablaViviendas.getSelectionModel().getSelectedItem();
        int posicion = Almacen.buscarCasa(d.getIdentificador());
        //Si la posición es -1 es que no se han añadido datos
        if (posicion != -1) {
            c = Almacen.casas.get(posicion);
        } else {
            System.out.println("No está en el arrayList");
        }
        txtDireccion.setText(c.direccion);
        txtPrecio.setText(c.precio + "");
        txtMetros.setText(c.metros + "");
        if (c.garaje == 1) {
            cbxGaraje.setSelected(true);
        } else {
            cbxGaraje.setSelected(false);
        }
        if (c.ascensor == 1) {
            cbxAscensor.setSelected(true);
        } else {
            cbxAscensor.setSelected(false);
        }
        btnBorrarVivienda.setVisible(true);
        btnModificarVivienda.setVisible(true);
        btnAnhadirVivienda.setVisible(false);
        btnNuevaVivienda.setVisible(true);
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
//------------------------------------------------------------------------------------------------------------------------------------------------------
    @FXML
    private void listViewVincularPropietariosOnClick(MouseEvent event) {
        propietarioAVincular = lvVincularPropietarios.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void listViewVincularViviendasOnClick(MouseEvent event) {
        viviendaAVincular = lvVincularViviendas.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void buscarPropietarioOnClick(KeyEvent event) {
        ArrayList<Propietario> propBuscar = new ArrayList<Propietario>();
        if (event.getCode().toString().equals("ENTER")) {
            lvVincularPropietarios.getItems().clear();
            if (txtBuscarPropietario.getText().isEmpty()) {
                mostrarTodoEnListViewPropietarioVincular();
            } else {
                String propietarioBuscar = txtBuscarPropietario.getText();
                propBuscar = GestionDatos.buscarPropietario(propietarioBuscar);
                for (Propietario propietario : propBuscar) {
                    lvVincularPropietarios.getItems().add(propietario);
                }
            }
        }
    }

    @FXML
    private void buscarViviendaOnClick(KeyEvent event) {
        ArrayList<Casa> viviendaBuscar = new ArrayList<Casa>();
        if (event.getCode().toString().equals("ENTER")){
            lvVincularViviendas.getItems().clear();
            if(txtBuscarVivienda.getText().isEmpty()){
                mostrarTodoEnListViewViviendaVincular();
            }else{
                String vivBuscar = txtBuscarVivienda.getText();
                viviendaBuscar = GestionDatos.buscarCasa(vivBuscar);
                for (Casa vivienda : viviendaBuscar) {
                    lvVincularViviendas.getItems().add(vivienda);
                }
            }

        }
    }
    
    @FXML
    private void btnVincularOnClick(ActionEvent event) {
        boolean result = GestionDatos.vincularPropietarioVivienda(propietarioAVincular, viviendaAVincular);
        if (result) {
            lblResultadoVincular.setText("Se ha vinculado con éxito");
        }
    }

}
