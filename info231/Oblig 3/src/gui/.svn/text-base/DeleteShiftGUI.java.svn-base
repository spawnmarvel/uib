package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import control.Controller;
import control.DateUtil;
import control.FeedbackHandler;
import entity.com.shift.Shift;

/**
 * @author Hedvig Andersland and Aslak Rødder
 * modified by Espen , David.
 *
 */
public class DeleteShiftGUI implements ActionListener {

	private JFrame frame;
	private JPanel window, idLinje, datoLinje, starttidLinje, bekreftLinje,
	skift;
	private JLabel idNummerLabel, datoLabel, starttidLabel;
	private JSpinner idInnField, datoInnField, starttidInnField;;
	private JButton bekreft;
	private Font font;
	private SpinnerDateModel dSpinner, sTime;
	public static final int EMP_LENGTH = 3;
	public static final int DATE_LENGTH = 8;
	public static final int TIME_LENGTH = 5;

	public DeleteShiftGUI() {
		createWindow();
		initComponents();
		addComponents();
		frame.setVisible(true);
	}

	/**
	 * Creates a window
	 */
	public void createWindow() {
		frame = new JFrame();
		frame.setSize(240, 240);
		frame.setTitle("Slett skift");
		frame.setLocation(500, 100);
		frame.setResizable(false);
	}

	/**
	 * Initializes components
	 */
	public void initComponents() {

		window = new JPanel();
		window.setLayout(new BorderLayout(2, 0));

		skift = new JPanel(new GridLayout(4, 0));

		idLinje = new JPanel(new GridLayout(1, 0));

		datoLinje = new JPanel(new GridLayout(1, 0));

		starttidLinje = new JPanel(new GridLayout(1, 0));

		bekreftLinje = new JPanel(new GridLayout(1, 0));

		idNummerLabel = new JLabel("ID-nummer: ");
		datoLabel = new JLabel("Dato: ");
		starttidLabel = new JLabel("Starttid: ");

		SpinnerModel sm = new SpinnerNumberModel(1, 1, 100, 1);
		idInnField = new JSpinner(sm);
		dSpinner = new SpinnerDateModel();
		JFormattedTextField idSpinn =
			((JSpinner.DefaultEditor)idInnField.getEditor()).getTextField();
		idSpinn.setEditable(false);

		datoInnField = new JSpinner(dSpinner);
		JSpinner.DateEditor setDate = new JSpinner.DateEditor(datoInnField, "dd/MM/yy");
		datoInnField.setEditor(setDate);
		JFormattedTextField oldDato =
			((JSpinner.DefaultEditor)datoInnField.getEditor()).getTextField();
		oldDato.setEditable(false);

		sTime = new SpinnerDateModel();

		starttidInnField = new JSpinner(sTime);
		JSpinner.DateEditor begin = new JSpinner.DateEditor(starttidInnField, "HH:mm");
		starttidInnField.setEditor(begin);
		JFormattedTextField startTime =
			((JSpinner.DefaultEditor)starttidInnField.getEditor()).getTextField();
		startTime.setEditable(false);
		bekreft = new JButton("Bekreft");
		bekreft.addActionListener(this);
		enlarge(idNummerLabel, 14);
		enlarge(datoLabel, 14);
		enlarge(starttidLabel, 14);
		enlarge(bekreft, 20);

	}

	/**
	 * Adds the components to the window
	 */
	public void addComponents() {
		frame.getContentPane().add(window);

		window.add(skift);

		skift.add(idLinje);
		skift.add(datoLinje);
		skift.add(starttidLinje);
		skift.add(bekreftLinje);
		bekreftLinje.add(bekreft);
		idLinje.add(idNummerLabel);
		idLinje.add(idInnField);
		datoLinje.add(datoLabel);
		datoLinje.add(datoInnField);
		starttidLinje.add(starttidLabel);
		starttidLinje.add(starttidInnField);

	}

	/**
	 * The method enlarges fonts on buttons or labels.
	 *
	 * @param o the button or label.
	 * @param size of the new font.
	 */

	public void enlarge(Object o, int size) {
		if (o instanceof JButton) {
			font = new Font("Verdana", Font.BOLD, size);
			((JButton) o).setFont(font);
			((JButton) o).setForeground(Color.GREEN);
		} else if (o instanceof JLabel) {
			Font font5 = new Font("Verdana", Font.BOLD, size);
			((JLabel) o).setFont(font5);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bekreft) {

			int idInn = (Integer)(idInnField.getValue());
			SimpleDateFormat dfd = new SimpleDateFormat("dd-MM-yy");
			Date date = (Date) datoInnField.getValue();
			String datoInn = dfd.format(date);
			DateUtil u = new DateUtil();
			if (u.isLegalTime(datoInn)) {
				SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
				Date sTime = (Date) starttidInnField.getValue();
				String starttidInn = dft.format(sTime);
				Controller c = new Controller();
				c.deleteShift(idInn, datoInn, starttidInn);
				FeedbackHandler.successfulFeedBack("Sletting er utført");
			} 
			else {}
		}
	}

	/**
	 * @author David Ronen This method gives an warning that an operation
	 *         failed.
	 * @param feedBackText the message given.
	 */
	public void failedFeedBack(String feedBackText) {
		FeedbackHandler.failedFeedback(feedBackText);
	}
	/**
	 * 
	 */
	public boolean testShiftToRemove(int id, String date, String startTime){
		Controller c = new Controller();
		ArrayList<Shift> als = c.getShiftArr().getList();
		for (Shift entry : als){
			if(entry.getStartTime().equalsIgnoreCase(startTime) && entry.getId() == id && entry.getDate().equalsIgnoreCase(date)){
				return true;
			}
		}
		{
			FeedbackHandler.failedFeedback("Operasjon mislyktes!");
			return false;
		}
	}

}