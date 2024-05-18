/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="suscriptor")
public class Suscriptor implements Serializable{
    
    @Id
    @Column(name="idSuscriptor")
    private int idSuscriptor;
}
