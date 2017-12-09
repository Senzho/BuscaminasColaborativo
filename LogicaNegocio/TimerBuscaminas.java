/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.Timer;

/**
 *
 * @author Compaq-presario-cq43
 */
public class TimerBuscaminas {
    private Timer timer;
    private TareaTimer tareaTimer;
    private TimerListener listener;
    /**
     * metodo que establece la asignacion de un timer listener  o interfaz con metodos para
     * un evento de tiempo
     * @param listener parametro del tipo timerListener que asigna su valor a la variable principal del timer
     */
    
    public TimerBuscaminas(TimerListener listener){
        this.listener = listener;
    }
    /**
     * metodo publico que permite detener la ejecucion de un timer de timpo secuencia
     */
    public void stop(){
        if (this.timer != null){
            this.timer.cancel();
        }if (this.tareaTimer != null){
            this.tareaTimer.cancel();
        }
    }
    /**
     * metodo que permite el inicio de una tarea del tipo timer con asignacion en segundos
     */
    public void start(){
        this.timer = new Timer();
        this.tareaTimer = new TareaTimer(this.listener);
        timer.schedule(tareaTimer, 10, 1000);
    }
}
