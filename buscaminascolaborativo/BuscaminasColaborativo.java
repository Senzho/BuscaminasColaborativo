package buscaminascolaborativo;

import AccesoDatos.RegistroIdioma;
import InterfazGrafica.VentanaInicioSesion;
import java.io.File;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;

public class BuscaminasColaborativo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        String bundle = "Recursos/Idioma_ING";
        File idioma = new File("C:\\Users\\Victor Javier\\Documents\\IdiomaBuscaminas.txt");
        if (idioma.exists()){
            String contenido = RegistroIdioma.obtenerIdioma(idioma);
            switch(contenido){
                case "ESP":
                    bundle = "Recursos/Idioma_ESP";
                    break;
                case "ING":
                    bundle = "Recursos/Idioma_ING";
                    break;
            }
        }
        ResourceBundle recursos = ResourceBundle.getBundle(bundle);
        new VentanaInicioSesion(recursos);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
