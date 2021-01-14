package gui;

import java.awt.*;
import javax.swing.*;

import com.listeners.ListenerCreateShift;

/**
 *
 * @author Aslak Rødder
 * @version 05.02.11
 * modified by David, Espen, Tommy
 *
 */
public class CreateShiftGui
{

	private JFrame frame;
	private JPanel window, panel, shift, comment, commentLine, idLine, dateLine, startTimeLine, endTimeLine, sTid,bTid, luft;
	private JLabel commentLabel, idLabel, dateLabel, startTimeLabel, endTimeLabel, luftMellom, luftId, luftDato, leftLabel, rightLabel, downLabel;
	private JButton submit;
	private Font font;
	private JTextArea commentArea;
	private JRadioButton en, to, tre, fire, fem,seks;
	private ButtonGroup bg1, bg2;
	private JSpinner idSpinner, sTimeSpinner, eTimeSpinner, dateSpinner;
	private SpinnerDateModel sTime, eTime,dSpinner;




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
		frame.setSize(300, 450);
		frame.setTitle("Nytt skift");
		frame.setLocation(500,100);
		frame.setResizable(false);
	}
	
	public void setInvisible(){
		frame.setVisible(false);
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
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 100, 1);
		idSpinner = new JSpinner(sm);
		JFormattedTextField df =
			((JSpinner.DefaultEditor)idSpinner.getEditor()).getTextField();
		df.setEditable(false);
		
		
		luftId = new JLabel("");
		dateLine = new JPanel();
		dateLine.setLayout(new GridLayout(1,3));
		dateLabel = new JLabel("Dato:");
		enlarge(dateLabel, 14);
		dSpinner = new SpinnerDateModel();
		dateSpinner = new JSpinner(dSpinner);
		JSpinner.DateEditor setDate = new JSpinner.DateEditor(dateSpinner, "dd/MM/yy");
		dateSpinner.setEditor(setDate);
		JFormattedTextField de =
			((JSpinner.DefaultEditor)dateSpinner.getEditor()).getTextField();
		de.setEditable(false);
		luftDato = new JLabel("dd-MM-yy");
		startTimeLine = new JPanel();
		startTimeLine.setLayout(new GridLayout(1,3));
		startTimeLabel = new JLabel("Starttid:");
		enlarge(startTimeLabel, 14);
		sTime = new SpinnerDateModel();
		sTimeSpinner = new JSpinner(sTime);
		JSpinner.DateEditor begin = new JSpinner.DateEditor(sTimeSpinner, "HH:mm");
		sTimeSpinner.setEditor(begin);
		JFormattedTextField dt =
			((JSpinner.DefaultEditor)sTimeSpinner.getEditor()).getTextField();
		dt.setEditable(false);

		//the radio buttons for the start time
		bTid = new JPanel();
		bTid.setLayout(new GridLayout(3,1));
		en = new JRadioButton("01:00");
		to = new JRadioButton("08:00");
		tre = new JRadioButton("16:00");

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
		fem = new JRadioButton("16:00");
		seks = new JRadioButton("24:00");
		bg2 = new ButtonGroup();
		bg2.add(fire);
		bg2.add(fem);
		bg2.add(seks);
		endTimeLine = new JPanel();
		endTimeLine.setLayout(new GridLayout(1,2));
		endTimeLabel = new JLabel("Sluttid:");
		enlarge(endTimeLabel, 14);
		eTime = new SpinnerDateModel();

		eTimeSpinner = new JSpinner(eTime);
		JSpinner.DateEditor end = new JSpinner.DateEditor(eTimeSpinner, "HH:mm");
		eTimeSpinner.setEditor(end);
		JFormattedTextField da =
			((JSpinner.DefaultEditor)eTimeSpinner.getEditor()).getTextField();
		da.setEditable(false);

		submit = new JButton("Legg til");
		enlarge(submit, 20);

		leftLabel = new JLabel("      ");
		rightLabel = new JLabel("      ");
		downLabel = new JLabel("   ");

		ListenerCreateShift lis = new ListenerCreateShift(this);
		en.addActionListener(lis);
		to.addActionListener(lis);
		tre.addActionListener(lis);
		fire.addActionListener(lis);
		fem.addActionListener(lis);
		seks.addActionListener(lis);
		submit.addActionListener(lis);

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
		idLine.add(idSpinner);

		dateLine.add(dateLabel);
		dateLine.add(luftDato);
		dateLine.add(dateSpinner);

		startTimeLine.add(startTimeLabel);
		startTimeLine.add(bTid);
		bTid.add(en);
		bTid.add(to);
		bTid.add(tre);
		startTimeLine.add(sTimeSpinner);

		endTimeLine.add(endTimeLabel);
		endTimeLine.add(sTid);
		sTid.add(fire);
		sTid.add(fem);
		sTid.add(seks);
		endTimeLine.add(eTimeSpinner);


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
	 * returns the JButton
	 * @return JButton
	 */
	public JButton getSubmit(){
		return submit;
	}
	/**
	 * returns the Id JSpinner
	 * @return JSpinner
	 */

	public JSpinner getIDSpinner(){
		return idSpinner;
	}
	
	/**
	 * returns the StartTime JSpinner
	 * @return JSpinner
	 */
	public JSpinner getSSpinner(){
		return sTimeSpinner;
	}
	
	/**
	 * returns the endTime JSpinner
	 * @return JSpinner
	 */
	public JSpinner getESpinner(){
		return eTimeSpinner;
	}
	/**
	 * Returns the Date JSpinner
	 * @return JSpinner
	 */

	public JSpinner getDateSpinner(){
		return dateSpinner;
	}
	/**
	 * returns the textarea
	 * @return textarea
	 */
	public JTextArea getCommentArea(){
		return commentArea;
	}
	/**
	 * returns the Radiobutton en, JRadioButton 
	 * @return JRadioButton
	 */

	public JRadioButton getEn(){
		return en;
	}
	/**
	 * RETURNS THE RadioButton to ,JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getTo(){
		return to;
	}
	/**
	 * returns the RadioButton tre, JRadioButton
	 * @return
	 */

	public JRadioButton getTre(){
		return tre;
	}
	/**
	 * returns the RadioButton fire, JRadioButton
	 * @return
	 */

	public JRadioButton getFire(){
		return fire;
	}
	/**
	 * returns the RadioButton fem, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getFem(){
		return fem;
	}
	/**
	 * returns the RadioButton seks, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getSeks(){
		return seks;
	}

}
