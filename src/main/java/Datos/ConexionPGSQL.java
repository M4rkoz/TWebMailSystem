
package Datos;

import java.sql.*;
import java.sql.DriverManager;

public class ConexionPGSQL {
    static String BD="jdbc:postgresql://www.tecnoweb.org.bo:5432/db_agenda";
    static String usuario="agenda";
    static String password="agendaagenda";
    
    
    public String getResultado(String patron){
       
        String resultado="";
        String linea="";
     try {
            Connection pgsql=DriverManager.getConnection(BD, usuario, password);
           Statement  st=pgsql.createStatement();
           String query="select * from persona where per_nom like '%"+patron+"%'OR per_appm like '%"+patron+"%' OR per_prof like '%"+patron+"%' OR per_email like '%"+patron+"%' OR per_dir like '%"+patron+"%' OR per_flug like '%"+patron+"%'";
           ResultSet rs=st.executeQuery(query);
            
            while(rs.next()){
                for (int i = 1; i <=14; i++) {                    
                 resultado=resultado+" | "+rs.getString(i).trim();          
                }
               
               linea=linea+resultado.trim()+"\n";
                
               resultado="";
                
            }
 
            
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
     return linea.trim();
        
    }
    
    
}
