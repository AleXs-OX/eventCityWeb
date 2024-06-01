package Controller.login;

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
import javax.faces.context.ExternalContext;
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

    
    public String login() throws IOException {
        System.out.println("Intentando iniciar sesión con: " + username + " / " + password);
        usuario = usuarioEJB.findByCredentials(this.username, this.password);
        if (usuario != null) {

            if(usuarioEJB.isSuscriptor(this.usuario.getIdUsuario())){
                System.out.println("Logeo de un suscriptor correcto: "+ this.usuario.getNombreusuario());
                /*Es suscriptor*/ // Redirige a la página principal o dashboard
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.getSessionMap().put("usuario", this.usuario);
                ec.redirect(ec.getRequestContextPath() + "/faces/subscriptor/homeUI.xhtml");
                //return "/subscriptor/homeUI.xhtml?faces-redirect=true";
                return "";
            }else{
                /*Es publicador*/ // Redirige a la página principal o dashboard
                System.out.println("Logeo de un publicador correcto: "+ this.usuario.getNombreusuario());
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.getSessionMap().put("usuario", this.usuario);
                ec.redirect(ec.getRequestContextPath() + "/faces/publicador/publicadorUI.xhtml");
                //return "/publicador/publicadorUI.xhtml?faces-redirect=true";
                return"";
            }
             // Redirige a la página principal o dashboard
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de usuario desconocido", null));
            PrimeFaces.current().executeScript("PF('loginDialog').show();");
            return "login";
        }
        
        
    
    } 
    
    public String irRegistro(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "registroUsuario?faces-redirect=true";
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


