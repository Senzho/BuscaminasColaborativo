package InterfazGrafica;

import LogicaNegocio.Controladores.VentanaTableroController;
import LogicaNegocio.Jugador;
import io.socket.client.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaTablero extends Application{
    private ResourceBundle resource;
    private Jugador jugador;
    private String direccionIp;
    private Socket socket;
    
    public VentanaTablero(ResourceBundle rb, Jugador jugador, String direccionIp, Socket socket){
        this.jugador = jugador;
        this.resource = rb;
        this.direccionIp = direccionIp;
        this.socket = socket;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaTablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaTablero.fxml"));
        AnchorPane layout = loader.load();
        VentanaTableroController tableroController = loader.getController();
        tableroController.setStage(primaryStage);
        tableroController.setSocket(this.socket);
        tableroController.setDireccionIp(this.direccionIp);
        tableroController.setJugador(this.jugador);
        tableroController.internacionalizar(resource);
        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
