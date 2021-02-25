/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templatesss
 * and open the template in the editor.
 */
package Datos;


/**
 *
 * @author RedHat
 */
public class Condominio {
  String codigo;
  String nombre;
  String direccion;
  ConexionPGSQL con;
  
    public Condominio(){
    this.con= new ConexionPGSQL();
    }
  

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
    

  
        
         
    
    
    
    
}
