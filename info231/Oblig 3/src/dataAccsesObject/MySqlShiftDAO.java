package dataAccsesObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;

import control.FeedbackHandler;
import control.Parser;
import entity.com.shift.Shift;
import interfaces.ShiftDAO;
/**
 * The class implements the ShiftDao interface.
 * The class controls inserting and extracting data to and from shift database.
 * @author jkl070, dro068.
 *
 */
public class MySqlShiftDAO extends MySqlDAOFactory implements ShiftDAO {



	String insertString = "INSERT INTO shift Values(?,?,?,?,?,?)";
	String getAllString = "SELECT * FROM shift";
	String deleteString = "DELETE FROM shift WHERE date = ? AND sTime = ? AND  id =?";
	PreparedStatement insert;
	PreparedStatement getAllShift;
	PreparedStatement deleteShift;
	private Connection conn;
	private static MySqlShiftDAO theMySqlShiftDAO = null;


	/**
	 * Constructor for the class.
	 */
	public MySqlShiftDAO() {
		conn = super.createConnection();
		try {
			insert = (PreparedStatement) conn.prepareStatement(insertString);
			getAllShift = (PreparedStatement) conn.prepareStatement(getAllString);
			deleteShift = (PreparedStatement) conn.prepareStatement(deleteString);


		} catch (SQLException e) {
			failedFeedBack("feil i konstruktøren");
			e.printStackTrace();
		}
	}
	/**
	 * The method takes a shift object as an argument and store
	 * the data of that particular shift in the database.
	 * @param  aShift.
	 */

	@Override
	public void newShift(Shift aShift){

		try {
			insert.setDate(1,Parser.stringDateToSqlDate((aShift.getDate())) );
			insert.setTime(2, Parser.stringTimeToSqlTime(aShift.getStartTime()));
			insert.setTime(3, Parser.stringTimeToSqlTime(aShift.getEndTime()));
			insert.setString(4, aShift.getName());
			insert.setString(5, aShift.getComment());
			insert.setInt(6, aShift.getId());

			insert.executeUpdate();
		} catch (SQLException e) {
			failedFeedBack("feil: "+e);
		}

	}

	/**
	 * The method informs the user in case something went wrong.
	 * @param message
	 */
	public void failedFeedBack(String message)

	{
		FeedbackHandler.failedFeedback(message);
	}
	/**
	 * The method collects the data stored in the database initiates objects and
	 * insert those into an array of objects.
	 * The method returns an array containing objects of the specific type.
	 * @return
	 */
	@Override
	public ArrayList<Shift> getAll() {
		ArrayList<Shift> shiftList = new ArrayList<Shift>();
		ResultSet res= null;

		try {
			res = getAllShift.executeQuery();
			while (res.next()) {
				Date date =  res.getDate("date");
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
				String sDate = df.format(date);

				SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
				Date sTime = res.getTime("sTime");
				String sTimeString = dft.format(sTime);

				Date eTime = res.getTime("eTime");
				String eTimeString = dft.format(eTime);

				String name = res.getString("name");
				String comment = res.getString("comment");

				int id = res.getInt("id");
				Shift aShift = new Shift(sDate,sTimeString, eTimeString, id, name, comment );


				shiftList.add(aShift);
			}



		} catch (SQLException e) {
			System.out.println("Feil i database");
			e.printStackTrace();
			return null;
		}


		return shiftList;
	}
	/**
	 * The method accepts a shift object as an argument, and
	 * deletes  the row containing this object's data from the database.
	 * @param aShift
	 */
	@Override
	public void deletShift(Shift aShift) {
		try{
			deleteShift.setDate(1,
					Parser.stringDateToSqlDate((aShift.getDate())));


			deleteShift.setTime(2,
					Parser.stringTimeToSqlTime(aShift.getStartTime()));

			deleteShift.setInt(3, aShift.getId());
			deleteShift.executeUpdate();

		}
		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
		}
	}
	/**
	 * The method replaces data stored for one (oldShift) with new data (newShift).
	 * @param oldShift
	 * @param newShift
	 */
	@Override
	public void updateShift(Shift oldShift, Shift newShift) {
		deletShift(oldShift);
		newShift(newShift);

		/**
		 * The method returns an instance of the class.
		 * @return theMySqlShiftDAO.
		 */
	}
	public static MySqlShiftDAO getInstance() {
		if (theMySqlShiftDAO == null)
			theMySqlShiftDAO = new MySqlShiftDAO();
		return theMySqlShiftDAO;
	}

}
