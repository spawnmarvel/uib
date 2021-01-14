package controller;

import model.DeAccenter;
import model.PorterStemmer;
/**
 * A class which has a static method for task 4 a 
 * @
 *
 */
public class NormalizeUtil {

   /**
    * method for normalizing a word according to specification
    * from task 4 a
    * @param term, the term to be normalized
    * @return the normalized term
    */
	public static String normalize(String term) {
		PorterStemmer ps = new PorterStemmer();
		String queryTerm = term;
		queryTerm = DeAccenter.unAccent(queryTerm);
		queryTerm = queryTerm.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
		queryTerm =  ps.stem(queryTerm);
		return queryTerm;

	}

}
