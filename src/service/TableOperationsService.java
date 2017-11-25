package service;

import java.io.IOException;

import client.TableOperations;
import model.response.tableResponse.CreateTableResponse;
import model.response.tableResponse.DeleteTableResponse;
import model.response.tableResponse.ListTableResponse;
import model.request.tableRequest.CreateTableRequest;
import model.request.tableRequest.DeleteTableRequest;

public class TableOperationsService {
	public CreateTableResponse createTable(CreateTableRequest createTableRequest){
		TableOperations to = new TableOperations();
		CreateTableResponse createTableResponse= new CreateTableResponse();
		
		createTableResponse.setTableName(createTableRequest.getName());
		try {
			to.createTable(createTableRequest.getName(), createTableRequest.getColumnFamilies());
		} catch (InterruptedException e) {
			createTableResponse.setError(true);
			createTableResponse.setResponseMessage(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createTableResponse;
	}
	
	public DeleteTableResponse deleteTable(DeleteTableRequest deleteTableRequest){
		TableOperations to = new TableOperations();
		DeleteTableResponse deleteTableResponse = new DeleteTableResponse();
		
		deleteTableResponse.setTableName(deleteTableRequest.getName());
		
		try {
			to.deleteTable(deleteTableRequest.getName());
		} catch (InterruptedException | IOException e) {
			deleteTableResponse.setError(true);
			deleteTableResponse.setResponseMessage(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteTableResponse;
	}
	
	public ListTableResponse listTable(){
		TableOperations to = new TableOperations();
		ListTableResponse listTableResponse = new ListTableResponse();
		try {
			listTableResponse.setTableNames(to.listTables());
		} catch (IOException e) {
			listTableResponse.setError(true);
			listTableResponse.setResponseMessage(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTableResponse;
	}
}
