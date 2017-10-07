package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VentanaInicioSesionController implements Initializable {
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnIngresar;
    
    private ResourceBundle rb;
    private Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rb = ResourceBundle.getBundle("Recursos/Idioma_ING");
        internacionalizar();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void internacionalizar(){
        lblNombreUsuario.setText(rb.getString("lblNombreUsuario"));
        btnIngresar.setText(rb.getString("btnIngresar"));
    }
    public void btnIngresar_MouseUp(){
        new VentanaTablero();
        this.stage.close();
    }
}
