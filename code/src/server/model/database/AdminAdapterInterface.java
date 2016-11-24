package server.model.database;

import java.sql.ResultSet;

/**
 * Interface used to interact with the storage adapter from the administrator's
 * perspective. Extends the standard adapter interface to provide additional
 * methods
 * 
 * @see AdapterInterface
 */
public interface AdminAdapterInterface extends AdapterInterface
{
   public boolean addUser(String username, String employee_ID, int department,
         String password);

   public boolean removeUser(String employee_ID);

   public boolean addDepartament(int department_ID, String depName);

   public boolean removeDepartament(int departament_ID);

   public ResultSet viewUpdate(String updateofObject);

   public ResultSet checkDepartament(int depId);
}
