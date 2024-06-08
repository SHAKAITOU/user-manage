//-------------------------------------------------------------------------------------------
//-----------------ProductStoreSetting.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ProductStoreSetting = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#product_store_setting_form');
	
};
ShaUtil.other.inherits(ProductStoreSetting, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ProductStoreSetting.prototype.ID = {
		
	BTN_SETTING						: 'btnSetting',
		
	TABLE_LIST						: 'productStoreListTable',	

	ITEM_TAX_RATE_RADIO 			: 'productResult_product_taxRate',
	ITEM_TAX_RATE 					: "productStoreResultList_productStore_taxRate",
	ITEM_PRICE_EX					: 'productStoreResultList_productStore_priceEx',
	ITEM_TAX						: 'productStoreResultList_productStore_tax',
	ITEM_PRICE_IN					: 'productStoreResultList_productStore_priceIn',
};
//------------------------------------------]

//---------------method define--------------[
//init 
ProductStoreSetting.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	self.getObject(self.ID.BTN_FULL_SIZE).click();
};

// init event
ProductStoreSetting.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE_RADIO+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var taxRate = self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE_RADIO+']').filter(":checked").val();
				self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE+']').each(function(i, elem){
					var index = i;
					$(elem).val(taxRate);
					var price = self.getObject(self.ID.ITEM_PRICE_EX+"_"+i).val();
					var priceIn;
					var tax;
					if(price != ''){
						priceIn = ShaUtil.number.getPriceInFromPrice(price, taxRate, 1, 0);
						tax = parseFloat(priceIn) - parseFloat(price);
					}else{
						priceIn = '';
						tax = '';
					}
					self.getObject(self.ID.ITEM_TAX+"_"+i).val(tax);
					self.getObject(self.ID.ITEM_PRICE_IN+"_"+i).val(priceIn);
				});
		    }
		);
	});
	
	self.getForm().find('[id^='+self.ID.ITEM_PRICE_EX+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				if ($(elem).val().length >= 8) {
					$(elem).val($(elem).val().slice(0, 8));
		        }
				var taxRate = self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE_RADIO+']').filter(":checked").val();
				var price = $(elem).val();
				var priceIn;
				var tax;
				if(price != ''){
					priceIn = ShaUtil.number.getPriceInFromPrice(price, taxRate, 1, 0);
					tax = parseFloat(priceIn) - parseFloat(price);
				}else{
					priceIn = '';
					tax = '';
				}
				self.getObject(self.ID.ITEM_TAX+"_"+i).val(tax);
				self.getObject(self.ID.ITEM_PRICE_IN+"_"+i).val(priceIn);
        	}
		);		
	});
	
	self.getForm().find('[id^='+self.ID.ITEM_PRICE_IN+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				if ($(elem).val().length >= 8) {
					$(elem).val($(elem).val().slice(0, 8));
		        }
				var taxRate = self.getForm().find('[id^='+self.ID.ITEM_TAX_RATE_RADIO+']').filter(":checked").val();
				var priceIn = self.getObject(self.ID.ITEM_PRICE_IN+"_"+i).val();
				var price;
		        var tax;
		        if(priceIn != ''){
					price = ShaUtil.number.getPriceFromPriceIn(priceIn, taxRate, 1, 0);
		        	tax = parseFloat(priceIn) - parseFloat(price);
		        }else{
		        	price = '';
		        	tax = '';
		        }
				self.getObject(self.ID.ITEM_TAX+"_"+i).val(tax);
				self.getObject(self.ID.ITEM_PRICE_EX+"_"+i).val(price);
    		}
		);
	});
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
        	return;
    	}
    );
    
    self.getForm().find('[id^='+self.ID.ITEM_TAX+']').each(function(i, elem){
    	ShaInput.obj.readonly($(elem));
	});
    

	ShaInput.button.onClick(self.getObject(self.ID.BTN_SETTING), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.productManage.product_store_setting_init_title,
            	self.getJsContext().jsView.productManage.product_store_setting_msg_confirm,
            	function(){
            		ShaAjax.ajax.post(
            			self.getJsContext().jsView.productManage.url_product_store_setting,
            			self.getForm().serializeArray(),
            			function(){self.callBackSetting();}
            		);
            	}
            );
		}
	);
	
	
};

//callBackAdd
ProductStoreSetting.prototype.callBackSetting = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
};

//----------------------------------------------------------------------------]
