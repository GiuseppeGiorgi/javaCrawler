package javaCrawler;

import java.util.ArrayList;

/**
* @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
*/

public interface Parameters {
	
	/**********************************parameters on the file .properties*******************/
	public String URL = ReadProperties.getInstance().getProperty("url");
	public String URL_FOLLOW = ReadProperties.getInstance().getProperty("followUrl");
	public int TIMEOUT = Integer.parseInt(ReadProperties.getInstance().getProperty("timeout"));
	public String USER_AGENT = ReadProperties.getInstance().getProperty("useragent");
	public String USER_AGENT_ROBOTS = ReadProperties.getInstance().getProperty("useragent-robots");
	public String LOCAL_ROBOTS = ReadProperties.getInstance().getProperty("local-robots");
	public ArrayList<String> TAGLIST = ReadProperties.getInstance().getTg("tag");
	public String WORD = ReadProperties.getInstance().getProperty("word");
	
	/**************************************************DELIMITER FILE*********************************/
	public String FILE_DELIMITER = ";";
	public String FILE_NEW_LINE = "\n";

}
