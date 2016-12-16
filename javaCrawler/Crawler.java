package javaCrawler;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





public class Crawler extends AbstractCrawler{
	
	
	private Url record;
	private String url;
	private String userAgent = Parameters.USER_AGENT;
	private int timeout = Parameters.TIMEOUT;
	private ArrayList<String> tag = new ArrayList<String>();
	private Boolean searchWord = false;

	
	public  Record search(Url record){



		this.record = record;
		this.url = this.record.getUrl();

		boolean statusConn = super.connection(this.url, userAgent, timeout);

		/**
		 * error connection
		 */
		if(!statusConn){
			buildRecord();
			return this.record;
		}


		if (Parameters.WORD != null){
			searchWord = searchWord(Parameters.WORD);
		}

		//list of tags to be searched
		ArrayList<String> tagL = Parameters.TAGLIST;

		//tags found
		for (String tg : tagL) {

			this.tag.add(String.valueOf(htmlDocument.select(tg).first()).replace("\n", " "));

		}

		this.buildRecord();

		/**
		 * list of urls to be scanned
		 */
		Elements linksOnPages = htmlDocument.body().select("a[abs:href*="+Parameters.URL_FOLLOW+"]");

		
		for (Element link : linksOnPages) {

			String linkSave = String.valueOf(link.attr("abs:href"));

			if(Visitable.getInstance().isVisitable(linkSave)){

				Record newRecord = new Url(linkSave, this.url);
				
				UrlToVisit.getInstance().setLinkToVisit(linkSave, newRecord);

			}
			
		}

		return this.record;

	}

	
	private void buildRecord(){
		
		this.record.setErrorMessage(super.errorMessage);
		this.record.setResponsCode(super.responseStatusCode);
		this.record.setResponseTime(super.responseTime);
		this.record.setTag(this.tag);
		this.record.setLogError(super.logError);
		this.record.setDate(super.date);
		this.record.setTimeStamp(super.timeStamp);
		this.record.setSearchWord(this.searchWord);
	}

	private boolean searchWord(String word){

		String bodyText = super.htmlDocument.body().text();

		return bodyText.toLowerCase().contains(word.toLowerCase());

	}


}
