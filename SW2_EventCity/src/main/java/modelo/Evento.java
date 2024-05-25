/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name="eventos")
public class Evento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="idEvento")
    private int idEvento;
    
    @Column (name="titulo")
    private String nombre;
    
    @Column (name="descripcion")
    private String descripcion;
    
    @Column (name="fechaAlta")
    private Date fechaAlta;
    
    @Column (name="fechaEvento")
    private Timestamp  fechaEvento;

    @Column (name="idCategoria")
    private int idCategoria ;
        
    @Column (name="precio")
    private boolean precio;

    @Column (name="activo")
    private int activo;
    
    @Column (name="capacidadActual")
    private int capacidadActual;


 //Faltan las foreing keys de Publicador,Categoria,Localizacion,Reseña
    
    
    
    // Getter y Setter para idEvento
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //Getter y Setter para fechaEvento
    public Timestamp  getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Timestamp  fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    
    // Getter y Setter para fechaAlta
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /*
    // Getter y Setter para idPuntuacion
    public int getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(int idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    // Getter y Setter para activo
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Getter y Setter para precio
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    // Getter y Setter para capacidad
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    */
}
