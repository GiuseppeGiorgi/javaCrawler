package report;

import javaCrawler.Record;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 */

public interface Log <T extends Record> {

	public void writeLog(T record);

}
