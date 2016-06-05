/**
 * 
 */
package main;

import dao.UserRecord;
import managers.CSVManager;
import utils.DateHelper;
import utils.enums.Genre;

/**
 * @author ES
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long initialTime = DateHelper.getTime();
		long startTime = 0;
		int numberOfTimeSpans = 100;

		System.out.println("STARTING...");
		// CSV Manager
		CSVManager csvManager = new CSVManager("Results", "test");
		
		// Sample userRecord
		UserRecord userRecord = new UserRecord(DateHelper.getDate(), Genre.FEMALE.toString(), 30);
		
		// Create a certain number of time spans
		startTime = DateHelper.getTime();
		for (int i = 0; i < numberOfTimeSpans; ++i) {
			userRecord.addRecord(DateHelper.getTime() / 1000f, i / 10f, (i + 1) / 100f, (i + 2) / 100f);
		}
		System.out.println(String.format("Creating %s time spans in: %f seconds", numberOfTimeSpans,
				(DateHelper.getTime() - startTime) / 1000f));

		// Write to csv file
		startTime = DateHelper.getTime();
		csvManager.writeAsCSV(userRecord.toStringArray(), false);
		System.out.println("Writing records to CSV in: " + (DateHelper.getTime() - startTime) / 1000f + " seconds");
		// Total time:
		System.out.println("Total time: " + (DateHelper.getTime() - initialTime) / 1000f + " seconds");
	}

}
