package InterfazGrafica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaEstadistica extends Application{
    private VentanaConfiguracionController configuracionController;
    public VentanaEstadistica(VentanaConfiguracionController controller){
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
        this.configuracionController.setControladorEstadisticas(controller);
        primaryStage.setTitle("Estadisticas");
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
