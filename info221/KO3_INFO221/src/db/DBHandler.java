package db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.DocFlavor.STRING;

import controller.Main;

public class DBHandler {

	private static String name = null;
	private static String user = null;
	private static String password = null;
	private static String url = null;
	private java.sql.Statement statement;
	static java.sql.Connection con;

	private static DBHandler instance = null;
	public static DBHandler getDB() throws Exception {
		return instance == null ? (instance = new DBHandler()) : instance;
	}

	/**
	 * Default constructor takes database settings
	 *
	 * @throws Exception
	 */
	private DBHandler() throws Exception {
		if(name == null || user == null || password == null)
			throw new Exception("Ulovlige innstillinger: noen nødvendige parametre er ikke innstillt.");
	}

	public static void setConnectionInfo(String hostname, String dbName, String dbUser, String dbPassword) throws SQLException {
		name = dbName;
		user = dbUser;
		password = dbPassword;
		url = "jdbc:mysql://" + hostname + ":3306/" + dbName;
		con = DriverManager.getConnection(
				url, user, password);
	}

	/**
	 * Returns the result set from running as query
	 *
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public ResultSet runQuery(String query) throws Exception {

		statement = con.createStatement();
		ResultSet rs = statement.executeQuery(query);

		return rs;
	}

	public static void runInsertDocument(int docID,String docName, String document) throws Exception {
		String insertString = "INSERT INTO " + Main.tableNameForDocuments + " VALUES(?,?,?)";
		PreparedStatement ps;
		ps = con.prepareStatement(insertString);
		ps.setInt(1, docID);
		ps.setString(2,docName);
		ps.setString(3, document);
		ps.executeUpdate();
	}

	public void runQueries(String[] queries) throws Exception {

		statement = con.createStatement();
		for(int i = 0; i < queries.length; i++)
			statement.execute(queries[i]);
	}
	/**
	 * 
	 * a method for inserting all the distinct terms from the collection, and the id
	 * of the term
	 * @param id, the id given to the term
	 * @param term, the term to be inserted
	 * @throws SQLException
	 */
	public static void insertDisTerms(int id, String term) throws SQLException{
		String insertString = "INSERT INTO " + Main.tableNameForDist_terms + " VALUES(?,?)";
		PreparedStatement ps;
		ps = con.prepareStatement(insertString);
		ps.setInt(1, id);
		ps.setString(2, term);
		ps.executeUpdate();
	}
	/**
	 * a method for inserting term, document and frequency of the term in that specific
	 * document
	 * @param term, the term to be inserted
	 * @param docID, the document id where the term occurs
	 * @param frequency, the frequency of the term
	 * @throws SQLException
	 */
	public static void inserInvertIndex(String term, int docID,int frequency) throws SQLException{
		String insertString = "INSERT INTO " + Main.tableNameForInvertIndexWithFreq + " VALUES(?,?,?)";
		PreparedStatement ps;
		ps = con.prepareStatement(insertString);
		ps.setString(1, term);
		ps.setInt(2, docID);
		ps.setInt(3, frequency);
		ps.executeUpdate();
	}
	/**
	 * a method for inserting a term and how many documents it occurs in
	 * @param term, the term to be inserted
	 * @param docFrequency
	 * @throws SQLException
	 */
	public static void insertDocFrequency(String term, int docFrequency) throws SQLException{
		String insertString = "INSERT INTO " + Main.tableNameForDoc_Frequency + " VALUES(?,?)";
		PreparedStatement ps;
		ps = con.prepareStatement(insertString);
		ps.setString(1, term);
		ps.setInt(2,docFrequency);
		ps.executeUpdate();
	}
	/**
	 * a method for getting the document frequency of a term
	 * @param queryTerm, the term to get the frequency for
	 * @return the frequency for the term
	 * @throws Exception
	 */
	public static int getDF(String queryTerm) throws Exception{
		String getString = ("SELECT `Frequency` FROM "+Main.tableNameForDoc_Frequency + " WHERE `term`=(?)");
		PreparedStatement getTermFreq;
		getTermFreq = con.prepareStatement(getString);
		int frequency = 0;
		getTermFreq.setString(1, queryTerm);
		ResultSet rs = getTermFreq.executeQuery();
		while(rs.next()) {
			frequency = rs.getInt ("Frequency");
		}
		return frequency;
	}
	/**
	 * a method for getting the frequency of a term in a specific document
	 * @param term, the term to get the frequency for
	 * @param docId,the id of the document to look in
	 * @return the frequency of the term in the document
	 * @throws SQLException
	 */
	public static int getTermFrequencyInDocument(String term, int docId) throws SQLException {
		String getString = ("SELECT `Frequency` FROM "+Main.tableNameForInvertIndexWithFreq + " WHERE `term`=(?) AND `Doc_id`=(?)");
		PreparedStatement docIdTermFreq;
		docIdTermFreq = con.prepareStatement(getString);
		int frequency = 0;
		docIdTermFreq.setString(1, term);
		docIdTermFreq.setInt(2, docId);
		ResultSet rs = docIdTermFreq.executeQuery();
		while(rs.next()) {
			frequency += rs.getInt("Frequency");
		}

		return frequency;

	}
	/**
	 * returns a document name given an document id
	 * @param docId
	 * @return
	 * @throws SQLException
	 */
	public static String getDocName(int docId) throws SQLException {
		String docName = "";
		String getString = ("SELECT `Doc_Name` FROM "+Main.tableNameForDocuments + " WHERE `ID`=(?)");
		PreparedStatement docIdGet;
		docIdGet = con.prepareStatement(getString);
		docIdGet.setInt(1, docId);

		ResultSet rs = docIdGet.executeQuery();
		while(rs.next()) {
			docName = rs.getString("Doc_Name");
		}

		return docName;

	}
}
