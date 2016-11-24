package server.controller;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import server.view.ServerView;
/**
 * 
 * @author 
 *the Singleton DP for Server
 */
public class SingleServerObject 
{
   private static SingleServerObject instance ;
   private static final String SERVICE = "chat";
   ServerController controller;
   ServerView view=new ServerView(controller);
      //make the constructor private so that this class cannot be
      //instantiated
      private SingleServerObject()
      {
         instance=null;
      }
      
      //Get the only object available
      public static synchronized SingleServerObject getInstance()
      { 
         if (instance == null)
            instance = new SingleServerObject();
         return instance;
      }
    
      public void runMain()
      {
         try
         {
            LocateRegistry.createRegistry(1099);
            
          
            controller=new ServerController();
            controller.begin(view);
            
            Naming.rebind(SERVICE, controller);
         }catch (Exception e) {
            e.printStackTrace();
         }
      }
      }

