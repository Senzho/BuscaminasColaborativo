package buscaminascolaborativo;

import AccesoDatos.RegistroArchivo;
import InterfazGrafica.VentanaAjusteDireccion;
import InterfazGrafica.VentanaInicioSesion;
import java.io.File;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;

public class BuscaminasColaborativo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        String bundle = "Recursos/Idioma_ING";
        File idioma = new File("C:\\Buscaminas\\Idioma.txt");
        File direccionIp = new File("C:\\Buscaminas\\direccionIP.txt");
        if (idioma.exists()){
            String contenido = RegistroArchivo.leerLinea(idioma);
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
        if(!direccionIp.exists()){
            new VentanaAjusteDireccion(recursos);
        }else{
            String direccion = RegistroArchivo.leerLinea(direccionIp);
            new VentanaInicioSesion(recursos,direccion);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
