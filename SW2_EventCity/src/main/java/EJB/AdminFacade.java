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
import modelo.Admin;

/**
 *
 * @author Beatriz
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> implements AdminFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;
    
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }
    
    @Override
    public boolean isAdmin(int userId) {
        
        TypedQuery<Admin> query = em.createQuery(
            "SELECT a FROM Admin a WHERE a.idUsuario = :idUsuario", Admin.class);
        query.setParameter("idUsuario", userId);
        boolean hola = query.getResultList().isEmpty();
        System.out.println(hola);
        return !hola;
    }
    
}
