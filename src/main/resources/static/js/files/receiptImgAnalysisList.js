//-------------------------------------------------------------------------------------------
//-----------------ReceiptImgAnalysisList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ReceiptImgAnalysisList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.formId = '#receipt_img_analysis_list_form';
	this.form = $(this.formId);
	
};
ShaUtil.other.inherits(ReceiptImgAnalysisList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ReceiptImgAnalysisList.prototype.ID = {
		
	BTN_EDIT_ITEM		: 'btnEditItem',
	BTN_DELETE_ITEM		: 'btnDeleteItem',
	
	BTN_REGIST_CONFIRM	: 'btnRegistConfirm',
	
	TABLE_LIST			: 'productListTable',
	
	SELECT_PRODUCT_INDEX : 0,
};
//------------------------------------------]

//---------------method define--------------[
//init 
ReceiptImgAnalysisList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.receiptImgAnalysisList = self;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_EDIT_ITEM));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_DELETE_ITEM));

	//init bond event to btn
	self.initEvent();

	//init click to view
};

// init event
ReceiptImgAnalysisList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.ID.SELECT_PRODUCT_INDEX = trIndex;
	    	ShaInput.obj.enabled(self.getObject(self.ID.BTN_EDIT_ITEM));
	    	ShaInput.obj.enabled(self.getObject(self.ID.BTN_DELETE_ITEM));

        	return;
    	}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_EDIT_ITEM), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'productIndex', self.ID.SELECT_PRODUCT_INDEX);
			ShaAjax.pop.postSubSubDialogMiddleCenter(
				self.getJsContext().jsView.receipt.receipt_detail_edit_title,
				self.getJsContext().jsView.receipt.url_receipt_detail_edit_init, 
				sendData
            );
	    }
	);
    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_DELETE_ITEM), 
		function(event) {
			ShaDialog.dialogs.confirm(
        		self.getJsContext().jsView.store.receipt_detail_delete_title,
        		self.getJsContext().common.msg_dialogs_confirm_delete,
            	function(){
        			var sendData = ShaUtil.json.create();
        			ShaUtil.json.setJsonData(sendData, 'productIndex', self.ID.SELECT_PRODUCT_INDEX);
        			ShaAjax.ajax.post(
            			self.getJsContext().jsView.receipt.url_receipt_detail_delete,
            			sendData,
            			function(){self.callBackDelete();}
            		);
            	}
            );
	    }
	);
    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_REGIST_CONFIRM), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'productIndex', self.ID.SELECT_PRODUCT_INDEX);
			ShaAjax.pop.postSubSubDialogMiddleCenter(
				self.getJsContext().jsView.receipt.receipt_analysis_regist_title,
				self.getJsContext().jsView.receipt.url_receipt_analysis_regist_init, 
				sendData
            );
	    }
	);
};

//callBack
ReceiptImgAnalysisList.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

ReceiptImgAnalysisList.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.pop.postSubDialogMiddleCenter(
		self.getJsContext().jsView.receipt.receipt_img_analysis_list_title,
		self.getJsContext().jsView.receipt.url_receipt_img_analysis_list_refresh, 
		{}
    );
};
//----------------------------------------------------------------------------]
