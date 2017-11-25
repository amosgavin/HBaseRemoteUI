Ext.Loader.setConfig({
	enabled : true,
});
Ext.application({
	name : 'HBaseRemoteUI',
	sessionExpireTimeInSec : null,
	sessionInfo : null,
	requires : [ ],
	views : ['main.MainView'],
//	controllers : ['general.BaseServiceController',
//			],
	model : [ ],

	launch : function() {
		/**
		 * to disable backspace key
		 */
//		Ext.EventManager.on(window, 'beforeunload', function(e) {
//			e.stopEvent();
//		});
		var loginWindow = Ext.create('HBaseRemoteUI.view.main.MainView');
		
		if (Ext.get('page-loader')) {
			Ext.get('page-loader').hide();
		}

	}

});
