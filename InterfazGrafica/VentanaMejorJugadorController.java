/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Compaq-presario-cq43
 */
public class VentanaMejorJugadorController implements Initializable{
    @FXML
    private Label lblMejoresJugadores;
    @FXML
    private Button btnAceptar;
    private ResourceBundle rb;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rb = ResourceBundle.getBundle("Recursos/Idioma_ESP");
        internacionalizar();
    }
    public void internacionalizar(){
        lblMejoresJugadores.setText(rb.getString("lblMejoresJugadores"));
        btnAceptar.setText(rb.getString("btnAceptar"));
    }
}
