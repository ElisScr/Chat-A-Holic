package main;

import client.controller.ClientController;
import client.view.ChatView;
/**
 * 
 * @author 
 *starting the client
 */
public class ClientTest
{
	public static void main(String[] args)
	{
		try
		{			
		   ClientController client = new ClientController();     
         ChatView frame1= new ChatView();
         client.login(frame1);
         frame1.start();
         frame1.initialize(); 				
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}
	}
}
