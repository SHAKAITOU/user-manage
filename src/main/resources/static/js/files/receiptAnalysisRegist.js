//-------------------------------------------------------------------------------------------
//-----------------ReceiptAnalysisRegist.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ReceiptAnalysisRegist = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#receipt_analysis_regist_form');
	
};
ShaUtil.other.inherits(ReceiptAnalysisRegist, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ReceiptAnalysisRegist.prototype.ID = {
		
	BTN_REGIST							: 'btnAnalysisRegist',
	
	FAMILYBUDGET_SIZE					: 'familyBudgetSize',
	
	FAMILYBUDGET_BILL_SIZE				: 'familyBudgetBillSize',
	
	STORE_ID							: 'storeId',
	
	ITEM_SELECT_FAMILYBUDGET_BILL_HEAD	: 'familyBudgetBillHeadId',
	
	ITEM_SELECT_FAMILYBUDGET			: 'familyBudgetId',
	
	ITEM_STORE_DIV						: 'storeDiv',
	
	ITEM_BUDGET_DIV						: 'budgetDiv',
	
	ITEM_BUDGET_BILL_DIV				: 'budgetBillDiv',
	
	ITEM_CHECK_BOX_NAME					: [],
	
	ITEM_CHECK_BOX_SAVE_ALL				: 'saveAll',
	ITEM_CHECK_BOX_SAVE_FDONLY			: 'saveFdOnly',
	ITEM_CHECK_BOX_SAVE_PRODUCTONLY		: 'saveProductOnly',
	
	ITEM_SPAN_NAME					: [],
	
	ITEM_SPAN_SAVE_ALL				: 'saveAllText',
	ITEM_SPAN_SAVE_FDONLY			: 'saveFdOnlyText',
	ITEM_SPAN_SAVE_PRODUCTONLY		: 'saveProductOnlyText',
	
	
};
//------------------------------------------]

//---------------method define--------------[
//init 
ReceiptAnalysisRegist.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.ID.ITEM_CHECK_BOX_NAME = [
		self.ID.ITEM_CHECK_BOX_SAVE_ALL,
		self.ID.ITEM_CHECK_BOX_SAVE_FDONLY,
		self.ID.ITEM_CHECK_BOX_SAVE_PRODUCTONLY
	];
	
	self.ID.ITEM_SPAN_NAME = [
		self.ID.ITEM_SPAN_SAVE_ALL,
		self.ID.ITEM_SPAN_SAVE_FDONLY,
		self.ID.ITEM_SPAN_SAVE_PRODUCTONLY
	];

	
	//init bond event to btn
	self.initEvent();
	
	var fdForbid = false;
	var productForbid = false;
	if (self.getObject(self.ID.FAMILYBUDGET_SIZE).val() == '0' ||
			self.getObject(self.ID.FAMILYBUDGET_BILL_SIZE).val() == '0') {
		ShaInput.obj.disabled(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_ALL));
		ShaInput.obj.disabled(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_FDONLY));
		fdForbid = true;
	}
	
	if (self.getObject(self.ID.STORE_ID).val() == '0') {
		ShaInput.obj.disabled(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_ALL));
		ShaInput.obj.disabled(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_PRODUCTONLY));
		productForbid = true;
	}
	
	if (fdForbid && !productForbid) {
		self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_PRODUCTONLY).click();
	} else if(!fdForbid && productForbid) {
		self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_FDONLY).click();
	} else if(fdForbid && productForbid) {
		ShaInput.obj.disabled(self.getObject(self.ID.BTN_REGIST));
	}

	//init click to view

};

// init event
ReceiptAnalysisRegist.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_ALL), 
		function(event) {
			self.setCheckBoxStatus(self.ID.ITEM_CHECK_BOX_SAVE_ALL);
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_FDONLY), 
		function(event) {
			self.setCheckBoxStatus(self.ID.ITEM_CHECK_BOX_SAVE_FDONLY);
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_PRODUCTONLY), 
		function(event) {
			self.setCheckBoxStatus(self.ID.ITEM_CHECK_BOX_SAVE_PRODUCTONLY);
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST), 
		function(event) {
		
        	ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.receipt.receipt_analysis_regist_title,
            	self.getJsContext().common.msg_dialogs_confirm_add,
            	function(){
            		var sendData = ShaUtil.json.create();
            		ShaUtil.json.setJsonData(sendData, 'familyBudgetBillHeadId', 
        					self.getObject(self.ID.ITEM_SELECT_FAMILYBUDGET_BILL_HEAD).val());
        			ShaUtil.json.setJsonData(sendData, 'familyBudgetId', 
        					self.getObject(self.ID.ITEM_SELECT_FAMILYBUDGET).val());
        			ShaUtil.json.setJsonData(sendData, 'saveType', self.getCheckedBoxName());
        			ShaAjax.ajax.post(
            			self.getJsContext().jsView.receipt.url_receipt_analysis_regist,
            			sendData,
            			function(){self.callBackAdd();}
            		);
            	}
            );
	    }
	);
};

