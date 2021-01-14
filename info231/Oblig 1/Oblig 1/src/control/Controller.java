package control;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import schedulePlanner.Employee;
import schedulePlanner.EmployeeArray;
import schedulePlanner.Shift;
import schedulePlanner.ShiftArray;

/**
 * The class controls updating, saving and presenting of shift and employee registry.
 *
 * @author David Ronen.
 * @version 10-02-11.
 *
 */

public class Controller {
    private EmployeeArray empArr;
    private Employee employee;
    private Shift shift;
    private ShiftArray shiftArr;

/**
 * Constructor for Controller object.
 */
    public Controller() {
    	EmployeeArray empArr = new EmployeeArray();
    	ShiftArray shiftArr = new ShiftArray();
    	loadObject(empArr);
     	loadObject(shiftArr);
     	initEmployee();
    }
/**
 * The method save updated shift and employee lists to file.
 * @param o - the object to be written, Employee list or Shift list.
 * @param path - to the particular file.
 */
    public void saveObjectToFile(Object o, String path){
    	IOHandler ioh = new IOHandler();
    	ioh.saveToFile(o, path );
    	System.out.println("Fil lagret");
    }

  /**
   * The method assigns the newly instantiated (EmployeeArray)empArr and (ShiftArray)shiftArr objects
   * to the objects (shiftArr and empArr) that were saved on files.
   * @param o - (ShiftArray)shiftArr, (EmployeeArray)empArr, to be assigned.
   */
    public void loadObject(Object o){
       	IOHandler ioh = new IOHandler();
       	if(o instanceof EmployeeArray){
       		empArr = (EmployeeArray) ioh.readFromFile(o,"EmployeeArray.obj" );
       		}
       	else if(o instanceof ShiftArray){
       		shiftArr = (ShiftArray) ioh.readFromFile(o, "ShiftArray.obj");
       		}
       	}
/**
 * The method initiates an employee entity and sets the number of employees to the actual number.
 * New employees will be registered with ascending numbers accordingly.
 */
    public void initEmployee(){
    	employee = new Employee();
    	int num = 0;
    	for (int i = 0; i < empArr.getStaffSize();i++){
    		int id = empArr.getList().get(i).getId();
    		if (id > num){
    			num = id;
    		}
    	}
    	employee.setTotalEmps(num);
    }

/**
 * An inquiry with the costumer verified that stake holders would like to see a text presentation of files content.
 * This method writes the list of shifts and of employees to a text file to enable reading.
 *
 * @param innArray - the list of employees or shifts with those employees who will be attending each shift.
 */
    public void saveArrayToTextFile(Object innArray, String path){
    	IOHandler ioh = new IOHandler();
    	ioh.writeToFile(innArray, path);
    }
    /**
     * This method performs the reschedule shift routine.
     * @param id - Employee's ID number.
     * @param oldDate - The date of the shift that will be rescheduled.
     * @param oldStartTime - the start time of the shift that will be rescheduled.
     * @param newDate - the date of the new shift to be scheduled.
     * @param newStartTime - the start time of the new shift to be scheduled.
     * @param newEndTime - the end time of the new shift to be scheduled.
     * @param comment - an optional comment. (null will set the default: "No comment".)
     */
    public void reSchedulShift(int id, String oldDate, String oldStartTime, String newDate, String newStartTime, String newEndTime, String comment){
    	if(empArr.isEmp(id)){
    		Employee emp = empArr.findEmployee(id);
    		Shift newShift = new Shift(newDate, newStartTime, newEndTime, emp, comment);
    		if(newShift.isLegalTime(newStartTime, newEndTime)){
    			if (shiftArr.deleteShift(id, oldDate, oldStartTime)){
    				saveArrayToTextFile(empArr, "Employee_List.txt");
    				if (shiftArr.addShift(newShift)){
    					successfulFeedBack("Endring fullført!");
    					saveUpDates();
    	    			}
    	    		}
    	    	}
    		}
    	}

/**
 * The method controls the deleting of a shift procedure.
 * @param id - The id number of the employee who's shift is to be deleted.
 * @param date - The date of the shift to be deleted.
 * @param startTime - The start time of the shift to be deleted.
 */
    public void deleteShift(int id, String date, String startTime){
    	if (shiftArr.deleteShift(id, date, startTime)){
    		saveUpDates();
    		successfulFeedBack("Sletting fullført!");
    	}
    }
    /**
     * The method gives a positive feed back when an operation is successful.
     * @param feedBackText
     */
    public void successfulFeedBack(String feedBackText) {
		   String title = "Operasjon vellykket";
		   String message = feedBackText;
		   JOptionPane.showMessageDialog(null,message,title,
				   JOptionPane.OK_CANCEL_OPTION);
    }

