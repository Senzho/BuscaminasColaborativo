package InterfazGrafica;

import LogicaNegocio.Jugador;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaConfiguracion extends Application{
    private VentanaTableroController controllerTablero;
    private ResourceBundle resource;
    private int idJugador;
    private String direccionIp;
    
    public VentanaConfiguracion(VentanaTableroController controller, ResourceBundle resource, int idJugador, String direccionIp){
        this.resource = resource;
        this.idJugador = idJugador;
        this.controllerTablero = controller;
        this.direccionIp = direccionIp;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaConfiguracion.fxml"));
        AnchorPane layout = loader.load();
        VentanaConfiguracionController controller = loader.getController();
        controller.setControladorTablero(this.controllerTablero);
        controller.setStage(primaryStage);
        controller.setIdJugador(this.idJugador);
        controller.setDireccionIp(this.direccionIp);
        controller.internacionalizar(resource);
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
