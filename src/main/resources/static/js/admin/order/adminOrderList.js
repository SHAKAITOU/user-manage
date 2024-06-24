//-------------------------------------------------------------------------------------------
//-----------------AdminOrderList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminOrderList = function(dataMap){
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.form = $('#AdminOrderListForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminOrderList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminOrderList.prototype.ID = {
    PAY_DATE_FROM_INPT           : "payDateFrom",
    PAY_DATE_TO_INPT             : "payDateTo",
	
	SHOW_MORE_BTN_ID             : "showMore",
	ADD_BTN_ID                   : "addBtn",
	SEARCH_BTN_ID                : "searchBtn",
	SEARCH_PANEL_ID              : "searchPanel",
    SHOW_SEARCH_PANEL_BTN_ID     : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID     : "hideSearchPanelBtn",
    HID_HIDE_SEARCH              : "hideSearch",
    HID_INVISABLE_SEARCH         : "inVisableSearch",
    
    ORDER_LIST_TABLE_ID          : "orderListTable",
    TABLE_BTN_DETAIL             : ".detail",
    TABLE_BTN_PUT_TO_REVIEW      : ".putToReview",
    TABLE_BTN_START_REVIEW       : ".startReview",
    
    //div
    ORDER_LIST_REFRESH_BODY_ID  : "orderListRefreshBody",
    ORDER_WAIT_CNT_DIV          : ".orderWaitCntDiv",
    ORDER_REVIEW_CNT_DIV        : ".orderReviewCntDiv",
    ORDER_NOT_FINISH_CNT_DIV    : ".orderNotFinishCntDiv",
    
    //div
    DIV_MAINBODY                    : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminOrderList.prototype.init = function(){
	//keep self instance for call back
	var self = this;

	//init bond event to btn
	self.initEvent();
	
	if (self.getObject(self.ID.HID_HIDE_SEARCH).val() === "true" || self.getObject(self.ID.HID_INVISABLE_SEARCH).val() === "true") {
		self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID).click();
	} else {
		self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID).click();
	}

};

// init event
AdminOrderList.prototype.initEvent = function(){
	
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
                self.jsContext.adminJsView.adminOrderSearch.url_order_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.getForm(), self.ID.ORDER_LIST_REFRESH_BODY_ID).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_MORE_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.adminJsView.adminOrderSearch.url_order_list_growing, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.getForm(), self.ID.ORDER_LIST_REFRESH_BODY_ID).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
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
    
    $tableBtnList = self.getObject(self.ID.ORDER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_PUT_TO_REVIEW);
    $tableBtnList.each(function(i, elem){
		//check box init
    	ShaInput.button.onClick($(elem),
	    	function(event) {
				var msg = ShaUtil.util.format(self.i18n["admin.order.msg.putToReview"], $(elem).attr("data"));
				var msgSuc = ShaUtil.util.format(self.i18n["admin.order.msg.putToReview.success"], $(elem).attr("data"));
				ShaDialog.dialogs.confirm(
					self.i18n["admin.order.btn.putToReview"],
					msg, 
					function () {
						ShaAjax.ajax.post(
							self.jsContext.adminJsView.adminOrderSearch.url_order_putToReview, 
							[{name:"id",     value:$(elem).attr("data")}], 
							function () {
								ShaDialog.dialogs.success(msgSuc);
								ShaPage.pageCom.refreshOrderCnt(
									self.jsContext, 
									self.menuForm, 
									function(){
										//refresh order list
										ShaAjax.ajax.post(
							                self.jsContext.adminJsView.adminOrderSearch.url_order_list_wait, 
							                null, 
							                function(data){
							                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
							                }
							            );
									}
								);
							}
						);
					}
				);
			}
	    );
    	
    });
    
    $tableBtnList = self.getObject(self.ID.ORDER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_START_REVIEW);
    $tableBtnList.each(function(i, elem){
		//check box init
    	ShaInput.button.onClick($(elem),
	    	function(event) {
				//refresh order list
				ShaAjax.ajax.post(
	                self.jsContext.adminJsView.adminReviewOrder.url_init, 
	                [{name:"id",     value:$(elem).attr("data")}], 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
    	
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.ADD_BTN_ID),
    	function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["order.addPayTitle"],
				self.jsContext.adminJsView.adminOrderSearch.url_init, 
				self.getForm().serializeArray());
		}
    );

};

//----------------------------------------------------------------------------]