    /**
     * This method controls the adding of a new employee to the array of employees.
     * @param name - the name of the new employee. (ID number will be automatically given by the system.
     */
     public void AddNewEmp(String name){
    	 employee = new Employee(name);
    	 if(empArr.addEmployee(employee)){
    		 successfulFeedBack("Ansatt ble lagt til!\n" +
    				 "Navn: "+ employee.getName() + ". ID-nummer: " + employee.getId());
    		 System.out.println("EMPARR: "+empArr.toString());
    		 saveUpDates();
    		 }
    	 }
     /**
      * The method deletes an employee from the list of employees and his scheduled shifts from shift list.
      * @param id
      */
     public void deleteEmp(int id){
    	 Shift myShift = null;
    	 if(empArr.deleteEmployee(id)){
    		 Iterator<Shift> itr = shiftArr.getList().iterator();
    		 while(itr.hasNext()){
    			 myShift = itr.next();
    			 if(myShift.getId() == id){
    				 itr.remove();
    				 shiftArr.getList().remove(myShift);
    				 }
    			 }
    	     successfulFeedBack("Ansatt med ID-nummer " + id + " slettet!");
    	     saveUpDates();
    	 }
     }
     /**
      * This method controls the scheduling of a new shift.
      * @param date - the date of the shift.
      * @param startTime - the start time of the shift.
      * @param endTime - the end time of the shift.
      * @param id - of the employee who will be attending the shift.
      * @param comment - optional. (null will set the default: "No comment".)
      */
     public void ScheduleNewShift(String date, String startTime, String endTime, int id, String comment){
    	 employee = empArr.findEmployee(id);
    	 shift = new Shift(date, startTime, endTime, employee, comment);
    	 if(shift.isLegalTime(startTime, endTime)){
    		 if (shiftArr.addShift(shift)){
    			 successfulFeedBack("Nytt skift ble lagt til!");
    			 saveUpDates();
    			 }
    		 }
    	 }
/**
 * The method is invoked by different procedures to ensure saving updates to files.
 */
     public void saveUpDates(){
		 saveObjectToFile(shiftArr, "ShiftArray.obj");
		 saveArrayToTextFile(shiftArr, "Shift_List.txt");
		 saveObjectToFile(empArr, "EmployeeArray.obj");
		 saveArrayToTextFile(empArr, "Employee_List.txt");
     }
/**
 * The method invokes the appendText method of the main screen
 * with the text requested by the user to be presented on the screen - in this
 * case the shift list for a given id number or a given date.
 * @param id
 * @return
 */
     public String showShiftPrIdOrDate(Object o){
    	 String myString = "";
    	 int myId = 0;
    	 int count = 0;
    	 String s = "";
    	 ArrayList<Shift >shiftList = shiftArr.getList();
    	 for (int i = 0; i < shiftList.size(); i++){
    		 Shift myShift = shiftList.get(i);
    		 if (o instanceof Integer){
    			 myId = (Integer) o;
    			 if(myShift.getId()== myId){
    			 count +=1;
    			 s += myShift.toString();
    			 }
    		 }
    		 else if(o instanceof String){
    			 myString = (String) o;
    			 if(myShift.getDate().equalsIgnoreCase(myString)){
    				 count +=1;
    				 s += myShift.toString();
    				 }
    			 }
    		 }
    	 if (count != 0){
    		 return s;
    		 }
    	 if (count == 0){
    		 String title = "Operasjon mislyktes!";
    		 String message = "Ingen vakt registrert med angitt data!";
    		 JOptionPane.showMessageDialog(null,message,title,
    				 JOptionPane.OK_CANCEL_OPTION);
    		 }
    	 return null;
     }
}
