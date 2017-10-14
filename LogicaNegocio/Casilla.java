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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Casilla {
    private int coordenadaX;
    private int coordenadaY;
    private boolean casillaCubierta;
    private ImageView panelMina;
    
    public Casilla(int coordenadaX, int coordenadaY){
       this.coordenadaX = coordenadaX;
       this.coordenadaY = coordenadaY;  
       this.casillaCubierta = true;
       this.panelMina = new ImageView();
    }
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
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 2:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 3:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 4:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 5:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 6:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 7:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
            break;
        case 8:
            panelMina.setImage(new Image(this.getClass().getResourceAsStream("/")));
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
