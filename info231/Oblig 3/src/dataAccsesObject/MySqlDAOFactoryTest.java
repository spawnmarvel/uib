package dataAccsesObject;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
/**
 * This class tests the MySqlDAOFactory class to ensure that the right DAO object 
 * is returned by the getDao method calls for each of the three DAOs.
 * 
 * @author David - dro068.
 *
 */

public class MySqlDAOFactoryTest {
	private static MySqlDAOFactory testObject;
/**
 * Initiate the test object.	
 * @throws Exception
 */
	@BeforeClass
	public static void BeforeClass() throws Exception {
		testObject = new MySqlDAOFactory();
	}
/**
 * Test that the right DAO is returned, in this case MySqlMessagesDao. 
 */
	@Test
	public void testGetMesagesDAO() {
		MySqlMessagesDao expected = MySqlMessagesDao.getInstance();
		MySqlMessagesDao actual =  testObject.getMesagesDAO();
		assertEquals(expected, actual);
	}
/**
 * Test that the right DAO is returned, in this case MySqlEmployeeDAO.
 */
	@Test
	public void testGetEmployeeDAO() {
		MySqlEmployeeDAO expected = MySqlEmployeeDAO.getInstance();
		MySqlEmployeeDAO actual = testObject.getEmployeeDAO();
		assertEquals(expected, actual);
	}
/**
 * Test that the right DAO is returned, in this case MySqlShiftDAO.
 */
	@Test
	public void testGetShiftDao() {
		MySqlShiftDAO expected = MySqlShiftDAO.getInstance();
		MySqlShiftDAO actual = testObject.getShiftDao();
		assertEquals(expected, actual);
	}

}
