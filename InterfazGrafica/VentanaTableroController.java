package InterfazGrafica;

import LogicaNegocio.Casilla;
import LogicaNegocio.Solicitud;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VentanaTableroController implements Initializable, CasillaListener {
    @FXML
    private ImageView botonConfiguracion;
    @FXML
    private ImageView botonPlayPause;
    @FXML
    private ImageView botonReply;
    @FXML
    private ImageView botonTerminar;
    @FXML
    private Label labelTiempo;
    @FXML
    private Label labelTextoMinas;
    @FXML
    private Label labelNumeroMinas;
    @FXML
    private Label resultadoJuego;
    @FXML
    private Label preguntaJuego;
    @FXML
    private Label respuestaSi;
    @FXML
    private Label respuestaNo;
    @FXML
    private GridPane gridJuego;
    
    private ResourceBundle resource;
    private VentanaNuevaPartidaController nuevaPartidaContrller;
    private Stage stage;
    
    private final Image GEAR = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/gear.png"));
    private final Image GEAR_HOVER = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/gear-hover.png"));
    private final Image GEAR_PRESSED = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/gear-pressed.png"));
    private final Image REPLY = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/reply.png"));
    private final Image REPLY_HOVER = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/reply-hover.png"));
    private final Image REPLY_PRESSED = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/reply-pressed.png"));
    private final Image TRIANGLE_RIGHT = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/triangle-right.png"));
    private final Image TRIANGLE_RIGHT_HOVER = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/triangle-right-hover.png"));
    private final Image TRIANGLE_RIGHT_PRESSED = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/triangle-right-pressed.png"));
    private final Image X = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/x.png"));
    private final Image X_HOVER = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/x-hover.png"));
    private final Image X_PRESSED = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/x-pressed.png"));
    private final String COLOR_ROJO = "RED";
    private final String COLOR_AMARILLO = "#ffb800";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resultadoJuego.setStyle("-fx-text-fill: " + this.COLOR_AMARILLO);
        this.gridJuego.setAlignment(Pos.CENTER);
        this.gridJuego.setStyle("-fx-border-width: 2;-fx-border-color: #848484;-fx-border-style: solid;");
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setNuevaPartidaController(VentanaNuevaPartidaController controller){
        this.nuevaPartidaContrller = controller;
    }
    public void internacionalizar(ResourceBundle rb){
        this.resource = rb;
        this.labelTextoMinas.setText(rb.getString("labelTextoMinas"));
        this.resultadoJuego.setText(rb.getString("win"));
        this.preguntaJuego.setText(rb.getString("newGame"));
        this.respuestaSi.setText(rb.getString("respuestaSi"));
        this.respuestaNo.setText(rb.getString("respuestaNo"));
        this.stage.setTitle(rb.getString("nombreVentanaTablero"));
        if(nuevaPartidaContrller != null){
            nuevaPartidaContrller.internacionalizar(resource);
        }
    }
    /*public ArrayList<Casilla> crearBotones(Solicitud solicitud){
        ArrayList<Casilla> casillas = new ArrayList<>();
        for (int i = 0; i < solicitud.getNumeroColumnas(); i++) {
            for (int j = 0; j < solicitud.getNumeroFilas(); j++) {
                casillas.add(new Casilla(i,j));
            }
        }
        return casillas;
    }*/
    public void cargarPanelJugador(Solicitud solicitud){
        //ArrayList<Casilla> casillas = this.crearBotones(solicitud);
        for (int i = 0; i < solicitud.getNumeroColumnas(); i++) {
            for (int j = 0; j < solicitud.getNumeroFilas(); j++) {
                Casilla casilla = new Casilla(i,j);//casilla.getCasilla()
                this.gridJuego.add(casilla.getCasilla(), i, j);
            }
        }
    }
    public void iniciarPartida(Solicitud solicitud){
        System.out.println("Iniciada!");
        System.out.println("Dificultad: " + solicitud.getTipoDificultad().name());
        System.out.println("Número de filas: " + solicitud.getNumeroFilas());
        System.out.println("Número de columnas: " + solicitud.getNumeroColumnas());
        System.out.println("Número de minas: " + solicitud.getNumeroMinas());
        this.labelNumeroMinas.setText("" + solicitud.getNumeroMinas());
        cargarPanelJugador(solicitud);
    }
    
    public void botonTerminar_MouseEnter(){
        this.botonTerminar.setImage(this.X_HOVER);
    }
    public void botonTerminar_MouseDown(){
        this.botonTerminar.setImage(this.X_PRESSED);
    }
    public void botonTerminar_MouseUp(){
        this.botonTerminar.setImage(this.X_HOVER);
    }
    public void botonTerminar_MouseLeave(){
        this.botonTerminar.setImage(this.X);
    }
    public void botonReply_MouseEnter(){
        this.botonReply.setImage(this.REPLY_HOVER);
    }
    public void botonReply_MouseDown(){
        this.botonReply.setImage(this.REPLY_PRESSED);
    }
    public void botonReply_MouseUp(){
        this.botonReply.setImage(this.REPLY_HOVER);
    }
    public void botonReply_MouseLeave(){
        this.botonReply.setImage(this.REPLY);
    }
    public void botonCOnfiguracion_MouseEnter(){
        this.botonConfiguracion.setImage(this.GEAR_HOVER);
    }
    public void botonCOnfiguracion_MouseDown(){
        this.botonConfiguracion.setImage(this.GEAR_PRESSED);
    }
    public void botonConfiguracion_MouseUp(){
        this.botonConfiguracion.setImage(this.GEAR_HOVER);
        new VentanaConfiguracion(this, resource);
    }
    public void botonCOnfiguracion_MouseLeave(){
        this.botonConfiguracion.setImage(this.GEAR);
    }
    public void botonPlayPause_MouseEnter(){
        this.botonPlayPause.setImage(this.TRIANGLE_RIGHT_HOVER);
    }
    public void botonPlayPause_MouseDown(){
        this.botonPlayPause.setImage(this.TRIANGLE_RIGHT_PRESSED);
    }
    public void botonPlayPause_MouseUp(){
        this.botonPlayPause.setImage(this.TRIANGLE_RIGHT_HOVER);
        new VentanaNuevaPartida(this, resource);
    }
    public void botonPlayPause_MouseLeave(){
        this.botonPlayPause.setImage(this.TRIANGLE_RIGHT);
    }
    public void respuestaSi_MouseEnter(){
        this.respuestaSi.setStyle("-fx-text-fill: #0066ff");
    }
    public void respuestaSi_MouseDown(){
        this.respuestaSi.setStyle("-fx-text-fill: #58ACFA");
    }
    public void respuestaSi_MouseUp(){
        this.respuestaSi.setStyle("-fx-text-fill: #0066ff");
    }
    public void respuestaSi_MouseLeave(){
        this.respuestaSi.setStyle("-fx-text-fill: #086600");
    }
    public void respuestaNo_MouseEnter(){
        this.respuestaNo.setStyle("-fx-text-fill: #0066ff");
    }
    public void respuestaNo_MouseDown(){
        this.respuestaNo.setStyle("-fx-text-fill: #58ACFA");
    }
    public void respuestaNo_MouseUp(){
        this.respuestaNo.setStyle("-fx-text-fill: #0066ff");
    }
    public void respuestaNo_MouseLeave(){
        this.respuestaNo.setStyle("-fx-text-fill: #086600");
    }

    @Override
    public boolean casillaSeleccionada(int coordenadaX, int coordenadaY) {
        return true;
    }
}
