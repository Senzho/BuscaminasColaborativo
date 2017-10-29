package LogicaNegocio;

public class DatosJugador {
    private Jugador jugador;
    private int partidasGanadas;
    private String tiempoPromedio;

    public DatosJugador(Jugador jugador, int partidasGanadas, String tiempoPromedio) {
        this.jugador = jugador;
        this.partidasGanadas = partidasGanadas;
        this.tiempoPromedio = tiempoPromedio;
    }

    public Jugador getJugador() {
        return jugador;
    }
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    public String getTiempoPromedio() {
        return tiempoPromedio;
    }
}
