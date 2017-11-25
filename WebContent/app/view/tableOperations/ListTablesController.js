Ext.define('HBaseRemoteUI.view.tableOperations.ListTablesController', {
	extend : 'Ext.app.ViewController',
	alias:'controller.listTablesController',
	requires : ['HBaseRemoteUI.model.hbaseTable.ListTableModel'],
	
	afterrenderPanel:function(){
		this.loadViewReferences();
		this.defineTableStore();
	},
	
	loadViewReferences:function(){
		var config= this.view.config;
		config.gridTables=this.lookupReference('gridTables');
		config.btnLoad=this.lookupReference('btnLoad');
	},
	
	defineTableStore:function(){
		var config= this.view.config;
		
		var store= Ext.create('Ext.data.Store',{
			model:'HBaseRemoteUI.model.hbaseTable.ListTableModel'
		});
		config.gridTables.setStore(store);
	},
	
	loadTables:function(){
		var config= this.view.config;
		config.btnLoad.setDisabled(true);
		
		var input={};
		input.serviceName='listTable';
		
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
				config.btnLoad.setDisabled(false);
		    },	
		    failure : function(responseObject) {
		    	config.btnLoad.setDisabled(false);
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
		var config= this.view.config;
		if(!response.isError){
			config.gridTables.store.removeAll();
			for(var i=0;i<response.tableNames.length;i++){
				var item= Ext.create('HBaseRemoteUI.model.hbaseTable.ListTableModel',{
					name:response.tableNames[i]
				});
				config.gridTables.store.add(item);
			}
			

			console.log(response + "listelendi.");
		}else{
			console.log("listelenemedi");
		}
	},
	
	deleteTable:function(grid,rowIndex,columnIndex,button){
		var config= this.view.config;
		config.gridTables.store.data.getAt(rowIndex).data.name;
		
		var input={};
		input.serviceName='deleteTable';
		input.name = config.gridTables.store.data.getAt(rowIndex).data.name;
		
		config.gridTables.setLoading(true);
		
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
				me.callBackCreateTable2(result);
				config.btnLoad.setDisabled(false);
				config.gridTables.setLoading(false);
		    },	
		    failure : function(responseObject) {
		    	config.btnLoad.setDisabled(false);
		    	config.gridTables.setLoading(false);
		    	var res = {};
				res.isError = true;
				res.errorType = 2;
				res.errorDetail = responseObject.status + ' - Server Error.';
				res.exception = responseObject.status + ' - Server Error.';
				result = Ext.JSON.encode(res);
			}
		});
	},
	callBackCreateTable2:function(response){
		var config= this.view.config;
		if(!response.isError){
			console.log(response.tableName + "basariyla silindi");
			Ext.toast({
			     html: response.tableName + ' basariyla silindi',
			     title: 'BILGI',
			     width: 200,
			     align: 't'
			 });
			for(var i=0;config.gridTables.store.data.items.length; i++){
				if(response.tableName == config.gridTables.store.data.getAt(i).data.name){
					config.gridTables.store.removeAt(i);
					break;
				}
			}
		}else{
			console.log("Tablo Silinemedi");
		}
	}
});