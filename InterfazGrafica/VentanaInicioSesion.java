
package InterfazGrafica;

import LogicaNegocio.Controladores.VentanaInicioSesionController;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VentanaInicioSesion extends Application{
    private ResourceBundle recursos;
    private String direccionIp;
    
    public VentanaInicioSesion(ResourceBundle recursos,String direccionIp){
        this.recursos = recursos;
        this.direccionIp = direccionIp;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaInicioSesion.fxml"));
        AnchorPane layout = loader.load();
        VentanaInicioSesionController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setDireccionIp(this.direccionIp);
        controller.internacionalizar(this.recursos);
        Scene scene = new Scene(layout, 400, 140);
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
}
