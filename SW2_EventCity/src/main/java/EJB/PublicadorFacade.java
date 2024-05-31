/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Publicador;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Stateless
public class PublicadorFacade extends AbstractFacade<Publicador> implements PublicadorFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicadorFacade() {
        super(Publicador.class);
    }
    
    @Override
    public Publicador findByUser(Usuario usuario) {
        return em.createQuery("SELECT e FROM Publicador e WHERE e.usuario.idUsuario = :idUsuario", Publicador.class)
                .setParameter("idUsuario", usuario.getIdUsuario())
                .getSingleResult();
    }
}
