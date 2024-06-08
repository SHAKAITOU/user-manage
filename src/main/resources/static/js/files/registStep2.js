//-------------------------------------------------------------------------------------------
//-----------------registStep2.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
RegistStep2 = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#registForm');
};
ShaUtil.other.inherits(RegistStep2, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
RegistStep2.prototype.ID = {
	//btn
	BTN_REGIST							: 'btnRegist2',
	BTN_BACK							: 'btnBack1',

	//item
	ITEM_ERROR_MSG						: 'errorMsg',
	
	ITEM_AUTH_CODE						: 'authCode',

};
//------------------------------------------]

//---------------method define--------------[
//init
RegistStep2.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();

	if(self.getObject(self.ID.ITEM_ERROR_MSG).val() != "") {
		ShaDialog.dialogs.alert(self.getObject(self.ID.ITEM_ERROR_MSG).val());
	}
};
// init event
RegistStep2.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
		 	ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.login.userRegist2_title,
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
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST), 
		function(event) {
			if(self.check()) {
	            return;
	        }
    		ShaAjax.pop.postDialogMiddleCenter(
    			Pos.constants.setInfo.jsView.login.userRegist3_title,
    			Pos.constants.setInfo.jsView.login.url_userRegist3, 
    			self.getForm().serializeArray()); 
	    }
	);
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
    
    //set init focus item when page loaded
	self.initFocus();
};

//checkValue
RegistStep2.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
    	[ self.getJsContext().jsView.login.label_authCode, 	self.getObject(self.ID.ITEM_AUTH_CODE)]
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};


//initFocus
RegistStep2.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_AUTH_CODE));
};
//----------------------------------------------------------------------------]
