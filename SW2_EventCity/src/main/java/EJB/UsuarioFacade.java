
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
    public Usuario findByCredentials(String username, String password) {
        System.out.println("Entro en findByCredentials");
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreusuario = :username AND u.contrasena = :password",
                Usuario.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            System.out.println(username + " - Imprimo username");
            System.out.println(password + " - Imprimo password");
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Entro en la excepcion");
            return null;
        }
    }
    
    @Override
    public Usuario findByUsername(String username){
        System.out.println(username);
        
        try{
            return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreusuario = :username",
                Usuario.class
            ).setParameter("username", username).getSingleResult();
            
        }catch(Exception e){
            return null;
        }
        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
}
