//-------------------------------------------------------------------------------------------
//-----------------storePrice.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
StorePrice = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#store_price_list_form');
};
ShaUtil.other.inherits(StorePrice, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
StorePrice.prototype.ID = {

	BTN_SAVE						: 'btnSaveBuyPriceHistory',
	
	ITEM_SELECT_STORE_ID			:'selectStoreId',
	
	TABLE_LIST						: 'storePriceListTable',
	
	ITEM_TRIP 						: 'trip_',
	
	SELECT_TRIP_VALUE				: 0,

};
//------------------------------------------]

//---------------method define--------------[
//init 
StorePrice.prototype.init = function(){
	//keep self instance for call back
	var self = this;

	//init bond event to btn
	self.initEvent();

	//init click to view

};

// init event
StorePrice.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SAVE), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	self.getJsContext().jsView.storePrice.save_buy_price_title,
            	self.getJsContext().jsView.storePrice.save_buy_price_msg_confirm,
            	function(){
            		var sendData = ShaUtil.json.create();
        			ShaUtil.json.setJsonData(sendData, 'selectStoreId', 
        					self.getObject(self.ID.ITEM_SELECT_STORE_ID).val());
        			ShaUtil.json.setJsonData(sendData, 'trip', 
        					self.ID.SELECT_TRIP_VALUE);
            		ShaAjax.ajax.post(
            			self.getJsContext().jsView.storePrice.url_save_buy_price,
            			sendData,
            			function(){self.callBack();}
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
    		self.setPostData(tr.attr("rowDataKey"));
    		self.ID.SELECT_TRIP_VALUE = self.getObject(self.ID.ITEM_TRIP+trIndex).val();
        	return;
    	}
    );
    
    self.getForm().find('[id^='+self.ID.ITEM_TRIP+']').each(function(i, elem){
		ShaInput.button.onChange($(elem), 
			function(event) {
				var trip = $(elem).val();
				if (trip == '') {
					$(elem).val('0');
				}
				trip = $(elem).val();
				var priceIn = self.getObject("priceIn_"+i).val();
				var diff = self.getObject("diff_"+i).val();
				
				var newDiff = parseInt(trip) + parseInt(diff);
				var newPriceIn = parseInt(trip) + parseInt(priceIn);
				self.getObject("showDiff_"+i).removeClass('label-14b-green');
				self.getObject("showDiff_"+i).removeClass('label-14b-red');
				if (newDiff >= 0) {
					self.getObject("showDiff_"+i).addClass('label-14b-red');
				} else {
					self.getObject("showDiff_"+i).addClass('label-14b-green');
				}
				self.getObject("showPriceIn_"+i).html(ShaUtil.number.formatCurrency(newPriceIn));
				self.getObject("showDiff_"+i).html(ShaUtil.number.formatCurrency(newDiff));
		    }
		);
	});
};

//setPostData
StorePrice.prototype.setPostData = function(selectStoreId){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_STORE_ID).val(selectStoreId);
	
};

//callBack
StorePrice.prototype.callBack = function(msg){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.subDialogClose();
	ShaDialog.dialogs.dialogClose();
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	ShaDialog.dialogs.success(msg);
};
//----------------------------------------------------------------------------]
