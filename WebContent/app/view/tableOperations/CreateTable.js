Ext.define('HBaseRemoteUI.view.tableOperations.CreateTable', {
	extend : 'Ext.panel.Panel',
	requires : [ 'HBaseRemoteUI.view.tableOperations.CreateTableController' ],
	xtype : 'createTables',
	controller : 'createTableController',
	layout : {
		type : 'vbox',
		pack : 'start',
		align : 'stretch'
	},
	config:{
		tableName:null,
		columnFamilies:null,
		
		gridColumnFamilies:null,
		textFieldTableName:null,
		textFieldCFName:null
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
					align : 'stretch',
					pack : 'start'
				},
				flex:1,
				items : [{
					xtype : 'textfield',
					reference:'textFieldTableName',
					name : 'name',
					height: 25,
					width: 100,
					fieldLabel : 'TableName',
					allowBlank : false
				// requires a non-empty value
				},{
					xtype:'panel',
					layout:'hbox',
					items:[{
						xtype : 'textfield',
						reference:'textFieldCFName',
						name : 'name',
						height: 25,
						width: 200,
						fieldLabel : 'ColmFamName',
						padding:'0 0 0 0',
						allowBlank : false
					},{
						xtype:'button',
						icon : 'resources/images/general/add.png',
						handler:'addColumnFamily'
					}
					       ]
				
				},{
					xtype:'grid',
					reference:'gridColumnFamilies',
					flex: 1,
					title: 'Column Family List',
					columns: [{ text: 'CF:', dataIndex: 'name' }]
				},
				{
					xtype: 'button',
					icon : 'resources/images/general/addFile.png',
					text:'Create Table',
					handler:'createTable'
				}
				]

			},  ]
		});
		me.callParent(arguments);
	}

});