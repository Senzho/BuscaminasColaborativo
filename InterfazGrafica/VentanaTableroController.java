package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VentanaTableroController implements Initializable {
    @FXML
    private ImageView botonConfiguracion;
    @FXML
    private ImageView botonPlayPause;
    @FXML
    private Label labelTiempo;
    @FXML
    private Label labelTextoMinas;
    
    private ResourceBundle recursos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recursos = ResourceBundle.getBundle("Recursos/Idioma_ESP");
        this.internacionalizar();
    }
    
    public void internacionalizar(){
        this.labelTextoMinas.setText(this.recursos.getString("labelTextoMinas"));
    }
    
    public void botonConfiguracion_MouseUp(){
        try {
            new VentanaConfiguracion().start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void botonPlayPause_MouseUp(){
        try{
            new VentanaNuevaPartida().start(new Stage());
        }catch(Exception ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
