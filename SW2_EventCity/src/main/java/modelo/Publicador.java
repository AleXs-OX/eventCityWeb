package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publicadores")
public class Publicador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublicador")
    private int idPublicador;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaAlta")
    private Date fechaAlta;
    
    @Column(name = "numEventos")
    private int numEventos;
    
    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "publicador")
    private List<Evento> eventos;

    // Constructors, getters, and setters
    
    @Override
    public int hashCode() {
        return Objects.hash(idPublicador);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Publicador other = (Publicador) obj;
        return idPublicador == other.idPublicador;
    }

    public int getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(int idPublicador) {
        this.idPublicador = idPublicador;
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