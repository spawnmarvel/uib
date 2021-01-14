package interfaces;

import java.util.ArrayList;



import entity.com.messages.Mess;
import entity.com.messages.MessageArrayD;
/**
 * This class is an Interface that defines the methods required to implement
 * data access for a message object from and to data base table.
 * @ author David, Espen.
 * @ version 1.
 */
public interface MessagesDAO {

	/**
	 * This method accepts a message object as an argument and transforms the object's fields
	 * into data to store in data base table.
	 * @param aMessage
	 */
	public void newMessage(Mess aMessage);
	/**
	 * The method collects the data stored in the database initiates objects and
	 * insert those into an array of objects.
	 * The method returns an array containing objects of the specific type.
	 * @return
	 */
	public MessageArrayD getAll();
	/**
	 * The method accepts a message object as an argument, and
	 * deletes  the row containing this object's data from the database.
	 * @param aMessage
	 */
	public void deleteMessage(Mess aMessage);
	/**
	 * The method updates the status (done: true/false) stored in the data base for the
	 * given message.
	 * @param aMessage
	 */
	public boolean updateStatus(Mess oldMess, Mess newMesss);

	/**
	 * The method gets the spesific messages from the database by the spesific messages id
	 * and returns the messages to the gui window to be edited
	 * @return
	 */
	public String getSpesificMess(int mId);



}
