package gui;
import java.awt.*;
import javax.swing.*;

import com.listeners.ListenerDeleteEmployee;

/**
 * The GUI class for deleting an employee
 * @author Hedvig, Tommy
 *
 */
public class DeleteEmployee {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 100;

	private JFormattedTextField jtf;
	private JFrame f;
	private JButton b, c;

	/**
	 * The constructor for the DeleteEmployee object
	 */
	public DeleteEmployee(){
		makeFrame();
	}

	/**
	 * The frame for inputing ID of an employee to be deleted
	 */
	public void makeFrame(){
		f = new JFrame("Slett en ansatt");
		JLabel l = new JLabel("Skriv inn ansatt-ID:");
		jtf = new JFormattedTextField();
		jtf.setColumns(3);
		b = new JButton("OK");
		c = new JButton("Avbryt");

		f.setSize(WIDTH, HEIGHT);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		f.add(l);
		f.add(jtf);
		f.add(b);
		f.add(c);

		f.setVisible(true);

		ListenerDeleteEmployee emp = new ListenerDeleteEmployee(this);
		jtf.addActionListener(emp);
		b.addActionListener(emp);
		c.addActionListener(emp);

	}
	
	/**
	 * sets input
	 * @param i - input
	 */
	public void setID(String i){
		jtf.setText(i);
	}

	/**
	 * returns the JFormattedTextField
	 * @return JFormattedTextField
	 */
	public JFormattedTextField getID(){
		return jtf;
	}
	/**
	 * returns the JFrame
	 * @return JFrame
	 */
	public JFrame getFrame(){
		return f;
	}
	
	/**
	 * returns JButton
	 * @return JButton
	 */
	public JButton getButton(){
		return b;
	}

}
