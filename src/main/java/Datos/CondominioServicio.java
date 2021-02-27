/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author RedHat
 */
public class CondominioServicio {
      int codigocondominio;
      int codigoservicio;
      String fcreaion;
      int estado;
      
      ConexionPGSQL conexion;
 
  
    public CondominioServicio(){
    this.conexion= new ConexionPGSQL();
    }

    public int getCodigocondominio() {
        return codigocondominio;
    }

    public void setCodigocondominio(int codigocondominio) {
        this.codigocondominio = codigocondominio;
    }

    public int getCodigoservicio() {
        return codigoservicio;
    }

    public void setCodigoservicio(int codigoservicio) {
        this.codigoservicio = codigoservicio;
    }

    public String getFcreaion() {
        return fcreaion;
    }

    public void setFcreaion(String fcreaion) {
        this.fcreaion = fcreaion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    public String registrar(){
        Connection pg=  this.conexion.getConnection();
        String res;
        try{
            
        Statement st=pg.createStatement();
        String query= "insert into condominio_servicio values("+this.codigocondominio+","+this.codigoservicio+",'"+this.fcreaion+"',"+this.estado+");";
         if(st.executeUpdate(query)==1){
        res="El registro fue ingresado correctamente";
        }else{
           res="Ocurrio un  error no se completo la tarea";
        }
          pg.close();
        }catch(Exception e){
            res="Se produjo un error en la inseccion del registro, error: "+e+"";
        }    
                
    return res;
    }
    
    public String getCondominio_servicio(){
    String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="SELECT * FROM condominio_servicio";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                for (int i = 1; i<=4; i++) {                    
                 resultado=resultado+" | "+rs.getString(i).trim();          
                }
               
               linea=linea+resultado.trim()+"</br>";
                
               resultado="";
                
            }
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error en la lectura de casa"+e);
        }
             
        
  
        return linea.trim();

        
    
    }
    
    public String actualizar(){
        
           Connection con=  this.conexion.getConnection();
        String res;
        
        try{
            
        Statement st=con.createStatement();
        String query= "update condominio_servicio set fcreacion='"+this.fcreaion+"',estado="+this.estado+" where codigocondominio="+this.codigocondominio+" and codigoservicio="+this.codigoservicio+";";
        
         if(st.executeUpdate(query)==1){
        res="El registro fue Actualizado";
        }else{
           res="El registro no existe, no se completo la tarea";
        }
            
        con.close();
        }catch(Exception e){
            System.out.println("Se produjo un error en la actualizacion de Casa: "+e);        
           res="Se produjo un error en la actualizacion del registro, error: "+e+"";
        }    
                return res;
    
    }
    
    
    public String eliminar(){
    
        String res;
        
          Connection con=  this.conexion.getConnection();
   
        try{
            
        Statement st=con.createStatement();
        String query= "delete from condominio_servicio where codigocondominio="+this.codigocondominio+" and codigoservicio="+this.codigoservicio+"";
        if(st.executeUpdate(query)==1){
        res="El registro fue eliminado";
        }else{
           res="El registro no existe, no se completo la tarea";
        }
            
        con.close();
        }catch(Exception e){
            System.out.println("Se produjo un error en eliminar de Casa: "+e);   
            res="Ocurrio un error";
        }
        
        
        return res;
    
    }
    
    

      
      
      
}
