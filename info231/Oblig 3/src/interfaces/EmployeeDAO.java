package interfaces;

import java.util.ArrayList;

import entity.com.employee.Employee;
/**
 * This class is an Interface that defines the methods required to implement 
 * data access for an employee object from and to data base table.
 * @ author David, Espen.
 * @ version 1.0 
 */
public interface EmployeeDAO {
	
	
	/**
	 * This method accepts an employee object as an argument and transforms the object's fields 
	 * into data to store in data base table.
	 * @param anEmployee
	 */
	public String setName(Employee anEmployee);
	/**
	 * The method collects the data stored in the database initiates objects and
	 * insert those into an array of objects.
	 * The method returns an array containing objects of the specific type.
	 * @return
	 */
	public ArrayList<Employee> getAll();
	/**
	 * The method accepts  an Employee object as an argument, and 
	 * deletes  the row containing this object's data from the database.
	 * @param anEmployee
	 */
	public void deletEmployee(Employee anEmployee);

}
