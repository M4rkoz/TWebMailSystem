package Negocio;

import java.io.*;
import java.net.*;

public class ClientePOP {
    //se establecen las cadenas con informacion del servidor,usuario receptor y emisor del email y puerto de conexion

    String servidor = "mail.tecnoweb.org.bo";
    //String servidor="172.20.172.254";
    String usuario = "grupo12sc";
    String contrasena = "grup012grup012";
    String comando = "";
    String linea = "";
    int puerto = 110;

    public String getPatron(int nroemail) {
        String patron = "";
        try {
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "USER " + usuario + "\r\n";
                salida.writeBytes(comando);
       
                comando = "PASS " + contrasena + "\r\n";       
                salida.writeBytes(comando);
             
                comando = "RETR " + nroemail + "\n";
            
                salida.writeBytes(comando); 
                patron = obtenerPatron(entrada);
                comando = "QUIT\r\n";
        
                salida.writeBytes(comando);
           
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patron;
    }
    
    
   public  Socket openPopConnection(){
       Socket socket=null;
       try{
            socket = new Socket(servidor, puerto);
       }catch(IOException e){
         
           System.out.println("Ocurrio un error al Iniciar el Socket: "+e);
       }
      
       
       return socket;
   }
   
   
   public void closeConnection(){
   
   
   }
  
   
 public int cantidadMensajes(){
     int nroMensajes=0;
    try {
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "USER " + usuario + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "PASS " + contrasena + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
  

                comando = "LIST\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                nroMensajes=Integer.parseInt(patronNroMensajes(entrada));
                //System.out.println("S : " + getMultiline(entrada) + "\r\n");
        
                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
     
  return nroMensajes;    
 }
   
   
    public void leerEmail(int nro) {
            
        try {
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "USER " + usuario + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "PASS " + contrasena + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
  

                comando = "RETR " + nro + "\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultiline(entrada) + "\r\n");
        
                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
       //Nos permite Obtener la Cantidad de Mensajes
    public String patronNroMensajes(BufferedReader in) throws IOException {
        String patron = "";
        String lines = "";
        while (true) {
            String line = in.readLine();

            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                // The line starts with a "." - strip it off.
                line = line.substring(1);

            }

            if (line.contains("+OK ")) {
                //SI ESTAMOS EN LA LINEA DEL SUBJECT EXTRAEMOS EL PATRON Y ROMPEMOS EL CICLO
                patron = line.substring(3);
                patron=patron.replace("messages:", "");
                patron = patron.trim();

                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return patron;

    }
    
    //Nos permite Obtener el Patron de un EMAIL
    public String obtenerPatron(BufferedReader in) throws IOException {
        String patron = "";
        String lines = "";
        while (true) {
            String line = in.readLine();

            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                // The line starts with a "." - strip it off.
                line = line.substring(1);

            }

            if (line.contains("Subject:")) {
                //SI ESTAMOS EN LA LINEA DEL SUBJECT EXTRAEMOS EL PATRON Y ROMPEMOS EL CICLO
                patron = line.substring(8);
                patron = patron.trim();

                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return patron;

    }

// Permite leer multiples lineas de respuesta del Protocolo POP
    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                // The line starts with a "." - strip it off.
                line = line.substring(1);
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }

}
