package server.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.postgresql.Driver;

/**
 * Responsible for connecting to a SQL database
 */
public class Database 
{
	Driver driver = new Driver();
   private Connection connection;
   private String username;
   private String password;
   
	public Database(  String username,
			String password) throws ClassNotFoundException
	{		
		this.username = username;
		this.password = password;
		connection = null;
	}

	/**
	 * Establishes a connection to the database.
	 * @return 
	 * 
	 * @throws SQLException
	 */
	public Connection openDatabase() throws SQLException
	{
	  connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
	         username, password);       
		return connection;
	}
	/**
	 * Terminates the database connection.
	 * 
	 * @throws SQLException
	 */
	public void closeDatabase() throws SQLException
	{
		connection.close();
	}

	public ArrayList<Object[]> query(String sql) throws SQLException
	{
		openDatabase();
		PreparedStatement statement = null;
		ArrayList<Object[]> resultList = null;
		ResultSet resultSet = null;
		if (sql != null && statement == null)
		{
			statement = connection.prepareStatement(sql);
		}
		resultSet = statement.executeQuery();
		resultList = new ArrayList<Object[]>();
		while (resultSet.next())
		{
			Object[] resultRow = new Object[resultSet.getMetaData()
					.getColumnCount()];
			for (int i = 0; i < resultRow.length; i++)
			{
				resultRow[i] = resultSet.getObject(i + 1);
			}
			resultList.add(resultRow);
		}
		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();
		closeDatabase();
		return resultList;
	}

	/**
	 * Used to send update requests to the database.
	 * 
	 * @param sql
	 *            the SQL statement to be executed
	 * @return an int value representing the number of rows affected by the
	 *         query
	 * @throws SQLException
	 */
	public int update(String sql) throws SQLException
	{
		int results = 0;

		openDatabase();
		PreparedStatement statement = connection.prepareStatement(sql);
		if (sql != null && statement == null)
		{
			statement = connection.prepareStatement(sql);
			JOptionPane.showMessageDialog(null, "Updated!");
		}
		results = statement.executeUpdate();
		//closeDatabase();
		return results;
	}
	
}
