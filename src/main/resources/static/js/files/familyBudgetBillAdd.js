//-------------------------------------------------------------------------------------------
//-----------------familyBudgetBillAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
FamilyBudgetBillAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#family_budget_bill_add_form');
};
ShaUtil.other.inherits(FamilyBudgetBillAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
FamilyBudgetBillAdd.prototype.ID = {
	
	BTN_BUDGET_IN_ADD_INIT_ID			: 'btnAddBill',
	//item
	
	ITEM_TARGET_IN_AMOUNT 		: 'detailInResultList_familyBudgetBillDetail_targetInAmount_',
	ITEM_IN_AMOUNT 				: 'detailInResultList_familyBudgetBillDetail_inAmount_',
	ITEM_TARGET_OUT_AMOUNT 		: 'detailOutResultList_familyBudgetBillDetail_targetOutAmount_',
	ITEM_OUT_AMOUNT 			: 'detailOutResultList_familyBudgetBillDetail_outAmount_',
	
	ITEM_SHOW_TTL_TRG_IN_AMOUNT		: 'showTtlTrgInAmonu',
	ITEM_SHOW_TTL_IN_AMOUNT			: 'showTtlInAmonu',
	ITEM_SHOW_TTL_TRG_OUT_AMOUNT	: 'showTtlTrgOutAmonu',
	ITEM_SHOW_TTL_OUT_AMOUNT		: 'showTtlOutAmonu',
	
	//tab
	IN_TABLE_LIST							: 'familyBudgetBillAddInTable',
	ITEM_SELECT_BUDGET_BILL_DETAIL_IN_ID	: 'selectFamilyBudgetBillAddInId',
	//tab
	OUT_TABLE_LIST							: 'familyBudgetBillAddOutTable',
	ITEM_SELECT_BUDGET_BILL_DETAIL_OUT_ID	: 'selectFamilyBudgetBillAddOutId',
	
	
};
//------------------------------------------]

//---------------method define--------------[
//init 
FamilyBudgetBillAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
FamilyBudgetBillAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_IN_ADD_INIT_ID), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
            ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.familyBudgetBill.family_budget_bill_add_init_title,
            	self.getJsContext().common.msg_dialogs_confirm_add,
            	function(){
            		ShaAjax.ajax.post(
            				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_add_regist,
	            			self.getForm().serializeArray(),
	            			function(data){self.callBackAdd(data);}
	            		);
            	}
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
    
    self.getForm().find('[id^='+self.ID.ITEM_TARGET_IN_AMOUNT+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var ttlAmount = self.countTtlAmount(self.ID.ITEM_TARGET_IN_AMOUNT);
				self.getObject(self.ID.ITEM_SHOW_TTL_TRG_IN_AMOUNT).html(ShaUtil.number.formatCurrency(ttlAmount));
		    }
		);
	});
    
    self.getForm().find('[id^='+self.ID.ITEM_IN_AMOUNT+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var ttlAmount = self.countTtlAmount(self.ID.ITEM_IN_AMOUNT);
				self.getObject(self.ID.ITEM_SHOW_TTL_IN_AMOUNT).html(ShaUtil.number.formatCurrency(ttlAmount));
		    }
		);
	});
    
    self.getForm().find('[id^='+self.ID.ITEM_TARGET_OUT_AMOUNT+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var ttlAmount = self.countTtlAmount(self.ID.ITEM_TARGET_OUT_AMOUNT);
				self.getObject(self.ID.ITEM_SHOW_TTL_TRG_OUT_AMOUNT).html("-"+ShaUtil.number.formatCurrency(ttlAmount));
		    }
		);
	});
    
    self.getForm().find('[id^='+self.ID.ITEM_OUT_AMOUNT+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var ttlAmount = self.countTtlAmount(self.ID.ITEM_OUT_AMOUNT);
				self.getObject(self.ID.ITEM_SHOW_TTL_OUT_AMOUNT).html("-"+ShaUtil.number.formatCurrency(ttlAmount));
		    }
		);
	});
	
};

FamilyBudgetBillAdd.prototype.countTtlAmount = function(prefixId) {
	//keep self instance for call back
	var self = this;	
	var ttlAmount = 0;
	self.getForm().find('[id^='+prefixId+']').each(function(i, elem){
		var trip = $(elem).val();
		if (trip == '') {
			$(elem).val('0');
		}
		trip = $(elem).val();
		ttlAmount += parseInt(trip);
	});
	
	return ttlAmount;
}

//check
FamilyBudgetBillAdd.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    
    return false;
};

//callBackAdd
FamilyBudgetBillAdd.prototype.callBackAdd = function(data){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	if (data == 'NG') {
		msg = self.getJsContext().jsView.familyBudgetBill.family_budget_bill_add_msg_exist;
		ShaDialog.dialogs.alert(msg);
	} else {
		ShaDialog.dialogs.dialogClose();
		ShaDialog.dialogs.success(msg);
		Pos.constants.familyBudgetBillManage.refresh();
	}
};

//----------------------------------------------------------------------------]
