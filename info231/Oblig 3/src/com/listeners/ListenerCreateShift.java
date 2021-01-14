package com.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import gui.CreateShiftGui;
import control.Controller;
import control.DateUtil;
import control.FeedbackHandler;
import entity.com.shift.Shift;

/**
 * The class for functionality to the Create Shift Gui
 *
 * @author David Ronen.
 * @Version 30.01.11
 * modified by Tommy
 */

public class ListenerCreateShift implements ActionListener {

	private CreateShiftGui cs;

	public ListenerCreateShift(CreateShiftGui csg){
		this.cs = csg;
	}

	/**
	 * Class based on original made by David Ronen
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cs.getSubmit()){
			Controller c = new Controller();
			int id = (Integer)(cs.getIDSpinner().getValue());
			if (c.findEmployee(id)){
				SimpleDateFormat dfd = new SimpleDateFormat("dd-MM-yy");
				Date date = (Date) cs.getDateSpinner().getValue();
				String dateString = dfd.format(date);
				DateUtil u = new DateUtil();
				if(u.isLegalTime(dateString)){
					
					SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
					Date sTime = (Date) cs.getSSpinner().getValue();
					String sTimeString = dft.format(sTime);
					Date eTime = (Date) cs.getESpinner().getValue();
					String endTime = dft.format(eTime);
					String comment = cs.getCommentArea().getText();
					Shift s = new Shift();
					if(s.isLegalTime(sTimeString, endTime)){
						Shift aShift = new Shift(dateString, sTimeString, endTime, id,null, comment);
						if(isUnique(aShift)){
					//}
					c.ScheduleNewShift(dateString, sTimeString, endTime, id, comment);
					FeedbackHandler.successfulFeedBack("Nytt skift ble lagt til!");
					cs.getCommentArea().setText("");
				}
					}
					else {FeedbackHandler.failedFeedback("Sluttiden må være senere enn starttiden!");
			}
			}
			else {}
		}
		}
		String [] sA = {"01:00","08:00", "16:00", "23:59"};
		if (e.getSource() == cs.getEn()) {
			String dateString = sA[0];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				cs.getSSpinner().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 

		}
		if (e.getSource() == cs.getTo()) {
			String dateString = sA[1];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				cs.getSSpinner().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == cs.getTre()) {
			String dateString = sA[2];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				cs.getSSpinner().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == cs.getFire()) {
			String dateString = sA[1];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				cs.getESpinner().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == cs.getFem()) {
			String dateString = sA[2];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				cs.getESpinner().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == cs.getSeks()) {
			String dateString = sA[3];
			SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
			try {
				Date convertedDate = dft.parse(dateString);
				cs.getESpinner().setValue(convertedDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

	}
	/**
	 * 
	 */
	public boolean isUnique(Shift aShift){
		Controller c = new Controller();
		ArrayList<Shift> testUnique = c.getShiftArr().getList();
        for (Shift entry : testUnique) {
            if(entry.getDate().equalsIgnoreCase(aShift.getDate())&& 
            		entry.getStartTime().equalsIgnoreCase(aShift.getStartTime()) && 
            		entry.getId() == aShift.getId()){
            	FeedbackHandler.failedFeedback("Vakt ble ikke registrert!");
                return false;
                }
        }
        return true;
	}
}
