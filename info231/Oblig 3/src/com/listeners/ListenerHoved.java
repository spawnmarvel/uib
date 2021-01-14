package com.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import control.Controller;
import control.DateUtil;
import control.FeedbackHandler;
import dataAccsesObject.MySqlMessagesDao;
import dataAccsesObject.MySqlShiftDAO;
import entity.com.messages.MessagePublication;
import entity.com.shift.Shift;
import gui.*;

/**
 * Class for adding functionality to the main window
 * @author Tommy
 * Modified by dro068, jkl070.
 *
 */


public class ListenerHoved implements ActionListener {

	Hoved hoved;
	private MessagePublication mp;

	public ListenerHoved(Hoved hoved){
		this.hoved = hoved;
	}

	/**
	 * The method controls the events according to user commands.
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equalsIgnoreCase("Endre skift")){
			System.out.println("Skift");
			new ChangeShiftGui();

		}

		if (e.getActionCommand().equalsIgnoreCase("Bekreft dato")){
			hoved.getSkjerm().setText("");
			SimpleDateFormat dfd = new SimpleDateFormat("dd-MM-yy");
			Date date = (Date) hoved.getDato().getValue();
			String inString = dfd.format(date);
			Controller c = new Controller();
			hoved.setFont();
			if(c.showShiftPrIdOrDate(inString)!= null){
			hoved.getSkjerm().append(c.showShiftPrIdOrDate(inString));
			}
			else { FeedbackHandler.failedFeedback("Ingen vakt registrert med angitt data!");
			
			}

		}

		if (e.getActionCommand().equalsIgnoreCase("Bekreft id")){
			hoved.getSkjerm().setText("");
			int inString = (Integer)(hoved.getID().getValue());
			Controller c = new Controller();
			hoved.setFont();
			if(c.showShiftPrIdOrDate(inString)!= null){
			hoved.getSkjerm().append(c.showShiftPrIdOrDate(inString));
			MySqlMessagesDao md = new MySqlMessagesDao();
			md.getMaxIdFromDb();
		}
			else{FeedbackHandler.failedFeedback("Ingen vakt registrert med angitt data!");			
			}
		}
		if (e.getActionCommand().equalsIgnoreCase("Nytt skift")){
			new CreateShiftGui();
		}
		if (e.getActionCommand().equalsIgnoreCase("Legg til en ansatt")){
			new AddEmployee(hoved.getHoved());
			
		}
		if (e.getActionCommand().equalsIgnoreCase("Se alle vakter")){
			hoved.setFont();
			hoved.getSkjerm().setText("");
			MySqlShiftDAO mssd = MySqlShiftDAO.getInstance();
			ArrayList<Shift>list = mssd.getAll();

			for (Shift entry:list){
				String s = entry.toString();
				hoved.getSkjerm().append(s);
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Se alle ansatte")) {
			hoved.getSkjerm().setText("");
			Controller c = new Controller();
			hoved.setFont();
			hoved.getSkjerm().append(c.showAllEmp());
		}
		if (e.getActionCommand().equalsIgnoreCase("Lukk")){
			System.exit(0);
		}
		if (e.getActionCommand().equalsIgnoreCase("Slett ansatt")){
			new DeleteEmployee();
		}
		if(e.getActionCommand().equalsIgnoreCase("Skriv melding")) {
			MessageGui msg = new MessageGui();
			msg.getHent().setEditable(false);
			//msg.getHentButton().setEnabled(false);
			msg.getLagreEndring().setEnabled(false);

		}
		if (e.getActionCommand().equalsIgnoreCase("Slett skift")){
			new DeleteShiftGUI();
		}
		if (e.getActionCommand().equalsIgnoreCase("Se vaktliste for i dag")){
			hoved.setFont();
			hoved.getSkjerm().setText("");
			hoved.getSkjerm().append("Vaktliste for i dag den " + DateUtil.getDate() + " :" + "\n");
			Controller c = new Controller();
			hoved.getSkjerm().append(c.showShiftPrIdOrDate(DateUtil.getDate()));
		}
		if(e.getActionCommand().equalsIgnoreCase("Ok")) {
			hoved.enableMessComp();
			hoved.getSkjerm().setText("");
			Date date = (Date) hoved.getMessDate().getValue();
			Controller c= new Controller();
			hoved.setFont();
			mp = new MessagePublication(c.publishByDate(date));
			hoved.getSkjerm().append(mp.publicAllMessages());
			if (hoved.getSkjerm().getText().equalsIgnoreCase("Ingen flere meldinger for denne dagen")){
				disableMessComp();
			}

		}

		if(e.getActionCommand().equalsIgnoreCase("Se neste melding")) {
			hoved.getSkjerm().setText("");
			hoved.getSkjerm().append(mp.publicAllMessages());
			if (hoved.getSkjerm().getText().equalsIgnoreCase("Ingen flere meldinger for denne dagen")){
				disableMessComp();
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Rediger denne melding")) {
			MessageGui msg = new MessageGui();
			msg.getMeldingID().setVisible(true);
			msg.getLagre().setEnabled(false);
			String msgId = Integer.toString(mp.getFrontMessID());
			msg.getHent().setText(msgId);
			Controller c = new Controller();
			msg.getArea().setText("");
			msg.getArea().append(c.appendMessageToRediger(mp.getFrontMessID()));
		}
	}
	/**
	 * 
	 */

	public void disableMessComp(){
		hoved.getRedigerDenneMelding().setEnabled(false);
		hoved.getRedigerDenneMelding().setVisible(false);
		hoved.getSeNesteMelding().setEnabled(false);
		hoved.getSeNesteMelding().setVisible(false);
	}


	
}
