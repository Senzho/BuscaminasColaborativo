package InterfazGrafica;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class VentanaConfiguracionController implements Initializable {
    @FXML
    private Label labelIdioma, labelEstadisticas, labelMejores;
    private RadioButton radioEspañol;
    private RadioButton radioIngles;
    private Button botonOk;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
