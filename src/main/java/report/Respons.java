package report;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import javaCrawler.Url;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 *
 *
 *Singleton : Bill Pugh technique
 */
public class Respons extends AbstractLog{
	
	
	private Respons() {
		
	}
	
	
	private static class Instance{
		private static final Respons INSTANCE = new Respons();
	}


	public static Respons getInstance(){
		return Instance.INSTANCE;
	}
	
	
	
	@Override
	public void writeLog(Url record) {
		
		super.createDir();
		
		parseRecord(record);
		
		
		try {

			FileWriter fileLog = new FileWriter("log/response-"+super.getDate()+".csv", true);
			BufferedWriter log = new BufferedWriter(fileLog);
			log.write("[" + date + "]");
			log.write(DELIMITER);
			log.write(super.parent);
			log.write(DELIMITER);
			log.write(super.url);
			log.write(DELIMITER);
			log.write(String.valueOf(super.responsCode));
			log.write(DELIMITER);
			log.write(String.valueOf(super.responseTime));
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
		super.responsCode = record.getResponsCode();	
		super.responseTime = record.getResponseTime();
		super.date = record.getDate();
		super.timeStamp = record.getTimeStamp();


	}

}
