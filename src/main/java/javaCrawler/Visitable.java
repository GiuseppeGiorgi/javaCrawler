package javaCrawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import robots.RobotClient;


/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 *
 *
 * crea un unica istanza di robots per ogni sotto dominio e la salva in una map
 * questo serve per non creare istanze con lo stesso robots
 * quando arriva una url con un dominio il cui robots è gia stato parserizzato
 * prende direttamente quelle regole senza parserizzare ogni volta il robots
 * 
 *
 *Singleton : Bill Pugh technique
 */
public class Visitable {

	private static Map<String, RobotClient> rb = new HashMap<String, RobotClient>();
	
		private Visitable(){
	    	
	    }
	
	  	private static class Instance{
	    	private static final Visitable INSTANCE = new Visitable();
	    }
	    
	    public static Visitable getInstance(){
	    	return Instance.INSTANCE;
	    }
	    

	    public boolean isVisitable(String url){

	        url = url.trim();

	        if(isUrlToVisited(url)){
	            return false;
	        }

	        else if(isUrlVisited(url)){
	            return false;

	        }
	        
	        else if(Parameters.LOCAL_ROBOTS != null){
	        	
	        	RobotClient robots = getInstanceRobotsLocal(Parameters.LOCAL_ROBOTS);
	        	return robots.isUrlAllowed(url);
	        }
	        
	        else{
	        	
	            RobotClient robots = getInstanceRobots(url);

	            return robots.isUrlAllowed(url);
	        }

	    }//end isVisitable
	    
	    private boolean isUrlToVisited(String url){

	        return UrlToVisit.getInstance().containsKey(url);

	    }
	    
	    private boolean isUrlVisited(String url){

	        return UrlVisited.getInstance().isVisited(url);

	    }
	    
	    
	    
	    /**
	     * il robots locale ha la priorita, se esiste viene usato questo
	     */
	    private RobotClient getInstanceRobotsLocal(String url){
	    	
	    	
	    	/**
	    	 * metodo che gestisce il robots.txt esterno al dominio (file locale o su un altro dominio)
	    	 */
	    	 if(rb.containsKey(url)){   	
		        	
		            return rb.get(url);
		        }
		        else {
		        			        	
		            //istanza di robots che contiene le regole
		            rb.put(url, new RobotClient(Parameters.USER_AGENT_ROBOTS, url));         

		            return rb.get(url);

		        }

	    }
	    
	    /**
	     * questo metodo viene usato quando non esiste un robots locale
	     * @param url
	     * @return
	     */
	    private RobotClient getInstanceRobots(String url){
	    		    	
	        
	        URL ur = null;

	        try {

	            ur = new URL(url);

	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }



	        String host = ur.getHost();
	        String protocol = ur.getProtocol();

	        String baseUrl = protocol+"://"+host;
	        
	        if(rb.containsKey(baseUrl)){   	
	        	
	            return rb.get(baseUrl);
	        }
	        else {
	        	
	        	
	            //istanza di robots che contiene le regole
	            rb.put(baseUrl, new RobotClient(Parameters.USER_AGENT_ROBOTS, baseUrl));         

	            return rb.get(baseUrl);


	        }

	    }
	    
	    
	    
	    
}
