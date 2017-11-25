package model;

public class BaseModel {
	public static final String MODEL_CREATE_TABLE="createTable";
	public static final String MODEL_DELETE_TABLE="deleteTable";	
	public static final String  MODEL_LIST_TABLE="listTable";
	public static final String  MODEL_SCAN_TABLE="scanTable";
	public static final String  MODEL_DELETE_ROW="deleteRow";
	
	private String serviceName;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
