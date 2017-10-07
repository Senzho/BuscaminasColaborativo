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
    private ResourceBundle resource;
    private VentanaNuevaPartidaController nuevaPartidaContrller;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setNuevaPartidaController(VentanaNuevaPartidaController controller){
        this.nuevaPartidaContrller = controller;
    }
    public void internacionalizar(ResourceBundle rb){
        this.resource = rb;
        this.labelTextoMinas.setText(rb.getString("labelTextoMinas"));
        if(nuevaPartidaContrller != null){
            nuevaPartidaContrller.internacionalizar(resource);
        }
    }
    
    public void botonConfiguracion_MouseUp(){
        new VentanaConfiguracion(this, resource);
    }
    public void botonPlayPause_MouseUp(){
        new VentanaNuevaPartida(this, resource);
    }
}
