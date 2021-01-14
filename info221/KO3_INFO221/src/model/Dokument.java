package model;

import static db.DBHandler.getDB;

import java.sql.ResultSet;

import controller.Main;
import db.DBHandler;

public class Dokument
{
	private int docID;
	private String dokument;
	
	
	private Dokument() {}
	
	public static Dokument hentDokument(int docId) throws Exception {
		ResultSet rs = getDB().runQuery("SELECT `Document` FROM "+Main.tableNameForDocuments+" where `id`=" + docId);
		
		rs.next();
		
		Dokument resultat = new Dokument();
		resultat.dokument = rs.getString("Document");
		resultat.docID = docId;
		
		return resultat;
	}
	
	public static Dokument settInn(int id, String document, String docName) throws Exception {
		DBHandler.runInsertDocument(id,document, docName);
		
		return hentDokument(id);
	}
	
	public int docID() { return docID; }
	
	public String dokument() { return dokument; }
}
