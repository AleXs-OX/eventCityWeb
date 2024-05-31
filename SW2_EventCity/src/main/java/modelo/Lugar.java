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
@Table(name="lugares")
public class Lugar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int idLugar;
    
    @Column
    private String tipo;
    
    @OneToMany(mappedBy = "lugar")
    private List<Localizacion> localizaciones;

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Localizacion> getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(List<Localizacion> localizaciones) {
        this.localizaciones = localizaciones;
    }
}
