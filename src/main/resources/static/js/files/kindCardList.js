//-------------------------------------------------------------------------------------------
//-----------------kindCardList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
KindCardList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(KindCardList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
KindCardList.prototype.ID = {

	BTN_BID_BC						: 'bidBC',
	
	BTN_CTGRY_BC					: 'ctgryBC',
	
	
	ITEM_SELECT_BID_ID				: 'selectBidId',
	
	ITEM_SELECT_CTGRY_ID			: 'selectCtgryId',
	
	ITEM_SELECT_KIND_ID				: 'selectKindId',
	ITEM_SELECT_KIND_NAME			: 'selectKindName',

};
//------------------------------------------]

//---------------method define--------------[
//init 
KindCardList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
	
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);

};

// init event
KindCardList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	ShaInput.button.onClick(self.getObject(self.ID.BTN_BID_BC), 
		function(event) {
			ShaAjax.ajax.postToDiv(
	        	self.getJsContext().jsView.bid.url_bid_card_list,
	        	self.getForm().serializeArray(),
	        	$('#'+self.dataMap.categoryDiv));
			
			// if have setting
			Pos.constants.bidClickAttach.call();
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CTGRY_BC), 
		function(event) {
			
			ShaAjax.ajax.postToDiv(
	        	self.getJsContext().jsView.ctgry.url_ctgry_card_list,
	        	self.getForm().serializeArray(),
	        	$('#'+self.dataMap.categoryDiv));
			
			// if have setting
			Pos.constants.ctgryClickAttach.call();
		}
	);
	
	$buttonList = self.getForm().find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('btn_30h_card')){
			var id = $(elem).attr('id');
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setAllCardBtnenabled();
					ShaInput.obj.disabled($(elem));
					self.setPostData($(elem).attr('id'), $(elem).attr('data-toolip'));
					Pos.constants.kindClickAttach.call();
			    }
			);
		}
	});
	
};

KindCardList.prototype.setAllCardBtnenabled = function(){
	//keep self instance for call back
	var self = this;
	$buttonList = self.getForm().find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('btn_30h_card')){
			ShaInput.obj.enabled($(elem));
		}
	});
	
};

//setPostData
KindCardList.prototype.setPostData = function(selectKindId, selectKindName){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_KIND_ID).val(selectKindId);
	self.getObject(self.ID.ITEM_SELECT_KIND_NAME).val(selectKindName);
	
};
//----------------------------------------------------------------------------]
