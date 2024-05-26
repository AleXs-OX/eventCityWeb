/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Beatriz
 */

@Entity
@Table(name = "publicadores")
public class Publicador implements Serializable{
    
    @Id
    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Column(name = "fechaAlta")
    private Date fechaAlta;
    
    @Column(name = "numEventos")
    private int numEventos;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "publicador")
    private List<Evento> eventos;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idUsuario;
        hash = 79 * hash + Objects.hashCode(this.fechaAlta);
        hash = 79 * hash + this.numEventos;
        hash = 79 * hash + Objects.hashCode(this.descripcion);
        hash = 79 * hash + Objects.hashCode(this.usuario);
        hash = 79 * hash + Objects.hashCode(this.eventos);
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
        final Publicador other = (Publicador) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.numEventos != other.numEventos) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlta, other.fechaAlta)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.eventos, other.eventos)) {
            return false;
        }
        return true;
    }

    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getNumEventos() {
        return numEventos;
    }

    public void setNumEventos(int numEventos) {
        this.numEventos = numEventos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    
}