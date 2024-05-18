/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.suscriptor;
import java.util.ArrayList;
import java.util.List;
import modelo.Evento;
import modelo.Suscriptor;

/*Hara falta dos metodos uno que guarde y devuelva una lista con todos los eventos que hay y otro metodo
* con todos los eventos a los que esta suscrito el usuario
*/
public class suscriptorController {
    
    private List<Evento> eventos = new ArrayList<>();
    
    /*
    Obtener todos los eventos de la base de datos y mostrarlos
    */
    public List<Evento> getEventos(){
        
        return eventos;
    }
    /*
    Obtener todos los eventos a los que esta suscrito el suscriptor y mostrarlos
    */
    public List<Evento> getEventosSuscriptor(Suscriptor suscriptor){
        
        return eventos;
    }
    
}

