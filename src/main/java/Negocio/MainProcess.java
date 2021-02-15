/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Datos.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
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
      
      int nroMensajes;
      BufferedReader entradaPop;
      DataOutputStream salidadPop;  
      Socket popSocket=pop.openPopConnection();
      
      entradaPop=pop.entradaPop(popSocket);
      salidadPop=pop.salidaPop(popSocket);
      
      pop.iniciarSesionPop(popSocket, entradaPop, salidadPop);
      
      
      
      nroMensajes=pop.cantidadMensajes(popSocket,entradaPop,salidadPop);
      if(nroMensajes>0){
       
          for (int i = 1; i <= nroMensajes; i++) {
          
              String email=pop.getEmail(i, popSocket, entradaPop, salidadPop);
              System.out.println("El Email es: "+email);
          
          }
          
          
       }
      
      
      pop.cerrarSesionPop(popSocket, entradaPop, salidadPop);
      
      
        System.out.println("LA CANTIDAD DE MENSAJES ES:"+nroMensajes);
      
      
        
    }
    
 
    
    
}
