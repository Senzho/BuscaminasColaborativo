/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import AccesoDatos.RegistroIdioma;
import LogicaNegocio.InvalidIpAddressException;
import LogicaNegocio.IpAddress;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    private final String NOMBRE_ARCHIVO = "direccionIP.txt";
    private final String NOMBRE_DIRECTORIO = "C:\\Buscaminas";
    private Stage stage;
    private ResourceBundle resource;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void guardarArchivo(String ruta, String contenido){
        File directorio = new File(this.NOMBRE_DIRECTORIO);
        if (!directorio.exists()){
            directorio.mkdir();
        }
        File archivo = new File(ruta);
        if (archivo.exists()){
            archivo.delete();
        }
        if(RegistroIdioma.guardarIdioma(archivo,contenido)){
            MessageFactory.showMessage(this.resource.getString("contenido"),resource.getString("informacion"),resource.getString("direccionRegistrada"), Alert.AlertType.INFORMATION);
            this.stage.close();
            new VentanaInicioSesion(this.resource, this.txtDireccionIP.getText());
        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void btnGuardar_onClicked(){
        try {
            IpAddress direccion = new IpAddress(this.txtDireccionIP.getText());
            this.guardarArchivo(NOMBRE_DIRECTORIO+"\\"+this.NOMBRE_ARCHIVO, direccion.getAddress());
        } catch (InvalidIpAddressException ex) {
            Logger.getLogger(VentanaAjusteDireccionController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage(resource.getString("error"),resource.getString("direccion"),resource.getString("ipNoValida"), Alert.AlertType.INFORMATION);
        }
    }
    public void btnCancelar_onClicked(){
        this.stage.close();
    }
    public void internacionalizar(ResourceBundle resource){
        this.resource = resource;
        this.stage.setTitle(this.resource.getString("nombreVentanaIp"));
        this.btnCancelar.setText(this.resource.getString("botonCancelar"));
        this.btnGuardar.setText(this.resource.getString("botonGuardar"));
        this.lblDireccion.setText(this.resource.getString("labelDireccion"));
        this.lblMensajeInstruccion.setText(this.resource.getString("labelMensajeInstruccion"));
    }
}
