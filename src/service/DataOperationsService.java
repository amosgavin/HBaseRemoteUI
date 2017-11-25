package service;

import java.io.IOException;

import client.DataOperations;
import model.request.dataRequest.DeleteRowRequest;
import model.request.dataRequest.ScanTableRequest;
import model.request.dataRequest.UpdateRowRequest;
import model.response.dataResponse.DeleteRowResponse;
import model.response.dataResponse.ScanTableResponse;
import model.response.dataResponse.UpdateRowResponse;

public class DataOperationsService {
	public ScanTableResponse scanTable(ScanTableRequest scanRequest) {
		DataOperations dataOperations = new DataOperations();
		ScanTableResponse scanResponse= new ScanTableResponse();
		try {
			scanResponse = dataOperations.scanTable(scanRequest.getName());
		} catch (IOException e) {
			scanResponse.setError(true);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scanResponse;
	}

//	public UpdateRowResponse updateRow(UpdateRowRequest updateRowRequest){
//		DataOperations dataOperations = new DataOperations();
//		UpdateRowResponse updateRowResponse = new UpdateRowResponse();
//		
//		updateRowResponse = dataOperations.updateData(updateRowRequest.getTableName(),updateRowRequest.getColmFamName(),updateRowRequest.getColumnName(),updateRowRequest.getNewValue(),updateRowRequest.getRowKey());
//		return null;
//	}
	
	
	
	public void deleteRow(DeleteRowRequest deleteRowRequest){
		DataOperations dataOperations = new DataOperations();
		DeleteRowResponse deleteRowResponse = new DeleteRowResponse();
		
		try {
			dataOperations.deleteRow(deleteRowRequest.getName(), deleteRowRequest.getRowKey());
		} catch (IOException e) {
			deleteRowResponse.setError(true);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
