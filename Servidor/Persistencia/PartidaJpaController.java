/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Victor Javier
 */
public class PartidaJpaController implements Serializable {

    public PartidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Partida partida) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador idJugador = partida.getIdJugador();
            if (idJugador != null) {
                idJugador = em.getReference(idJugador.getClass(), idJugador.getIdJugador());
                partida.setIdJugador(idJugador);
            }
            em.persist(partida);
            if (idJugador != null) {
                idJugador.getPartidaCollection().add(partida);
                idJugador = em.merge(idJugador);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPartida(partida.getIdPartida()) != null) {
                throw new PreexistingEntityException("Partida " + partida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Partida partida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partida persistentPartida = em.find(Partida.class, partida.getIdPartida());
            Jugador idJugadorOld = persistentPartida.getIdJugador();
            Jugador idJugadorNew = partida.getIdJugador();
            if (idJugadorNew != null) {
                idJugadorNew = em.getReference(idJugadorNew.getClass(), idJugadorNew.getIdJugador());
                partida.setIdJugador(idJugadorNew);
            }
            partida = em.merge(partida);
            if (idJugadorOld != null && !idJugadorOld.equals(idJugadorNew)) {
                idJugadorOld.getPartidaCollection().remove(partida);
                idJugadorOld = em.merge(idJugadorOld);
            }
            if (idJugadorNew != null && !idJugadorNew.equals(idJugadorOld)) {
                idJugadorNew.getPartidaCollection().add(partida);
                idJugadorNew = em.merge(idJugadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = partida.getIdPartida();
                if (findPartida(id) == null) {
                    throw new NonexistentEntityException("The partida with id " + id + " no longer exists.");
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
            Partida partida;
            try {
                partida = em.getReference(Partida.class, id);
                partida.getIdPartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partida with id " + id + " no longer exists.", enfe);
            }
            Jugador idJugador = partida.getIdJugador();
            if (idJugador != null) {
                idJugador.getPartidaCollection().remove(partida);
                idJugador = em.merge(idJugador);
            }
            em.remove(partida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Partida> findPartidaEntities() {
        return findPartidaEntities(true, -1, -1);
    }

    public List<Partida> findPartidaEntities(int maxResults, int firstResult) {
        return findPartidaEntities(false, maxResults, firstResult);
    }

    private List<Partida> findPartidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Partida.class));
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

    public Partida findPartida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Partida.class, id);
        } finally {
            em.close();
        }
    }

    public int getPartidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Partida> rt = cq.from(Partida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
