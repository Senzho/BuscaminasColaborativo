package LogicaNegocio;

import Persistencia.JugadorJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MejorJugadorDAO {
    /**
     * 
     * @return lista, regresa un ArrayList con los mejores jugadores del juego, de 
     *         acuerdo a las políticas.
     */
    public ArrayList<Jugador> getListaJugadores(){
        ArrayList<Jugador> lista = new ArrayList();
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        List<Persistencia.Jugador> listaJugadores = jugadorController.findJugadorEntities();
        for (Persistencia.Jugador jugador : listaJugadores){
            int partidasGanadas = this.getNumeroPartidas(jugador.getIdJugador(), managerFactory);
            double porcentaje = jugador.getPartidasJugadas() * 0.4;
            if (partidasGanadas >= porcentaje && jugador.getPartidasJugadas() > 9){
                lista.add(new Jugador(jugador.getIdJugador(), jugador.getNombreJugador(), jugador.getPartidasJugadas(), jugador.getPartidasPerdidas()));
            }
        }
        return lista;
    }
    /**
     * 
     * @param idJugador, el identificador del jugador del cual se requiere saber el
     *        número de partida.
     * @param managerFactory, EntityManagerFactory para la unidad de persistencia.
     * @return 
     */
    public int getNumeroPartidas(int idJugador, EntityManagerFactory managerFactory){
        int cuenta;
        String consulta = "selct count(p) from Partida p where p.idJugador.idJugador = :id";
        cuenta = managerFactory.createEntityManager().createQuery(consulta, Integer.class)
                .setParameter("id", idJugador)
                .getSingleResult();
        return cuenta;
    }
}
