package com.allTest;

import junit.framework.Test;


import junit.framework.TestSuite;



//import junit.framework.Test;
//import junit.framework.TestSuite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.listeners.ListenerChangeShiftTest;
import com.listeners.ListenerCreateShiftTest;
import com.listeners.ListenerDeleteEmployeeTest;
import com.listeners.MessageListenerTest;

import control.ControllerTest;
import control.DateUtilTest;
import control.ParserTest;
import dataAccsesObject.MySqlDAOFactoryTest;

import entity.com.employee.EmployeeArrayTest;
import entity.com.messages.MessageArrayDTest;
import entity.com.messages.MessagePublicationTest;
import entity.com.shift.ShiftArrayTest;
import entity.com.shift.ShiftTest;
import gui.AddEmployeeTest;




	@RunWith(Suite.class)
	@Suite.SuiteClasses( {
		ShiftTest.class,
		ShiftArrayTest.class,
		ParserTest.class,
		DateUtilTest.class,
		AddEmployeeTest.class,
		ListenerChangeShiftTest.class,
		ControllerTest.class,
		MessageListenerTest.class,
		ListenerCreateShiftTest.class,
		MySqlDAOFactoryTest.class,
		ListenerDeleteEmployeeTest.class,
		MessagePublicationTest.class,
		MessageArrayDTest.class,
		EmployeeArrayTest.class})

	public class AllTests {


  @BeforeClass
  public static void setUp(){
  }

  @AfterClass
  public static void tearDown(){
	  
  }
  }


