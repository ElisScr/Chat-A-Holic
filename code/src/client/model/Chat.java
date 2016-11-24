package client.model;

import java.io.Serializable;

/**
 * 
 * @author 
 *this class takes an username, a message and shows the time
 */
@SuppressWarnings("serial")
public class Chat implements Serializable
{
	private String name;
	private String chat;
	private TimeKeeper time;

	public Chat()
	{
	
	}
	
	public Chat(String name,String chat)
	{
		this.name=name;
		this.chat=chat;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getChat()
	{
		return chat;
	}
	
	public void setChat(String chat)
	{
		this.chat=chat;
	}

	@SuppressWarnings("static-access")
   public String toString()
	 {
	  return time.printTime()+ name + ": " + chat;
	 }

}
