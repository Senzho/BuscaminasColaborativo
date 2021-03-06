package LogicaNegocio.Controladores;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Cliente;
import LogicaNegocio.DatosJugador;
import LogicaNegocio.Jugador;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    @FXML
    private Label lblNumeroPartidasJugadas;
    @FXML
    private Label lblNumeroPartidasGanadas;
    @FXML
    private Label lblNumeroPartidasPerdidas;
    @FXML
    private Label lblValorTiempoPromedio;
    
    private ResourceBundle rb;
    private Stage stage;
    private int idJugador;
    private String direccionIp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setIdJugador(int idJugador){
        this.idJugador = idJugador;
        this.cargarEstadisticas();
    }
    public void setDireccionIp(String direccionIp){
        this.direccionIp = direccionIp;
    }
    public void internacionalizar(ResourceBundle resource){
        this.rb = resource;
        lblPartidasJugadas.setText(rb.getString("lblPartidasJugadas"));
        lblPartidasGanadas.setText(rb.getString("lblPartidasGanadas"));
        lblPartidasPerdidas.setText(rb.getString("lblPartidasPerdidas"));
        lblTiempoPartida.setText(rb.getString("lblTiempoPartida"));
        btnAceptar.setText(rb.getString("btnAceptar"));
        this.stage.setTitle(rb.getString("nombreVentanaEstadistica"));
    }
    public void cargarEstadisticas(){
        try {
            Cliente cliente = new Cliente(this.direccionIp);
            DatosJugador datosJugador = cliente.getEstadisticas(this.idJugador);
            Jugador jugador = datosJugador.getJugador();
            this.lblNombreUsuario.setText(jugador.getNombreJugador());
            this.lblNumeroPartidasJugadas.setText("" + jugador.getPartidasJugadas());
            this.lblNumeroPartidasPerdidas.setText("" + jugador.getPartidasPerdidas());
            this.lblNumeroPartidasGanadas.setText("" + datosJugador.getPartidasGanadas());
            this.lblValorTiempoPromedio.setText(datosJugador.getTiempoPromedio());
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(VentanaEstadisticaController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage(rb.getString("errorConexion"), rb.getString("conexionServidor"), rb.getString("mensajeErrorIP"), Alert.AlertType.ERROR);
        }
    }
    
    public void btnAceptar_Click(){
        this.stage.close();
    }
}
