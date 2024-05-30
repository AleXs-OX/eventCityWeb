/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Date;
import modelo.Usuario;
import modelo.Admin;
import modelo.Publicador;
import modelo.Suscriptor;
import EJB.UsuarioFacadeLocal;
import EJB.SuscriptorFacadeLocal;
import EJB.PublicadorFacadeLocal;
import EJB.AdminFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    private final int idUser = 1;
    
    private Usuario usuario;
    private Publicador publicador;
    private Suscriptor suscriptor;
    private Admin admin;    

    /**
     * Indica el tipo de usuario para control de errores
     */
    private int userType = 0;

    @PostConstruct
    public void init(){
        System.out.println("HOLA ESTOY FUNCIONANDO");
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        usuario = (Usuario) FacesContext.getCurrentInstance().getAttributes().get("usuario");
        if (usuario == null){
		System.out.println("El usuario indicado no existe. Se procede a redirigir al login");
            try {
                ec.redirect(ec.getRequestContextPath() + "/loginUsuario.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(perfilUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	else{
		suscriptor = suscriptorEJB.findByUser(usuario);
		//publicador = publicadorEJB.findByUser(usuario);
		//admin = adminEJB.findByUser(usuario);
		//if (suscriptor != null) userType = 1;
                //else if (publicador != null) userType = 2;
                //else if (admin != null) userType = 3;
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
}
