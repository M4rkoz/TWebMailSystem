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
public class Calle {
    int numero;
    String nombre;
    int codigocondominio;
    
     ConexionPGSQL conexion;
 
  
    public Calle(){
    this.conexion= new ConexionPGSQL();
    }
  

    public int getNumero() {
        return numero;
    }

    public void setNumero(int Numero) {
        this.numero = Numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public int getCodigocondominio() {
        return codigocondominio;
    }

    public void setCodigocondominio(int codigocondominio) {
        this.codigocondominio = codigocondominio;
    }
    
   public String registrar(){
        Connection pg=  this.conexion.getConnection();
        String res;
        try{
            
        Statement st=pg.createStatement();
        String query= "insert into calle values("+this.numero+",'"+this.nombre+"',"+this.codigocondominio+");";
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
    
    public String getCalles(){
    String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="SELECT * FROM calle";
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
            System.out.println("Error en la lectura de calles"+e);
        }
             
        
       
        return linea.trim();

        
    
    }
    
    public String actualizar(){
        
           Connection con=  this.conexion.getConnection();
        String res;
        
        try{
            
        Statement st=con.createStatement();
        String query= "update calle set nombre='"+this.nombre+"',codigocondominio="+this.codigocondominio+" where numero="+this.numero+";";
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
        String query= "delete from calle where numero="+this.numero+"";
        if(st.executeUpdate(query)==1){
        res="El registro fue eliminado";
        }else{
           res="El registro no existe, no se completo la tarea";
        }
            
        con.close();
        }catch(Exception e){
            System.out.println("Se produjo un error en eliminar de Condominio: "+e);   
            res="Ocurrio un error";
        }
        
        
        return res;
    
    } 
    
    
    
    
    
    
    
}
