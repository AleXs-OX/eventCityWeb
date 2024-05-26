/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Puntuacion;

/**
 *
 * @author Beatriz
 */
@Stateless
public class PuntuacionFacade extends AbstractFacade<Puntuacion> implements PuntuacionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuntuacionFacade() {
        super(Puntuacion.class);
    }
    
}
