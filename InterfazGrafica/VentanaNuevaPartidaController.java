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
    private Label labelJugadores;
    @FXML
    private Label labelDificultad;
    @FXML
    private Label labelFacil;
    @FXML
    private Label labelMedio;
    @FXML
    private Label labelAvanzado;
    @FXML
    private RadioButton radioFacil;
    @FXML
    private RadioButton radioMedio;
    @FXML
    private RadioButton radioAvanzado;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonSolicitud;
    
    private ResourceBundle recursos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recursos = ResourceBundle.getBundle("Recursos/Idioma_ING");
        this.internacionalizar();
    }    
    
    public void internacionalizar(){
        this.labelJugadores.setText(this.recursos.getString("labelJugadores"));
        this.labelDificultad.setText(this.recursos.getString("labelDificultad"));
        this.labelFacil.setText(this.recursos.getString("labelFacil"));
        this.labelMedio.setText(this.recursos.getString("labelMedio"));
        this.labelAvanzado.setText(this.recursos.getString("labelAvanzado"));
        this.radioFacil.setText(this.recursos.getString("radioFacil"));
        this.radioMedio.setText(this.recursos.getString("radioMedio"));
        this.radioAvanzado.setText(this.recursos.getString("radioAvanzado"));
        this.botonCancelar.setText(this.recursos.getString("botonCancelar"));
        this.botonSolicitud.setText(this.recursos.getString("botonSolicitud"));
    }
}
