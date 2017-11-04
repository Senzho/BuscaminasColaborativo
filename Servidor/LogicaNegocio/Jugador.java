package LogicaNegocio;

import java.io.Serializable;

public class Jugador implements Serializable {
    private int idJugador;
    private String nombreJugador;
    private int partidasJugadas;
    private int partidasPerdidas;

    public Jugador(int idJugador, String nombreJugador, int partidasJugadas, int partidasPerdidas) {
        this.idJugador = idJugador;
        this.nombreJugador = nombreJugador;
        this.partidasJugadas = partidasJugadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    public int getIdJugador() {
        return idJugador;
    }
    public String getNombreJugador() {
        return nombreJugador;
    }
    public int getPartidasJugadas() {
        return partidasJugadas;
    }
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }
}
