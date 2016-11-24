package server.model.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Handles the system's requests to the database, from the administrator's
 * perspective. Contains a reference of {@link Database} to process those
 * requests and receive results. Implements {@link AdminAdapterInterface}
 */
public class AdminDBAdapter implements AdminAdapterInterface
{
   private Database db;
   private Connection connection = null;
   /**
    * Initializes the {@link Database} instance variable.
    * @throws SQLException 
    */
   public AdminDBAdapter() throws SQLException
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
   // check password admin
   public boolean checkPassword(String username, String password)
   {
      try
      {
         if (username.equals("admin"))
         {
            String sql = "SELECT password FROM chat.employees WHERE employee_ID ='"
                  + username + "'";
            ArrayList<Object[]> results = db.query(sql);
            if (results.size() != 1)           
               return false;                        
               return password.equals((results.get(0))[0].toString());          
         }
      }
      catch (SQLException e)
      {
      }
      return false;
   }
   @Override
   public boolean addUser(String username, String name, int department_ID,
         String password)
   {
      int results;
      String sql;
      try
      {
         sql = "INSERT INTO chat.employees VALUES ('" + username + "','" + name
               + "','" + department_ID + "','" + password + "')";
         results = db.update(sql);
         if (results == 0)
         {
            return false;
         }
      }
      catch (SQLException e)
      {
        JOptionPane.showMessageDialog(null, e);
      }
      return true;
      
   }
   public boolean addDepartament(int department_ID, String depName)
   {
      int results;
      String sql;
      try
      {
         sql = "INSERT INTO chat.departament VALUES ('" + department_ID + "','"
               + depName + "')";
         results = db.update(sql);
         if (results == 0)
         {
            return false;
         }
      }
      catch (SQLException e)
      {
        JOptionPane.showMessageDialog(null, e);
      }
      return true;
   }
   @Override
   public boolean removeUser(String userName)
   {
      int results;
      try
      {
         String sql = "DELETE FROM chat.employees WHERE employee_ID = '"
               + userName + "'";
         results = db.update(sql);
         if (results == 0)
          
            return false;
      }
      catch (Exception e)
      { 
        JOptionPane.showMessageDialog(null, e);
         return false;
      }
      JOptionPane.showMessageDialog(null,"User with employee_ID: "+ userName+" was removed successfully ");
      return true;
   }
   public boolean removeDepartament(int departament_ID)
   {
      int results;
      try
      {
         String sql = "DELETE FROM chat.employees WHERE employee_departament_ID = '"
                 + departament_ID + "';"+"DELETE FROM chat.departament WHERE departament_ID = '"
               + departament_ID + "';";
         results = db.update(sql);
         if (results == 0){   
            return false;
         }
        
      }
      catch (SQLException e)
      { 
      JOptionPane.showMessageDialog(null, e);
         return false;
      }
      JOptionPane.showMessageDialog(null, "Correct!");
      return true;
   }
   public ResultSet viewUpdate(String updateofObject)
   {
      if (updateofObject.equals("employees"))
         try
         {
            String query = "select * from chat.employees ";
            PreparedStatement preparedStatement = db.openDatabase()
                  .prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
         }
         catch (Exception e)
         {
           JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
         }
      else if (updateofObject.equals("departament"))
      {
         try
         {
            String query = "select * from chat.departament ";
            PreparedStatement preparedStatement = db.openDatabase()
                  .prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
         }
         catch (Exception e)
         {
          JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
         }
      }
      return null;
   }
   @Override
   public ResultSet checkDepartament(int depId)
   {
      String query = null;
      ResultSet resultSet = null;
      try
      {
         if (depId == 1)
         {
            query = "select * from chat.departament_executive ";
         }
         else if (depId == 2)
         {
            query = "select * from chat.departament_it ";
         }
         else if (depId == 3)
         {
            query = "select * from chat.departament_marketing ";
         }
         else if (depId == 4)
         {
            query = "select * from chat.departament_production ";
         }
         else
         {
            for (int i = 5; i <=20; i++)
            {
               if (depId == i)
                  query = "select * from chat.employees WHERE employee_departament_id="
                        + depId + "";
            }
         }
         PreparedStatement preparedStatement = db.openDatabase()
               .prepareStatement(query);
         resultSet = preparedStatement.executeQuery();
         return resultSet;
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, e);
      }
      return resultSet;
   }
   @Override
   public Connection openDatabase()
   {
      return connection;
   }
}