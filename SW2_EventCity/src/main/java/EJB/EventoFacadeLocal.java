/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import modelo.Evento;


@Local
public interface EventoFacadeLocal {

    void create(Evento evento);

    void edit(Evento evento);

    void remove(Evento evento);

    Evento find(Object id);

    List<Evento> findAll();

    List<Evento> findRange(int[] range);

    int count();

   List<Evento> findEventoByCategoria(Integer idCategoria);
   
   List<Evento> findEventosByCategoriaAndFecha(Integer idCategoria,Date fecha);
   
   Evento findEventoById(Integer idEvento);
   
   List<Evento> findEventosByCategoriaAndFechaAndId(Integer idCategoria, Date fecha, Integer idPublicador);
   
   public List<Evento> findEventosByIdPublicador(Integer idPublicador);
   
   void creaEvento(Evento nuevoEvento, Integer idPublicador, Integer idCategoria, Integer idLocalizacion);
   
    
}
