package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;

import controller.NormalizeUtil;

import db.DBHandler;

import model.DeAccenter;
import model.Document;
import model.Dokument;
import model.PorterStemmer;
import model.Term;

public class ProcessPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private File rotkatalog = null;
	private JTextField filnavn;
	private JButton browse;
	private JButton lastinn;
	private JFileChooser fileChooser = new JFileChooser();
	private JTextArea textArea;
	public static int size;
	PorterStemmer ps = new PorterStemmer();
	int termID = 1;
	Set<String> s = new HashSet<String>();
	public static HashMap<String, Term> termsMap;
	public static HashMap<Integer, String> docsMap;

	public ProcessPanel() {
		setLayout(new BorderLayout());

		JPanel topp = new JPanel();
		//må endre til txt
		rotkatalog = new File("txt");

		filnavn = new JTextField(25);
		filnavn.setText(rotkatalog.getPath());
		filnavn.setEditable(false);
		topp.add(filnavn);

		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		browse = new JButton("Fil...");
		browse.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				velgFil();
			}
		});
		topp.add(browse);

		lastinn = new JButton("Last inn");
		lastinn.setEnabled(rotkatalog != null);
		lastinn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastInn();
			}
		});
		topp.add(lastinn);

		add(topp, BorderLayout.NORTH);

		textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		melding("Meldinger:");

	}

	public void melding(String melding) {
		textArea.append(melding + "\n");
	}

	private void velgFil() {
		fileChooser.showOpenDialog(this);
		if(fileChooser.getSelectedFile() == null) return;
		rotkatalog = fileChooser.getSelectedFile();
		filnavn.setText(rotkatalog.getAbsolutePath());
		lastinn.setEnabled(rotkatalog != null);
	}

	private void lastInn() {
		try {
			DBHandler handler = DBHandler.getDB();
			File[] filer = rotkatalog.listFiles();
			//a list holding all the documents in the file
			ArrayList<Document> allDocs = new ArrayList<Document>();
			size = filer.length;
			//a map of word, and term object
			termsMap = new HashMap<String, Term>();
			//a map of doc id and document name
			docsMap = new HashMap<Integer, String>();
			for(int i = 0; i < filer.length; i++) {
				//for each i, create a new document
				Document doc = new Document(i+1,filer[i].getName());
				docsMap.put(i, filer[i].getName());
				melding((i+1) + "/" + filer.length + " : " +
						filer[i].getName());
				//insert the i document into database
				DBHandler.runInsertDocument(doc.id,filer[i].getName(), lesInnFil(filer[i]));
				//add the i document to the list of all the documents
				allDocs.add(doc);
				//take the text from the file i
				String text = lesInnFil(filer[i]);
				//split the text into an array
				String[]words = text.split(" ");
				String w = "";
				//for every word in the words array
				for (int k = 0; k < words.length; k++) {
					w = words[k];
					//Normalize
					w = NormalizeUtil.normalize(w);
					//add unique words to the document
					doc.addUnique(w);
					//add every word to the same document
					doc.addWord(w);
					if(s.add(w)){//distinct word
						//create a new term
						Term t = new Term(termID, w, size);
						termID++;
						t.DF++;
						t.docOccurencies[doc.id] = true;
						t.frequencies[doc.id]++;
						termsMap.put(w, t);
						//insert the distinct term into database
						if(!(t.term.length() == 0)) {
							DBHandler.insertDisTerms(t.id, t.term);	
						}
					}
					else{//not new word
						Term myTerm = termsMap.get(w);
						myTerm.frequencies[doc.id]++;
						doc.addWord(w);
						if(myTerm.docOccurencies[doc.id] == false){
							myTerm.DF++;
							myTerm.docOccurencies[doc.id] = true;
						}
					}

				}
			}
			//insert the the term and its document frequency
			Iterator<String> itr = s.iterator();
			while(itr.hasNext()){
				String myString = itr.next();
				Term myTerm = termsMap.get(myString);
				String term = myTerm.term;
				int DF = myTerm.DF;
				if(!(term.length() == 0)){
				DBHandler.insertDocFrequency(term, DF);
				}
			}
			//insert a term with its frequency in the document it occurs
			for (int i = 0; i < allDocs.size();i++) {
				Document d = allDocs.get(i);
				Term t = null;
				int docID = d.id;
				String term = "";
				Iterator<String> ita = d.distinctTerm.iterator();
				while(ita.hasNext()) {
					String tempWord = ita.next();
					t = termsMap.get(tempWord);
					term = t.term;
					int freq = t.frequencies[docID];

					DBHandler.inserInvertIndex(term, docID,freq);

				}

			}

		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(
					this,
					e.getMessage(),
					"Feil!",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public static String lesInnFil(File fil) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fil));
		StringBuilder resultat = new StringBuilder();
		String linje = null;
		while((linje = br.readLine()) != null)
			resultat.append(linje + "\n");
		return resultat.toString();
	}


}
