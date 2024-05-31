
package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Column(name = "nombreusuario", nullable = false)
    private String nombreusuario;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "telefono")
    private int telefono;
    
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Publicador publicador;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Admin admin;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Suscriptor suscriptor;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Publicador getPublicador() {
        return publicador;
    }

    public void setPublicador(Publicador publicador) {
        this.publicador = publicador;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    
    public String getNombreCompleto(){
        return (this.nombre+" "+this.apellidos);
    }
}
