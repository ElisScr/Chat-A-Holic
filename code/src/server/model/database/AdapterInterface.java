package server.model.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface used to interact with the data storage adapter from the user's
 * perspective.
 */
public interface AdapterInterface
{
   public boolean checkPassword(String username, String password)
         throws SQLException;
   public Connection openDatabase();

}
