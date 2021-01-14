package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import com.listeners.ListenerChangeShift;

/**
 * The class implements the user interface for changing an already scheduled shift.
 *
 * @author David Ronen.
 * @Version 30.01.11
 * modified by Tommy
 */

public class ChangeShiftGui{

	private JPanel window,pane,oldShift, newShift, idLine, dateLine, startTimeLine, newDateLine, newStartTimeLine,
	newEndTimeLine, comment, commentLine, buttons,gTid, bTid, sTid;
	private JLabel emptLeft, emptRight,header, oldTitleLabel, idLabel, dateLabel, startTimeLabel, newTitleLabel, newDateLabel,
	newStartTimeLabel,newEndTimeLabel, commentLabel, optional, leftBorder, rightBorder, emptDown, air1, air2, air3;
	private JSpinner idField, dateField, startTimeField, newDateField, newStartTimeField, newEndTimeField;
	private JButton submit, cancel;
	private Font font6;
	private JTextArea commentArea;
	private JFrame frame;
	private JRadioButton en,to, tre,fire,fem,seks, En, To, Tre;
	private ButtonGroup bg1, bg2, bg3;
	private SpinnerDateModel dSpinner, sTime, newDSpinner, newSTime, newETime;

	/**
	 * Constructor of the a class object.
	 */
	public ChangeShiftGui() {
		createWindow();
		initComponents();
		addComponents();
		frame.setVisible(true);
	}
	/**
	 * Creates the window.
	 */
	public void createWindow() {

		frame = new JFrame();
		frame.setSize(320, 550);
		frame.setTitle("Skiftorganiserer");
		frame.setLocation(500,100);
		frame.setResizable(false);

	}
	
	public void setInvisible1() {
		frame.setVisible(false);
		
	}
	/**
	 * The method initiates the different components.
	 */

	public void initComponents(){
		pane = new JPanel();
		pane.setLayout(new BorderLayout());
		emptLeft = new JLabel("      ");
		emptRight = new JLabel("      ");
		emptDown = new JLabel("   ");
		window = new JPanel();
		window.setLayout(new GridLayout(3,1));
		oldShift = new JPanel();
		oldShift.setLayout(new GridLayout(5,1));
		newShift = new JPanel();
		newShift.setLayout(new GridLayout(4,1));
		comment = new JPanel();
		comment.setLayout(new BorderLayout());

		header = new JLabel("Skiftorganiserer::EndreSkift:");


		idLine = new JPanel();
		idLine.setLayout(new GridLayout(1,3));
		dateLine = new JPanel();
		dateLine.setLayout(new GridLayout(1,3));
		startTimeLine = new JPanel();
		startTimeLine.setLayout(new GridLayout(1,2));
		oldTitleLabel = new JLabel("Vakten du ønsker å endre:");
		idLabel = new JLabel("IDnummer:");
		enlarge(idLabel, 14);
		//air in the panel id
		air1 = new JLabel();
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 100, 1);
		idField = new JSpinner(sm);
		JFormattedTextField df =
			((JSpinner.DefaultEditor)idField.getEditor()).getTextField();
		df.setEditable(false);
		dateLabel = new JLabel("Dato:");
		enlarge(dateLabel, 14);
		air2 = new JLabel("dd-MM-yy");

		dSpinner = new SpinnerDateModel();
		dateField = new JSpinner(dSpinner);
		JSpinner.DateEditor setDate = new JSpinner.DateEditor(dateField, "dd/MM/yy");
		dateField.setEditor(setDate);
		JFormattedTextField dt =
			((JSpinner.DefaultEditor)dateField.getEditor()).getTextField();
		dt.setEditable(false);

		startTimeLabel = new JLabel("Starttid:");
		enlarge(startTimeLabel, 14);
		sTime = new SpinnerDateModel();
		startTimeField = new JSpinner(sTime);
		JSpinner.DateEditor begin = new JSpinner.DateEditor(startTimeField, "HH:mm");
		startTimeField.setEditor(begin);
		JFormattedTextField da =
			((JSpinner.DefaultEditor)startTimeField.getEditor()).getTextField();
		da.setEditable(false);
		ListenerChangeShift chs = new ListenerChangeShift(this);

		//the radio buttons for the start time
		gTid = new JPanel();
		gTid.setLayout(new GridLayout(3,1));
		En = new JRadioButton("01:00");
		En.addActionListener(chs);
		To = new JRadioButton("08:00");
		To.addActionListener(chs);
		Tre = new JRadioButton("16:00");
		Tre.addActionListener(chs);
		bg3 = new ButtonGroup();
		bg3.add(En);
		bg3.add(To);
		bg3.add(Tre);


