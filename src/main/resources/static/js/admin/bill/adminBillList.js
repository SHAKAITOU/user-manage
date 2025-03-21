//-------------------------------------------------------------------------------------------
//-----------------AdminBillList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminBillList = function(dataMap){
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.form = $('#AdminBillListForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminBillList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminBillList.prototype.ID = {
    BILL_DATE_FROM_INPT           : "billDateFrom",
    BILL_DATE_TO_INPT             : "billDateTo",
	
	SHOW_MORE_BTN_ID             : "showMore",
	SEARCH_BTN_ID                : "searchBtn",
	SEARCH_PANEL_ID              : "searchPanel",
    SHOW_SEARCH_PANEL_BTN_ID     : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID     : "hideSearchPanelBtn",
    HID_HIDE_SEARCH              : "hideSearch",
    HID_INVISABLE_SEARCH         : "inVisableSearch",
    
    ORDER_LIST_TABLE_ID          : "billListTable",
    TABLE_BTN_DETAIL             : ".detail",
    TABLE_BTN_TO_BE              : ".tobeInvoice",
    
    //div
    ORDER_LIST_REFRESH_BODY_ID  : "billListRefreshBody",
    
    PAGE_LINK_ID_PREFIX         : "billPageLinkIdPrefix",
    //div
    DIV_MAINBODY                    : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminBillList.prototype.init = function(){
	//keep self instance for call back
	var self = this;

	$('[data-toggle="tooltip"]').tooltip({
	   trigger: 'manual'
	 }).on('mouseover', function () {
	   var $this = $(this);
	   if (this.offsetWidth < this.scrollWidth) {
	     $this.tooltip('show');
	   }
	 }).on('mouseout', function () {
	   var $this = $(this);
	   $this.tooltip('hide');
	 });
		 
	//init bond event to btn
	self.initEvent();
	
	if (self.getObject(self.ID.HID_HIDE_SEARCH).val() === "true" || self.getObject(self.ID.HID_INVISABLE_SEARCH).val() === "true") {
		self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID).click();
	} else {
		self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID).click();
	}

};

// init event
AdminBillList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
    self.getObject(self.ID.BILL_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
    
    self.getObject(self.ID.BILL_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
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
			ShaAjax.ajax.get(
                self.jsContext.adminJsView.adminBillSearch.url_bill_list, 
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

    $tableBtnList = self.getObject(self.ID.ORDER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_TO_BE);
    $tableBtnList.each(function(i, elem){
		//check box init
    	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.pop.postDialogLargeCenter(
					self.i18n["admin.bill.btn.tobe"],
					self.jsContext.adminJsView.adminBill.url_init,
					[{name:"id",     value:$(elem).attr("data")}]); 
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
AdminBillList.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.get(
        self.jsContext.adminJsView.adminBillSearch.url_bill_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    ); 
};

//----------------------------------------------------------------------------]
