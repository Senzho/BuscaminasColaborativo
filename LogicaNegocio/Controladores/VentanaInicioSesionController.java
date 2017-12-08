package LogicaNegocio.Controladores;

import InterfazGrafica.MessageFactory;
import InterfazGrafica.VentanaAjusteDireccion;
import InterfazGrafica.VentanaTablero;
import LogicaNegocio.Cliente;
import LogicaNegocio.Jugador;
import LogicaNegocio.RegistroJugador;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        this.stage.setOnCloseRequest(eventoVentana);
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
            this.cliente = new Cliente(direccionIp);
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage(rb.getString("errorConexion"),rb.getString("conexionServidor"), rb.getString("mensajeErrorIP"), Alert.AlertType.ERROR);
        }
        try {
            this.socket = IO.socket("http://"+direccionIp+":7000");
            this.conectar();
        } catch (URISyntaxException ex) {
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
                Platform.runLater(()->{
                    if(!valido){
                        new VentanaTablero(rb, jugador, direccionIp);
                        stage.close();
                    }else{
                        MessageFactory.showMessage(rb.getString("error"), rb.getString("cuentaAcceso"),rb.getString("inicioSesionFallido"),Alert.AlertType.WARNING);
                    }
                });   
            }
        });
        socket.connect();
    }
    public String obtenerTextoAbsoluto(String texto){
        String textoAbsoluto = "";
        int lenght = texto.length();
        for (int i = 0; i < lenght; i++) {
            char caracter = texto.charAt(i);
            if (caracter != 32) {
                textoAbsoluto = textoAbsoluto + caracter;
            }
        }
        return textoAbsoluto;
    }

    public void btnIngresar_MouseUp() {
        String texto = this.obtenerTextoAbsoluto(txtNombreUsuario.getText());
        if (!texto.equals("")){
            try {
                jugador = this.cliente.validarSesión(texto);
                if (jugador != null) {
                    socket.emit("validarSesion",jugador.getIdJugador());
                } else {
                    MessageFactory.showMessage(rb.getString("error"), rb.getString("cuentaAcceso"),rb.getString("noEncontrado") +" "+ this.txtNombreUsuario.getText() +" "+ rb.getString("baseDatos"), Alert.AlertType.WARNING);
                }
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                MessageFactory.showMessage(rb.getString("errorConexion"), rb.getString("conexionServidor"), rb.getString("mensajeErrorIP"), Alert.AlertType.ERROR);
            }
        }else{
            MessageFactory.showMessage(rb.getString("error"), rb.getString("formatoNombre"), rb.getString("mensajeFormatoNombre"), Alert.AlertType.ERROR);
        }  
    }

    public void btnRegistrar_Click(){
        String texto = this.obtenerTextoAbsoluto(txtNombreUsuario.getText());
        if(!texto.equals("")){
            try {
                RegistroJugador registro = cliente.registrarJugador(texto);
                switch(registro){
                    case JUGADOR_APROBADO:
                        this.txtNombreUsuario.setText(texto);
                        MessageFactory.showMessage(rb.getString("exito"),rb.getString("registroJugador"), rb.getString("elJugador") + " " + texto + " " + rb.getString("registroExitoso"), Alert.AlertType.INFORMATION);
                        break;
                    case JUGADOR_EXISTENTE:
                        MessageFactory.showMessage(rb.getString("error"), rb.getString("registroJugador"),  rb.getString("elJugador")+ " " + texto + " " + rb.getString("yaExiste"), Alert.AlertType.ERROR);
                        break;
                    case ERROR_REGISTRO:
                        MessageFactory.showMessage(rb.getString("error"), rb.getString("registroJugador"), rb.getString("accesoNegado"), Alert.AlertType.NONE);
                        break;
                }
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                MessageFactory.showMessage(rb.getString("errorConexion"), rb.getString("conexionServidor"), rb.getString("mensajeErrorIP"), Alert.AlertType.ERROR);
            }
        }else{
             MessageFactory.showMessage(rb.getString("error"), rb.getString("formatoNombre"), rb.getString("mensajeFormatoNombre"), Alert.AlertType.ERROR);
        }
    }
    public void btnIp_Click(){
        new VentanaAjusteDireccion(this.rb);
        this.stage.close();
    }
    EventHandler<WindowEvent> eventoVentana = new EventHandler<WindowEvent>(){
        @Override
        public void handle(WindowEvent event) {
            socket.disconnect();
            System.exit(0);
        }
    };
}
