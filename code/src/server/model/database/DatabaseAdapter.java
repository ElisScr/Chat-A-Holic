package server.model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Handles the system's requests to the database, from the user's perspective.
 * Contains a reference of {@link Database} to process those requests and
 * receive results. Implements {@link AdapterInterface}
 */
public class DatabaseAdapter implements AdapterInterface
{
   private Database db;
   private Connection connection = null;

   /**
    * Initializes the {@link Database} instance variable.
    * 
    * @throws SQLException
    */
   public DatabaseAdapter() throws SQLException
   {
      try
      {
         db = new Database("postgres", "111");
         connection = db.openDatabase();
      }
      catch (ClassNotFoundException e)
      {
      }
   }

   @Override
   public boolean checkPassword(String username, String password)
   {
      try
      {
         String sql = "SELECT password FROM chat.employees WHERE employee_id ='"
               + username + "'";

         ArrayList<Object[]> results = db.query(sql);
         if (results.size() != 1)
            return false;

         return password.equals((results.get(0))[0].toString());
      } catch (SQLException e)
      {
         return false;
      }
   }

   @Override
   public Connection openDatabase()
   {

      return connection;
   }

}
