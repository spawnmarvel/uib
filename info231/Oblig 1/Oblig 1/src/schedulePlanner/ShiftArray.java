package schedulePlanner;


import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * The class holds - and provides procedures for manipulating and updating - an array of shifts.
 *  
 * @author dro068 - David Ronen.
 * @version 01-02-11.
 *
 */
public class ShiftArray implements Serializable{

	    private  ArrayList<Shift> atWork = new ArrayList<Shift>();

	    /**
	     * Constructor for objects of class ShiftArray
	     */
	    public ShiftArray(){
	    }
/**
 * Returns the size of the list of shifts i.g the number of shifts in the list.
 * The shifts are placed in 0 to n-1 position.  	    
 * @return - the size of the shifts list.
 */
	    public int getAtWorkSize(){
	    	return atWork.size();
	    }

	   /**
	    * The method adds a shift to the array by passing an Shift object as a parameter.
	    * @param aShift
	    */
	    public boolean addShift(Shift aShift){
	        for (Shift entry : atWork) {
	            if(entry.getDate().equalsIgnoreCase(aShift.getDate())&& 
	            		entry.getStartTime().equalsIgnoreCase(aShift.getStartTime()) && 
	            		entry.getId() == aShift.getId()){
	            	failedFeedBack("Vakt ble ikke registrert!");
	                return false;
	               }
	           }
	        atWork.add(aShift);
	        return true;
	    }

	    /**
	     * The method deletes a shift from the array if the employees name, his/hers ID number
	     * and the date of the shift matches the search terms.
	     * @param startTime
	     * @param date
	     * @param id
	     */
	    public boolean deleteShift(int id, String date, String startTime){
	        Iterator<Shift> itr = atWork.iterator();
	        while (itr.hasNext()){
	            Shift aShift = (Shift)itr.next();
	            if(aShift.getStartTime().equalsIgnoreCase(startTime) && aShift.getId() == id && aShift.getDate().equalsIgnoreCase(date)){
	            	atWork.remove(aShift);
	                return true;
	            }
	        }
	        failedFeedBack("Operasjon mislyktes!");
	        return false;
	    }
	    
	    /**
	     * This method gives an warning that an operation failed.
	     * @param feedBackText - the message given.
	     */
	    public void failedFeedBack(String feedBackText){
	    	String title = "Feil oppstod!";
			String message = feedBackText;
			JOptionPane.showMessageDialog(null,message,title,
					JOptionPane.OK_CANCEL_OPTION);
	    }

	    /**
	     * The method finds a particular shift by matching the date given by the user.
	     * @param date
	     * @return
	     */
	    public Shift findShift(String date){
	    	String searchString = date;
	    	String returnString ="Working today: \n";
	    	Iterator<Shift> it = atWork.iterator();
	    	while (it.hasNext()){
	    		Shift myShift = it.next();
	    		if (myShift.getDate() == searchString){
	    		    returnString += "Name: " + myShift.getName() + ". / IDnumber: " + myShift.getId() + ". / From: " + myShift.getStartTime() +
	    		    ". Until: " + myShift.getEndTime() + ".\n";
	    		    System.out.println(returnString);
	    		    return myShift;
	    		    }
	    		}
	    	return null;
	    	}
/**
 * The method returns the list of shifts.	    
 * @return - The list of shifts.
 */
	    public ArrayList<Shift> getList(){
	    	return atWork;
	    }

	    /**
	     * The method print the content of array by printing it's toString representation.
	     */
	    public void print(){
	        System.out.println(toString());
	    }
	    /**
	     * The method returns a String representation of the array.
	     */
	    public String toString() {
	        String returnString = "Shift list contains:\n";
	        for (Shift aShift : atWork){
	        returnString += "Name: " + aShift.getName() + ". / IDnumber: " + aShift.getId() + ". / Date: " + aShift.getDate() +
	        ". / From: " + aShift.getStartTime() + " Until: " + aShift.getEndTime() + ". / Comment: " + aShift.getComment() + ".\n";
	       }
	       return returnString;
	   }
}



