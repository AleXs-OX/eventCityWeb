/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import modelo.Publicador;

/**
 *
 * @author Beatriz
 */
@Stateless
public class PublicadorFacade extends AbstractFacade<Publicador> implements PublicadorFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    public boolean isPublicador(int userId) {
        TypedQuery<Publicador> query = em.createQuery(
            "SELECT p FROM Publicador p WHERE p.usuario.idUsuario = :idUsuario", Publicador.class);
        query.setParameter("idUsuario", userId);
        return !query.getResultList().isEmpty();
    }
    
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicadorFacade() {
        super(Publicador.class);
    }
    
}
