package LogicaNegocio;

import Persistencia.JugadorJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MejorJugadorDAO {
    public ArrayList<Jugador> getListaJugadores(){
        ArrayList<Jugador> lista = new ArrayList();
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        List<Persistencia.Jugador> listaJugadores = jugadorController.findJugadorEntities();
        for (Persistencia.Jugador jugador : listaJugadores){
            List<Persistencia.Partida> partidas = this.getListaPorJugador(jugador.getIdJugador(), managerFactory);
            double porcentaje = jugador.getPartidasJugadas() * 0.4;
            if (partidas.size() >= porcentaje && partidas.size() > 9){
                lista.add(new Jugador(jugador.getIdJugador(), jugador.getNombreJugador(), jugador.getPartidasJugadas(), jugador.getPartidasPerdidas()));
            }
        }
        return lista;
    }
    public List<Persistencia.Partida> getListaPorJugador(int idJugador, EntityManagerFactory managerFactory){
        String consulta = "SELECT p FROM Partida p WHERE p.idJugador.idJugador = :id";
        List<Persistencia.Partida> partidas = managerFactory.createEntityManager().createQuery(consulta, Persistencia.Partida.class)
                .setParameter("id", idJugador)
                .getResultList();
        return partidas;
    }
}
