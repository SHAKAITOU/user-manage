//-------------------------------------------------------------------------------------------
//-----------------adminUserManage.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserManage = function(dataMap){
	this.form = $('#menu_form');
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminUserManage, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserManage.prototype.ID = {
	
	DIV_MENUBODY					: 'menuBody',
	
	BTN_FILTER_ID	: "btnUserFilter",
	
	ITEM_FILTER		: "filterUserName",
	
	BTN_SETTING		: "btnAccountSetting",
	
	BTN_REFRESH		: "btnAccountRefresh",
	
	TABLE_LIST		: 'userTable',
	
	ITEM_SELECT_USER_ID : '',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserManage.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.accountManage = self;

	ShaInput.obj.disabled(self.getObject(self.ID.BTN_SETTING));

	//init bond event to btn
	self.initEvent();

	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
AdminUserManage.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_FILTER), 
		function(event) {
			var filterValue = self.getObject(self.ID.ITEM_FILTER).val();
			ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", filterValue);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_FILTER_ID), 
		function(event) {
			var filterValue = self.getObject(self.ID.ITEM_FILTER).val();
			ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", filterValue);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SETTING), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectUserId', self.ID.ITEM_SELECT_USER_ID);
			ShaAjax.pop.postDialogLargeCenter(
				self.getJsContext().adminJsView.adminAccount.a_account_setting_title,
				self.getJsContext().adminJsView.adminAccount.url_menu_account_setting_init, 
				sendData
	        );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REFRESH), 
		function(event) {
			self.refresh();
		}
	);
	
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.ID.ITEM_SELECT_USER_ID = tr.attr("rowDataKey");
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_SETTING));

        	return;
    	}
    );
	
};

AdminUserManage.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.get(
		self.getJsContext().adminJsView.adminAccount.url_menu_account, 
		null, 
		function(data){
			self.getObject(self.ID.DIV_MENUBODY).html(data);
		}
	);
}
//----------------------------------------------------------------------------]
