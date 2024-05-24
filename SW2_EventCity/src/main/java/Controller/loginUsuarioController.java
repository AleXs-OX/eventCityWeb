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

/**
 *
 * @author Beatriz
 */

@Named //Porque es una clase que se va a enlazar con una vista
@ViewScoped //Este es el ámbito que tiene
public class loginUsuarioController implements Serializable{
    
    private String username;
    
    private String password;
    
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
        ec.redirect(ec.getRequestContextPath() + "/registroUsuario.xhtml");
        System.out.println(ec.getRequestContextPath() + "/faces/registroUsuario.xhtml");
        /*String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "../SW2_EventCity/registroUsuario.xhtml";
        System.out.println("Redirigiendo a: " + url);
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);*/
    } 
    
    public String irRegistro(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return "registroUsuario?faces-redirect=true";
    }
    
    public void tryLogin() throws IOException{
        System.out.println("*********");
        //Estas dos lineas no se si son necesarias
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        //Verificación de usuario y contraseña
        //try{
            Usuario usuarioLogeado = usuarioEJB.tryLogin(username, password);
        //Si todo es correcto
        usuarioLogeado.setPassword(null);//se elimina la contraseña por seguridad
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuarioLogeado); // Guardar usuario en la sesión
        System.out.println("*********************");
        ec.getSessionMap().put("user",usuarioLogeado);
        //MIRAR PATH
       
        ec.redirect(ec.getRequestContextPath() + "/subscriptor/" + usuarioLogeado.getIdRol() + "/homeUI.xhtml");
        /**}
         * catch(NoUserException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de inicio de sesión", "Usuario no encontrado"));
            
        }* */
        
        
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
