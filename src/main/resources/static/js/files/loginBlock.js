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
	//btn
	BTN_LOGIN							: 'loginBtn',
	BTN_SET_LANG_JP						: 'setLangJp',
	BTN_SET_LANG_CN						: 'setLangCn',
	BTN_SET_LANG_US						: 'setLangUs',
	BTN_SET_THEMES_BLACK				: 'setThemesBlack',
	BTN_SET_THEMES_WHITE				: 'setThemesWhite',
	BTN_REGIST							: 'registBtn',
	BTN_HELP							: 'helpBtn',
	//item
	ITEM_STORE_CODE						: 'storeCode',
	ITEM_USER_CODE						: 'userCode',
	ITEM_PASSWORD						: 'password',
	ITEM_LOGIN_FORM_RETURN_ERROR_FLAG	: 'login_form_return_error_flag',
	ITEM_LOGIN_FORM_RETURN_ERROR_MSG	: 'login_form_return_error_msg',
};
//------------------------------------------]

//---------------method define--------------[
//init
LoginBlock.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();
	
	self.initFocus();
	
	if(self.getObject(self.ID.ITEM_LOGIN_FORM_RETURN_ERROR_FLAG).val() == 'true'){
		ShaDialog.dialogs.alert(
			self.getObject(self.ID.ITEM_LOGIN_FORM_RETURN_ERROR_MSG).val()
        );
	}
	
};
// init event
LoginBlock.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
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
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SET_LANG_JP), 
		function(event) {

			var param = [{name:'lang', value:'ja'}];
	        ShaRestful.restful.get(
	        	self.getJsContext().jsView.login.url_login,
	        	param
	        );  
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SET_LANG_CN), 
		function(event) {

			var param = [{name:'lang', value:'zh'}];
	        ShaRestful.restful.get(
	        	self.getJsContext().jsView.login.url_login,
	        	param
	        );  
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SET_LANG_US), 
		function(event) {
			var param = [{name:'lang', value:'en'}];
	        ShaRestful.restful.get(
	        	self.getJsContext().jsView.login.url_login,
	        	param
	        );  
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SET_THEMES_BLACK), 
		function(event) {
			var param = [{name:'themesType', value:'black'}];
	        ShaRestful.restful.get(
	        	self.getJsContext().jsView.login.url_login,
	        	param
	        );  
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SET_THEMES_WHITE), 
		function(event) {
			var param = [{name:'themesType', value:'white'}];
	        ShaRestful.restful.get(
	        	self.getJsContext().jsView.login.url_login,
	        	param
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
