package controller;

import java.sql.ResultSet;
import java.util.regex.Pattern;

import javax.swing.text.TabExpander;

import db.DBHandler;
import view.MainFrame;

public abstract class Main {
	public static String tableNameForDocuments ="documents2";
	public static String tableNameForDist_terms ="Dist_term";
	public static String tableNameForInvertIndexWithFreq ="Invert_Index_With_Freq";
	public static String tableNameForDoc_Frequency ="Doc_Frequency";


	public static void main(String[] args) throws Exception {
		System.out.println((Runtime.getRuntime().maxMemory()));
		DBHandler.setConnectionInfo(
				"hoguslg.uib.no",
				"jkl070",
				"jkl070",
				"javafrosk");

		new MainFrame();

	}

}
