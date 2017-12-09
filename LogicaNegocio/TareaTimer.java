package LogicaNegocio;

import java.util.TimerTask;
/**
 * 
 * @author Compaq-presario-cq43
 */
public class TareaTimer extends TimerTask{
    private int minutos = 0;
    private int segundos = 0;
    private String tiempo = "";
    private TimerListener listener;
    /**
     * metodo que establece la asignacion de un timer listener  o interfaz con metodos para
     * un evento de tiempo
     * @param listener parametro del tipo timerListener que asigna su valor a la variable principal del timer
     */
    public TareaTimer(TimerListener listener){
        this.listener = listener;
    }
    /**
     * metodo de un hilo que permite la ejecucion de las tareas asignadas al timerListener
     */
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
    /**
     * metodo de tipo String que permite el calculo progresivo de minutos y segundos transcurridos en una 
     * partida
     * @param minutos valor del tipo int actual que identifica los minutos transcurridos 
     * @param segundos vallor de tipo int actual que identifica los segundos transcurridos
     * @return fecha, valor String que muestra un tiempo progresivo actualizado en el timer 
     */
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
}
