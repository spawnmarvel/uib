package com.listeners;


import static org.junit.Assert.assertEquals;
import interfaces.MessagesDAO;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import gui.MessageGui;

import org.junit.BeforeClass;
import org.junit.Test;

import control.Controller;
import control.Parser;
import dataAccsesObject.MySqlMessagesDao;
import entity.com.messages.MessageArrayD;

/**
 * This class is testing functionalities in MessageListener class. 
 * @author dro068
 *
 */

public class MessageListenerTest {
	private static Controller cont;
	private static MessageListener messL;
	private static MessageGui mGui;
/**
 * Initializes components that will be used in the test process.
 * @throws Exception
 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cont = new Controller();
		mGui = new MessageGui();
		mGui.getFrame().setVisible(false);
		messL = new MessageListener(mGui);
	}
	/**
	 * Assigns data and testing the method with the data.
	 * @throws ParseException
	 */
	@Test public void testMessageActionEvent() throws ParseException {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Date value = new Date();//sdf.parse("12/12/12");
		mGui.getDatoSpinn().setValue(value);
		mGui.getId().setText("2");
		mGui.getEnDag().setSelected(true);
		mGui.getLav().setSelected(true);
		mGui.getArea().setText("TEST");
		mGui.getFerdig().setSelected(false);
		ActionEvent e = new ActionEvent(mGui.getLagre(), 1, "Lagre");
		messL.actionPerformed(e);
		MessagesDAO mDAO = MySqlMessagesDao.getInstance();
		MessageArrayD mAd = mDAO.getAll();
		int mId = mAd.getLast();
		String expected = "TEST";
		String actual = mAd.getList()[mId].getMelding();
		assertEquals(expected, actual);
	}

}
