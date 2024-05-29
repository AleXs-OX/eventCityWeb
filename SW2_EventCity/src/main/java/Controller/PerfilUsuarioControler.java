/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import modelo.Usuario;
import java.io.Serializable;
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
    
    private int idUser;
    
    private String username;
    private String nombre;
    private String apellidos;
    private String numero;
    private String correo;
    
    private Usuario usuario;
    
    /**
     * Guarda los cambios realizados en el perfil en la base de datos
     */
    public boolean saveChanges(){
        
        return true;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setName(String name){
        this.nombre = name;
    }
    
    public void setSurname(String surname){
        this.apellidos = surname;
    }
    
    public void setNumber(String number){
        this.numero = number;
    }
    
    public void setEmail(String email){
        this.correo = email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getName(){
        return nombre;
    }
    
    public String getSurname(){
        return apellidos;
    }
    
    public String getNumber(){
        return numero;
    }
    
    public String getEmail(){
        return correo;
    }
    
}
