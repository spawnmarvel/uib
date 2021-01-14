package com.listeners;


import static org.junit.Assert.assertEquals;
import gui.ChangeShiftGui;

import interfaces.ShiftDAO;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;


import org.junit.Before;

import org.junit.Test;

import control.Controller;
import control.Parser;


import dataAccsesObject.MySqlShiftDAO;

import entity.com.shift.Shift;


/**
 * The test class for testing functionality of the ListenerChangeShift
 *
 * @author Hedvig & Aslak
 * modified by David & Espen
 *
 */

public class ListenerChangeShiftTest {



	private static Controller cont;
	private static ListenerChangeShift shiftCL;
	private static ChangeShiftGui cGui;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		cont = new Controller();
		cGui = new ChangeShiftGui();
		cGui.setInvisible();
		shiftCL = new ListenerChangeShift(cGui);
		cont.ScheduleNewShift("17-05-11", "08:00", "16:00", 2, "Test");
	}
	
	/**
	 * Tester om hvorvidt spinnerne fungerer skikkelig i ListenerChangeShift
	 */
	@Test 
	public void testActionEventSpinner() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		Date value = sdf.parse("17-05-11");
		cGui.getIdField().setValue(2);
		cGui.getDateField().setValue(value);
		
		Date sTime = Parser.stringTimeToSqlTime("08:00");
		cGui.getStartTimeField().setValue(sTime);
		
		Date newStartTime = Parser.stringTimeToSqlTime("16:00");
		cGui.getNewStartTimeField().setValue(newStartTime);
		
		Date newEndTime = Parser.stringTimeToSqlTime("23:59");
		cGui.getNewEndTimeField().setValue(newEndTime);
		cGui.getCommentArea().setText("Test2");
		
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

		
		ActionEvent e = new ActionEvent(cGui.getSubmit(), 1, "Endre");
		shiftCL.actionPerformed(e);
		ShiftDAO sDAO = MySqlShiftDAO.getInstance();
		
		ArrayList<Shift> shiftA = sDAO.getAll();
		
		Shift myShift = null;
		Shift aShift = new Shift("17-05-11", "16:00", "23:59", 2, "David r", "Test2");
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
