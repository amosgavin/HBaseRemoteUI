package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import connection.HConfiguration;

public class TableOperations {
	private Configuration conf;
	private static Scanner input2;
	private String[] tableNames;

	public TableOperations() {
		conf = HConfiguration.getConfiguration();
	}

	public boolean createTable(String tableNameStr, ArrayList<String> cfNames) throws InterruptedException {

		System.out.println("Start create table...");
		TableName tableName = TableName.valueOf(tableNameStr);
		try {
			Connection conn = ConnectionFactory.createConnection(conf);
			Admin admin = conn.getAdmin();

			HTableDescriptor htDescriptor = new HTableDescriptor(tableName);
			System.out.println("htdesc initilazed");
			// control
//			if (admin.tableExists(tableName)) {
//				deleteTable(tableNameStr);
//			}
			// creating column family
			// creating column family
						for (String family : cfNames) {
							HColumnDescriptor descriptor = new HColumnDescriptor(family);
							htDescriptor.addFamily(descriptor);
						}
						System.out.println("Column Families created");
			System.out.println("Creating new table...");
			admin.createTable(htDescriptor);
			System.out.println("Created.");
			return true;
		} catch (MasterNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ZooKeeperConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteTable(String tableNameStr) throws InterruptedException, IOException {
		Connection conn = ConnectionFactory.createConnection(conf);
		TableName tableName = TableName.valueOf(tableNameStr);
		Admin admin = conn.getAdmin();
		if (!(admin.tableExists(tableName))) {
			System.out.println("Table already doesn't exist! ");
			return false;
		} else {
			try {
				System.out.println("Table: " + tableName + " is exist.");
				admin.disableTable(tableName);
				System.out.println("Dropping exist table..");
				admin.deleteTable(tableName);

				System.out.println("Dropped.");
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

	}

	public ArrayList<String> listTables() throws IOException {
		Connection conn = ConnectionFactory.createConnection(conf);
		Admin admin = conn.getAdmin();
		HTableDescriptor[] tableDescriptor = admin.listTables();
		ArrayList<String> tables = new ArrayList<String>();
		
		// printing all the table names.
		for (int i = 0; i < tableDescriptor.length; i++) {
			System.out.println(tableDescriptor[i].getNameAsString());
			tables.add(tableDescriptor[i].getNameAsString());
		}
		
		return tables;

	}

	public void createTableOp(String tableNameStr, ArrayList<String> cfNames) {
		TableOperations tableOperations = new TableOperations();
		try {
			tableOperations.createTable(tableNameStr,cfNames);
			// tableOperations.deleteTable(tableNameStr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteTableOp(String tableNameStr) {
		TableOperations tableOperations = new TableOperations();
		try {
			tableOperations.deleteTable(tableNameStr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableOperations tb = new TableOperations();
		String tableName = null;
		ArrayList<String> cfNames = new ArrayList<String>();
		String colmFamBuffer = "enter";
		int choise = 0;
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		input2 = new Scanner(System.in);
		
		System.out.println("Whic op? \n1-CreateTable\n2-DeleteTable\n3-List Tables");
		choise = input.nextInt();
		if ((choise == 1)) {
			System.out.println("Table name");
			tableName = input1.nextLine();
			while (!colmFamBuffer.equals("close")) {
				System.out.println("Enter Colm Fam Name ------------ type \"close\" for exit -------------");
				colmFamBuffer = input2.nextLine();
				cfNames.add(colmFamBuffer);
			}
		}
		
		switch (choise) {
		case 1:
			tb.createTableOp(tableName,cfNames);
			break;
		case 2:
			System.out.println("Table name");
			tableName = input1.nextLine();
			tb.deleteTableOp(tableName);
			break;
		case 3:
			try {
				tb.listTables();
			} catch (IOException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
		input.close();
		input1.close();
	}
}
