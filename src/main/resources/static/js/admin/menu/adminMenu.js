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
    
    CLASS_NM_ORDER_SEARCH           : "menu2001",
    CLASS_NM_ORDER_WAIT_SEARCH      : "menu2002",
    CLASS_NM_ORDER_REVIEW           : "menu2003",
    CLASS_NM_ORDER_PASS             : "menu2004",
    CLASS_NM_BILL_SEARCH            : "menu3001",
    CLASS_NM_REFUND_SEARCH          : "menu3002",
    CLASS_NM_USER_SEARCH      		: "menu4001",
    CLASS_NM_MESSAGE_SEARCH         : "menu5001",
    
    CLASS_NM_LOGOUT                 : "menu9003",
    ITEM_LANGUAGE                   : "language",
    //div
    DIV_MAINBODY                    : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminMenu.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip({ boundary: 'window' });
    
	$('#mediaHeight').val(window.innerHeight ? window.innerHeight: $(window).height());
	$('#mediaWidth').val(window.innerWidth ? window.innerWidth: $(window).width());
    
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
	
	
	//缴费订单查询
    $buttonList = self.getObjectList(self.ID.CLASS_NM_ORDER_SEARCH);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.adminJsView.adminOrderSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//缴费订单等待审核
	$buttonList = self.getObjectList(self.ID.CLASS_NM_ORDER_WAIT_SEARCH);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.post(
	                self.jsContext.adminJsView.adminOrderSearch.url_order_list_wait, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//缴费订单审核中
	$buttonList = self.getObjectList(self.ID.CLASS_NM_ORDER_REVIEW);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.post(
	                self.jsContext.adminJsView.adminOrderSearch.url_order_list_review, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//缴费订单已审 
	$buttonList = self.getObjectList(self.ID.CLASS_NM_ORDER_PASS);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.post(
	                self.jsContext.adminJsView.adminOrderSearch.url_order_list_pass, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//开票查询
    $buttonList = self.getObjectList(self.ID.CLASS_NM_BILL_SEARCH);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.adminJsView.adminBillSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//退款查询
    $buttonList = self.getObjectList(self.ID.CLASS_NM_REFUND_SEARCH);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.adminJsView.adminRefundSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
    
    //会员查询
    $buttonList = self.getObjectList(self.ID.CLASS_NM_USER_SEARCH);
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
	
	//站内消息查询
	$buttonList = self.getObjectList(self.ID.CLASS_NM_MESSAGE_SEARCH);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.get(
	                self.jsContext.adminJsView.adminMessageSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	//退出登录
	$buttonList = self.getObjectList(self.ID.CLASS_NM_LOGOUT);
    $buttonList.each(function(i, elem){
	    ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.logout.title"],
					self.i18n["dialogs.confirm.logout.msg"], 
					function () { 
						ShaRestful.restful.get(self.jsContext.adminJsView.adminLogin.url_logout, null);
					}
				);
			}
	    );
	});
    
    window.onresize = function(event) {
    	self.ajustNavi();
	};
    
    $buttonList = self.getObjectList(self.ID.CLASS_NM_USER_SEARCH);
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
