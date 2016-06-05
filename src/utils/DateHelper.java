/**
 * 
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author ES
 *
 */
public class DateHelper {
	
	public static final String DATE_YMD_FORMAT = "yyyy-MM-dd";
	private static Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("America/Montreal"));
	/**
	 * Singleton class
	 */
	private DateHelper () { }
	
	/**
	 * the date string formatted as yyyy-MM-dd
	 * @return
	 */
	public static String getDate() {
		return formatDateToYMD(cal.getTime());
	}
	
	/**
	 * 
	 * @return
	 */
	public static long getTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Format date to yyyy-MM-dd 
	 * e.g., 20160215 for February 15, 2016
	 */
	public static String formatDateToYMD (Date date) {
		return new SimpleDateFormat(DATE_YMD_FORMAT).format(date);
	}
	
	/**
	 * Reconstructs the Date using the given string based on the default date format 
	 */
	public static Date getDateFromString(String dateString) {
		try {
			return new SimpleDateFormat(DATE_YMD_FORMAT).parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException (dateString, e);
		}
	}
	
	/**
	 * Reconstructs the Date using the given string based on the date format 
	 */
	public static Date getDateFromString(String dateString, String dateFormat) {
		try {
			return new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException (dateString, e);
		}
	}
	
	/**
	 * Returns the total number of hours between two dates
	 */
	public static int getNumberOfHours(Calendar startDate, Calendar endDate) {
		int numberOfHours = 0;
		Calendar tmpDate = (Calendar) startDate.clone();
		while (tmpDate.before(endDate)) {
			tmpDate.add(Calendar.HOUR, 1);
			++numberOfHours;
		}
		return numberOfHours;
	}
	
	
}
