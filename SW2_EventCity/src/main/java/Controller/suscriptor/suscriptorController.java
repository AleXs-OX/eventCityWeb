/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.suscriptor;
import EJB.EventoFacadeLocal;
import EJB.PuntuacionFacadeLocal;
import EJB.ResenaFacadeLocal;
import EJB.SuscripcionFacadeLocal;
import EJB.SuscriptorFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Evento;
import modelo.Puntuacion;
import modelo.Resena;
import modelo.Suscripcion;
import modelo.Suscriptor;
import org.primefaces.event.SelectEvent;

/*Hara falta dos metodos uno que guarde y devuelva una lista con todos los eventos que hay y otro metodo
* con todos los eventos a los que esta suscrito el usuario
*/
@Named //Porque es una clase que se va a enlazar con una vista
@ViewScoped //Este es el ámbito que tiene

public class suscriptorController implements Serializable{
    
    private List<Evento> eventos = new ArrayList<>();
    
    //Interaccion con los metodos para hacer querys con la tabla Evento
    @EJB
    private EventoFacadeLocal eventoEJB;
    
    @EJB
    private ResenaFacadeLocal resenaEJB;
    
    @EJB
    private PuntuacionFacadeLocal puntuacionEJB;
    
    @EJB
    private SuscripcionFacadeLocal suscripcionEJB;
    
    @EJB
    private SuscriptorFacadeLocal suscriptorEJB;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private Date diaSeleccionadoEventos;
    private Date diaSeleccionadoSus;
    private Date diaActual;

    private int usuarioActual = 1;
    

    private final int concierto=1;
    private final int talleresClases=2;
    private final int competicionesTorneos=3;
    private final int miscelaneo=4;
    private String estadoSuscripcion ="Acabas de suscribirte al evento ";
    
    private int puntuacionResena = 0;
    private String textoResena = "";


