/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="eventos")
public class Evento implements Serializable{
    
    @Column (name="nombre")
    private String nombre;
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
