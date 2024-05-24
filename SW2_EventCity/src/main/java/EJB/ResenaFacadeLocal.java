/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Resena;

/**
 *
 * @author AleXs
 */
@Local
public interface ResenaFacadeLocal {

    void create(Resena resena);

    void edit(Resena resena);

    void remove(Resena resena);

    Resena find(Object id);

    List<Resena> findAll();

    List<Resena> findRange(int[] range);

    int count();
    
}
