/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Compaq-presario-cq43
 */
public class panelJugadorController implements Initializable {
    @FXML
    private Label lblNombreJugador;
    @FXML
    private Label lblPartidasGanadas;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    public void setValues(String jugador, String partidasGanadas){
        lblNombreJugador.setText(jugador);
        lblPartidasGanadas.setText(partidasGanadas);
    }
}
