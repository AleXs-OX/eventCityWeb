/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    /*@Override
    public Usuario findUsuariorById(Integer idSuscriptor){
        return em.createQuery("SELECT e FROM Usuario e WHERE e.admin = :idSuscriptor", Usuario.class)
                 .setParameter("idSuscriptor", idSuscriptor)
                 .getSingleResult();
    }*/
}