		newDateLine = new JPanel();
		newDateLine.setLayout(new GridLayout(1,2));
		newStartTimeLine = new JPanel();
		newStartTimeLine.setLayout(new GridLayout(1,3));
		//The radio buttons for the start time
		bTid = new JPanel();
		bTid.setLayout(new GridLayout(3,1));
		en = new JRadioButton("01:00");
		en.addActionListener(chs);
		to = new JRadioButton("08:00");
		to.addActionListener(chs);
		tre = new JRadioButton("16:00");
		tre.addActionListener(chs);
		bg1 = new ButtonGroup();
		bg1.add(en);
		bg1.add(to);
		bg1.add(tre);
		newEndTimeLine = new JPanel();
		newEndTimeLine.setLayout(new GridLayout(1,3));
		sTid = new JPanel();
		sTid.setLayout(new GridLayout(3,1));
		//The radio buttons for the end time
		fire = new JRadioButton("08:00");
		fire.addActionListener(chs);
		fem = new JRadioButton("16:00");
		fem.addActionListener(chs);
		seks = new JRadioButton("24:00");
		seks.addActionListener(chs);
		bg2 = new ButtonGroup();
		bg2.add(fire);
		bg2.add(fem);
		bg2.add(seks);
		newTitleLabel = new JLabel("Ny vakt");
		newDateLabel = new JLabel("Dato:");
		enlarge(newDateLabel, 14);
		air3 = new JLabel();
		newStartTimeLabel = new JLabel("Starttid:");
		enlarge(newStartTimeLabel, 14);
		newEndTimeLabel = new JLabel("Slutttid:");
		enlarge(newEndTimeLabel, 14);

		newDSpinner = new SpinnerDateModel();
		newDateField = new JSpinner(newDSpinner);
		JSpinner.DateEditor setNewDate = new JSpinner.DateEditor(newDateField, "dd/MM/yy");
		newDateField.setEditor(setNewDate);
		JFormattedTextField dy =
			((JSpinner.DefaultEditor)newDateField.getEditor()).getTextField();
		dy.setEditable(false);

		newSTime = new SpinnerDateModel();
		newStartTimeField = new JSpinner(newSTime);
		JSpinner.DateEditor setNewStartTime = new JSpinner.DateEditor(newStartTimeField, "HH:mm");
		newStartTimeField.setEditor(setNewStartTime);
		JFormattedTextField du =
			((JSpinner.DefaultEditor)newStartTimeField.getEditor()).getTextField();
		du.setEditable(false);


		newETime = new SpinnerDateModel();
		newEndTimeField = new JSpinner(newETime );
		JSpinner.DateEditor setNewEndTime = new JSpinner.DateEditor(newEndTimeField, "HH:mm");
		newEndTimeField.setEditor(setNewEndTime);
		JFormattedTextField di =
			((JSpinner.DefaultEditor)newEndTimeField.getEditor()).getTextField();
		di.setEditable(false);
		submit = new JButton("Endre");
		submit.addActionListener(chs);
		enlarge(submit, 14);
		cancel = new JButton("Angre");
		cancel.addActionListener(chs);
		font6 = new Font("Verdana", Font.BOLD, 14);
		cancel.setFont(font6);
		cancel.setForeground(Color.RED);

