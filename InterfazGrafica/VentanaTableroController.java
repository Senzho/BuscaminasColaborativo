package InterfazGrafica;

import LogicaNegocio.Casilla;
import LogicaNegocio.Solicitud;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
    private ArrayList<Casilla> historial;
    private ArrayList<Casilla> minas;
    private Casilla[][] matrizCasillas;
    private int numeroColumnas;
    private int numeroFilas;
    
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
        this.gridJuego.setStyle("-fx-border-width: 2;-fx-border-color: #848484;-fx-border-style: solid;");
        this.gridJuego.setHgap(1);
        this.gridJuego.setVgap(1);
        this.historial = new ArrayList();
        this.minas = new ArrayList();
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
    public void cargarPanelJugador(Solicitud solicitud){
        this.matrizCasillas = new Casilla[solicitud.getNumeroColumnas()][solicitud.getNumeroFilas()];
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        columnConstraints.setHgrow(Priority.ALWAYS);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.CENTER);
        rowConstraints.setVgrow(Priority.ALWAYS);
        for (int i = 0; i < solicitud.getNumeroColumnas(); i ++){
            this.gridJuego.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < solicitud.getNumeroFilas(); i ++){
            this.gridJuego.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < solicitud.getNumeroColumnas(); i++) {
            for (int j = 0; j < solicitud.getNumeroFilas(); j++) {
                this.matrizCasillas[i][j] = new Casilla(i,j, this);
                this.gridJuego.add(this.matrizCasillas[i][j].getCasilla(), i, j);
            }
        }
        this.agregarMinas(solicitud.getNumeroMinas());
    }
    public void iniciarPartida(Solicitud solicitud){
        this.numeroFilas = solicitud.getNumeroFilas();
        this.numeroColumnas = solicitud.getNumeroColumnas();
        System.out.println("Iniciada!");
        System.out.println("Dificultad: " + solicitud.getTipoDificultad().name());
        System.out.println("Número de filas: " + solicitud.getNumeroFilas());
        System.out.println("Número de columnas: " + solicitud.getNumeroColumnas());
        System.out.println("Número de minas: " + solicitud.getNumeroMinas());
        this.labelNumeroMinas.setText("" + solicitud.getNumeroMinas());
        cargarPanelJugador(solicitud);
    }
    public void mostrarMinas(){
        for (Casilla casilla : this.minas){
            casilla.mostrarMina();
        }
    }
    public void agregarMina(int x, int y){
        Casilla casilla = this.matrizCasillas[y][x];
        casilla.agregarMina();
        this.minas.add(casilla);
    }
    public void agregarMinas(int numeroMinas){
        for (int i = 0; i < numeroMinas; i ++){
            int y = this.getRandom(0, this.numeroFilas - 1);
            int x = this.getRandom(0, this.numeroColumnas - 1);
            this.agregarMina(y, x);
        }
    }
    public int getRandom(int min, int max){
        int numero = 0;
        numero = (int)(Math.random()*(max - min + 1 ) + min);
        return numero;
    }
    public ArrayList<Casilla> getRango(Casilla casilla){
        ArrayList<Casilla> rango = new ArrayList();
        int x = casilla.getX();
        int y = casilla.getY();
        int xInicio = x - 1;
        int xFin = x + 1;
        int yInicio = y - 1;
        int yFin = y + 1;
        if (xInicio < 0){
            xInicio = 0;
        }
        if (yInicio < 0){
            yInicio = 0;
        }
        if (xFin > this.numeroColumnas - 1){
            xFin = this.numeroColumnas -1;
        }
        if (yFin > this.numeroFilas - 1){
            yFin = this.numeroFilas - 1;
        }
        for (int i = yInicio; i < yFin + 1; i ++){
            for (int c = xInicio; c < xFin + 1; c ++){
                rango.add(this.matrizCasillas[c][i]);
            } 
        }
        return rango;
    }
    public int getMinasPorRango(ArrayList<Casilla> rango){
        int minas = 0;
        for (Casilla casilla : rango){
            if (casilla.tieneMina()){
                minas ++;
            }
        }
        return minas;
    }
    public void buscar(int x, int y){
        Casilla casilla = this.matrizCasillas[x][y];
        ArrayList<Casilla> rango = this.getRango(casilla);
        int minas = this.getMinasPorRango(rango);
        if (minas > 0){
            casilla.descubrirCasilla();
            casilla.establecerNumeros(minas);
        }else{
            for (Casilla cas : rango){
                if (!cas.tieneMina()){
                    int minasRango = this.getMinasPorRango(this.getRango(cas));
                    if (minasRango > 0){
                        cas.descubrirCasilla();
                        cas.establecerNumeros(minasRango);
                    }else{
                        cas.descubrirCasilla();
                        if (!this.yaRevisado(cas)){
                            this.historial.add(cas);
                            this.buscar(cas.getX(), cas.getY());
                        }
                    }
                }
            }
        }
    }
    public boolean yaRevisado(Casilla casilla){
        boolean revisado = false;
        for (Casilla cas : this.historial){
            if (cas.equals(casilla)){
                revisado = true;
                break;
            }
        }
        return revisado;
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
    public void casillaSeleccionada(int coordenadaX, int coordenadaY) {
        if (this.matrizCasillas[coordenadaX][coordenadaY].tieneMina()){
            this.mostrarMinas();
        }else{
            this.buscar(coordenadaX, coordenadaY);
        }
    }
}
