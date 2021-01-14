package com.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.DeleteEmployee;
import control.*;

/**
* Class for functionality to DeleteEmployee
* @author Tommy
*
*/

public class ListenerDeleteEmployee implements ActionListener {

		DeleteEmployee del;
		int id;


    /**
     * The constructor
     */
	public ListenerDeleteEmployee(DeleteEmployee d){
		this.del = d;
	}
	
    /**
     * sets ID
     * @param i - id
     */
	public void setID(int i){
		id = i;
	}

	/**
	 * Adds functionality to components
	 */
	public void actionPerformed(ActionEvent e){
        
        Controller c = new Controller();
		if(e.getActionCommand().equals("Avbryt")){
			del.getFrame().dispose();
		}else{
		
		
		if(e.getSource() == del.getButton()){
			boolean b = true;
			String a = del.getID().getText();
			
			if(a.equals("")){
				control.FeedbackHandler.failedFeedback("Fyll inn ID");
			}else{
			try{
				id = Integer.parseInt(a);
				}catch(NumberFormatException nFE){
					control.FeedbackHandler.failedFeedback("ID må være et tall");
					b = false;
				}
				
		    

			if(b == true && c.findEmployee(id)){
				//the joption is turned off due to the test assignment
				
//				int x = JOptionPane.showConfirmDialog(null,
//	                 "Slette " + c.getEmployee(id) + " -  " +
//	                 " Fortsette? ", " Fortsette? ", JOptionPane.YES_NO_OPTION);
//				if(x == JOptionPane.YES_OPTION){
					c.deleteEmp(id);
//					FeedbackHandler.successfulFeedBack("Ansatt med ID-nummer " + id + " slettet!");

					del.getFrame().dispose();
				
			}
		}
	  }
	}
  }
}
