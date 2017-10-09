package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    public void internacionalizar(ResourceBundle resource){
        this.rb = resource; 
        labelIdioma.setText(rb.getString("labelIdioma"));
        labelEstadisticas.setText(rb.getString("labelEstadisticas"));
        labelMejores.setText(rb.getString("labelMejores"));
        radioEspañol.setText(rb.getString("radioEspañol"));
        radioIngles.setText(rb.getString("radioIngles"));
        botonOk.setText(rb.getString("botonOk"));
        this.stage.setTitle(rb.getString("nombreVentanaConfiguracion"));
    }
    public void botonOk_Click(){
        this.stage.close();
    }
    public void radioEspañol_click(){
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ESP");
        this.internacionalizar(rb);
        this.controlerTablero.internacionalizar(rb);
        if(this.estadisticaController!=null){
            this.estadisticaController.internacionalizar(rb);
        }
        if(this.mejorJugadorController!=null){
            this.mejorJugadorController.internacionalizar(rb);
        }
    }
    public void radioIngles_click(){
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ING");
        this.internacionalizar(rb);
        this.controlerTablero.internacionalizar(rb);
        if(this.estadisticaController!=null){
            this.estadisticaController.internacionalizar(rb);
        }
        if(this.mejorJugadorController!=null){
            this.mejorJugadorController.internacionalizar(rb);
        }
    }
    
    //Eventos
    
    public void labelEstadisticas_MouseEnter(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #0066ff");
    }
    public void labelEstadisticas_MouseDown(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #58ACFA");
    }
    public void labelEstadisticas_MouseUp(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #0066ff");
        new VentanaEstadistica(this, this.rb);
    }
    public void labelEstadisticas_MouseLeave(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #808080");
    }
    public void labelMejores_MouseEnter(){
        this.labelMejores.setStyle("-fx-text-fill: #0066ff");
    }
    public void labelMejores_MouseDown(){
        this.labelMejores.setStyle("-fx-text-fill: #58ACFA");
    }
    public void labelMejores_MouseUp(){
        this.labelMejores.setStyle("-fx-text-fill: #0066ff");
        new VentanaMejorJugador(this, this.rb);
    }
    public void labelMejores_MouseLeave(){
        this.labelMejores.setStyle("-fx-text-fill: #808080");
    }
}
