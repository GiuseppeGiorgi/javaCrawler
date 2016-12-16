package javaCrawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


/**
 * 
 * @author Giuseppe
 *
 *
 *Singleton : Bill Pugh technique
 */
public class ReadProperties {
	
	private static final String fileName = "config/parametri.properties";
	private final Properties configProperties = new Properties();
	
	private ReadProperties() {
		
		try{
			
			
			configProperties.load(new FileInputStream(fileName));

		}
		catch (NullPointerException n){
			System.out.print("The file not exist");
			
		}
		catch(IOException e){
			System.out.print("Generic error " + e );
		}
		
	}//end Constructor
	
	private static class Instance{
		private static final ReadProperties INSTANCE = new ReadProperties();
	}
	
	public static ReadProperties getInstance(){
		return Instance.INSTANCE;
	}
	
	public String getProperty(String key){

		return configProperties.getProperty(key);
	}
	
	
	public ArrayList<String> getTg(String tg ){

		ArrayList<String> tags = new ArrayList<String>();


		String st = new String(configProperties.getProperty(tg));
		
		
		if (st != null) {
			for (String splt : st.split(";")) {

				tags.add(splt);
			}
		}

		return tags;
	}
	

}
