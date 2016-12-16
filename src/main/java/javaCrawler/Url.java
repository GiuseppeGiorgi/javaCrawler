package javaCrawler;

import java.util.ArrayList;

public class Url extends Record {
	
	
		private String parent = "NULL";

	    private ArrayList<String> tag = new ArrayList<String>();
	    private int responsCode = 0;
	    private long responseTime = 0;
	    private String errorMessage = "NULL";
	    private boolean logError = false;
	    private String date;
	    private long timeStamp;
		private Boolean searchWord;
	    


	    public Url(String url){
	        super.setUrl(url);
	    }

	    public Url(String url, String parent){
	        super.setUrl(url);
	        this.parent = parent;
	    }


	    public void setParent(String parent) {
	        this.parent = parent;
	    }

	    public String getParent() {
	        return parent;
	    }

	    public void setResponsCode(int responsCode) {
	        this.responsCode = responsCode;
	    }

	    public int getResponsCode() {
	        return responsCode;
	    }

	    public void setResponseTime(long responseTime) {
	        this.responseTime = responseTime;
	    }

	    public long getResponseTime() {
	        return responseTime;
	    }

	    public void setTag(ArrayList<String> tag ) {
	        this.tag.addAll(tag);
	    }

	    public ArrayList<String> getTag() {
	        return tag;
	    }

	    public void setErrorMessage(String errorMessage) {
	        this.errorMessage = errorMessage;
	    }

	    public String getErrorMessage() {
	        return errorMessage;
	    }

	    public void setLogError(boolean logError) {
	        this.logError = logError;
	    }

	    public boolean isLogError() {
	        return logError;
	    }
	    
	    public void setDate(String date) {
			this.date = date;
		}
	    public String getDate() {
			return date;
		}
	    
	    public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}
	    public long getTimeStamp() {
			return timeStamp;
		}

		public Boolean getSearchWord() {
			return searchWord;
		}

		public void setSearchWord(Boolean searchWord) {
			this.searchWord = searchWord;
		}
	

}
