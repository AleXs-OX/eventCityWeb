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
import modelo.Suscripcion;

/**
 *
 * @author Beatriz
 */
@Stateless
public class SuscripcionFacade extends AbstractFacade<Suscripcion> implements SuscripcionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuscripcionFacade() {
        super(Suscripcion.class);
    }
    
    @Override
    public List<Suscripcion> findSuscripcionesByIdSuscriptor(Integer idSuscriptor, Integer idCategoria, Date fecha){
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return em.createQuery("SELECT e FROM Suscripcion e WHERE e.suscriptor.idSubscriptor = :idSuscriptor AND e.evento.categoria.idCategoria = :idCategoria AND e.evento.fechaEvento = :fechaEvento", Suscripcion.class)
                 .setParameter("idSuscriptor", idSuscriptor)
                 .setParameter("idCategoria", idCategoria)
                 .setParameter("fechaEvento", sqlDate)
                 .getResultList();
    }
}
