/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Persistencia.Jugador;

/**
 *
 * @author Compaq-presario-cq43
 */
public interface EstadisticaDAO {
    public Jugador getJugador(int idJugador);
    public int getPatidasGanadas(int idJugador);
    public String getTiempoPromedio(int idJugador);
}
