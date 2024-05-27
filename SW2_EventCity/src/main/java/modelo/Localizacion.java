/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "localizaciones")
public class Localizacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocalizacion")
    private int idLocalizacion;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "ciudad")
    private String ciudad;
    
    @Column(name = "pais")
    private String pais;
    
    @Column(name = "capacidadTotal")
    private int capacidadTotal;
    
    @ManyToOne
    @JoinColumn(name = "idLugar")
    private Lugar lugar;

    @OneToMany(mappedBy = "localizacion")
    private List<Evento> eventos;

    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idLocalizacion;
        hash = 67 * hash + Objects.hashCode(this.direccion);
        hash = 67 * hash + Objects.hashCode(this.ciudad);
        hash = 67 * hash + Objects.hashCode(this.pais);
        hash = 67 * hash + this.capacidadTotal;
        hash = 67 * hash + Objects.hashCode(this.lugar);
        hash = 67 * hash + Objects.hashCode(this.eventos);
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
        final Localizacion other = (Localizacion) obj;
        if (this.idLocalizacion != other.idLocalizacion) {
            return false;
        }
        if (this.capacidadTotal != other.capacidadTotal) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        if (!Objects.equals(this.eventos, other.eventos)) {
            return false;
        }
        return true;
    }
    
    
    
    
}

