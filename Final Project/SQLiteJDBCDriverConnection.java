
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*; 
import java.util.*;

public class SQLiteJDBCDriverConnection 
{
  
   public static Connection connect() 
   {
      Connection conn = null;
      try {
         // db parameters
         String url = "jdbc:sqlite:game.db";
         // create a connection to the database
         conn = DriverManager.getConnection(url);
         /*
         Statement s = conn.createStatement();
         s.execute("CREATE TABLE test (name VARCHAR(20))"); //use this to make edits sql stuff to "game" file
         */
         System.out.println("Connection to SQLite has been established.\n");
         return conn;
         
      }
      catch (SQLException e)
      {
         System.out.println(e.getMessage());
      } /*finally {
         try {
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
      }*/
      return null;
   }

   public static void add(String ss, int sco)
   {
      try{
         Connection conn = connect();
         Statement s = conn.createStatement();
         s.execute("INSERT INTO snake (username, sco) VALUES ('"+ ss +"', '"+ /*Integer.parseInt(*/sco/*) */+"')"); //use this to make edits sql stuff to "game" file
      }
      catch (SQLException e) 
      {
         System.out.println(e.getMessage());
      }
   }

   public static String selectAll()
   {
      String sql = "SELECT * FROM snake";
      String st = "";
        
      try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))
      {
            
            // loop through the result set
         while (rs.next()) 
         {
            st += "\n" + rs.getString("username") + "       " + rs.getString("sco");
         }
      } 
      catch (SQLException e)
      {
         System.out.println(e.getMessage());
      }
      return st;
   }
   
   public static String filter ()
   {
      
      String sql = "SELECT * FROM snake ORDER BY sco DESC LIMIT 5";
      String s = "";
      try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
         // pstmt.setInt(1,sco);
            //
         ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
         while (rs.next()) {
            s+= rs.getString("username") +  "\t" + 
                                   rs.getInt("sco") + "\n";
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return s;
   }
    
    
   public static void main(String[] args)
   {
   
   /*
      //drop and add table and stuff
      
      try{
            Connection conn = connect();
             Statement st = conn.createStatement();
             st.execute("CREATE TABLE snake (username VARCHAR(500), sco INT)");
          }
          catch(Exception e)
          {
          System.out.println(e);
          }
      
      */
  
  //*******************************************************************************    
      
      /*
      //to delete the records
      try{
         
            Connection conn = connect();
             Statement st = conn.createStatement();
             st.execute("DELETE FROM snake");
      }
      catch(Exception e)
      {
         System.out.println(e);
      }
      */
      
   //*******************************************************************************       
      
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter username to put into SQL database ");
      String s =  sc.nextLine();
      //add a while game is active thing here to continue adding scores/names
      System.out.println("enter score");
      int sco = sc.nextInt();
      add(s,sco);
      
      System.out.println();
      System.out.println(selectAll());//printing values in database
      
      System.out.println();
      System.out.println(filter());//top 5
   
      
   }
}