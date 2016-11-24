package client.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import server.controller.ServerController;
import server.controller.ServerInterface;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
/**
 * 
 * @author 
 *the administrator panel
 */
public class AdministratorView
{
   private JFrame frame;

   // text field tfEmployeeNameADD
   private JTextField tfEmployeeNameADD;
   private String EmployeeNameADD;

   // text field tfEmployee_ID_ADD
   private JTextField tfEmployee_ID_ADD;
   private String Employee_ID_ADD;

   // text field tfEmpDepIDADD
   private JTextField tfEmpDepIDADD;
   private int EmpDepIDADD;

   // text field tfPasswordADD
   private JTextField tfPasswordADD;
   private String PasswordADD;

   // text field tfEmployee_ID_Remove
   private JTextField tfEmployee_ID_Remove;
   private String Employee_ID_Remove;

   // text field tfDepId_Remove
   private JTextField tfDepId_Remove;
   private int DepId_Remove;

   // text field textDepNameAdd
   private JTextField textDepNameAdd;
   private String departamentNameADD;

   // text field tfDep_ID_ADD
   private JTextField tfDep_ID_ADD;
   private int departamentID_ADD;

   // db connection
   static Connection connection = null;
   // server
   static ServerInterface rmiServer;
   // table info
   private JTable table;

   // text field tfCheckDepartamentID
   private JTextField tfCheckDepartamentID;
   private int CheckDepartamentID;

   public void start() throws Exception
   {
      AdministratorView window = new AdministratorView();
      window.frame.setVisible(true);
      rmiServer = new ServerController();
      connection = rmiServer.databaseConnection();
   }

