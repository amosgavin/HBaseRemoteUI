package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import connection.HConfiguration;
import model.HBaseCol;
import model.ScanColumnsModel;
import model.TableCellModel;
import model.response.dataResponse.ScanTableResponse;

/**
 * Created by dauut on 02/01/16.
 */
public class DataOperations {
	private Configuration conf;

	public DataOperations() {
		conf = HConfiguration.getConfiguration();
	}

	public void insertData(String tableName, HashSet<HBaseCol> columns, String rowKey, List<String> vals)
			throws IOException {
		Connection conn = ConnectionFactory.createConnection(conf);
		Table table = conn.getTable(TableName.valueOf(tableName));
		insertRow(table, columns, rowKey, vals);
	}

	public void insertRow(Table table, HashSet<HBaseCol> columns, String rowKey, List<String> vals) throws IOException {

		// Instantiating Put class
		// accepts a row name.
		Put put = new Put(Bytes.toBytes(rowKey));

		// adding values using add() method
		// accepts column family name, qualifier/row name ,value
		int col = 0;

		for (HBaseCol column : columns) {
			if (col >= vals.size()) {
				break;
			}
			put.addColumn(column.family.getBytes("UTF-8"), column.qualifier.getBytes(), vals.get(col).getBytes());
			col++;
		}


		// Saving the put Instance to the HTable.
		table.put(put);
		System.out.println("data inserted");

		// closing HTable
		table.close();

	}

	public void updateData(String tableName, String colmFamName, String columnName, String newValue, String rowKey)
			throws IOException {

		Connection conn = ConnectionFactory.createConnection(conf);
		Table table = conn.getTable(TableName.valueOf(tableName));
		// Instantiating Put class
		// accepts a row name
		Put p = new Put(Bytes.toBytes(rowKey));
		// Updating a cell value
		p.addColumn(Bytes.toBytes(colmFamName), Bytes.toBytes(columnName), Bytes.toBytes(newValue));
		// Saving the put Instance to the HTable.
		table.put(p);
		System.out.println("data Updated");

		// closing HTable
		table.close();

	}

	public void readData(String tableName, String rowKey) throws IOException {

		Connection conn = ConnectionFactory.createConnection(conf);
		Table table = conn.getTable(TableName.valueOf(tableName));

		Get get = new Get(rowKey.getBytes());
		Result rs = table.get(get);
		for (KeyValue kv : rs.raw()) {
			System.out.print(new String(kv.getRow()) + " ");
			System.out.print(new String(kv.getFamily()) + ":");
			System.out.print(new String(kv.getQualifier()) + " ");
			System.out.print(kv.getTimestamp() + " ");
			System.out.println(new String(kv.getValue()));
		}


	}

	public void deleteRow(String tableName, String rowKey) throws IOException {

		Connection conn = ConnectionFactory.createConnection(conf);
		Table table = conn.getTable(TableName.valueOf(tableName));

		List<Delete> list = new ArrayList<Delete>();
		Delete del = new Delete(rowKey.getBytes());
		list.add(del);
		table.delete(list);
		System.out.println("Record deleted " + rowKey + "Status:Finish");

		// closing the HTable object
		table.close();
	}

	public ScanTableResponse scanTable(String tableName) throws IOException {// ,
																				// ArrayList<String>
																				// cfNames
		Connection conn = ConnectionFactory.createConnection(conf);
		Admin admin = conn.getAdmin();
		TableName controlTableName = TableName.valueOf(tableName);
		if (!admin.tableExists(controlTableName)) {
			System.out.println("Table doesn not exist!");
			return null;
		}

		Table table = conn.getTable(TableName.valueOf(tableName));
		// Instantiating the Scan class
		Scan scan = new Scan();

		ArrayList<TableCellModel> resultList = new ArrayList<>();

		ResultScanner scanner = table.getScanner(scan);
		for (Result res : scanner) {
			for (Cell c : res.rawCells()) {
				TableCellModel item = new TableCellModel();
				item.setColumnFamily(Bytes.toString(CellUtil.cloneFamily(c)));
				;
				item.setQualifier(Bytes.toString(CellUtil.cloneQualifier(c)));
				;
				item.setRowKey(Bytes.toString(CellUtil.cloneRow(c)));
				;
				item.setValue(Bytes.toString(CellUtil.cloneValue(c)));

				System.out.println(Bytes.toString(CellUtil.cloneRow(c)) + " ==> "
						+ Bytes.toString(CellUtil.cloneFamily(c)) + " {" + Bytes.toString(CellUtil.cloneQualifier(c))
						+ ":" + Bytes.toString(CellUtil.cloneValue(c)) + "}");
				resultList.add(item);
			}
		}

		ScanTableResponse response = new ScanTableResponse();
		response.setTableCells(resultList);
		return response;
	}

