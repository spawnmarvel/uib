package control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import interfaces.MessagesDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

//import org.easymock.EasyMock;
import org.easymock.classextension.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;

import dataAccsesObject.MySqlMessagesDao;
import entity.com.employee.Employee;
import entity.com.employee.EmployeeArray;
import entity.com.messages.Mess;
import entity.com.messages.MessageArrayD;
import entity.com.shift.Shift;
import entity.com.shift.ShiftArray;
/**
 * This class is testing functionalities in the Controller class.
 * @author David Ronen.
 * @version 1.0.0
 */

public class ControllerTest {
	private static Controller testObject;

	/**
	 * Initializes the test object - Controller class.
	 * @BeforeClass
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		//Setting Controller to test mode: Only the status variable is initialized. By not initializing
		//all other variables we avoid dependences and database call:
		testObject = new Controller("Test mode");
	}

	/**
	 * Assigns data and mock and testing the appendMessageToRediger() method.
	 * @throws ParseException
	 *
	 */
	@Test
	public void testAppendMessageToRediger() throws ParseException{
		//Initializing a Date object that will be passed to a Shift constructor:
		DateFormat df = new SimpleDateFormat("dd-MM-yy");
		Date today = df.parse("14-04-11");
		//Initializing a new Shift instance:
		Mess aMess = new Mess(7 , 7,today , "TEST: Controller mock1", 2, false, today);
		//The tested method must return the text stored in "melding" variable:
		String expected = aMess.getMelding();
		//Creating a mock for MySqlMessagesDao class to avoid dependences and database call:
		MySqlMessagesDao mock = EasyMock.createMock(MySqlMessagesDao.class);
		//Replacing the real MySqlMessagesDao object with the mock object:
		testObject.setDao(mock);
		//Preparing the mock object to execution:
		EasyMock.expect(mock.getSpesificMess(aMess.getMessageId())).andReturn(aMess.getMelding());
		EasyMock.replay(mock);
		//Getting the actual value returned by the tested method in order to compare with the expected value:
		String actual = testObject.appendMessageToRediger(aMess.getMessageId());
		assertTrue(expected.equalsIgnoreCase(actual));
	}
	/**
	 * Assigns data and mock and testing the publishByDate() method.
	 * @throws ParseException
	 *
	 */
	@Test
	public void testPublishByDate() throws ParseException{
		//Initializing a Date object that will be passed to a Shift constructor:
		DateFormat df = new SimpleDateFormat("dd-MM-yy");
		Date today = df.parse("14-04-11");
		//Initializing a new Shift instance:
		Mess aMess = new Mess(7 , 7,today , "TEST: Controller mock2", 2, false, today);
		//Initializing an array of messages (MessageArrayD) - the return type of the method
		//that will be called by the tested method:
		MessageArrayD mArrayD = new MessageArrayD();
		//Adding the message object to the array:
		mArrayD.addToList(aMess);
		//The message array that will be returned must include this message object:
		Mess expected = aMess;
		//Mocking the MySqlMessagesDao. Working with the mock instead of the real object
		//makes it possible to determine the result of the method call in the mock and
		//by that avoiding dependency on the result:
		MySqlMessagesDao mock = EasyMock.createMock(MySqlMessagesDao.class);
		//Replacing the real DAO object with the mock:
		testObject.setDao(mock);
		//Telling the mock which method call to expect and what to return:
		EasyMock.expect(mock.getMessageArrayByDate(aMess.getDate())).andReturn(mArrayD);
		//Preparing the mock for execution:
		EasyMock.replay(mock);
		//Getting the actual returned value from the tested method call:
		MessageArrayD returnedArray = testObject.publishByDate(aMess.getDate());
		//Extracting the actual message object from the array that was returned:
		Mess actual = returnedArray.getList()[0];
		//Testing that the expected result are in fact the actual result:
		assertTrue(expected.equals(actual));
	}

