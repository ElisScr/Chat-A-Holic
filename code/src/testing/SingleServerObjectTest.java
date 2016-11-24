package testing;

import server.controller.SingleServerObject;

import static org.junit.Assert.*;
import org.junit.Test;

public class SingleServerObjectTest
{
  //testing the Singleton Design Pattern

      @Test
      public void testSingleton()
      {
         //we create 2 instance of the same server and we check if are the same
         
         SingleServerObject instance1 = SingleServerObject.getInstance();
       
         SingleServerObject instance2 = SingleServerObject.getInstance();
       
                 System.out.println("checking singleton objects equality");
        
                 assertEquals(true, instance1==instance2);

      }
   }


