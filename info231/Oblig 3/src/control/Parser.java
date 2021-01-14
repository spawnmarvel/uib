package control;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * This class parses the different time and date objects so the can be read
 * @author dro068, jkl070
 *
 */
public class Parser {

	/**
	 * parses string to java.sql.Date 
	 * @param String the date to parse
	 * @return java.sql.Date 
	 */
	public static java.sql.Date stringDateToSqlDate(String date) {
		java.util.Date parseDateU = null;

		DateFormat format = new SimpleDateFormat("dd-MM-yy");
		try {
			parseDateU = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Date(new Date(parseDateU.getTime()).getTime());
	}
	/**
	 * parses string to java.sql.Date 
	 * @param String the time to parse
	 * @return java.sql.Time
	 */
	public static Time stringTimeToSqlTime(String time) {
		java.util.Date parseDateU = null;

		DateFormat format = new SimpleDateFormat("HH:mm");
		try {
			parseDateU = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Time(new Date(parseDateU.getTime()).getTime());
	}


	/**
	 * parses ava.util.Date to java.sql.Date 
	 * @param java.util.Date the date  to parse
	 * @return java.sql.Date
	 */
	public static java.sql.Date utilDateToSqlDate(java.util.Date inDate) {

		java.util.Date utilDate = inDate;
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;


	}
}



