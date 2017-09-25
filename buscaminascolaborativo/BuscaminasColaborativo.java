package buscaminascolaborativo;

import InterfazGrafica.VentanaTablero;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

public class BuscaminasColaborativo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VentanaTablero ventana = new VentanaTablero();
        try {
            ventana.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(BuscaminasColaborativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
