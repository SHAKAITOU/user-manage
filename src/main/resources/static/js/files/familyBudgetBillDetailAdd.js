//-------------------------------------------------------------------------------------------
//-----------------familyBudgetBillDetailAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
FamilyBudgetBillDetailAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#family_budget_bill_detail_add_form');
};
ShaUtil.other.inherits(FamilyBudgetBillDetailAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
FamilyBudgetBillDetailAdd.prototype.ID = {
	BTN_ADD				: "btnAddBudgetBillDetail",
	//item
	ITEM_ADD_FLAG	 	: 'addFlag',
	
	ITEM_AMOUNT			: 'amount',
	
	ITEM_INOUT			: 'inOut',
};
//------------------------------------------]

//---------------method define--------------[
//init 
FamilyBudgetBillDetailAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
FamilyBudgetBillDetailAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if(self.getObject(self.ID.ITEM_ADD_FLAG).val() == 'true'){
				var title = self.getObject(self.ID.ITEM_INOUT).val() == '1' ? 
						self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_addIn_init_title :
							self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_addOut_init_title;
	            ShaDialog.dialogs.confirm(
	            		title,
	            	self.getJsContext().common.msg_dialogs_confirm_add,
	            	function(){
	            		ShaAjax.ajax.post(
	            				self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail_add_regist,
		            			self.getForm().serializeArray(),
		            			function(){self.callBackAdd();}
		            		);
	            	}
	            );
            }
            else{
            	var title = self.getObject(self.ID.ITEM_INOUT).val() == '1' ? 
						self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_editIn_init_title :
							self.getJsContext().jsView.familyBudgetBill.family_budget_bill_detail_editOut_init_title;
            	ShaDialog.dialogs.confirm(
            			title,
            		self.getJsContext().common.msg_dialogs_confirm_edit,
	            	function(){
            			ShaAjax.ajax.post(
	            			self.getJsContext().jsView.familyBudgetBill.url_family_budget_bill_detail_edit_regist,
	            			self.getForm().serializeArray(),
	            			function(){self.callBackEdit();}
	            		);
	            	}
	            );
            }
		}
	);
	
	
};

//check
FamilyBudgetBillDetailAdd.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.familyBudgetBill.label_amount, self.getObject(self.ID.ITEM_AMOUNT)],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};

//callBackAdd
FamilyBudgetBillDetailAdd.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	self.callBack(msg);
};

// callBackEdit
FamilyBudgetBillDetailAdd.prototype.callBackEdit = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	self.callBack(msg);
};

//callBack
FamilyBudgetBillDetailAdd.prototype.callBack = function(msg){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.subDialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.familyBudgetBillDetail.refresh();
};

//----------------------------------------------------------------------------]
