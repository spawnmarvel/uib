package db;

import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Main;
import model.DBData;
import static db.DBHandler.getDB;

public class QueryHandler {
	
	public ArrayList<DBData> getTitleID(String searchID, String tableName) {
		ArrayList<DBData> result = new ArrayList<DBData>();
		
		String query = "SELECT id,title FROM "+Main.tableNameForDocuments+" WHERE id="+searchID;		
		ResultSet resultSet;
		
		try {
			resultSet = getDB().runQuery(query);
		
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				result.add(new DBData(id, title));
			}
		
			resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
}
