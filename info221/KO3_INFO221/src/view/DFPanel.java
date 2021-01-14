package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.NormalizeUtil;

import model.DeAccenter;
import model.PorterStemmer;

import db.DBHandler;

public class DFPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextField term;
	private JButton finndf;
	private JTextArea textArea;
	private String melding = "Resultater:";
	PorterStemmer ps = new PorterStemmer();

	public DFPanel() {
		setLayout(new BorderLayout());

		JPanel topp = new JPanel();

		topp.add(new JLabel("term:"));
		term = new JTextField(25);
		topp.add(term);

		finndf = new JButton("Finn DF");
		finndf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Finn DF")) {
					//get the text from field
					String queryTerm = term.getText();
					//normalize the text
					queryTerm = NormalizeUtil.normalize(queryTerm);
					try {
						//get the frequency for the term
						int frequency =	DBHandler.getDF(queryTerm);
						//pares the frequency value
						String freq = Integer.toString(frequency);
						//append it to the text area
						melding(freq);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//				lastInn();
			}
		});
		topp.add(finndf);

		add(topp, BorderLayout.NORTH);

		textArea = new JTextArea(melding);
		add(new JScrollPane(textArea), BorderLayout.CENTER);

	}

	public void melding(String melding) {
		this.melding += "\n" + melding;
		textArea.setText(this.melding);
	}

	private void lastInn() {
		JOptionPane.showMessageDialog(
				this,
		"Fullfør implementasjonen (DFPanel.java).");
	}
}
