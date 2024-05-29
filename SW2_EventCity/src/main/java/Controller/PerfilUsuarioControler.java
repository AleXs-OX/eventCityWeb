/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import modelo.Usuario;
import EJB.UsuarioFacadeLocal;
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
    
    
    private int idUser = 1;
    
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario = usuarioEJB.find(idUser);
    }

    /**
     * Guarda los cambios realizados en el perfil en la base de datos
     */
    public boolean saveChanges(){
        usuarioEJB.edit(usuario);
        return true;
    }
    
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
    
}
