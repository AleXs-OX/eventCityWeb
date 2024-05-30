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
import modelo.Usuario;

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
    
    @Override
    public Suscriptor findSuscriptorById(Integer idSuscriptor){
        return em.createQuery("SELECT e FROM Suscriptor e WHERE e.idSubscriptor = :idSuscriptor", Suscriptor.class)
                 .setParameter("idSuscriptor", idSuscriptor)
                 .getSingleResult();
    }
    
    /*@Override
    public Suscriptor findSuscriptorById(Integer idSuscriptor){
        return em.createQuery("SELECT e FROM Suscriptor e WHERE e.idSubscriptor = :idSuscriptor", Suscriptor.class)
                 .setParameter("idSuscriptor", idSuscriptor)
                 .getSingleResult();
    }*/

    @Override
    public Suscriptor findByUser(Usuario usuario) {
        return em.createQuery("SLECT e FROM SUSCRIPTOR e WHERE e.idUsuario = :idUsuario", Suscriptor.class)
                .setParameter("idusuario", usuario.getIdUsuario())
                .getSingleResult();
    }
}
