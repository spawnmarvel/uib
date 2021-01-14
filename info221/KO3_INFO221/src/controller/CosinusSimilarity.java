package controller;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CosinusSimilarity {

	public static double cosSim(ArrayList<String> searchSequence, ArrayList<String> documentSequence) {
		HashSet<String> allWords = new HashSet<String>();
		allWords.addAll(searchSequence);
		allWords.addAll(documentSequence);
		double dotProduct = 0;
		double vector1 = 0;
		double vector2 = 0;
		for (String s : allWords)  {
			dotProduct += searchSequence.indexOf(s) * documentSequence.indexOf(s) ;
		}
		for (String k : searchSequence){
			vector1 += searchSequence.indexOf(k) * searchSequence.indexOf(k);
		}
		for (String k : documentSequence){
			vector2 += documentSequence.indexOf(k) * documentSequence.indexOf(k);
		}
		return dotProduct / Math.sqrt(vector1 * vector2);
	}

	
	
	
}


