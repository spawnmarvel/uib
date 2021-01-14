package dataAccsesObject;

import interfaces.EmployeeDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;

import control.FeedbackHandler;
import entity.com.employee.Employee;
/**
 * The class implements the EmployeeDAO interface.
 * The class controls inserting and extracting data to and from employee database.
 * @author dro068,jkl070
 *
 */
public class MySqlEmployeeDAO extends MySqlDAOFactory implements EmployeeDAO {


	Connection conn;
	String insertString = "INSERT INTO employee Values(?,?)";
	String getAllString = "SELECT name, id FROM employee";
	String deleteString = "DELETE FROM employee WHERE id =?";
	PreparedStatement insert;
	PreparedStatement getAllEmp;
	PreparedStatement deleteEmp;
	private static MySqlEmployeeDAO theMySqlEmployeeDAO = null;

	/**
	 * Constructor for the class.
	 */
	MySqlEmployeeDAO() {

		conn = super.createConnection();

		try {

			getAllEmp = conn.prepareStatement(getAllString);

			insert = conn.prepareStatement(insertString);

			deleteEmp = conn.prepareStatement(deleteString);



		}		
		catch (SQLException ex){
			FeedbackHandler.failedFeedback("Feil oppstod");

			System.err.println(ex.getMessage());
		}
	}

	/**
	 * This method accepts an employee object as an argument and transforms the object's fields 
	 * into data to store in data base table.
	 * @param anEmployee
	 */
	@Override
	public String setName(Employee anEmployee) {
		try {
			insert.setInt(1,anEmployee.getId());
			insert.setString(2, anEmployee.getName());
			insert.executeUpdate();
		}
		catch(SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			System.out.println(e);
		}

		return null;
	}
	/**
	 * The method collects the data stored in the database initiates objects and
	 * insert those into an array of objects.
	 * The method returns an array containing objects of the specific type.
	 * @return
	 */
	@Override
	public ArrayList<Employee> getAll() {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		ResultSet res= null;

		try {
			res = getAllEmp.executeQuery();
			while (res.next()) {
				String name = res.getString("name");
				int id = res.getInt("id");
				Employee emp = new Employee(name, id);
				empList.add(emp);
			}
		} catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
			return null;
		}

		return empList;
	}
	/**
	 * The method returns an instance of the class.
	 * @return theMySqlEmployeeDAO.
	 */
	public static MySqlEmployeeDAO getInstance() {
		if (theMySqlEmployeeDAO == null)
			theMySqlEmployeeDAO = new MySqlEmployeeDAO();
		return theMySqlEmployeeDAO;
	}
	/**
	 * The method accepts  an Employee object as an argument, and 
	 * deletes  the row containing this object's data from the database.
	 * @param anEmployee
	 */
	@Override
	public void deletEmployee(Employee anEmp) {

		try {
			deleteEmp.setInt(1,anEmp.getId());
			deleteEmp.executeUpdate();
		} catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
		}


	}


}
