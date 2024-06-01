
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import modelo.Suscriptor;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    public Usuario findByCredentials(String username, String password) {
        System.out.println("Entro en findByCredentials");
        try {
            // Encriptar la contraseña ingresada
            String encryptedPassword = getSecurePassword(password);

            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreusuario = :username AND u.contrasena = :password",
                Usuario.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password); // Usar la contraseña encriptada
            System.out.println(username + " - Imprimo username");
            System.out.println(encryptedPassword + " - Imprimo password");
            
            List<Usuario> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Entro en la excepcion");
            return null;
        }
    }

    private String getSecurePassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public boolean findByUsername(String username) {
        System.out.println(username);

        TypedQuery<Usuario> query = em.createQuery(
            "SELECT u FROM Usuario u WHERE u.nombreusuario = :username", 
            Usuario.class
        );
        query.setParameter("username", username);

        List<Usuario> usuarios = query.getResultList();
        System.out.println(usuarios.isEmpty());
        return usuarios.isEmpty();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public boolean isSuscriptor(int idUsuario) {
        TypedQuery<Suscriptor> query = em.createQuery(
            "SELECT s FROM Suscriptor s WHERE s.usuario.idUsuario = :idUsuario", 
            Suscriptor.class
        );
        query.setParameter("idUsuario", idUsuario);
        return !query.getResultList().isEmpty();
    }
   
    @Override
    public Usuario findUserByUsername(String username){
        System.out.println(username);
        
        try{
            return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreusuario = :username",
                Usuario.class
            ).setParameter("username", username).getSingleResult();
            
        }catch(Exception e){
            return null;
        }
        
    }
}



