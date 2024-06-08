//-------------------------------------------------------------------------------------------
//-----------------AdminAccountSetting.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminAccountSetting = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#account_setting_form');
	
};
ShaUtil.other.inherits(AdminAccountSetting, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminAccountSetting.prototype.ID = {
	BTN_CONFIRM			: 'btnConfirm',
	
	BTN_PERIODADD1		: "periodAdd1",
	BTN_PERIODADD3		: "periodAdd3",
	BTN_PERIODADD6		: "periodAdd6",
	BTN_PERIODADD12		: "periodAdd12",
	BTN_PERIODMINUS1	: "periodMinus1",
	
	RADIO_ROLE			: 'users_role',
	ITEM_EXPIREDDT		: 'usersExtend_expiredDt',
	ITEM_CHANGECOMMENTROLE : 'changeCommentRole',
	ITEM_CHANGECOMMENTEXPIREDDT : 'changeCommentExpiredDt',
	
	ORG_ROLE			: null,
	ORG_EXPIREDDT		: null,
	
};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminAccountSetting.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.ID.ORG_ROLE = self.getForm().find('[id^='+self.ID.RADIO_ROLE+']').filter(":checked").val();
	self.ID.ORG_EXPIREDDT = self.getObject(self.ID.ITEM_EXPIREDDT).val();
	self.getObject(self.ID.ITEM_CHANGECOMMENTROLE).hide();
	self.getObject(self.ID.ITEM_CHANGECOMMENTEXPIREDDT).hide();
	
	
	
	self.getObject(self.ID.ITEM_EXPIREDDT).datepicker({
        format: 'yyyy/mm/dd',
        language: 'ja',
    });
	
	//init bond event to btn
	self.initEvent();

};

// init event
AdminAccountSetting.prototype.initEvent = function(){
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PERIODADD1), 
		function(event) {
			self.calcuDt(1);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PERIODADD3), 
		function(event) {
			self.calcuDt(3);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PERIODADD6), 
		function(event) {
			self.calcuDt(6);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PERIODADD12), 
		function(event) {
			self.calcuDt(12);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PERIODMINUS1), 
		function(event) {
			self.calcuDt(-1);
		}
	);

	ShaInput.button.onChange(self.getObject(self.ID.ITEM_EXPIREDDT), 
		function(event) {
			var dt = self.getObject(self.ID.ITEM_EXPIREDDT).val();
			if(dt != self.ID.ORG_EXPIREDDT) {
				self.getObject(self.ID.ITEM_CHANGECOMMENTEXPIREDDT).show();
			} else {
				self.getObject(self.ID.ITEM_CHANGECOMMENTEXPIREDDT).hide();
			}
		}
	);
	
	self.getForm().find('[id^='+self.ID.RADIO_ROLE+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var type = self.getForm().find('[id^='+self.ID.RADIO_ROLE+']').filter(":checked").val();
				if (type != self.ID.ORG_ROLE) {
					self.getObject(self.ID.ITEM_CHANGECOMMENTROLE).show();
				} else {
					self.getObject(self.ID.ITEM_CHANGECOMMENTROLE).hide();
				}
		    }
		);
	});
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CONFIRM), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().adminJsView.adminAccount.a_account_setting_title,
            	self.getJsContext().common.msg_dialogs_confirm_edit,
            	function(){
            		ShaAjax.ajax.post(
            			self.getJsContext().adminJsView.adminAccount.url_menu_account_setting,
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

AdminAccountSetting.prototype.calcuDt = function(months){
	//keep self instance for call back
	var self = this;
	var dateStr = self.getObject(self.ID.ITEM_EXPIREDDT).val();
	var expiredDt = ShaUtil.time.parseYYYYSMMSDD(dateStr);
	expiredDt.setMonth(expiredDt.getMonth() + months);
	dateStr = ShaUtil.time.formatDate(expiredDt, 'yyyy/MM/dd');
	self.getObject(self.ID.ITEM_EXPIREDDT).val(dateStr);
	self.getObject(self.ID.ITEM_EXPIREDDT).datepicker("setDate", dateStr);
};


AdminAccountSetting.prototype.callBack = function(){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	ShaDialog.dialogs.success(msg);
	Pos.constants.accountManage.refresh();
};
//----------------------------------------------------------------------------]
