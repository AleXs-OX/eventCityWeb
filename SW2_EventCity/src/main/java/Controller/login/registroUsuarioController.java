/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.login;

import EJB.SuscriptorFacadeLocal;
import EJB.UsuarioFacadeLocal;
import modelo.Usuario;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Suscriptor;
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
    private String telefono;
    private String email;
    private Date fechaNacimiento;
    private String direccion;
    private String ciudad;
    private String pais;
    
    private Suscriptor suscriptor;

    @EJB
    private SuscriptorFacadeLocal suscriptorEJB;
    
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
    if (nombre == "" || apellidos == "") {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se debe rellenar los datos personales");
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().executeScript("PF('nombreApellidosDialog').show();");
        return;
    }

    if (!usuarioEJB.findByUsername(username)) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de usuario ya existe. Por favor, elija otro.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().executeScript("PF('usernameDialog').show();");
        return;
    }

    try {
        Usuario newUser = new Usuario();
        newUser.setNombreusuario(username);
        newUser.setContrasena(password); // Almacenar la contraseña encriptada
        newUser.setNombre(nombre);
        newUser.setApellidos(apellidos);
        newUser.setTelefono(Integer.parseInt(telefono));
        newUser.setEmail(email);

        usuarioEJB.create(newUser);
        Suscriptor newSuscriptor = new Suscriptor();
        newSuscriptor.setUsuario(newUser);
        newSuscriptor.setNumSuscripciones("0"); // Configurar otras propiedades del suscriptor si es necesario
        newSuscriptor.setCiudad(ciudad);
        newSuscriptor.setDireccion(direccion);
        newSuscriptor.setPais(pais);
        
        
        suscriptorEJB.create(newSuscriptor);


        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Usuario y suscriptor registrados con éxito.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().executeScript("PF('successDialog').show();"); // Mostrar diálogo de éxito
        // Redirigir al login
        volverLogin();

    } catch (Exception e) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar el usuario y el suscriptor: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}





private String getSecurePassword(String password) {
    String generatedPassword = null;
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return generatedPassword;
}

    
    
    public String volverLogin() {
        return "loginUsuario.xhtml?faces-redirect=true";
    }
    
}


