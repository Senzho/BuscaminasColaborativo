/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Persistencia.Jugador;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Compaq-presario-cq43
 */
public class EstadisticaDAOSqlTest {
    private Persistencia.Jugador jugador;
    private String tiempoPartida;
    private int partidaGanada;
    
    public EstadisticaDAOSqlTest() {
        jugador = new Jugador();
        jugador.setIdJugador(1);
        jugador.setNombreJugador("mario");
        jugador.setPartidasJugadas(0);
        jugador.setPartidasPerdidas(0);
        tiempoPartida = "610";
        partidaGanada=1;
    }
    @Test
    public void testGetJugadorEquals(){
        EstadisticaDAOSql instance = new EstadisticaDAOSql();
        Persistencia.Jugador jugador2 = instance.getJugador(jugador.getIdJugador());
        assertSame(jugador.getIdJugador(),jugador2.getIdJugador());
    }
    //esta falla
    @Test
    public void testGetPatidasGanadas(){
        EstadisticaDAOSql instance = new EstadisticaDAOSql();
        Integer numero = instance.getPatidasGanadas(jugador.getIdJugador());
        assertSame(partidaGanada,numero);
    }
    @Test
    public void testGetTiempoPromedio(){
        EstadisticaDAOSql instance = new EstadisticaDAOSql();
        String tiempo = instance.getTiempoPromedio(jugador.getIdJugador());
        assertEquals(tiempoPartida,tiempo);
    }
}
