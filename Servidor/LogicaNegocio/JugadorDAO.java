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
public interface JugadorDAO {
    public RegistroJugador registrarJugador(String nombre);
    public LogicaNegocio.Jugador validarSesion(String nombreJugador);
}
