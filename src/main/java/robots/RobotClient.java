package robots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.prefs.BackingStoreException;



/**
 * check if a URL is allow or disallow
 * */
public class RobotClient extends RulesEngine {

	private String userAgent;
	private RulesEngine rules;
	private RulesEngine wildcardRules;
	private URL baseUrl;
	private boolean existRobots;
	private boolean wildcardsAllowed;
	
	
	/**
	 *
	 *@param userAgent 
	 * */
	
	public RobotClient(String userAgent){
		
		this.userAgent = userAgent;
		existRobots = false;
		wildcardsAllowed = false;
	}

	/**
	 * @param userAgent
	 * @param url
	 */
	public RobotClient(String userAgent, String url){

		try {

			this.baseUrl = new URL(url);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.userAgent = userAgent;
		existRobots = false;
		wildcardsAllowed = false;
		setWildcardsAllowed(true);

		try {

			parse(baseUrl);

		} catch (RobotsException e) {
			e.printStackTrace();
		}

	}
	
	
	public void setWildcardsAllowed(boolean wildcardsAllowed) {
		this.wildcardsAllowed = wildcardsAllowed;
	}
	
	public static URL getBaseUrl(URL baseUrl) {


		if(!"file".equals(baseUrl.getProtocol())){

			String strBaseUrl = rewriteBaseUrl(baseUrl.toExternalForm());
			if("".equals(strBaseUrl)){
				return null;
			}
			try{
				return new URL(strBaseUrl);
			}
			catch(MalformedURLException m){
				return null;
			}
			catch (Exception e) {
				return null;
			}
		}
		else{

			return baseUrl;
		}

	}
	
	public static String rewriteBaseUrl(String baseUrl){
		/**
		 *  url.getProtocol
		 *  @return -1  default port
		 **/
		
		try{
			URL url = new URL(baseUrl);
			String ret = url.getProtocol()+ "://" + url.getHost();
			
			if(url.getPort() != -1){
				
				ret += ":" + url.getPort();
				
			}
			
			
			ret += "/";
			return ret;
								
		}
		catch(Exception e){
			return e.getMessage();
		}
				
	}
	
	


	/**
	 * @param baseUrl
	 * @throws RobotsException
	 */
	
	public void parse(URL baseUrl) throws RobotsException{
		
		
		this.rules = new RulesEngine();
		
		URL txtUrl = null;
		
		try{
			
		
			/**
			 * builds baseurl + robot.txt
			 * 
			 **/
			
			txtUrl = new URL(baseUrl, "robots.txt");
	
		}
		catch(MalformedURLException m){
			 throw new RobotsException("Bad URL: "+baseUrl+", robots.txt. ", m);
		}
		
		
		String txt = null;
		try{
			
			
			txt = loadContent(txtUrl, this.userAgent);
			
			
			
			if(txt == null){
				throw new RobotsException("Error load content for this url: "+ txtUrl);
			}
						
		}
		catch(Exception e){
								
			throw new RobotsException("Error timeout robots.txt for this url: "+txtUrl, e);
		}
		
		try{
			parseText(baseUrl, txt);
		}
		catch(RobotsException e){
			
			throw new RobotsException("Problem while parsing "+txtUrl, e);
		}
		
		
	}
	
	
	
	
	//StringBuffered
	public void parseText(URL baseUrl, String txt)throws RobotsException{
		
		this.baseUrl = getBaseUrl(baseUrl);
		this.rules = parseTextForUserAgent(txt, this.userAgent);
		this.wildcardRules = parseTextForUserAgent(txt, "*");
		existRobots = true;
			
	}
	
	
	
	
	
	private RulesEngine parseTextForUserAgent(String txt, String userAgent) throws RobotsException{
		
		RulesEngine engine = new RulesEngine();
		
		BufferedReader rdr = new BufferedReader(new StringReader(txt));
		String line = "";
		String value = null;
		boolean parsingAllowBlock = false;

		try{
			

			
			while((line = rdr.readLine()) != null){
				//trim eventuali spazi
				line = line.trim();
				
				//trasformo la linea in lovercase(minuscolo)
				String lineToLowerCase = line.toLowerCase();
				
				//ignora i commenti, se l'inizio della stringa è #
				if(line.startsWith("#")){
					continue;
				}


				if(lineToLowerCase.startsWith("user-agent:")){   
					
					if(parsingAllowBlock){
						
						//qui abbiamo appena finito di leggere allow/disallow
						if(engine.isEmpty()){

							continue;
													
						}
						else{
							break;
						}
												
					}
					
					//substring(intero), quindi la lunghezza della stringa da togliere
					value = line.substring("User-agent:".length()).trim();
					
					if(value.equalsIgnoreCase(userAgent)){
						
						parsingAllowBlock = true;
						continue;
					}
				}//end userAgent
					else{
						
						if(parsingAllowBlock){
							if(lineToLowerCase.startsWith("allow:")){
								value = line.substring("Allow:".length()).trim();
								value = URLDecoder.decode(value, "UTF-8");
								engine.allowPath(value, wildcardsAllowed);
								
							}
							else if(lineToLowerCase.startsWith("disallow:")){
									value = line.substring("Disallow:".length()).trim();
									value = URLDecoder.decode(value, "UTF-8");
									engine.disallowPath(value, wildcardsAllowed);
							}
								else{
									//ignora
									continue;
								}
						}
						else{
							continue;
						}
							
					}//else
											
			}//while
		}//try
		catch(Exception e){
			throw new RobotsException("Problem while parsing text. ", e);
		}
		
		return engine;
		
	}
	
	

	public boolean isUrlAllowed(String u){

		URL url = null;
		try {

			 url = new URL(u);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		return isUrlAllowed(url);

	}


	
	/**
	 * Controlla se la url puo essere analizzata o scartata
	 * 
	 * @param "URL"
	 * @return se la url è allowed
	 **/
	
	public boolean isUrlAllowed(URL url) throws IllegalStateException, IllegalArgumentException{



		if(!existRobots){
			return true;
		}
		
		if(rules == null){
			throw new IllegalStateException("You must call parse before you call this method. ");
		}
		
		String hostBaseUrl = baseUrl.getHost();
		
		if(hostBaseUrl.indexOf(".") == hostBaseUrl.lastIndexOf(".")){
			hostBaseUrl = "www." + hostBaseUrl;
		}
		
		String hostUrl = url.getHost();
		if(hostUrl.indexOf(".") == hostUrl.lastIndexOf(".")){
			hostUrl = "www." + hostUrl;
		}
		if(!hostBaseUrl.equals(hostUrl) || baseUrl.getPort() != url.getPort() || !baseUrl.getProtocol().equals(url.getProtocol())){
			return true;
		}
				
		String urlStr;
		
		try{


			urlStr = url.toExternalForm().substring(this.baseUrl.toExternalForm().length()-1);

			if("/robots.txt".equals(urlStr)){
				return true;
			}
			
			urlStr = URLDecoder.decode(urlStr, "UTF-8");
			
		}
		catch(Exception e){
			return true;
			
		}
		
		Boolean allowed = this.rules.isAllowed(urlStr);


		if(allowed == null){
			allowed = this.wildcardRules.isAllowed(urlStr);
		}
		
		
				
		if(allowed == null){
			allowed = Boolean.TRUE;
			
		}


		return allowed.booleanValue();
	
	}
	

	
	//esegue tutte le connessioni al file
	
	private static String loadContent(URL url, String userAgent)throws IOException{
		
		//apre connessione a questa url
		URLConnection urlConn = url.openConnection();
		urlConn.setConnectTimeout(50000);
		urlConn.setReadTimeout(50000);
		if(urlConn instanceof HttpURLConnection){	
			if(userAgent != null){
				//aggiunge una richiesta di connessione dallo userAgent
				((HttpURLConnection)urlConn).addRequestProperty("User-Agent", userAgent);
			}
		}
		
		
		InputStream in = urlConn.getInputStream();
		BufferedReader rdr = new BufferedReader(new InputStreamReader(in));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		
		while((line = rdr.readLine()) != null){
			buffer.append(line);
			buffer.append("\n");
		}
		in.close();
		return buffer.toString();
	 			
	}
	

}//end class
