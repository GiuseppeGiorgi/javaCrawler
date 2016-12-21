package javaCrawler;

import java.util.HashMap;
import java.util.Map;



/**
* @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
*
* Singleton : Bill Pugh technique
*/
public class UrlToVisit{
	
	private Map<String, Record> linkToVisit = new HashMap<String, Record>();
	
	
	private UrlToVisit(){
	}


	
	private static class Instance{
		private static final UrlToVisit INSTANCE = new UrlToVisit();
	}


	public static UrlToVisit getInstance(){
		return Instance.INSTANCE;
	}

	
	public void setLinkToVisit(String key, Record value) {
		this.linkToVisit.put(key, value);
	}
	
	public   Map<String, Record> getLinkToVisit() {
		return linkToVisit;
	}
	
	
	/**
	 * read and delete the first item on the map,
	 * 
	 * @return value of the first item
	 */
	public Record next(){

		
		Map.Entry<String, Record> next = linkToVisit.entrySet().iterator().next();
		
		String key = next.getKey();
		Record value = next.getValue();
		
		linkToVisit.remove(key);
		
		return value;
	}
	
	
	public boolean containsKey(String url){

		return linkToVisit.containsKey(url);

	}

	public boolean isEmpty(){
		
		return linkToVisit.isEmpty();
		
	}
	
	
}
