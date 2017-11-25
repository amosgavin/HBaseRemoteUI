//package client;
//
//import connection.HConfiguration;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Admin;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//
//import java.io.IOException;
//
///**
// * Created by dauut on 05/01/16.
// */
//public class DeleteTable {
//    private Configuration conf;
//
//    public DeleteTable(){
//        conf = HConfiguration.getConf();
//    }
//
//    public boolean deleteTable(String tableNameStr) throws InterruptedException, IOException {
//        Connection conn = ConnectionFactory.createConnection(conf);
//        TableName tableName = TableName.valueOf(tableNameStr);
//        Admin admin = conn.getAdmin();
//        if (!(admin.tableExists(tableName))) {
//            System.out.println("Table already doesn't exist! ");
//            return false;
//        } else {
//            try {
//                System.out.println("Table: " + tableName + " is exist.");
//                admin.disableTable(tableName);
//                System.out.println("Dropping exist table..");
//                admin.deleteTable(tableName);
//
//                System.out.println("Dropped.");
//                return true;
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return false;
//            }
//        }
//        public void deleteTableOp(String tableNameStr) {
//            TableOperations tableOperations = new TableOperations();
//            try {
//                deleteTable(tableNameStr);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//
//        }
//    }
//}
