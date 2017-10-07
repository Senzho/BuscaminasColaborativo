package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Compaq-presario-cq43
 */
public class VentanaEstadisticaController implements Initializable{
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblPartidasJugadas;
    @FXML
    private Label lblPartidasGanadas;
    @FXML
    private Label lblPartidasPerdidas;
    @FXML
    private Label lblTiempoPartida;
    @FXML
    private Button btnAceptar;
    private ResourceBundle rb;
    private Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ING");
        internacionalizar();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void internacionalizar(){
        lblNombreUsuario.setText(rb.getString("lblNombreUsuario"));
        lblPartidasJugadas.setText(rb.getString("lblPartidasJugadas"));
        lblPartidasGanadas.setText(rb.getString("lblPartidasGanadas"));
        lblPartidasPerdidas.setText(rb.getString("lblPartidasPerdidas"));
        lblTiempoPartida.setText(rb.getString("lblTiempoPartida"));
        btnAceptar.setText(rb.getString("btnAceptar"));
    }
    public void btnAceptar_Click(){
        this.stage.close();
    }
}
