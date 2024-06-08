//-------------------------------------------------------------------------------------------
//-----------------buyPriceHistoryShow.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BuyPriceHistoryShow = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $("#buyPriceHistoryShowForm");
	
};
ShaUtil.other.inherits(BuyPriceHistoryShow, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BuyPriceHistoryShow.prototype.ID = {
	BTN_PRINT		: 'btnPrint',
};
//------------------------------------------]

//---------------method define--------------[
//init 
BuyPriceHistoryShow.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
};

// init event
BuyPriceHistoryShow.prototype.initEvent = function(){
	
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRINT), 
		function(event) {
			ShaRestful.restful.postPdf(
				self.getJsContext().jsView.buyPriceHistory.url_buy_price_history_print, 
				self.getForm()
            );
	    }
	);
};
//----------------------------------------------------------------------------]
