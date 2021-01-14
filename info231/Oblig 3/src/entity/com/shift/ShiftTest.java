package entity.com.shift;

/**
 * This class tests the shift`s date and test if the method isLegal time
 * works
 * @author jkl070 + dro068
 */


import static org.junit.Assert.*;



import org.junit.BeforeClass;
import org.junit.Test;


public class ShiftTest {
	private static Shift s; 


    /**
     * This method initializes the object to be used(classes), Sometimes several tests
     * need to share computationally expensive setup 
     * (like logging into a database). While this can compromise the independence of tests, 
     * sometimes it is a necessary optimization.  
     * The @BeforeClass methods of super classes will be run before those the current class.
     */
	@BeforeClass
	public static void setUp() {
		s = new Shift( "12-05-11", "12:00", "18:00", 2,"David Ronen", "");

	}
    /**
     * This method tests the shifts method setDate,
     * and checks if it returns the date expected
     */
	@Test public void testSetDate(){
		String newDate = "13-06-11";
		s.setDate(newDate);
		String expected = "13-06-11";
		String result = s.getDate();
		assertTrue(expected.equalsIgnoreCase(result));
	}
	/**
	 * This method tests the isLegalTime method from the shift class,
	 * (The method verifies that the shift's start time
	 *  is earlier than it's end time. )
	 */
	@Test public void testIsLegalTime() {
		assertTrue(s.isLegalTime( "12:00", "18:00"));
		assertFalse(s.isLegalTime( "19:00", "18:00"));
		assertFalse(s.isLegalTime("19:00", "19:00"));
		}
}



