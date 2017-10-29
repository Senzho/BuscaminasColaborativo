/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "partida")
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")
    , @NamedQuery(name = "Partida.findByDificultad", query = "SELECT p FROM Partida p WHERE p.dificultad = :dificultad")
    , @NamedQuery(name = "Partida.findByTiempo", query = "SELECT p FROM Partida p WHERE p.tiempo = :tiempo")
    , @NamedQuery(name = "Partida.findByIdPartida", query = "SELECT p FROM Partida p WHERE p.idPartida = :idPartida")})
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "dificultad")
    private String dificultad;
    @Column(name = "tiempo")
    private String tiempo;
    @Id
    @Basic(optional = false)
    @Column(name = "idPartida")
    private Integer idPartida;
    @JoinColumn(name = "idJugador", referencedColumnName = "idJugador")
    @ManyToOne
    private Jugador idJugador;

    public Partida() {
    }

    public Partida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Partida[ idPartida=" + idPartida + " ]";
    }
    
}
