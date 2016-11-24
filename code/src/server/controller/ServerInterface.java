package server.controller;


import java.rmi.Remote;  
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import client.controller.ClientInterface;
import client.model.Chat;
/**
 * 
 * @author 
 *interface for ServerController
 */
public interface ServerInterface extends Remote
{
	 public void attach(ClientInterface obs) throws RemoteException;
	
	 public void detach(ClientInterface obs) throws RemoteException;

	 public void sendMessage(Chat chat) throws RemoteException;
	
	 public void sendUsers() throws RemoteException;
	
	 public void addUser(String user) throws RemoteException;
	
	 public void removeUserServer(String user) throws RemoteException;
	 
	 public Connection databaseConnection() throws RemoteException, SQLException;
	 
	 public boolean checkPassword(String username, String password) throws RemoteException, SQLException;
		 
	 public boolean addUser(String username, String employee_ID, int department,String password)throws RemoteException;
	 
	 public boolean checkAdministrator(String userName, String password)throws RemoteException;
	 
	 public boolean removeUser(String employee_ID)throws RemoteException;
	 
	 public boolean addDepartament(int depID, String departmentName)throws RemoteException;
	 
	 public boolean removeDepartament(int departament_ID)throws RemoteException;
	 
	 public ResultSet viewUpdate(String updateofObject)throws RemoteException;
	 
	 public ResultSet checkDepartament(int depId)throws RemoteException;
}
