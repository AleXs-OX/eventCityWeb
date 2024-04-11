/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CategoriaFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Beatriz
 */
@Named
@ViewScoped
public class altaUsuarioController implements Serializable {
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
}
