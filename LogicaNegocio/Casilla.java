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
    private ImageView panelMina;
    private CasillaListener casillaListener;
    
    public Casilla(int coordenadaX, int coordenadaY, CasillaListener casillaListener){
       this.casillaListener = casillaListener;
       this.coordenadaX = coordenadaX;
       this.coordenadaY = coordenadaY;  
       this.casillaCubierta = true;
       this.tieneMina = false;
       this.marcada = false;
       this.panelMina = new ImageView();
       panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/Color_Gris.PNG")));
       panelMina.setFitHeight(30);
       panelMina.setFitWidth(30);
       panelMina.setPreserveRatio(true);
       this.panelMina.addEventHandler(MouseEvent.MOUSE_CLICKED, this.imageEventHandler);
    }
    public ImageView getCasilla(){
        return this.panelMina;
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

    public void dispararEvento(){
        casillaListener.casillaSeleccionada(coordenadaX, coordenadaY, false);
    }
    public void descubrirCasilla(){
        this.casillaCubierta = false;
        panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/Color_Blanco.PNG")));
    }
    public void marcar(){
        if (!this.marcada){
            this.marcada = true;
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/flag.png")));
        }else{
            this.marcada = false;
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/Color_Gris.PNG")));
        }
    }
    public boolean estaCubierta(){
        return this.casillaCubierta;
    }
    public boolean estaMarcada(){
        return this.marcada;
    }
    public void agregarMina(){
        this.tieneMina = true;
    }
    public void mostrarMina(){
       panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/mina.png")));
       this.casillaCubierta = false;
    }
    public boolean tieneMina(){
        return this.tieneMina;
    }
    public void establecerNumeros(int numero){
        switch(numero){
        case 1:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/uno.png")));
            break;
        case 2:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/dos.png")));
            break;
        case 3:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/tres.png")));
            break;
        case 4:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/cuatro.png")));
            break;
        case 5:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/cinco.png")));
            break;
        case 6:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/seis.png")));
            break;
        case 7:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/siete.png")));
            break;
        case 8:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/ocho.png")));
            break;
        }     
    }
    public int getX(){
        return this.coordenadaX;
    }
    public int getY(){
        return this.coordenadaY;
    }
}   
