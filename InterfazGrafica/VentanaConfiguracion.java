package InterfazGrafica;

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
    public VentanaConfiguracion(VentanaTableroController controller, ResourceBundle resource){
        this.resource = resource;
        this.controllerTablero = controller;
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
        controller.internacionalizar(resource);
        controller.setStage(primaryStage);
        primaryStage.setTitle("Configuración");
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
