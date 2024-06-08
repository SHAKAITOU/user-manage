//-------------------------------------------------------------------------------------------
//-----------------CartListDiv.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
CartListDiv = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#cart_list_form');
};
ShaUtil.other.inherits(CartListDiv, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
CartListDiv.prototype.ID = {

	BTN_ADD_ID						: 'btnAddQty',
	BTN_MINUS_ID					: 'btnMinusQty',
	BTN_DELETE_ID					: 'btnDeleteFromCart',
	BTN_COMPARE_ID					: 'btnComparePrice',
	
	ITEM_SELECT_PRODUCT_ID			: 'selectProductId',
	
	TABLE_LIST						: 'cartListTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
CartListDiv.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_ADD_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_MINUS_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_DELETE_ID));
	
	var rows = ShaInput.table.length(self.getForm(), self.ID.TABLE_LIST);
	if (rows == 0) {
		ShaInput.obj.disabled(self.getObject(self.ID.BTN_COMPARE_ID));
	}

	//init bond event to btn
	self.initEvent();

	//init click to view

};

// init event
CartListDiv.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectProductId', 
					self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val());
			ShaUtil.json.setJsonData(sendData, 'addQty', '1');
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.product.cart_list_title,
				self.getJsContext().jsView.product.url_cart_list_search, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_MINUS_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectProductId', 
					self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val());
			ShaUtil.json.setJsonData(sendData, 'addQty', '-1');
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.product.cart_list_title,
				self.getJsContext().jsView.product.url_cart_list_search, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_DELETE_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectProductId', 
					self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val());
			ShaUtil.json.setJsonData(sendData, 'addQty', '0');
			ShaDialog.dialogs.confirm(
        		self.getJsContext().jsView.product.cart_delete_init_title,
        		self.getJsContext().common.msg_dialogs_confirm_delete,
            	function(){
        			ShaAjax.pop.postDialogMiddleCenter(
    					self.getJsContext().jsView.product.cart_list_title,
    					self.getJsContext().jsView.product.url_cart_list_search, 
    					sendData
    	            );
            	}
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_COMPARE_ID), 
		function(event) {
			ShaAjax.pop.postSubDialogMiddleCenter(
				self.getJsContext().jsView.storePrice.store_price_init_title,
				self.getJsContext().jsView.storePrice.url_store_price_init, 
				{}
            );
		}
	);
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.setPostData(tr.attr("rowDataKey"));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_ADD_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_MINUS_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_DELETE_ID));
        	return;
    	}
    );
	

    $imgList = self.getObject(self.ID.TABLE_LIST).find('img');
    $imgList.each(function(i, elem){
		ShaInput.button.onClick($(elem), 
			function(event) {
				Pos.constants.showImgOrgObj = $(elem);
				ShaAjax.pop.postSubDialogMiddleCenter(
						self.getJsContext().jsView.commonUtil.showImg_title,
						self.getJsContext().jsView.commonUtil.url_showImg, 
						{}
	            );
		    }
		);
	});
    
    /*
    $buttonList = self.getObject(self.ID.TABLE_LIST).find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('addToCar')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					var sendData = ShaUtil.json.create();
					ShaUtil.json.setJsonData(sendData, 'selectProductId', $(elem).attr('data'));
					ShaAjax.pop.postDialogMiddleCenter(
						self.getJsContext().jsView.product.cart_add_init_title,
						self.getJsContext().jsView.product.url_cart_add_init, 
						sendData
		            );
			    }
			);
		}
	});
	*/
};

//setPostData
CartListDiv.prototype.setPostData = function(selectProductId){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val(selectProductId);
};
//----------------------------------------------------------------------------]
