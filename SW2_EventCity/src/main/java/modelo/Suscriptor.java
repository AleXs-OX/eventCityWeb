/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "suscriptores")
public class Suscriptor implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSubscriptor")
    private int idSubscriptor;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @Column(name = "numSuscripciones")
    private String numSuscripciones;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "ciudad")
    private String ciudad;
    
    @Column(name = "pais")
    private String pais;

    @OneToMany(mappedBy = "suscriptor")
    private List<Suscripcion> suscripciones;

    @OneToMany(mappedBy = "suscriptor")
    private List<Puntuacion> puntuaciones;

    @OneToMany(mappedBy = "suscriptor")
    private List<Resena> resenas; 

    public int getIdSubscriptor() {
        return idSubscriptor;
    }

    public void setIdSubscriptor(int idSubscriptor) {
        this.idSubscriptor = idSubscriptor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumSuscripciones() {
        return numSuscripciones;
    }

    public void setNumSuscripciones(String numSuscripciones) {
        this.numSuscripciones = numSuscripciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idSubscriptor;
        hash = 53 * hash + Objects.hashCode(this.usuario);
        hash = 53 * hash + Objects.hashCode(this.numSuscripciones);
        hash = 53 * hash + Objects.hashCode(this.direccion);
        hash = 53 * hash + Objects.hashCode(this.ciudad);
        hash = 53 * hash + Objects.hashCode(this.pais);
        hash = 53 * hash + Objects.hashCode(this.suscripciones);
        hash = 53 * hash + Objects.hashCode(this.puntuaciones);
        hash = 53 * hash + Objects.hashCode(this.resenas);
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
        final Suscriptor other = (Suscriptor) obj;
        if (this.idSubscriptor != other.idSubscriptor) {
            return false;
        }
        if (!Objects.equals(this.numSuscripciones, other.numSuscripciones)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.suscripciones, other.suscripciones)) {
            return false;
        }
        if (!Objects.equals(this.puntuaciones, other.puntuaciones)) {
            return false;
        }
        if (!Objects.equals(this.resenas, other.resenas)) {
            return false;
        }
        return true;
    }
    
}
