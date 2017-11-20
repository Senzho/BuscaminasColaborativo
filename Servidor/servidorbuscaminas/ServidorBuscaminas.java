package servidorbuscaminas;

import LogicaNegocio.DatosJugador;
import LogicaNegocio.EstadisticaDAOSql;
import LogicaNegocio.Jugador;
import LogicaNegocio.JugadorDAOSql;
import LogicaNegocio.MejorJugadorDAO;
import LogicaNegocio.Partida;
import LogicaNegocio.PartidaDAO;
import LogicaNegocio.RegistroJugador;
import LogicaNegocio.ServidorDatos;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorBuscaminas implements ServidorDatos {
    
    public static void main(String[] args) {
        ServidorBuscaminas servidorBuscaminas = new ServidorBuscaminas();
        try {
            ServidorDatos stub = (ServidorDatos) UnicastRemoteObject.exportObject(servidorBuscaminas,0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("ServidorBuscaminasOnline", stub);
            System.out.println("Server ready");
        } catch (RemoteException |AlreadyBoundException ex) {
            Logger.getLogger(ServidorBuscaminas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Jugador validarSesion(String nombreJugador) throws RemoteException {
        JugadorDAOSql jugador = new JugadorDAOSql();
        return jugador.validarSesion(nombreJugador);
    }

    @Override
    public RegistroJugador registrarJugador(String nombreJugador) throws RemoteException {
        JugadorDAOSql jugadorDao = new JugadorDAOSql();
        return jugadorDao.registrarJugador(nombreJugador);
    }

    @Override
    public boolean registrarPartida(Partida partida) throws RemoteException {
        PartidaDAO partidaDao = new PartidaDAO();
        return partidaDao.registrarPartida(partida);
    }

    @Override
    public DatosJugador getEstadisticas(int idJugador) throws RemoteException {
        EstadisticaDAOSql estadisticaDao = new EstadisticaDAOSql();
        
        DatosJugador datos = new DatosJugador(estadisticaDao.getJugador(idJugador),estadisticaDao.getPatidasGanadas(idJugador),
        estadisticaDao.getTiempoPromedio(idJugador));
        return datos;
    }

    @Override
    public ArrayList<Jugador> getListaJugadores() throws RemoteException {
        MejorJugadorDAO jugador = new MejorJugadorDAO();
        return jugador.getListaJugadores();
    }
}
