package com.listeners;


import static org.junit.Assert.assertEquals;
import interfaces.ShiftDAO;


import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JOptionPane;

import gui.CreateShiftGui;



import org.junit.Before;
import org.junit.Test;

import control.Controller;
import control.Parser;
import dataAccsesObject.MySqlShiftDAO;
import entity.com.shift.Shift;


/**
 * The test class for testing functionality of the ListenerCreateShift class.
 *
 * @author Hedvig & Aslak
 *
 */

public class ListenerCreateShiftTest {
	
	private static Controller cont;
	private static ListenerCreateShift shiftL;
	private static CreateShiftGui sGui;
	
	
	/**
	 * Sets up the Controller, CreateShiftGui and ListenerCreateShift.
	 * Sets the CreateShiftGui frame to invisible.
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		cont = new Controller();
		sGui = new CreateShiftGui();
		sGui.setInvisible();
		shiftL = new ListenerCreateShift(sGui);
	}
	/**
	 * Tests if the spinners work as they should in the ListenerCreateShift class.
	 */
	@Test 
	public void testActionEventSpinner() throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		Date value = sdf.parse("18-05-11");
		sGui.getDateSpinner().setValue(value);
		
		sGui.getIDSpinner().setValue(2);
		
		Date sTime = Parser.stringTimeToSqlTime("08:00");
		sGui.getSSpinner().setValue(sTime);
		
		Date eTime = Parser.stringTimeToSqlTime("16:00");
		sGui.getESpinner().setValue(eTime);
		
		sGui.getCommentArea().setText("Test");

		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					sleep(50);
				} catch (InterruptedException e) {
				}
				JOptionPane.getRootFrame().dispose();
			}
		};
		t.start();

		
		ActionEvent e = new ActionEvent(sGui.getSubmit(), 1, "Legg til");
		shiftL.actionPerformed(e);
		
		ShiftDAO sDAO = MySqlShiftDAO.getInstance();
		ArrayList<Shift> shiftA = sDAO.getAll();
		
		Shift myShift = null;
		Shift aShift = new Shift("18-05-11", "08:00", "16:00", 2, "David r", "Test");
		for (Shift entry : shiftA){
			if (aShift.getId()== entry.getId() && aShift.getStartTime().equalsIgnoreCase(entry.getStartTime()) && aShift.getDate().equalsIgnoreCase(entry.getDate())){
				myShift = entry;
			}
		}
		
		String expected = "Test";
		String actual = myShift.getComment();
		assertEquals (expected, actual);
		cont.deleteShift(aShift.getId(), aShift.getDate(),aShift.getStartTime());
		
	}
	
	/**
	 * Testes if the radioButtons work as they should in the ListenerCreateShift class.
	 */
	@Test 
	public void testActionEventRadioButton() throws ParseException{
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		Date value = sdf.parse("19-05-11");
		sGui.getDateSpinner().setValue(value);
		
		sGui.getIDSpinner().setValue(2);
		
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					sleep(50);
				} catch (InterruptedException e) {
				}
				JOptionPane.getRootFrame().dispose();
			}
		};
		t.start();
		
		sGui.getTo().setSelected(true);
		ActionEvent e1 = new ActionEvent(sGui.getTo(), 1, "");
		shiftL.actionPerformed(e1);
		// Since the data is collected from the spinners an actionPerformed is required to extract the data to this test.
		
		sGui.getFem().setSelected(true);
		ActionEvent e12 = new ActionEvent(sGui.getFem(), 1, "");
		shiftL.actionPerformed(e12);
		//Since the data is collected from the spinners an actionPerformed is required to extract the data to this test.
		
		sGui.getCommentArea().setText("Test2");
		
		ActionEvent e = new ActionEvent(sGui.getSubmit(), 1, "Legg til");
		shiftL.actionPerformed(e);
		
		ShiftDAO sDAO = MySqlShiftDAO.getInstance();
		ArrayList<Shift> shiftA = sDAO.getAll();
		
		Shift myShift = null;
		Shift aShift = new Shift("19-05-11", "08:00", "16:00", 2, "David r", "Test2");
		for (Shift entry : shiftA){
			if (aShift.getId()== entry.getId() && aShift.getStartTime().equalsIgnoreCase(entry.getStartTime()) && aShift.getDate().equalsIgnoreCase(entry.getDate())){
				myShift = entry;
			}
		}
		
		String expected = "Test2";
		String actual = myShift.getComment();
		assertEquals (expected, actual);
		cont.deleteShift(aShift.getId(), aShift.getDate(),aShift.getStartTime());
		
	}

}
