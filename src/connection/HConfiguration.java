package connection;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HConfiguration {
	public static final String HBASE_CONFIGURATION_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
	public static final String HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT = "hbase.zookeeper.property.clientPort";

	public static Configuration getConfiguration() {
		Configuration conf;
		conf = HBaseConfiguration.create();

		// conf.setInt("timeout", 120000);
		conf.set("hbase.master", "*" + "172.17.13.91" + ":9000*");
		conf.set("hbase.zookeeper.quorum", "172.17.6.26,172.17.5.28");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		return conf;
    }

}
