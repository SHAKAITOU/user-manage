//-------------------------------------------------------------------------------------------
//-----------------registStep3.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
RegistStep3 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#registForm');
};
ShaUtil.other.inherits(RegistStep3, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
RegistStep3.prototype.ID = {
	//btn
	BTN_REGIST							: 'btnRegist3',
	BTN_BACK							: 'btnBack3',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	ITEM_FAMILY_NAME					: 'users_familyName',
	ITEM_SURNAME						: 'users_surname',
	ITEM_FAMILY_NAME_JA					: 'users_familyNameJa',
	ITEM_SURNAME_JA						: 'users_surnameJa',
	ITEM_PW								: 'newPw',
	ITEM_PW2							: 'users_pw',
	ITEM_TEL							: 'usersExtend_tel',
	ITEM_USERSEXTEND_BIRTHDAY			: 'usersExtend_birthday',

};
//------------------------------------------]

//---------------method define--------------[
//init
RegistStep3.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.getObject(self.ID.ITEM_USERSEXTEND_BIRTHDAY).datepicker({
        format: 'yyyy/mm/dd',
        language: 'ja',
    });
	
	if(self.getObject(self.ID.ITEM_ERROR_MSG).val() != "") {
		ShaDialog.dialogs.alert(self.getObject(self.ID.ITEM_ERROR_MSG).val());
	}
	
	self.initEvent();
	
};
// init event
RegistStep3.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.login.userRegist3_title,
            	self.getJsContext().jsView.login.msg_stop_regist_confirm,
            	function(){
            		ShaAjax.pop.postDialogMiddleCenter(
                			Pos.constants.setInfo.loginTitle,
                			Pos.constants.setInfo.jsView.login.url_login_init, 
                			$('#index_form').serializeArray()); 
            	}
            );
	    }
	);
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.login.userRegist3_title,
            	self.getJsContext().jsView.login.msg_regist_confirm,
            	function(){
            		ShaAjax.ajax.post(
            			self.getJsContext().jsView.login.url_userRegistFinal,
            			self.getForm().serializeArray(),
            			function(data){self.callBack(data);}
            		);
            	}
            );
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
    
    //set init focus item when page loaded
	self.initFocus();
};

//checkValue
RegistStep3.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
    	[ self.getJsContext().jsView.login.label_familyName, 	self.getObject(self.ID.ITEM_FAMILY_NAME)], 
    	[ self.getJsContext().jsView.login.label_surname, 	self.getObject(self.ID.ITEM_SURNAME)],
    	[ self.getJsContext().jsView.login.label_familyNameJa, 	self.getObject(self.ID.ITEM_FAMILY_NAME_JA)],
    	[ self.getJsContext().jsView.login.label_surnameJa, 	self.getObject(self.ID.ITEM_SURNAME_JA)],
    	[ self.getJsContext().jsView.login.label_pw, 	self.getObject(self.ID.ITEM_PW)], 
    	[ self.getJsContext().jsView.login.label_pw2, 	self.getObject(self.ID.ITEM_PW2)], 
        [ self.getJsContext().jsView.login.label_tel, 	self.getObject(self.ID.ITEM_TEL)],
        
    ];
    
    var flag = ShaCheck.check.checkNotBlank(inputCheckItemList);
    
    if (flag) {
    	return flag;
    }
    
    flag = ShaCheck.check.checkItem(self.getObject(self.ID.ITEM_PW2), 
    		(self.getObject(self.ID.ITEM_PW).val() != self.getObject(self.ID.ITEM_PW2).val()),
    		self.getJsContext().jsView.login.msg_regist_pwNotSameError);
    return flag;
};

//callBack
RegistStep3.prototype.callBack = function(data){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(Pos.constants.setInfo.jsView.login.msg_regist_success);
	
	ShaAjax.pop.postDialogMiddleCenter(
			Pos.constants.setInfo.loginTitle,
			Pos.constants.setInfo.jsView.login.url_login_init, 
			$('#index_form').serializeArray()); 
};

//initFocus
RegistStep3.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_AUTH_CODE));
};
//----------------------------------------------------------------------------]
