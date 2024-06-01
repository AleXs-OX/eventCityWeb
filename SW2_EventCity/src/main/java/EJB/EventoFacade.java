/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Categoria;
import modelo.Evento;
import modelo.Localizacion;
import modelo.Publicador;


@Stateless
public class EventoFacade extends AbstractFacade<Evento> implements EventoFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }

    @Override
    public List<Evento> findEventoByCategoria(Integer idCategoria){
       return em.createQuery("SELECT e FROM Evento e WHERE e.categoria.idCategoria = :idCategoria", Evento.class)
                 .setParameter("idCategoria", idCategoria)
                 .getResultList();
    }
    
    @Override
    public List<Evento> findEventosByCategoriaAndFecha(Integer idCategoria, Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return em.createQuery("SELECT e FROM Evento e WHERE e.categoria.idCategoria = :idCategoria AND e.fechaEvento = :fechaEvento", Evento.class)
                 .setParameter("idCategoria", idCategoria)
                 .setParameter("fechaEvento", sqlDate)
                 .getResultList();
    }
    
    @Override
    public Evento findEventoById(Integer idEvento){
        return em.createQuery("SELECT e FROM Evento e WHERE e.idEvento = :idEvento", Evento.class)
                 .setParameter("idEvento", idEvento)
                 .getSingleResult();
    }
    
    @Override
    public List<Evento> findEventosByCategoriaAndFechaAndId(Integer idCategoria, Date fecha, Integer idPublicador) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return em.createQuery("SELECT e FROM Evento e WHERE e.categoria.idCategoria = :idCategoria AND e.fechaEvento = :fechaEvento AND e.publicador.idPublicador =:idPublicador", Evento.class)
                 .setParameter("idCategoria", idCategoria)
                 .setParameter("fechaEvento", sqlDate)
                 .setParameter("idPublicador", idPublicador)
                 .getResultList();
    }
    
    @Override
    public List<Evento> findEventosByIdPublicador(Integer idPublicador){
        return em.createQuery("SELECT e FROM Evento e WHERE e.publicador.idPublicador = :idPublicador", Evento.class)
                 .setParameter("idPublicador", idPublicador)
                 .getResultList();
    }
    
    @Override
    public void creaEvento(Evento nuevoEvento, Integer idPublicador, Integer idCategoria, Integer idLocalizacion){
        Publicador publicador = em.find(Publicador.class, idPublicador);
        Categoria categoria = em.find(Categoria.class, idCategoria);
        //Localizacion localizacion = em.find(Localizacion.class, idLocalizacion);
     
        
        if (publicador == null || categoria == null) {
            throw new IllegalArgumentException("Publicador o Categoria o Localizacion no encontrado"
                    + "creando Evento en EventoFacade");
        }
//        
        nuevoEvento.setPublicador(publicador);
        nuevoEvento.setCategoria(categoria);
        
        super.create(nuevoEvento);
    }

}
