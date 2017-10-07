package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    private VentanaNuevaPartidaController nuevaPartidaContrller;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recursos = ResourceBundle.getBundle("Recursos/Idioma_ING");
        this.internacionalizar();
    }
    
    public void setNuevaPartidaController(VentanaNuevaPartidaController controller){
        this.nuevaPartidaContrller = controller;
    }
    public void internacionalizar(){
        this.labelTextoMinas.setText(this.recursos.getString("labelTextoMinas"));
    }
    
    public void botonConfiguracion_MouseUp(){
        new VentanaConfiguracion(this);
    }
    public void botonPlayPause_MouseUp(){
        new VentanaNuevaPartida(this);
    }
}
