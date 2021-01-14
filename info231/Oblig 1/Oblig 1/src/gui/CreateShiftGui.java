package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.Controller;
import control.DateUtil;

/**
 *
 * @author Aslak Rødder
 * @version 05.02.11
 *
 */
public class CreateShiftGui implements ActionListener
{

	private JFrame frame;
	private JPanel window, panel, shift, comment, commentLine, idLine, dateLine, startTimeLine, endTimeLine, sTid,bTid, luft;
	private JLabel commentLabel, idLabel, dateLabel, startTimeLabel, endTimeLabel, luftMellom, luftId, luftDato, leftLabel, rightLabel, downLabel;
	private JTextField idField, dateField, startTimeField, endTimeField;
	private JButton submit;
	private Font font;
	private JTextArea commentArea;
	private JRadioButton en, to, tre, fire, fem,seks;
	private ButtonGroup bg1, bg2;




	/**
	 * Creates the window with components.
	 */
	public CreateShiftGui()
	{
		createWindow();
		initComponents();
		addComponents();
		frame.setVisible(true);
	}

	/**
	 * Creates a window
	 */
	public void createWindow()
	{
		frame = new JFrame();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 450);
		frame.setTitle("Nytt skift");
		frame.setLocation(500,100);
		frame.setResizable(false);
	}


	/**
	* This method initializes all the components
	*/
	public void initComponents ()
	{

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		window = new JPanel();
		window.setLayout(new GridLayout(2,3));

		shift = new JPanel();
		shift.setLayout(new GridLayout(6,1));

		comment = new JPanel();
		comment.setLayout(new BorderLayout());
		commentLabel = new JLabel("Kommentar: (valgfritt)");
		commentLine = new JPanel();
		commentLine.setLayout(new GridLayout(1,2));
		commentArea = new JTextArea();
		enlarge(commentLabel, 14);

		idLine = new JPanel();
		idLine.setLayout(new GridLayout(1,3));
		idLabel = new JLabel("ID nummer:");
		enlarge(idLabel, 12);
		idField = new JTextField();
		luftId = new JLabel("");

		dateLine = new JPanel();
		dateLine.setLayout(new GridLayout(1,3));
		dateLabel = new JLabel("Dato:");
		enlarge(dateLabel, 14);
		dateField = new JTextField();
		luftDato = new JLabel("dd-MM-yy");

		startTimeLine = new JPanel();
		startTimeLine.setLayout(new GridLayout(1,3));
		startTimeLabel = new JLabel("Starttid:");
		enlarge(startTimeLabel, 14);
		startTimeField = new JTextField();

        //the radio buttons for the start time
		bTid = new JPanel();
		bTid.setLayout(new GridLayout(3,1));
		en = new JRadioButton("01:00");
        en.addActionListener(this);
        to = new JRadioButton("08:00");
        to.addActionListener(this);
        tre = new JRadioButton("16:00");
        tre.addActionListener(this);
        bg1 = new ButtonGroup();
        bg1.add(en);
        bg1.add(to);
        bg1.add(tre);
        //Air Between the text fields
        luft = new JPanel();
        luftMellom = new JLabel("    ");
        //the radio buttons for end time
        sTid = new JPanel();
        sTid.setLayout(new GridLayout(3,1));
        fire = new JRadioButton("08:00");
        fire.addActionListener(this);
        fem = new JRadioButton("16:00");
        fem.addActionListener(this);
        seks = new JRadioButton("24:00");
        seks.addActionListener(this);
        bg2 = new ButtonGroup();
        bg2.add(fire);
        bg2.add(fem);
        bg2.add(seks);
	    endTimeLine = new JPanel();
	    endTimeLine.setLayout(new GridLayout(1,2));
	    endTimeLabel = new JLabel("Sluttid:");
		enlarge(endTimeLabel, 14);
		endTimeField = new JTextField();

		submit = new JButton("Legg til");
		submit.addActionListener(this);
		enlarge(submit, 20);
		
		leftLabel = new JLabel("      ");
		rightLabel = new JLabel("      ");
		downLabel = new JLabel("   ");


	}
	/**
	 * This method adds the different components from top to bottom
	 */
	public void addComponents()
	{
		
		frame.getContentPane().add(panel);
		
		panel.add(window, BorderLayout.CENTER);
		panel.add(leftLabel, BorderLayout.WEST);
		panel.add(rightLabel, BorderLayout.EAST);
		panel.add(downLabel, BorderLayout.SOUTH);

		window.add(shift);
		window.add(comment);

		shift.add(idLine);
		shift.add(dateLine);
		shift.add(startTimeLine);
		shift.add(luft);
		luft.add(luftMellom);
		shift.add(endTimeLine);


		idLine.add(idLabel);
		idLine.add(luftId);
		idLine.add(idField);

		dateLine.add(dateLabel);
		dateLine.add(luftDato);
		dateLine.add(dateField);

		startTimeLine.add(startTimeLabel);
		startTimeLine.add(bTid);
		bTid.add(en);
		bTid.add(to);
		bTid.add(tre);
		startTimeLine.add(startTimeField);

		endTimeLine.add(endTimeLabel);
		endTimeLine.add(sTid);
		sTid.add(fire);
		sTid.add(fem);
		sTid.add(seks);
		endTimeLine.add(endTimeField);



		comment.add(commentLine,BorderLayout.NORTH);
		commentLine.add(commentLabel);
		comment.add(commentArea, BorderLayout.CENTER);
		comment.add(submit, BorderLayout.SOUTH);


	}
    /**
     * The method enlarges fonts on buttons or labels.
     * @param o - the button or label.
     * @param size - of the new font.
     */

	public void enlarge(Object o, int size) {
		if (o instanceof JButton){
		font = new Font("Verdana", Font.BOLD, size);
		((JButton) o).setFont(font);
		((JButton) o).setForeground(Color.GREEN);
		}
		else if(o instanceof JLabel){
			Font font5 = new Font("Verdana", Font.BOLD, size);
			((JLabel) o).setFont(font5);
			}
		}



    /**
     * Class based on original made by David Ronen
     *
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit){
			if(idField.getText().equalsIgnoreCase("")
					|| dateField.getText().equalsIgnoreCase("")
					|| startTimeField.getText().equalsIgnoreCase("")
					|| endTimeField.getText().equalsIgnoreCase("")){
				failedFeedBack("Ikke-valgfrie felt må fylles ut!");
				}
			else{
				try {
					int id = Integer.parseInt(idField.getText());
				    String date = dateField.getText();
				    DateUtil u = new DateUtil();
				    if(u.isLegalTime(date)){
			        String startTime = startTimeField.getText();
			        String endTime = endTimeField.getText();
			        String comment = commentArea.getText();
				    Controller c = new Controller();
				    c.ScheduleNewShift(date, startTime, endTime, id, comment);
//				    frame.dispose();
				    idField.setText("");
				    dateField.setText("");
				    startTimeField.setText("");
				    endTimeField.setText("");
				    commentArea.setText("");
				    }
				    else {}
				    }
				catch (Exception NumberFormatException) {
			 	    	failedFeedBack("ID-nummeret finnes ikke!");
			 	    	}
				}
			}
		if (e.getSource() == en) {
			startTimeField.setText(en.getText());
			}
		if (e.getSource() == to) {
			startTimeField.setText(to.getText());
		}
		if (e.getSource() == tre) {
			startTimeField.setText(tre.getText());
		}
		if (e.getSource() == fire) {
			endTimeField.setText(fire.getText());
			}
		if (e.getSource() == fem) {
			endTimeField.setText(fem.getText());
			}
		if (e.getSource() == seks) {
			endTimeField.setText(seks.getText());
			}

		}



    /**
     * @author David Ronen
     * This method gives an warning that an operation failed.
     * @param feedBackText - the message given.
     */
    public void failedFeedBack(String feedBackText){
    	String title = "En feil har oppstått";
		String message = feedBackText;
		JOptionPane.showMessageDialog(null,message,title,
				JOptionPane.OK_CANCEL_OPTION);
		}


}
