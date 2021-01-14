
package gui;

/**
 * This test class test the function addEmployee which begins
 * in the gui and end in the database
 *@author jkl070
 *
 */
import static org.junit.Assert.*;
import interfaces.EmployeeDAO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JDialog;
//import org.fest.swing.fixture.JOptionPaneFixture;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import control.Controller;
import control.FeedbackHandler;
import dataAccsesObject.MySqlEmployeeDAO;
import entity.com.employee.Employee;

public class AddEmployeeTest {
	private static AddEmployee addEmp;
	

    /**
     * This method creates new objects of the class which is to be tested,
     * the addEmployee class
     * @throws Exception
     */
	@BeforeClass
	public static  void setUpBeforeClass() throws Exception {
		Hoved ho = new Hoved();
		ho.getWindow().setVisible(false);
		addEmp = new AddEmployee(ho);
		addEmp.getWindow().setVisible(false);
		}
    
	/**
	 * This method executes a test with an employee and assertEquals if it is the last employee
	 * in the database
	 */
	@Test public void testAddEmployeeActionEvent() {
        //The employee 
        String expected = "Xiang lou";
		addEmp.getFirsName().setText("Xiang");
		addEmp.getLastName().setText("lou");
		//The action event,the name is typed in the text fields and the button is pressed'
		//The Employee object is inserted in the database
		ActionEvent e = new ActionEvent(addEmp.getSubmit(), 1, "Legg til ansatt");
		addEmp.actionPerformed(e);
		//All the employee are extracted from the database and put in a list to
		//get the last employee object, this is the result object
	    EmployeeDAO m = MySqlEmployeeDAO.getInstance();
		List<Employee> l = m.getAll();
		Employee sisteE = l.get(l.size()-1);
		String result = sisteE.getName();
		System.out.println("exp: "+ expected);
		System.out.println("res: " + result);
		assertEquals(expected,result);
		//This deletes the last employee, the test employee object
		Controller c = new Controller();
		c.deleteEmp(sisteE.getId());
		
	}
	


}
