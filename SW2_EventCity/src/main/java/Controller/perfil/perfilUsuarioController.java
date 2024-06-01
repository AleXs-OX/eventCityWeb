/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.perfil;

import java.util.Date;
import modelo.Usuario;
import modelo.Admin;
import modelo.Publicador;
import modelo.Suscriptor;
import EJB.UsuarioFacadeLocal;
import EJB.SuscriptorFacadeLocal;
import EJB.PublicadorFacadeLocal;
import EJB.AdminFacadeLocal;
import EJB.EventoFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 *
 * @author Pablo González Santamarta
 */
@Named
@SessionScoped
public class perfilUsuarioController implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private SuscriptorFacadeLocal suscriptorEJB;
    @EJB
    private PublicadorFacadeLocal publicadorEJB;
    @EJB
    private AdminFacadeLocal adminEJB;
    @EJB
    private EventoFacadeLocal eventoEJB;
        
    private Usuario usuario;
    private Publicador publicador;
    private Suscriptor suscriptor;
    private Admin admin;    

    /**
     * Indica el tipo de usuario para control de errores
     */
    private int userType = 0;
    
    public perfilUsuarioController(){
        //this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    @PostConstruct
    public void init(){
        //FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	//ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        System.out.println("->> Estoy cargando el usuario"+this.usuario.getNombre());
        if (usuario == null){
		System.out.println("El usuario indicado no existe. Se procede a redirigir al login");
            /*try {
                ec.redirect(ec.getRequestContextPath() + "/loginUsuario.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(perfilUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
	}
	else{

                if(this.suscriptorEJB.isSuscriptor(this.usuario.getIdUsuario())){
                    suscriptor = suscriptorEJB.findByUser(usuario);
                    userType = 1;
                    System.out.println("Typed 1");
                } else if(this.publicadorEJB.isPublicador(this.usuario.getIdUsuario())){
                    publicador = publicadorEJB.findByUser(usuario);
                    userType = 2;
                    System.out.println("Typed 2");
                }else{
                    System.out.println("No se encontro el usuario en perfilUsuarioController");
                }
	}
    }
    
    /**
     * Muestra la información del usuario como Suscriptor, Publicador o Administrador
     * @return 
     */
    
    public String displayExtraInformartion(){
    
        switch (userType){
            case 1:
                return createSubPanel();
            case 2:
                return createPubPanel();
            case 3:
                return createAdminPanel();
            default:
                return "";
        }
    }
    
    /**
     * Guarda los cambios realizados en el perfil en la base de datos
     * @return 
     */
    public boolean saveChanges(){
        usuarioEJB.edit(usuario);
	switch (userType){
		case 1:
			suscriptorEJB.edit(suscriptor);
			break;
		case 2:
			publicadorEJB.edit(publicador);
			break;
		case 3:
			adminEJB.edit(admin);
			break;
	}
        return true;
    }
    
    // Getter del tipo de usuario
    
    public int getUserType(){
        return userType;
    }
    
    // Getters y Setters comunes a todos los usuarios

    public void setUsername(String username){
        usuario.setNombreusuario(username);
    }
    
    public void setName(String name){
        usuario.setNombre(name);
    }
    
    public void setSurname(String surname){
        usuario.setApellidos(surname);
    }
    
    public void setNumber(int number){
        usuario.setTelefono(number);
    }
    
    public void setEmail(String email){
        usuario.setEmail(email);
    }
    
    public String getUsername(){
        return usuario.getNombreusuario();
    }
    
    public String getName(){
        return usuario.getNombre();
    }
    
    public String getSurname(){
        return usuario.getApellidos();
    }
    
    public int getNumber(){
        return usuario.getTelefono();
    }
    
    public String getEmail(){
        return usuario.getEmail();
    }
    
    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }
    
    // Getters y Setters exclusivos de suscriptores

    public String getNumSuscripciones(){
	if (userType == 1) return suscriptor.getNumSuscripciones();
        return "";
    }

    public String getLocation(){
	if (userType == 1) return suscriptor.getDireccion() + ", " + suscriptor.getCiudad() + ", " + suscriptor.getPais();
        return "";
    }

    public void setDireccion(String direccion){
	if (userType == 1) suscriptor.setDireccion(direccion);
    }

    public void setCiudad(String ciudad){
	if (userType == 1) suscriptor.setCiudad(ciudad);
    }

    public void setPais(String pais){
	if (userType == 1) suscriptor.setPais(pais);
    }

    // Getters y setters exclusivos de publicadores

    public Date getFechaAlta(){
	if (userType == 2) return publicador.getFechaAlta();
        return null;
    }

    public int getNumEventos(){
	if (userType == 2) return publicador.getNumEventos();
        return 0; 
    }
   
    public String getDescripcion(){
	if (userType == 2) return publicador.getDescripcion();
        return "";
    }   

    public void setFechaAlta(Date fecha){
	if (userType == 2) publicador.setFechaAlta(fecha);
    }

    public void setNumEventos(int eventos){
	if (userType == 2) publicador.setNumEventos(eventos);
    }
   
    public void setDescripcion(String descripcion){
	if (userType == 2) publicador.setDescripcion(descripcion);
    }   
   
    // Getters y setters exclusivos de administradores

    public String getRol(){
	if (userType == 3) return admin.getRol();
	return "";
    }

    public void setRol(String rol){
	if (userType == 3) admin.setRol(rol); 
    }

    private String createSubPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createPubPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createAdminPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String volverAtras() throws IOException{
        System.out.println("aaaa"+this.userType);
        if(this.userType == 1){
            //FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/faces/subscriptor/homeUI.xhtml");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/faces/subscriptor/homeUI.xhtml");
            return"";
        }else if(this.userType == 2){
            //FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/faces/publicador/publicadorUI.xhtml");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/faces/publicador/publicadorUI.xhtml");
            return"";
        }else{
            /*Type3*/
            //FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/faces/publicador/publicadorUI.xhtml");
            return "publicadorUI.xhtml?faces-redirect=true";
        }
    }
}
