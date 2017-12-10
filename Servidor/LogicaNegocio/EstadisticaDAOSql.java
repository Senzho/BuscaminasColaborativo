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
    private static final String UNIDAD_PERSISTENCIA = "ServidorBuscaminasPU";
    /**
     * Metodo del tipo LogicaNegocio.Jugador, permite obtener un Jugador a partir de un numero entero
     * identificador
     * @param idJugador es el identificador unico del jugador buscado
     * @return JugadorPrincipal es el tipo de datos Jugador encontrado en la lista
     */
    @Override
    public LogicaNegocio.Jugador getJugador(int idJugador) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(EstadisticaDAOSql.UNIDAD_PERSISTENCIA);
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        Jugador jugador = jugadorController.findJugador(idJugador);
        return new LogicaNegocio.Jugador(jugador.getIdJugador(),jugador.getNombreJugador(),
                jugador.getPartidasJugadas(),jugador.getPartidasPerdidas());
    }
    /**
     * Metodo de tipo entero que permite buscar las partidas ganadas por un jugador mediante un identificado 
     * unico
     * @param idJugador identificador unico del jugador que es buscado 
     * @return numeroPartidas, parametro de tipo entero que muestra las partidas jugadas ganadas
     */

    @Override
    public int getPatidasGanadas(int idJugador) {
       EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(EstadisticaDAOSql.UNIDAD_PERSISTENCIA);
       EntityManager entityManager = managerFactory.createEntityManager();
       Query query = entityManager.createQuery("select P from Partida P where P.idJugador.idJugador = :idJugador");
       query.setParameter("idJugador",idJugador);
       List<Persistencia.Partida> partidas = query.getResultList();
       return partidas.size();
    }
    /**
     * Metodo privado de tipo entero, permite el calculo de segundos de una partida  a partir de un
     * parametro String dividiendo y concatenando sus valores
     * @param numero  es la cantidas de segundos deducida del parametro de entrada que cuenta con
     * minutos y segundo en un string y con dos puntos suspensivos para su diferenciacion
     * @return minSeconds + seconds es la suma de los parametros de minutos convertidos en segundos
     * y segundos sobrantes que no abarcan un minuto
     */
    private int calcularSegundos(String numero){
        String[] parts = numero.split(":");
        String part1 = parts[0];
        String part2 = parts[1];
        int minSeconds = Integer.parseInt(part1);
        int seconds =  Integer.parseInt(part2);
        minSeconds = minSeconds*60;
        return minSeconds+seconds;
    }
    /**
     * Metodo que obtiene el tiempo promedio de partidas jugadas y ganadas mediente un identificado entero
     * @param idJugador parametro unico de un jugador, permite su indetificacion 
     * @return calcularMinutos(String); valor de tipo String que muestra el tiempo promedio de juego
     */
    @Override
    public String getTiempoPromedio(int idJugador) {
        int numeroPartidas = 0;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(EstadisticaDAOSql.UNIDAD_PERSISTENCIA);
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
    /**
     * metodode tipo String que permite el calculo de minutos dependiente del numero de segundos calculados
     * @param numeroPartidas parametro de tipo entero que muestra  el tiempo jugado en segundos
     * @return tiempoPromedio parametro de tipo String que muestra los minutos y segundo jugador por un jugador
     */
    public String calcularMinutos(int numeroPartidas){
        String tiempoPromedio = "";
        int minutos = numeroPartidas/60;
        int segundos = numeroPartidas%60;
        String minutoFinal = minutos + "";
        String segundoFinal = segundos + "";
        if(minutos < 10){
            minutoFinal = "0" + minutos;
        }
        if(segundos < 10){
            segundoFinal = "0" + segundos;
        }
        tiempoPromedio = minutoFinal+":"+segundoFinal;
        return tiempoPromedio;
    }
}
