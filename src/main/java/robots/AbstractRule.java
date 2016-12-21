package robots;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 */

public abstract class AbstractRule implements Rule {

	private String path;
	private boolean wildcardsAllowed;
	
	
	
	public AbstractRule(String path, boolean wildcardsAllowed) {
		
		this.path = path;
		this.wildcardsAllowed = wildcardsAllowed;
				
	}
	
	public String getPath() {
		return this.path;
	}
	
	
	public abstract Boolean isAllowed(String path);
	
	@Override
	public String toString() {
		
		return getClass().getName()+ " on "+ this.path;
	}
	
	protected boolean match(String query) {
		
		if(!wildcardsAllowed || (path.indexOf("*")== -1 && path.indexOf("$")== -1)){
		
			return query.startsWith(path);
			
		}
		
		
		String regExp = path;
		regExp = regExp.replace("/", "\\/");
		regExp = regExp.replace("*", ".*");
		
		
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(query);
		boolean r = m.find();

		return r;
		
	}

}
