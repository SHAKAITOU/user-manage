//-------------------------------------------------------------------------------------------
//-----------------productAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ProductAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.formId = '#'+this.dataMap.addForm;
	this.form = $(this.formId);
};
ShaUtil.other.inherits(ProductAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ProductAdd.prototype.ID = {
	BTN_ADD							: "btnAddProduct",
	BTN_SHOW_IMG					: 'btnShowImg',
	//item
	ITEM_ADD_FLAG	 	: 'addFlag',
	ITEM_CATEGORYNAME	: 'categoryName',
	ITEM_NAME 			: 'productResult_product_name',
	ITEM_PRICE_EX 		: 'productResult_product_priceEx',
	ITEM_TAX 			: 'productResult_product_tax',
	ITEM_TAX_RATE 		: 'productResult_product_taxRate',
	ITEM_PRICE_IN 		: 'productResult_product_priceIn',
	ITEM_FILE_IMG		: 'fileName',
	ITEM_PREVIEW_IMG	: 'filePreview',
};
//------------------------------------------]

//---------------method define--------------[
//init 
ProductAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.readonly(self.getObject(self.ID.ITEM_CATEGORYNAME));
	ShaInput.obj.readonly(self.getObject(self.ID.ITEM_TAX));
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
ProductAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var taxRate = self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE+']').filter(":checked").val();
				var price = self.getObject(self.ID.ITEM_PRICE_EX).val();
				var priceIn;
		        var tax;
				if(price != ''){
					priceIn = ShaUtil.number.getPriceInFromPrice(price, taxRate, 1, 0);
			        tax = parseFloat(priceIn) - parseFloat(price);
				}else{
					priceIn = '';
		        	tax = '';
				}
				self.getObject(self.ID.ITEM_TAX).val(tax);
				self.getObject(self.ID.ITEM_PRICE_IN).val(priceIn);
		    }
		);
	});
	
	//init event to ITEM_PRODUCT_PRICE_BUY
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_PRICE_EX), 
		function(event) {
			if (self.getObject(self.ID.ITEM_PRICE_EX).val().length >= 8) {
				self.getObject(self.ID.ITEM_PRICE_EX).val(self.getObject(self.ID.ITEM_PRICE_EX).val().slice(0, 8));
	        }
			var taxRate = self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE+']').filter(":checked").val();
			var price = self.getObject(self.ID.ITEM_PRICE_EX).val();
			var priceIn;
	        var tax;
			if(price != ''){
				priceIn = ShaUtil.number.getPriceInFromPrice(price, taxRate, 1, 0);
		        tax = parseFloat(priceIn) - parseFloat(price);
			}else{
				priceIn = '';
	        	tax = '';
			}
			self.getObject(self.ID.ITEM_TAX).val(tax);
			self.getObject(self.ID.ITEM_PRICE_IN).val(priceIn);
        }
	);
	
	//init event to ITEM_PRODUCT_PRICE_BUY
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_PRICE_IN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_PRICE_IN).val().length >= 8) {
				self.getObject(self.ID.ITEM_PRICE_IN).val(self.getObject(self.ID.ITEM_PRICE_IN).val().slice(0, 8));
	        }
			var taxRate = self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE+']').filter(":checked").val();
			var priceIn = self.getObject(self.ID.ITEM_PRICE_IN).val();
			var price;
	        var tax;
	        if(priceIn != ''){
				price = ShaUtil.number.getPriceFromPriceIn(priceIn, taxRate, 1, 0);
	        	tax = parseFloat(priceIn) - parseFloat(price);
	        }else{
	        	price = '';
	        	tax = '';
	        }
			self.getObject(self.ID.ITEM_TAX).val(tax);
			self.getObject(self.ID.ITEM_PRICE_EX).val(price);
        }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_FILE_IMG), 
		function(event) {
			ShaInput.img.preview(self.getForm(), self.ID.ITEM_FILE_IMG, self.ID.ITEM_PREVIEW_IMG);
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SHOW_IMG), 
		function(event) {
			Pos.constants.showImgOrgObj = self.getObject(self.ID.ITEM_PREVIEW_IMG);
			ShaAjax.pop.postSubDialogMiddleCenter(
					self.getJsContext().jsView.commonUtil.showImg_title,
					self.getJsContext().jsView.commonUtil.url_showImg, 
					{}
	            );
		
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if(self.getObject(self.ID.ITEM_ADD_FLAG).val() == 'true'){
	            ShaDialog.dialogs.confirm(
	            	self.getJsContext().jsView.productManage.product_add_init_title,
	            	self.getJsContext().common.msg_dialogs_confirm_add,
	            	function(){
	            		ShaAjax.ajax.postWithUploadFile(
	            			self.getJsContext().jsView.productManage.url_product_add_regist,
	            			self.formId,
	            			function(){self.callBackAdd();}
	            		);
	            	}
	            );
            }
            else{
            	ShaDialog.dialogs.confirm(
            		self.getJsContext().jsView.productManage.product_edit_init_title,
            		self.getJsContext().common.msg_dialogs_confirm_edit,
	            	function(){
            			ShaAjax.ajax.postWithUploadFile(
	            			self.getJsContext().jsView.productManage.url_product_edit_regist,
	            			self.formId,
	            			function(){self.callBackEdit();}
	            		);
	            	}
	            );
            }
		}
	);
	
	
};

//check
ProductAdd.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.productManage.label_name, self.getObject(self.ID.ITEM_NAME)],
    	[ self.getJsContext().jsView.productManage.label_priceEx, self.getObject(self.ID.ITEM_PRICE_EX)],
    	[ self.getJsContext().jsView.productManage.label_priceIn, self.getObject(self.ID.ITEM_PRICE_IN)],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};

//callBackAdd
ProductAdd.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	self.callBack(msg);
};

// callBackEdit
ProductAdd.prototype.callBackEdit = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	self.callBack(msg);
};

//callBack
ProductAdd.prototype.callBack = function(msg){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.productManage.searchProduct();
};

//----------------------------------------------------------------------------]
