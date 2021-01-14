package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.Controller;
import control.DateUtil;

/**
 * @author Hedvig Andersland and Aslak Rødder
 *
 */
public class DeleteShiftGUI implements ActionListener {

	private JFrame frame;
	private JPanel window, idLinje, datoLinje, starttidLinje, bekreftLinje,
			skift;
	private JLabel idNummerLabel, datoLabel, starttidLabel;
	private JTextField idInnField, datoInnField, starttidInnField;
	private JButton bekreft;
	private Font font;

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

		idInnField = new JTextField(EMP_LENGTH);
		datoInnField = new JTextField(DATE_LENGTH);
		starttidInnField = new JTextField(TIME_LENGTH);

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
			if (idInnField.getText().equalsIgnoreCase("")
					|| datoInnField.getText().equalsIgnoreCase("")
					|| starttidInnField.getText().equalsIgnoreCase("")) {
				failedFeedBack("Alle ikke-valgfrie felt må være fylt ut");
				new DeleteShiftGUI();
			} else {
				try {
					int idInn = Integer.parseInt(idInnField.getText());
					String datoInn = datoInnField.getText();
					DateUtil u = new DateUtil();
					if (u.isLegalTime(datoInn)) {
						String starttidInn = starttidInnField.getText();
						Controller c = new Controller();
						c.deleteShift(idInn, datoInn, starttidInn);
						idInnField.setText("");
						datoInnField.setText("");
						starttidInnField.setText("");
					} 
					else {
					}
				} catch (Exception NumberFormatException) {
					failedFeedBack("ID-nummeret finnes ikke");
				}
			}
		}
	}

	/**
	 * @author David Ronen This method gives an warning that an operation
	 *         failed.
	 * @param feedBackText the message given.
	 */
	public void failedFeedBack(String feedBackText) {
		String title = "En feil har oppstått";
		String message = feedBackText;
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.OK_CANCEL_OPTION);
	}

}