package InterfazGrafica;

import LogicaNegocio.Solicitud;
import LogicaNegocio.TipoDificultad;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import org.json.JSONObject;

public class VentanaNuevaPartidaController implements Initializable {
    @FXML
    private Label labelJugadores;
    @FXML
    private Label labelDificultad;
    @FXML
    private Label labelFacil;
    @FXML
    private Label labelMedio;
    @FXML
    private Label labelAvanzado;
    @FXML
    private RadioButton radioFacil;
    @FXML
    private RadioButton radioMedio;
    @FXML
    private RadioButton radioAvanzado;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonSolicitud;
    
    private ResourceBundle recursos;
    private Stage stage;
    private VentanaTableroController tableroController;
    private Socket socket;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.socket = IO.socket("http://192.168.43.174:7000");
            this.conectar();
        } catch (URISyntaxException ex) {
            Logger.getLogger(VentanaNuevaPartidaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setTableroController(VentanaTableroController controller){
        this.tableroController = controller;
        this.tableroController.setNuevaPartidaController(this);
    }
    
    public void internacionalizar(ResourceBundle resource){
        this.recursos = resource;
        this.labelJugadores.setText(this.recursos.getString("labelJugadores"));
        this.labelDificultad.setText(this.recursos.getString("labelDificultad"));
        this.labelFacil.setText(this.recursos.getString("labelFacil"));
        this.labelMedio.setText(this.recursos.getString("labelMedio"));
        this.labelAvanzado.setText(this.recursos.getString("labelAvanzado"));
        this.radioFacil.setText(this.recursos.getString("radioFacil"));
        this.radioMedio.setText(this.recursos.getString("radioMedio"));
        this.radioAvanzado.setText(this.recursos.getString("radioAvanzado"));
        this.botonCancelar.setText(this.recursos.getString("botonCancelar"));
        this.botonSolicitud.setText(this.recursos.getString("botonSolicitud"));
        this.stage.setTitle(this.recursos.getString("nombreVentanaNueva"));
    }
    public void conectar(){
        this.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
            @Override
            public void call(Object... os) {
                
            }
        }).on("solicitud", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                JSONObject object = (JSONObject) os[0];
                Solicitud solicitud = new Solicitud();
                solicitud.setColumnas(object.getInt("numeroColumnas"));
                solicitud.setFilas(object.getInt("numeroFilas"));
                solicitud.setIdCompañero(object.getInt("idCompañero"));
                solicitud.setIdSolicitante(object.getInt("idSolicitante"));
                solicitud.setNumeroMinas(object.getInt("numeroMinas"));
                Platform.runLater(()->{
                    tableroController.iniciarPartidaCliente(solicitud);
                    cerrarVentana();
                });
            }
        });
        this.socket.connect();
    }
    public void cerrarVentana(){
        this.socket.disconnect();
        this.stage.close();
    }
    
    public void botonCanelar_Click(){
        this.cerrarVentana();
    }
    public void botonSolicitud_Click(){
        int idSolicitante = 1;
        int idCompañero = 2;
        Solicitud solicitud = null;
        if (this.radioFacil.isSelected()){
            solicitud = new Solicitud(idSolicitante, idCompañero, TipoDificultad.facil);
        }else if(this.radioMedio.isSelected()){
            solicitud = new Solicitud(idSolicitante, idCompañero, TipoDificultad.medio);
        }else if(this.radioAvanzado.isSelected()){
            solicitud = new Solicitud(idSolicitante, idCompañero, TipoDificultad.avanzado);
        }
        if (solicitud != null){
            socket.emit("solicitud", new JSONObject(solicitud));
            this.tableroController.iniciarPartida(solicitud);
        }else{
            MessageFactory.showMessage("Warning", "Partida", "No has seleccionado la dificultad", Alert.AlertType.NONE);
        }
    }
}
