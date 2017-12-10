package LogicaNegocio;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Cliente {
    private Registry registro;
    
    private static final String ID_SERVIDOR = "ServidorBuscaminasOnline";
    /**
     * 
     * @param direccionServidor, la dirección IP del servidor.
     * @throws RemoteException 
     */
    public Cliente(String direccionServidor) throws RemoteException{
        this.registro = LocateRegistry.getRegistry(direccionServidor);
    }
    /**
     * 
     * @param nombreJugador, el nombre del jugador que intenta iniciar sesión.
     * @return jugador, regresa el jugador encontrado en caso de ser un nombre válido, 
     *         en el caso contrario el jugador será nulo.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public Jugador validarSesión(String nombreJugador) throws RemoteException, NotBoundException{
        Jugador jugador = null;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(Cliente.ID_SERVIDOR);
        jugador = stub.validarSesion(nombreJugador);
        return jugador;
    }
    /**
     *
     * @param nombre , el nombre del jugador que pretend registrarse.
     * @return registroJugador, regresa un Enum RegistroJugador:
           JUGADOR_APROBADO: el jugador fue aprobado.
           JUGADOR_EXISTENTE: el jugador ya está registrado.
           ERROR_REGISTRO: ocurrió un error al registrar el jugador.
     * @throws RemoteException
     * @throws NotBoundException
     */
    public RegistroJugador registrarJugador(String nombre) throws RemoteException, NotBoundException{
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(Cliente.ID_SERVIDOR);
        return  stub.registrarJugador(nombre);
    }
    /**
     * 
     * @param partida, entidadad lógica Partida correspondiente a la partida que se
     *        desea registrar.
     * @return registrada, regresa un valor booleano de acuerdo a si la partida fue
     *         registrada (true) o no (false).
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public boolean registrarPartida(Partida partida)throws RemoteException, NotBoundException{
        boolean registrada = false;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(Cliente.ID_SERVIDOR);
        registrada = stub.registrarPartida(partida);
        return registrada;
    }
    /**
     * 
     * @param idJugador, el identificador del jugador para el cual se desean obtener
     *        las estadísticas.
     * @return datosJugador, la entidad DatosJugador con los datos de sus estadísticas.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public DatosJugador getEstadisticas(int idJugador) throws RemoteException, NotBoundException{
        DatosJugador datosJugador = null;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(Cliente.ID_SERVIDOR);
        datosJugador = stub.getEstadisticas(idJugador);
        return datosJugador;
    }
    /**
     * 
     * @return jugadores, regresa un ArrayList con los jugadores registrados.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public ArrayList<Jugador> getListaJugadores() throws RemoteException, NotBoundException{
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(Cliente.ID_SERVIDOR);
        return stub.getListaJugadores();
    }
    /**
     * 
     * @param jugador, la entidad lógica Jugador correspondiente al jugador que se desea
     *        editar.
     * @return editado, regresa un valor booleano indicando si el jugador fue editado
     *         (true) o no (false).
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public boolean editarJugador(Jugador jugador) throws RemoteException, NotBoundException{
        boolean editado = true;
        ServidorDatos stub = (ServidorDatos) this.registro.lookup(Cliente.ID_SERVIDOR);
        editado = stub.editarJugador(jugador);
        return editado;
    }
}
