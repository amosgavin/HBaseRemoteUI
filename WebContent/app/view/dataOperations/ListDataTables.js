Ext.define('HBaseRemoteUI.view.dataOperations.ListDataTables', {
	extend : 'Ext.panel.Panel',
	requires : [ 'HBaseRemoteUI.view.dataOperations.ListDataTablesController'],
	xtype : 'listDataTables',
	controller : 'listDataTablesController',
	layout:{
		type : 'hbox',
		pack : 'start',
		align:'stretch'
	},
	
	config:{
		gridDataTables:null,
		gridData:null,
	    loadDataTables:null,
		btnTableLoad:null,
	},
	
	listeners:{
		afterrender:'afterrenderPanel'
	},
	
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [ {
				xtype : 'panel',
				layout : {
					type : 'vbox',
					pack : 'start'
				},
				width : 100,
				height:'100%',
				items : [ {
					xtype : 'button',
					width:100,
					text:'Load',
					reference : 'btnTableLoad',
					handler : 'loadTables',
					}]
				} 
			,{
					xtype:'gridpanel',
					reference:'gridDataTables',
					flex:1,
					title:'Tables',
					height:'100%',
					listeners:{
						itemcontextmenu: 'openContextMenu', 
					},
					columns:[
					         { text: 'Table Name', dataIndex: 'name', width: 80 },
					         {
					        	 xtype : 'actioncolumn',
					        	 text:'Scan',
									width : 70,
									resizable : false,
									items : [
											{
												icon : 'resources/images/general/search_32.png',
												tooltip : 'Read Data',
												handler : 'scanTable'
											}],

					         }]
				},{

					xtype:'gridpanel',
					reference:'gridData',
					flex:10,
					title:'Data',
					height:'100%',
					listeners:{
						itemcontextmenu: 'openContextMenu', 
					},
					columns:[{
						xtype: 'gridcolumn',
						dataIndex: 'rowKey',
						text : 'RowKey',
						maxWidth:200,
						flex: 1,
					},{
						xtype: 'gridcolumn',
						dataIndex: 'cfq',
						text : 'CF:Column',
						maxWidth:200,
						flex: 1,
					},{
						xtype: 'gridcolumn',
						dataIndex: 'value',
						text : 'Value',
						maxWidth:200,
						flex: 1,
					}]
				}]
		});
		me.callParent(arguments);
	}

});
