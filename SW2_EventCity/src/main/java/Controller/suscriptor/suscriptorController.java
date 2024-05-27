/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.suscriptor;
import EJB.EventoFacadeLocal;
import EJB.PuntuacionFacadeLocal;
import EJB.ResenaFacadeLocal;
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
    
    private Date diaSeleccionado;
    private Date diaActual;
    private int usuarioActual = 1;
    
    private int concierto=1;
    private int c2=2;
    private int c3=3;
    private int miscelaneo=4;

    public suscriptorController(){
        this.diaActual = new Date();
        this.diaSeleccionado = new Date();
        System.out.println(new java.sql.Date(this.diaSeleccionado.getTime()));
    }
    
    
    public List<Evento> getEventoConciertos(){
        return eventoEJB.findEventosByCategoriaAndFecha(this.concierto,this.diaSeleccionado);
    }
    public List<Evento> getEventoC2(){
        return eventoEJB.findEventoByCategoria(this.c2);
    }
    public List<Evento> getEventoC3(){
        return eventoEJB.findEventoByCategoria(this.c3);
    }
    public List<Evento> getEventoMiscelaneo(){
        return eventoEJB.findEventoByCategoria(this.miscelaneo);
    }
    
    /*
    Obtener todos los eventos a los que esta suscrito el suscriptor y mostrarlos
    */
    public List<Evento> getEventosSuscriptor(Suscriptor suscriptor){
        
        return eventos;
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
        int num = puntuacionEJB.findPuntuacionByIdSuscriptorAndIdEvento(idSuscriptor, idEvento).getPuntuacion();
        return num;
        
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

