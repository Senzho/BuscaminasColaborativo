package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

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
    private Stage stage;
    private VentanaTableroController controlerTablero;
    private VentanaMejorJugadorController mejorJugadorController;
    private VentanaEstadisticaController estadisticaController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ING");
        this.internacionalizar();
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setControladorTablero(VentanaTableroController controller){
        this.controlerTablero = controller;
    }
    public void setControladorMejorJugador(VentanaMejorJugadorController controller){
        this.mejorJugadorController = controller;
    }
    public void setControladorEstadisticas(VentanaEstadisticaController controller){
        this.estadisticaController = controller;
    }
    
    public void internacionalizar(){
        labelIdioma.setText(rb.getString("labelIdioma"));
        labelEstadisticas.setText(rb.getString("labelEstadisticas"));
        labelMejores.setText(rb.getString("labelMejores"));
        radioEspañol.setText(rb.getString("radioEspañol"));
        radioIngles.setText(rb.getString("radioIngles"));
        botonOk.setText(rb.getString("botonOk"));
    }
    public void labelEstadisticas_MouseUp(){
            new VentanaEstadistica(this);
    }
    public void labelMejores_MouseUp(){
            new VentanaMejorJugador(this);
    }
    public void botonOk_Click(){
        this.stage.close();
    }
}
