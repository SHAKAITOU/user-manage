//-------------------------------------------------------------------------------------------
//-----------------familyBudgetBillDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
FamilyBudgetBillDetail = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#family_budget_bill_detail_form');
};
ShaUtil.other.inherits(FamilyBudgetBillDetail, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
FamilyBudgetBillDetail.prototype.ID = {
	
	BTN_BUDGET_IN_ADD_INIT_ID			: 'btnAddBudgetBillDetailInInit',
	BTN_BUDGET_IN_EDIT_INIT_ID			: 'btnEditBudgetBillDetailInInit',
	BTN_BUDGET_IN_DELETE_ID				: 'btnDeleteBudgetBillDetailIn',
	BTN_BUDGET_OUT_ADD_INIT_ID			: 'btnAddBudgetBillDetailOutInit',
	BTN_BUDGET_OUT_EDIT_INIT_ID			: 'btnEditBudgetBillDetailOutInit',
	BTN_BUDGET_OUT_DELETE_ID			: 'btnDeleteBudgetBillDetailOut',
	//item
	ITEM_FAMILYBUDGETBILL_HEAD_ID	 	: 'familyBudgetBillHeadId',
	
	//tab
	IN_TABLE_LIST							: 'familyBudgetBillDetailInTable',
	ITEM_SELECT_BUDGET_BILL_DETAIL_IN_ID	: 'selectFamilyBudgetBillDetailInId',
	//tab
	OUT_TABLE_LIST							: 'familyBudgetBillDetailOutTable',
	ITEM_SELECT_BUDGET_BILL_DETAIL_OUT_ID	: 'selectFamilyBudgetBillDetailOutId',
};
//------------------------------------------]

//---------------method define--------------[
//init 
FamilyBudgetBillDetail.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.familyBudgetBillDetail = self;
	
	//init bond event to btn
	self.initEvent();
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_IN_EDIT_INIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_IN_DELETE_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_OUT_EDIT_INIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_OUT_DELETE_ID));

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
	
	ShaDialog.tooltip.boundleTooltips(self.getForm());
};

// init event
FamilyBudgetBillDetail.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_IN_ADD_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'familyBudgetBillHeadId', 
					self.getObject(self.ID.ITEM_FAMILYBUDGETBILL_HEAD_ID).val());
			ShaUtil.json.setJsonData(sendData, 'inOut', "1");
			ShaAjax.pop.postSubDialogMiddleCenter(
				self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_addIn_init_title,
				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail_add_init, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_OUT_ADD_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'familyBudgetBillHeadId', 
					self.getObject(self.ID.ITEM_FAMILYBUDGETBILL_HEAD_ID).val());
			ShaUtil.json.setJsonData(sendData, 'inOut', "2");
			ShaAjax.pop.postSubDialogMiddleCenter(
				self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_addOut_init_title,
				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail_add_init, 
				sendData
            );
		}
	);
	

	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_IN_EDIT_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'familyBudgetBillDetailId', 
					self.getObject(self.ID.ITEM_SELECT_BUDGET_BILL_DETAIL_IN_ID).val());
			ShaUtil.json.setJsonData(sendData, 'inOut', "1");
			ShaAjax.pop.postSubDialogMiddleCenter(
				self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_editIn_init_title,
				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail_edit_init, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_OUT_EDIT_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'familyBudgetBillDetailId', 
					self.getObject(self.ID.ITEM_SELECT_BUDGET_BILL_DETAIL_OUT_ID).val());
			ShaUtil.json.setJsonData(sendData, 'inOut', "2");
			ShaAjax.pop.postSubDialogMiddleCenter(
				self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_editOut_init_title,
				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail_edit_init, 
				sendData
            );
		}
	);
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.IN_TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.IN_TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.IN_TABLE_LIST, 
    	function(tr, trIndex){
    		self.getObject(self.ID.ITEM_SELECT_BUDGET_BILL_DETAIL_IN_ID).val(tr.attr("rowDataKey"));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_IN_EDIT_INIT_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_IN_DELETE_ID));
        	return;
    	}
    );
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.OUT_TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.OUT_TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.OUT_TABLE_LIST, 
    	function(tr, trIndex){
    		self.getObject(self.ID.ITEM_SELECT_BUDGET_BILL_DETAIL_OUT_ID).val(tr.attr("rowDataKey"));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_OUT_EDIT_INIT_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_OUT_DELETE_ID));
        	return;
    	}
    );
	
};

FamilyBudgetBillDetail.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	var sendData = ShaUtil.json.create();
	ShaUtil.json.setJsonData(sendData, 'selectFamilyBudgetBillId', 
			self.getObject(self.ID.ITEM_FAMILYBUDGETBILL_HEAD_ID).val());
	ShaAjax.pop.postDialogMiddleCenter(
		self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_title,
		self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail, 
		sendData
	);
	
};


//----------------------------------------------------------------------------]
