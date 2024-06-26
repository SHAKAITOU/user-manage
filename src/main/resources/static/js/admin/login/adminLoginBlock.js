//-------------------------------------------------------------------------------------------
//-----------------adminAdminLoginBlock.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminLoginBlock = function(dataMap){
	this.form = $('#login_form');
	this.jsContext = Pos.constants.setInfo;
	this.i18n = JSON.parse(this.jsContext.i18n);
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminLoginBlock, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminLoginBlock.prototype.ID = {
	
	//btn
	BTN_LOGIN							: 'loginBtn',
	BTN_REFRESH_IMG				    	: 'refreshImgBtn',
	//item
	ITEM_USER_CODE						: 'userCode',
	ITEM_PASSWORD						: 'password',
	ITEM_LOGIN_FORM_RETURN_ERROR_FLAG	: 'login_form_return_error_flag',
	ITEM_LOGIN_FORM_RETURN_ERROR_MSG	: 'login_form_return_error_msg',
	ITEM_AUTH_IMG                       : 'imgPhoto',
};
//------------------------------------------]

//---------------method define--------------[
//init
AdminLoginBlock.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();
	
	self.initFocus();
	
	if(self.dataMap.errorMsg != null){
		ShaDialog.dialogs.alertWithCallBack(
			self.dataMap.errorMsg,
			function() {
				ShaRestful.restful.get(self.jsContext.adminJsView.adminLogin.url_logout, null);
			}
        );
	}
	
};
// init event
AdminLoginBlock.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_LOGIN), 
		function(event) {
	        if(self.checkValue()) {
	            return;
	        }
	        ShaRestful.restful.post(
	        	self.getJsContext().adminJsView.adminLogin.url_user_login,
	        	self.getForm()
	        );  
	    }
	);

	//add btn enter event
    ShaInput.keyPress.enterPress(self.getObject(self.ID.BTN_LOGIN), function(){
    	self.getObject(self.ID.BTN_LOGIN).click();
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_REFRESH_IMG), 
		function(event) {
	        ShaAjax.ajax.post(
                self.getJsContext().adminJsView.adminLogin.url_refreshImg, 
                self.form.serializeArray(), 
                function(data){
					self.getObject(self.ID.ITEM_AUTH_IMG).attr("src", data);
                }
            ); 
	    }
	);

	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//checkValue
AdminLoginBlock.prototype.checkValue = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["m_admin.id"], 			self.getObject(self.ID.ITEM_USER_CODE)], 
        [ self.i18n["m_admin.password"], 	self.getObject(self.ID.ITEM_PASSWORD)],
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};

// initFocus
AdminLoginBlock.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_USER_CODE));
};

//----------------------------------------------------------------------------]
