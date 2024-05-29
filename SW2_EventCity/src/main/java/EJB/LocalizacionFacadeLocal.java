/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Localizacion;

/**
 *
 * @author Beatriz
 */
@Local
public interface LocalizacionFacadeLocal {

    void create(Localizacion localizacion);

    void edit(Localizacion localizacion);

    void remove(Localizacion localizacion);

    Localizacion find(Object id);

    List<Localizacion> findAll();

    List<Localizacion> findRange(int[] range);

    int count();
    
}
