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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import modelo.Evento;
import modelo.Puntuacion;
import modelo.PuntuacionId;
import modelo.Resena;
import modelo.ResenaId;
import modelo.Suscripcion;
import modelo.Suscriptor;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Stateless
public class PuntuacionFacade extends AbstractFacade<Puntuacion> implements PuntuacionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuntuacionFacade() {
        super(Puntuacion.class);
    }
    
    @Override
    public Puntuacion findPuntuacionByIdSuscriptorAndIdEvento(Integer idSuscriptor, Integer idEvento) {
    try{
        return em.createQuery("SELECT e FROM Puntuacion e WHERE e.suscriptor.idSubscriptor = :idSuscriptor AND e.evento.idEvento = :idEvento", Puntuacion.class)
                     .setParameter("idSuscriptor", idSuscriptor)
                     .setParameter("idEvento", idEvento)
                     .getSingleResult();
     }catch (NoResultException e){
        return null;
     }
   }
    @Override
    public boolean existePuntuacion(Integer idSuscriptor, Integer idEvento) {
        TypedQuery<Puntuacion> query = em.createQuery(
            "SELECT e FROM Puntuacion e WHERE e.suscriptor.idSubscriptor = :idSuscriptor AND e.evento.idEvento = :idEvento", 
            Puntuacion.class
        );
        query.setParameter("idSuscriptor", idSuscriptor);
        query.setParameter("idEvento", idEvento);

        List<Puntuacion> suscripciones = query.getResultList();
        return !suscripciones.isEmpty();
    }
    @Override
    public void crearPuntuacion(Integer idUsuario, Integer idSuscriptor, Integer idEvento, Integer puntuacion) {
        
        // Obtener Suscriptor y Evento existentes
        Usuario usuario = em.find(Usuario.class, idUsuario);
        Suscriptor suscriptor = em.find(Suscriptor.class, idSuscriptor);
        Evento evento = em.find(Evento.class, idEvento);
        
        if (suscriptor == null || evento == null || usuario == null) {
            throw new IllegalArgumentException("Suscriptor o Evento o Usuario no encontrados");
        }

        // Crear el objeto PuntuacionID        
        PuntuacionId puntuacionId = new PuntuacionId();
        puntuacionId.setIdUsuario(usuario.getIdUsuario());
        puntuacionId.setIdSuscriptor(suscriptor.getIdSubscriptor());
        puntuacionId.setIdEvento(evento.getIdEvento());

        Puntuacion puntuacionOBJ = new Puntuacion();
        puntuacionOBJ.setUsuario(usuario);
        puntuacionOBJ.setSuscriptor(suscriptor);
        puntuacionOBJ.setEvento(evento);
        puntuacionOBJ.setPuntuacion(puntuacion);
        puntuacionOBJ.setId(puntuacionId);

        // Usar el método create de AbstractFacade para persistir la Resena
        super.create(puntuacionOBJ);
        
        System.out.println("Puntuacion creada con exito");
    }
}
