//-------------------------------------------------------------------------------------------
//-----------------familyBudgetManage.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
FamilyBudgetManage = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#family_budget_list_form');
};
ShaUtil.other.inherits(FamilyBudgetManage, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
FamilyBudgetManage.prototype.ID = {
		
	BTN_BUDGET_ADD_INIT_ID				: 'btnAddBudgetInit',
	BTN_BUDGET_EDIT_INIT_ID				: 'btnEditBudgetInit',
	BTN_BUDGET_DELETE_ID				: 'btnDeleteBudget',
	
	ITEM_IN_OUT							: 'inOut',
	ITEM_IN_OUT_IN						: '1',
	ITEM_IN_OUT_OUT						: '2',
	ITEM_IN_OUT_ALL						: '3',
	ITEM_SELECT_BUDGET_ID				: 'selectFamilyBudgetId',

	//tab
	TABLE_LIST							: 'familyBudgetTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
FamilyBudgetManage.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.familyBudgetManage = self;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_EDIT_INIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_BUDGET_DELETE_ID));
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
FamilyBudgetManage.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	self.getForm().find('[id^='+self.ID.ITEM_IN_OUT+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var type = self.getForm().find('[id^='+self.ID.ITEM_IN_OUT+']').filter(":checked").val();
				if (type == self.ID.ITEM_IN_OUT_IN) {
					ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", type);
				} else if (type == self.ID.ITEM_IN_OUT_OUT) {
					ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", type);
				} else {
					ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", "");
				}
		    }
		);
	});
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_ADD_INIT_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.familyBudget.family_budget_add_init_title,
				self.getJsContext().jsView.familyBudget.url_family_budget_add_init, 
				self.getForm().serializeArray()
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BUDGET_EDIT_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectFamilyBudgetId', 
					self.getObject(self.ID.ITEM_SELECT_BUDGET_ID).val());
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.familyBudget.family_budget_edit_init_title,
				self.getJsContext().jsView.familyBudget.url_family_budget_edit_init, 
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
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.getObject(self.ID.ITEM_SELECT_BUDGET_ID).val(tr.attr("rowDataKey"));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_EDIT_INIT_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_BUDGET_DELETE_ID));
        	return;
    	}
    );

};

//callBack
FamilyBudgetManage.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

FamilyBudgetManage.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			Pos.constants.setInfo.jsView.familyBudget.url_family_budget_init,
        	{});
	
};
//----------------------------------------------------------------------------]
