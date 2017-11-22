/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Compaq-presario-cq43
 */
public class TimerBuscaminas {
    private Timer timer;
    private TimerTask timerTask;
    private TimerListener listener;
    
    public TimerBuscaminas(TimerListener listener){
        this.listener = listener;
        this.timer = new Timer();
        this.timerTask = new TimerTask(){
            int minutos = 0;
            int segundos = 0;
            String tiempo = "";
            @Override
            public void run() {
               if(segundos%60 == 0 && segundos!= 0){
                    minutos ++;
                    segundos = 0;
                    tiempo = validarDatos(minutos,segundos);
                    listener.eventoTimer(tiempo);        
                    segundos++;
                }else{
                   tiempo = validarDatos(minutos,segundos);
                    listener.eventoTimer(tiempo);
                    segundos ++;
                }
            }
        };
    }
    public String validarDatos(int minutos,int segundos){
        String fecha ="";
        String segundo = "";
        String minuto = "";
        if(segundos < 10){
            segundo = "0"+segundos;
        }
        if(minutos < 10){
            minuto = "0"+minutos;
        }
        if(minutos >= 10){
            minuto = Integer.toString(minutos);
        }
        if(segundos >= 10){
            segundo = Integer.toString(segundos);
        }
        return fecha = minuto+":"+segundo;
    }
    public void stop(){
        this.timer.cancel();
    }
    public void start(){
        timer.schedule(timerTask, 10, 1000);
    }
}
