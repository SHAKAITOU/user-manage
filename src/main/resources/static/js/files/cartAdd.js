//-------------------------------------------------------------------------------------------
//-----------------cartAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
CartAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#add_cart_form');
	
};
ShaUtil.other.inherits(CartAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
CartAdd.prototype.ID = {
	BTN_ADD_ID		: 'btnAddToCart',
	BTN_M_QTY_ID	: 'btnMinusQty',
	BTN_P_QTY_ID	: 'btnAddQty',
	
	ITEM_QTY		: 'qty',
	ITEM_PRICE_IN	: 'productResult_product_priceIn',
	
	ITEM_TTL_PRICE_IN : "ttlPriceIn",
	
};
//------------------------------------------]

//---------------method define--------------[
//init 
CartAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.readonly(self.getObject(self.ID.ITEM_QTY));
	
	//init bond event to btn
	self.initEvent();

	//init click to view

};

// init event
CartAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_M_QTY_ID), 
		function(event) {
			var qty = parseInt(self.getObject(self.ID.ITEM_QTY).val(), 10) - 1;
			if (qty < 1) {
				qty = 1;
			}
			self.getObject(self.ID.ITEM_QTY).val(qty);
			var priceIn = parseInt(self.getObject(self.ID.ITEM_PRICE_IN).val(), 10);
			var ttl = ShaUtil.number.formatCurrency(priceIn*qty);
			self.getObject(self.ID.ITEM_TTL_PRICE_IN).html(ttl);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_P_QTY_ID), 
		function(event) {
			var qty = parseInt(self.getObject(self.ID.ITEM_QTY).val(), 10) + 1;
			self.getObject(self.ID.ITEM_QTY).val(qty);
			var priceIn = parseInt(self.getObject(self.ID.ITEM_PRICE_IN).val(), 10);
			var ttl = ShaUtil.number.formatCurrency(priceIn*qty);
			self.getObject(self.ID.ITEM_TTL_PRICE_IN).html(ttl);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD_ID), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.product.cart_add_init_title,
            	self.getJsContext().jsView.product.cart_add_confirm_msg,
            	function(){
            		ShaAjax.ajax.post(
            			self.getJsContext().jsView.product.url_cart_add,
            			self.getForm().serializeArray(),
            			function(){self.callBackAdd();}
            		);
            	}
            );
		}
	);
	
};

//callBackAdd
CartAdd.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().jsView.product.cart_add_success_msg;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
};
//----------------------------------------------------------------------------]
