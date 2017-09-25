package InterfazGrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaNuevaPartida extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane layout = FXMLLoader.load(this.getClass().getResource("VentanaNuevaPartida.fxml"));
        primaryStage.setTitle("Nueva partida");
        Scene scene = new Scene(layout, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
