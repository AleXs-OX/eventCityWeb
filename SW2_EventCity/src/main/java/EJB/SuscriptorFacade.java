/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Suscriptor;

/**
 *
 * @author Beatriz
 */
@Stateless
public class SuscriptorFacade extends AbstractFacade<Suscriptor> implements SuscriptorFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuscriptorFacade() {
        super(Suscriptor.class);
    }
    
}
