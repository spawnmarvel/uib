package entity.com.employee;





/**
 * The class represents an employee entity.
 * Each employee holds a list of his/hers own shifts.
 * 
 * @author David Ronen(Ronen)
 * @version 01-02-11.
 *
 */
public class Employee {
	    private static int totalEmpsEver = 0;
	    private int empId;
	    private String name;
	   

	    /**
	     * Constructor for objects of class Employee
	     */
	    
	    public Employee(String name){
	        empId = totalEmpsEver +1;
	    	totalEmpsEver = empId;
	        this.name = name;

	        }
	    /**
	     * Constructor for employee objects - takes no arguments.	    
	     */
	    
	    public Employee(){
	    }
	    public Employee (String name, int id){
	    	this.name = name;
	    	this.empId = id;
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
	        return returnString;
	        }
	    }





