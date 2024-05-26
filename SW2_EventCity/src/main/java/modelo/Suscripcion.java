/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "suscripciones")
public class Suscripcion {
    
    @EmbeddedId
    private SuscripcionId id;

    @ManyToOne
    @MapsId("idSuscriptor")
    @JoinColumn(name = "idSuscriptor")
    private Suscriptor suscriptor;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "idEvento")
    private Evento evento;
    
    @Column(name = "fechaSus")
    private Date fechaSus;
    
    @Column(name = "estado")
    private boolean estado;

    public SuscripcionId getId() {
        return id;
    }

    public void setId(SuscripcionId id) {
        this.id = id;
    }

    public Suscriptor getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(Suscriptor suscriptor) {
        this.suscriptor = suscriptor;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Date getFechaSus() {
        return fechaSus;
    }

    public void setFechaSus(Date fechaSus) {
        this.fechaSus = fechaSus;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
   
}

@Embeddable
class SuscripcionId implements Serializable {
    private int idSuscriptor;
    private int idEvento;

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idSuscriptor;
        hash = 97 * hash + this.idEvento;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuscripcionId other = (SuscripcionId) obj;
        if (this.idSuscriptor != other.idSuscriptor) {
            return false;
        }
        if (this.idEvento != other.idEvento) {
            return false;
        }
        return true;
    }

    
}
