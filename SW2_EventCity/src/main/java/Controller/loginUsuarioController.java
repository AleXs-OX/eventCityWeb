
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UsuarioFacadeLocal;
import modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Controlador para el inicio de sesión de usuarios.
 */
@Named
@SessionScoped
public class loginUsuarioController implements Serializable {

    private String username;
    private String password;
    private Usuario usuario;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @PostConstruct
    public void init() {
        // Inicialización si es necesario
    }

    public String login() {
        System.out.println("Intentando iniciar sesión con: " + username + " / " + password);
        usuario = usuarioEJB.findByCredentials(username, password);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso para usuario: " + usuario.getNombreusuario());
            return "/subscriptor/homeUI?faces-redirect=true"; // Redirige a la página principal o dashboard
        } else {
            System.out.println("Error de inicio de sesión para usuario: " + username);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));
            return "login";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "loginUsuario?faces-redirect=true";
    }

    public String irRegistro() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "registroUsuario?faces-redirect=true";
    }

    // Getters y setters

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

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
}
