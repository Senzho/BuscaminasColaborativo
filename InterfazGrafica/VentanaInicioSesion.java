
package InterfazGrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaInicioSesion extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaInicioSesion.fxml"));
        AnchorPane layout = loader.load();
        VentanaInicioSesionController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Iniciar sesión");
        Scene scene = new Scene(layout, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
}
