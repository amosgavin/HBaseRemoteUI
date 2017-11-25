package model.request.dataRequest;

public class UpdateRowRequest {
	String tableName;
	String colmFamName;
	String columnName;
	String newValue;
	String rowKey;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColmFamName() {
		return colmFamName;
	}
	public void setColmFamName(String colmFamName) {
		this.colmFamName = colmFamName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	
	
}
