//-------------------------------------------------------------------------------------------
//-----------------buyProduct.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BuyProduct = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(BuyProduct, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BuyProduct.prototype.ID = {

	BTN_MENU_BC						: 'menuBC',
	
	BTN_SHOW_CART_ID		: "btnShowCartList",
	
	BTN_ADD_TO_CART_ID		: "btnAddToCart",
	
	// レシート写真解析
	BTN_UPLOAD_RECEIPT_IMG	: "btnUploadReceiptImgInit",
	
	ITEM_SELECT_BID_ID				: 'selectBidId',
	
	ITEM_SELECT_BID_NAME			: 'selectBidName',
	
	//tab
	PRODUCT_LIST_TAB			: 'productListTab',
	//panel
	PANEL_PRODUCT_LIST			: 'productList1Panel',
	PANEL_PRODUCT_SELECTED_LIST	: 'productList2Panel',
	
	PRODUCTLISTBODY_DIV_ID			: 'productListBody',


};
//------------------------------------------]

//---------------method define--------------[
//init 
BuyProduct.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_ADD_TO_CART_ID));
	
	ShaInput.tab.initTab(self.getForm(), self.ID.PRODUCT_LIST_TAB);
	
	//init bond event to btn
	self.initEvent();
	ShaInput.tab.activeTab(self.getForm(), self.ID.PRODUCT_LIST_TAB, self.ID.PANEL_PRODUCT_LIST);
	
	//call back when kind selected
	Pos.constants.bidClickAttach.ctrlClass = self;
	Pos.constants.bidClickAttach.callBackFunc = self.searchProduct;
	Pos.constants.ctgryClickAttach.ctrlClass = self;
	Pos.constants.ctgryClickAttach.callBackFunc = self.searchProduct;
	Pos.constants.kindClickAttach.ctrlClass = self;
	Pos.constants.kindClickAttach.callBackFunc = self.searchProduct;
	
	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
	
    //set init focus item when page loaded
	ShaUtil.other.setFocus(self.getObject(self.ID.BTN_ADD_TO_CART_ID));
};

// init event
BuyProduct.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	// bid init
	ShaAjax.ajax.postToDiv(
        	self.getJsContext().jsView.bid.url_bid_card_list,
        	self.getForm().serializeArray(),
			$('#'+self.dataMap.categoryDiv));
	
	
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
};

BuyProduct.prototype.clearProduct = function(ctrlClass){
	//keep self instance for call back
	if(ctrlClass == null) {
		ctrlClass = self;
	}
	ShaAjax.ajax.postToDiv(
			Pos.constants.setInfo.jsView.product.url_buy_product_list_clear,
			ctrlClass.getForm().serializeArray(),
			ctrlClass.getObject(ctrlClass.ID.PRODUCTLISTBODY_DIV_ID));
};

BuyProduct.prototype.searchProduct = function(ctrlClass){
	//keep self instance for call back
	if(ctrlClass == null) {
		ctrlClass = self;
	}
	ShaAjax.ajax.postToDiv(
			Pos.constants.setInfo.jsView.product.url_buy_product_search,
			ctrlClass.getForm().serializeArray(),
			ctrlClass.getObject(ctrlClass.ID.PRODUCTLISTBODY_DIV_ID));
};

//setPostData
BuyProduct.prototype.setPostData = function(selectBidId, selectBidName){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_BID_ID).val(selectBidId);
	self.getObject(self.ID.ITEM_SELECT_BID_NAME).val(selectBidName);
	
};
//----------------------------------------------------------------------------]
