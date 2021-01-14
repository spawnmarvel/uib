package com.listeners;

import static org.junit.Assert.*;

import interfaces.EmployeeDAO;

import java.awt.event.ActionEvent;
import java.util.List;

import gui.DeleteEmployee;
import org.junit.Before;
import org.junit.Test;
import com.listeners.ListenerDeleteEmployee;

import control.Controller;

import dataAccsesObject.MySqlEmployeeDAO;
import entity.com.employee.Employee;


/**
 * @author Tommy
 *
 */
public class ListenerDeleteEmployeeTest {

	private static DeleteEmployee del;
	private static ListenerDeleteEmployee lisdel;
	
	/**
	 * Initial set up method for test class
	 *
	 */
	@Before
	public void setUp() throws Exception {
		del = new DeleteEmployee();
		lisdel = new ListenerDeleteEmployee(del);
		del.getFrame().setVisible(false);
	}
	
	/**
	 * Method for testing the deletion of an employee.
	 * Adds an employee, then deletes it.
	 */
	@Test
	public void testDeleteEmployeeActionEvent() throws Exception {
		Controller c = new Controller();
		c.AddNewEmp("Random");
	    EmployeeDAO m = MySqlEmployeeDAO.getInstance();
		List<Employee> l = m.getAll();
		Employee sisteE = l.get(l.size()-1);
		int id = sisteE.getId();
		String i = Integer.toString(id);
		del.setID(i);
		ActionEvent e = new ActionEvent(del.getButton(), 1, "Slett ansatt");
		lisdel.actionPerformed(e);
	    EmployeeDAO mm = MySqlEmployeeDAO.getInstance();
		List<Employee> ll = mm.getAll();
		Employee sisteEE = ll.get(ll.size()-1);
		assertTrue(id != sisteEE.getId() && !sisteEE.getName().equals("Random"));
	}

}
