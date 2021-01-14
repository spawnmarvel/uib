package controller;

import view.ProcessPanel;
import model.Term;
/**
 * A class which has a static method for computing
 * term frequency - inverted document frequency
 * @
 *
 */
public class ComputeTfIdf {
    
	/**
	 * method compute term frequency - inverted document frequency
	 * @param term, the term in the document
	 * @param docId, the id of the specific document
	 * @return the value of tf - idf
	 */
	public static double compute(String term, int docId) {
		int size = ProcessPanel.size;
		Term myTerm = ProcessPanel.termsMap.get(term);
        double tf = myTerm.frequencies[docId];
        double df = myTerm.DF;
        double factor = size/df;
        double idf = StrictMath.log10(factor);
        double tfidf = tf*idf;
		return tfidf;


	}

}
