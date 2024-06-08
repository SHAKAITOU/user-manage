//-------------------------------------------------------------------------------------------
//-----------------buyProductListDiv.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BuyProductListDiv = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(BuyProductListDiv, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BuyProductListDiv.prototype.ID = {
	
	BTN_PRODUCT_FILTER_ID	: "btnProductFilter",
	
	ITEM_PRODUCT_FILTER		: "filterProductName",
	
	BTN_SHOW_CART_ID		: "btnShowCartList",
	
	BTN_ADD_TO_CART_ID		: "btnAddToCart",
	
	// レシート写真解析
	BTN_UPLOAD_RECEIPT_IMG	: "btnUploadReceiptImgInit",
	
	TABLE_LIST						: 'productListTable',
	ITEM_SELECT_BID_ID				: 'selectBidId',
	
	ITEM_SELECT_CTGRY_ID			: 'selectCtgryId',
	
	ITEM_SELECT_KIND_ID				: 'selectKindId',
	
	ITEM_PRODUCT_FILTER				: "filterProductName",
	
	ITEM_SELECT_PRODUCT_ID			: "",

};
//------------------------------------------]

//---------------method define--------------[
//init 
BuyProductListDiv.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_ADD_TO_CART_ID));
	
	Pos.constants.receiptCallBackAttach.ctrlClass = self;
	Pos.constants.receiptCallBackAttach.callBackFunc = null;
	
	//init bond event to btn
	self.initEvent();

	//init click to view

};

// init event
BuyProductListDiv.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	ShaInput.button.onChange(self.getObject(self.ID.ITEM_PRODUCT_FILTER), 
		function(event) {
			var filterValue = self.getObject(self.ID.ITEM_PRODUCT_FILTER).val();
			ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", filterValue);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT_FILTER_ID), 
		function(event) {
			var filterValue = self.getObject(self.ID.ITEM_PRODUCT_FILTER).val();
			ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", filterValue);
		}
	);
	
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD_TO_CART_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectProductId', self.ID.ITEM_SELECT_PRODUCT_ID);
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.product.cart_add_init_title,
				self.getJsContext().jsView.product.url_cart_add_init, 
				sendData
	        );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SHOW_CART_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.product.cart_list_title,
				self.getJsContext().jsView.product.url_cart_list_search, 
				{}
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_UPLOAD_RECEIPT_IMG), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.receipt.receipt_img_upload_init_title,
				self.getJsContext().jsView.receipt.url_receipt_img_upload_init, 
				{}
            );
		}
	);

	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.ID.ITEM_SELECT_PRODUCT_ID = tr.attr("rowDataKey");
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_ADD_TO_CART_ID));
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
};

//----------------------------------------------------------------------------]
