package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import control.Controller;
import control.DateUtil;

/**
 * The class implements the user interface for changing an already scheduled shift.
 *
 * @author David Ronen.
 * @Version 30.01.11
 */

public class ChangeShiftGui implements ActionListener{

	private JPanel window,pane,oldShift, newShift, idLine, dateLine, startTimeLine, newDateLine, newStartTimeLine,
	newEndTimeLine, comment, commentLine, buttons,gTid, bTid, sTid;
	private JLabel emptLeft, emptRight,header, oldTitleLabel, idLabel, dateLabel, startTimeLabel, newTitleLabel, newDateLabel,
	newStartTimeLabel,newEndTimeLabel, commentLabel, optional, leftBorder, rightBorder, emptDown, air1, air2, air3;
	private JTextField idField, dateField, startTimeField, newDateField, newStartTimeField, newEndTimeField;
	private JButton submit, cancel;
	private Font font6;
	private JTextArea commentArea;
	private JFrame frame;
	private JRadioButton en,to, tre,fire,fem,seks, En, To, Tre;
	private ButtonGroup bg1, bg2, bg3;

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
		idField = new JTextField(3);
		dateLabel = new JLabel("Dato:");
		enlarge(dateLabel, 14);
		air2 = new JLabel("dd-MM-yy");
		dateField = new JTextField(8);
		startTimeLabel = new JLabel("Starttid:");
		enlarge(startTimeLabel, 14);
		startTimeField = new JTextField(5);
		//the radio buttons for the start time
		gTid = new JPanel();
		gTid.setLayout(new GridLayout(3,1));
		En = new JRadioButton("01:00");
        En.addActionListener(this);
        To = new JRadioButton("08:00");
        To.addActionListener(this);
        Tre = new JRadioButton("16:00");
        Tre.addActionListener(this);
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
        en.addActionListener(this);
        to = new JRadioButton("08:00");
        to.addActionListener(this);
        tre = new JRadioButton("16:00");
        tre.addActionListener(this);
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
        fire.addActionListener(this);
        fem = new JRadioButton("16:00");
        fem.addActionListener(this);
        seks = new JRadioButton("24:00");
        seks.addActionListener(this);
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
		newDateField = new JTextField(8);
		newStartTimeField = new JTextField(5);
		newEndTimeField = new JTextField(5);
		submit = new JButton("Endre");
		submit.addActionListener(this);
		enlarge(submit, 14);
		cancel = new JButton("Angre");
		cancel.addActionListener(this);
		font6 = new Font("Verdana", Font.BOLD, 14);
		cancel.setFont(font6);
		cancel.setForeground(Color.RED);
		//enlarge(cancel, 20);

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
     * The method controls the execution of user commands.
     */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit){
			if(idField.getText().equalsIgnoreCase("")|| dateField.getText().equalsIgnoreCase("")|| startTimeField.getText().equalsIgnoreCase("")||
					newDateField.getText().equalsIgnoreCase("")|| newStartTimeField.getText().equalsIgnoreCase("")||
					newEndTimeField.getText().equalsIgnoreCase("")){
				failedFeedBack("Alle ikke-valgfrie felt må skrives inn!");
				
				}
			else{
				try {
					int id = Integer.parseInt(idField.getText());
				    String oDate = dateField.getText();
			        String oStartTime = startTimeField.getText();
			        String nDate = newDateField.getText();
				    DateUtil u = new DateUtil();
				    if(u.isLegalTime(nDate)){

			        String nStartTime = newStartTimeField.getText();
			        String eTime = newEndTimeField.getText();
			        String com = commentArea.getText();
				    Controller c = new Controller();
				    c.reSchedulShift(id, oDate, oStartTime, nDate, nStartTime, eTime, com);
				    idField.setText("");
				    dateField.setText("");
				    startTimeField.setText("");
				    newDateField.setText("");
				    newStartTimeField.setText("");
				    newEndTimeField.setText("");
				    commentArea.setText("");
				    }
				    else{}
				    }
				catch (Exception NumberFormatException) {
			 	    	failedFeedBack("ID-feltet må kun innholde tall!");
			 	    	}
				}
			}
		if(e.getSource() == cancel){
			frame.dispose();
		}
		if(e.getSource() == cancel){
			frame.dispose();
		}
		if (e.getSource() == En) {
			startTimeField.setText(En.getText());
			}
		if (e.getSource() == To) {
		    startTimeField.setText(To.getText());
		}
		if (e.getSource() == Tre) {
			startTimeField.setText(Tre.getText());
		}
		if (e.getSource() == en) {
			newStartTimeField.setText(en.getText());
			}
		if (e.getSource() == to) {
		    newStartTimeField.setText(to.getText());
		}
		if (e.getSource() == tre) {
			newStartTimeField.setText(tre.getText());
		}
		if (e.getSource() == fire) {
			newEndTimeField.setText(fire.getText());
			}
		if (e.getSource() == fem) {
			newEndTimeField.setText(fem.getText());
			}
		if (e.getSource() == seks) {
			newEndTimeField.setText(seks.getText());
			}
		}


    /**
     * This method gives an warning that an operation failed.
     * @param feedBackText - the message given.
     */
    public void failedFeedBack(String feedBackText){
    	String title = "Feil oppstod!";
		String message = feedBackText;
		JOptionPane.showMessageDialog(null,message,title,
				JOptionPane.OK_CANCEL_OPTION);
		}
    }





