/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Beatriz
 */

@Named
@ViewScoped
public class registroUsuarioController implements Serializable {
    
    private String username;
    private String password;
            
            
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    
    @PostConstruct //Dice que es el primer metodo que se ejecuta
    public void init(){

    }
    public void test(){
        System.out.println("botonnn");
    }
}
