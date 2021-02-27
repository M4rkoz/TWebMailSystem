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
public class Casa {
    
   int numero;
   String tipo;
   int cipropietario;
   int codigocondominio;

     ConexionPGSQL conexion;
 
  
    public Casa(){
    this.conexion= new ConexionPGSQL();
    }
   
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCipropietario() {
        return cipropietario;
    }

    public void setCipropietario(int cipropietario) {
        this.cipropietario = cipropietario;
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
        String query= "insert into casa values("+this.numero+",'"+this.tipo+"',"+this.cipropietario+","+this.codigocondominio+");";
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
    
    public String getCasas(){
    String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="SELECT * FROM casa";
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
        String query= "update casa set tipo='"+this.tipo+"',cipropietario="+this.cipropietario+",codigocondominio="+this.codigocondominio+" where numero="+this.numero+";";
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
        String query= "delete from casa where numero="+this.numero+"";
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
