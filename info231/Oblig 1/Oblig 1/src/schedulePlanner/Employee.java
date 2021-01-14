package schedulePlanner;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * The class represents an employee entity.
 * Each employee holds a list of his/hers own shifts.
 * 
 * @author David Ronen
 * @version 01-02-11.
 *
 */
public class Employee implements Serializable{
	    private static int totalEmpsEver = 0;
	    private int empId;
	    private String name;
	    private ArrayList<Shift>myShifts;

	    /**
	     * Constructor for objects of class Employee
	     */
	    
	    public Employee(String name){
	        empId = totalEmpsEver +1;
	    	totalEmpsEver = empId;
	        this.name = name;
	        myShifts = new ArrayList<Shift>();
	        }
	    /**
	     * Constructor for employee objects - takes no arguments.	    
	     */
	    
	    public Employee(){
	    }
	    
	    /**
	      * The method adds a shift to the list of shifts for that particular employee.
	      * 
	      * @param newShift
	      * @return
	      */ 
        // Ask whether an Employee can work doble shift? Two shifts at the same date?
	    public boolean addShift(Shift newShift) {
	        for(Shift oldShift : myShifts){
	            if(oldShift.getDate() == newShift.getDate()&& oldShift.getStartTime() == newShift.getStartTime()){
	           return false;
	       }
	    }
	    myShifts.add(newShift);
	    return true;
	   }
	    
	    /**
	     * The method deletes a shift from the list of shifts of that particular employee.
	     * @param id - The employee's id number.
	     * @param date - The date of the shift to be deleted.
	     * @param startTime - The start time of the shift to be deleted.
	     */
	    public void deleteShiftFromEmployee(int id, String date, String startTime){
	    	Shift myShift = null;
	    		for (Iterator<Shift> it = myShifts.iterator(); it.hasNext();) {
	    		    Shift aShift = it.next();
	    		    	if(aShift.getId() == id && aShift.getDate().equalsIgnoreCase(date)&& aShift.getStartTime().equalsIgnoreCase(startTime)){
	    		    		myShift = aShift;
	    		    		it.remove();
	    		    		}
	    				}
	    		myShifts.remove(myShift);
	    }
	    
     /**
      * The method Sets the employee's name.
      * @param newName
      */
	    public void setName (String newName){
	        name = newName;
	    }
	    
    /**
     * The method returns the name of the employee.
     * @return - the employee's name.
     */
	    public String getName() {
	        return name;
	    }
	    
    /**
     * The method sets the employee's id number.
     * @param newId
     */
	    public void setId(int newId){
	    	empId = newId;
	    }
	    
    /**
     * The method returns the employee's id number.
     * @return
     */
	    public int getId(){
	    	return empId;
	    }
	    
    /**
     * The method prints the employee's toString representation.
     */
	    public void print() {
	        System.out.println(toString());
	    }
	    
	/**
	 * The method sets the total number of employees,
	 * New employees will be rgistered ascendingly. 
	 * @param inTotNum
	 */
	    public void setTotalEmps(int inTotNum){
	    	totalEmpsEver = inTotNum;
	    	}
	    
	/**
	 * The method returns the number of total employees.
	 * @return
	 */
	    public int getTotNum(){
	    	return totalEmpsEver;
	    }
	    
    /**
     * The method returns a string representation of the employee object.
     */
	    public String toString(){
	        String returnString = " Name: " + name + ". IDnumber: "+ empId + "\n";
	        returnString += " My shifts: \n";
	        for (Shift entry : myShifts) {
	            returnString += "Date: " + entry.getDate() +". \n" + 
	            "Start time: " + entry.getStartTime() + ". \n" +
	            "End time: " + entry.getEndTime() + ". \n" +
	            "Comment: " + entry.getComment() + ". \n";
	            }
	        return returnString;
	        }
	    }





