package InterfazGrafica;

import LogicaNegocio.Cliente;
import LogicaNegocio.Jugador;
import LogicaNegocio.RegistroJugador;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
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
    @FXML
    private Button btnIp;
    
    private ResourceBundle rb;
    private Stage stage;
    private Cliente cliente;
    private String direccionIp;
    private Socket socket;
    private Jugador jugador;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
    public void setDireccionIp(String direccionIp){
        this.direccionIp = direccionIp;
        try {
            this.socket = IO.socket("http://" + this.direccionIp + ":7000");
            this.conectar();
        } catch (URISyntaxException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.cliente = new Cliente(direccionIp);
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void conectar(){
        this.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                
            }
        }).on("jugadorConectado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                boolean valido = (boolean) os[0];
                if(valido == true){
                    MessageFactory.showMessage("advertencia", "ayuda", "jugadorConectado", Alert.AlertType.WARNING);
                }else{
                    new VentanaTablero(rb, jugador, direccionIp);
                    stage.close();
                }
            }
        });
    }

    public void btnIngresar_MouseUp() {
        try {
            jugador = this.cliente.validarSesión(this.txtNombreUsuario.getText());
            if (jugador != null) {
                this.socket.emit("buscarJugador", jugador.getIdJugador());
            } else {
                MessageFactory.showMessage("Error", "Cuenta de acceso", "No se encuentra " + this.txtNombreUsuario.getText() + " en la base de datos", Alert.AlertType.WARNING);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnRegistrar_Click(){
        try {
            RegistroJugador registro = cliente.registrarJugador(this.txtNombreUsuario.getText());
            switch(registro){
                case JUGADOR_APROBADO:
                    MessageFactory.showMessage("Éxito", "Registro de jugador", "El jugador: " + this.txtNombreUsuario.getText() + " fue registrado exitosamente", Alert.AlertType.INFORMATION);
                    break;
                case JUGADOR_EXISTENTE:
                    MessageFactory.showMessage("Error", "Registro de jugador", "El jugador: " + this.txtNombreUsuario.getText() + " ya existe", Alert.AlertType.ERROR);
                    break;
                case ERROR_REGISTRO:
                    MessageFactory.showMessage("Error", "Registro de jugador", "No se pudo acceder a la base de datos", Alert.AlertType.NONE);
                    break;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void btnIp_Click(){
        new VentanaAjusteDireccion(this.rb);
        this.stage.close();
    }
}
