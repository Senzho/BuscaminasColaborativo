package LogicaNegocio;

import InterfazGrafica.CasillaListener;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Casilla{
    private int coordenadaX;
    private int coordenadaY;
    private boolean casillaCubierta;
    private boolean tieneMina;
    private boolean marcada;
    private ImageView imagen;
    private CasillaListener casillaListener;
    /**
     * 
     * @param coordenadaX, posición en X del tablero.
     * @param coordenadaY, posición en Y del tablero.
     * @param casillaListener, la implementación de una interfaz que escuchará los
     *        eventos de selección de la casilla.
     */
    public Casilla(int coordenadaX, int coordenadaY, CasillaListener casillaListener){
       this.casillaListener = casillaListener;
       this.coordenadaX = coordenadaX;
       this.coordenadaY = coordenadaY;  
       this.casillaCubierta = true;
       this.tieneMina = false;
       this.marcada = false;
       this.imagen = new ImageView();
       imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/Color_Gris.PNG")));
       imagen.setFitHeight(30);
       imagen.setFitWidth(30);
       imagen.setPreserveRatio(true);
       this.imagen.addEventHandler(MouseEvent.MOUSE_CLICKED, this.imageEventHandler);
    }
    /**
     * 
     * @return imagen, un ImageView de la casilla.
     */
    public ImageView getCasilla(){
        return this.imagen;
    } 
    EventHandler<MouseEvent> imageEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.PRIMARY)){
                if (!marcada){
                    casillaListener.casillaSeleccionada(coordenadaX, coordenadaY, true);
                }
            }else{
                if (casillaCubierta){
                    casillaListener.casillaMarcada(coordenadaX, coordenadaY);
                }
            }
        }
    };
    /**
     * Invoca al evento casillaSeleccionada sin necesidad de hacer click con el mouse.
     */
    public void dispararEvento(){
        casillaListener.casillaSeleccionada(coordenadaX, coordenadaY, false);
    }
    /**
     * Establece una imágen que simula el descubrimiento de una casilla en el tablero.
     */
    public void descubrirCasilla(){
        this.casillaCubierta = false;
        imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/Color_Blanco.PNG")));
    }
    /**
     * Establece o quita una bandera en la casilla.
     */
    public void marcar(){
        if (!this.marcada){
            this.marcada = true;
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/flag.png")));
        }else{
            this.marcada = false;
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/Color_Gris.PNG")));
        }
    }
    /**
     * 
     * @return estaCubierta, regresa un valor boolean indicando si la casilla esta
     *         cubierta (true) o no (false).
     */
    public boolean estaCubierta(){
        return this.casillaCubierta;
    }
    /**
     * 
     * @return marcada, regresa un valor booleano indicando si la casilla esta marcada 
     *         (bandera) o no.
     */
    public boolean estaMarcada(){
        return this.marcada;
    }
    /**
     * Establece que la casilla esconde una mina.
     */
    public void agregarMina(){
        this.tieneMina = true;
    }
    /**
     * Muestra la mina en el ImageView.
     */
    public void mostrarMina(){
       imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/mina.png")));
       this.casillaCubierta = false;
    }
    /**
     * 
     * @return tieneMina, regresa un valor booleano indicando si la casilla esconde
     *         una mina (true) o no (false).
     */
    public boolean tieneMina(){
        return this.tieneMina;
    }
    /**
     * Muestra en forma de imágen el número de minas del rango de la casilla.
     * @param numero, el número de minas correspondiente al rango de la casilla.
     */
    public void establecerNumeros(int numero){
        switch(numero){
        case 1:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/uno.png")));
            break;
        case 2:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/dos.png")));
            break;
        case 3:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/tres.png")));
            break;
        case 4:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/cuatro.png")));
            break;
        case 5:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/cinco.png")));
            break;
        case 6:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/seis.png")));
            break;
        case 7:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/siete.png")));
            break;
        case 8:
            imagen.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/ocho.png")));
            break;
        }     
    }
    /**
     * 
     * @return coordenadaX, regresa la posición en X de la casilla en el tablero.
     */
    public int getX(){
        return this.coordenadaX;
    }
    /**
     * 
     * @return coordenadaY, regresa la posición en Y de la casilla en el tablero.
     */
    public int getY(){
        return this.coordenadaY;
    }
}   
