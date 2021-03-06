package schedulePlanner;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * The class holds - and provides procedures to manipulate - the list of employees.
 *   
 * @author David Ronen.
 * @version 01-02-11.
 *
 */

public class EmployeeArray implements Serializable {
	//private static int totalEmpsEver = 1;
	private  ArrayList<Employee> staff;

    /**
     * Constructor for objects of class EmployeeArray
     */
    public EmployeeArray ()
    {
       staff = new ArrayList<Employee>();
    }
    
    /**
     * The method returns the size of the list of employees.
     * @return
     */

    public int getStaffSize(){
    	return staff.size();
    }
   /**
    * The method adds an employee to the list by receiving an employee object as an argument..
    * @param anEmployee
    * @return - true if the employee was successfully added, false otherwise.
    */
    public boolean addEmployee(Employee anEmployee){
        for(Employee entry : staff){
            if (entry.getName().equalsIgnoreCase(anEmployee.getName())){
                int n = JOptionPane.showConfirmDialog(null,
                        "En ansatt " + anEmployee.getName() + " finnes i systemet" + "\n" +
                        "Fortsette?", "Fortsette?", JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.YES_OPTION) {
                        	staff.add(anEmployee);
                        	return true;
                        }

                        else if(n == JOptionPane.NO_OPTION) {
                        	return false;
                        	}
                        }
            }
        staff.add(anEmployee);
        return true;
        }
    
    /**
     * The method adds an employee to the list by receiving the employee's name and id number as arguments..
     * @param name - of the employee.
     * @param id - of the employee.
     * @return - true if the employee was successfully added, false otherwise.
     */
    public boolean addEmployee(String name, int id){
        for (Employee anEmployee : staff){
            if (anEmployee.getId()== id){
                return false;
            }
        }
        Employee myEmployee = new Employee(name);
        staff.add(myEmployee);
        return true;
    }
    
   /**
    * The method finds an employee in the list by matching the id number given
    * by the user to a matching number in the list.
    * @param id
    * @return - the employee object if a match was made.
    */
    public Employee findEmployee(int id){
        int searchInt = id;
        for (int i = 0; i < staff.size(); i++){
            Employee anEmployee = staff.get(i);
            if (anEmployee.getId() == searchInt){
                return anEmployee;
            }
        }
        return null;
    }
    
   /**
    * The method verifies that a given id number - i.g an employee - actually exists in the list of employees. 
    * @param empId - the number to be verified.
    * @return - true if such a number exists in the list, false otherwise.
    */
    public boolean isEmp(int empId){
    	for (Employee entry : staff){
    		if(entry.getId()== empId){
    			return true;
    			}
    		}
    	failedFeedBack("IDnummeret finnes ikke i systemet!");
    	return false;
    	}

    /**
     * The method deletes an employee who's id number was entered by the user.
     * @param empId- the id number of the employee to be deleted. 
     * @return - true if the delete operation was successful, false otherwise.
     */

    public boolean deleteEmployee(int empId){
    	if(isEmp(empId)){
        Iterator<Employee> it = staff.iterator();
        while (it.hasNext()){
            Employee anEmployee = (Employee)it.next();
            if(anEmployee.getId() == empId){
            	Employee myEmp = anEmployee;
                staff.remove(myEmp);
                return true;
            }
            }
        }
        failedFeedBack("Sletting mislyktes!");
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
     * The method returns the list of employees.
     * @return - the list of employees.
     */
    public ArrayList<Employee>getList(){
    	return staff;
    }
    
    /**
     * The method deletes a shift from the list of shifts for that particular employee.
     * @param id - of the employee.
     * @param date - of the shift to be deleted.
     * @param startTime - of the shift to be deleted.
     */
    public void deleteShiftFromEmp(int id, String date, String startTime){
		for (Iterator<Employee> it = staff.iterator(); it.hasNext();) {
		    Employee emp = it.next();
		    emp.deleteShiftFromEmployee(id, date, startTime);
		    }
		}
    /**
     * the method prints the toString representation of the object.
     */
    public void print() {
        System.out.println(toString());
    }
    
    /**
     * The method returns a string representation of the object.
     */
    public String toString(){
        String returnString = "Staff members: \n";
        for(Employee entry : staff){
            returnString += "Name: " + entry.getName() + ". IDnumber: " + entry.getId() + ". \n";
        }
        return returnString;
    }

}

