package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * a class which represents a document
 * @author Espen
 *
 */

public class Document {
	public int id;
	public String tittle;
	//the distinct words in the document
	public  HashSet<String> distinctTerm;
	//all the words in the document, including duplicates
	public  ArrayList<String> allWords;
	/**
	 * 
	 * Constructor for document
	 * @param id, the id of the document
	 * @param tittle, the tittel of the document
	 */
	public Document(int id, String tittle){
		distinctTerm = new HashSet<String>();
		allWords = new ArrayList<String>();
		this.id = id;
		this.tittle = tittle;



	}
	/**
	 * adds a word to the list (contains duplicates)
	 * @param word
	 */
	public void addWord(String word) {
		allWords.add(word);
	}
	/**
	 * adds a word to the list (no duplicates)
	 * @param term
	 */
	public void addUnique(String term) {
		distinctTerm.add(term);
	}

}
