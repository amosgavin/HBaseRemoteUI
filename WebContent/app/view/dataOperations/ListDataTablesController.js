Ext.define('HBaseRemoteUI.view.dataOperations.ListDataTablesController', {
	extend : 'Ext.app.ViewController',
	alias:'controller.listDataTablesController',
	requires : ['HBaseRemoteUI.model.hbaseTable.ListTableModel',
	            'HBaseRemoteUI.model.hbaseData.TableDataModel'],
	
	afterrenderPanel:function(){
		this.loadViewReferences();
		this.defineTableStore();
		this.defineDataStore();
	},
	
	loadViewReferences:function(){
		var config= this.view.config;
		config.gridDataTables=this.lookupReference('gridDataTables');
		config.btnTableLoad=this.lookupReference('btnTableLoad');
		config.gridData=this.lookupReference('gridData');
		config.loadDataTables=this.lookupReference('loadDataTables');
	},
	
	defineTableStore:function(){
		var config= this.view.config;
		
		var store= Ext.create('Ext.data.Store',{
			model:'HBaseRemoteUI.model.hbaseTable.ListTableModel'
		});
		config.gridDataTables.setStore(store);
	},
	
	defineDataStore:function(){
		var config=this.view.config;
		var store = Ext.create('Ext.data.Store',{
			model:'HBaseRemoteUI.model.hbaseData.TableDataModel'
		});
		config.gridData.setStore(store);
	},
	
	loadTables:function(){
		var config= this.view.config;
		config.btnTableLoad.setDisabled(true);
		
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
				me.callBackListTables(result);
				config.btnTableLoad.setDisabled(false);
		    },	
		    failure : function(responseObject) {
		    	config.btnTableLoad.setDisabled(false);
		    	var res = {};
				res.isError = true;
				res.errorType = 2;
				res.errorDetail = responseObject.status + ' - Server Error.';
				res.exception = responseObject.status + ' - Server Error.';
				result = Ext.JSON.encode(res);
			}
		});
		
	},

	callBackListTables:function(response){
		var config= this.view.config;
		if(!response.isError){
			config.gridDataTables.store.removeAll();
			for(var i=0;i<response.tableNames.length;i++){
				var item= Ext.create('HBaseRemoteUI.model.hbaseTable.ListTableModel',{
					name:response.tableNames[i]
				});
				config.gridDataTables.store.add(item);
			}
			console.log(response + "listelendi.");
		}else{
			console.log("listelenemedi");
		}
	},
	
	scanTable:function(grid,rowIndex,columnIndex,button){
		var config= this.view.config;
		config.gridDataTables.store.data.getAt(rowIndex).data.name;
		config.gridDataTables.setLoading(true);
		var input={};
		input.serviceName='scanTable';
		input.name = config.gridDataTables.store.data.getAt(rowIndex).data.name;
		
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
				me.callBackScanTable(result);
				config.gridDataTables.setLoading(false);
		    },	
		    failure : function(responseObject) {
		    	config.gridDataTables.setLoading(false);
		    	var res = {};
				res.isError = true;
				res.errorType = 2;
				res.errorDetail = responseObject.status + ' - Server Error.';
				res.exception = responseObject.status + ' - Server Error.';
				result = Ext.JSON.encode(res);
			}
		});
	},
	callBackScanTable:function(response){
		var config= this.view.config;
		if(!response.isError){
			debugger;
			
			
			this.prepareStoreData(response);
//			config.gridData.getView().refresh();
			
		}else{
			console.log("Tablo okunamadı");
		}
	},
	
	openContextMenu: function(target, record, item, index, e, eOpts) {
		var config= this.view.config;
		e.stopEvent();
		var menu = Ext.create('Ext.menu.Menu', {
		    width: 100,
		    floating: true,  
		    margin: '0 0 10 0',
		    renderTo: Ext.getBody(),  // usually rendered by it's containing component
		    items: [{
		        text: 'Insert Row',
		        handler:function(){
		        	console.log('inserted');
		        }
		    },{
		        text: 'Update',
		        handler:function(){
		        	console.log('Updated');
		        }
		        	
		    },{
		        text: 'Delete Row',
		        handler:function(){
		        	
//		    		
		        	debugger;
		        	Ext.Msg.prompt('New value', 'Please enter Tablename for rowkey ='+record.data.rowKey, function(btn, text){
		        	    if (btn == 'ok'){
		        	    	var tableName = text;
		        	    	handler: deleteRow(text);
		        	        console.log(text);
		        	    }
		        	});
		        }
		    }]
		});
		menu.showAt(e.getXY());

	},
	
	deleteRow:function(grid,rowIndex,columnIndex,button){
		var config= this.view.config;
		config.gridTables.store.data.getAt(rowIndex).data.name;
		
		var input={};
		input.serviceName='deleteRow';
		input.rowKey = config.gridData.store.data.getAt(rowIndex).data.rowKey;
		
		config.gridData.setLoading(true);
		
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
	prepareStoreData:function(result){
		var config= this.view.config;
		
		var familyAndColumn=[];
		var data=[];
		for(var i=0;i<result.tableCells.length;i++){
			var item=result.tableCells[i];
			var c=item.columnFamily+':'+item.qualifier;
			if(familyAndColumn.indexOf(c)===-1){
				familyAndColumn.push(c);
			}
			data.rowKey=item.rowKey;
			data.cfq=c;
			data.value=item.value;
			
			var item=Ext.create('HBaseRemoteUI.model.hbaseData.TableDataModel',{
				rowKey:item.rowKey,
				cfq:c,
				value:item.value,
			});
			config.gridData.store.add(item);
			
		}
		return data;
	}
	
});