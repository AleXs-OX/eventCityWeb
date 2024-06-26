
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    

    Usuario findByCredentials(String username, String password);
    
    public boolean findByUsername(String username);

    //public Usuario findByUsername(String username);
    boolean isSuscriptor(int idUsuario);
    
    Usuario findUserByUsername(String username);

    
}
