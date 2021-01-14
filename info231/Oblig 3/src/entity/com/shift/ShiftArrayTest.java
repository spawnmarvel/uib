package entity.com.shift;


import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
   /**
    * This class tests the shiftArray`s to method`s addShift and deleteShift
    * @author jkl070 + dro068
    *
    */

public class ShiftArrayTest {

	private static Shift s;
	private static ShiftArray shiftArr;
    
	/**
     * This method initializes the object to be used(classes), Sometimes several tests
     * need to share computationally expensive setup 
     * (like logging into a database). While this can compromise the independence of tests, 
     * sometimes it is a necessary optimization.  
     * The @BeforeClass methods of super classes will be run before those the current class.
     */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s = new Shift( "12-09-11", "13:00", "21:00", 2,"David Ronen", "");
		shiftArr = new ShiftArray();
	}
	/**
	 * This method tests the addShift method from ShiftArray:
	 * (The method adds a shift to the array by passing an Shift object as a parameter.)
	 */
	@Test public void testPositivAddShift() {
		assertTrue(shiftArr.addShift(s));
	}
	
	/**
	 * This method tests the deleteShift from shiftArray:
	 * (The method deletes a shift from the array if the employees name, his/hers ID number
	 *  and the date of the shift matches the search terms.)
	 */
	@Test public void testPositiveDeletShift() {
		assertTrue(shiftArr.deleteShift(s.getId(), s.getDate(), s.getStartTime()));
	}
	



}
