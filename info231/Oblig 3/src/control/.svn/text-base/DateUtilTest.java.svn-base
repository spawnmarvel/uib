package control;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
/**
 * This class is testing functionalities in the DateUtil class.
 * @author dro068
 *
 */

public class DateUtilTest {
	private static String inNewDate;
	private static boolean allowPast;
	private static String formatStr;
	private static DateUtil du;

/**
 * Initiates data to be used in the testing process.
 * @throws Exception
 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inNewDate = "12-12-12";
		allowPast = true;
		formatStr = ("dd-MM-yy");
		du = new DateUtil();
	}

/**
 * Testing the isLegalTime()method.
 */
	@Test public void testPositiveIsLegalTime() {
		assertTrue(du.isLegalTime(inNewDate));


	}
	/**
	 * Testing the validateDate() method.
	 */

	@Test public void testPositiveValidateDate() {
		assertTrue(du.validateDate(inNewDate, allowPast, formatStr));
	}

}
