/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Persistencia.Jugador;
import Persistencia.JugadorJpaController;
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
    public LogicaNegocio.Jugador getJugador(int idJugador) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        Jugador jugador = jugadorController.findJugador(idJugador);
        LogicaNegocio.Jugador jugadorPrincipal = new LogicaNegocio.Jugador(jugador.getIdJugador(),jugador.getNombreJugador(),
        jugador.getPartidasJugadas(),jugador.getPartidasPerdidas());
        return jugadorPrincipal;
    }

    @Override
    public int getPatidasGanadas(int idJugador) {
       EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
       EntityManager entityManager = managerFactory.createEntityManager();
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
        int numeroPartidas = 0;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasEjemplo1PU");
        EntityManager entityManager = managerFactory.createEntityManager();
        Query query = entityManager.createQuery("select P from Partida P where P.idJugador.idJugador = :idJugador");
        query.setParameter("idJugador",idJugador);
        List<Persistencia.Partida> partidas = query.getResultList();
        int contador = 0;
        for (int i = 0; i < partidas.size(); i++) {
            int aux = contador;
            contador = calcularSegundos(partidas.get(i).getTiempo())+ aux;
        }
        if(!partidas.isEmpty()){
           numeroPartidas = contador/partidas.size();
        }
        return calcularMinutos(numeroPartidas);
    }
    public String calcularMinutos(int numeroPartidas){
        String tiempoPromedio = "";
        int minutos = numeroPartidas/60;
        int segundos = numeroPartidas%60;
        tiempoPromedio = minutos+":"+segundos;
        return tiempoPromedio;
    }
}
