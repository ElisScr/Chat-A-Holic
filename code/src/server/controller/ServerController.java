package server.controller;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import server.model.database.AdapterInterface;
import server.model.database.AdminAdapterInterface;
import server.model.database.AdminDBAdapter;
import server.model.database.DatabaseAdapter;
import server.view.ServerView;
import client.controller.ClientInterface;
import client.model.Chat;
/**
 * 
 * @author 
 *the ServerController controls the information from database, ClientController and view part
 */
@SuppressWarnings("serial")
public class ServerController extends UnicastRemoteObject implements
      ServerInterface
{
   ServerView view;
   ArrayList<ClientInterface> observers = new ArrayList<ClientInterface>();
   ArrayList<String> users = new ArrayList<String>();
   private Connection connection1 = null;
   AdapterInterface db = null;
   AdminAdapterInterface admin = new AdminDBAdapter();

   public ServerController() throws Exception
   {
      super();
   }

   // /server panel
   // //////////////////////////////////
   public void begin(ServerView view) throws Exception
   {
      this.view = view;

      view.println("Server listening on:"
            + InetAddress.getLocalHost().getHostAddress());
   }

   @Override
   public synchronized void attach(ClientInterface obs) throws RemoteException
   {
      observers.add(obs);

   }

   @Override
   public synchronized void detach(ClientInterface obs) throws RemoteException
   {

      observers.remove(obs);
      view.println("dettached");
   }

   @Override
   public synchronized void sendMessage(Chat message) throws RemoteException
   {
      for (ClientInterface o : observers)
      {
         o.receiveMessage(message);
      }
      try
      {
         view.println("server sending the message {" + message.toString() + "}");

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public synchronized void sendUsers() throws RemoteException
   {

      view.println("yes");
      for (ClientInterface o : observers)
      {
         try
         {
            o.getName(users);
            o.printUsernames();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }

   }

   public void addUser(String user) throws RemoteException
   {
      users.add(user);
      view.println("IN: " + user);
   }

   public void removeUserServer(String user)
   {
      users.remove(user);
      view.println("OUT:" + user);
   }

   public void closeServer()
   {
      System.exit(1);
   }

   // //////////////////Login panel
   // /////////////////////////////////////////////////////////
   public Connection databaseConnection() throws RemoteException, SQLException
   {
      db = new DatabaseAdapter();
      connection1 = db.openDatabase();

      // view.println("connected to database");
      return connection1;
   }

   public boolean checkPassword(String username, String password)
         throws RemoteException, SQLException
   {

      
      return   db.checkPassword(username, password);
     
   }

   // ////////////////ADMIN
   // /////////////////////////////////////////////////////////////////

   public boolean checkAdministrator(String userName, String password)
         throws RemoteException
   {

      try
      {
         return admin.checkPassword(userName, password);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      return false;
   }

   public boolean addUser(String username, String employee_ID, int department,
         String password) throws RemoteException
   {
      return admin.addUser(username, employee_ID, department, password);
   }

   public boolean removeUser(String employee_ID) throws RemoteException
   {
      return admin.removeUser(employee_ID);
   }

   public boolean addDepartament(int depID, String departmentName)
         throws RemoteException
   {
      return admin.addDepartament(depID, departmentName);
   }

   public boolean removeDepartament(int departament_ID) throws RemoteException
   {
      return admin.removeDepartament(departament_ID);
   }

   // to see all the employees from that department

   public ResultSet checkDepartament(int depId) throws RemoteException
   {
      ResultSet rs = null;
      try
      {
         rs = admin.checkDepartament(depId);
         return rs;
      }
      catch (Exception ex1)
      {

      }
      return rs;
   }

   @Override
   public ResultSet viewUpdate(String updateofObject) throws RemoteException
   {
      return admin.viewUpdate(updateofObject);
   }

}
