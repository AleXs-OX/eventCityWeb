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
import javax.persistence.Id;

@Entity
@Table(name="resenas")
public class Resena implements Serializable{
    
    @Id
    @Column (name="idResena")
    private int idResena;
    @Column (name="comentario")
    private String comentario;

    
    // Getter y Setter para idEvento
    public int getIdResena() {
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
    }

    // Getter y Setter para nombre
    public String getcomentario() {
        return comentario;
    }

    public void setcomentario(String comentario) {
        this.comentario = comentario;
    }
}