package report;

import javaCrawler.Record;

public interface Log <T extends Record> {

	public void writeLog(T record);

}
