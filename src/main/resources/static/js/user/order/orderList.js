//-------------------------------------------------------------------------------------------
//-----------------OrderList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
OrderList = function(dataMap){
    this.form = $('#userOrderListForm');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(OrderList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
OrderList.prototype.ID = {
    PAY_DATE_FROM_INPT           : "payDateFrom",
    PAY_DATE_TO_INPT             : "payDateTo",
	
	SHOW_MORE_BTN_ID             : "showMore",
	ADD_BTN_ID                   : "addBtn",
	SEARCH_BTN_ID                : "searchBtn",
	SEARCH_PANEL_ID              : "searchPanel",
    SHOW_SEARCH_PANEL_BTN_ID     : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID     : "hideSearchPanelBtn",
    HID_HIDE_SEARCH              : "hideSearch",
    
    ORDER_LIST_TABLE_ID          : "orderListTable",
    TABLE_BTN_DETAIL             : ".detail",
    
    //div
    ORDER_LIST_REFRESH_BODY_ID  : "orderListRefreshBody",
    PAGE_LINK_ID_PREFIX         : "orderPageLinkIdPrefix",
    //div
    DIV_MAINBODY                : 'mainBody',
};
//------------------------------------------]

//---------------method define--------------[
//init 
OrderList.prototype.init = function(){
	//keep self instance for call back
	var self = this;

	//init bond event to btn
	self.initEvent();
	
	if (self.getObject(self.ID.HID_HIDE_SEARCH).val() === "true") {
		self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID).click();
	} else {
		self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID).click();
	}

};

// init event
OrderList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
    self.getObject(self.ID.PAY_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    self.getObject(self.ID.PAY_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
	
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("false");
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.ORDER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("true");
			self.getObject(self.ID.SEARCH_PANEL_ID).hide();
			self.getObject(self.ID.ORDER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenHideSearch + "px");
		}
    );
	
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.jsView.orderSearch.url_order_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.ORDER_LIST_TABLE_ID);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.ORDER_LIST_TABLE_ID);
    
    $tableBtnList = self.getObject(self.ID.ORDER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL);
    $tableBtnList.each(function(i, elem){
		//check box init
    	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.pop.postDialogLargeCenter(
					self.i18n["admin.order.title.detail"],
					self.jsContext.common.orderDetail.url_init,  
					[{name:"id", value:$(elem).attr("data")}]);
			}
	    );
    	
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.ADD_BTN_ID),
    	function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["order.addPayTitle"],
				self.jsContext.jsView.order.url_init, 
				self.getForm().serializeArray());
		}
    );
    
    
    //initPageLink
    ShaPage.pageLink.initPageLink(self.ID.PAGE_LINK_ID_PREFIX,
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    );

};

//doPageLink
OrderList.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.post(
        self.jsContext.jsView.orderSearch.url_order_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    ); 
};

//----------------------------------------------------------------------------]
