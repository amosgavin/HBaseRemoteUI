Ext.define('HBaseRemoteUI.view.tableOperations.ListTables', {
	extend : 'Ext.panel.Panel',
	requires : [ 'HBaseRemoteUI.view.tableOperations.ListTablesController' ],
	xtype : 'listTables',
	controller : 'listTablesController',
	layout:{
		type : 'hbox',
		pack : 'start',
		align:'stretch'
	},
	
	config:{
		gridTables:null,
		btnLoad:null,
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
					reference : 'btnLoad',
					handler : 'loadTables',
					}]
				} 
			,{
					xtype:'gridpanel',
					reference:'gridTables',
					flex:1,
					title:'Tables',
					height:'100%',
					columns:[
					         { text: 'Table Name', dataIndex: 'name', flex: 1 },
					         {
					        	 xtype : 'actioncolumn',
					        	 text:'Delete',
									width : 70,
									resizable : false,
									items : [
											{
												icon : 'resources/images/general/clear.png',
												tooltip : 'Delete Table',
												handler : 'deleteTable'
											} ],

					         }]
				}]
		});
		me.callParent(arguments);
	}

});
