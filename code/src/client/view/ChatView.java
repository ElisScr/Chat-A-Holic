package client.view;

import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import client.controller.ClientController;
import server.controller.ServerController;
import server.controller.ServerInterface;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author 
 *the view for login panel
 */
@SuppressWarnings("serial")
public class ChatView extends JFrame
{
   private JFrame frame;
   static Connection connection = null;
   private JTextField tfUserName;
   private JPasswordField passwordField;
   private JLabel lbNetwork;
   private JLabel lblLoginIcon;
   private String userName;
   private String password;
   private ClientController client;
   static ServerInterface rmiServer;
   private boolean isLoginPressed = false;

   public void start() throws Exception 
   {
      ChatView window = new ChatView();
      window.frame.setVisible(true);
      rmiServer = new ServerController();
      connection = rmiServer.databaseConnection();
   }

   public ChatView() throws Exception
   {
      initialize();
   }

   public ClientController getClient()
   {
      return client;
   }

   public String getClientName()
   {
      return userName;
   }

   public String getPassword()
   {
      return password;
   }

   /**
    * Initialize the contents of the frame.
    */
   public void initialize()
   {
      frame = new JFrame("Chat-A-Holic");
      frame.setBounds(100, 100, 450, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JLabel lblUserName = new JLabel("User Name");
      lblUserName.setBounds(208, 51, 89, 51);
      frame.getContentPane().add(lblUserName);

      JLabel lbPassword = new JLabel("Password");
      lbPassword.setBounds(208, 122, 89, 27);
      frame.getContentPane().add(lbPassword);

      tfUserName = new JTextField();
      tfUserName.setBounds(279, 66, 86, 20);
      frame.getContentPane().add(tfUserName);
      tfUserName.setColumns(10);

      JButton btnLogin = new JButton("Login");
      // set the path of the 2nd image- Login
      lblLoginIcon = new JLabel("");
      Image img2 = new ImageIcon(this.getClass().getResource(
            "/img//Keys-icon.png")).getImage();
      // setting the icon to the button
      btnLogin.setIcon(new ImageIcon(img2));
      lblLoginIcon.setBounds(279, 202, 46, 14);
      frame.getContentPane().add(lblLoginIcon);

      btnLogin.addActionListener(new ActionListener()
      {
         @SuppressWarnings("deprecation")
         public void actionPerformed(ActionEvent arg0)
         {
            try
            {
               userName = tfUserName.getText();
               password = passwordField.getText();
               rmiServer.checkPassword(userName, password);
               isLoginPressed = true;

               // first close the frame
               frame.dispose();
               if (rmiServer.checkPassword(userName, password) == true)
               {
                  JOptionPane.showMessageDialog(null,
                        "Username and password is correct!");

                  if (rmiServer.checkAdministrator(userName, password) == true)

                  {
                     AdministratorView adminframe = new AdministratorView();
                     adminframe.start();
                  }
                  else
                  {
                     ClientController clientC = new ClientController(userName);
                     ClientView frameC = new ClientView(clientC);
                     clientC.begin(frameC);
                     frameC.setVisible(true);
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null,
                        "Username and password is not correct!");
               }
            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(null, e);
            }

         }

      });
      btnLogin.setBounds(276, 198, 89, 23);
      frame.getContentPane().add(btnLogin);

      passwordField = new JPasswordField();
      passwordField.setEchoChar('*');
      passwordField.setBounds(279, 125, 86, 20);
      frame.getContentPane().add(passwordField);

      // set the path of the 1st image- Network Image
      lbNetwork = new JLabel("");
      Image img = new ImageIcon(this.getClass().getResource(
            "/img//Network-1-icon.png")).getImage();
      // setting the icon
      lbNetwork.setIcon(new ImageIcon(img));
      lbNetwork.setBounds(0, 0, 191, 261);
      frame.getContentPane().add(lbNetwork);
   }

   public boolean isLoginPressed()
   {
      return isLoginPressed;
   }
}
