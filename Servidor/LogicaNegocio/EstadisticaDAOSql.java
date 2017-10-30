/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Persistencia.Jugador;
import Persistencia.JugadorJpaController;
import Persistencia.PartidaJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Compaq-presario-cq43
 */
public class EstadisticaDAOSql implements EstadisticaDAO {

    @Override
    public Jugador getJugador(int idJugador) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        Persistencia.Jugador jugadorP = new Persistencia.Jugador();
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        Jugador jugador = jugadorController.findJugador(idJugador);
        return jugador;
    }

    @Override
    public int getPatidasGanadas(int idJugador) {
       EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
       EntityManager entityManager = managerFactory.createEntityManager();
       PartidaJpaController partidaController = new PartidaJpaController(managerFactory);
       Query query = entityManager.createQuery("select P from Partida P where P.idJugador.idJugador = :idJugador");
       query.setParameter("idJugador",idJugador);
       List<Persistencia.Partida> partidas = query.getResultList();
       int numeroPartidas = partidas.size();
       return numeroPartidas;
    }
    private int calcularSegundos(String numero){
        String[] parts = numero.split(":");
        String part1 = parts[0];
        String part2 = parts[1];
        int minSeconds = Integer.parseInt(part1);
        int seconds =  Integer.parseInt(part2);
        minSeconds = minSeconds*60;
        return minSeconds+seconds;
    }
 
    @Override
    public String getTiempoPromedio(int idJugador) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        EntityManager entityManager = managerFactory.createEntityManager();
        PartidaJpaController partidaController = new PartidaJpaController(managerFactory);
        Query query = entityManager.createQuery("select P from Partida P where P.idJugador.idJugador = :idJugador");
        query.setParameter("idJugador",idJugador);
        List<Persistencia.Partida> partidas = query.getResultList();
        int contador = 0;
        for (int i = 0; i < partidas.size(); i++) {
            int aux = contador;
            contador = calcularSegundos(partidas.get(i).getTiempo())+ aux;
        }
        int numeroPartidas = contador/partidas.size();
        return Integer.toString(numeroPartidas);
    }
}
