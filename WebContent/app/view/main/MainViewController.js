Ext.define('HBaseRemoteUI.view.main.MainViewController', {
	extend : 'Ext.app.ViewController',
	alias:'controller.mainViewController',
	
	loadPanel:function(button){
		var container = this.lookupReference('refContainer');
		var panel1 = Ext.create(button.panelRef,{
		height:500
	});
		container.items.removeAll();
		container.add(panel1);
		
	},
});