package Presentacion;

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

    public BufferedReader entradaSmtp(Socket smtpSocket) {
        try {

            return new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error En el Metodo EntradaPop(): " + e);
            return null;
        }

    }

    public DataOutputStream salidaSmtp(Socket smtpSocket) {
        try {

            return new DataOutputStream(smtpSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error En el Metodo salidadPop(): " + e);
            return null;
        }

    }

    public void iniciarSesionSmtp(Socket smtpSocket, BufferedReader entradaSmtp, DataOutputStream salidaSmtp) {

        try {

            System.out.println("S : " + entradaSmtp.readLine());

//            comando = "HELO " + servidor + " \r\n";
//            System.out.print("C : " + comando);
//            salidaSmtp.writeBytes(comando);
//            System.out.println("S : " + entradaSmtp.readLine());
//            //System.out.println("S : " + getMultiline(entradaSmtp));
//
//            comando = "MAIL FROM : " + user_emisor + " \r\n";
//            System.out.print("C : " + comando);
//            salidaSmtp.writeBytes(comando);
//            System.out.println("S : " + entradaSmtp.readLine());

        } catch (IOException e) {
            System.out.println("Se producjo un error en el Metodo iniciarSesionPop: " + e);
        }

    }

    public void cerrarSesionSmtp(Socket smtpSocket, BufferedReader entradaSmtp, DataOutputStream salidaSmtp) {

        try {

            comando = "QUIT\r\n";
            System.out.print("C : " + comando);
            salidaSmtp.writeBytes(comando);
            System.out.println("S : " + entradaSmtp.readLine());

            // Cerramos los flujos de salida y de entrada y el socket cliente
            salidaSmtp.close();
            entradaSmtp.close();
            smtpSocket.close();

        } catch (IOException e) {
            System.out.println("Se producjo un error en el Metodo CerrarSesionPop: " + e);
        }

    }

  

    public void sendEmail(String patron, String email, String data, BufferedReader entradaSmtp, DataOutputStream salidaSmtp) {
        try {

//                comando = "HELO " + servidor + " \r\n";
//                System.out.print("C : " + comando);
//                salida.writeBytes(comando);
//                System.out.println("S : " + getMultiline(entrada));
            comando = "MAIL FROM : " + user_emisor + " \r\n";
            System.out.print("C : " + comando);
            salidaSmtp.writeBytes(comando);
            System.out.println("S : " + entradaSmtp.readLine());

            comando = "RCPT TO : " + email + " \r\n";
            System.out.print("C : " + comando);
            salidaSmtp.writeBytes(comando);
            System.out.println("S : " + entradaSmtp.readLine());

            comando = "DATA\n";
            System.out.print("C : " + comando);
            salidaSmtp.writeBytes(comando);
            System.out.println("S : " + entradaSmtp.readLine());
//            System.out.println("S : " + getMultiline(entradaSmtp));

           // comando = "SUBJECT:\n" + "Patron:" + patron + "\n" + "<h1>Resultado de Consulta a BD</h1>\n" + data + "\n.\r\n";//DEMO EEC\r\n"+"Probando\n"+"el envio de mensajes\n"+".\r\n";
           
            comando = "Subject: Respuesta al Comando : " + patron + "\r\n"
                        + "Content-Type: text/html; charset=\"UTF-8\"\n"
                        + "<h1 style=\"color:red;\">Sistema Gestion de Condominios - Grupo12SC </h1>\n" + data + "\n"
                        + ".\r\n";
            System.out.print("C : " + comando);
            salidaSmtp.writeBytes(comando);
            System.out.println("S : " + entradaSmtp.readLine());

//                comando = "QUIT\r\n";
//                System.out.print("C : " + comando);
//                salida.writeBytes(comando);
//                System.out.println("S : " + entrada.readLine());
//        
        } catch (IOException e) {
            System.out.println("Se produjo un error en el Metodo SendEmail() :" + e);
        }

    }

    public Socket openSmtpConnection() {
        Socket socket = null;
        try {
            socket = new Socket(servidor, puerto);
        } catch (IOException e) {

            System.out.println("Ocurrio un error al Iniciar el Socket: " + e);
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
