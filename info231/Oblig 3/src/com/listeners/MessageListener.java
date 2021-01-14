package com.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

import control.*;
import entity.com.messages.Mess;

import gui.MessageGui;

/**
 * Class for functionality to MessageGUI
 * @author Hedvig, Aslak, Tommy
 * modified by dro068, jkl070
 *
 */


public class MessageListener implements ActionListener{

	MessageGui mgui;
	int id;
	int mid;



	/**
	 * The constructor
	 */
	public MessageListener(MessageGui mgui){

		this.mgui = mgui;
	}

	/**
	 * Checks if the message contains a message text.
	 */
	public boolean checkId(){
		if(mgui.getId() != null){
			return true;
		}
		else{
			control.FeedbackHandler.failedFeedback("Det må skrives inn ansattid i meldingen.");
			return false;
		}

	}

	/**
	 * Checks if the message contains a message text.
	 */
	public boolean checkText(){
		if(mgui.getText() != ""){
			return true;
		}
		else{
			control.FeedbackHandler.failedFeedback("Meldingen må inne holde tekst.");
			return false;
		}

	}

	/**
	 * Checks if the message has a viktighet value.
	 */
	public boolean checkViktighet(){
		if(mgui.getLav().isSelected() || mgui.getMiddels().isSelected() || mgui.getHøy().isSelected()){
			return true;
		}
		else{
			control.FeedbackHandler.failedFeedback("Det må velges en viktighetsgrad for meldingen.");
			return false;
		}
	}

	/**
	 * Checks if the message has a varighet value.
	 */
	public boolean checkVarighet(){
		if(mgui.getEnDag().isSelected() || mgui.getTreDag().isSelected() || mgui.getSyvDag().isSelected() || mgui.getFjortenDag().isSelected()){
			return true;
		}
		else{
			System.out.println("feil..her");
			control.FeedbackHandler.failedFeedback("Det må velges en varighetsperiode for meldingen.");
			return false;
		}
	}

	/**
	 * @return the int level of viktighet.
	 */
	public int getViktighet(){
		if(mgui.getLav().isSelected()){
			int v = 1;
			return v;
		}
		if(mgui.getMiddels().isSelected()){
			int v = 2;
			return v;
		}
		if(mgui.getHøy().isSelected()){
			int v = 3;
			return v;
		}
		else{
			return 0;
		}
	}
	public Date getDate(){
		Calendar rightNow = Calendar.getInstance();
		java.util.Date date = rightNow.getTime();
		return date;
	}


	/**
	 * Adds the checked number of days to the varighet value.
	 * @return the date of varighet.
	 */
	public Date getDelDate(){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime((Date) mgui.getDatoSpinn().getValue());

		if(mgui.getEnDag().isSelected()){
			rightNow.add(Calendar.DAY_OF_YEAR, +1);
			java.util.Date date = rightNow.getTime();
			return date;
		}
		else if(mgui.getTreDag().isSelected()){
			rightNow.add(Calendar.DAY_OF_YEAR, +3);
			java.util.Date date = rightNow.getTime();
			return date;
		}
		else if(mgui.getSyvDag().isSelected()){
			rightNow.add(Calendar.DAY_OF_YEAR, +7);
			java.util.Date date = rightNow.getTime();
			return date;
		}
		else if(mgui.getFjortenDag().isSelected()){
			rightNow.add(Calendar.DAY_OF_YEAR, +14);
			java.util.Date date = rightNow.getTime();
			return date;
		}
		else{
			return null;
		}
	}


	/**
	 * Adds functionality to components
	 */
	public void actionPerformed(ActionEvent e){

		boolean b = true;
		if(e.getActionCommand().equals("Avbryt")){
			mgui.getFrame().dispose();
		}

		if (e.getActionCommand().equalsIgnoreCase("Oppdater")){

			Controller c = new Controller();
			String emp = mgui.getId().getText();
			if( emp.equalsIgnoreCase("")|| mgui.getHent().getText().equalsIgnoreCase("")){
				FeedbackHandler.failedFeedback("Fyll inn ansatt og melding idnummer");
			}
			else{
				int aEmpId = Integer.parseInt(emp);
				if(checkText() == true && checkId() == true){
					String text = mgui.getArea().getText();
					String nyMsgId = mgui.getHent().getText();
					int id = Integer.parseInt(nyMsgId);
					Date date = null;
					Date date2 = (Date) mgui.getDatoSpinn().getValue();//null;
					int empId = 0;
					int pri = 0;
					Mess mToDelete = new Mess(id, empId, date , "", pri, false, date2);
					
					Date messDateToPublish = (Date) mgui.getDatoSpinn().getValue();

					Mess oppdatertMess = new Mess(id,aEmpId, messDateToPublish,text,getViktighet(),false, getDelDate());
					if( mgui.getFerdig().isSelected()){
						oppdatertMess.setDone();
					}
					if(id > 0 && checkId() == true && checkViktighet() == true && checkVarighet() == true){
						if(c.upDateMessages(mToDelete, oppdatertMess)){
							FeedbackHandler.successfulFeedBack("Oppdatering velykket!");
							mgui.getFrame().dispose();

						}
						else {
							FeedbackHandler.failedFeedback("Oppdatering mislykket!");
						}

					}

				}
			}

		}
		if(e.getSource() == mgui.getLagre()){
			Date messDateToPublish = (Date) mgui.getDatoSpinn().getValue();

			Controller c = new Controller();
			String empId = mgui.getId().getText();
			if(empId.equals("")){
				control.FeedbackHandler.failedFeedback("Ansatt ID må skrives inn");
				b = false;
			} 
			if (b == true && c.findEmployee(Integer.parseInt(empId))){
				try{
					id = Integer.parseInt(empId);
				}catch(NumberFormatException nFE){
					JOptionPane.showMessageDialog(null, "ID kan kun være et tall");
					b = false;
				}
				if(b == true && id > 0 && checkText() == true && checkId() == true && checkViktighet() == true && checkVarighet() == true){
					Mess aMessage = new Mess(getDelDate(), messDateToPublish, getViktighet(), mgui.getText(), id, false);
					if( mgui.getFerdig().isSelected()){
						aMessage.setDone();
					}
					c.saveMessage(aMessage);
					//Feedback is removed for the purpose of testing.
					//FeedbackHandler.successfulFeedBack("Melding lagt til");
					mgui.getFrame().dispose();
				}
			}
		}
		else {

		}

	}
}




