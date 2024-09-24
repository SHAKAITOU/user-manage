//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ReviewOk = function(dataMap){
    this.menuForm = $('#menu_form');
    this.fatherForm = $('#ReviewOrderForm');
    this.form = $('#ReviewOkForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(ReviewOk, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ReviewOk.prototype.ID = {
	
	BTN_BACK                 : "btnBack",
	
	BTN_OK                   : "btnOk",
	BTN_CLOSE                : "btnClose",
	
	ITEM_ID                  : "id",
	ITEM_PAY_AMOUNT          : "payAmount",
	ITEM_VALID_END_DATE      : "validEndDate",
	ITEM_ANS                 : "ans",
	
	HID_ITEM_ORDER_AMOUNT    : "order_amount",
	
    //div
    DIV_MAINBODY             : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
ReviewOk.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();

	self.initFocus();
};

// init event
ReviewOk.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
	//init event to BTN_BACK
    self.getObject(self.ID.ITEM_VALID_END_DATE).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			ShaDialog.dialogs.confirm(
				self.i18n["admin.order.btn.reviewOk"],
				self.i18n["dialogs.confirm.add.msg"], 
				function () { 
					ShaAjax.ajax.post(
		                self.jsContext.adminJsView.adminReviewOk.url_review_commit, 
		                self.form.serializeArray(), 
		                function(data){
							ShaDialog.dialogs.dialogClose();
		                    ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
		                    ShaPage.pageCom.refreshOrderCnt(
								self.jsContext, 
								self.menuForm, 
								function(){
									self.getObjectInForm(self.fatherForm, self.ID.BTN_BACK).click();
								}
							);
		                    
		                }
		            );
				}
			);
	    }
	);
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose(); 
	    }
	);
};

//checkValue
ReviewOk.prototype.check = function(){
	//keep self instance for call back
	var self = this;
	ShaCheck.check.setFirstItemFocus(true);
	
    if (ShaCheck.check.checkNotBlank([[ self.i18n["m_order.pay_amount"], self.getObject(self.ID.ITEM_PAY_AMOUNT)]])) {
		return true;
	}
		
	if (ShaCheck.check.checkNotNumber([[ self.i18n["m_order.pay_amount"], self.getObject(self.ID.ITEM_PAY_AMOUNT)]])) {
		return true;
	}
	
	if (ShaCheck.check.checkNumberRange([[ self.i18n["m_order.pay_amount"], 	
		self.getObject(self.ID.ITEM_PAY_AMOUNT), parseInt(self.getObject(self.ID.HID_ITEM_ORDER_AMOUNT).val()), ShaConstants.constants.MIN_BILL_AMOUNT]])) {
		return true;
	}
		
	return false;
};

// initFocus
ReviewOk.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	ShaUtil.other.setFocus(self.getObject(self.ID.ITEM_PAY_AMOUNT));
};
//----------------------------------------------------------------------------]
