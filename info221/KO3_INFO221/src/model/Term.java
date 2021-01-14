package model;
/**
 * A class which represents a word, term
 * @
 *
 */
public class Term {
	//if of term
	public int id;
	//the term
	public String term;
	//array of document , mapped with doc id to cell in array
	public boolean[]docOccurencies;
	//array which holds the frequencies of a term in a document
	public int[]frequencies;
	//the document frequency the term
	public int DF;
    /**
     * Constructor for term
     * @param id, the id
     * @param term, the string term
     * @param size, size of the hole collection, all the files / documents
     */
	public Term(int id, String term, int size) {
		DF = 0;//initialized to zero
		this.id = id;
		this.term = term;
		frequencies = new int[size+1];
		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = 0;//initialized to zero for frequency
		}
		docOccurencies = new boolean[size+1];
		for (int i = 0; i < docOccurencies.length; i++) {
			docOccurencies[i] = false;//initialized to false
		}
	}

}
