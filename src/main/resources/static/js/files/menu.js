//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
Menu = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.form = $('#'+this.jsContext.jsView.menu.form);
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(Menu, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
Menu.prototype.ID = {
	
	BTN_PRODUCT						: 'menu2',
	BTN_LOGOUT						: 'menu7',
	
	BODY_ID							: 'body',
	
	ITEM_SELECT						: 'menuSelect',

	ITEM_LANGUAGE					: 'language',
	//div
	DIV_MENUBODY					: 'menuBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
Menu.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
Menu.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT), 
		function(event) {
			ShaAjax.ajax.postToMain(
	        	self.getJsContext().jsView.product.url_product_init,
	        	self.getForm().serializeArray());
		}
	);
	
	
	
	//init event to BTN_LOGOUT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_LOGOUT), 
		function(event) {
			var param = [{name:'lang', value:self.getObject(self.ID.ITEM_LANGUAGE).val()}];
			ShaDialog.dialogs.confirm(
				self.getJsContext().jsView.login.btn_logout,
				self.getJsContext().jsView.login.msg_logout_confirm,
		    	function(){
		    		ShaRestful.restful.get(self.getJsContext().jsView.login.url_logout, param);
		    	}
		    );
		}
	);
};
//----------------------------------------------------------------------------]
