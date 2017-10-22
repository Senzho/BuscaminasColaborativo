/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;
/**
 *
 * @author Compaq-presario-cq43
 */
import InterfazGrafica.CasillaListener;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Casilla{
    private int coordenadaX;
    private int coordenadaY;
    private boolean casillaCubierta;
    private ImageView panelMina;
    private CasillaListener casillaListener;
    
    public Casilla(int coordenadaX, int coordenadaY){
       this.coordenadaX = coordenadaX;
       this.coordenadaY = coordenadaY;  
       this.casillaCubierta = true;
       this.panelMina = new ImageView();
       Image image = new Image(this.getClass().getResourceAsStream("/RecursosGraficos/tres.png"));
       panelMina.setImage(image);
       panelMina.setFitWidth(50);
       panelMina.setPreserveRatio(true);
       
       this.panelMina.addEventHandler(MouseEvent.MOUSE_EXITED, this.imageEventHandler);
    }
    public ImageView getCasilla(){
        return this.panelMina;
    } 
    EventHandler<MouseEvent> imageEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            EventType type = event.getEventType();
            if(type.equals(MouseEvent.MOUSE_EXITED)){
                System.out.println("click en "+coordenadaX+" : "+ coordenadaY);
            }
        }
    };

    public boolean descubrirCasilla(int coordenadaFila, int coordenadaColumna){
        if(coordenadaFila == coordenadaX && coordenadaColumna == coordenadaY){
            this.casillaCubierta = false;
        }
        return casillaCubierta;
    }
    public void agregarMina(){
       panelMina.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/mina.png")));        
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
    public int getx(){
        return this.coordenadaX;
    }
    public int getY(){
        return this.coordenadaY;
    }
}   
