/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.publicador;

import EJB.CategoriaFacadeLocal;
import EJB.EventoFacadeLocal;
import EJB.LocalizacionFacadeLocal;
import EJB.PublicadorFacadeLocal;
import EJB.PuntuacionFacadeLocal;
import EJB.ResenaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Evento;
import modelo.Localizacion;
import modelo.Publicador;
import modelo.Puntuacion;
import modelo.Resena;
import modelo.Usuario;
import org.primefaces.PrimeFaces;

/**
 *
 * @author AleXs
 */
@Named //Porque es una clase que se va a enlazar con una vista
@ViewScoped
public class publicadorController implements Serializable{
    
    @EJB
    private EventoFacadeLocal eventoEJB;
    
    @EJB
    private ResenaFacadeLocal resenaEJB;
    
    @EJB
    private PuntuacionFacadeLocal puntuacionEJB;
    
    @EJB
    private PublicadorFacadeLocal publicadorEJB;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @EJB
    private LocalizacionFacadeLocal localizacionEJB;
    
    private Date diaSeleccionadoEventos;
    
    private int concierto=1;
    private int talleresClases=2;
    private int competicionesTorneos=3;
    private int miscelaneo=4;
    
    /*Session*/
    Usuario usuarioActual;
    Publicador publicadorActual;
    Localizacion localizacion;
    
    /*Creacion de un nuevo evento*/
    private int idEvento;
    private String tituloEvento;
    private String descripcionEvento;
    private Date fechaAltaEvento;
    private Date fechaEvento;
    private Date horaEvento;
    private boolean activoEvento;
    private int precioEvento;
    private int capacidadActualEvento;
    private Publicador publicadorEvento;
    private Categoria categoriaEvento;
    private Localizacion localizacionEvento;
    private String direccion;
    private String nombreDireccion;
    
    
    private List<Categoria> categorias;
    private String categoriaEventoId;
    
    
    public publicadorController(){
        this.diaSeleccionadoEventos = new Date();
        this.fechaAltaEvento = new Date();
    }
    
    @PostConstruct
    public void init(){
        categorias = categoriaEJB.findAll();
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        try{
            /*Obtiene el session del usuario logeado*/
            this.usuarioActual = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if(!this.usuarioEJB.isSuscriptor(this.usuarioActual.getIdUsuario())){
                System.out.println("=============================================================================");
                System.out.println("El usuario logeado en controller publicador es "+this.usuarioActual.getNombre());
                /*Obtiene el suscriptor correspondiente a ese Usuario*/
                this.publicadorActual = publicadorEJB.getPublicadorById(this.usuarioActual.getIdUsuario());
                System.out.println("Cargo clase Publicador");
            }
        }catch(Exception e){
            System.err.println("Se va a echar al usuario debido a un fallo durante su verificación de Affiliate (" + e.getMessage() + ").");
            kickUser();
        }
    }
    
    public List<Evento> getEventoConciertos(){
        return this.eventoEJB.findEventosByCategoriaAndFechaAndId(this.concierto, this.diaSeleccionadoEventos, this.publicadorActual.getIdPublicador());
    }
    public List<Evento> getEventoTalleresyClases(){
        return this.eventoEJB.findEventosByCategoriaAndFechaAndId(this.talleresClases, this.diaSeleccionadoEventos, this.publicadorActual.getIdPublicador());
    }
    public List<Evento> getEventoCompeticionesyTorneos(){
        return this.eventoEJB.findEventosByCategoriaAndFechaAndId(this.competicionesTorneos, this.diaSeleccionadoEventos, this.publicadorActual.getIdPublicador());
    }
    public List<Evento> getEventoMiscelaneo(){
        return this.eventoEJB.findEventosByCategoriaAndFechaAndId(this.miscelaneo, this.diaSeleccionadoEventos, this.publicadorActual.getIdPublicador());
    }
    
    public void setDateSeleccionada(Date date){
        this.diaSeleccionadoEventos = date;
        System.out.println("Date publicadores");
        System.out.println(new java.sql.Date(this.diaSeleccionadoEventos.getTime()));
        System.out.println("");
        }
    public Date getDateSeleccionada(){
        return this.diaSeleccionadoEventos;
    }
    
    public List<Resena> getResenasByEvento(int idEvento){
        return resenaEJB.findResenasByIdEvento(idEvento);
    }   
    
    public int getPuntuacionByIdSuscriptorAndIdEvento(int idSuscriptor, int idEvento){
        Puntuacion puntuacion = puntuacionEJB.findPuntuacionByIdSuscriptorAndIdEvento(idSuscriptor, idEvento);
        
        if (puntuacion == null)
            return 0;
        
        return puntuacion.getPuntuacion();
        
    }    
    
