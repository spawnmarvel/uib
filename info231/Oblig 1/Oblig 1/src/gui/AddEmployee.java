package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Controller;

/**
 * This interface frame represents AddEmployee function
 * @author Espen kleivane
 * @version 27.01.2011
 */



public class AddEmployee implements ActionListener{

	private JFrame window;
	private JPanel zero,first, second, third, fourth, fifth;
	private JLabel info, fName, air, lName, air1;
    private JTextField firstName, lastName;
    private JButton submit;
	private Font font6;



	/**
	 * The constructor of the class
	 *
	 */
	public AddEmployee() {
		createWindow();
		initComp();
		addComp();
		window.setVisible(true);
	}


	 /**
	  * This method creates the window
	  *
	  */
     public void createWindow() {

		//
		window = new JFrame();
		window.setSize(300, 260);
		window.setTitle("AddEmployee");
		window.setLocation(500,50);
		window.setLayout(new GridLayout(6,1));

	}

    /**
     * This method initializes all the components in the right order in the gui frame
 	 *
 	 */
 	public void initComp() {
 		//the zero panel
 		zero = new JPanel();
 		zero.setLayout(new GridLayout(1,2));
 		info = new JLabel("VaktPlanlegger: LeggTilAnsatt   ");
 		//info.setForeground(Color.RED);
 		//the first panel
 		first = new JPanel();
 		first.setLayout(new GridLayout(1,2));
 		fName = new JLabel("Fornavn :");
 		enlarge(fName, 18);
 		firstName = new JTextField(12);
 		firstName.setToolTipText("Eksempel : Tom ");
        //the second panel just air between
 		second = new JPanel();
 		second.setLayout(new GridLayout());
 		air = new JLabel(" ");
        //the third panel
 		third = new JPanel();
 		third.setLayout(new GridLayout(1,2));
 		lName = new JLabel("Etternavn :");
 		enlarge(lName, 18);
 		lastName = new JTextField(12);
 		lastName.setToolTipText("Eksempel : Harrions ");
        //the fourth panel just air
 		fourth = new JPanel();
 		fourth.setLayout(new GridLayout());
 		air1 = new JLabel(" ");
        //the fifth panel with button
 		fifth = new JPanel();
 		fifth.setLayout(new BorderLayout());
 		submit = new JButton("Legg til ");
 		submit.setForeground(Color.GREEN);
 		enlarge(submit, 20);


 	}

     /**
      * This method adds the different components to each other in
      * Right order from top to bottom
      */
     public void addComp() {
    	 window.getContentPane().add(zero);
    	 window.getContentPane().add(first);
    	 window.getContentPane().add(second);
    	 window.getContentPane().add(third);
    	 window.getContentPane().add(fourth);
    	 window.getContentPane().add(fifth);
    	 zero.add(info);
         first.add(fName);
    	 first.add(firstName);
         second.add(air);
         third.add(lName);
    	 third.add(lastName);
         fourth.add(air1);
         fifth.add(submit);
         submit.addActionListener(this);
    	 firstName.addActionListener(this);
    	 lastName.addActionListener(this);
    	 window.setVisible(true);


     }

     /**
 	 * Enlarge numbers on buttons.
 	 * @parm JButton button, int Size.
 	 */
 	public void enlarge(JButton button, int size) {
 		JButton b = button;
 		font6 = new Font("Verdana", Font.BOLD, size);
 		b.setFont(font6);
 	}
 	/**
 	 * EnLarge the text in the labels
 	 */
 	public void enlarge(JLabel label, int size){
        JLabel l = label;
		Font font5 = new Font("Verdana", Font.BOLD, size);
		l.setFont(font5);
 	}


/**
 * 
 */

 	@Override
	public void actionPerformed(ActionEvent e) {
		String a = "";
		if(e.getSource() ==submit){
			if (firstName.getText().length()!= 0 && lastName.getText().length() != 0) {
			 a += firstName.getText();
			 a += " ";
			 a += lastName.getText();
			 int n = JOptionPane.showConfirmDialog(null,
                     "Legg inn " + a + " -  " +
                     " Fortsette? ", " Fortsette? ", JOptionPane.YES_NO_OPTION);
                     if (n == JOptionPane.YES_OPTION) {
                    	 Controller c = new Controller();
            			 c.AddNewEmp(a);
            			 firstName.setText("");
            			 lastName.setText("");
            			 window.dispose();
            			 }

                     else if(n == JOptionPane.NO_OPTION) {
                    	 return;


                     	}
                     }
			else if (a.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Fyll inn begge felter! Bruk bokstaver!","Feil oppstod!",
                JOptionPane.ERROR_MESSAGE);
                }
		}

		}
}




















