//-------------------------------------------------------------------------------------------
//-----------------AdminMessageSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminMessageSearch = function(dataMap){
    this.form = $('#AdminMessageSearchForm');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminMessageSearch, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminMessageSearch.prototype.ID = {
    
    REGIST_DATE_FROM_INPT    : "registDateFrom",
    REGIST_DATE_TO_INPT      : "registDateTo",
    
    ADD_BTN_ID               : "btnAdd",
    SHOW_MORE_BTN_ID         : "showMore",
    SEARCH_BTN_ID            : "searchBtn",
    SHOW_SEARCH_PANEL_BTN_ID : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID : "hideSearchPanelBtn",
    HID_HIDE_SEARCH          : "hideSearch",

    TABLE_BTN_DETAIL         : ".detail",
    
    //div
    DIV_REFRESH_BODY         : "messageListRefreshBody",
    SEARCH_PANEL_ID          : "searchPanel",
    LIST_TABLE_ID            : "messageListTable",
    PAGE_LINK_ID_PREFIX      : "messagePageLinkIdPrefix",
    //div
    DIV_MAINBODY             : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminMessageSearch.prototype.init = function(){
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
AdminMessageSearch.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
    self.getObject(self.ID.REGIST_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    self.getObject(self.ID.REGIST_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("false");
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("true");
			self.getObject(self.ID.SEARCH_PANEL_ID).hide();
			self.getObject(self.ID.LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenHideSearch + "px");
		}
    );
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.LIST_TABLE_ID);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.adminJsView.adminMessageSearch.url_message_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.ADD_BTN_ID),
    	function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["admin.messageList.sendAll"],
				self.jsContext.adminJsView.adminMessagePush.url_init, 
				self.getForm().serializeArray());
		}
    );
    
    $tableBtnList = self.getObject(self.ID.LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL);
    $tableBtnList.each(function(i, elem){
		//check box init
    	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.pop.postDialogMiddleCenter(
					self.i18n["admin.message.title.detail"],
					self.jsContext.common.messageDetail.url_init, 
					[{name:"id", value:$(elem).attr("data")}]);
			}
	    );
    	
    });
    
    //initPageLink
    ShaPage.pageLink.initPageLink(self.ID.PAGE_LINK_ID_PREFIX,
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    );
    
};

//doPageLink
AdminMessageSearch.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.post(
        self.jsContext.adminJsView.adminMessageSearch.url_message_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    );  
};

//----------------------------------------------------------------------------]
