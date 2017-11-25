Ext.define('HBaseRemoteUI.view.tableOperations.CreateTableController', {
	extend : 'Ext.app.ViewController',
	alias:'controller.createTableController',
	requires:['HBaseRemoteUI.model.hbaseTable.ColumnFamilyModel'],
	
	afterrenderPanel:function(){
		this.loadViewReferences();
		this.defineCFStore();
	},
	
	loadViewReferences:function(){
		var config= this.view.config;
		config.gridColumnFamilies=this.lookupReference('gridColumnFamilies');
		config.textFieldTableName=this.lookupReference('textFieldTableName');
		config.textFieldCFName=this.lookupReference('textFieldCFName');
	},
	
	defineCFStore:function(){
		var config= this.view.config;
		
		var store= Ext.create('Ext.data.Store',{
			model:'HBaseRemoteUI.model.hbaseTable.ColumnFamilyModel'
		});
		config.gridColumnFamilies.setStore(store);
	},
	
	
	
	addColumnFamily:function(){
		var config= this.view.config;
		var item= Ext.create('HBaseRemoteUI.model.hbaseTable.ColumnFamilyModel',{
			name:config.textFieldCFName.getValue()
		});
		config.gridColumnFamilies.store.add(item);
//		config.
	},
	
	
	createTable:function(){
		var config= this.view.config;
		
//		if(config.tableName==undefined || config.columnFamilies==null){
//			return;
//		}
		var input={};
		input.serviceName='createTable';
		input.name=config.textFieldTableName.getValue();
		
		var columnFamilies=[];
		for(var i=0;i<config.gridColumnFamilies.store.data.items.length;i++){
			var item=config.gridColumnFamilies.store.data.items[i].data.name; 
			columnFamilies.push(item);
		}
		
		input.columnFamilies=columnFamilies;
		
//		var inputStr= Ext.JSON.encode(input);
		var me = this;
		Ext.Ajax.request({
		    url: '../../HBaseRemoteUI/HBaseServlet.do',
		    method : "POST",
			async : true,
			headers : 'application/json; charset=utf-8',
			jsonData : input,
            waitMsg: 'Please Wait...',
            success : function(responseObject, o) {
				var result = Ext.decode(responseObject.responseText);
		        // process server response here
				me.callBackCreateTable(result);
		    },	
		    failure : function(responseObject) {
		    	debugger;
		    	var res = {};
				res.isError = true;
				res.errorType = 2;
				res.errorDetail = responseObject.status + ' - Server Error.';
				res.exception = responseObject.status + ' - Server Error.';
				result = Ext.JSON.encode(res);
			}
		});
	},
	callBackCreateTable:function(response){
		if(!response.isError){
			console.log(response.tableName+" olusturuldu");
		}else{
			console.log("olusturulamadı");
		}
	}
});