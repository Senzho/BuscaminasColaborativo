package LogicaNegocio;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    private Registry registro;
    
    private final String ID_SERVIDOR = "ServidorBuscaminasOnline";
    
    public Cliente(String direccionServidor) throws RemoteException{
        this.registro = LocateRegistry.getRegistry(direccionServidor);
    }
    
    public Jugador validarSesión(String nombreJugador) throws RemoteException, NotBoundException{
        Jugador jugador = null;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
        jugador = stub.validarSesion(nombreJugador);
        return jugador;
    }
    public RegistroJugador registrarJugador(String nombre) throws RemoteException, NotBoundException{
        RegistroJugador registro = RegistroJugador.JUGADOR_APROBADO;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
        registro = stub.registrarJugador(nombre);
        return registro;
    }
    public boolean registrarPartida(Partida partida)throws RemoteException, NotBoundException{
        boolean registrada = false;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
        registrada = stub.registrarPartida(partida);
        return registrada;
    }
    public DatosJugador getEstadisticas(int idJugador) throws RemoteException, NotBoundException{
        DatosJugador datosJugador = null;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
        datosJugador = stub.getEstadisticas(idJugador);
        return datosJugador;
    }
    public ArrayList<Jugador> getListaJugadores() throws RemoteException, NotBoundException{
        ArrayList<Jugador> jugadores = new ArrayList();
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
        jugadores = stub.getListaJugadores();
        return jugadores;
    }
    public boolean editarJugador(Jugador jugador) throws RemoteException, NotBoundException{
        boolean editado = true;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
        editado = stub.editarJugador(jugador);
        return editado;
    }
}
