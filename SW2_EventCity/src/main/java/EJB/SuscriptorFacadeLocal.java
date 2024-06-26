/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Suscriptor;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Local
public interface SuscriptorFacadeLocal {

    void create(Suscriptor suscriptor);

    void edit(Suscriptor suscriptor);

    void remove(Suscriptor suscriptor);

    Suscriptor find(Object id);

    List<Suscriptor> findAll();

    List<Suscriptor> findRange(int[] range);

    int count();

    boolean isSuscriptor(int userId);

    Suscriptor findSuscriptorById(Integer idSuscriptor);
    
    Suscriptor findByUser(Usuario usuario);
    
    public Suscriptor findSuscriptorByIdUsuario(Integer idUsuario);

    
}
