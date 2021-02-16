/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author RedHat
 */
public class Analizador {

    public String procesarPatron(String patron) {
        String resultado="";
        String[] cuerpo=patron.split(":");
        String codigo;
        String[] aux;
        String crud="";
        String[] datos={};
        if(cuerpo.length==1){
        codigo=cuerpo[0].replace("[", "").replace("]", "").trim();
        aux=codigo.split(",");
        if(aux.length==1){
            patron=aux[0].toUpperCase();
           }else{
             patron=aux[0].toUpperCase();
             crud=aux[1].toUpperCase();
              }
                   
       
            }else{
                codigo=cuerpo[0].replace("[", "").replace("]", "").trim();
                aux=codigo.split(",");
                patron=aux[0].toUpperCase();
                crud=aux[1].toUpperCase();
                datos=cuerpo[1].replace("[", "").replace("]", "").trim().split(",");
        
        
        
        
        
                    }
        
        
        
        switch (patron) {
            case "AYUDA":{
            
                System.out.println("El patron es:"+patron);
              resultado= "El Resultado de AYUDA son los siguientes Comandos";
            }
                break;
            case "C1":{
               System.out.println("El patron es:"+patron);
               
                 switch(crud){
                     case "I":{
                          System.out.println(patron+" INSERTARRRRRRRR:"+ datos[0]);
                          resultado= "El patron es:"+patron+" y LA OPERACION INSERTAR CON DATOS: "+datos[0];
                         
                     }break;
                     case "R":{
                         System.out.println(patron+" LECTURAAAAAAAAAAAAAAA");
                          resultado= "El patron es:"+patron+" y el OPERACION LECTURAAAA";
                     }break;
                     case "D":{
                     }break;
                     default:{
                         System.out.println("Error en la Peticion para el C1");
                     }
                         
                 
                 }
               
            
            }
                break;

            case "C2":{
            
            }
                break;

            case "C3":{
            
            
            }
                break;

            case "C4":{
            
            }
                break;
            case "C5":{
            
            }
                break;
            case "C6":{
            
            }
                break;
            case "C7":{
            
            }
                break;
            case "C8":{
            
            }
                break;
            default: {
                   
                System.out.println("Error en el patron no se pudo analizar");
                 resultado= "Error en el patron no se pudo analizar";
              }
        }
        
       
         return resultado;
    }

}
