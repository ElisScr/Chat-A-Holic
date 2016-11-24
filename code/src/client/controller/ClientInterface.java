package client.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import client.model.Chat;
/**
 * 
 * @author 
 *the interface for ClientController
 */
public interface ClientInterface extends Remote
{
   void sendMessage(String msg) throws RemoteException;

   void attachToServer() throws RemoteException;

   void dettachFromServer() throws RemoteException;

   void receiveMessage(Chat chat) throws RemoteException;

   void printUsernames() throws RemoteException;

   Connection databaseConnection() throws RemoteException;

   boolean checkPassword(String username, String password)
         throws RemoteException, SQLException;

   void getName(ArrayList<String> users) throws RemoteException;

}
