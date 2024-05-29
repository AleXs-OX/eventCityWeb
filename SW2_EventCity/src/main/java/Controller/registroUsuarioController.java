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
    private String prefijo;
    private String telefono;
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

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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
            PrimeFaces.current().executeScript("PF('passwordDialog').show();");
            return;
        }

        if (password.length() < 8) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe tener al menos 8 caracteres.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().executeScript("PF('passwordDialog').show();");
            return;
        }

        if (!password.matches(".*\\d.*")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe contener al menos un número.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().executeScript("PF('passwordDialog').show();");
            return;
        }

        if (fechaNacimiento == null || fechaNacimiento.after(new Date())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha de nacimiento debe ser anterior a la fecha actual.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().ajax().update("register-form");
            PrimeFaces.current().executeScript("PF('fechaNacimientoDialog').show();");
            return;
        }

        if (telefono == null || telefono.length() != 9 || !telefono.matches("\\d{9}")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El número de teléfono debe tener 9 dígitos y solo contener números.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().executeScript("PF('telefonoDialog').show();");
            return;
        }

        /*Usuario existingUser = usuarioEJB.findByUsername(username);
        System.out.println("He salido del findByUsername");
        System.out.println("existingUser.getNombreusuario()" + existingUser.getNombreusuario());
        if (existingUser.getNombreusuario().equals(username)) {
            System.out.println("Entro en el else");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de usuario ya existe. Por favor, elija otro.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().executeScript("PF('usernameDialog').show();");
            return;
            
        }*/

        try {
            Usuario newUser = new Usuario();
            newUser.setNombreusuario(username);
            newUser.setContrasena(password); // Asegúrate de encriptar la contraseña antes de guardarla
            newUser.setNombre(nombre);
            newUser.setApellidos(apellidos);
            newUser.setTelefono(Integer.parseInt(telefono));
            
            System.out.println("Llego aqui");
            newUser.setEmail(email);
            usuarioEJB.create(newUser);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Usuario registrado con éxito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("Me he registrado");
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el usuario: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}



