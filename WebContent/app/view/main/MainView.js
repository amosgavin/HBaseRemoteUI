Ext.define('HBaseRemoteUI.view.main.MainView', {
	extend : 'Ext.container.Viewport',
	requires : [ 'Ext.layout.container.Accordion',
	             'HBaseRemoteUI.view.dataOperations.ListDataTables',
	             'HBaseRemoteUI.view.tableOperations.ListTables',
	             'HBaseRemoteUI.view.main.MainViewController' ],
	xtype : 'app-main',
	controller : 'mainViewController',
	itemId : 'mainView',
	reference : 'mainView',
	flex : 1,
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [ {
				xtype : 'panel',
				margin:'100 0 0 0',
				flex : 1,
				layout : {
					type : 'hbox',
					pack : 'start'
				},
				width : '100%',
				height : '100%',
				
				items : [ {
					xtype : 'panel',
					width:150,
					layout:{
						type:'accordion',
						align:'stretch',
					},
				animate : true,
				activeOnTop : false,
				defaults:{
					ui:'blue-panel',
				},
					items : [ {
						xtype:'panel',
						title:'Table Operations',
						layout:{
							type:'vbox',
							align:'stretch'
						},
						defaults:{
							ui:'orange-button',
							padding:'2 0 2 0',
							margin:'0 0 1 0',
							handler:'loadPanel'
						},
						items:[{
							xtype:'button',
							text:'List Tables',
							panelRef:'HBaseRemoteUI.view.tableOperations.ListTables'
						},{
							xtype:'button',
							text:'Create Table',
							panelRef:'HBaseRemoteUI.view.tableOperations.CreateTable'
						}]
					}, {
						xtype:'panel',
						title:'Data Operations',
						layout:{
							type:'vbox',
							align:'stretch'
						},
						defaults:{
							ui:'orange-button',
							padding:'2 0 2 0',
							margin:'0 0 1 0',
							handler:'loadPanel'
						},
						items:[{
							xtype:'button',
							text:'List Tables',
							panelRef:'HBaseRemoteUI.view.dataOperations.ListDataTables'
						}]
					} ]

				} ,{
					xtype:'panel',
					reference:'refContainer',
					flex:1,
					height:500,
				}],
				

			} ]
		});

		me.callParent(arguments);
	}

});
