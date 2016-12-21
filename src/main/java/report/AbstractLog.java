package report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javaCrawler.Parameters;
import javaCrawler.Url;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 */

public abstract class AbstractLog implements Log<Url> {
	
	public String parent;
	public String url;
	public ArrayList<String> tag;
	public int responsCode;
	public long responseTime;
	public String errorMessage;
	public String date;
	public long timeStamp;
	public Boolean SearchWord;
	
	public static final String DELIMITER = Parameters.FILE_DELIMITER;
	public static final String NEW_LINE = Parameters.FILE_NEW_LINE;
	
	
	public abstract void  writeLog(Url record);
	
	public void createDir(){
		
		File logDir = new File("log");

		if(!logDir.exists()){
			logDir.mkdirs();
		}
		
	}
	
	public String getDate(){
		Date dT = new Date();
		SimpleDateFormat sD = new SimpleDateFormat("y-M-d"); 
		return sD.format(dT);
	}

}
