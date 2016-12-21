package javaCrawler;

/**
* @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
*/

public abstract class Record {
	
	/**
	 * Generic
	 */
	private String url = "NULL";
	
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}

}