    Suscriptor suscriptorActual = new Suscriptor();
    Suscripcion suscripcionesUsuario;

    
    public suscriptorController(){
        this.diaActual = new Date();
        
        this.diaSeleccionadoEventos = new Date();
        this.diaSeleccionadoSus  = new Date();
        
        /*Setea al suscriptor de prueba un id ya creado en bdd*/
        
        this.suscriptorActual.setIdSubscriptor(5);
        
        //System.out.println(new java.sql.Date(this.diaSeleccionado.getTime()));
    }
    
    
    public List<Evento> getEventoConciertos(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.concierto,this.diaSeleccionadoEventos);
    }
    public List<Evento> getEventoTalleresyClases(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.talleresClases,this.diaSeleccionadoEventos);
    }
    public List<Evento> getEventoCompeticionesyTorneos(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.competicionesTorneos,this.diaSeleccionadoEventos);
    }
    public List<Evento> getEventoMiscelaneo(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.miscelaneo,this.diaSeleccionadoEventos);
    }
    
    /*
    Obtener todos los eventos a los que esta suscrito el suscriptor y mostrarlos

    */
 
    public List<Suscripcion> getSuscripcionesConciertos(){
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.concierto,this.diaSeleccionadoSus);
    }
    
    public List<Suscripcion> getSuscripcionesTalleresyClases(){
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.talleresClases,this.diaSeleccionadoSus);
    }    
    
    public List<Suscripcion> getSuscripcionesCompeticionesyTorneos(){
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.competicionesTorneos,this.diaSeleccionadoSus);
    }
    
    public List<Suscripcion> getSuscripcionesMiscelaneo(){
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.miscelaneo,this.diaSeleccionadoSus);
    }
    
    public void setDateSeleccionadaEventos(Date date){
        this.diaSeleccionadoEventos = date;
        System.out.println("Date eventos");
        System.out.println(new java.sql.Date(this.diaSeleccionadoEventos.getTime()));
        System.out.println("");
    }
    
    public Date getDateSeleccionadaEventos(){
        return this.diaSeleccionadoEventos;
    }
    
    public void setDateSeleccionadaSus(Date date){
        this.diaSeleccionadoSus = date;
        System.out.println("Date Susc");
        System.out.println(new java.sql.Date(this.diaSeleccionadoSus.getTime()));
        System.out.println("");
    }
    
    public Date getDateSeleccionadaSus(){
        return this.diaSeleccionadoSus;
    }
    
    public void doTest(){
        System.out.println("Puntuacion");
        System.out.println(this.puntuacionResena);
        System.out.println("Resena");
        System.out.println(this.textoResena);
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
    
    /*public void setDay(SelectEvent<Date> event){
        this.diaSeleccionado = event.getObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(this.diaSeleccionado);
        System.out.println("Fecha seleccionada: " + formattedDate);
    }*/
    
    public String suscribirseAEvento(Evento evento){
        /*Crea la suscripcion a un evento*/
        if(this.suscripcionEJB.existeSuscripcion(this.suscriptorActual.getIdSubscriptor(), evento.getIdEvento())){
            Suscripcion suscripcion = new Suscripcion();
        
            suscripcion.setEstado(true);
            suscripcion.setSuscriptor(this.suscriptorActual);
            suscripcion.setEvento(evento);
            suscripcion.setFechaSus(this.diaActual);

            this.suscripcionEJB.create(suscripcion);
            System.out.println("suscrito con exito");
            this.estadoSuscripcion = "Acabas de suscribirte al evento ";
            //return "homeUI.xhtml?faces-redirect=true";
            return "";
        }
        else{
            this.estadoSuscripcion = "Ya estas suscrito al evento ";
            System.out.println("Ya estas suscrito a este evento");
            return "";
        }
    }
    
    public String desuscribirseAEvento(Evento evento){
        Suscripcion suscripcion = this.suscripcionEJB.findByIds(this.suscriptorActual.getIdSubscriptor(), evento.getIdEvento());
        this.suscripcionEJB.remove(suscripcion);
        System.out.println("Suscripcion eliminada con exito");
        //return "homeUI.xhtml?faces-redirect=true";
        return"";
    }
    
    public String estadoDeSuscripcion(){
         return this.estadoSuscripcion;
    }
    
    public String compruebaColorBotonEventos(Evento evento){
        //return "ui-button-raised btn_card";
        if(!this.suscripcionEJB.existeSuscripcion(this.suscriptorActual.getIdSubscriptor(), evento.getIdEvento())){
            return "ui-button-raised ui-button-success btn_card";
        }
        return "ui-button-raised btn_card";
    }
    
        
    public void setTextoResena(String textoR){
        this.textoResena = textoR;
    }
    
    public String getTextoResena(){
        return this.textoResena;
    }
    

    public void setPuntuacionResena(int puntuacionR){
        this.puntuacionResena = puntuacionR;
    }
    public int getPuntuacionResena(){
        return this.puntuacionResena;
    }
    

    public void creaResenaYPuntuacion(int idEvento, int idSuscriptor){
        /*Comprueba que ese usuario no tiene ni reseña ni puntuacion en ese evento*/
        
        /*Crea la resena*/
        /*Crea la puntuacion*/
    }
    public void creaResenaYPuntuacion(int idEvento){
        /*Comprueba que ese usuario no tiene ni reseña ni puntuacion en ese evento*/
        if(!this.puntuacionEJB.existePuntuacion(this.suscriptorActual.getIdSubscriptor()
                , idEvento) && !this.resenaEJB.existeResena(this.suscriptorActual.getIdSubscriptor()
                        , idEvento)){
            /*Crea la resena*/
            System.out.println("Creando reseña");
            /*Resena nuevaResena = new Resena();
            nuevaResena.setComentario(this.textoResena);
            java.sql.Date sqlDate = new java.sql.Date(this.diaSeleccionadoSus.getTime());
            nuevaResena.setFecha(sqlDate);
            nuevaResena.setEvento(this.eventoEJB.findEventoById(idEvento));
            nuevaResena.setSuscriptor(this.suscriptorEJB.findSuscriptorById(this.suscriptorActual.getIdSubscriptor()));*/
            
            /*Carga la resena creada en la bdd*/
            //resenaEJB.create(nuevaResena);
            
            /*Crea la puntuacion*/
            /*Puntuacion nuevaPuntuacion = new Puntuacion();
            nuevaPuntuacion.setEvento(this.eventoEJB.findEventoById(idEvento));
            nuevaPuntuacion.setSuscriptor(this.suscriptorEJB.findSuscriptorById(this.suscriptorActual.getIdSubscriptor()));
            //nuevaPuntuacion.setUsuario(this.usuarioEJB.findUsuariorById(this.suscriptorActual.getIdSubscriptor()));
            nuevaPuntuacion.setPuntuacion(this.puntuacionResena);
            
            puntuacionEJB.create(nuevaPuntuacion);*/
        }else{
            System.out.println("Ya existe reseña de este usuario en este evento");
        }
       
    }
}

