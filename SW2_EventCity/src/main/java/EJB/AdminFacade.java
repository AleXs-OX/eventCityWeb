/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Admin;
import modelo.Usuario;

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
    public Admin findByUser(Usuario usuario) {
        return em.createQuery("SELECT e FROM Admin e WHERE e.usuario.idUsuario = :idUsuario", Admin.class)
                .setParameter("idUsuario", usuario.getIdUsuario())
                .getSingleResult();}
    
}
