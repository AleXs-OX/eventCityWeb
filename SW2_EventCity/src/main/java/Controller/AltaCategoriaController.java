/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CategoriaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;

/**
 *
 * @author Beatriz
 */

@Named //Porque es una clase que se va a enlazar con una vista
@ViewScoped //Este es el ámbito que tiene

public class AltaCategoriaController implements Serializable{
    
    private Categoria categoria;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @PostConstruct //Dice que es el primer metodo que se ejecuta
    public void init(){
        //Reservar memoria de categoria
        categoria = new Categoria();
        //categoria.setNombre("categoria por defecto");
    }
    
    public void insertarCategoria(){
        try{
            categoriaEJB.create(categoria);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria en la base de datos" + e.getMessage());
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }
    
}
