package Controller;

import EJB.UsuarioFacadeLocal;
import EJB.AdminFacadeLocal;
import EJB.PublicadorFacadeLocal;
import EJB.SuscriptorFacadeLocal;
import java.io.IOException;
import modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class loginUsuarioController implements Serializable {

    private String username;
    private String password;
    private Usuario usuario;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @EJB
    private AdminFacadeLocal adminEJB;

    @EJB
    private PublicadorFacadeLocal publicadorEJB;

    @EJB
    private SuscriptorFacadeLocal suscriptorEJB;

    @PostConstruct
    public void init() {
        // Inicialización si es necesario
    }

    public String login() {
    System.out.println("Intentando iniciar sesión con: " + username + " / " + password);
    //String encryptedPassword = getSecurePassword(password);
    usuario = usuarioEJB.findByCredentials(username, password);
    if (usuario != null) {
        // Guardar el usuario en la sesión
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
        System.out.println("Inicio de sesión exitoso para usuario: " + usuario.getNombreusuario());
            //return "registroUsuario?faces-redirect=true";
            
        
        // Redirigir según el tipo de usuario
        if (adminEJB.isAdmin(usuario.getIdUsuario())) {
            return "/admin/adminUI?faces-redirect=true";
        } else if (suscriptorEJB.isSuscriptor(usuario.getIdUsuario())) {
            System.out.println("****************************************************************");
            return "/subscriptor/homeUI?faces-redirect=true";
        } else if (publicadorEJB.isPublicador(usuario.getIdUsuario())) {
            return "/admin/adminUI?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de usuario desconocido", null));
            return "login";
        }
    } else {
        System.out.println("Error de inicio de sesión para usuario: " + username);
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));
        return "login";
    }
}


/*private String getSecurePassword(String password) {
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
*/
    public void volverLogin() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("loginUsuario.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public String irRegistro(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "registroUsuario?faces-redirect=true";
    }
    // Getters y setters para las propiedades
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


