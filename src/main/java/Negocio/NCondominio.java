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
public class NCondominio {
    Condominio dcondominio;

    public NCondominio() {
        this.dcondominio=new Condominio();
        
    }
    
   
  
    public String setCondominio(String[] d){
      
        dcondominio.setCodigo(Integer.parseInt(d[0]));
        dcondominio.setNombre(d[1]);
         dcondominio.setDireccion(d[2]);
      return dcondominio.registrar();
      
      
        
    } 
    
     public String getCondominios(){
         
         return  dcondominio.getCondominios();
         
    } 
     
     public String actualizarCondominio(String[] d){
        dcondominio.setCodigo(Integer.parseInt(d[0]));
        dcondominio.setNombre(d[1]);
         dcondominio.setDireccion(d[2]);
       return  dcondominio.actualizar();
         
         
     }
     public String eliminar(String[] d){
      dcondominio.setCodigo(Integer.parseInt(d[0]));
       return dcondominio.eliminar();
         
     }
   
}
