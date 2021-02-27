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
    CondominioServicio dcon_servicio;

    public NCondominio() {
        this.dcondominio=new Condominio();
        this.dcon_servicio=new CondominioServicio();
        
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
     
      public String getCondominio_Servicio(String[] d){
         dcondominio.setCodigo(Integer.parseInt(d[0]));
         return  dcondominio.getServiciosdeCondominio();
         
    } 
     //Condominio_Servicio
     //TODO
     //Negocio condiminio
     //negocio presentacion
   
      
//    public String getCon_Servicio(String[] d){
//         dcon_servicio.setCodigocondominio(Integer.parseInt(d[0]));
//         dcon_servicio.setCodigoservicio(Integer.parseInt(d[1]));
//         return  dcon_servicio.getCondominio_servicio();
//         
//    } 
//     
     
    public String setCondominio_servicio(String[] d){
    
        dcon_servicio.setCodigocondominio(Integer.parseInt(d[0]));
        dcon_servicio.setCodigoservicio(Integer.parseInt(d[1]));
        dcon_servicio.setFcreaion(d[2]);
        dcon_servicio.setEstado(Integer.parseInt(d[2]));
        return dcon_servicio.registrar();
      }
    
    
   
     
     public String actualizarCon_servicio(String[] d){
           dcon_servicio.setCodigocondominio(Integer.parseInt(d[0]));
        dcon_servicio.setCodigoservicio(Integer.parseInt(d[1]));
        dcon_servicio.setFcreaion(d[2]);
        dcon_servicio.setEstado(Integer.parseInt(d[3]));
       return  dcon_servicio.actualizar();
         
         
     }
     public String eliminarCon_servicio(String[] d){
      dcon_servicio.setCodigocondominio(Integer.parseInt(d[0]));
      dcon_servicio.setCodigoservicio(Integer.parseInt(d[0]));
       return dcon_servicio.eliminar();
         
     }
    
    
    
    
    
     
}
