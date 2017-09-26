
package InterfazGrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaInicioSesion extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane layout = FXMLLoader.load(this.getClass().getResource("VentanaInicioSesion.fxml"));
        primaryStage.setTitle("Iniciar sesión");
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
}
