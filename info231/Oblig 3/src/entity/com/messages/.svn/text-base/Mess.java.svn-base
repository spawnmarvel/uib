package entity.com.messages;

import java.util.Calendar;
import java.util.Date;

import dataAccsesObject.MySqlMessagesDao;

//import java.util.Calendar;
//import java.util.GregorianCalendar;

/**
 *
 * @author Aslak Roedder, Hedvig Andersland
 * modified by David, Espen.
 *
 */
public class Mess {

	private Date delDate;
	private Date date;
	private int importance;
	private String message; // text - skrift alt
	private int empId;
	private boolean done;
	private int messageId;
	private Calendar cal = Calendar.getInstance();
	




	/**
	 * Constructors for objects of class Shift
	 */
	public Mess(Date delDate, Date date, int importance, String message, int empId, boolean done) {
		this.delDate = delDate;
		this.date = date;
		this.importance = importance;
		this.message = message;
		this.empId = empId;
		this.done = done;
		MySqlMessagesDao md = new MySqlMessagesDao();
		setMessageId(md.getMaxIdFromDb()+1);
		setRemovePriority();

	}
	 /**
	  * Constructor.
	 * @param nt message_id, int emp_id, Date me_date, String text,int pri, boolean done, Date ex_date
	 */
     public Mess(int message_id, int emp_id, Date me_date, String text,int pri, boolean done, Date ex_date){
    	 this.messageId= message_id;
    	 this.empId= emp_id;
    	 this.date=me_date;
    	 this.message= text;
    	 this.importance= pri;
    	 this.done=done;
    	 this.delDate= ex_date;
    	 setRemovePriority();
     }
     /**
      * Returns the message id.
      * @return int - the id number.
      */
	public int getMessageId() {
		return messageId;
	}
	/**
	 * Sets the message id.
	 * @param int - the message id number(mId).
	 */
	public void setMessageId(int mId){
		messageId = mId;
	}
	/**
	 * updates status of the message (if done).
	 */

	public void setDone() {
		done = true;
		
	}

	/**
	 *Sets publication's date.
	 *@param Date - message's publication date.
	 */

	public void setDate(Date newDate) {
		date = newDate;
	}

	/**
	 * Returns publication's date.
	 * @return Date - the publication date.
	 */
	public Date getDate() {
		return date;
	}

//	/**
//	 * @param newDone
//	 */
//	public void setDone(boolean newDone) {
//		done = newDone;
//	}

	/**
	 * gets the status of the message.
	 * @return boolean - the status.
	 */
	public boolean getDone() {
		return done;
	}

	/**
	 * The method sets the employee's id number.
	 *
	 * @param newId
	 */
	public void setId(int newId) {
		empId = newId;
	}

	/**
	 * The method returns the employee's id number.
	 *
	 * @return
	 */
	public int getId() {
		return empId;
	}

	/**
	 * Sets the delete date for the message.
	 * @param Date - newDeleteDate
	 */
	public void setDeleteDate(Date deleteDate) {
		delDate = deleteDate;
	}

	/**
	 *Returns the deleting date for the message.
	 * @return Date - delete date.
	 */
	public Date getDeleteDate() {
		return delDate;
	}

	/**
	 * sets the message priority level.
	 * @param  int - Importance
	 */
	public void setImportance(int setImportance) {
		importance = setImportance;
	}

	/**
	 *Returns the message priority level.
	 * @return int - the importance.
	 */
	public int getImportance() {
		return importance;
	}

	/**
	 *
	 * @param setMelding
	 */
	public void setMelding(String setMelding) {
		if (setMelding == null || setMelding.length() < 1) {
			message = "tom melding.";
			return;
		}
		message = setMelding;
	}

	/**
	 *Returns the text of the message.
	 * @return String - the text.
	 */
	public String getMelding() {
		return message;
	}
	/**
	 * Sets an out dated message to priority level 0.
	 * A message with 0 priority will not be published.
	 */
	private void setRemovePriority() {
	if (cal.getTime().after(delDate)|| cal.getTime().equals(delDate)){
		setImportance(0);
		}
	}

	/**
	 * The method prints the message toString representation.
	 */
	public void print() {
		System.out.println(toString());
	}

	public String toString() {
		String meldingString = "MeldingId: " + messageId + " SlettDato: "+ delDate +" LagetDato: " + date  + " Importance: "
				+ importance + " Melding: " + message + " Utført = " + done + "\n";
		return meldingString;
	}

}
