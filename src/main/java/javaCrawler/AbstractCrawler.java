package javaCrawler;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class AbstractCrawler {
	
	Document htmlDocument;
	long responseTime;
	int responseStatusCode;
	String errorMessage = "NULL";
	boolean logError = false;
	String date;
	long timeStamp;
	
	public boolean connection(String url, String userAgent, int timeout){
		
		
		Connection connection = Jsoup.connect(url).timeout(timeout).userAgent(userAgent).followRedirects(true);
		
		long start_time = System.currentTimeMillis();
		
		Date dT = new Date();
		SimpleDateFormat sD = new SimpleDateFormat("y-M-d:H:m:s"); 
		date = sD.format(dT);
		timeStamp = new Timestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())).getTime();
		
		
		try {
			htmlDocument = connection.get();
		}catch (java.net.SocketTimeoutException j){

			errorMessage = "Error Timeout";
			logError = true;
			return false;

		}catch (HttpStatusException h){

			errorMessage = "Error HTTP status code";
			responseStatusCode = h.getStatusCode();
			logError = true;
			return false;

		}catch (Exception e) {

			errorMessage = "Generic Error";
			logError = true;
			return false;
		}

		
		long end_time = System.currentTimeMillis();
		long total_time = end_time - start_time;

		responseStatusCode = connection.response().statusCode();
		//this.responsTime = TimeUnit.MILLISECONDS.toSeconds(total_time);
		responseTime = total_time;

		return true;
		
	}

}
