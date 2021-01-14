package schedulePlanner;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/**
 *The class represents a shift entity.
 *
 * @author David Ronen
 * @version 01-02-11.
 */



public class Shift implements Serializable {

	    private String date;
	    private String startTime;
	    private String endTime;
	    private Employee anEmployee;
	    private String comment;
	    private int anEmployeeId;

	    /**
	     * Constructor for objects of class Shift
	     */
	    public Shift(String date, String startTime, String endTime, Employee anEmployee, String comment){
	    		this.date = date;
	    		this.startTime = startTime;
	    		this.endTime = endTime;
	    		this.anEmployee = anEmployee;
	    		anEmployeeId = anEmployee.getId();
	    		setComment(comment);
	    		addShiftToMyShift();
	    		}
    /**
     * The method sets the date of the shift.
     * @param newDate
     */
	    public void setDate(String newDate) {
	        date = newDate;
	    }

	/**
	 * The method returns the date of the shift.
	 * @return - the date of the shift.
	 */
	    public String getDate() {
	        return date;
	    }

    /**
     * The method sets the start time of the shift.
     * @param newStartTime
     */
	    public void setStartTime(String newStartTime) {
	        startTime = newStartTime;
	    }

     /**
      * The method returns the start time of the shift.
      * @return
      */
	    public String getStartTime() {
	        return startTime;
	    }

	/**
	 * The method sets the end time for the shift.
	 * @param newEndTime
	 */
	    public void setEndTime(String newEndTime) {
	        endTime = newEndTime;
	    }

	/**
	 * The method returns the end time of the shift.
	 * @return - the end time of the shift.
	 */
	    public String getEndTime() {
	        return endTime;
	    }

	/**
	 * The method sets a comment if one is given by the user.
	 * The comment is optional. if no comment is given by the user, the default set is "No comment".
	 * @param innComment
	 */
	    public void setComment(String innComment){
	    	if (innComment == null || innComment.length()<1){
	    		comment = "Ingen kommentar.";
	    		return;
	    	}

	    	comment = innComment;
	    }

	/**
	 * The method returns the comment given by the user or  a default "No comment" set by the program..
	 * @return
	 */
	    public String getComment() {
	    	return comment;
	    }

	/**
	 * The method adds the employee that registered for that particular shift.
	 * @param name
	 */
	    public void addAnsatt(String name) {
	        anEmployee = new Employee(name);
	    }

	 /**
	  * the method returns a string representation of the shift object.
	  */
	    public String toString() {
	        String returnString = " Dato: "  + date +
	        " Starttid: " + startTime +
	        " Sluttid: " + endTime +
	        " Navn: " + anEmployee.getName() +
	        " Kommentar: " + comment + "\n" ;
	        return returnString;
	    }

	/**
	 * The method prints the toString representation of the shift object.
	 */
	    public void print(){
	    System.out.println(toString());
	    }

	 /**
	  * The method returns the id number of the employee that registered for that particular shift.
	  * @return the employee's id number.
	  */
	    public int getId(){
	    	return anEmployeeId;
	    }

	/**
	 * The method returns the name of the employee registered for that particular shift.
	 * @return
	 */
	    public String getName() {
	        String name = anEmployee.getName();
	        return name;
	    }

	/**
	 * The method adds the shift to the employee's list of his/hers own shifts -
	 * i.g. it invokes the addShift() method of the employee.
	 */
	    public void addShiftToMyShift() {
	        anEmployee.addShift(this);
	       }

	/**
	 * The method verifies that the shift's start time is earlier than it's end time.
	 * @param inStartTime - of the shift.
	 * @param inEndTime - of the shift.
	 * @return - true if end time comes after start time, false otherwise.
	 */
	    public boolean isLegalTime(String inStartTime, String inEndTime){
	    	String startTime = inStartTime;
	 	    String endTime = inEndTime;
	 	    DateFormat sdf = new SimpleDateFormat("HH:mm");
	 	    try {
	 	    	Date  startDate = sdf.parse(startTime);
	            Date  endDate = sdf.parse(endTime);
	            GregorianCalendar startGC = new GregorianCalendar();
	            startGC.setTime(startDate);
	            GregorianCalendar endGC = new GregorianCalendar();
	            endGC.setTime(endDate);
	            if(startGC.before(endGC)){
	         	  return true;
	         	  }
	            else{
	            	failedFeedBack("Sluttiden må være senere enn starttiden!");
	            	return false;
	            }
	 	    }
	 	    catch (Exception NumberFormatException) {
	 	    	failedFeedBack("Dato og tid må kun inneholde tall!\n"+
	 	    			"Format: Dato: dd-mm-yy. Tid: hh:mm.");
	 	    	//return false ;
	 	    	}
	 	    return false;
	 	    }


	/**
	 * The method sends a message if an operation failed.
	 * @param inMessage
	 */
       public void failedFeedBack(String inMessage){
    			String title = "Feil oppstod!";
    			String message = inMessage;
    			JOptionPane.showMessageDialog(null,message,title,
    					JOptionPane.OK_CANCEL_OPTION);
    			}
       }






