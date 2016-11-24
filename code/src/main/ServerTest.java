package main;

import server.controller.SingleServerObject;
/**
 * 
 * @author 
 *starting the server
 */
public class ServerTest
{
   public static void main(String[] args)
   {
      SingleServerObject object = SingleServerObject.getInstance();
      object.runMain();
   }
}
