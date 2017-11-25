//package client;
//
//import connection.HConfiguration;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.*;
//import org.apache.hadoop.hbase.client.Admin;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//
//import java.io.IOException;
//
///**
// * Created by dauut on 05/01/16.
// */
//public class CreateTable {
//    private Configuration conf;
//
//    public CreateTable(){
//        conf = HConfiguration.getConf();
//    }
//    public boolean createTable(String tableNameStr) throws InterruptedException {
//
//        System.out.println("Start create table...");
//        TableName tableName = TableName.valueOf(tableNameStr);
//        try {
//            Connection conn = ConnectionFactory.createConnection(conf);
//            Admin admin = conn.getAdmin();
//
//            HTableDescriptor htDescriptor = new HTableDescriptor(tableName);
//            System.out.println("htdesc initilazed");
//            // control
//            if (admin.tableExists(tableName)) {
//                deleteTable(tableNameStr);
//            }
//            // creating column family
//            htDescriptor.addFamily(new HColumnDescriptor("cf"));
//            System.out.println("Column Family created");
//            System.out.println("Creating new table...");
//            admin.createTable(htDescriptor);
//            System.out.println("Created.");
//            return true;
//        } catch (MasterNotRunningException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return false;
//        } catch (ZooKeeperConnectionException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return false;
//        }
//        public void createTableOp(String tableNameStr) {
//            TableOperations tableOperations = new TableOperations();
//            try {
//                createTable(tableNameStr);
//                // tableOperations.deleteTable(tableNameStr);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//}
