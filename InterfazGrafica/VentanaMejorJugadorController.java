/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import LogicaNegocio.Cliente;
import LogicaNegocio.Jugador;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author Compaq-presario-cq43
 */
public class VentanaMejorJugadorController implements Initializable{
    @FXML
    private Label lblMejoresJugadores;
    @FXML
    private Label lblPorque;
    @FXML
    private Button btnAceptar;
    @FXML
    private FlowPane panelJugadores;
    private ResourceBundle rb;
    private Stage stage;
    private String direccionIp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void internacionalizar(ResourceBundle resource){
        this.rb = resource;
        lblMejoresJugadores.setText(rb.getString("lblMejoresJugadores"));
        btnAceptar.setText(rb.getString("btnAceptar"));
        this.lblPorque.setText(rb.getString("lblPorque"));
        this.stage.setTitle(rb.getString("nombreVentanaMejor"));
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setDireccionIp(String direccionIp){
        this.direccionIp = direccionIp;
        this.cargarJugadores();
    }
    public void cargarJugadores(){
        try {
            Cliente cliente = new Cliente(this.direccionIp);
            ArrayList<Jugador> jugadores = cliente.getListaJugadores();
            for (Jugador jugador : jugadores){
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PanelJugador.fxml"));
                AnchorPane panel;
                try {
                    panel = loader.load();
                    panelJugadorController controller = loader.getController();
                    controller.setValues(jugador.getNombreJugador() + ":", (jugador.getPartidasJugadas() - jugador.getPartidasPerdidas()) + " partidas ganadas de: " + jugador.getPartidasJugadas());
                    this.panelJugadores.getChildren().add(panel);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaMejorJugadorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(VentanaMejorJugadorController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage(rb.getString("errorConexion"), rb.getString("conexionServidor"), rb.getString("mensajeErrorIP"), Alert.AlertType.ERROR);
        }
    }
    
    public void btnAceptar_click(){
        this.stage.close();
    }
    public void lblPorque_mouseEnter(){
        this.lblPorque.setStyle("-fx-text-fill: #0066ff");
    }
    public void lblPorque_mouseDown(){
        this.lblPorque.setStyle("-fx-text-fill: #58ACFA");
    }
    public void lblPorque_mouseUp(){
        this.lblPorque.setStyle("-fx-text-fill: #0066ff");
        MessageFactory.showMessage(this.rb.getString("mensaje"), this.rb.getString("politicas"), this.rb.getString("mensajePoliticas"), Alert.AlertType.INFORMATION);
    }
    public void lblPorque_mouseLeave(){
        this.lblPorque.setStyle("-fx-text-fill: #808080");
    }
}
