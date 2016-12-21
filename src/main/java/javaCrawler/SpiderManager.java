package javaCrawler;

import report.Error;
import report.Report;
import report.Respons;

/**
* @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
*/

public class SpiderManager {
	
	public SpiderManager(String url){
		
		UrlToVisit.getInstance().setLinkToVisit(url, new Url(url));

		this.spider();
		
	}
	
	
	private void spider(){

		while(!UrlToVisit.getInstance().isEmpty() ){

			Crawler crawler = new Crawler();

			Url analyzedRecord = (Url) UrlToVisit.getInstance().next();

			UrlVisited.getInstance().add(analyzedRecord.getUrl());
			

			Url currentRecord = (Url) crawler.search(analyzedRecord);
 
			logManager(currentRecord);
		}//end while
		
	}
	
	private void logManager(Url record){
		
		if(record.isLogError()){
			Error.getInstance().writeLog(record);
		}
		else{
			Report.getInstance().writeLog(record);
			Respons.getInstance().writeLog(record);
		}
		
		
	}

}
