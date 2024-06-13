//-------------------------------------------------------------------------------------------
//-----------------adminMenu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminMenu = function(dataMap){
    this.form = $('#menu_form');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminMenu, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminMenu.prototype.ID = {
    
    BTN_LOGOUT                      : 'btnLogoutInit',
    
    NAVI_FOR_PC                     : "bigScreenNav",
    NAVI_FOR_PHONE                  : "smallScreenNav",
    
    CLASS_NM_MENU5004      			: 'menu5004',
    ITEM_LANGUAGE                   : 'language',
    //div
    DIV_MAINBODY                    : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminMenu.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip({ boundary: 'window' });
    
    ShaUtil.time.showTime();
    
    //init bond event to btn
    self.initEvent();

    //init click to view
    //self.getObject(self.ID.ITEM_SELECT).val(self.ID.MENU_TYPE_WORK);
    self.getObject(self.ID.ITEM_SELECT).change();

};

// init event
AdminMenu.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
	self.ajustNavi();
    
    //会员查询
    $buttonList = self.getObjectList(self.ID.CLASS_NM_MENU5004);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.adminJsView.adminUserSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
    
    window.onresize = function(event) {
    	self.ajustNavi();
	};
    
    $buttonList[0].click();
    
};

AdminMenu.prototype.ajustNavi = function(){
    
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
