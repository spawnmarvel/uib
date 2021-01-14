package dataAccsesObject;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

import control.FeedbackHandler;
import interfaces.MessagesDAO;
/**
 * The class returns the different data access objects.
 * The class connects to and disconnects from database. 
 * @author dro068, jkl070
 *
 */


public class MySqlDAOFactory extends DAOFactory {




	String host = "hoguslg.uib.no";
	String dbName = "jkl070";
	int port = 3306;
	String mySqlUrl = "jdbc:mysql//" + host + ":" + port + "/" + dbName;
	private static String pass = "javafrosk";
	static Connection conn;

	/**
	 * The method instantiates a connection object which connects to database.
	 * @return conn.
	 */
	public static Connection createConnection(){
		conn = null;

		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://hoguslg.uib.no:3306/" + "jkl070", "jkl070", pass);

		}		
		catch (SQLException ex){

			System.err.println(ex.getMessage());
		}
		return conn;
	}
	/**
	 * The method returns an instance of MySqlEmployeeDAO.
	 */
	@Override
	public MySqlEmployeeDAO getEmployeeDAO() {
		MySqlEmployeeDAO empDao = MySqlEmployeeDAO.getInstance();
		return empDao;
	}
	/**
	 * The method returns an instance of MySqlShiftDAO.
	 */

	@Override
	public MySqlShiftDAO getShiftDao() {
		MySqlShiftDAO shiftDao = MySqlShiftDAO.getInstance();
		return shiftDao;
	}
	/**
	 * The method returns an instance of MySqlMessageDAO.
	 */
	@Override
	public MySqlMessagesDao getMesagesDAO() {
		MySqlMessagesDao messagesDao = MySqlMessagesDao.getInstance();
		return messagesDao;
	}
	/**
	 * The method disconnects from database.
	 */
	public void disConnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			FeedbackHandler.failedFeedback("koble fra gikk ikke");
			e.printStackTrace();
		}
	}

}
