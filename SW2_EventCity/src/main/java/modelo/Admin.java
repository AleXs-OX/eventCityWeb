
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name="admin")
public class Admin implements Serializable {
    
    @Id
    @Column(name="idUsuario") //Valor autogenerado
    private int idUsuario;
    
    @Column(name="rol")
    private String rol;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idUsuario;
        hash = 71 * hash + Objects.hashCode(this.rol);
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
        final Admin other = (Admin) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        return true;
    }

    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



}
