package client.controller;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import client.model.Chat;
import client.view.ClientView;
import client.view.ChatView;
import server.controller.ServerController;
import server.controller.ServerInterface;
/**
 * 
 * @author 
 * ClientController controls the flow between model and view, also between client and server
 *
 */
@SuppressWarnings("serial")
public class ClientController extends UnicastRemoteObject implements ClientInterface
{
   private ChatView frame1;
   private ServerInterface server;
   public static final String SERVICE = "chat";
   private String username;
   private String password;
   private Connection connection = null;
   private ClientView frame;
   private ArrayList<String> users;

   public ClientController(String username) throws RemoteException
   {
      super();
      this.username = username;
      System.out.println(username);

      try
      {
         server = new ServerController();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public ClientController() throws RemoteException
   {
      super();

      try
      {
         server = new ServerController();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
/**
 * getting the information from GUI part
 * 
 */
   public void login(ChatView frame12)
   {

      this.frame1 = frame12;

      try
      {
         server = new ServerController();
         System.out.println("Login Pannel");
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.out.println("test it!!!");
      }
   }

   public String getPassword()
   {
      this.password = frame1.getPassword();
      return password;
   }

   public String getUserName()
   {
      this.username = frame1.getClientName();
      return username;
   }
/**
 * using the server interface this ClientController is connected with the database to get all the information needed 
 * for checking the password
 */
   @Override
   public Connection databaseConnection() throws RemoteException
   {
      try
      {
         connection = server.databaseConnection();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      return connection;
   }

   public boolean checkPassword(String username, String password)
         throws RemoteException, SQLException
   {
      this.username = username;
      this.password = password;
      return server.checkPassword(this.username, this.password);
   }

   public void begin(ClientView frame)
   {
      this.frame = frame;

      try
      {
         String ip = InetAddress.getLocalHost().getHostAddress() + "/"
               + SERVICE;
         String URL = "rmi://" + ip;
         server = (ServerInterface) Naming.lookup(URL);
         attachToServer();
         System.out.println(users.toString());
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.out.println("test it!!!");
      }
   }

   @Override
   public void sendMessage(String msg)
   {
      try
      {
         Chat chat = new Chat();
         chat.setChat(msg);
         chat.setName(username);
         server.sendMessage(chat);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void printUsernames() throws RemoteException
   {
      System.out.println("getName() called");
      frame.clearUserArea();

      for (int i = 0; i < users.size(); i++)
      {
         frame.receiveUsers(users.get(i));
      }
   }

   @Override
   public void getName(ArrayList<String> users) throws RemoteException
   {
      this.users = users;
   }

   @Override
   public void attachToServer() throws RemoteException
   {
      server.attach(this);
      server.addUser(username);
      server.sendUsers();
   }

   @Override
   public void dettachFromServer() throws RemoteException
   {
      server.removeUserServer(username);
      server.sendUsers();
      server.detach(this);
      System.exit(1);
   }

   @Override
   public void receiveMessage(Chat chat) throws RemoteException
   {
      frame.receiveMessage(chat);
   }
}
