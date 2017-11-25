package model.request.dataRequest;

public class DeleteRowRequest {
	String name;
	String rowKey;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRowKey() {
		return rowKey;
	}
	
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
}
