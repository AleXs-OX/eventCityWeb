/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name="personas")
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int idPersona;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido1")
    private String apellido1;
    
    @Column(name="apellido2")
    private String apellido2;
          
    @Column(name="sexo")
    private String sexo;
    
    @Column(name="fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    
}
