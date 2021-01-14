package gui;
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

		JFormattedTextField i;
	    int id;
	    JFrame frame;


    /**
     * The constructor
     * @param in - the input from DeleteEmployee
     * @param inn - the formatted ID
     * @param f - the frame
     */
	public ListenerDeleteEmployee(JFormattedTextField in, int inn, JFrame f){
		i = in;
		id = inn;
		frame = f;
	}

	/**
	 * Adds functionality to components
	 */
	public void actionPerformed(ActionEvent e){

		if(e.getActionCommand().equals("OK")){
			String a = i.getText();

			if(a.length()!=0){
				try{
				DeleteEmployee.confirmFrame(a);
				}catch(NumberFormatException nFE){
					JOptionPane.showMessageDialog(null, "ID kan kun være et tall");
				}
			}
			else if (a.length() == 0){
				JOptionPane.showMessageDialog(null, "Fyll inn ansatt-ID");
			}
		}

		if(e.getActionCommand().equals("Bekreft")){
			Controller c = new Controller();
			c.deleteEmp(id);

			frame.dispose();
		}

		if(e.getActionCommand().equals("Avbryt")){
			frame.dispose();
		}
	}
}
