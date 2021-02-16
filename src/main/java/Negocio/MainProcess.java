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
      Analizador analizer;
      
      
      public MainProcess(){
      pop=new ClientePOP();
      smtp= new ClienteSMTP();
      db= new ConexionPGSQL();
      analizer=new Analizador();
     }
    
    
    
    
    public void process(){
      
         
    
        
    
    
    }
    
    
    public void readMessages(){
      
        //INICIO PROTOCOLO POP
      int nroMensajes;
      BufferedReader entradaPop;
      DataOutputStream salidaPop;  
      Socket popSocket=pop.openPopConnection();
      
      entradaPop=pop.entradaPop(popSocket);
      salidaPop=pop.salidaPop(popSocket);
      
      pop.iniciarSesionPop(popSocket, entradaPop, salidaPop);
      
      // FIN PROTOCOLO POP
      //INICIO PROTOCOLO SMTP
      BufferedReader entradaSmtp;
      DataOutputStream salidaSmtp; 
     
      Socket smtpSocket=smtp.openSmtpConnection();
      entradaSmtp=smtp.entradaSmtp(smtpSocket);
      salidaSmtp=smtp.salidaSmtp(smtpSocket);
     
      smtp.iniciarSesionSmtp(smtpSocket, entradaSmtp, salidaSmtp);
      //FIN PROTOCOLO SMTP
      
      nroMensajes=pop.cantidadMensajes(popSocket,entradaPop,salidaPop);
      if(nroMensajes>0){
       
          for (int i = 1; i <= nroMensajes; i++) {
          
              //Captura de Email
              String email="grupo16sc@tecnoweb.org.bo";//pop.getEmail(i, popSocket, entradaPop, salidaPop);
              //Captura del Patron
              String patron=pop.getPatron(i, entradaPop, salidaPop);
              //Analizando el Patron
              
              //Obtencion de resultado
              String resultados=analizer.procesarPatron(patron); //db.getResultado(patron);
              //Enviando Resultados al Email      
              smtp.sendEmail(patron,email, resultados,entradaSmtp,salidaSmtp);
              //Eliminando el Mensaje respondido
              pop.eliminarMensaje(i, entradaPop, salidaPop);
              
              System.out.println("El Email es: "+email);
              System.out.println("El patron es: "+patron);
          
          }
          
          
       }
      
      
      smtp.cerrarSesionSmtp(smtpSocket, entradaSmtp, salidaSmtp);
      pop.cerrarSesionPop(popSocket, entradaPop, salidaPop);
      
      
       System.out.println("LA CANTIDAD DE MENSAJES ES:"+nroMensajes);
      
      
        
    }
    
 
    
    
}
