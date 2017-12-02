package InterfazGrafica;

import LogicaNegocio.Casilla;
import LogicaNegocio.Cliente;
import LogicaNegocio.Jugador;
import LogicaNegocio.Partida;
import LogicaNegocio.Reproductor;
import LogicaNegocio.Solicitud;
import LogicaNegocio.TimerBuscaminas;
import LogicaNegocio.TimerListener;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.JSONArray;
import org.json.JSONObject;

public class VentanaTableroController implements Initializable, CasillaListener, TimerListener {
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
    @FXML
    private Label nombreCuentaLabel;
    @FXML
    private Label labelBienvenido;
    @FXML
    private ImageView imagenSemaforo;
    @FXML
    private Label labelTextoSemaforo;
    
    private ResourceBundle resource;
    private VentanaNuevaPartidaController nuevaPartidaContrller;
    private Stage stage;
    private ArrayList<Casilla> historial;
    private ArrayList<Casilla> minas;
    private Casilla[][] matrizCasillas;
    private int numeroColumnas;
    private int numeroFilas;
    private Jugador jugador;
    private Socket socket;
    private ObservableList<Jugador> listaJugadores;
    private Solicitud solicitudTurno;
    private boolean miTurno;
    private Cliente cliente;
    private TimerBuscaminas timer;
    private String direccionIp;
    
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
    private final Image TRAFFIC_LIGHT_RED = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/traffic_light_red.png"));
    private final Image TRAFFIC_LIGHT_GREEN = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/traffic_light_green.png"));
    private final String COLOR_ROJO = "RED";
    private final String COLOR_AMARILLO = "#ffb800";
    private final String TIEMPO_CERO = "00:00";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.timer = new TimerBuscaminas(this);
        this.miTurno = false;
        this.gridJuego.setStyle("-fx-border-width: 2;-fx-border-color: #848484;-fx-border-style: solid;");
        this.gridJuego.setHgap(1);
        this.gridJuego.setVgap(1);
        this.historial = new ArrayList();
        this.minas = new ArrayList();
        this.listaJugadores = FXCollections.observableArrayList();
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
        this.stage.setOnCloseRequest(this.windowHandler);
    }
    public void setDireccionIp(String direccionIp){
        this.direccionIp = direccionIp;
        try {
            this.socket = IO.socket("http://" + this.direccionIp + ":7000");
            this.conectar();
        } catch (URISyntaxException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.cliente = new Cliente(this.direccionIp);
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setJugador(Jugador jugador){
        this.jugador = jugador;
        this.nombreCuentaLabel.setText(jugador.getNombreJugador());
        this.socket.emit("jugadorConectado", new JSONObject(this.jugador));
    }
    public void setNuevaPartidaController(VentanaNuevaPartidaController controller){
        this.nuevaPartidaContrller = controller;
    }
    public void internacionalizar(ResourceBundle rb){
        this.resource = rb;
        if (this.imagenSemaforo.getImage() != null){
            if (this.miTurno){
                this.labelTextoSemaforo.setText(rb.getString("enTurno"));
            }else{
                this.labelTextoSemaforo.setText(rb.getString("noEnTurno"));
            }
        } 
        this.labelBienvenido.setText(rb.getString("labelBienvenido"));
        this.labelTextoMinas.setText(rb.getString("labelTextoMinas"));
        if (this.resultadoJuego.getText().startsWith("Epic") || this.resultadoJuego.getText().startsWith("Gan")){
            this.resultadoJuego.setText(rb.getString("win"));
        }else{
            this.resultadoJuego.setText(rb.getString("gameOver"));
        }
        this.preguntaJuego.setText(rb.getString("newGame"));
        this.respuestaSi.setText(rb.getString("respuestaSi"));
        this.respuestaNo.setText(rb.getString("respuestaNo"));
        this.stage.setTitle(rb.getString("nombreVentanaTablero"));
        if(nuevaPartidaContrller != null){
            nuevaPartidaContrller.internacionalizar(resource);
        }
    }
    public void semaforo(Image imagen, String texto){
        this.labelTextoSemaforo.setText(texto);
        this.imagenSemaforo.setImage(imagen);
    }
    public void cargarPanelJugador(Solicitud solicitud){
        this.ocultarEtiquetas();
        this.matrizCasillas = new Casilla[solicitud.getNumeroColumnas()][solicitud.getNumeroFilas()];
        ColumnConstraints columnConstraints = new ColumnConstraints();
        //columnConstraints.setHalignment(HPos.CENTER);
        //columnConstraints.setHgrow(Priority.ALWAYS);
        RowConstraints rowConstraints = new RowConstraints();
        //rowConstraints.setValignment(VPos.CENTER);
        //rowConstraints.setVgrow(Priority.ALWAYS);
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
    }
    public void iniciarPartida(Solicitud solicitud){
        this.reestablecerGrid();
        this.historial.clear();
        this.minas.clear();
        this.numeroFilas = solicitud.getNumeroFilas();
        this.numeroColumnas = solicitud.getNumeroColumnas();
        this.labelNumeroMinas.setText("" + solicitud.getNumeroMinas());
        cargarPanelJugador(solicitud);
        this.timer.start(); 
        if (this.miTurno){
            this.semaforo(this.TRAFFIC_LIGHT_GREEN, this.resource.getString("enTurno"));
        }else{
            this.semaforo(this.TRAFFIC_LIGHT_RED, this.resource.getString("noEnTurno"));
        }
    }
    public void mostrarMinas(){
        for (Casilla casilla : this.minas){
            casilla.mostrarMina();
        }
    }
    public void agregarMina(int x, int y){
        Casilla casilla = this.matrizCasillas[y][x];
        casilla.agregarMina();
        if(! this.minas.contains(casilla)){
            this.minas.add(casilla);       
        }
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
        casilla.descubrirCasilla();
        if (minas > 0){
            casilla.establecerNumeros(minas);
        }else{
            for (Casilla cas : rango){
                if (!this.yaRevisado(cas)){
                    this.historial.add(cas);
                    this.buscar(cas.getX(), cas.getY());
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
    public void partidaTerminada(boolean ganada){
        this.timer.stop();
        this.miTurno = false;
        this.semaforo(null, "");
        this.preguntaJuego.setVisible(true);
        this.resultadoJuego.setVisible(true);
        this.respuestaNo.setVisible(true);
        this.respuestaSi.setVisible(true);
        String colorEstilo;
        String mensaje;
        Reproductor reproductor;
        if (ganada){
            reproductor = new Reproductor("/RecursosAudio/win.wav");
            colorEstilo = this.COLOR_AMARILLO;
            mensaje = this.resource.getString("win");
        }else{
            reproductor = new Reproductor("/RecursosAudio/fail.wav");
            colorEstilo = this.COLOR_ROJO;
            mensaje = this.resource.getString("gameOver");
        }
        this.resultadoJuego.setStyle("-fx-text-fill: " + colorEstilo);
        this.resultadoJuego.setText(mensaje);
        this.solicitudTurno = null;
        this.aumentarCuentaPartida(ganada);
    }
    public void ocultarEtiquetas(){
        this.preguntaJuego.setVisible(false);
        this.resultadoJuego.setVisible(false);
        this.respuestaNo.setVisible(false);
        this.respuestaSi.setVisible(false);
    }
    public void reestablecerGrid(){
        this.gridJuego.getChildren().clear();
        this.gridJuego.getRowConstraints().clear();
        this.gridJuego.getColumnConstraints().clear();
    }
    public void mostrarVentanaNuevaPartida(){
        new VentanaNuevaPartida(this, this.resource, this.listaJugadores, this.jugador.getIdJugador());
    }
    public void enviarSolicitud(Solicitud solicitud){
    	this.socket.emit("solicitudPartida", new JSONObject(solicitud));
        this.miTurno = true;
    }
    public void registrarPartida(int idJugador, int idCompañero){
        String dificultad = "";
        switch(this.minas.size()){
            case 15:
            dificultad = "fácil";
            break;
            case 30:
            dificultad = "medio";
            break;
            case 45:
            dificultad = "avanzado";
            break;
        }
        Partida partidaJugador = new Partida(dificultad, this.labelTiempo.getText(), idJugador);
        Partida partidaCompañero = new Partida(dificultad, this.labelTiempo.getText(), idCompañero);
        try {
            if(!this.cliente.registrarPartida(partidaJugador) || !this.cliente.registrarPartida(partidaCompañero)){
                MessageFactory.showMessage("Error", "Partida", "No se pudo registrar la partida", Alert.AlertType.ERROR);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void aumentarCuentaPartida(boolean partidaGanada){
        this.jugador.setPartidasJugadas(this.jugador.getPartidasJugadas() + 1);
        if (!partidaGanada){
            this.jugador.setPartidasPerdidas(this.jugador.getPartidasPerdidas() + 1);
        }
        try {
            this.cliente.editarJugador(jugador);
        } catch (RemoteException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void conectar(){
        this.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                
            }
        }).on("iniciarPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                JSONObject solicitudJson = (JSONObject) os[0];
                Solicitud solicitud = new Solicitud();
                solicitud.setColumnas(solicitudJson.getInt("numeroColumnas"));
                solicitud.setFilas(solicitudJson.getInt("numeroFilas"));
                solicitud.setIdCompañero(solicitudJson.getInt("idCompañero"));
                solicitud.setIdSolicitante(solicitudJson.getInt("idSolicitante"));
                solicitud.setNumeroMinas(solicitudJson.getInt("numeroMinas"));
                solicitudTurno = solicitud;
                Platform.runLater(()->{
                    iniciarPartida(solicitud);
                    if (nuevaPartidaContrller != null){
                        nuevaPartidaContrller.cerrarVentana();
                    }
                    JSONArray arregloJson = (JSONArray) os[1];
                    for (int i = 0; i < arregloJson.length(); i ++){
                        Object objecto = arregloJson.get(i);
                        JSONObject jsonObject = (JSONObject) objecto;
                        agregarMina(jsonObject.getInt("coordenadaX"), jsonObject.getInt("coordenadaY"));
                    }
                });
            }
        }).on("nuevoJugador", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                JSONObject objecto = (JSONObject) os[0];
                Jugador jugador = new Jugador(objecto.getInt("idJugador"), objecto.getString("nombreJugador"), 0, 0);
                Platform.runLater(()->{
                    if (nuevaPartidaContrller != null){
                        nuevaPartidaContrller.agregarJugador(jugador);
                    }
                });
            }
        }).on("jugadorLista", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                listaJugadores.clear();
                JSONArray arregloJson = (JSONArray) os[0];
                for (int i = 0; i < arregloJson.length(); i ++){
                    Object objecto = arregloJson.get(i);
                    JSONObject jsonObject = (JSONObject) objecto;
                    Jugador jugador = new Jugador(jsonObject.getInt("idJugador"), jsonObject.getString("nombreJugador"), 0, 0);
                    listaJugadores.add(jugador);
                }
                Platform.runLater(()->{
                    mostrarVentanaNuevaPartida();
                });
            }
        }).on("solicitud", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(()->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Nueva partida");
                    alert.setHeaderText("Solicitud");
                    alert.setContentText("El jugador " + os[1].toString() + " quiere jugar una partida contigo");
                    Optional<ButtonType> respuesta = alert.showAndWait();
                    String aceptado;
                    if (respuesta.get().getButtonData().equals(ButtonData.OK_DONE)){
                        aceptado = "aceptado";
                    }else{
                        aceptado = "noAceptado";
                    }
                    socket.emit("respuestaPartida", aceptado, os[0]);
                });
            }
        }).on("rechazado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                miTurno = false;
                Platform.runLater(()->{
                    nuevaPartidaContrller.mostrarMensajeRechazo();
                });
            }
        }).on("tiroRecibido", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                miTurno = true;
                int x = Integer.parseInt(String.valueOf(os[0]));
                int y = Integer.parseInt(String.valueOf(os[1]));
                Platform.runLater(()->{
                    semaforo(TRAFFIC_LIGHT_GREEN, resource.getString("enTurno"));
                    matrizCasillas[x][y].dispararEvento();
                });
            }
        }).on("jugadorDesconectado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() ->{  
                    if (nuevaPartidaContrller != null){
                        int idJugador = Integer.parseInt(String.valueOf(os[0]));
                        nuevaPartidaContrller.eliminarJugador(idJugador);
                    }  
                });
            }
        }).on("partidaTerminada", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(()->{
                    MessageFactory.showMessage("Información", "Partida", "Tu compañero decidió terminar la partida", Alert.AlertType.INFORMATION);
                    reestablecerGrid();
                    semaforo(null, "");
                    labelNumeroMinas.setText("0");
                    timer.stop();
                    labelTiempo.setText("00:00");
                    solicitudTurno = null;
                });
            }
        }).on("marcaRecibida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                int x = Integer.parseInt(String.valueOf(os[0]));
                int y = Integer.parseInt(String.valueOf(os[1]));
                Platform.runLater(()->{
                    matrizCasillas[x][y].marcar();
                });
            }
        });
        this.socket.connect();
    }
    public int todoDescubierto(){
        int cont = 0;
        for (int i = 0; i < numeroColumnas; i++) {
            for (int j = 0; j < numeroFilas; j++) {
                if(this.matrizCasillas[i][j].estaCubierta() == false && matrizCasillas[i][j].tieneMina() == false){
                    cont+=1;
                }
            }
        }
        return cont;
    }
    public int ganarParida(){
        int numero = 0;
        for (int i = 0; i < this.numeroColumnas; i++) {
            for (int j = 0; j < this.numeroFilas; j++) {
                if(this.matrizCasillas[i][j].estaCubierta() && matrizCasillas[i][j].tieneMina()){
                    numero++;
                }
            }
        }
        return numero;
    }
    
    //Eventos:
    
    public void botonTerminar_MouseEnter(){
        this.botonTerminar.setImage(this.X_HOVER);
    }
    public void botonTerminar_MouseDown(){
        this.botonTerminar.setImage(this.X_PRESSED);
    }
    public void botonTerminar_MouseUp(){
        this.botonTerminar.setImage(this.X_HOVER);
        this.socket.emit("terminarPartida", this.solicitudTurno.getIdCompañero());
        reestablecerGrid();
        semaforo(null, "");
        labelNumeroMinas.setText("0");
        timer.stop();
        labelTiempo.setText("00:00");
        solicitudTurno = null;
        MessageFactory.showMessage("Infromación", "Partida", "Partida terminada", Alert.AlertType.INFORMATION);
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
        new VentanaConfiguracion(this, resource, this.jugador.getIdJugador(), this.direccionIp);
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
        this.socket.emit("getJugadores");
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
        this.socket.emit("getJugadores");
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
        this.labelNumeroMinas.setText("0");
        this.labelTiempo.setText("00:00");
        this.ocultarEtiquetas();
        this.reestablecerGrid();
    }
    public void respuestaNo_MouseLeave(){
        this.respuestaNo.setStyle("-fx-text-fill: #086600");
    }

    @Override
    public void casillaSeleccionada(int coordenadaX, int coordenadaY, boolean emitir) {
        if (this.miTurno){
            if (emitir){
                this.miTurno = false;
                this.socket.emit("tiro", coordenadaX, coordenadaY, this.solicitudTurno.getIdCompañero());
                this.semaforo(this.TRAFFIC_LIGHT_RED, this.resource.getString("noEnTurno"));
            }
            if (this.matrizCasillas[coordenadaX][coordenadaY].tieneMina()){
                this.mostrarMinas();
                this.partidaTerminada(false);
            }else{
                this.buscar(coordenadaX, coordenadaY);
                if((this.numeroColumnas* this.numeroFilas)==(todoDescubierto()+ganarParida())){
                    if (emitir){
                        this.registrarPartida(this.jugador.getIdJugador(), this.solicitudTurno.getIdCompañero());
                    }
                    this.partidaTerminada(true);
                }
            }
        }else{
            MessageFactory.showMessage("Información", "Turno", "Debes esperar tu turno", Alert.AlertType.INFORMATION);
        }
    }
    @Override
    public void casillaMarcada(int x, int y) {
        this.socket.emit("marca", x, y, this.solicitudTurno.getIdCompañero());
        this.matrizCasillas[x][y].marcar();
    }
    
    EventHandler<WindowEvent> windowHandler = new EventHandler<WindowEvent>(){
        @Override
        public void handle(WindowEvent event) {
            int id = jugador.getIdJugador();
            socket.emit("jugadorDesconectado", id);
            if (solicitudTurno != null){
                socket.emit("terminarPartida", solicitudTurno.getIdCompañero());
            }
            socket.disconnect();
            System.exit(0);
        }
    };

    @Override
    public void eventoTimer(String tiempo) {
        Platform.runLater(()->{
            this.labelTiempo.setText(tiempo);
        });
    }
}