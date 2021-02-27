/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templatesss
 * and open the template in the editor.
 */
package Datos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author RedHat
 */
public class Condominio {
  int codigo;
  String nombre;
  String direccion;
  ConexionPGSQL conexion;
 
  
    public Condominio(){
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String registrar(){
        Connection pg=  this.conexion.getConnection();
        String res;
        try{
            
        Statement st=pg.createStatement();
        String query= "insert into condominio values("+this.codigo+",'"+this.nombre+"','"+this.direccion+"');";
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
    
    public String getCondominios(){
    String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="SELECT * FROM condominio";
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
            System.out.println("Error en la lectura de condominios"+e);
        }
             
        
  
        return linea.trim();

        
    
    }
    
    public String actualizar(){
        
           Connection con=  this.conexion.getConnection();
        String res;
        
        try{
            
        Statement st=con.createStatement();
        String query= "update condominio set nombre='"+this.nombre+"',direccion='"+this.direccion+"' where codigo='"+this.codigo+"';";
         if(st.executeUpdate(query)==1){
        res="El registro fue Actualizado";
        }else{
           res="El registro no existe, no se completo la tarea";
        }
            
        con.close();
        }catch(Exception e){
            System.out.println("Se produjo un error en la actualizacion de Condominio: "+e);        
           res="Se produjo un error en la actualizacion del registro, error: "+e+"";
        }    
                return res;
    
    }
    
    
    public String eliminar(){
    
        String res;
        
          Connection con=  this.conexion.getConnection();
   
        try{
            
        Statement st=con.createStatement();
        String query= "delete from condominio where codigo="+this.codigo+"";
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
    
    public String getServiciosdeCondominio(){
      String resultado="";
    String linea="";
        Connection con=this.conexion.getConnection();
        try {
            Statement st=con.createStatement();
            String query="select * from condominio as c INNER JOIN condominio_servicio as cs on c.codigo=cs.codigocondominio\n" +
                         "INNER JOIN servicio as s on cs.codigoservicio=s.codigo\n" +
                          "where c.codigo="+this.codigo+"";
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
            System.out.println("Error en la lectura de condominios"+e);
        }
             
        
  
        return linea.trim();
    
    }
    

  
        
         
    
    
    
    
}
