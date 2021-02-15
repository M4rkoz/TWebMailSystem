
package src;


public class Proceso {

    public static void main(String[] args) {
        ClientePOP pop = new ClientePOP();
        ClienteSMTP smtp=new ClienteSMTP();
        ConexionPGSQL con = new ConexionPGSQL();
        //LEEMOS EL EMAIL DE DONDE OBTENDREMOS EL PATRON
        pop.leerEmail(80);
         
        //OBTENEMOS EL PATRON, DEL EMAIL EN DONDE TENGO QUE OBTENER MI PATRON
        String patron = pop.getPatron(80);
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
        
        
    }

}
