//-------------------------------------------------------------------------------------------
//-----------------ReceiptDetailEdit.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ReceiptDetailEdit = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.formId = '#receipt_detail_edit_form';
	this.form = $(this.formId);
	
};
ShaUtil.other.inherits(ReceiptDetailEdit, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ReceiptDetailEdit.prototype.ID = {
		
	BTN_EDIT				: 'btnEdit',
		
	
	ITEM_SELECT_CATEGORY	: 'categoryId',
	ITEM_SELECT_CATEGORY_NAME	: 'categoryName',
	ITEM_QTY				: 'qty',
	ITEM_PRICE				: 'price',
	ITEM_AMOUNT				: 'amount',
	ITEM_FIX_PRICE_TYPE		: 'fixPriceType',
	FIX_AMONUT				: '1',
};
//------------------------------------------]

//---------------method define--------------[
//init 
ReceiptDetailEdit.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.getObject(self.ID.ITEM_SELECT_CATEGORY_NAME).val(
			self.getObject(self.ID.ITEM_SELECT_CATEGORY).find(":selected").attr('data'));

	//init bond event to btn
	self.initEvent();

	//init click to view
};

// init event
ReceiptDetailEdit.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_SELECT_CATEGORY), 
		function(event) {
			self.getObject(self.ID.ITEM_SELECT_CATEGORY_NAME).val(
					self.getObject(self.ID.ITEM_SELECT_CATEGORY).find(":selected").attr('data'));
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_PRICE), 
		function(event) {
			self.calculate();
		}
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_QTY), 
		function(event) {
			self.calculate();
		}
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_AMOUNT), 
		function(event) {
			self.calculate();
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_EDIT), 
		function(event) {
			ShaAjax.ajax.post(
				self.getJsContext().jsView.receipt.url_receipt_detail_edit,
				self.getForm().serializeArray(),
				function(){self.callBackEdit();}
			);
		
	    }
	);
	


};

ReceiptDetailEdit.prototype.calculate = function(){
	//keep self instance for call back
	var self = this;
	var qty = parseInt(self.getObject(self.ID.ITEM_QTY).val(), 10);
	if (qty < 1) {
		qty = 1;
	}
	self.getObject(self.ID.ITEM_QTY).val(qty);
	
	var price = parseInt(self.getObject(self.ID.ITEM_PRICE).val(), 10);
	if (price < 1) {
		price = 1;
	}
	self.getObject(self.ID.ITEM_PRICE).val(price);
	
	var amount = parseInt(self.getObject(self.ID.ITEM_AMOUNT).val(), 10);
	if (amount < 1) {
		amount = 1;
	}
	self.getObject(self.ID.ITEM_PRICE).val(amount);
	
	var fixPriceType = self.getForm().find('[id^='+self.ID.ITEM_FIX_PRICE_TYPE+']').filter(":checked").val();
	if (fixPriceType === self.ID.FIX_AMONUT) {
		price = parseInt(amount/qty, 10);
	} else {
		amount = price*qty;
	}
	self.getObject(self.ID.ITEM_PRICE).val(price);
	self.getObject(self.ID.ITEM_AMOUNT).val(amount);
};

ReceiptDetailEdit.prototype.callBackEdit = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	ShaDialog.dialogs.subSubDialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.receiptImgAnalysisList.refresh();
};
//----------------------------------------------------------------------------]
