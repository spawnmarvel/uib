package model;

public class DBData {
	
	private int id;
	private String title;
	
	public DBData(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return "ID:"+id+" Title:"+title;
	}

}
