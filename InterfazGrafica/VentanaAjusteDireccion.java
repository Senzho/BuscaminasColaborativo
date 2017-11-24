/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Compaq-presario-cq43
 */
public class VentanaAjusteDireccion extends Application {
    
    private ResourceBundle resource;
    
    public VentanaAjusteDireccion(ResourceBundle resource){
        this.resource = resource;
        try {
            this.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(VentanaAjusteDireccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VentanaAjusteDireccion.fxml"));
        AnchorPane layout = loader.load();
        VentanaAjusteDireccionController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.internacionalizar(resource);
        Scene scene = new Scene(layout, 438, 181);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
