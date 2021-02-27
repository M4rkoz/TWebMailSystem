/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Datos.*;
/**
 *
 * @author RedHat
 */
public class NMensaje {
    Mensaje dmensaje;

   
    public NMensaje() {
        this.dmensaje=new Mensaje();
        
    }
    
   
  
    public String setMensaje(String[] d){
      
        dmensaje.setNumero(Integer.parseInt(d[0]));
        dmensaje.setTexto(d[1]);
        
      return dmensaje.registrar();
      
      
        
    } 
    
     public String getMensajes(){
         
         return  dmensaje.getMensajes();
         
    } 
     
     public String actualizarMensaje(String[] d){
        dmensaje.setNumero(Integer.parseInt(d[0]));
        dmensaje.setTexto(d[1]);
       
       return  dmensaje.actualizar();
         
         
     }
     public String eliminar(String[] d){
      dmensaje.setNumero(Integer.parseInt(d[0]));
       return dmensaje.eliminar();
         
     }
    
    
}
