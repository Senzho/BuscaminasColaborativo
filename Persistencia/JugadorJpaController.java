/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Compaq-presario-cq43
 */
public class JugadorJpaController implements Serializable {

    public JugadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jugador jugador) throws PreexistingEntityException, Exception {
        if (jugador.getPartidaCollection() == null) {
            jugador.setPartidaCollection(new ArrayList<Partida>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Partida> attachedPartidaCollection = new ArrayList<Partida>();
            for (Partida partidaCollectionPartidaToAttach : jugador.getPartidaCollection()) {
                partidaCollectionPartidaToAttach = em.getReference(partidaCollectionPartidaToAttach.getClass(), partidaCollectionPartidaToAttach.getIdPartida());
                attachedPartidaCollection.add(partidaCollectionPartidaToAttach);
            }
            jugador.setPartidaCollection(attachedPartidaCollection);
            em.persist(jugador);
            for (Partida partidaCollectionPartida : jugador.getPartidaCollection()) {
                Jugador oldIdJugadorOfPartidaCollectionPartida = partidaCollectionPartida.getIdJugador();
                partidaCollectionPartida.setIdJugador(jugador);
                partidaCollectionPartida = em.merge(partidaCollectionPartida);
                if (oldIdJugadorOfPartidaCollectionPartida != null) {
                    oldIdJugadorOfPartidaCollectionPartida.getPartidaCollection().remove(partidaCollectionPartida);
                    oldIdJugadorOfPartidaCollectionPartida = em.merge(oldIdJugadorOfPartidaCollectionPartida);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJugador(jugador.getIdJugador()) != null) {
                throw new PreexistingEntityException("Jugador " + jugador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jugador jugador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador persistentJugador = em.find(Jugador.class, jugador.getIdJugador());
            Collection<Partida> partidaCollectionOld = persistentJugador.getPartidaCollection();
            Collection<Partida> partidaCollectionNew = jugador.getPartidaCollection();
            Collection<Partida> attachedPartidaCollectionNew = new ArrayList<Partida>();
            for (Partida partidaCollectionNewPartidaToAttach : partidaCollectionNew) {
                partidaCollectionNewPartidaToAttach = em.getReference(partidaCollectionNewPartidaToAttach.getClass(), partidaCollectionNewPartidaToAttach.getIdPartida());
                attachedPartidaCollectionNew.add(partidaCollectionNewPartidaToAttach);
            }
            partidaCollectionNew = attachedPartidaCollectionNew;
            jugador.setPartidaCollection(partidaCollectionNew);
            jugador = em.merge(jugador);
            for (Partida partidaCollectionOldPartida : partidaCollectionOld) {
                if (!partidaCollectionNew.contains(partidaCollectionOldPartida)) {
                    partidaCollectionOldPartida.setIdJugador(null);
                    partidaCollectionOldPartida = em.merge(partidaCollectionOldPartida);
                }
            }
            for (Partida partidaCollectionNewPartida : partidaCollectionNew) {
                if (!partidaCollectionOld.contains(partidaCollectionNewPartida)) {
                    Jugador oldIdJugadorOfPartidaCollectionNewPartida = partidaCollectionNewPartida.getIdJugador();
                    partidaCollectionNewPartida.setIdJugador(jugador);
                    partidaCollectionNewPartida = em.merge(partidaCollectionNewPartida);
                    if (oldIdJugadorOfPartidaCollectionNewPartida != null && !oldIdJugadorOfPartidaCollectionNewPartida.equals(jugador)) {
                        oldIdJugadorOfPartidaCollectionNewPartida.getPartidaCollection().remove(partidaCollectionNewPartida);
                        oldIdJugadorOfPartidaCollectionNewPartida = em.merge(oldIdJugadorOfPartidaCollectionNewPartida);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = jugador.getIdJugador();
                if (findJugador(id) == null) {
                    throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador jugador;
            try {
                jugador = em.getReference(Jugador.class, id);
                jugador.getIdJugador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.", enfe);
            }
            Collection<Partida> partidaCollection = jugador.getPartidaCollection();
            for (Partida partidaCollectionPartida : partidaCollection) {
                partidaCollectionPartida.setIdJugador(null);
                partidaCollectionPartida = em.merge(partidaCollectionPartida);
            }
            em.remove(jugador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jugador> findJugadorEntities() {
        return findJugadorEntities(true, -1, -1);
    }

    public List<Jugador> findJugadorEntities(int maxResults, int firstResult) {
        return findJugadorEntities(false, maxResults, firstResult);
    }

    private List<Jugador> findJugadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jugador.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Jugador findJugador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugador.class, id);
        } finally {
            em.close();
        }
    }

    public int getJugadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jugador> rt = cq.from(Jugador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
