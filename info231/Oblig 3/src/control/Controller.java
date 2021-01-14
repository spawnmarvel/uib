
package control;

import interfaces.MessagesDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.swing.JOptionPane;

import dataAccsesObject.MySqlDAOFactory;
import dataAccsesObject.MySqlEmployeeDAO;
import dataAccsesObject.MySqlMessagesDao;
import dataAccsesObject.MySqlShiftDAO;
import entity.com.employee.Employee;
import entity.com.employee.EmployeeArray;
import entity.com.shift.Shift;
import entity.com.shift.ShiftArray;


import java.util.Date;

import entity.com.messages.Mess;
import entity.com.messages.MessageArray;
import entity.com.messages.MessageArrayD;



/**
 * The class controls updating, saving and presenting of shift and employee registry.
 *
 * @author David Ronen.
 * @version 10-02-11.
 *
 */public class Controller {
	 private EmployeeArray empArr;
	 private Employee employee;
	 private Shift shift;
	 private ShiftArray shiftArr;
	 private Mess message;
	 private MessageArray messArr;
	 private MySqlDAOFactory msdf;
	 MySqlEmployeeDAO empDAO ;
	 MySqlShiftDAO shiftDAO;
	 MySqlMessagesDao messageDAO;
	 private MessageArrayD messArray;
	 private String objectStatus;

	 /**
	  * Constructor for Controller object.
	  */
	 public Controller() {
		 msdf = new MySqlDAOFactory();
		 empDAO = msdf.getEmployeeDAO();
		 shiftDAO = msdf.getShiftDao();
		 messageDAO= (MySqlMessagesDao) msdf.getMesagesDAO();
		 empArr = new EmployeeArray();
		 shiftArr = new ShiftArray();
		 messArr = new MessageArray();
		 empArr.setList(empDAO.getAll());
		 shiftArr.setList(shiftDAO.getAll());
		 messageDAO = (MySqlMessagesDao) msdf.getMesagesDAO();
		 initEmployee();
		 messArray= messageDAO.getAll();
	 }
	 /**
	  * Constructor for testing. All initializations are removed for independence. 
	  */
	 public Controller(String status){
		 objectStatus = status;
		 
	 }
	 

	 /**
	  * Assigns DAO object.  
	  */
	 public void setDao(Object innObject){
		 if (innObject instanceof MySqlMessagesDao){
			 messageDAO = (MySqlMessagesDao) innObject; 
		 }
	 }
	 /**
	  * This method get the specific messages to update
	  * @param int - messageID
	  * @return String- the messages
	  */
	 public String appendMessageToRediger(int messageID){
		 String mTr = messageDAO.getSpesificMess(messageID);
		 if(mTr == null){

		 }
		 return mTr;

	 }
	 /**
	  * this method takes an old messages and a new messages and passes it to the dao 
	  * @param oldMess
	  * @param newMess
	  * @return boolean - true if updated succeeded , false otherwise
	  */
	 public boolean upDateMessages(Mess oldMess, Mess newMess) {
		 return messageDAO.updateStatus(oldMess, newMess);
	 }

	 /**
	  * Saves a message to database
	  *
	  * @param delDate
	  * @param date
	  * @param importance
	  * @param melding
	  * @param empId
	  * @param done
	  */
	 public void saveMessage(Mess aMessage){

		 if(messArr.addMessage(aMessage)){
			 messageDAO.newMessage(aMessage);
		 }
	 }
	 /**
	  * returns an array of the messages for a given date
	  * @param Date date
	  * @return array - messages
	  */
	 public MessageArrayD publishByDate(Date date){
		 MessageArrayD mbd=  messageDAO.getMessageArrayByDate(date);
		 return mbd;
	 }
	 /**
	  * method to show all the employees in the database stored at the moment
	  * @return String Employee details
	  */
	 public String showAllEmp() {
		 String s = "";
		 for(Employee e : empArr.getList()) {
			  s+= e.toString();
			 
		 }
		 return s+"\n";
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
			 Shift newShift = new Shift(newDate, newStartTime, newEndTime, emp.getId(), emp.getName(), comment);
			 Shift oldShift = new Shift(oldDate, oldStartTime, null, emp.getId(), emp.getName(), comment);
			 if(newShift.isLegalTime(newStartTime, newEndTime)){
				 if (shiftArr.deleteShift(id, oldDate, oldStartTime)){
					 if (shiftArr.addShift(newShift)){
						 shiftDAO.updateShift(oldShift, newShift);
						 

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
			 Shift shiftToDelete = new Shift(date,startTime,null,id,null,null);
			 shiftDAO.deletShift(shiftToDelete);

		 }
	 }
	 /**
	  * This method controls the adding of a new employee to the array of employees.
	  * @param name - the name of the new employee. (ID number will be automatically given by the system.
	  */
	 public void AddNewEmp(String name){
		 employee = new Employee(name);
		 if(empArr.addEmployee(employee)){
			 empDAO.setName(employee);
		 }
	 }
	 /**
	  * The method deletes an employee from the list of employees and his scheduled shifts from shift list.
	  * @param id
	  */
	 public void deleteEmp(int id){
		 Shift myShift = null;
		 Employee myEmployee = empArr.findEmployee(id);
		 if(empArr.deleteEmployee(id)){
			 Iterator<Shift> itr = shiftArr.getList().iterator();
			 while(itr.hasNext()){
				 myShift = itr.next();
				 if(myShift.getId() == id){
					 itr.remove();
					 shiftArr.getList().remove(myShift);
					 shiftDAO.deletShift(myShift);
				 }
			 }
			 empDAO.deletEmployee(myEmployee);
			 
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
		 shift = new Shift(date, startTime, endTime, employee.getId(), employee.getName(), comment);
			 if (shiftArr.addShift(shift)){
				 shiftDAO.newShift(shift);
			 }
		 }
	 /**
	  * this method finds an employee by a given id
	  * @param int - id
	  * @return boolean- true if employee exists, false if not
	  */
	 public boolean findEmployee(int id){
		 return empArr.isEmp(id);

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
		 ArrayList<Shift>shiftList = shiftArr.getList();
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
			 return null;
		 }
		 return null;
	 }
	 /**
	  * Method returning name of an employee based on ID
	  * @param id - employee id
	  * @return a - employee name
	  */
	 public String getEmployee(int i){

		 Employee e = empArr.findEmployee(i);

		 if(e == null){
			 return "ansatt med ID " + i;
		 }else{
			 String a = e.getName().toString();
			 return a;
		 }
	 }
	 /**
	  * Assigns shiftArray object.
	  */
	 public void setShiftArray(ShiftArray innList){
		 shiftArr = innList;
	 }
	 /**
	  * Returns the shiftArray object.
	  */
	 public ShiftArray getShiftArr(){
		 return shiftArr;
	 }
	 /**
	  * Assigns employeeArray object.
	  */
	 public void setEmpArr(EmployeeArray innEmpArr){
		 empArr = innEmpArr;
	 }
 }