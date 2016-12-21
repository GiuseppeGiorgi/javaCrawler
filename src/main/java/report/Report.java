package report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javaCrawler.Url;


/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 *
 *Singleton : Bill Pugh technique
 */
public class Report extends AbstractLog {

	private Report() {
		
	}
	
	
	private static class Instance{
		private static final Report INSTANCE = new Report();
	}


	public static Report getInstance(){
		return Instance.INSTANCE;
	}
	
	
	@Override
	public void writeLog(Url record) {
		
		super.createDir();
		
		parseRecord(record);
		
		
		try {

			FileWriter fileLog = new FileWriter("log/report-"+super.getDate()+".csv", true);
			BufferedWriter log = new BufferedWriter(fileLog);
			log.write("[" + super.date + "]");
			log.write(DELIMITER);
			log.write("[" + super.timeStamp + "]");
			log.write(DELIMITER);
			log.write(super.parent);
			log.write(DELIMITER);
			log.write(super.url);

			for (String t : super.tag) {
				log.write(DELIMITER);
				log.write(t);
			}
			log.write(DELIMITER);
			log.write("Key trovata = "+super.SearchWord);

			log.write(NEW_LINE);
			log.close();

		} catch (IOException e) {
			System.out.println("Errore nella composizione del file per questa URL" + super.url);
			System.exit(0);
		}

		
	}

	
	private void parseRecord(Url record){

		super.parent = record.getParent();
		super.url = record.getUrl();
		super.tag = record.getTag();
		super.date = record.getDate();
		super.timeStamp = record.getTimeStamp();
		super.SearchWord = record.getSearchWord();

	}
}
