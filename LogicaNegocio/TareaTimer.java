package LogicaNegocio;

import java.util.TimerTask;

public class TareaTimer extends TimerTask{
    private int minutos = 0;
    private int segundos = 0;
    private String tiempo = "";
    private TimerListener listener;
    
    public TareaTimer(TimerListener listener){
        this.listener = listener;
    }
    
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
