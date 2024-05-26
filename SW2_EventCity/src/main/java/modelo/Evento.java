/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="eventos")
public class Evento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvento")
    private int idEvento;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @Column(name = "fechaAlta")
    private Date fechaAlta;
    
    @Column(name = "fechaEvento")
    private Date fechaEvento;
    
    @Column (name="horaEvento")
    private Time horaEvento;
    
    @Column(name = "activo")
    private boolean activo;
    
    @Column(name = "precio", nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    private float precio;
    
    @ManyToOne
    @JoinColumn(name = "idPublicador")
    private Publicador publicador;
    
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "idLocalizacion")
    private Localizacion localizacion;
    
    @Column(name = "capacidadActual", columnDefinition = "INT DEFAULT 1")
    private int capacidadActual;

    @OneToMany(mappedBy = "evento")
    private List<Suscripcion> suscripciones;

    @OneToMany(mappedBy = "evento")
    private List<Puntuacion> puntuaciones;

    @OneToMany(mappedBy = "evento")
    private List<Resena> resenas;
 //Faltan las foreing keys de Publicador,Categoria,Localizacion,Reseña

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Publicador getPublicador() {
        return publicador;
    }

    public void setPublicador(Publicador publicador) {
        this.publicador = publicador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
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

    public Time getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Time horaEvento) {
        this.horaEvento = horaEvento;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idEvento;
        hash = 31 * hash + Objects.hashCode(this.titulo);
        hash = 31 * hash + Objects.hashCode(this.descripcion);
        hash = 31 * hash + Objects.hashCode(this.fechaAlta);
        hash = 31 * hash + Objects.hashCode(this.fechaEvento);
        hash = 31 * hash + (this.activo ? 1 : 0);
        hash = 31 * hash + Float.floatToIntBits(this.precio);
        hash = 31 * hash + Objects.hashCode(this.publicador);
        hash = 31 * hash + Objects.hashCode(this.categoria);
        hash = 31 * hash + Objects.hashCode(this.localizacion);
        hash = 31 * hash + this.capacidadActual;
        hash = 31 * hash + Objects.hashCode(this.suscripciones);
        hash = 31 * hash + Objects.hashCode(this.puntuaciones);
        hash = 31 * hash + Objects.hashCode(this.resenas);
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
        final Evento other = (Evento) obj;
        if (this.idEvento != other.idEvento) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (this.capacidadActual != other.capacidadActual) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlta, other.fechaAlta)) {
            return false;
        }
        if (!Objects.equals(this.fechaEvento, other.fechaEvento)) {
            return false;
        }
        if (!Objects.equals(this.publicador, other.publicador)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.localizacion, other.localizacion)) {
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
