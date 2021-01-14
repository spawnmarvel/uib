package interfaces;

import java.util.ArrayList;
/**
 * This class is an Interface that defines the methods required to implement 
 * data access for shift object from and to data base table.
 * @ author David, Espen.
 * @ version 1. 
 */

import entity.com.shift.Shift;

public interface ShiftDAO {
	
	
	/**
	 * This method accepts a shift object as an argument and transforms the object's fields 
	 * into data to store in data base table.
	 * @param aShift
	 */
	public void newShift(Shift aShift);
	/**
	 * The method collects the data stored in the database initiates objects and
	 * insert those into an array of objects.
	 * The method returns an array containing objects of the specific type.
	 * @return
	 */
	public ArrayList<Shift> getAll();
	/**
	 * The method accepts a shift object as an argument, and 
	 * deletes  the row containing this object's data from the database.
	 * @param aShift
	 */
	public void deletShift(Shift aShift);
	
	/**
	 * The method replaces data stored for one (oldShift) with new data (newShift). 
	 * @param oldShift
	 * @param newShift
	 */
	public void updateShift(Shift oldShift, Shift newShift);
	
	
	
	

}
