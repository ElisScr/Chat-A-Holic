package server.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import server.controller.ServerController;
/**
 * 
 * @author 
 *a panel to can se the information between server and client
 */
@SuppressWarnings("serial")
public class ServerView extends JFrame
{
	JTextArea area = new JTextArea( 20, 50 );
	
	private ServerController controller;
	
	public ServerView(ServerController controller2)
	{
		this.controller=controller2;
		
		area.setEditable(false);
		add( new JScrollPane( area ) );
		
		pack();
		setLocationRelativeTo( null );
		setVisible( true );
		
		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		addWindowListener(new FrameHandler());		
	}	
	
	public void println( String text )
	{
		area.append( text + "\n" );
		area.setCaretPosition( area.getText().length() );
	}
	
	private class FrameHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
			controller.closeServer();
		}
	}
}
