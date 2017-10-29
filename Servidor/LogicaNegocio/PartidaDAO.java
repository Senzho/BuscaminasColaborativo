package LogicaNegocio;

import Persistencia.JugadorJpaController;
import Persistencia.PartidaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PartidaDAO {
    public boolean registrarPartida(Partida partida) {
        boolean registrada = false;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        Persistencia.Partida partidaP = new Persistencia.Partida();
        partidaP.setIdPartida(this.getNuevoId());
        JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
        partidaP.setIdJugador(jugadorController.findJugador(partida.getIdJugador()));
        partidaP.setDificultad(partida.getDificultad());
        partidaP.setTiempo(partida.getTiempo());
        PartidaJpaController partidaController = new PartidaJpaController(managerFactory);
        try {
            partidaController.create(partidaP);
            registrada = true;
        } catch (Exception ex) {
            Logger.getLogger(PartidaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrada;
    }
    public int getNuevoId(){
        long id = 0;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        String consulta = "SELECT COUNT(p) FROM Partida p";
        id = managerFactory.createEntityManager().createQuery(consulta, Long.class)
                .getSingleResult();
        return Integer.parseInt(String.valueOf(id)) + 1;
    }
}