ReceiptAnalysisRegist.prototype.getCheckedBoxName = function(){
	//keep self instance for call back
	var self = this;
	for (i=0; i<self.ID.ITEM_CHECK_BOX_NAME.length; i++) {
		if (self.getObject(self.ID.ITEM_CHECK_BOX_NAME[i]).prop("checked") == true) {
			return self.ID.ITEM_CHECK_BOX_NAME[i];
		}
	}
}

ReceiptAnalysisRegist.prototype.setCheckBoxStatus = function(selectName){
	
	//keep self instance for call back
	var self = this;
	
	var selectedIndex = 0;
	var activeClassName = "label-14b";
	var normalClassName = "label-12";
	for (i=0; i<self.ID.ITEM_CHECK_BOX_NAME.length; i++) {
		if (self.ID.ITEM_CHECK_BOX_NAME[i] === selectName) {
			selectedIndex = i;
		}
	}

	var checked = self.getObject(self.ID.ITEM_CHECK_BOX_NAME[selectedIndex]).prop("checked");
	
	if (checked) {
		if (self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).hasClass(normalClassName)) {
			self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).removeClass(normalClassName);
		}
		
		if (!self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).hasClass(activeClassName)) {
			self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).addClass(activeClassName);
		}
		
		for (i=0; i<self.ID.ITEM_CHECK_BOX_NAME.length; i++) {
			if (i != selectedIndex) {
				self.getObject(self.ID.ITEM_CHECK_BOX_NAME[i]).prop("checked", false);
				if (self.getObject(self.ID.ITEM_SPAN_NAME[i]).hasClass(activeClassName)) {
					self.getObject(self.ID.ITEM_SPAN_NAME[i]).removeClass(activeClassName);
				}
				
				if (!self.getObject(self.ID.ITEM_SPAN_NAME[i]).hasClass(normalClassName)) {
					self.getObject(self.ID.ITEM_SPAN_NAME[i]).addClass(normalClassName);
				}
			}
		}
	} else {
		if (self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).hasClass(activeClassName)) {
			self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).removeClass(activeClassName);
		}
		
		if (!self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).hasClass(normalClassName)) {
			self.getObject(self.ID.ITEM_SPAN_NAME[selectedIndex]).addClass(normalClassName);
		}
	}
	
	var allUnCheck = true;
	for (i=0; i<self.ID.ITEM_CHECK_BOX_NAME.length; i++) {
		if (self.getObject(self.ID.ITEM_CHECK_BOX_NAME[i]).prop("checked") == true) {
			allUnCheck = false;
			break;
		}
	}
	
	if (allUnCheck) {
		ShaInput.obj.disabled(self.getObject(self.ID.BTN_REGIST));
	} else {
		ShaInput.obj.enabled(self.getObject(self.ID.BTN_REGIST));
	}
	
	if (self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_PRODUCTONLY).prop("checked")) {
		self.getObject(self.ID.ITEM_BUDGET_DIV).hide();
		self.getObject(self.ID.ITEM_BUDGET_BILL_DIV).hide();
		
	} else {
		self.getObject(self.ID.ITEM_BUDGET_DIV).show();
		self.getObject(self.ID.ITEM_BUDGET_BILL_DIV).show();
	}
	
	if (self.getObject(self.ID.ITEM_CHECK_BOX_SAVE_FDONLY).prop("checked")) {
		self.getObject(self.ID.ITEM_STORE_DIV).hide();
	} else {
		self.getObject(self.ID.ITEM_STORE_DIV).show();
	}
};


ReceiptAnalysisRegist.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	ShaDialog.dialogs.subSubDialogClose();
	ShaDialog.dialogs.subDialogClose();
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.receiptCallBackAttach.call();
};
//----------------------------------------------------------------------------]
