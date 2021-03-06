package LogicaNegocio;

import java.io.Serializable;

public class Partida implements Serializable{
    private String dificultad;
    private String tiempo;
    private int idJugador;

    public Partida(String dificultad, String tiempo, int idJugador) {
        this.dificultad = dificultad;
        this.tiempo = tiempo;
        this.idJugador = idJugador;
    }

    public String getDificultad() {
        return dificultad;
    }
    public String getTiempo() {
        return tiempo;
    }
    public int getIdJugador() {
        return idJugador;
    }
}
