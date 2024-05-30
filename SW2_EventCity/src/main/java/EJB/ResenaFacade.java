/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import modelo.Evento;
import modelo.ResenaId;
import modelo.Resena;
import modelo.Suscriptor;

@Stateless
public class ResenaFacade extends AbstractFacade<Resena> implements ResenaFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResenaFacade() {
        super(Resena.class);
    }
    
    @Override
    public List<Resena> findResenasByIdEvento(Integer idEvento) {
    return em.createQuery("SELECT r FROM Resena r WHERE r.evento.idEvento = :idEvento", Resena.class)
             .setParameter("idEvento", idEvento)
             .getResultList();
    }
    @Override
    public boolean existeResena(Integer idSuscriptor, Integer idEvento) {
        TypedQuery<Resena> query = em.createQuery(
            "SELECT r FROM Resena r WHERE r.evento.idEvento = :idEvento AND r.suscriptor.idSubscriptor = :idSuscriptor", 
            Resena.class
        );
        query.setParameter("idSuscriptor", idSuscriptor);
        query.setParameter("idEvento", idEvento);

        List<Resena> suscripciones = query.getResultList();
        return !suscripciones.isEmpty();
    }
    
    @Override
    public void crearResena(Integer idSuscriptor, Integer idEvento, String comentario, Date fecha) {
        
        // Obtener Suscriptor y Evento existentes
        Suscriptor suscriptor = em.find(Suscriptor.class, idSuscriptor);
        Evento evento = em.find(Evento.class, idEvento);

        if (suscriptor == null || evento == null) {
            throw new IllegalArgumentException("Suscriptor o Evento no encontrados");
        }

        // Crear el objeto ResenaID
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        
        ResenaId resenaId = new ResenaId();
        resenaId.setIdSuscriptor(suscriptor.getIdSubscriptor());
        resenaId.setIdEvento(evento.getIdEvento());
        
        // Crear el objeto Resena
        Resena resena = new Resena();
        resena.setId(resenaId);
        resena.setSuscriptor(suscriptor);
        resena.setEvento(evento);
        resena.setComentario(comentario);
        resena.setFecha(sqlDate);

        // Usar el método create de AbstractFacade para persistir la Resena
        super.create(resena);
        
        System.out.println("Reseña creada con exito");
    }
}
