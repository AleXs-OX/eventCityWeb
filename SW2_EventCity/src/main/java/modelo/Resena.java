
package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "resenas")
public class Resena {
    
    @EmbeddedId
    private ResenaId id;

    @ManyToOne
    @MapsId("idSuscriptor")
    @JoinColumn(name = "idSuscriptor")
    private Suscriptor suscriptor;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "idEvento")
    private Evento evento;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;

    // Getters and Setters
    public ResenaId getId() {
        return id;
    }

    public void setId(ResenaId id) {
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
