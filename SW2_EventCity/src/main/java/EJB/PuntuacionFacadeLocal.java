/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Puntuacion;

/**
 *
 * @author Beatriz
 */
@Local
public interface PuntuacionFacadeLocal {

    void create(Puntuacion puntuacion);

    void edit(Puntuacion puntuacion);

    void remove(Puntuacion puntuacion);

    Puntuacion find(Object id);

    List<Puntuacion> findAll();

    List<Puntuacion> findRange(int[] range);

    int count();
    
    Puntuacion findPuntuacionByIdSuscriptorAndIdEvento(Integer idSuscriptor, Integer idEvento);
    boolean existePuntuacion(Integer idSuscriptor, Integer idEvento);
}
