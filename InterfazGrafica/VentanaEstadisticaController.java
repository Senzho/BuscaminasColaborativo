package InterfazGrafica;

import LogicaNegocio.Cliente;
import LogicaNegocio.DatosJugador;
import LogicaNegocio.Jugador;
import java.net.URL;
import java.rmi.RemoteException;
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
    public void internacionalizar(ResourceBundle resource){
        this.rb = resource;
        lblNombreUsuario.setText(rb.getString("lblNombreUsuario"));
        lblPartidasJugadas.setText(rb.getString("lblPartidasJugadas"));
        lblPartidasGanadas.setText(rb.getString("lblPartidasGanadas"));
        lblPartidasPerdidas.setText(rb.getString("lblPartidasPerdidas"));
        lblTiempoPartida.setText(rb.getString("lblTiempoPartida"));
        btnAceptar.setText(rb.getString("btnAceptar"));
        this.stage.setTitle(rb.getString("nombreVentanaEstadistica"));
    }
    public void cargarEstadisticas(){
        try {
            Cliente cliente = new Cliente("192.168.43.174");
            DatosJugador datosJugador = cliente.getEstadisticas(this.idJugador);
            Jugador jugador = datosJugador.getJugador();
            this.lblNombreUsuario.setText(jugador.getNombreJugador());
            this.lblNumeroPartidasJugadas.setText("" + jugador.getPartidasJugadas());
            this.lblNumeroPartidasPerdidas.setText("" + jugador.getPartidasPerdidas());
            this.lblNumeroPartidasGanadas.setText("" + datosJugador.getPartidasGanadas());
            this.lblValorTiempoPromedio.setText(datosJugador.getTiempoPromedio());
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaEstadisticaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnAceptar_Click(){
        this.stage.close();
    }
}
