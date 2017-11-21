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
    
    public Jugador validarSesión(String nombreJugador) throws RemoteException{
        Jugador jugador = null;
        try {
            ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
            jugador = stub.validarSesion(nombreJugador);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jugador;
    }
    public RegistroJugador registrarJugador(String nombre) throws RemoteException{
        RegistroJugador registro = RegistroJugador.JUGADOR_APROBADO;
        try {
            ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
            registro = stub.registrarJugador(nombre);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registro;
    }
    public boolean registrarPartida(Partida partida)throws RemoteException{
        boolean registrada = false;
        try {
            ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
            registrada = stub.registrarPartida(partida);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrada;
    }
    public DatosJugador getEstadisticas(int idJugador) throws RemoteException{
        DatosJugador datosJugador = null;
        try {
            ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
            datosJugador = stub.getEstadisticas(idJugador);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datosJugador;
    }
    public ArrayList<Jugador> getListaJugadores() throws RemoteException{
        ArrayList<Jugador> jugadores = new ArrayList();
        try {
            ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
            jugadores = stub.getListaJugadores();
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jugadores;
    }
    public boolean editarJugador(Jugador jugador) throws RemoteException{
        boolean editado = true;
        try {
            ServidorDatos stub = (ServidorDatos) this.registro.lookup(this.ID_SERVIDOR);
            editado = stub.editarJugador(jugador);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editado;
    }
}
