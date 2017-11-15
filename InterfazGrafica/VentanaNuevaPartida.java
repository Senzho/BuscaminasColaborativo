package InterfazGrafica;

import LogicaNegocio.Jugador;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaNuevaPartida extends Application{
    private VentanaTableroController tableroController;
    private ResourceBundle resource;
    private int idJugador;
    private ObservableList<Jugador> jugadores;
    
    public VentanaNuevaPartida (VentanaTableroController controler, ResourceBundle resource, ObservableList<Jugador> jugadores, int idJugador){
        this.resource = resource;
        this.tableroController = controler;
        this.jugadores = jugadores;
        this.idJugador = idJugador;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaNuevaPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaNuevaPartida.fxml"));
        AnchorPane layout = loader.load();
        VentanaNuevaPartidaController controler = loader.getController();
        controler.setStage(primaryStage);
        controler.setTableroController(this.tableroController);
        controler.setListaJugadores(this.jugadores);
        controler.setIdJugador(this.idJugador);
        tableroController.setNuevaPartidaController(controler);
        controler.internacionalizar(resource);
        Scene scene = new Scene(layout, 860, 460);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
