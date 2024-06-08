//-------------------------------------------------------------------------------------------
//-----------------buyPriceHistoryList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BuyPriceHistoryList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#buyPriceHistoryListForm');
};
ShaUtil.other.inherits(BuyPriceHistoryList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BuyPriceHistoryList.prototype.ID = {
		
	BTN_STORE_ANALYSIS				: 'btnStoreAnalysis',
	BTN_PRODUCT_ANALYSIS			: 'btnProductAnalysis',
	BTN_PRICE_ANALYSIS				: 'btnPriceAnalysis',

		//tab
	TABLE_LIST						: 'buyPriceHistoryListTable',
	ITEM_PAGELINKIDPREFIX 			: 'buyPriceHistory_pageLinkIdPrefix',

};
//------------------------------------------]

//---------------method define--------------[
//init 
BuyPriceHistoryList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
	
	self.initPageLink();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
BuyPriceHistoryList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_STORE_ANALYSIS), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.analysis.store_analysis_title,
				self.getJsContext().jsView.analysis.url_store_analysis_init, 
				{}
	        );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT_ANALYSIS), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.analysis.buyProduct_analysis_title,
				self.getJsContext().jsView.analysis.url_buyProduct_analysis_init, 
				{}
	        );
		}
	);	

	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
	    	var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectHeadId', tr.attr("rowDataKey"));
    		ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.buyPriceHistory.buy_price_history_show_title,
				self.getJsContext().jsView.buyPriceHistory.url_buy_price_history_show, 
				sendData
            );
        	return;
    	}
    );

};

//initPageLink
BuyPriceHistoryList.prototype.initPageLink = function(){
	//keep self instance for call back
	var self = this;
	
	ShaPage.pageLink.initPageLink(
		self.getObject(self.ID.ITEM_PAGELINKIDPREFIX).val(),
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    ); 
};

//doCompanyPageLink
BuyPriceHistoryList.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			Pos.constants.setInfo.jsView.message.url_message_list_search,
			self.getForm().serializeArray());
};

//----------------------------------------------------------------------------]
