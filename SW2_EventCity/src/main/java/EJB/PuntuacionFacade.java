/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    
    @Override
    public Puntuacion findPuntuacionByIdSuscriptorAndIdEvento(Integer idSuscriptor, Integer idEvento) {
    try{
        return em.createQuery("SELECT e FROM Puntuacion e WHERE e.suscriptor.idSubscriptor = :idSuscriptor AND e.evento.idEvento = :idEvento", Puntuacion.class)
                     .setParameter("idSuscriptor", idSuscriptor)
                     .setParameter("idEvento", idEvento)
                     .getSingleResult();
     }catch (NoResultException e){
        return null;
     }
   }
}
