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
import modelo.Usuario;
import Exception.NoUserException;

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
    public Usuario tryLogin(String username, String password){
        try{

            List<Usuario> resultado = em.createQuery("FROM Usuario u WHERE u.username=:param1").setParameter("param1", username).getResultList();
            if(resultado.isEmpty()){ //Si no se encuentra el nombre de usuario, lanza excepcion
                throw new NoUserException();
            }else{
                Usuario user = resultado.get(0);            }
                /**
                 * 
                 * CHEKEAR CONTRASEÑA
                 * 
                 */
         }
        catch(NoUserException e){
            
        }
        return null;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
}
