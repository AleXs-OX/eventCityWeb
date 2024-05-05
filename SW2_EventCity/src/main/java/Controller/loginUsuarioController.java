/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CategoriaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct //Dice que es el primer metodo que se ejecuta
    public void init(){
        //Reservar memoria de categoria
        usuario = new Usuario();
        //categoria.setNombre("categoria por defecto");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }
    


}
