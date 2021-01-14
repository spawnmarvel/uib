package view;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.Query;
import javax.print.Doc;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ComputeTfIdf;
import controller.Euclidean;
import controller.NormalizeUtil;
import controller.PresentScoreOfSearch;
import db.DBHandler;

public class SearchPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextField søkefelt;
	private JTextArea resultatfelt;

	public SearchPanel() {
		setLayout(new BorderLayout());

		JPanel toppPanel = new JPanel(new BorderLayout());
		toppPanel.add(søkefelt = new JTextField(), 
				BorderLayout.CENTER);
		JButton søk = new JButton("Søk");
		søk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					søk();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}

			}
		});
		toppPanel.add(søk, BorderLayout.EAST);
		add(toppPanel, BorderLayout.NORTH);
		add(new JScrollPane(resultatfelt = new JTextArea()), 
				BorderLayout.CENTER);
		resultatfelt.setText("Resultater:");
	}

	private void søk() throws IOException, SQLException {
		CosinusScore();
		//		
		//		JOptionPane.showMessageDialog(
		//				this,
		//				"Fullfør implementasjonen for søking (\""+søketekst+"\")");
	}
	/**
	 * jkl070
	 * method cosinus score
	 * @throws SQLException
	 */
	public void CosinusScore() throws SQLException {
		String søketekst = søkefelt.getText();
		//split the text into array if words > 1
		String [] termSequenceRaw = søketekst.split(" ");
		//an normalized array
		String [] termsequenceNormalized = new String [(termSequenceRaw.length)];
		for (int i = 0; i < termSequenceRaw.length; i++) {
			//normalize every word from the raw array
			String NormalizeTerm = NormalizeUtil.normalize(termSequenceRaw[i]);
			//add the normalized word to the normalized array
			termsequenceNormalized[i] = NormalizeTerm.trim();
		}
		//må endre til txt
		File rotkatalog = new File("txt");//txt
		File[] filer = rotkatalog.listFiles();
		//the score for each term in the query
		double termScore = 0.0;
		//an array of documents scores, size = file length
		double []scores = new double[filer.length+1];
		for (int j = 0; j < termsequenceNormalized.length; j++) {
			//for every query word
			for (int i = 1; i < filer.length+1; i++) {
				//for every file i
				try{
					termScore = ComputeTfIdf.compute(termsequenceNormalized[j], i);
					if(!(termScore == 0.0)) {
						int tempScore = DBHandler.getTermFrequencyInDocument( termsequenceNormalized[j], i);
						double documentScore = tempScore;
						//score i, the document i
						scores[i] += termScore * documentScore;
					}
				}

				catch (NullPointerException n) {
					//if term scorer throws exception catch and do nothing
					//it will not be in the top list, not valid for result
				}
			}
		}
		//a map to keep track of the scores
		HashMap<String, Double> listOfScores = new HashMap<String, Double>();
		//for all the scores in the score array
		//Euclidean.normalizeEuclidean(scores);
		for (int k = 1; k < scores.length; k++) {
			//scores[k] = scores[k] / 
			String tittelOfDoc = DBHandler.getDocName(k);
			//add the scores with document id to the map
			listOfScores.put(tittelOfDoc, scores[k]);
		}
		//iterate over the map with the scores sort them and present them
		melding(PresentScoreOfSearch.printTop(PresentScoreOfSearch.sortByValues(listOfScores)));
	}



	public void melding(String melding) {
		resultatfelt.setText(melding);
	}
}
