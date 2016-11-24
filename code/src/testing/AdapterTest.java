package testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import server.model.database.AdapterInterface;
import server.model.database.DatabaseAdapter;

public class AdapterTest
{

   @Test
   public void test() throws SQLException
   {
      AdapterInterface adapter = new DatabaseAdapter();

      // To check the existing values in the database, check the Database

      // Checking the password should only return true if the
      // username/password combination exists in the database.

      // Wrong username and password
      assertEquals(false, adapter.checkPassword("password", "password"));
      // Wrong username
      assertEquals(false, adapter.checkPassword("blahblah", "password"));
      // Wrong password
      assertEquals(false, adapter.checkPassword("JoDog", "wrong"));
      // Correct login
      assertEquals(true, adapter.checkPassword("JoDog", "password"));
      
      System.out.println("Checking the Adapter DP");

   }

}
