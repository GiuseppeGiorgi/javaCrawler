package javaCrawler;

import java.util.HashSet;
import java.util.Set;



/**
 * 
 * @author Giuseppe
 *
 *Singleton : Bill Pugh technique
 */
public class UrlVisited {
	
	private Set<String> linkVisited = new HashSet<String>();
	
	private UrlVisited(){
	}
	
	
	private static class Instance{
		private static final UrlVisited INSTANCE = new UrlVisited();
	}


	public static UrlVisited getInstance(){
		return UrlVisited.Instance.INSTANCE;
	}
	
	public void add(String val){
		this.linkVisited.add(val);
	}
	
	
	public Set<String> getLinkVisitati(){
		
		return this.linkVisited;
	}
	
	
	public boolean isVisited(String val) {
		
		if(this.linkVisited.contains(val)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public boolean isEmpty() {
		
		if(this.linkVisited.size()> 0 ){
			return false;
		}
		else{
			return true;
		}

	}
	
	

}
