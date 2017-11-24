/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import AccesoDatos.RegistroIdioma;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private final String NOMBRE_ARCHIVO = "direccionIP.txt";
    private final String NOMBRE_DIRECTORIO = "C:\\Buscaminas";
    private Stage stage;
    
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
        RegistroIdioma.guardarIdioma(archivo,contenido);
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void btnGuardar_onClicked(){
        this.guardarArchivo(NOMBRE_DIRECTORIO, this.txtDireccionIP.getText());
    }
    public void btnCancelar_onClicked(){
        this.stage.close();
    }
    public void internacionalizar(ResourceBundle resource){
        
    }
}
