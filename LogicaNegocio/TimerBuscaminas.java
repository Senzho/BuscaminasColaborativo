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
    
    public TimerBuscaminas(TimerListener listener){
        this.listener = listener;
    }
    public void stop(){
        if (this.timer != null){
            this.timer.cancel();
        }if (this.tareaTimer != null){
            this.tareaTimer.cancel();
        }
    }
    public void start(){
        this.timer = new Timer();
        this.tareaTimer = new TareaTimer(this.listener);
        timer.schedule(tareaTimer, 10, 1000);
    }
}
