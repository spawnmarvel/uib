package entity.com.messages;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * @author Tommy
 *
 */
public class MessageArrayDTest {

	private static MessageArrayD messA;
	private static Mess mess;
	
	
	/**
	 * Initial set up for test class
	 *
	 */	
	@Before
	public void setUp() throws Exception {
		messA = new MessageArrayD();
		Date date = new Date();
		mess = new Mess(date, date, 1, "Test", 1, true);
	}
	
	/**
	 * Test method for adding a message to list
	 *
	 */
	@Test
	public void testAddToList() throws Exception {
		messA.addToList(mess);
		Mess[] result = messA.getList();
		assertTrue(result[messA.getLast()].equals(mess));
	}
	
	/**
	 * Test method for removing a message from list
	 *
	 */
	@Test
	public void testRemove() throws Exception {
		messA.addToList(mess,0);
		messA.remove(0);
		assertTrue(messA.getLast()== -1);
	}

}
