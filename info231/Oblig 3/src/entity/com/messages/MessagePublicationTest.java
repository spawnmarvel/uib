package entity.com.messages;


import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tommy
 */

public class MessagePublicationTest {

	private static MessagePublication messP;
	private static MessageArrayD messA;
	private static Mess mess;
	private static Date date, delDate;
	
	/**
	 * Initial set up method for test class
	 *
	 */
	@Before
	public void setUp() throws Exception {
		messA = new MessageArrayD();
		Calendar rightNow = Calendar.getInstance();
		date = rightNow.getTime();
		rightNow.add(Calendar.DAY_OF_YEAR, +1);
		delDate = rightNow.getTime(); 
		
		mess = new Mess(delDate, date, 1, "Test", 1, true);
		messA.addToList(mess);
		messP = new MessagePublication(messA);
	}
	
	/**
	 * Method for testing publication of a message
	 *
	 */
	@Test
	public void testPublicAllMessages() throws Exception {
		assertTrue(messP.publicAllMessages().equals("MeldingId: " + messP.getFrontMessID() + " SlettDato: " + delDate + " LagetDato: " + date + " Importance: 1 Melding: Test Utført = true\n"));
	}
}
