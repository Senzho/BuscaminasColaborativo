package InterfazGrafica;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaMejorJugador extends Application{
    private VentanaConfiguracionController configuracionController;
    private ResourceBundle resource;
    private String direccionIp;
    
    public VentanaMejorJugador(VentanaConfiguracionController controller, ResourceBundle resource, String direccionIp){
        this.resource = resource;
        this.direccionIp = direccionIp;
        this.configuracionController = controller;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaMejorJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaMejorJugador.fxml"));
        AnchorPane layout = loader.load();
        VentanaMejorJugadorController jugadorController = loader.getController();
        jugadorController.setStage(primaryStage);
        jugadorController.setDireccionIp(this.direccionIp);
        jugadorController.internacionalizar(resource);
        configuracionController.setControladorMejorJugador(jugadorController);
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}