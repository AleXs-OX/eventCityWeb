
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

    @OneToOne(mappedBy = "usuario")
    private Publicador publicador;

    @OneToOne(mappedBy = "usuario")
    private Admin admin;

    @OneToMany(mappedBy = "usuario")
    private List<Suscriptor> suscriptores;

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

    public List<Suscriptor> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(List<Suscriptor> suscriptores) {
        this.suscriptores = suscriptores;
    }
    
    public String getNombreCompleto(){
        return (this.nombre+" "+this.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreusuario, contrasena, nombre, apellidos, telefono, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return idUsuario == other.idUsuario &&
               telefono == other.telefono &&
               Objects.equals(nombreusuario, other.nombreusuario) &&
               Objects.equals(contrasena, other.contrasena) &&
               Objects.equals(nombre, other.nombre) &&
               Objects.equals(apellidos, other.apellidos) &&
               Objects.equals(email, other.email);
    }
    
}