	/**
	 * Assigns data and mock and testing the findEmployee() method.
	 */
	@Test
	public void testFindEmployee(){
		//Creating a EmployeeArray mock:
		EmployeeArray empArrMock = EasyMock.createMock(EmployeeArray.class);
		//Passing the new EmployeeArray instance (the mock) to be use by the Controller class. In
		//this way avoiding any call to database for that purpose:
		testObject.setEmpArr(empArrMock);
		EasyMock.expect(empArrMock.isEmp(1)).andReturn(true);
		EasyMock.replay(empArrMock);
		//Asserting that the operation was successful and the method returned true:
		assertTrue(testObject.findEmployee(1));
	}
	/**
	 * This is the first test for the showShiftPrIdOrDate method. This method presents a chosen shift
	 * given either ID or Date. This part tests the function of finding a shift by a given ID number.
	 *
	 */
	@Test
	public void testShowShiftPrId(){
		//Creating an ArrayList and a Shift object. This shift will be used to test the method.
		//The shift is expected to be returned by passing it's ID field as an argument:
		ArrayList<Shift>shiftList = new ArrayList<Shift>();
		Shift aShift = new Shift("12-09-11", "13:00", "21:00", 2,"David Ronen", "");
		//Inserting the Shift in to the array:
		shiftList.add(aShift);
		//Mocking the ShiftArray object:
		ShiftArray shiftArrMock = EasyMock.createMock(ShiftArray.class);
        //Passing the mock to replace the real ShiftArray object:
		testObject.setShiftArray(shiftArrMock);
		//Telling the mock which method call to expect and what to return as the result:
		EasyMock.expect(shiftArrMock.getList()).andReturn(shiftList);
		//Preparing the mock for execution:
		EasyMock.replay(shiftArrMock);
		//The expected result:
		String expected = aShift.toString();
		//The actual result from the tested method call:
		String actual = testObject.showShiftPrIdOrDate(2);
		//Testing that expected result and actual result are equal:
		assertTrue(expected.equalsIgnoreCase(actual));
	}
	/**
	 *  This is the second test for the showShiftPrIdOrDate method. This method presents a chosen shift
	 * given either ID or Date. This part tests the function of finding a shift by a given date.
	 *
	 */
	@Test
	public void testShowShiftPrDate(){
		//Creating an ArrayList and a Shift object. This shift will be used to test the method.
		//The shift is expected to be returned by passing it's date field as an argument:
		ArrayList<Shift>shiftList = new ArrayList<Shift>();
		Shift aShift = new Shift("12-09-11", "13:00", "21:00", 2,"David Ronen", "");
		//Inserting the Shift in to the array:
		shiftList.add(aShift);
		//Mocking the ShiftArray object:
		ShiftArray shiftArrMock = EasyMock.createMock(ShiftArray.class);
		//Passing the mock to replace the real ShiftArray object:
		testObject.setShiftArray(shiftArrMock);
		//Telling the mock which method call to expect and what to return as the result:
		EasyMock.expect(shiftArrMock.getList()).andReturn(shiftList);
		//Preparing the mock for execution:
		EasyMock.replay(shiftArrMock);
		//The expected result:
		String expected = aShift.toString();
		//The actual result from the tested method call:
		String actual = testObject.showShiftPrIdOrDate(aShift.getDate());
		//Testing that expected result and actual result are equal:
		assertTrue(expected.equalsIgnoreCase(actual));
	}
	/**
	 * Assigns data and mock and testing the getEmployee() method.
	 */
	@Test
	public void testGetEmployee(){
		EmployeeArray empArrMock = EasyMock.createMock(EmployeeArray.class);
		testObject.setEmpArr(empArrMock);
		EasyMock.expect(empArrMock.findEmployee(1)).andReturn(new Employee("David", 1));
		EasyMock.replay(empArrMock);
		String expected = "David";
		String actual = testObject.getEmployee(1);
		assertTrue(expected.equalsIgnoreCase(actual));
	}
	/**
	 * Assigns data and mock and testing the initEmployee() method.
	 */
	@Test
	public void testInitEmployee(){
		ArrayList<Employee>empList = new ArrayList<Employee>();
		Employee e = new Employee("David", 1);
		empList.add(e);
		EmployeeArray empArrMock = EasyMock.createMock(EmployeeArray.class);
		testObject.setEmpArr(empArrMock);
		EasyMock.expect(empArrMock.getStaffSize()).andReturn(1).anyTimes();
		EasyMock.expect(empArrMock.getList()).andReturn(empList);
		EasyMock.replay(empArrMock);
		int expected = 1;
		testObject.initEmployee();
		Employee emp = new Employee();
		int actual = emp.getTotNum();
		assertEquals(expected, actual);
	}

	/**
	 * //Clean up the mock object after the test session interface
	 */

	protected void after(Object easyMock){

		EasyMock.reset();
	}
}
