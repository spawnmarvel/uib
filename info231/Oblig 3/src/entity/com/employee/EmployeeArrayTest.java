/**
 * 
 */
package entity.com.employee;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;



/**
 * The test class for testing functionality of the EmployeeArray class.
 * 
 * @author Aslak & Hedvig
 *
 */
public class EmployeeArrayTest {
	private static EmployeeArray ea;
	private static Employee emp1, emp2, emp3;

	/**
	 * Sets up the EmployeeArray and three employees that are used in the testing.
	 * Adds one of the employees to the EmployeeArray.
	 */
	@Before
	public void setUp() throws Exception {
		ea = new EmployeeArray();
		emp1 = new Employee ("Hedvig");
		emp2 = new Employee ("Aslak");
		emp3 = new Employee ("Tommy");
		ea.addEmployee(emp1);
		
	}

	/**
	 * Tests the AddEmployee method by checking if you can add a new employee,
	 * and not add an employee that already exists in the EmployeeArray.
	 */
	@Test
	public void testAddEmployee() {
		assertTrue(ea.addEmployee(emp2.getName(), emp2.getId()));
		assertFalse(ea.addEmployee(emp1.getName(), emp1.getId()));
	}


	/**
	 * Tests the findEmplyee method by checking if an already existing employee 
	 * can be found in the EmployeeArray.
	 */
	@Test
	public void testFindEmployee() {
		Employee expected = emp1;
		Employee actual = ea.findEmployee(emp1.getId());
		
		assertEquals (expected, actual);
		
	}

	/**
	 * Tests the isEmp method by checking one employee that is supposed to be in
	 * the EmployeeArray and one that is not supposed to be there. 
	 */
	@Test
	public void testIsEmp() {
		assertTrue(ea.isEmp(emp1.getId()));
		assertFalse(ea.isEmp(emp3.getId()));
	}

	/**
	 * Tests the deleteEmployee method by deleting one employee that already exists
	 * in the EmployeeArray and one that doesn't.
	 */
	@Test
	public void testDeleteEmployee() {
		assertTrue(ea.deleteEmployee(emp1.getId()));
		assertFalse(ea.deleteEmployee(emp3.getId()));
	}

}
