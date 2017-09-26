/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Compaq-presario-cq43
 */
public class VentanaInicioSesionController implements Initializable {
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnIngresar;
    private ResourceBundle rb;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rb = ResourceBundle.getBundle("Recursos/Idioma_ESP");
        internacionalizar();
    }
    public void internacionalizar(){
        lblNombreUsuario.setText(rb.getString("lblNombreUsuario"));
        btnIngresar.setText(rb.getString("btnIngresar"));
    }
    public void btnIngresar_MouseUp(){
        try {
            new VentanaTablero().start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
