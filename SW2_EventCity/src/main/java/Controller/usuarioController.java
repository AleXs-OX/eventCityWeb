/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CategoriaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Usuario;

/**
 *
 * @author Beatriz
 */
@Named
@ViewScoped
public class usuarioController implements Serializable {
    private Categoria categoria;
    private List<Categoria> listaCategorias;
    private Usuario usuario;
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
      
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        cargarCategorias();
        
    }
    
    public void cargarCategorias(){
        listaCategorias = categoriaEJB.findAll();
    }
    
    public void insertarUsuario(){
        try{
            
            /*if(usuario == null || persona == null){
                throw new IllegalArgumentException("Los datos de la persona y del usuario son necesarios");
            }*/
            
            usuarioEJB.create(usuario);

            //persona.setNombre(usuario.getUsuario());
            //persona.setApellido1();
            
            usuarioEJB.create(usuario);
        }catch(Exception e){
            System.out.println("Error al insertar al usuario en la base de datos" + e.getMessage());
        }
    }
    
    
    
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    //GETTERS Y SETTERS ********************************************************
    public void setListaCategorias(List<Categoria> listaCategorias) {    
        this.listaCategorias = listaCategorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

   
    
    
}