    public void test(){
        List<Evento> eventos = this.eventoEJB.findEventosByIdPublicador(this.publicadorActual.getIdPublicador());
        System.out.println(eventos);
    }
    
    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("admin");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/");
            System.out.println("Saliendo de la sesion....");
        } catch (Exception e) {
            System.err.println("Ocurrio un error inesperado durante el cierre de sesion.");
            System.err.println("[ERROR]: " + e.getCause() + " (" + e.getMessage() + ").");
        }
    }
    
    public void kickUser() {
        try {
            // Se limpia la sesion por precaucion
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/");
        } catch (IOException e) {
            System.err.println("No se pudo redirigir al usuario al Login");
            System.err.println("[ERROR]: " + e.getCause() + " (" + e.getMessage() + ").");
        }
    }
    public Categoria findCategoriaById(String id) {
        return categoriaEJB.find(Integer.parseInt(id));
    }
    public void btnCreaEvento() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/faces/publicador/publicadorUI.xhtml");
    }
    
    /*Setters y getters para crear un nuevo evento*/
    public Date getDiaSeleccionadoEventos() {
        return diaSeleccionadoEventos;
    }

    public void setDiaSeleccionadoEventos(Date diaSeleccionadoEventos) {
        this.diaSeleccionadoEventos = diaSeleccionadoEventos;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public Date getFechaAltaEvento() {
        return fechaAltaEvento;
    }

    public void setFechaAltaEvento(Date fechaAltaEvento) {
        this.fechaAltaEvento = fechaAltaEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Date horaEvento) {
        this.horaEvento = horaEvento;
    }

    public boolean getActivoEvento() {
        return activoEvento;
    }

    public void setActivoEvento(boolean activoEvento) {
        this.activoEvento = activoEvento;
    }

    public int getPrecioEvento() {
        return precioEvento;
    }

    public void setPrecioEvento(int precioEvento) {
        this.precioEvento = precioEvento;
    }

    public int getCapacidadActualEvento() {
        return capacidadActualEvento;
    }

    public void setCapacidadActualEvento(int capacidadActualEvento) {
        this.capacidadActualEvento = capacidadActualEvento;
    }

    public Publicador getPublicadorEvento() {
        return publicadorEvento;
    }

    public void setPublicadorEvento(Publicador publicadorEvento) {
        this.publicadorEvento = publicadorEvento;
    }

    public Categoria getCategoriaEvento() {
        return categoriaEvento;
    }

    public void setCategoriaEvento(Categoria categoriaEvento) {
        this.categoriaEvento = categoriaEvento;
    }

    public Localizacion getLocalizacionEvento() {
        return localizacionEvento;
    }

    public void setLocalizacionEvento(Localizacion localizacionEvento) {
        this.localizacionEvento = localizacionEvento;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getCategoriaEventoId() {
        return categoriaEventoId;
    }

    public void setCategoriaEventoId(String categoriaEventoId) {
        this.categoriaEventoId = categoriaEventoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }
    
    
    
    
    /*Crea un nuevo evento*/
   public void creaNuevoEvento() throws IOException {
    FacesContext context = FacesContext.getCurrentInstance();

    if (this.tituloEvento == null || this.tituloEvento.isEmpty()) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El título del evento es obligatorio."));
    }

    if (this.descripcionEvento == null || this.descripcionEvento.isEmpty()) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La descripción del evento es obligatoria."));
    }

    if (this.fechaEvento == null) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha del evento es obligatoria."));
    } else {
        LocalDateTime fechaEvento = this.fechaEvento.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime horaActual = LocalDateTime.now();
        if (fechaEvento.isBefore(horaActual)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha del evento debe ser posterior a la fecha y hora actual."));
        }
    }

    if (this.horaEvento == null) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La hora del evento es obligatoria."));
    }

    if (this.capacidadActualEvento < 0) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La capacidad del evento es obligatoria."));
    }

    if (this.precioEvento < 0) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El precio del evento debe ser un valor positivo."));
    }

    if (this.categoriaEventoId == null) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una categoria"));
    }

    if (!context.getMessageList().isEmpty()) {
        PrimeFaces.current().executeScript("PF('dlg').show()");
        return;
    }

    try {
        // Crear la localización
        Localizacion localizacion = new Localizacion();
        localizacion.setDireccion(this.direccion);
        localizacion.setNombre(this.nombreDireccion);
        localizacion.setCapacidadTotal(this.capacidadActualEvento);
        localizacionEJB.create(localizacion);

        // Crear el evento
        Evento nuevoEvento = new Evento();
        nuevoEvento.setTitulo(this.tituloEvento);
        nuevoEvento.setDescripcion(this.descripcionEvento);
        nuevoEvento.setFechaAlta(new java.sql.Date(new Date().getTime()));
        nuevoEvento.setFechaEvento(new java.sql.Date(this.fechaEvento.getTime()));
        nuevoEvento.setHoraEvento(new java.sql.Time(this.horaEvento.getTime()));
        nuevoEvento.setActivo(true);
        nuevoEvento.setPrecio(this.precioEvento);
        nuevoEvento.setCapacidadActual(this.capacidadActualEvento);
        nuevoEvento.setLocalizacion(localizacion); // Asignar la localización al evento

        Categoria categoriaSeleccionada = findCategoriaById(this.categoriaEventoId);
        eventoEJB.creaEvento(nuevoEvento, this.publicadorActual.getIdPublicador(), categoriaSeleccionada.getIdCategoria(), localizacion.getIdLocalizacion());

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Evento creado exitosamente."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/faces/publicador/publicadorUI.xhtml");

    } catch (Exception e) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al crear el evento."));
        e.printStackTrace(); 
    }
}
    public String irCreaEvento(){
        return "creaEventos.xhtml?faces-redirect=true";
      
    }
    
    public void visualizarPerfil() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/SW2_EventCity/faces/perfil/perfilUsuario.xhtml");
    }
}
