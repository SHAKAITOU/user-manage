//-------------------------------------------------------------------------------------------
//-----------------AdminManageSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminManageSearch = function(dataMap){
    this.form = $('#AdminManageSearchForm');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminManageSearch, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminManageSearch.prototype.ID = {
    
    SEARCH_BTN_ID            : "searchBtn",
    SHOW_SEARCH_PANEL_BTN_ID : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID : "hideSearchPanelBtn",
    HID_HIDE_SEARCH          : "hideSearch",
	HID_SELECTED_USER_ID     : "selectedUserId",
	
	USERLIST_ADD_BTN_ID      : "addBtn",
    
    TABLE_BTN_RESETPW        : ".restPw",
    TABLE_BTN_EDIT           : ".edit",
    
    //div
    DIV_REFRESH_BODY         : "userListRefreshBody",
    SEARCH_PANEL_ID          : "searchPanel",
    USER_LIST_TABLE_ID       : "userListTable",
    
    PAGE_LINK_ID_PREFIX      : "userPageLinkIdPrefix",
    //div
    DIV_MAINBODY             : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminManageSearch.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();

	if (self.getObject(self.ID.HID_HIDE_SEARCH).val() === "true") {
		self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID).click();
	} else {
		self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID).click();
	}

};

// init event
AdminManageSearch.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("false");
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("true");
			self.getObject(self.ID.SEARCH_PANEL_ID).hide();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenHideSearch + "px");
		}
    );
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.adminJsView.adminManageSearch.url_user_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    //initPageLink
    ShaPage.pageLink.initPageLink(self.ID.PAGE_LINK_ID_PREFIX,
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    ); 
	
	
	ShaInput.button.onClick(self.getObject(self.ID.USERLIST_ADD_BTN_ID),
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
    			self.i18n["admin.userEdit.title"],
    			self.jsContext.adminJsView.adminManageEdit.url_init, 
				null); 
		}
	);

	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_RESETPW);
	$tableBtnList.each(function(i, elem){
		//check box init
	   	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.ajax.post(
	                self.jsContext.adminJsView.adminUserReview.url_init, 
	                [{name:"id",     value:$(elem).attr("data")}],
				    //self.getForm().serializeArray(),  
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_EDIT);
	$tableBtnList.each(function(i, elem){
		//check box init
	   	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.pop.postDialogMiddleCenter(
	    			self.i18n["admin.userEdit.title"],
	    			self.jsContext.adminJsView.adminManageEdit.url_init, 
					[{name:"id",     value:$(elem).attr("data")}]); 
			}
	    );
	});
   	
};

//doPageLink
AdminManageSearch.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.post(
        self.jsContext.adminJsView.adminManageSearch.url_user_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    ); 
};
//----------------------------------------------------------------------------]
