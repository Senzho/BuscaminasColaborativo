package LogicaNegocio.Controladores;

import InterfazGrafica.MessageFactory;
import LogicaNegocio.Jugador;
import LogicaNegocio.Solicitud;
import LogicaNegocio.TipoDificultad;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private Label labelRespuesta;
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
    @FXML
    private ListView listaJugadores;
    @FXML
    private Pane panelFacil;
    @FXML
    private Pane panelMedio;
    @FXML
    private Pane panelAvanzado;
    
    private ResourceBundle recursos;
    private Stage stage;
    private VentanaTableroController tableroController;
    private ObservableList<Jugador> jugadores;
    private int idJugador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.panelFacil.setStyle("-fx-border-width: 2;-fx-border-color: #BDBDBD;-fx-border-style: solid;");
        this.panelMedio.setStyle("-fx-border-width: 2;-fx-border-color: #BDBDBD;-fx-border-style: solid;");
        this.panelAvanzado.setStyle("-fx-border-width: 2;-fx-border-color: #BDBDBD;-fx-border-style: solid;");
    }    
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setTableroController(VentanaTableroController controller){
        this.tableroController = controller;
        this.tableroController.setNuevaPartidaController(this);
    }
    public void setListaJugadores(ObservableList<Jugador> jugadores){
        this.jugadores = jugadores;
        for (Jugador jugador : this.jugadores){
            int id = jugador.getIdJugador();
            if (id != this.idJugador){
                this.listaJugadores.getItems().add(jugador.getNombreJugador());
            }
        }
    }
    public void setIdJugador(int idJugador){
        this.idJugador = idJugador;
    }
    public void agregarJugador(Jugador jugador){
        this.jugadores.add(jugador);
        this.listaJugadores.getItems().add(jugador.getNombreJugador());
    }
    public void eliminarJugador(int idJugador){
        for (Object objeto : this.jugadores){
            Jugador jugador = (Jugador) objeto;
            if (jugador.getIdJugador() == idJugador){
                this.jugadores.remove(objeto);
                this.listaJugadores.getItems().remove(jugador.getNombreJugador());
                break;
            }
        }
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
        this.labelRespuesta.setText(recursos.getString("labelRespuestaListo"));
        this.stage.setTitle(this.recursos.getString("nombreVentanaNueva"));
    }
    public void cerrarVentana(){
        this.stage.close();
    }
    public int getId(String nombreJugador){
        int id = 0;
        for (Jugador jugador : this.jugadores){
            if (jugador.getNombreJugador().equals(nombreJugador)){
                id = jugador.getIdJugador();
            }
        }
        return id;
    }
    public void mostrarMensajeRechazo(){
        this.labelRespuesta.setText(recursos.getString("labelRespuestaListo"));
        MessageFactory.showMessage(recursos.getString("mensaje"), recursos.getString("solicitudRechazada"), recursos.getString("rechazoPartida"), Alert.AlertType.INFORMATION);
    }
    
    public void botonCanelar_Click(){
        this.cerrarVentana();
    }
    public void botonSolicitud_Click(){
        int idSolicitante = this.idJugador;
        int idCompañero = 0;
        Object itemSeleccionado = this.listaJugadores.getSelectionModel().getSelectedItem();
        if (itemSeleccionado != null){
            idCompañero = this.getId(itemSeleccionado.toString());
            Solicitud solicitud = null;
            if (this.radioFacil.isSelected()){
                solicitud = new Solicitud(idSolicitante, idCompañero, TipoDificultad.facil);
            }else if(this.radioMedio.isSelected()){
                solicitud = new Solicitud(idSolicitante, idCompañero, TipoDificultad.medio);
            }else if(this.radioAvanzado.isSelected()){
                solicitud = new Solicitud(idSolicitante, idCompañero, TipoDificultad.avanzado);
            }
            if (solicitud != null){
                this.labelRespuesta.setText(recursos.getString("labelRespuestaEspera"));
                this.tableroController.enviarSolicitud(solicitud);
            }else{
                MessageFactory.showMessage(recursos.getString("advertencia"), recursos.getString("partida"), recursos.getString("seleccionDificultad"), Alert.AlertType.WARNING);
            }
        }else{
            MessageFactory.showMessage(recursos.getString("advertencia"), recursos.getString("partida"), recursos.getString("seleccionCompañero"), Alert.AlertType.WARNING);
        }
    }
}
