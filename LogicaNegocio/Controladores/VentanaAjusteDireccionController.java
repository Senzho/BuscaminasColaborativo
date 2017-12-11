/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Controladores;

import AccesoDatos.RegistroArchivo;
import InterfazGrafica.MessageFactory;
import InterfazGrafica.VentanaInicioSesion;
import LogicaNegocio.InvalidIpAddressException;
import LogicaNegocio.IpAddress;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Compaq-presario-cq43
 */
public class VentanaAjusteDireccionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtDireccionIP;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblMensajeInstruccion;
    
    private static final String NOMBRE_ARCHIVO = "direccionIP.txt";
    private static final String NOMBRE_DIRECTORIO = "C:\\Buscaminas";
    private static final String SEPARADOR = "\\";
    private static final String ERROR = "error";
    private Stage stage;
    private ResourceBundle resource;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void validarDirectorio(String ruta, String contenido){
        File directorio = new File(VentanaAjusteDireccionController.NOMBRE_DIRECTORIO);
        if (!directorio.exists()){
            if (directorio.mkdir()){
                this.guardarArchivo(ruta, contenido);
            }else{
                MessageFactory.showMessage(this.resource.getString(ERROR), this.resource.getString("archivo"), this.resource.getString("errorDirectorioIp"), Alert.AlertType.ERROR);
            }
        }else{
            this.guardarArchivo(ruta, contenido);
        }
    }
    public void guardarArchivo(String ruta, String contenido){
        if(RegistroArchivo.guardar(new File(ruta),contenido)){
            MessageFactory.showMessage(this.resource.getString("contenido"),resource.getString("informacion"),resource.getString("direccionRegistrada"), Alert.AlertType.INFORMATION);
            this.stage.close();
            new VentanaInicioSesion(this.resource, this.txtDireccionIP.getText());
        }else{
            MessageFactory.showMessage(this.resource.getString(ERROR), this.resource.getString("archivo"), this.resource.getString("errorGuardadoIp"), Alert.AlertType.ERROR);
        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
        stage.setOnCloseRequest(eventoVentana);
    }
    public void cerrarVentana(){
        File direccionIp = new File(VentanaAjusteDireccionController.NOMBRE_DIRECTORIO + VentanaAjusteDireccionController.SEPARADOR + VentanaAjusteDireccionController.NOMBRE_ARCHIVO);
        String direccion;
        if (direccionIp.exists()){
            direccion = RegistroArchivo.leerLinea(direccionIp);
        }else{
            direccion = "localhost";
        }
        new VentanaInicioSesion(this.resource, direccion);
    }
    public void btnGuardar_onClicked(){
        try {
            IpAddress direccion = new IpAddress(this.txtDireccionIP.getText());
            this.validarDirectorio(VentanaAjusteDireccionController.NOMBRE_DIRECTORIO+ VentanaAjusteDireccionController.SEPARADOR +VentanaAjusteDireccionController.NOMBRE_ARCHIVO, direccion.getAddress());
        } catch (InvalidIpAddressException ex) {
            Logger.getLogger(VentanaAjusteDireccionController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage(resource.getString(ERROR),resource.getString("direccion"),resource.getString("ipNoValida"), Alert.AlertType.INFORMATION);
        }
    }
    public void btnCancelar_onClicked(){
        this.stage.close();
        this.cerrarVentana();
    }
    public void internacionalizar(ResourceBundle resource){
        this.resource = resource;
        this.stage.setTitle(this.resource.getString("nombreVentanaIp"));
        this.btnCancelar.setText(this.resource.getString("botonCancelar"));
        this.btnGuardar.setText(this.resource.getString("botonGuardar"));
        this.lblDireccion.setText(this.resource.getString("labelDireccion"));
        this.lblMensajeInstruccion.setText(this.resource.getString("labelMensajeInstruccion"));
    }
    EventHandler<WindowEvent> eventoVentana = new EventHandler<WindowEvent>(){
        @Override
        public void handle(WindowEvent event) {
            cerrarVentana();
        }
    };
}
