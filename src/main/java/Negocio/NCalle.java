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
public class NCalle {
    Calle dcalle;

   
    public NCalle() {
        this.dcalle=new Calle();
        
    }
    
   
  
    public String setCalle(String[] d){
      
        dcalle.setNumero(Integer.parseInt(d[0]));
        dcalle.setNombre(d[1]);
         dcalle.setCodigocondominio(Integer.parseInt(d[2]));
      return dcalle.registrar();
      
      
        
    } 
    
     public String getCalles(){
         
         return  dcalle.getCalles();
         
    } 
     
     public String actualizarCalle(String[] d){
        dcalle.setNumero(Integer.parseInt(d[0]));
        dcalle.setNombre(d[1]);
         dcalle.setCodigocondominio(Integer.parseInt(d[0]));
       return  dcalle.actualizar();
         
         
     }
     public String eliminar(String[] d){
      dcalle.setNumero(Integer.parseInt(d[0]));
       return dcalle.eliminar();
         
     }
}
