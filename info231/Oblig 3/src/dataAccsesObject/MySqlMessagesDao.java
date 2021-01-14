package dataAccsesObject;

import interfaces.MessagesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.mysql.jdbc.Messages;
//import com.mysql.jdbc.PreparedStatement;

import control.FeedbackHandler;
import control.Parser;
import entity.com.employee.Employee;
import entity.com.messages.Mess;
import entity.com.messages.MessageArrayD;

/**
 * The class implements the MessageDAO interface.
 * The class controls inserting and extracting data to and from message database.
 * @author dro068, jkl070
 *
 */
public class MySqlMessagesDao extends MySqlDAOFactory  implements MessagesDAO {
	String insertString = "INSERT INTO tbl_messages Values(?,?,?,?,?,?,?)";
	String getAllString = " SELECT * FROM tbl_messages";
	String delMessages= "DELETE FROM tbl_messages WHERE message_Id= (?)";
	String getOneMess = "SELECT me_text FROM tbl_messages WHERE message_id=(?)";
	String findByDate= "SELECT * FROM tbl_messages WHERE me_date= (?)";
	String getMaxId = "SELECT max(message_id) AS message_id FROM tbl_messages";
	String updateString = "UPDATE tbl_messages SET m_id = ?,me_date = ?, me_text =?,me_status =?,me_done =?,me_expeir =? WHERE message_Id= (?)";

	private static MySqlMessagesDao theMySqlMessagesDAO = null;
	PreparedStatement delete;
	PreparedStatement insert;
	PreparedStatement getA;
	PreparedStatement getOne;
	PreparedStatement getByDate;
	PreparedStatement getMax;
	PreparedStatement upDateMe;

	private Connection conn;
	


	public MySqlMessagesDao() {
		conn = super.createConnection();
		try {
			insert = (PreparedStatement) conn.prepareStatement(insertString);
			getA = (PreparedStatement) conn.prepareStatement(getAllString);
			delete= (PreparedStatement) conn.prepareStatement(delMessages);
			getOne = (PreparedStatement) conn.prepareStatement(getOneMess);
			getByDate= (PreparedStatement) conn.prepareStatement(findByDate);
			getMax = (PreparedStatement) conn.prepareStatement(getMaxId);
			upDateMe = (PreparedStatement) conn.prepareStatement(updateString);
		}

		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
		}
	}


	/**
	 * This method accepts a message object as an argument and transforms the object's fields
	 * into data to store in data base table.
	 * @param aMessage
	 */
	@Override
	public void newMessage(Mess aMessage) {
		try {
			insert.setInt(1, aMessage.getMessageId());
			insert.setInt(2, aMessage.getId());
			insert.setDate(3,Parser.utilDateToSqlDate(aMessage.getDate()));
			insert.setString(4, aMessage.getMelding());
			insert.setInt(5, aMessage.getImportance());
			insert.setBoolean(6, aMessage.getDone());
			insert.setDate(7, Parser.utilDateToSqlDate(aMessage.getDeleteDate()));
			insert.executeUpdate();



		}
		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			System.out.println(e);
		}

	}


	/**
	 * The method collects the data stored in the database initiates objects and
	 * insert those into an array of objects.
	 * The method returns an array containing objects of the specific type.
	 * @return
	 */

	@Override
	public MessageArrayD getAll() {
		MessageArrayD messages = new MessageArrayD();
		ResultSet res= null;

		try {
			res = getA.executeQuery();
			while (res.next()) {
				int message_id = res.getInt("message_id");
				int emp_id = res.getInt("m_id");
				Date me_date =  res.getDate("me_date");
				String text= res.getString("me_text");
				int pri= res.getInt("me_status");
				boolean done= res.getBoolean("me_done");
				Date ex_date= res.getDate("me_expeir");

				Mess aMessage= new Mess(message_id, emp_id, me_date, text, pri, done, ex_date);
				messages.addToList(aMessage);
			}

		} catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
			return null;
		}

		return messages;
	}


	/**
	 * The method accepts a message object as an argument, and
	 * deletes  the row containing this object's data from the database.
	 * @param aMessage
	 */
	@Override
	public void deleteMessage(Mess aMessage) {

		try{
			delete.setInt(1,  aMessage.getMessageId());
			delete.executeUpdate();
		}
		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();

		}
	}
	@Override
	public String getSpesificMess(int messageId) {
		ResultSet res= null;
		String s = null;
		try {
			getOne.setInt(1, messageId);
			res = getOne.executeQuery();
			while (res.next()) {
				s = res.getString("me_text");
				System.out.println(s);

			}
		}
		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();

		}

		return s ;
	}

	public  MessageArrayD getMessageArrayByDate(java.util.Date date){
		MessageArrayD messages = new MessageArrayD();
		ResultSet res= null;
		java.sql.Date sqlDate= Parser.utilDateToSqlDate(date);

		try {
			getByDate.setDate(1, sqlDate);

			res = getByDate.executeQuery();
			while (res.next()) {

				int message_Id= res.getInt("message_id");
				int emp_id= res.getInt("m_id");
				Date me_date= res.getDate("me_date");
				String text= res.getString("me_text");
				int pri= res.getInt("me_status");
				boolean done= res.getBoolean("me_done");
				Date ex_date= res.getDate("me_expeir");

				Mess aMessage= new Mess(message_Id, emp_id, me_date, text, pri, done, ex_date);
				messages.addToList(aMessage);
			}

		} catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
			return null;
		}

		return messages;
	}





	/**
	 * The method updates the status (done: true/false) stored in the data base for the
	 * given message.
	 * @param aMessage
	 */
	@Override
	public boolean updateStatus(Mess oldMess, Mess newMess) {


		try {
			int newEmp_id= newMess.getId();
			java.sql.Date newMe_date= Parser.utilDateToSqlDate(newMess.getDate());
			String newText= newMess.getMelding();
			int newPri= newMess.getImportance();
			boolean newDone= newMess.getDone();
			java.sql.Date newEx_date= Parser.utilDateToSqlDate(newMess.getDeleteDate());



			upDateMe.setInt(1,newEmp_id );
			upDateMe.setDate(2, newMe_date);
			upDateMe.setString(3, newText);
			upDateMe.setInt(4, newPri);
			upDateMe.setBoolean(5, newDone);
			upDateMe.setDate(6, newEx_date);
			upDateMe.setInt(7,oldMess.getMessageId());

			upDateMe.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();
			return false;

		}





	}
	public int getMaxIdFromDb() {
		ResultSet res= null;
		int id = 0;
		try {

			res = getMax.executeQuery();
			while (res.next()) {
				id = res.getInt("message_id");


			}
		}
		catch (SQLException e) {
			FeedbackHandler.failedFeedback("Feil oppstod");
			e.printStackTrace();

		}
		return id ;
	}


	/**
	 * he method returns an instance of the class.
	 * @return
	 */

	public static MySqlMessagesDao getInstance() {
		if (theMySqlMessagesDAO == null)
			theMySqlMessagesDAO  = new MySqlMessagesDao ();
		return theMySqlMessagesDAO ;
	}




}
