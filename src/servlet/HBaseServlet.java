package servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.ScanRequest;

import com.google.gson.Gson;

import client.TableOperations;
import model.BaseModel;
import model.response.dataResponse.ScanTableResponse;
import model.response.tableResponse.CreateTableResponse;
import model.response.tableResponse.DeleteTableResponse;
import model.response.tableResponse.ListTableResponse;
import model.request.dataRequest.ScanTableRequest;
import model.request.tableRequest.CreateTableRequest;
import model.request.tableRequest.DeleteTableRequest;
import service.DataOperationsService;
import service.TableOperationsService;

/**
 * Servlet implementation class HBaseServlet
 */
@WebServlet("/HBaseServlet")
public class HBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HBaseServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String s;
		String tableName;
		ArrayList<String> cfNames = new ArrayList<String>();

		while ((s = request.getReader().readLine()) != null) {
			sb.append(s);
		}
		String inputStr = sb.toString();
		System.out.println("input: " + inputStr);

		Gson gson = new Gson();
		BaseModel model = gson.fromJson(inputStr, BaseModel.class);
		TableOperationsService tableOperationsService = new TableOperationsService();
		DataOperationsService dataOperationsService = new DataOperationsService();
		
		if (model.getServiceName().equals(BaseModel.MODEL_CREATE_TABLE)) {
			
			CreateTableRequest createTableRequest = gson.fromJson(inputStr, CreateTableRequest.class);
			CreateTableResponse resp = tableOperationsService.createTable(createTableRequest);
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(resp));
			response.setStatus(HttpServletResponse.SC_OK);
			
		} else if (model.getServiceName().equals(BaseModel.MODEL_DELETE_TABLE)) {
			
			DeleteTableRequest deleteTableRequest = gson.fromJson(inputStr, DeleteTableRequest.class);
			DeleteTableResponse deleteTableResponse = tableOperationsService.deleteTable(deleteTableRequest);
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(deleteTableResponse));
			response.setStatus(HttpServletResponse.SC_OK);
			
		} else if (model.getServiceName().equals(BaseModel.MODEL_LIST_TABLE)) {
			
			ListTableResponse listTableResponse = tableOperationsService.listTable();
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(listTableResponse));
			response.setStatus(HttpServletResponse.SC_OK);
			
		}else if (model.getServiceName().equals(BaseModel.MODEL_SCAN_TABLE)){
			
			ScanTableRequest scanRequest = gson.fromJson(inputStr, ScanTableRequest.class);
			ScanTableResponse response2 = dataOperationsService.scanTable(scanRequest);
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(response2));
			response.setStatus(HttpServletResponse.SC_OK);
			
		}
	}

	public static void main(String[] args) {

	}

}
