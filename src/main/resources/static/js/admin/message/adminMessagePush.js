//-------------------------------------------------------------------------------------------
//-----------------AdminMessageSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminMessagePush = function(dataMap){
    this.form = $('#AdminMessagePushForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminMessagePush, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminMessagePush.prototype.ID = {
    
	BTN_CLOSE       : "btnClose",
    BTN_ADD         : "btnAdd",
    SEARCH_BTN_ID   : "searchBtn",
    
    ITEM_TITLE : "title",
    ITEM_MSG   : "msg",
    

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminMessagePush.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
AdminMessagePush.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    var mainForm = $('#AdminMessageSearchForm');
    //init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        ShaDialog.dialogs.confirm(
				self.i18n["dialogs.confirm.add.title"], 
				self.i18n["dialogs.confirm.add.msg"], 
				function () {
	        		self.getObject(self.ID.BTN_CLOSE).click();
					ShaAjax.ajax.post(
						self.jsContext.adminJsView.adminMessagePush.url_message_add, 
						self.getForm().serializeArray(), 
						function () {
							ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
							self.getObjectInForm(mainForm, self.ID.SEARCH_BTN_ID).click();
						}
					);
				}
			);
	    }
	);
	
	
    //init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
};

//checkValue
AdminMessagePush.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["m_message.title"], 	self.getObject(self.ID.ITEM_TITLE)], 
        [ self.i18n["m_message.msg"], 	self.getObject(self.ID.ITEM_MSG)], 
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};

//----------------------------------------------------------------------------]
