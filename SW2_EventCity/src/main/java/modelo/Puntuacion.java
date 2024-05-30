/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "puntuaciones")
public class Puntuacion implements Serializable{
    
    @EmbeddedId
    private PuntuacionId id;

    @ManyToOne
    @MapsId("idSuscriptor")
    @JoinColumn(name = "idSuscriptor")
    private Suscriptor suscriptor;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "idEvento")
    private Evento evento;
    
    @Column(name = "puntuacion")
    private int puntuacion;

    public PuntuacionId getId() {
        return id;
    }

    public void setId(PuntuacionId id) {
        this.id = id;
    }

    public Suscriptor getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(Suscriptor suscriptor) {
        this.suscriptor = suscriptor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
