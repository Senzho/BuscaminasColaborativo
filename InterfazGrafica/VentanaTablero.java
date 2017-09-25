package InterfazGrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaTablero extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane layout = FXMLLoader.load(this.getClass().getResource("VentanaTablero.fxml"));
        primaryStage.setTitle("Buscaminas");
        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
