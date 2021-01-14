package control;


import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.BeforeClass;
import org.junit.Test;
   /**
    * This class tests the Parser class which is responsible for:
    * (parses the different time and date objects so the can be read)
    * @author jkl070 + dro068
    *
    */

public class ParserTest {
	private static String stringDate;
	private static String time;
	private static java.util.Date utilDate;
	private static Parser p;
	private static java.sql.Date javaSql;
	private static java.sql.Time sqlJud;


	/**
     * This method initializes the object to be used(classes), Sometimes several tests
     * need to share computationally expensive setup 
     * (like logging into a database). While this can compromise the independence of tests, 
     * sometimes it is a necessary optimization.  
     * The @BeforeClass methods of super classes will be run before those the current class.
     */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new Parser();
		stringDate = "12-12-12";
		time ="12:00";
		DateFormat format = new SimpleDateFormat("dd-MM-yy");
		utilDate = format.parse("12-12-12");
		javaSql = new java.sql.Date(utilDate.getTime());
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		java.util.Date jud = timeFormat.parse(time);
		sqlJud = new java.sql.Time(jud.getTime());
		
		
		

	}
	/**
	 * This method tests the Parser`s method which parses string to java.sql.Date 
	 */
	@Test public void testJavasqlDatestringDateToSqlDate() {
		java.sql.Date expected = javaSql;
		java.sql.Date result = p.stringDateToSqlDate(stringDate);

		assertTrue(result.equals(expected));
	}
	/**
	 * This method tests the Parser`s method which parses string to java.sql.Time
	 */
	@Test public void testStringTimeToSqlTime() {
		 java.sql.Time expected = sqlJud;
		 java.sql.Time result = p.stringTimeToSqlTime(time);
		 assertTrue(result.equals(expected));
		
		
	}
	/**
	 * This method tests the Parser`s method which parses java.util.Date to java.sql.Date 
	 */
	@Test public void testUtilDateToSqlDate() {
		java.sql.Date expected = javaSql;
		java.sql.Date result = p.utilDateToSqlDate(utilDate);
		assertTrue(result.equals(expected));
	}

}
