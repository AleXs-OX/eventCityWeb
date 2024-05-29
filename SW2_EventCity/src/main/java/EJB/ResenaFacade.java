/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import modelo.Puntuacion;
import modelo.Resena;

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
}