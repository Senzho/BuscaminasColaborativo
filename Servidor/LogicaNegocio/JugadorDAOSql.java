/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Persistencia.Jugador;
import Persistencia.JugadorJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Compaq-presario-cq43
 */
public class JugadorDAOSql implements JugadorDAO {
    public int getAsignarId(){
        int id = 0;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        String consulta = "SELECT COUNT(j) FROM Jugador j";
        id = Integer.parseInt(String.valueOf(managerFactory.createEntityManager().createQuery(consulta, Integer.class).getSingleResult()));
        return id + 1;
    }
    
    @Override
    public boolean registrarJugador(String nombre) {
        boolean registrado = false;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        Persistencia.Jugador jugadorP = new Persistencia.Jugador();
        jugadorP.setIdJugador(getAsignarId());
        jugadorP.setNombreJugador(nombre);
        jugadorP.setPartidasJugadas(0);
        jugadorP.setPartidasPerdidas(0);
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        try {
            jugadorController.create(jugadorP);
            registrado = true;
        } catch (Exception ex) {
            Logger.getLogger(JugadorDAOSql.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return registrado;
    }

    @Override
    public LogicaNegocio.Jugador validarSesion(String nombreJugador) {
        Persistencia.Jugador jugadorPersistencia = null;
        LogicaNegocio.Jugador jugadorPrincipal = null;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        EntityManager entityManager = managerFactory.createEntityManager();
        Query query = entityManager.createQuery("select j from Jugador j where j.nombreJugador = :nombreJugador");
        query.setParameter("nombreJugador",nombreJugador);
        List<Jugador> jugadores = query.getResultList();
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).getNombreJugador().equals(nombreJugador)){
                jugadorPersistencia = jugadores.get(i);
                jugadorPrincipal = new LogicaNegocio.Jugador(jugadorPersistencia.getIdJugador(),jugadorPersistencia.getNombreJugador(),
                jugadorPersistencia.getPartidasJugadas(),jugadorPersistencia.getPartidasPerdidas());
                break;
            }  
        }
        return jugadorPrincipal;
     }
}
