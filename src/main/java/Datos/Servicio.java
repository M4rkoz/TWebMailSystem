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
public class Servicio {
    int codigo;
    String nombre;
    String descripcion;
     
     ConexionPGSQL conexion;
 
  
    public Servicio(){
    this.conexion= new ConexionPGSQL();
    }
  

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     public String registrar(){
        Connection pg=  this.conexion.getConnection();
        String res;
        try{
            
        Statement st=pg.createStatement();
        String query= "insert into servicio values("+this.codigo+",'"+this.nombre+"','"+this.descripcion+"');";
         if(st.executeUpdate(query)==1){
        res="El registro fue ingresado correctamente";
        }else{
           res="Ocurrio un  error no se completo la tarea";
        }
          pg.close();
        }catch(Exception e){
            res="Se produjo un error en la inserccion del registro, error: "+e+"";
        }    
                
    return res;
    }
    
    public String getServicios(){
    String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="SELECT * FROM servicio";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                for (int i = 1; i<=3; i++) {                    
                 resultado=resultado+" | "+rs.getString(i).trim();          
                }
               
               linea=linea+resultado.trim()+"</br>";
                
               resultado="";
                
            }
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error en la lectura de servicio"+e);
        }
             
        
  
        return linea.trim();

        
    
    }
    
    public String actualizar(){
        
           Connection con=  this.conexion.getConnection();
        String res;
        
        try{
            
        Statement st=con.createStatement();
        String query= "update servicio set nombre='"+this.nombre+"',descripcion='"+this.descripcion+"' where codigo="+this.codigo+";";
         if(st.executeUpdate(query)==1){
        res="El registro fue Actualizado";
        }else{
           res="El registro no existe, no se completo la tarea";
        }
            
        con.close();
        }catch(Exception e){
           
           res="Se produjo un error en la actualizacion del registro, error: "+e+"";
        }    
                return res;
    
    }
    
    
    public String eliminar(){
    
        String res;
        
          Connection con=  this.conexion.getConnection();
   
        try{
            
        Statement st=con.createStatement();
        String query= "delete from servicio where codigo="+this.codigo+"";
        if(st.executeUpdate(query)==1){
        res="El registro fue eliminado";
        }else{
           res="El registro no existe, no se completo la tarea";
        }
            
        con.close();
        }catch(Exception e){
            System.out.println("Se produjo un error en eliminar: "+e);   
            res="Ocurrio un error";
        }
        
        
        return res;
    
    }
    
    
}
