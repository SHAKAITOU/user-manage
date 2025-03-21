//-------------------------------------------------------------------------------------------
//-----------------OrderList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
OrderList = function(dataMap){
    this.form = $('#userOrderListForm');
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
	
	ADD_BTN_ID                   : "addBtn",
	SEARCH_BTN_ID                : "searchBtn",
	SEARCH_PANEL_ID              : "searchPanel",
    SHOW_SEARCH_PANEL_BTN_ID     : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID     : "hideSearchPanelBtn",
    
    ORDER_LIST_TABLE_ID          : "orderListTable",
    
    //div
    ORDER_LIST_REFRESH_BODY_ID  : "orderListRefreshBody"

};
//------------------------------------------]

//---------------method define--------------[
//init 
OrderList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
OrderList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
    self.getObject(self.ID.PAY_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
    
    self.getObject(self.ID.PAY_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
	
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.ORDER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
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
                    self.getObjectInForm(self.getForm(), self.ID.ORDER_LIST_REFRESH_BODY_ID).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.ORDER_LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.ADD_BTN_ID),
    	function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["order.addPayTitle"],
				self.jsContext.jsView.order.url_init, 
				self.getForm().serializeArray());
		}
    );

};

//----------------------------------------------------------------------------]
