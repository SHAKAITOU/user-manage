//-------------------------------------------------------------------------------------------
//-----------------familyBudgetBillManage.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
FamilyBudgetBillManage = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#family_budget_bill_list_form');
};
ShaUtil.other.inherits(FamilyBudgetBillManage, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
FamilyBudgetBillManage.prototype.ID = {
		
	BTN_BUDGET_ADD_INIT_ID				: 'btnAddBudgetBillInit',
	BTN_BUDGET_EDIT_INIT_ID				: 'btnEditBudgetBillInit',
	BTN_BUDGET_DELETE_ID				: 'btnDeleteBudgetBill',
	// レシート写真解析
	BTN_UPLOAD_RECEIPT_IMG				: "btnUploadReceiptImgInit",
	
	ITEM_IN_OUT							: 'inOut',
	ITEM_IN_OUT_IN						: '1',
	ITEM_IN_OUT_OUT						: '2',
	ITEM_IN_OUT_ALL						: '3',
	ITEM_SELECT_BUDGET_BILL_ID			: 'selectFamilyBudgetBillId',

	//tab
	TABLE_LIST							: 'familyBudgetBillTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
FamilyBudgetBillManage.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.familyBudgetBillManage = self;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_EDIT_INIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_DELETE_ID));
	
	Pos.constants.receiptCallBackAttach.ctrlClass = self;
	Pos.constants.receiptCallBackAttach.callBackFunc = self.refresh;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
FamilyBudgetBillManage.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_ADD_INIT_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.familyBudgetBill.family_budget_bill_add_init_title,
				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_add_init, 
				self.getForm().serializeArray()
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_EDIT_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectFamilyBudgetBillId', 
					self.getObject(self.ID.ITEM_SELECT_BUDGET_BILL_ID).val());
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_title,
				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_DELETE_ID), 
		function(event) {
			ShaDialog.dialogs.confirm(
        		self.getJsContext().jsView.familyBudget.family_budget_delete_init_title,
        		self.getJsContext().common.msg_dialogs_confirm_delete,
            	function(){
        			var sendData = ShaUtil.json.create();
        			ShaUtil.json.setJsonData(sendData, 'selectFamilyBudgetId', 
        					self.getObject(self.ID.ITEM_SELECT_BUDGET_ID).val());
        			ShaAjax.ajax.post(
            			self.getJsContext().jsView.familyBudget.url_family_budget_delete_regist,
            			sendData,
            			function(){self.callBackDelete();}
            		);
            	}
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_UPLOAD_RECEIPT_IMG), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.receipt.receipt_img_upload_init_title,
				self.getJsContext().jsView.receipt.url_receipt_img_upload_init, 
				{}
            );
		}
	);
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.getObject(self.ID.ITEM_SELECT_BUDGET_BILL_ID).val(tr.attr("rowDataKey"));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_EDIT_INIT_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_DELETE_ID));
        	return;
    	}
    );

};

//callBack
FamilyBudgetBillManage.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

FamilyBudgetBillManage.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			Pos.constants.setInfo.jsView.familyBudgetBill.url_family_budget_bill_init,
        	{});
	
};
//----------------------------------------------------------------------------]
