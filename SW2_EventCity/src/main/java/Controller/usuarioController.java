/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CategoriaFacadeLocal;
import EJB.RolFacade;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Persona;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Named
@ViewScoped
public class usuarioController implements Serializable {
    private Categoria categoria;
    private Persona persona;
    private Rol rol;
    private Usuario usuario;
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private RolFacade rolEJB;
    
    public void insertarUsuario(){
        try{
            
            if(usuario == null || persona == null){
                throw new IllegalArgumentException("Los datos de la persona y del usuario son necesarios");
            }
            
            usuarioEJB.create(usuario);

            persona.setNombre(usuario.getUsuario());
            //persona.setApellido1();
            
            usuarioEJB.create(usuario);
        }catch(Exception e){
            System.out.println("Error al insertar al usuario en la base de datos" + e.getMessage());
        }
    }
    public Rol obtenerRolSeleccionado(int idRol) {
        return rolEJB.find(idRol);
    }

    
    //GETTERS Y SETTERS ********************************************************
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public RolFacade getRolEJB() {
        return rolEJB;
    }

    public void setRolEJB(RolFacade rolEJB) {
        this.rolEJB = rolEJB;
    }
    
    
}
