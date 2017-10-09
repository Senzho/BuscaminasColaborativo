package InterfazGrafica;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaEstadistica extends Application{
    private VentanaConfiguracionController configuracionController;
    private ResourceBundle resource;
    public VentanaEstadistica(VentanaConfiguracionController controller, ResourceBundle resource){
        this.resource = resource;
        this.configuracionController = controller;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaEstadistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaEstadistica.fxml"));
        AnchorPane layout = loader.load();
        VentanaEstadisticaController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.internacionalizar(this.resource);
        this.configuracionController.setControladorEstadisticas(controller);
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
