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
public class JugadorDAOSqlTest {
    private String nombre;
    private String nombre2;
    private String nombre3;
    public JugadorDAOSqlTest(){
        nombre = "victor";
        nombre2 = "mario";
        nombre3 = "desconocido";
    }
    
    @Test
    public void testRegistrarJugador() {
        JugadorDAOSql jugadorDao = new JugadorDAOSql();
        boolean aceptado = jugadorDao.registrarJugador(nombre);
        assertTrue(aceptado);
    }
    @Test
    public void testValidarSesion() {
        JugadorDAOSql jugadorDao = new JugadorDAOSql();
        Jugador jugador = jugadorDao.validarSesion(nombre2);
        assertNotNull(jugador);
    }
    @Test
    public void testValidarSesionNula() {
        JugadorDAOSql jugadorDao = new JugadorDAOSql();
        Jugador jugador = jugadorDao.validarSesion(nombre3);
        assertNull(jugador);
    }
}
