package model.response.tableResponse;

import java.util.ArrayList;

import model.response.ServerResponse;

public class ListTableResponse extends ServerResponse {
	private ArrayList<String> tableNames;

	public ArrayList<String> getTableNames() {
		return tableNames;
	}
	public void setTableNames(ArrayList<String> tableNames) {
		this.tableNames = tableNames;
	}
}
