
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CategoriaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import Exception.NoUserException;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;
import javax.inject.Named;


/**
 *
 * @author Beatriz
 */

@Named //Porque es una clase que se va a enlazar con una vista
@SessionScoped //Este es el ámbito que tiene
public class loginUsuarioController implements Serializable{
    
    //private static final long serialVersionUID = 7186752730965960966L;
    
    private String username;
    
    private String password;
    
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    
    @PostConstruct //Dice que es el primer metodo que se ejecuta
    public void init(){
        
        //categoria.setNombre("categoria por defecto");
         
    }
    
    public void redirectToRegister() throws Exception {
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //ec.redirect(ec.getRequestContextPath() + "/registroUsuario.xhtml");
        //FacesContext.getCurrentInstance().getExternalContext().redirect("/registro/registroUsuario.xhtml");
        System.out.println("HOLAAAAAAAAAAA");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/loginUsuario.xhtml");
        System.out.println(ec.getRequestContextPath() + "/faces/registroUsuario.xhtml");
        System.out.println("Cambie");
        /*String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "../SW2_EventCity/registroUsuario.xhtml";
        System.out.println("Redirigiendo a: " + url);
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);*/
    } 
    
    public String login() {
        System.out.println("Intentando iniciar sesión con: " + username + " / " + password);
        usuario = usuarioEJB.findByCredentials(username, password);
        if (usuario != null) {

            if(usuarioEJB.isSuscriptor(this.usuario.getIdUsuario())){
                System.out.println("Logeo de un suscriptor correcto: "+ this.usuario.getNombreusuario());
                /*Es suscriptor*/ // Redirige a la página principal o dashboard
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.getSessionMap().put("usuario", this.usuario);
                return "/subscriptor/homeUI.xhtml?faces-redirect=true";
            }else{
                /*Es publicador*/ // Redirige a la página principal o dashboard
                System.out.println("Logeo de un publicador correcto: "+ this.usuario.getNombreusuario());
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.getSessionMap().put("usuario", this.usuario);
                return "/publicador/publicadorUI.xhtml?faces-redirect=true";
            }
             // Redirige a la página principal o dashboard
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
    
    public String irRegistro(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "registroUsuario?faces-redirect=true";
    }
    
    
    
    
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