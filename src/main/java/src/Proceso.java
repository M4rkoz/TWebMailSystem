
package src;

import Negocio.ClientePOP;
import Negocio.ClienteSMTP;
import Datos.ConexionPGSQL;
import Negocio.MainProcess;


public class Proceso {

    public static void main(String[] args) {
        ClientePOP pop = new ClientePOP();
        ClienteSMTP smtp=new ClienteSMTP();
        ConexionPGSQL con = new ConexionPGSQL();
        //LEEMOS EL EMAIL DE DONDE OBTENDREMOS EL PATRON
    /*    pop.leerEmail(1);
         
        //OBTENEMOS EL PATRON, DEL EMAIL EN DONDE TENGO QUE OBTENER MI PATRON
        String patron = pop.getPatron(1);
        System.out.println("============================================================");
        System.out.println("Patron Obtenido:" + patron);
        System.out.println("============================================================");
 
        
         
        //OBTENGO RESULTADOS DE LA BD CON EL PATRON OBTENIDO   
        String resultados=con.getResultado(patron);
        System.out.println("Resultados \n" + resultados);
      
        //DEVUELVO UN EMAIL CON LOS RESULTADOS OBTENIDOS
        System.out.println("============================================================");
        System.out.println("Enviando Resultados"); 
        smtp.sendEmail(patron, resultados);
      */
     //   System.out.println("La cantidad de mensajes es:"+pop.cantidadMensajes());
       // System.out.println("El email es:"+pop.getEmail(13));
       
       MainProcess mainp=new MainProcess();
       mainp.readMessages();
       
    }

}
