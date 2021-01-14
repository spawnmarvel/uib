package dataAccsesObject;

import interfaces.EmployeeDAO;
import interfaces.MessagesDAO;
import interfaces.ShiftDAO;
/**
 * The class enables the choice between different platforms for storing and accessing data.
 * The class is abstract and is extends by the different data access classes.
 * @author dro068, jkl070
 *
 */

public abstract class DAOFactory {
	
	
	
	public static final int MYSQL = 1;
	
	
	/**
	 * The method that extend/ inhered  this method will return an instance of EmployeeDAO.
	 * @return
	 */
	public abstract EmployeeDAO getEmployeeDAO();
	/**
	 * The method that extend/ inhered  this method will return an instance of ShiftDAO.
	 * @return
	 */
	public abstract ShiftDAO getShiftDao();
	/**
	 * The method that extend/ inhered  this method will return an instance of MessageDAO.
	 * @return
	 */
	public abstract MessagesDAO getMesagesDAO();

	
	/**
	 * The method returns the platform chosen.
	 * @param whichFactory
	 * @return
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL :
			return new MySqlDAOFactory();
			default :
				return null;
				}
	}
}
