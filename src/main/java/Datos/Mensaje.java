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
public class Mensaje {
    
    int numero;
    String texto;
    
    
      ConexionPGSQL conexion;
 
  
    public Mensaje(){
    this.conexion= new ConexionPGSQL();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    public String registrar(){
        Connection pg=  this.conexion.getConnection();
        String res;
        try{
            
        Statement st=pg.createStatement();
        String query= "insert into mensaje values("+this.numero+",'"+this.texto+"');";
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
    
    public String getMensajes(){
    String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="SELECT * FROM mensaje";
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
        String query= "update mensaje set texto='"+this.texto+"' where numero="+this.numero+";";
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
        String query= "delete from mensaje where numero="+this.numero+"";
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
