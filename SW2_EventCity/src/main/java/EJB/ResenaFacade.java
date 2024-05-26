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
import modelo.Resena;

/**
 *
 * @author Beatriz
 */
@Stateless
public class ResenaFacade extends AbstractFacade<Resena> {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResenaFacade() {
        super(Resena.class);
    }
    
    public List<Resena> findResenasByIdEvento(Integer idEvento) {
    return em.createQuery("SELECT r FROM Resena r WHERE r.evento.idEvento = :idEvento", Resena.class)
             .setParameter("idEvento", idEvento)
             .getResultList();
    }
}
