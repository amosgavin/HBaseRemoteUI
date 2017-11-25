package model.request.tableRequest;

import java.util.ArrayList;

public class CreateTableRequest {

	private String name;
	private ArrayList<String> columnFamilies;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getColumnFamilies() {
		return columnFamilies;
	}
	
	public void setColumnFamilies(ArrayList<String> columnFamilies) {
		this.columnFamilies = columnFamilies;
	}
	
}
