package com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import gui.ChangeShiftGui;
import control.Controller;
import control.DateUtil;
import control.FeedbackHandler;

/**
 * The class for functionality to the Change Shift Gui
 *
 * @author David Ronen.
 * @Version 30.01.11
 * modified by Tommy
 */

public class ListenerChangeShift implements ActionListener {

	private ChangeShiftGui chs;

	public ListenerChangeShift(ChangeShiftGui changeShiftGui) {
		this.chs = changeShiftGui;	
	}
	/**
	 * The method controls the execution of user commands.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chs.getSubmit()){
			Controller c = new Controller();
			int id = (Integer)(chs.getIdField().getValue());
			if( c.findEmployee(id)) {

			}
			SimpleDateFormat dfd = new SimpleDateFormat("dd-MM-yy");

			Date date = (Date) chs.getDateField().getValue();
			String dateString = dfd.format(date);

			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			Date sTime = (Date) chs.getStartTimeField().getValue();
			String oStartTime = dft.format(sTime);
			Date newDate = (Date) chs.getDateField().getValue();
			String nDate = dfd.format(newDate);

			DateUtil u = new DateUtil();
			if(u.isLegalTime(nDate)){

				Date thisNewStartTime = (Date) chs.getNewStartTimeField().getValue();
				String nStartTime = dft.format(thisNewStartTime);
				Date thisNewEndTime = (Date) chs.getNewEndTimeField().getValue();
				String eTime = dft.format(thisNewEndTime);
				String com = chs.getCommentArea().getText();
				c.reSchedulShift(id, dateString, oStartTime, nDate, nStartTime, eTime, com);
				//FeedbackHandler is removed for test purpose.
				//FeedbackHandler.successfulFeedBack("Endring fullført");
				chs.getCommentArea().setText("");
			}
			else{}
		}

		if(e.getSource() == chs.getCancel()){
			chs.getFrame().dispose();
		}
		String [] sA = {"01:00","08:00", "16:00", "23:59"};
		if (e.getSource() == chs.get_En()) {
			String dateString = sA[0];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getStartTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 

		}

		if (e.getSource() == chs.get_To()) {
			String dateString = sA[1];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getStartTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == chs.get_Tre()) {
			String dateString = sA[2];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getStartTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}

		if (e.getSource() == chs.getEn()) {
			String dateString = sA[0];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getNewStartTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}

		if (e.getSource() == chs.getTo()) {
			String dateString = sA[1];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getNewStartTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}
		if (e.getSource() == chs.getTre()) {
			String dateString = sA[2];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getNewStartTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}
		if (e.getSource() == chs.getFire()) {
			String dateString = sA[1];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getNewEndTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}
		if (e.getSource() == chs.getFem()) {
			String dateString = sA[2];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getNewEndTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}
		if (e.getSource() == chs.getSeks()) {
			String dateString = sA[3];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				chs.getNewEndTimeField().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		}
	}

}
