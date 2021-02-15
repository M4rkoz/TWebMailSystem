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
public class MainProcess {
    
      ClientePOP pop;
      ClienteSMTP smtp;
      ConexionPGSQL db;
      
      
      public MainProcess(){
      pop=new ClientePOP();
      smtp= new ClienteSMTP();
      db= new ConexionPGSQL();
   
     }
    
    
    
    
    public void process(){
    
    
        
    
    
    }
    
    
    public void readMessages(){
    
    
    }
    
    
}
