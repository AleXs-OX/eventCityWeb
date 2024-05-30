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
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 *
 * @author Pablo González Santamarta
 */
@Named
@ViewScoped

public class PerfilUsuarioControler implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private SuscriptorFacadeLocal suscriptorEJB;
    @EJB
    private PublicadorFacadeLocal publicadorEJB;
    @EJB
    private AdminFacadeLocal adminEJB;

    private int idUser = 1;
    
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
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        usuario = usuarioEJB.find(idUser);
        if (usuario == NULL){
		System.out.println("El usuario indicado no existe. Se procede a redirigir al login");
		ec.redirect(ec.getRequestContextPath() + "/loginUsuario.xhtml");
	}
	else{
		suscriptor = suscriptorEJB.find(idUser);
		publicador = publicadorEJB.find(idUser);
		admin = adminEJB.find(idUser);
		if (suscriptor != NULL) userType = 1;
		elseif (publicador != NULL) userType = 2;
		elseif (admin != NULL) userType = 3;
	}
    }

    /**
     * Guarda los cambios realizados en el perfil en la base de datos
     */
    public boolean saveChanges(){
        usuarioEJB.edit(usuario);
	switch (tipoUsuario){
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

    // Getters y Setters exclusivos de suscriptores

    public int getNumSuscripciones(){
	if (userType == 1) return suscriptor.getNumSuscripciones();
        return 0;
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
        return NULL;
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
	if (userType == 2) return publicador.setDescripcion(String descripcion);
    }   
   
    // Getters y setters exclusivos de administradores

    public String getRol(){
	if (userType == 3) return admin.getRol();
	return "";
    }

    public void setRol(String rol){
	if (userType == 3) admin.setRol(rol); 
    }
}
