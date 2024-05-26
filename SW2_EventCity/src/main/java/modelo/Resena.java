package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "resenas")
public class Resena implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idSuscriptor", nullable = false)
    private Suscriptor suscriptor;

    @ManyToOne
    @JoinColumn(name = "idEvento", nullable = false)
    private Evento evento;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha")
    private Date fecha;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
