/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Persistencia.Jugador;
import Persistencia.JugadorJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Compaq-presario-cq43
 */
public class JugadorDAOSql implements JugadorDAO {
    /**
     * metodo que funciona para buscar todos los jugadores existentes y asignar un identificador mayor a 
     * los ya existentes
     * @return id + 1, parametro de tipo entero saliente de la consulta en los jugadores existentes
     */
    public int getAsignarId(){
        int id = 0;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        String consulta = "SELECT COUNT(j) FROM Jugador j";
        id = Integer.parseInt(String.valueOf(managerFactory.createEntityManager().createQuery(consulta, Integer.class).getSingleResult()));
        return id + 1;
    }
    /**
     * metodo de tipo booleano que permite el registro de jugadores, recibe un argumento de texto
     * @param nombre valor de tipo String perteneciente al nombre del jugador.
     * @return registrado valor de tipo booleano que verifica el correcto almacenamiento del jugador
     */
    private boolean RegistroJugador(String nombre){
        boolean registrado = false;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        EntityManager entityManager = managerFactory.createEntityManager();
        Persistencia.Jugador jugadorP = new Persistencia.Jugador();
        Query query = entityManager.createQuery("select j from Jugador j");
        List<Persistencia.Jugador> listaJugadores = query.getResultList();
        for (int i = 0; i < listaJugadores.size(); i++) {
            if(listaJugadores.get(i).getNombreJugador().equals(nombre)){
                registrado = true;
                break;
            }
        }
        return registrado;
    }
    /**
     * metodo del tipo RegistroJugador que permite registrar a un nuevo jugador  validando la entrada de nombre
     * @param nombre parametro del tipo String que permite un registro usando como clave de jugador su nombre
     * @return registro parametro del tipo enumerador que muestra la validacion de la entrada y el registro
     * correcto
     */
    @Override
    public RegistroJugador registrarJugador(String nombre) {
        RegistroJugador registro = RegistroJugador.ERROR_REGISTRO;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        if(this.RegistroJugador(nombre) == false){           
            Persistencia.Jugador jugadorP = new Persistencia.Jugador();
            jugadorP.setIdJugador(getAsignarId());
            jugadorP.setNombreJugador(nombre);
            jugadorP.setPartidasJugadas(0);
            jugadorP.setPartidasPerdidas(0);
            JugadorJpaController jugadorController = new JugadorJpaController(managerFactory);
            try {
                jugadorController.create(jugadorP);
                registro = RegistroJugador.JUGADOR_APROBADO;
            } catch (Exception ex) {
                Logger.getLogger(JugadorDAOSql.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }else if(this.RegistroJugador(nombre) == true){
            registro = RegistroJugador.JUGADOR_EXISTENTE;
        }
        return registro;
    }
    /**
     * metodo del tipo LogicaNegocio.Jugador que permite validar los inicios de sesion validar su ingreso
     * @param nombreJugador parametro del tipo String perteneciente al nombre del jugador que quiere conectarse
     * @return jugadorPrincipal permite la validacion del jugador conectado retonrnando un objeto del tipo 
     * LogicaNegocio.Jugador
     */
    @Override
    public LogicaNegocio.Jugador validarSesion(String nombreJugador) {
        Persistencia.Jugador jugadorPersistencia = null;
        LogicaNegocio.Jugador jugadorPrincipal = null;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        EntityManager entityManager = managerFactory.createEntityManager();
        Query query = entityManager.createQuery("select j from Jugador j where j.nombreJugador = :nombreJugador");
        query.setParameter("nombreJugador",nombreJugador);
        List<Jugador> jugadores = query.getResultList();
        for (int i = 0; i < jugadores.size(); i++) {
            if(jugadores.get(i).getNombreJugador().equals(nombreJugador)){
                jugadorPersistencia = jugadores.get(i);
                jugadorPrincipal = new LogicaNegocio.Jugador(jugadorPersistencia.getIdJugador(),jugadorPersistencia.getNombreJugador(),
                jugadorPersistencia.getPartidasJugadas(),jugadorPersistencia.getPartidasPerdidas());
                break;
            }  
        }
        return jugadorPrincipal;
    }
    /**
     * metodo de tipo booleano que permite modificar las partidas de un jugador dependiente de su identificador 
     * @param jugador objeto del tipo LogicaNegocio.Jugador que permite la edicion de sus atributos
     * @return editado muestra la validez del registro y modificacion del objeto
     */
    public boolean editarJugador(LogicaNegocio.Jugador jugador){
        boolean editado = true;
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ServidorBuscaminasPU");
        JugadorJpaController jugadorJpaController = new JugadorJpaController(managerFactory);
        Persistencia.Jugador jugadorJpa = new Persistencia.Jugador();
        jugadorJpa.setIdJugador(jugador.getIdJugador());
        jugadorJpa.setNombreJugador(jugador.getNombreJugador());
        jugadorJpa.setPartidasJugadas(jugador.getPartidasJugadas());
        jugadorJpa.setPartidasPerdidas(jugador.getPartidasPerdidas());
        String query = "select p from Partida p where p.idJugador.idJugador = :idJugador";
        List<Persistencia.Partida> partidas = managerFactory.createEntityManager().createQuery(query)
                .setParameter("idJugador", jugador.getIdJugador())
                .getResultList();
        jugadorJpa.setPartidaCollection(partidas);
        try {
            jugadorJpaController.edit(jugadorJpa);
        } catch (Exception ex) {
            editado = false;
            Logger.getLogger(JugadorDAOSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editado;
    }
}
