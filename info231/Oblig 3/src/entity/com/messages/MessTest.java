package entity.com.messages;


import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.listeners.MessageListener;

/**
 * @author Tommy, Erlend
 * Class will not be added to AllTests because it only contains testing of a set method
 */
public class MessTest {

	private static Mess m;
	
	/**
	 * Intitial set up method for test class
	 *
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		Date date = new Date();
		m = new Mess(date, date, 1, "Test", 1, true);
	}
	
	
	/**
	 * Test method for setting a message
	 *
	 */
	@Test
	public void testSetMessage() throws Exception {
		String message = "Test1";
		m.setMelding(message);
		String expected = "Test1";
		String result = m.getMelding();
		assertTrue(expected.equals(result));
	}

}