   public AdministratorView()
   {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize()
   {
      // jframe administrator
      frame = new JFrame("Administrator...");
      frame.setBounds(100, 100, 754, 682);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      // text Field tfEmployeeNameADD
      tfEmployeeNameADD = new JTextField();
      tfEmployeeNameADD.setBounds(34, 75, 86, 20);
      frame.getContentPane().add(tfEmployeeNameADD);
      tfEmployeeNameADD.setColumns(50);

      // text Field tfEmployee_ID_ADD
      tfEmployee_ID_ADD = new JTextField();
      tfEmployee_ID_ADD.setBounds(150, 75, 68, 20);
      frame.getContentPane().add(tfEmployee_ID_ADD);
      tfEmployee_ID_ADD.setColumns(10);

      // j label lblEmployee_NameADD
      JLabel lblEmployee_NameADD = new JLabel(" employee_Name");
      lblEmployee_NameADD.setBounds(34, 50, 131, 29);
      frame.getContentPane().add(lblEmployee_NameADD);

      // j label lblEmployeeidADD
      JLabel lblEmployeeidADD = new JLabel("employee_ID");
      lblEmployeeidADD.setBounds(150, 45, 105, 38);
      frame.getContentPane().add(lblEmployeeidADD);

      // j label lblEmployeedepartamentidADD
      JLabel lblEmployeedepartamentidADD = new JLabel("employee_departament_ID");
      lblEmployeedepartamentidADD.setBounds(284, 45, 145, 38);
      frame.getContentPane().add(lblEmployeedepartamentidADD);

      // text Field tfEmpDepIDADD
      tfEmpDepIDADD = new JTextField();
      tfEmpDepIDADD.setBounds(284, 75, 131, 20);
      frame.getContentPane().add(tfEmpDepIDADD);
      tfEmpDepIDADD.setColumns(10);

      // text Field tfPasswordADD
      tfPasswordADD = new JTextField();
      tfPasswordADD.setBounds(440, 75, 86, 20);
      frame.getContentPane().add(tfPasswordADD);
      tfPasswordADD.setColumns(10);

      // j label lblPasswordAdd
      JLabel lblPasswordAdd = new JLabel("password ");
      lblPasswordAdd.setBounds(442, 45, 94, 38);
      frame.getContentPane().add(lblPasswordAdd);

      // button ADD USERS
      JButton btnAddUsers = new JButton("Add User");
      btnAddUsers.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            try
            {
               EmployeeNameADD = tfEmployeeNameADD.getText();
               Employee_ID_ADD = tfEmployee_ID_ADD.getText();
               EmpDepIDADD = Integer.parseInt(tfEmpDepIDADD.getText());
               PasswordADD = tfPasswordADD.getText();

               rmiServer.addUser(EmployeeNameADD, Employee_ID_ADD, EmpDepIDADD,
                     PasswordADD);

               JOptionPane.showMessageDialog(null, "User: " + EmployeeNameADD
                     + " was added successfully ");

            }
            catch (Exception e)
            {
               JOptionPane.showMessageDialog(null, e);
            }
         }
      });
      btnAddUsers.setBounds(578, 75, 118, 23);
      frame.getContentPane().add(btnAddUsers);

      // j label lblEmployeeidRemove
      JLabel lblEmployeeidRemove = new JLabel("employee_ID");
      lblEmployeeidRemove.setBounds(34, 140, 105, 38);
      frame.getContentPane().add(lblEmployeeidRemove);

      // text Field Employee_ID_Remove
      tfEmployee_ID_Remove = new JTextField();

      tfEmployee_ID_Remove.setColumns(10);
      tfEmployee_ID_Remove.setBounds(34, 170, 68, 20);
      frame.getContentPane().add(tfEmployee_ID_Remove);

      // button Remove USER
      JButton btnRemove = new JButton("Remove User");
      btnRemove.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent arg0)
         {
            Employee_ID_Remove = tfEmployee_ID_Remove.getText();
            try
            {
               rmiServer.removeUser(Employee_ID_Remove);
               // JOptionPane.showMessageDialog(null,
               // "User with employee_ID: "+Employee_ID_Remove+" was removed successfully ");
            }
            catch (RemoteException e)
            {
               JOptionPane.showMessageDialog(null, e);
            }
         }
      });
      btnRemove.setBounds(516, 157, 180, 23);
      frame.getContentPane().add(btnRemove);

      // j label lblAddUsers
      JLabel lblAddUsers = new JLabel("Add Users");
      lblAddUsers.setFont(new Font("Tahoma", Font.PLAIN, 20));
      lblAddUsers.setBounds(34, 14, 106, 43);
      frame.getContentPane().add(lblAddUsers);

      // j label labelRemoveUsers
      JLabel labelRemoveUsers = new JLabel("Remove Users");
      labelRemoveUsers.setFont(new Font("Tahoma", Font.PLAIN, 20));
      labelRemoveUsers.setBounds(34, 102, 184, 43);
      frame.getContentPane().add(labelRemoveUsers);

      // j label labelAddDepartaments
      JLabel labelAddDepartaments = new JLabel("Add Departaments");
      labelAddDepartaments.setFont(new Font("Tahoma", Font.PLAIN, 20));
      labelAddDepartaments.setBounds(34, 212, 191, 43);
      frame.getContentPane().add(labelAddDepartaments);

      // button ADD Department
      JButton btnAddDepartament = new JButton("Add Departament");
      btnAddDepartament.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {

            departamentNameADD = textDepNameAdd.getText();

            departamentID_ADD = Integer.parseInt(tfDep_ID_ADD.getText());
            try
            {
               rmiServer.addDepartament(departamentID_ADD, departamentNameADD);
               JOptionPane.showMessageDialog(null, "Departament : "
                     + departamentNameADD + " was added successfully ");

            }
            catch (RemoteException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
      btnAddDepartament.setBounds(516, 282, 180, 23);
      frame.getContentPane().add(btnAddDepartament);

      // j label lblRemoveDepartaments
      JLabel lblRemoveDepartaments = new JLabel("Remove Departaments");
      lblRemoveDepartaments.setFont(new Font("Tahoma", Font.PLAIN, 20));
      lblRemoveDepartaments.setBounds(34, 313, 246, 43);
      frame.getContentPane().add(lblRemoveDepartaments);

      // button REMOVE Departament
      JButton btnRemoveDep = new JButton("Remove Departament");
      btnRemoveDep.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {

            DepId_Remove = Integer.parseInt(tfDepId_Remove.getText());

            try
            {
               rmiServer.removeDepartament(DepId_Remove);
            }
            catch (Exception ex)
            {
               JOptionPane.showMessageDialog(null, ex);
            }
         }
      });
      btnRemoveDep.setBounds(516, 375, 180, 23);
      frame.getContentPane().add(btnRemoveDep);

      // text field tfDepId_Remove
      tfDepId_Remove = new JTextField();
      tfDepId_Remove.setColumns(10);
      tfDepId_Remove.setBounds(34, 376, 68, 20);
      frame.getContentPane().add(tfDepId_Remove);

      // j label labelDepID_Remove
      JLabel labelDepID_Remove = new JLabel("departament_ID");
      labelDepID_Remove.setBounds(34, 346, 105, 38);
      frame.getContentPane().add(labelDepID_Remove);

      // text field textDepNameAdd
      textDepNameAdd = new JTextField();
      textDepNameAdd.setColumns(50);
      textDepNameAdd.setBounds(34, 283, 86, 20);
      frame.getContentPane().add(textDepNameAdd);

      // j label labeldepartament_NameAdd
      JLabel labeldepartament_NameAdd = new JLabel("departament_Name");
      labeldepartament_NameAdd.setBounds(34, 258, 131, 29);
      frame.getContentPane().add(labeldepartament_NameAdd);

      // text field tfDep_ID_ADD
      tfDep_ID_ADD = new JTextField();
      tfDep_ID_ADD.setColumns(10);
      tfDep_ID_ADD.setBounds(150, 283, 68, 20);
      frame.getContentPane().add(tfDep_ID_ADD);

      // j label labeldepartament_IDADD
      JLabel labeldepartament_IDADD = new JLabel("departament_ID");
      labeldepartament_IDADD.setBounds(150, 365, 105, 38);
      frame.getContentPane().add(labeldepartament_IDADD);

      // button VIEW UPDATES Users
      JButton btnViewUpdateUsers = new JButton("View Update Users");
      btnViewUpdateUsers.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {

            try
            {
               String s = "employees";
               // DBUtils- is a class inside rs2xml.jar
               // sets the table to the database model
               table.setModel(DbUtils.resultSetToTableModel(rmiServer
                     .viewUpdate(s)));
            }
            catch (Exception ex)
            {
               ex.printStackTrace();
            }
         }
      });
      JScrollPane scrollPaneViewEmployeeUpdate = new JScrollPane();
      scrollPaneViewEmployeeUpdate.setBounds(34, 490, 662, 101);
      frame.getContentPane().add(scrollPaneViewEmployeeUpdate);

      table = new JTable();
      scrollPaneViewEmployeeUpdate.setViewportView(table);
      btnViewUpdateUsers.setBackground(Color.LIGHT_GRAY);
      btnViewUpdateUsers.setBounds(34, 446, 173, 23);
      frame.getContentPane().add(btnViewUpdateUsers);

      JButton btnViewUpdatesDepatments = new JButton("View Update Departments");
      btnViewUpdatesDepatments.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {

            String s = "departament";
            try
            {
               table.setModel(DbUtils.resultSetToTableModel(rmiServer
                     .viewUpdate(s)));
            }
            catch (RemoteException e1)
            {
               e1.printStackTrace();
            }
         }
      });
      btnViewUpdatesDepatments.setBackground(Color.LIGHT_GRAY);
      btnViewUpdatesDepatments.setBounds(217, 446, 197, 23);
      frame.getContentPane().add(btnViewUpdatesDepatments);

      JButton btnCheckThisDepartment = new JButton(
            "<--Check Employees in Dep...");
      btnCheckThisDepartment.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               CheckDepartamentID = Integer.parseInt(tfCheckDepartamentID
                     .getText());

               // DBUtils- is a class inside rs2xml.jar
               // sets the table to the database model
               table.setModel(DbUtils.resultSetToTableModel(rmiServer
                     .checkDepartament(CheckDepartamentID)));
            }
            catch (Exception ex)
            {
               JOptionPane.showMessageDialog(null,
                     "You have to enter a department number between 1-20!");
            }
         }
      });
      btnCheckThisDepartment.setBackground(Color.LIGHT_GRAY);
      btnCheckThisDepartment.setBounds(499, 446, 197, 23);
      frame.getContentPane().add(btnCheckThisDepartment);

      tfCheckDepartamentID = new JTextField();
      tfCheckDepartamentID.setBounds(440, 447, 45, 20);
      frame.getContentPane().add(tfCheckDepartamentID);
      tfCheckDepartamentID.setColumns(10);
   }
}
