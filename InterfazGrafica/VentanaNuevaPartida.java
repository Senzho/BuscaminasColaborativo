package InterfazGrafica;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaNuevaPartida extends Application{
    private VentanaTableroController tableroController;
    private ResourceBundle resource;
    public VentanaNuevaPartida (VentanaTableroController controler, ResourceBundle resource){
        this.resource = resource;
        this.tableroController = controler;
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
        tableroController.setNuevaPartidaController(controler);
        controler.internacionalizar(resource);
        primaryStage.setTitle("Nueva partida");
        Scene scene = new Scene(layout, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
