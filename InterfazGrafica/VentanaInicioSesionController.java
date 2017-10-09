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
    @FXML
    private Button btnRegistrar;
    
    private ResourceBundle rb;
    private Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void internacionalizar(ResourceBundle resources){
        this.rb = resources;
        lblNombreUsuario.setText(rb.getString("lblNombreUsuario"));
        btnIngresar.setText(rb.getString("btnIngresar"));
        this.btnRegistrar.setText(rb.getString("btnRegistrar"));
        this.stage.setTitle(rb.getString("nombreVentanaInicio"));
    }
    public void btnIngresar_MouseUp(){
        new VentanaTablero(this.rb);
        this.stage.close();
    }
    public void btnRegistrar_Click(){
        
    }
}
