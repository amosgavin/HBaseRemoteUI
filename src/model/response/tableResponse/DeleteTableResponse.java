package model.response.tableResponse;

import model.response.ServerResponse;

public class DeleteTableResponse extends ServerResponse {
	private String tableName;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
