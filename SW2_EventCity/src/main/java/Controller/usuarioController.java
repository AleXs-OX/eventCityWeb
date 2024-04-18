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
    private UsuarioFacadeLocal usuarioEJB;
    private RolFacade rolEJB;
    
    public void insertarUsuario(){
        try{
            usuarioEJB.create(usuario);
        }catch(Exception e){
            System.out.println("Error al insertar al usuario en la base de datos" + e.getMessage());
        }
    }
    public Rol obtenerRolSeleccionado(int idRol) {
        return rolEJB.find(idRol);
    }
    
    
}