		commentLine = new JPanel();
		commentLine.setLayout(new GridLayout(1,2));
		commentLabel = new JLabel("Kommentar:");
		enlarge(commentLabel, 14);
		optional = new JLabel("(valgfritt)");
		commentArea = new JTextArea();
		leftBorder = new JLabel("   ");
		rightBorder = new JLabel("   ");

		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());


	}
	/**
	 * The method adds the different components.
	 */

	public void addComponents(){
		frame.getContentPane().add(pane);
		pane.add(window, BorderLayout.CENTER);
		pane.add(emptLeft, BorderLayout.WEST);
		pane.add(emptRight, BorderLayout.EAST);
		pane.add(emptDown, BorderLayout.SOUTH);
		window.add(oldShift);
		window.add(newShift);
		window.add(comment);
		oldShift.add(header);
		oldShift.add(idLine);
		idLine.add(idLabel);
		idLine.add(air1);
		idLine.add(idField);
		oldShift.add(oldTitleLabel);
		oldShift.add(dateLine);
		dateLine.add(dateLabel);
		dateLine.add(air2);
		dateLine.add(dateField);
		oldShift.add(startTimeLine);
		startTimeLine.add(startTimeLabel);
		startTimeLine.add(gTid);
		gTid.add(En);
		gTid.add(To);
		gTid.add(Tre);
		startTimeLine.add(startTimeField);


		newShift.add(newTitleLabel);
		newDateLine.add(newDateLabel);
		newDateLine.add(air3);
		newDateLine.add(newDateField);
		newShift.add(newDateLine);
		newStartTimeLine.add(newStartTimeLabel);
		newStartTimeLine.add(bTid);
		bTid.add(en);
		bTid.add(to);
		bTid.add(tre);
		newStartTimeLine.add(newStartTimeField);
		newShift.add(newStartTimeLine);
		newEndTimeLine.add(newEndTimeLabel);
		newEndTimeLine.add(sTid);
		sTid.add(fire);
		sTid.add(fem);
		sTid.add(seks);
		newEndTimeLine.add(newEndTimeField);
		newShift.add(newEndTimeLine);

		commentLine.add(commentLabel);
		commentLine.add(optional);
		comment.add(commentLine,BorderLayout.NORTH);
		comment.add(commentArea, BorderLayout.CENTER);
		buttons.add(submit);
		buttons.add(cancel);
		comment.add(buttons, BorderLayout.SOUTH);
		comment.add(leftBorder, BorderLayout.WEST);
		comment.add(rightBorder, BorderLayout.EAST);
	}

	/**
	 * The method enlarges fonts on buttons or labels.
	 * @param o - the button or label.
	 * @param size - of the new font.
	 */

	public void enlarge(Object o, int size) {
		if (o instanceof JButton){
			font6 = new Font("Verdana", Font.BOLD, size);
			((JButton) o).setFont(font6);
			((JButton) o).setForeground(Color.GREEN);
		}
		else if(o instanceof JLabel){
			Font font5 = new Font("Verdana", Font.BOLD, size);
			((JLabel) o).setFont(font5);
		}
	}

	/**
	 * returns the  window JPanel
	 * @return JPanel
	 */
	public JPanel getWindow() {
		return window;
	}
	/**
	 * returns the submit JButton
	 * @return JButton
	 */

	public JButton getSubmit() {
		return submit;
	}
	/**
	 * returns the RadioButton en, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getEn() {
		return en;
	}
	/**
	 * returns the RadioButton to, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getTo() {
		return to;
	}
	/**
	 * returns the RadioButton tre, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getTre() {
		return tre;
	}
	/**
	 * returns the RadioButton fire, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getFire() {
		return fire;
	}
	/**
	 * returns the RadioButton fem, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getFem() {
		return fem;
	}
	/**
	 * returns the RadioButton seks, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton getSeks() {
		return seks;
	}
	/**
	 * returns the RadioButton En, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton get_En() {
		return En;
	}
	/**
	 * returns the RadioButton To, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton get_To() {
		return To;
	}
	/**
	 * returns the RadioButton Tre, JRadioButton
	 * @return JRadioButton
	 */

	public JRadioButton get_Tre() {
		return Tre;
	}
	/**
	 * returns the Id JSpinner
	 * @return JSpinner
	 */
	public JSpinner getIdField() {
		return idField;
	}
	/**
	 * returns the Date  JSpinner
	 * @return JSpinner
	 */

	public JSpinner getDateField() {
		return dateField;
	}
	/**
	 * returns the starttime JSpinner
	 * @return JSpinner
	 */

	public JSpinner getStartTimeField() {
		return startTimeField;
	}
	/**
	 * returns the newstarttime JSpinner
	 * @return JSpinner
	 */

	public JSpinner getNewStartTimeField() {
		return newStartTimeField;
	}
	/**
	 * returns the endtime  JSpinner
	 * @return JSpinner
	 */

	public JSpinner getNewEndTimeField() {
		return newEndTimeField;
	}/**
	 * returns the cancel JButton
	 * @return JButton
	 */

	public JButton getCancel() {
		return cancel;

	}
	/**
	 * returns the textarea
	 * @return JTextarea
	 */

	public JTextArea getCommentArea() {
		return commentArea;
	}
    /**
     * returns the window JFrame
     * @return JFrame
     */
	public JFrame getFrame() {
		return frame;
	}
	public void setInvisible() {
		frame.setVisible(false);		
	}

}