	public ArrayList<ScanColumnsModel> scanColumns(String tableName) throws IOException {
		ArrayList<ScanColumnsModel> resultList = new ArrayList<>();
		Connection conn = ConnectionFactory.createConnection(conf);
		Admin admin = conn.getAdmin();
		TableName controlTableName = TableName.valueOf(tableName);

		if (!admin.tableExists(controlTableName)) {
			System.out.println("Table doesn not exist!");
			return null;
		}

		Table table = conn.getTable(TableName.valueOf(tableName));
		// Instantiating the Scan class
		Scan scan = new Scan();

		ResultScanner scanner = table.getScanner(scan);
		// for (Result res : scanner) {
		for (Cell c : scanner.next().rawCells()) {
			ScanColumnsModel item = new ScanColumnsModel();
			item.setColumnFamily(Bytes.toString(CellUtil.cloneFamily(c)));
			item.setQualifier(Bytes.toString(CellUtil.cloneQualifier(c)));
			/*
			 * System.out.println(Bytes.toString(CellUtil.cloneRow(res.rawCells(
			 * )[0])) + " ==> " +
			 * Bytes.toString(CellUtil.cloneFamily(res.rawCells()[0])) + " {" +
			 * Bytes.toString(CellUtil.cloneQualifier(res.rawCells()[0])) + ":"
			 * + Bytes.toString(CellUtil.cloneValue(res.rawCells()[0])) + "}");
			 */
			resultList.add(item);

		}
		// }

//		for (int i = 0; i < resultList.size(); i++) {
//			System.out.println("ColFam: " + i + resultList.get(i).getColumnFamily());
//			System.out.println("Col: " + i + resultList.get(i).getQualifier());
//		}

		return resultList;

	}

	public static void main(String[] args) throws IOException {
		DataOperations dataOperations = new DataOperations();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);

		String tableName = null;
		String rowKey = null;
		ArrayList<String> cfNames = new ArrayList<String>();
		String uniuqeColFamName = null;
		String uniqueColName = null;
		String cellNewValue = null;
		String[] insertVals = null;
		HashMap<String, String> insertObj = new HashMap<>();
		int option = 10;

		while (option != 0) {
			System.out.println("");
			System.out.println("Menu:");
			System.out.println("1 - Scan a table");
			System.out.println("2 - Delete a row");
			System.out.println("3 - Update a row");
			System.out.println("4 - Read a row");
			System.out.println("5 - Insert a row");
			
			System.out.println("0 - EXIT!");
			option = in.nextInt();
			if(option < 0 || option > 5){
				System.out.println("Please choose a proper option!");
			}
			switch (option) {
			case 1:
				System.out.println("Please enter table name to scan:");
				tableName = in1.nextLine();
				dataOperations.scanTable(tableName);
				break;

			case 2:
				System.out.println("Please enter table name for delete row:");
				tableName = in1.nextLine();

				System.out.println("Please enter rowKey to delete");
				rowKey = in1.nextLine();

				dataOperations.deleteRow(tableName, rowKey);
				in1.close();

				break;

			case 3:
				System.out.println("Please enter table name for update a cell:");
				tableName = in1.nextLine();

				System.out.println("Please enter column family name");
				uniuqeColFamName = in1.nextLine();

				System.out.println("Please enter column name");
				uniqueColName = in1.nextLine();

				System.out.println("Please enter rowkey");
				rowKey = in1.nextLine();

				System.out.println("Please enter new value");
				cellNewValue = in1.nextLine();

				dataOperations.updateData(tableName, uniuqeColFamName, uniqueColName, cellNewValue, rowKey);
				in1.close();
				break;
			case 4:
				System.out.println("Please enter table name to read a row");
				tableName = in1.nextLine();

				System.out.println("Please enter rowKey to read");
				rowKey = in1.nextLine();

				dataOperations.readData(tableName, rowKey);
				break;
			case 5:
				HashSet<HBaseCol> columns = new HashSet<HBaseCol>();
				List<String> vals = new ArrayList<>();
				ArrayList<ScanColumnsModel> resultlist = new ArrayList<>();

				System.out.println("Please enter table name to insert a row");
				tableName = in1.nextLine();

				System.out.println("Please enter rowKey to insert");
				rowKey = in1.nextLine();

				resultlist = dataOperations.scanColumns(tableName);

				int i = 0;
				String valBuffer = null;

				while (i < resultlist.size()) {
					System.out.println("Please value for " + resultlist.get(i).getColumnFamily() + ":"
							+ resultlist.get(i).getQualifier());
					columns.add(new HBaseCol(resultlist.get(i).getColumnFamily(), resultlist.get(i).getQualifier()));
					valBuffer = in1.nextLine();
					vals.add(valBuffer);
					i++;
				}

				dataOperations.insertData(tableName, columns, rowKey, vals);
				break;
			}
		}
		
		System.out.println("done");
	}
}
