package Negocio;

import java.io.*;
import java.net.*;

public class ClienteSMTP {
    // TODO Auto-generated method stub

    String servidor = "mail.tecnoweb.org.bo";
    //String servidor="172.20.172.254";
    String user_receptor = "grupo12sc@tecnoweb.org.bo";
    String user_emisor = "grupo12sc@tecnoweb.org.bo";
    String line;
    String comando = "";
    int puerto = 25;

    public void sendEmail(String patron,String data) {
        try {
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine());

                comando = "HELO " + servidor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultiline(entrada));

                comando = "MAIL FROM : " + user_emisor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "RCPT TO : " + user_receptor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "DATA\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultiline(entrada));

                comando = "SUBJECT:\n"+"Patron:"+patron+"\n"+"<h1>Resultado de Consulta a BD</h1>\n"+data+ "\n.\r\n";//DEMO EEC\r\n"+"Probando\n"+"el envio de mensajes\n"+".\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());
                
      
           //     System.out.println("S : " + getMultiline(entrada));

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : No se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public Socket openSmtpConnection(){
         Socket socket=null;
       try{
            socket = new Socket(servidor, puerto);
       }catch(IOException e){
         
           System.out.println("Ocurrio un error al Iniciar el Socket: "+e);
       }
      
       
       return socket;
    
    }

 

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }

}