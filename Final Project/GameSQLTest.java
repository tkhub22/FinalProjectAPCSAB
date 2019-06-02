/*
purpose:

make a game that puts highscores into an sql server 

notes:

- FAILURE SO FAR YEEET


2/7: try to establish connection to sql server
*/
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.mysql.jdbc.Connection;

public class GameSQLTest
{
   //declare instant variable
   private static Connection con = null;
   
   //create method connecting to database using sql server
   public static void openConnection()
   {
      try {
         //DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
         Class.forName("com.mysql.jdbc.Driver");  
         con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "peridotSQL22");
         
         if(con != null)
            System.out.println("connected successfully!");
      }
      
      catch(Exception e){
         System.out.println("Error making connection !!!!\n not connected to SQL database");
      }
   }
   
   //function to return connection
   
   public static void main (String [] args)
   {
      openConnection();
   }
   
}
