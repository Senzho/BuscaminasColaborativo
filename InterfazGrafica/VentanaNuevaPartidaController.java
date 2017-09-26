package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class VentanaNuevaPartidaController implements Initializable {
    @FXML
    private Label labelJugadores, labelDificultad, labelFacil, labelMedio, labelAvanzado;
    private RadioButton radioFacil, radioMedio, radioAvanzado;
    private Button botonCancelar, botonSolicitud;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
