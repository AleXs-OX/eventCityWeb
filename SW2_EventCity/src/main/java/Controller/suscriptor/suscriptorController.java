/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.suscriptor;
import EJB.EventoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Evento;
import modelo.Suscriptor;

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

    /*
    Obtener todos los eventos de la base de datos y mostrarlos
    */
    public List<Evento> getEventos(){
        System.out.println("test");     
        System.out.println(eventoEJB.findAll());
        return eventoEJB.findAll();
    }
    /*
    Obtener todos los eventos a los que esta suscrito el suscriptor y mostrarlos
    */
    public List<Evento> getEventosSuscriptor(Suscriptor suscriptor){
        
        return eventos;
    }
    
}

