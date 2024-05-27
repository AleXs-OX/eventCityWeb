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
import modelo.Evento;


@Stateless
public class EventoFacade extends AbstractFacade<Evento> implements EventoFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }

    @Override
    public List<Evento> findEventoByCategoria(Integer idCategoria){
       return em.createQuery("SELECT e FROM Evento e WHERE e.categoria.idCategoria = :idCategoria", Evento.class)
                 .setParameter("idCategoria", idCategoria)
                 .getResultList();
    }
    
    @Override
    public List<Evento> findEventosByCategoriaAndFecha(Integer idCategoria, Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return em.createQuery("SELECT e FROM Evento e WHERE e.categoria.idCategoria = :idCategoria AND e.fechaEvento = :fechaEvento", Evento.class)
                 .setParameter("idCategoria", idCategoria)
                 .setParameter("fechaEvento", sqlDate)
                 .getResultList();
    }
    
}
