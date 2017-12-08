package LogicaNegocio.Controladores;

import AccesoDatos.RegistroArchivo;
import InterfazGrafica.VentanaEstadistica;
import InterfazGrafica.VentanaMejorJugador;
import LogicaNegocio.Jugador;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class VentanaConfiguracionController implements Initializable {
    @FXML
    private Label labelIdioma;
    @FXML
    private Label labelEstadisticas;
    @FXML
    private Label labelMejores;
    @FXML
    private RadioButton radioEspañol;
    @FXML
    private RadioButton radioIngles;
    @FXML
    private Button botonOk;
    
    private ResourceBundle rb;
    private Stage stage;
    private VentanaTableroController controlerTablero;
    private VentanaMejorJugadorController mejorJugadorController;
    private VentanaEstadisticaController estadisticaController;
    private int idJugador;
    private String direccionIp;
    
    private final String NOMBRE_ARCHIVO = "Idioma.txt";
    private final String NOMBRE_DIRECTORIO = "C:\\Buscaminas";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setIdJugador(int idJugador){
        this.idJugador = idJugador;
    }
    public void setDireccionIp(String direccionIp){
        this.direccionIp = direccionIp;
    }
    public void setControladorTablero(VentanaTableroController controller){
        this.controlerTablero = controller;
    }
    public void setControladorMejorJugador(VentanaMejorJugadorController controller){
        this.mejorJugadorController = controller;
    }
    public void setControladorEstadisticas(VentanaEstadisticaController controller){
        this.estadisticaController = controller;
    }
    
    public void internacionalizar(ResourceBundle resource){
        this.rb = resource; 
        labelIdioma.setText(rb.getString("labelIdioma"));
        labelEstadisticas.setText(rb.getString("labelEstadisticas"));
        labelMejores.setText(rb.getString("labelMejores"));
        radioEspañol.setText(rb.getString("radioEspañol"));
        radioIngles.setText(rb.getString("radioIngles"));
        botonOk.setText(rb.getString("botonOk"));
        this.stage.setTitle(rb.getString("nombreVentanaConfiguracion"));
    }
    public void botonOk_Click(){
        this.stage.close();
    }
    public void radioEspañol_click(){
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ESP");
        this.guardarArchivo(this.NOMBRE_DIRECTORIO + "\\" + this.NOMBRE_ARCHIVO, "ESP");
        this.internacionalizar(rb);
        this.controlerTablero.internacionalizar(rb);
        if(this.estadisticaController!=null){
            this.estadisticaController.internacionalizar(rb);
        }
        if(this.mejorJugadorController!=null){
            this.mejorJugadorController.internacionalizar(rb);
        }
    }
    public void radioIngles_click(){
        this.rb = ResourceBundle.getBundle("Recursos/Idioma_ING");
        this.guardarArchivo(this.NOMBRE_DIRECTORIO + "\\" + this.NOMBRE_ARCHIVO, "ING");
        this.internacionalizar(rb);
        this.controlerTablero.internacionalizar(rb);
        if(this.estadisticaController!=null){
            this.estadisticaController.internacionalizar(rb);
        }
        if(this.mejorJugadorController!=null){
            this.mejorJugadorController.internacionalizar(rb);
        }
    }
    public void guardarArchivo(String ruta, String contenido){
        File directorio = new File(this.NOMBRE_DIRECTORIO);
        if (!directorio.exists()){
            directorio.mkdir();
        }
        File archivo = new File(ruta);
        if (archivo.exists()){
            archivo.delete();
        }
        RegistroArchivo.guardar(archivo, contenido);
    }
    
    //Eventos
    
    public void labelEstadisticas_MouseEnter(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #0066ff");
    }
    public void labelEstadisticas_MouseDown(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #58ACFA");
    }
    public void labelEstadisticas_MouseUp(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #0066ff");
        new VentanaEstadistica(this, this.rb, this.idJugador, this.direccionIp);
    }
    public void labelEstadisticas_MouseLeave(){
        this.labelEstadisticas.setStyle("-fx-text-fill: #808080");
    }
    public void labelMejores_MouseEnter(){
        this.labelMejores.setStyle("-fx-text-fill: #0066ff");
    }
    public void labelMejores_MouseDown(){
        this.labelMejores.setStyle("-fx-text-fill: #58ACFA");
    }
    public void labelMejores_MouseUp(){
        this.labelMejores.setStyle("-fx-text-fill: #0066ff");
        new VentanaMejorJugador(this, this.rb, this.direccionIp);
    }
    public void labelMejores_MouseLeave(){
        this.labelMejores.setStyle("-fx-text-fill: #808080");
    }
}
