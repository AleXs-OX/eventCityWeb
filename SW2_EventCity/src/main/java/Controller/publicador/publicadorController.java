/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.publicador;

import EJB.EventoFacadeLocal;
import EJB.PuntuacionFacadeLocal;
import EJB.ResenaFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Evento;
import modelo.Publicador;
import modelo.Puntuacion;
import modelo.Resena;
import modelo.Usuario;

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
    
    private Date diaSeleccionadoEventos;
    
    private int concierto=1;
    private int talleresClases=2;
    private int competicionesTorneos=3;
    private int miscelaneo=4;
    
    /*Session*/
    Usuario usuarioActual = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    Publicador publicadorActual;
    
    public publicadorController(){
        this.diaSeleccionadoEventos = new Date();
        //this.publicadorActual = this.usuarioActual.getPublicador();
        this.publicadorActual = new Publicador();
        this.publicadorActual.setIdPublicador(3);
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
        

}
