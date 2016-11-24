package client.model;

import java.util.Date;
/**
 * 
 * @author 
 *have access to real time
 */
public class TimeKeeper
{

	@SuppressWarnings("deprecation")
	public static  String printTime()
	{
		Date date = new Date();
		return "<" + date.getHours() + ":" + date.getMinutes() + ":"
				+ date.getSeconds() + ">";

	}
}