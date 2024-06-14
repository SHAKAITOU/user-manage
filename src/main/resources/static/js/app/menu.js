//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
Menu = function(dataMap){
    this.form = $('#menu_form');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(Menu, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
Menu.prototype.ID = {
	
	BTN_PRODUCT						: 'menu2',
	BTN_LOGOUT						: 'menu7',
	
    NAVI_FOR_PC                     : "bigScreenNav",
    NAVI_FOR_PHONE                  : "smallScreenNav",

};
//------------------------------------------]

//---------------method define--------------[
//init 
Menu.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
	
    window.onresize = function(event) {
    	self.ajustNavi();
	};

};

// init event
Menu.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	self.ajustNavi();
	
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

Menu.prototype.ajustNavi = function(){
    
    //keep self instance for call back
    var self = this;
      
    if (window.outerWidth < 800) {
		self.getObject(self.ID.NAVI_FOR_PC).hide();
		self.getObject(self.ID.NAVI_FOR_PHONE).show();
	} else {
		self.getObject(self.ID.NAVI_FOR_PC).show();
		self.getObject(self.ID.NAVI_FOR_PHONE).hide();
	}
    
};
//----------------------------------------------------------------------------]
