package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import javax.swing.*;
import client.controller.ClientController;
import client.model.Chat;
import java.awt.SystemColor;
import java.awt.FlowLayout;
/**
 * 
 * @author 
 * the chat for employee panel
 */
@SuppressWarnings("serial")
public class ClientView extends JFrame implements ActionListener
{

   private ClientController controller;
   private JTextArea messageArea;
   private JTextArea usersArea;
   private JTextField messageInput;
   private JButton send;

   public ClientView(ClientController controller) throws RemoteException
   {
      this.controller = controller;

      JPanel p = new JPanel(new BorderLayout());
      JPanel N = new JPanel();
      JPanel S = new JPanel();
      JPanel C = new JPanel();
      JPanel E = new JPanel();

      p.add(N, BorderLayout.NORTH);
      p.add(S, BorderLayout.SOUTH);
      p.add(C, BorderLayout.CENTER);
      p.add(E, BorderLayout.EAST);
      N.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      // N panel.....
      JLabel l = new JLabel("Chat_Flow", JLabel.CENTER);
      l.setFont(new Font("Calisto MT", Font.BOLD, 24));
      N.add(l);
      N.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      // C panel.....
      messageArea = new JTextArea(30, 30);
      messageArea.setEditable(false);
      C.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      C.add(messageArea);

      // E panel
      usersArea = new JTextArea(30, 20);
      usersArea.setBackground(SystemColor.control);
      usersArea.setEditable(false);
      E.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      E.add(usersArea);

      // S panel..
      messageInput = new JTextField(30);
      messageInput.addKeyListener(new MovementAdapter());
      send = new JButton("Send");
      send.addActionListener(this);

      S.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      S.add(messageInput);
      S.add(send);

      getContentPane().add(p);
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      addWindowListener(new FrameHandler());
      setVisible(true);
      pack();
   }

   class MovementAdapter extends KeyAdapter
   {
      /**
       * Handle for the KeyListener interface key control for movement
       */
      public void keyPressed(KeyEvent ke)
      {
         int kc = ke.getKeyCode();
         if (kc == KeyEvent.VK_ENTER)
         {
            sendMessage();
         }
      }
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == send)
      {
         sendMessage();
      }
   }

   public void sendMessage()
   {
      String s = "";
      s = messageInput.getText();
      messageInput.selectAll();
      controller.sendMessage(s);
      messageInput.setText("");
   }

   public void receiveMessage(Chat message)
   {
      messageArea.append(message.toString() + "\n");
   }

   public void receiveUsers(String name)
   {
      usersArea.append(name + "\n");
   }

   public void clearUserArea()
   {
      usersArea.setText("");
   }

   private class FrameHandler extends WindowAdapter
   {

      public void windowClosing(WindowEvent event)
      {
         try
         {
            controller.dettachFromServer();
         }
         catch (RemoteException e)
         {
            e.printStackTrace();
            System.out.println("Not working!");
         }
      }
   }
}
