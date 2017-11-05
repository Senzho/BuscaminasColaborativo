/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Compaq-presario-cq43
 */
public interface ServidorDatos extends Remote{
    public Jugador validarSesion(String nombreJugador) throws RemoteException;
    public boolean registrarJugador(String nombreJugador) throws RemoteException;
    public boolean registrarPartida(Partida partida) throws RemoteException;
    public DatosJugador getEstadisticas(int idJugador) throws RemoteException;
    public ArrayList<Jugador> getListaJugadores() throws RemoteException;
}
