Ext.define('HBaseRemoteUI.model.hbaseData.TableDataModel', {
	extend : 'Ext.data.Model',
	alias : 'model.tableDataModel',
	fields : [{
		name:'rowKey', type:'string'
	},{
		name:'cfq', type:'string'
	},{
		name:'value', type:'string'
	}]
});