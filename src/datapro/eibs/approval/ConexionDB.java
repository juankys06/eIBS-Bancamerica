package datapro.eibs.approval;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
      
    public static Connection GetConnection()
    {
        Connection conexion=null;
      
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://192.168.10.6:1433;databaseName=bancamerica;";
   
            conexion= DriverManager.getConnection(url,"banco","juanky");
        }
        catch(ClassNotFoundException ex)
        {
            
           System.out.print("Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex)
        {
     
            System.out.print( "Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex)
        {
        
            System.out.print( "Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        finally
        {
            return conexion;
        }
    }
}