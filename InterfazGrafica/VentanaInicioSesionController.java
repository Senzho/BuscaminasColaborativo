package InterfazGrafica;

import LogicaNegocio.Cliente;
import LogicaNegocio.Jugador;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaInicioSesionController implements Initializable {
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TextField txtNombreUsuario;
    
    private ResourceBundle rb;
    private Stage stage;
    private Cliente cliente;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.cliente = new Cliente("192.168.43.174");
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void internacionalizar(ResourceBundle resources){
        this.rb = resources;
        lblNombreUsuario.setText(rb.getString("lblNombreUsuario"));
        btnIngresar.setText(rb.getString("btnIngresar"));
        this.btnRegistrar.setText(rb.getString("btnRegistrar"));
        this.stage.setTitle(rb.getString("nombreVentanaInicio"));
    }
    public void btnIngresar_MouseUp(){
        try {
            Jugador jugador = this.cliente.validarSesión(this.txtNombreUsuario.getText());
            if (jugador != null){
                new VentanaTablero(this.rb, jugador);
                this.stage.close();
            }else{
                MessageFactory.showMessage("Error", "Cuenta de acceso", "No se encuentra " + this.txtNombreUsuario.getText() + " en la base de datos", Alert.AlertType.WARNING);    
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void btnRegistrar_Click(){
        try {
            if (cliente.registrarJugador(this.txtNombreUsuario.getText())){
                MessageFactory.showMessage("Éxito", "Registro de jugador", "El jugador: " + this.txtNombreUsuario.getText() + " fue registrado exitosamente", Alert.AlertType.INFORMATION);
            }else{
                MessageFactory.showMessage("Error", "Registro de jugador", "El jugador: " + this.txtNombreUsuario.getText() + " no pudo ser registrado", Alert.AlertType.ERROR);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
