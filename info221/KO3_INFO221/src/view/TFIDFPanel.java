package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ComputeTfIdf;
import controller.NormalizeUtil;

public class TFIDFPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextField term;
	private JTextField docid;
	private JButton finntfidf;
	private JTextArea textArea;
	private String melding = "Resultater:";

	public TFIDFPanel() {
		setLayout(new BorderLayout());

		JPanel topp = new JPanel();

		topp.add(new JLabel("term:"));
		term = new JTextField(15);
		topp.add(term);

		topp.add(new JLabel("docID:"));
		docid = new JTextField(5);
		topp.add(docid);

		finntfidf = new JButton("Finn TF-IDF");
		finntfidf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Finn TF-IDF")) {
					//get the text
					String word = term.getText();
					//get the doc id
					int id  = Integer.parseInt(docid.getText());
					//normalize the text
					word = NormalizeUtil.normalize(word);
					//compute the tf - idf
					double tfidf = ComputeTfIdf.compute(word, id);
					//parse the result
					String result = Double.toString(tfidf);
					//append the result
					melding(result);
				}
			}
		});
		topp.add(finntfidf);

		add(topp, BorderLayout.NORTH);

		textArea = new JTextArea(melding);
		add(new JScrollPane(textArea), BorderLayout.CENTER);

	}

	public void melding(String melding) {
		this.melding += "\n" + melding;
		textArea.setText(this.melding);
	}

	private void lastInn() {
		// ProcessPanel.termsMap.get(key);

		//		JOptionPane.showMessageDialog(
		//				this,
		//				"Fullfør implementasjonen (TFIDFPanel.java).");
	}
}
