//-------------------------------------------------------------------------------------------
//-----------------payContact.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
PayContact = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#payContactForm');
	
};
ShaUtil.other.inherits(PayContact, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
PayContact.prototype.ID = {
		
	BTN_ADD					: 'btnContact',
		
	RADIO_CONTACT_ID		: 'contactType',
	
	ITEM_MAIL_ID			: 'userMail',
	ITEM_PAYBANKACCOUNT_ID	: 'userName',
	ITEM_PAYDATE_ID			: 'payDate',
	ITEM_COMMENT_ID			: 'comment',
	
	ITEM_USERCODE			: 'userCodeDiv',
	ITEM_PAYPERIODTYPE		: 'payPeriodTypeDiv',
	ITEM_PAYBANKACCOUNT		: 'payBankAccountDiv',
	ITEM_PAYDATE			: 'payDateDiv',
	
	
	CONTACT_TYPE_PAY		: '1',
	CONTACT_TYPE_OTHER		: '2',
};
//------------------------------------------]

//---------------method define--------------[
//init 
PayContact.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
PayContact.prototype.initEvent = function(){
	
	var self = this;
	
	self.getForm().find('[id^='+self.ID.RADIO_CONTACT_ID+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var type = self.getForm().find('[id^='+self.ID.RADIO_CONTACT_ID+']').filter(":checked").val();
				if (type == self.ID.CONTACT_TYPE_PAY) {
					self.getObject(self.ID.ITEM_USERCODE).show();
					self.getObject(self.ID.ITEM_PAYPERIODTYPE).show();
					self.getObject(self.ID.ITEM_PAYBANKACCOUNT).show();
					self.getObject(self.ID.ITEM_PAYDATE).show();
				} else {
					self.getObject(self.ID.ITEM_USERCODE).hide();
					self.getObject(self.ID.ITEM_PAYPERIODTYPE).hide();
					self.getObject(self.ID.ITEM_PAYBANKACCOUNT).hide();
					self.getObject(self.ID.ITEM_PAYDATE).hide();
				}
		    }
		);
	});
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			ShaDialog.dialogs.confirm(
            	self.dataMap.payContact_title,
            	self.dataMap.payContact_msg_confirm,
            	function(){
            		ShaAjax.ajax.post(
            			self.dataMap.url_payContact_regist,
            			self.getForm().serializeArray(),
            			function(){self.callBackAdd();}
            		);
            	}
            );
		}
	);
	
};

//check
PayContact.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
	var type = self.getForm().find('[id^='+self.ID.RADIO_CONTACT_ID+']').filter(":checked").val();
	var inputCheckItemList;
	if (type == self.ID.CONTACT_TYPE_PAY) {
	    inputCheckItemList = [
	    	[ "", self.getObject(self.ID.ITEM_MAIL_ID)],
	    	[ "", self.getObject(self.ID.ITEM_PAYBANKACCOUNT_ID)],
	    	[ "", self.getObject(self.ID.ITEM_PAYDATE_ID)]
	    ];
	} else {
		inputCheckItemList = [
	    	[ "", self.getObject(self.ID.ITEM_MAIL_ID)],
	    	[ "", self.getObject(self.ID.ITEM_COMMENT_ID)]
	    ];
	}
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};

PayContact.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(self.dataMap.payContact_msg_ok);
};
//----------------------------------------------------------------------------]
