/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import LogicaNegocio.Cliente;
import LogicaNegocio.Jugador;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
public class VentanaMejorJugadorController implements Initializable{
    @FXML
    private Label lblMejoresJugadores;
    @FXML
    private Button btnAceptar;
    private ResourceBundle rb;
    private Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cargarJugadores();
    }
    public void internacionalizar(ResourceBundle resource){
        this.rb = resource;
        lblMejoresJugadores.setText(rb.getString("lblMejoresJugadores"));
        btnAceptar.setText(rb.getString("btnAceptar"));
        this.stage.setTitle(rb.getString("nombreVentanaMejor"));
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void cargarJugadores(){
        try {
            Cliente cliente = new Cliente("localhost");
            ArrayList<Jugador> jugadores = cliente.getListaJugadores();
            for (Jugador jugador : jugadores){
                System.out.println("/n" + jugador.getNombreJugador() + ":");
                System.out.println((jugador.getPartidasJugadas() - jugador.getPartidasPerdidas()) + " partidas ganadas de: " + jugador.getPartidasJugadas());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaMejorJugadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnAceptar_click(){
        this.stage.close();
    }
}
