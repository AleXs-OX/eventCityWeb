/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import EJB.UsuarioFacadeLocal;
import modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 * Controlador para el registro de usuarios.
 */
@Named
@ViewScoped
public class registroUsuarioController implements Serializable {
    
    private String username;
    private String password;
    private String confirmPassword;
    private String nombre;
    private String apellidos;
    private Integer telefono;
    private String email;
    private Date fechaNacimiento;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @PostConstruct
    public void init() {
        // Inicialización si es necesario
    }

    // Getters y setters para todas las propiedades

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void registro() {
        if (password == null || password.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña no puede estar vacía.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        if (!password.equals(confirmPassword)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        if (fechaNacimiento == null || fechaNacimiento.after(new Date())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha de nacimiento debe ser anterior a la fecha actual.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().ajax().update("register-form");
            PrimeFaces.current().executeScript("PF('fechaNacimientoDialog').show();");
            return;
        }

        try {
            Usuario newUser = new Usuario();
            newUser.setNombreusuario(username);
            newUser.setContrasena(password); // Asegúrate de encriptar la contraseña antes de guardarla
            newUser.setNombre(nombre);
            newUser.setApellidos(apellidos);
            newUser.setTelefono(telefono);
            newUser.setEmail(email);

            usuarioEJB.create(newUser);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Usuario registrado con éxito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el usuario: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}


