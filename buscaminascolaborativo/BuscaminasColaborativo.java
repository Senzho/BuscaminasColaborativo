package buscaminascolaborativo;

import InterfazGrafica.VentanaInicioSesion;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;

public class BuscaminasColaborativo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ResourceBundle recursos = ResourceBundle.getBundle("Recursos/Idioma_ING");
        new VentanaInicioSesion(recursos);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
