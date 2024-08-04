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
	
	CLASS_NM_USER_DETAIL         	: 'menu1001',
	
	CLASS_NM_USER_CERTI         	: 'menu1002',
	
	CLASS_NM_ORDER      			: 'menu2001',
	
	CLASS_NM_USER_REVIEW   			: 'menu4001',
	
	CLASS_NM_MESSAGE      			: 'menu9001',
	
	CLASS_NM_LOGOUT      			: 'menu9003',
	
	BTN_LOGOUT						: 'menu7',
	
    NAVI_FOR_PC                     : "bigScreenNav",
    NAVI_FOR_PHONE                  : "smallScreenNav",
    //div
    DIV_MAINBODY                    : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
Menu.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	$('#mediaHeight').val(window.innerHeight ? window.innerHeight: $(window).height());
	$('#mediaWidth').val(window.innerWidth ? window.innerWidth: $(window).width());
	
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

	self.ajustWinSize();
	self.ajustNavi();
	
    //会员信息
    $buttonList = self.getObjectList(self.ID.CLASS_NM_USER_DETAIL);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.jsView.userDetail.url_user_detail_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//电子会员证
    $buttonList = self.getObjectList(self.ID.CLASS_NM_USER_CERTI);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.jsView.userCerti.url_user_certi_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//缴费确认
	$buttonList = self.getObjectList(self.ID.CLASS_NM_ORDER);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.jsView.orderSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//审核状态
	$buttonList = self.getObjectList(self.ID.CLASS_NM_USER_REVIEW);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.jsView.userReviewSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//站内信息确认
	$buttonList = self.getObjectList(self.ID.CLASS_NM_MESSAGE);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.jsView.messageSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//init event to BTN_LOGOUT
	//退出登录
	$buttonList = self.getObjectList(self.ID.CLASS_NM_LOGOUT);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.logout.title"],
					self.i18n["dialogs.confirm.logout.msg"], 
					function () { 
						ShaRestful.restful.get(self.jsContext.jsView.login.url_logout, null);
					}
				);
			}
	    );
	});
	
	self.getObjectList(self.ID.CLASS_NM_USER_DETAIL).click();
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

Menu.prototype.ajustWinSize = function(){
    
    //keep self instance for call back
    var self = this;
      
    $('#mediaHeight').val(window.innerHeight ? window.innerHeight: $(window).height());
	$('#mediaWidth').val(window.innerWidth ? window.innerWidth: $(window).width());
	ShaAjax.ajax.post(
		Pos.constants.setInfo.common.common.url_com_win_resize, 
		self.mainForm.serializeArray(), 
		function(){}
	);
    
};
//----------------------------------------------------------------------------]
