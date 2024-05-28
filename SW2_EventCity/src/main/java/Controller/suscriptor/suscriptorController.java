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
    
    private Date diaSeleccionado;
    private Date diaActual;

    private int usuarioActual = 1;
    
    private final int concierto=1;
    private final int talleresClases=2;
    private final int competicionesTorneos=3;
    private final int miscelaneo=4;

    Suscriptor suscriptorActual = new Suscriptor();
    Suscripcion suscripcionesUsuario;

    
    public suscriptorController(){
        this.diaActual = new Date();
        this.diaSeleccionado = new Date();
        
        /*Setea al suscriptor de prueba un id ya creado en bdd*/
        
        this.suscriptorActual.setIdSubscriptor(5);
        
        System.out.println(new java.sql.Date(this.diaSeleccionado.getTime()));
    }
    
    
    public List<Evento> getEventoConciertos(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.concierto,this.diaSeleccionado);
    }
    public List<Evento> getEventoTalleresyClases(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.talleresClases,this.diaSeleccionado);
    }
    public List<Evento> getEventoCompeticionesyTorneos(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.competicionesTorneos,this.diaSeleccionado);
    }
    public List<Evento> getEventoMiscelaneo(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.miscelaneo,this.diaSeleccionado);
    }
    
    /*
    Obtener todos los eventos a los que esta suscrito el suscriptor y mostrarlos

    */
 
    public List<Suscripcion> getSuscripcionesConciertos(){
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.concierto,this.diaSeleccionado);
    }
    public List<Suscripcion> getSuscripcionesTalleresyClases(){
        
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.talleresClases,this.diaSeleccionado);
    }    
    public List<Suscripcion> getSuscripcionesCompeticionesyTorneos(){
        
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.competicionesTorneos,this.diaSeleccionado);
    }
    public List<Suscripcion> getSuscripcionesMiscelaneo(){
        
        return this.suscripcionEJB.findSuscripcionesByIdSuscriptor
        (this.suscriptorActual.getIdSubscriptor(),this.miscelaneo,this.diaSeleccionado);
    }
    public void setDateSeleccionada(Date date){
        this.diaSeleccionado = date;
    }
    
    public Date getDateSeleccionada(){
        return this.diaSeleccionado;
    }
    
    public void doTest(){
        System.out.println("estoy detectando el boton");
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
    
    public void showResena(SelectEvent<String> event){
        String prueba = event.toString();
        System.out.println("prueba");
    }
    
    public void setDay(SelectEvent<Date> event){
        this.diaSeleccionado = event.getObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(this.diaSeleccionado);
        System.out.println("Fecha seleccionada: " + formattedDate);
    }
}

