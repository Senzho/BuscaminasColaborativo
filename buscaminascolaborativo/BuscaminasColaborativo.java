package buscaminascolaborativo;

import AccesoDatos.RegistroArchivo;
import InterfazGrafica.VentanaAjusteDireccion;
import InterfazGrafica.VentanaInicioSesion;
import java.io.File;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;

public class BuscaminasColaborativo extends Application {
    private static final String NOMBRE_DIRECTORIO = "C:\\Buscaminas";
    private static final String ARCHIVO_IDIOMA = "Idioma.txt";
    private static final String ARCHIVO_IP = "direccionIP.txt";
    private static final String SEPARADOR = "\\";
    
    @Override
    public void start(Stage primaryStage) {
        String defaultBundle = "Recursos/Idioma_ING";
        String bundle;
        File idioma = new File(BuscaminasColaborativo.NOMBRE_DIRECTORIO + BuscaminasColaborativo.SEPARADOR + BuscaminasColaborativo.ARCHIVO_IDIOMA);
        File direccionIp = new File(BuscaminasColaborativo.NOMBRE_DIRECTORIO + BuscaminasColaborativo.SEPARADOR + BuscaminasColaborativo.ARCHIVO_IP);
        if (idioma.exists()){
            String contenido = RegistroArchivo.leerLinea(idioma);
            if (contenido.equals("ESP")){
                bundle = "Recursos/Idioma_ESP";
            }else if (contenido.equals("ING")){
                bundle = "Recursos/Idioma_ING";
            }else{
                bundle = defaultBundle;
            }
        }else{
            bundle = defaultBundle;
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
