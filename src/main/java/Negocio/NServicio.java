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
public class NServicio {
    Servicio dservicio;

    public NServicio() {
        this.dservicio=new Servicio();
        
    }
    
   
  
    public String setServicio(String[] d){
      
        dservicio.setCodigo(Integer.parseInt(d[0]));
        dservicio.setNombre(d[1]);
         dservicio.setDescripcion(d[2]);
      return dservicio.registrar();
      
      
        
    } 
    
     public String getServicios(){
         
         return  dservicio.getServicios();
         
    } 
     
     public String actualizarServicios(String[] d){
        dservicio.setCodigo(Integer.parseInt(d[0]));
        dservicio.setNombre(d[1]);
         dservicio.setDescripcion(d[2]);
       return  dservicio.actualizar();
         
         
     }
     public String eliminar(String[] d){
      dservicio.setCodigo(Integer.parseInt(d[0]));
       return dservicio.eliminar();
         
     }
   
}
