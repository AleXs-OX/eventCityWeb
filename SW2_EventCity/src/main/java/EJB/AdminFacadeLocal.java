/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Admin;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Local
public interface AdminFacadeLocal {

    void create(Admin admin);

    void edit(Admin admin);

    void remove(Admin admin);

    Admin find(Object id);

    List<Admin> findAll();

    List<Admin> findRange(int[] range);

    int count();
    
    boolean isAdmin(int userId);
    
    Admin findByUser(Usuario usuario);
    
    
}
