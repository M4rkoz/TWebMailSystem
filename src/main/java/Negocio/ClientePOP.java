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

    public BufferedReader entradaPop(Socket popSocket) {
        try {

            return new BufferedReader(new InputStreamReader(popSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error En el Metodo EntradaPop(): " + e);
            return null;
        }

    }

    public DataOutputStream salidaPop(Socket popSocket) {
        try {

            return new DataOutputStream(popSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error En el Metodo salidadPop(): " + e);
            return null;
        }

    }

    public void iniciarSesionPop(Socket popSocket, BufferedReader entradaPop, DataOutputStream salidaPop) {

        try {

           
               System.out.println("S : " + entradaPop.readLine() + "\r\n");
               
               comando = "USER " + usuario + "\r\n";
                System.out.print("C : " + comando);
                salidaPop.writeBytes(comando);
                System.out.println("S : " + entradaPop.readLine() + "\r\n");

                comando = "PASS " + contrasena + "\r\n";
                System.out.print("C : " + comando);
                salidaPop.writeBytes(comando);
                System.out.println("S : " + entradaPop.readLine() + "\r\n");

            
        } catch (IOException e) {
            System.out.println("Se producjo un error en el Metodo iniciarSesionPop: " + e);
        }

    }

    public void cerrarSesionPop(Socket popSocket, BufferedReader entradaPop, DataOutputStream salidaPop) {

        try {

            comando = "QUIT\r\n";
            salidaPop.writeBytes(comando);

            // Cerramos los flujos de salida y de entrada y el socket cliente
            salidaPop.close();
            entradaPop.close();
            popSocket.close();

        } catch (IOException e) {
            System.out.println("Se producjo un error en el Metodo CerrarSesionPop: " + e);
        }

    }

    public String getPatron(int nroemail, Socket popSocket, BufferedReader entradaPop, DataOutputStream salidaPop) {
        String patron = "";
        /*  try {
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
         */

        try {
            comando = "RETR " + nroemail + "\n";
            salidaPop.writeBytes(comando);
            patron = obtenerPatron(entradaPop);

        } catch (IOException e) {
            System.out.println("Se produjo un error en el metodo getPatron: " + e);
        }
//                comando = "QUIT\r\n";
//        
//                salida.writeBytes(comando);

        // Cerramos los flujos de salida y de entrada y el socket cliente
        /*       salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }                     */
        return patron;
    }

    public Socket openPopConnection() {
        Socket socket = null;
        try {
            socket = new Socket(servidor, puerto);
        } catch (IOException e) {

            System.out.println("Ocurrio un error al Iniciar el Socket: " + e);
        }

        return socket;
    }

    public void closeConnection() {

    }

    public int cantidadMensajes(Socket popSocket, BufferedReader entradaPop, DataOutputStream salidaPop) {
        int nroMensajes = 0;
        try {

            comando = "LIST\n";
            System.out.print("C : " + comando);
            salidaPop.writeBytes(comando);
            nroMensajes = Integer.parseInt(patronNroMensajes(entradaPop));
            System.out.println("S : " + getMultiline(entradaPop) + "\r\n");

        } catch (IOException e) {
            System.out.println("Se produjo un Error en el Metodo cantidadMensajes: " + e);
        }

        return nroMensajes;
    }

    public String getEmail(int nroemail,Socket popSocket,BufferedReader entradaPop, DataOutputStream salidaPop ) {
        String email = "";
        try {
        
                
                comando = "RETR " + nroemail + "\n";
                salidaPop.writeBytes(comando);
            //    System.out.print("C : " + comando);
               email = patronEmail(entradaPop);
   //Esta linea es importante, es para que que multilinea lea correro completo,  asi para en la siguiente lectura no salte 2 campos adelante del siguiente email
               getMultiline(entradaPop);
 
   
               
   
                
           
              
                
        } catch (IOException e) {
            System.out.println("Se produjo un Error en el Metodo getEmtail(...): "+e);
        }

        return email;
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
                patron = patron.replace("messages:", "");
                patron = patron.trim();

                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return patron;

    }
    //Nos permite Obtener la Cantidad de Mensajes

    public String patronEmail(BufferedReader in) throws IOException {
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

            if (line.contains("Return-Path: ")) {
                //SI ESTAMOS EN LA LINEA DEL SUBJECT EXTRAEMOS EL PATRON Y ROMPEMOS EL CICLO
                patron = line.substring(12);
                patron = patron.replace("<", "");
                patron = patron.replace(">", "");
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
