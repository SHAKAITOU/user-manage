//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
userTypeSettings = function(dataMap){
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.form = $('#userTypeSettingsForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(userTypeSettings, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
userTypeSettings.prototype.ID = {
	PREFIX_ID                : "userTypeSettings_",
	ITEM_USER_TYPE           : "userType",
	ITEM_FEE_AMOUNT          : "feeAmount",
	ITEM_EFFECTIVE_YEAR      : "effectiveYear",
	
	LIST_TABLE_ID      		 : "userTypeSettingsTable",
	
	BTN_OK              	 : "btnOk",
		
    //div
    DIV_MAINBODY             : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
userTypeSettings.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
userTypeSettings.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
	        ShaDialog.dialogs.confirm(
				self.i18n["dialogs.confirm.edit.title"], 
				self.i18n["dialogs.confirm.edit.msg"], 
				function () {
					ShaAjax.ajax.post(
						self.jsContext.adminJsView.userTypeSettings.url_edit,
						self.getForm().serializeArray(), 
						function (data) {
							if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
								ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
							}else{
								ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
							}
						}
					);
				}
			);
	    }
	);
};


//checkValue
userTypeSettings.prototype.check = function(){
	//keep self instance for call back
	var self = this;
	//var vConsole = new window.VConsole();
	result = false;
	
	ShaCheck.check.setFirstItemFocus(true);
	list = self.getObject(self.ID.LIST_TABLE_ID).find("[name*='" + self.ID.ITEM_FEE_AMOUNT+"']");
	console.log("list.size="+list.length);
	list.each(function(i, elem){
		console.log(i+" value="+$(elem).val());
		if (ShaCheck.check.checkNotBlank([[self.i18n["m_user_type_settings.fee_amount"], 	$(elem)]])){
			result = true;
		}else if (ShaCheck.check.checkNotNumber([[self.i18n["m_user_type_settings.fee_amount"], 	$(elem)]])){
			result = true;
		}
	});
	
	//list = self.getObject(self.ID.LIST_TABLE_ID).find("[name*='"+self.ID.PREFIX_ID.replace('_', '.') + self.ID.ITEM_EFFECTIVE_YEAR+"']");
	list = self.getObject(self.ID.LIST_TABLE_ID).find("[name*='" + self.ID.ITEM_EFFECTIVE_YEAR+"']");
	list.each(function(i, elem){
		if (ShaCheck.check.checkNotBlank([[self.i18n["m_user_type_settings.effective_year"], 	$(elem)]])){
			result = true;
		}else if (ShaCheck.check.checkNotNumber([[self.i18n["m_user_type_settings.effective_year"], 	$(elem)]])){
			result = true;
		}

	});

    return result;
};
//----------------------------------------------------------------------------]
