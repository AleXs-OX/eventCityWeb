/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class PuntuacionId implements Serializable {
    private int idSuscriptor;
    private int idUsuario;
    private int idEvento;

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.idSuscriptor;
        hash = 13 * hash + this.idUsuario;
        hash = 13 * hash + this.idEvento;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PuntuacionId other = (PuntuacionId) obj;
        if (this.idSuscriptor != other.idSuscriptor) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idEvento != other.idEvento) {
            return false;
        }
        return true;
    }
   
}