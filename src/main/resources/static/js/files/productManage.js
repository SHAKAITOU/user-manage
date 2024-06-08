//-------------------------------------------------------------------------------------------
//-----------------productManage.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ProductManage = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(ProductManage, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ProductManage.prototype.ID = {

	BTN_MENU_BC						: 'menuBC',
	BTN_PRODUCT_ADD_ID		: "btnAddProductInit",
	BTN_PRODUCT_EDIT_ID		: "btnEditProductInit",
	BTN_PRODUCT_DELETE_ID	: "btnDeleteProduct",
	BTN_PRODUCT_STORE_SETTING_ID	: "btnSettingProduct",
	
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
ProductManage.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_ADD_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_EDIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_DELETE_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_STORE_SETTING_ID));
	
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
	Pos.constants.productManage = self;
	
	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
ProductManage.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	// bid init
	ShaAjax.ajax.postToDiv(
        	self.getJsContext().jsView.bid.url_bid_card_list,
        	self.getForm().serializeArray(),
			$('#'+self.dataMap.categoryDiv));
};

ProductManage.prototype.clearProduct = function(ctrlClass){
	//keep self instance for call back
	var self = this;
	if(ctrlClass == null) {
		ctrlClass = self;
	}
	ShaAjax.ajax.postToDiv(
			Pos.constants.setInfo.jsView.productManage.url_product_manage_list_clear,
			ctrlClass.getForm().serializeArray(),
			ctrlClass.getObject(ctrlClass.ID.PRODUCTLISTBODY_DIV_ID));
};

ProductManage.prototype.searchProduct = function(ctrlClass){
	//keep self instance for call back
	var self = this;
	if(ctrlClass == null) {
		ctrlClass = self;
	}
	ShaAjax.ajax.postToDiv(
			Pos.constants.setInfo.jsView.productManage.url_product_manage_search,
			ctrlClass.getForm().serializeArray(),
			ctrlClass.getObject(ctrlClass.ID.PRODUCTLISTBODY_DIV_ID));
};

//setPostData
ProductManage.prototype.setPostData = function(selectBidId, selectBidName){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_BID_ID).val(selectBidId);
	self.getObject(self.ID.ITEM_SELECT_BID_NAME).val(selectBidName);
	
};
//----------------------------------------------------------------------------]
