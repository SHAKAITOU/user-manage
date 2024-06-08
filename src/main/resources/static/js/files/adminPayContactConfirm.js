//-------------------------------------------------------------------------------------------
//-----------------AdminPayContactConfirm.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminPayContactConfirm = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#pay_contact_confirm_form');
	
};
ShaUtil.other.inherits(AdminPayContactConfirm, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminPayContactConfirm.prototype.ID = {
	BTN_CONFIRM			: 'btnConfirm',
	BTN_CANCEL			: 'btnCancel',
	
	ITEM_STATUS			: 'status',
};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminPayContactConfirm.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
AdminPayContactConfirm.prototype.initEvent = function(){
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CONFIRM), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().adminJsView.adminPayContact.a_contact_confirm_init_title,
            	self.getJsContext().common.msg_dialogs_confirm_add,
            	function(){
            		self.getObject(self.ID.ITEM_STATUS).val("1");
            		ShaAjax.ajax.post(
            			self.getJsContext().adminJsView.adminPayContact.url_a_contact_confirm_regist,
            			self.getForm().serializeArray(),
            			function(){self.callBack();}
            		);
            	}
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CANCEL), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().adminJsView.adminPayContact.a_contact_confirm_init_title,
            	self.getJsContext().common.msg_dialogs_confirm_delete,
            	function(){
            		self.getObject(self.ID.ITEM_STATUS).val("2");
            		ShaAjax.ajax.post(
            			self.getJsContext().adminJsView.adminPayContact.url_a_contact_confirm_regist,
            			self.getForm().serializeArray(),
            			function(){self.callBack();}
            		);
            	}
            );
		}
	);
	
};

AdminPayContactConfirm.prototype.callBack = function(){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	ShaDialog.dialogs.success(msg);
	Pos.constants.payContactManage.doPageLink();
};
//----------------------------------------------------------------------------]
