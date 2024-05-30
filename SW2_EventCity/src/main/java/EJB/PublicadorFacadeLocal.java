/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Publicador;

/**
 *
 * @author Beatriz
 */
@Local
public interface PublicadorFacadeLocal {

    void create(Publicador publicador);

    void edit(Publicador publicador);

    void remove(Publicador publicador);

    Publicador find(Object id);

    List<Publicador> findAll();

    List<Publicador> findRange(int[] range);

    int count();
    
    boolean isPublicador(int userId);
    
}
