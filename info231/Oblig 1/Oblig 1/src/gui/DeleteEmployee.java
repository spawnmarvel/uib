package gui;
import java.awt.*;
import javax.swing.*;

import gui.ListenerDeleteEmployee;
/**
 * The GUI class for deleting an employee
 * @author Hedvig, Tommy
 *
 */
public class DeleteEmployee {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 100;

	/**
	 * The constructor for the DeleteEmployee object
	 */
	public DeleteEmployee(){
		makeFrame();

	}

	/**
	 * The DeleteEmployee frame
	 * @return the frame
	 */
    public static JFrame getFrame(){
    	JFrame f = new JFrame("Slett en ansatt");
    	return f;
    }

	/**
	 * The first frame for inputing ID of an employee to be deleted
	 */
	public void makeFrame(){

		JFrame f = getFrame();
		JLabel l = new JLabel("Skriv inn ansatt-ID:");
		JFormattedTextField jtf = new JFormattedTextField();
		jtf.setColumns(3);
		JButton b = new JButton("OK");
		JButton c = new JButton("Avbryt");

		f.setSize(WIDTH, HEIGHT);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		f.add(l);
		f.add(jtf);
		f.add(b);
		f.add(c);

		f.setVisible(true);

		ListenerDeleteEmployee emp = new ListenerDeleteEmployee(jtf, 0, f);
        jtf.addActionListener(emp);
        b.addActionListener(emp);
        c.addActionListener(emp);

	}

    /**
     * Second frame for confirming whether the ID inputed is correct or not
     * @param a - the input ID
     */
	public static void confirmFrame(String a){
		String emp = a;
		int id = Integer.parseInt(emp);

		JFrame f = getFrame();
		JLabel l = new JLabel("Slette ansatt med ID " + id + "?");
		JButton bekreft = new JButton("Bekreft");
		JButton avbryt = new JButton("Avbryt");

		f.setSize(WIDTH, HEIGHT);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		f.add(l);
		f.add(bekreft);
		f.add(avbryt);

		f.setVisible(true);


		ListenerDeleteEmployee del = new ListenerDeleteEmployee(null, id, f);
        bekreft.addActionListener(del);
        avbryt.addActionListener(del);

	}

}
