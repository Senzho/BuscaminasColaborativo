package InterfazGrafica;

import LogicaNegocio.Solicitud;
import LogicaNegocio.TipoDificultad;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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
    
    public void botonCanelar_Click(){
        this.stage.close();
    }
    public void botonSolicitud_Click(){
        //Id's correspondientes a los identificadores de los jugadores de la partida (no implementado)
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
            this.tableroController.iniciarPartida(solicitud);
            this.stage.close();
        }else{
            //Enviar mensaje de error
            System.out.println("No has seleccionado la dificultad");
        }
    }
}
