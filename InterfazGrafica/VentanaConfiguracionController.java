package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class VentanaConfiguracionController implements Initializable {
    @FXML
    private Label labelIdioma;
    @FXML
    private Label labelEstadisticas;
    @FXML
    private Label labelMejores;
    @FXML
    private RadioButton radioEspañol;
    @FXML
    private RadioButton radioIngles;
    @FXML
    private Button botonOk;
    
    private ResourceBundle rb;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ESP");
        this.internacionalizar();
    }
    
    public void internacionalizar(){
        labelIdioma.setText(rb.getString("labelIdioma"));
        labelEstadisticas.setText(rb.getString("labelEstadisticas"));
        labelMejores.setText(rb.getString("labelMejores"));
        radioEspañol.setText(rb.getString("radioEspañol"));
        radioIngles.setText(rb.getString("radioIngles"));
        botonOk.setText(rb.getString("botonOk"));
    }
}
