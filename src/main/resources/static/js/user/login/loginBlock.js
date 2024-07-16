//-------------------------------------------------------------------------------------------
//-----------------loginBlock.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
LoginBlock = function(dataMap){
	this.form = $('#login_form');
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(LoginBlock, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
LoginBlock.prototype.ID = {
	
	TAB_ID                   : "loginTab",
	TAB_TITLE_BY_PASS_ID     : "loginByPassTab",
	TAB_TITLE_BY_AUTH_ID     : "loginByAuthCodeTab",
	TAB_BODY_BY_PASS_ID      : "loginByPassTabBody",
    TAB_BODY_BY_AUTH_ID      : "loginByAuthCodeTabBody",
	//btn
	BTN_LOGIN							: 'loginBtn',
	BTN_REGIST							: 'registBtn',
	BTN_HELP							: 'helpBtn',
	BTN_REFRESH_IMG				    	: 'refreshImgBtn',
	//item
	ITEM_STORE_CODE						: 'storeCode',
	ITEM_USER_CODE						: 'userCode',
	ITEM_PASSWORD						: 'password',
	ITEM_LOGIN_FORM_RETURN_ERROR_FLAG	: 'login_form_return_error_flag',
	ITEM_LOGIN_FORM_RETURN_ERROR_MSG	: 'login_form_return_error_msg',
	ITEM_AUTH_IMG                       : 'imgPhoto',
};
//------------------------------------------]

//---------------method define--------------[
//init
LoginBlock.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();
	
	self.initFocus();
	
	if(self.dataMap.errorMsg != null){
		ShaDialog.dialogs.alertWithCallBack(
			self.dataMap.errorMsg,
			function() {
				ShaRestful.restful.get(self.jsContext.jsView.login.url_logout, null);
			}
        );
	}
	
};
// init event
LoginBlock.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
    ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_BY_PASS_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_BY_PASS_ID, self.ID.TAB_BODY_BY_PASS_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_BY_AUTH_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_BY_AUTH_ID, self.ID.TAB_BODY_BY_AUTH_ID);
		}
	);
	
	//init event to loginBtn
	ShaInput.button.onClick(self.getObject(self.ID.BTN_LOGIN), 
		function(event) {
	        if(self.checkValue()) {
	            return;
	        }
	        ShaRestful.restful.post(
	        	self.getJsContext().jsView.login.url_user_login,
	        	self.getForm()
	        );  
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				Pos.constants.setInfo.jsView.login.userRegist_title,
				Pos.constants.setInfo.jsView.login.url_userRegist, 
				$('#index_form').serializeArray());
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_HELP), 
		function(event) {
			window.open("/help");
	    }
	);
	
	
	//add btn enter event
    ShaInput.keyPress.enterPress(self.getObject(self.ID.BTN_LOGIN), function(){
    	self.getObject(self.ID.BTN_LOGIN).click();
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_REFRESH_IMG), 
		function(event) {
	        ShaAjax.ajax.post(
                self.getJsContext().jsView.login.url_refreshImg, 
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
LoginBlock.prototype.checkValue = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
    	[ self.getJsContext().jsView.login.label_storeCode, 	self.getObject(self.ID.ITEM_STORE_CODE)], 
        [ self.getJsContext().jsView.login.label_user, 			self.getObject(self.ID.ITEM_USER_CODE)], 
        [ self.getJsContext().jsView.login.label_pw, 			self.getObject(self.ID.ITEM_PASSWORD)],
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};

// initFocus
LoginBlock.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_STORE_CODE));
};

//----------------------------------------------------------------------------]
