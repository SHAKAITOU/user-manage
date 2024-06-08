//-------------------------------------------------------------------------------------------
//-----------------ctgryCardList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
CtgryCardList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(CtgryCardList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
CtgryCardList.prototype.ID = {
	
	BTN_BID_BC						: 'bidBC',
	
	
	ITEM_SELECT_BID_ID				: 'selectBidId',

	ITEM_SELECT_BID_NAME			: 'selectBidName',
	
	ITEM_SELECT_CTGRY_ID			: 'selectCtgryId',

	ITEM_SELECT_CTGRY_NAME			: 'selectCtgryName',

};
//------------------------------------------]

//---------------method define--------------[
//init 
CtgryCardList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//ShaDialog.tooltip.boundleTooltips(self.getForm());	
	
	//init bond event to btn
	self.initEvent();
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
CtgryCardList.prototype.initEvent = function(){
	
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
	
	$buttonList = self.getForm().find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('btn_30h_card')){
			var id = $(elem).attr('id');
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem).attr('id'), $(elem).attr('data-toolip'));
					ShaAjax.ajax.postToDiv(
							Pos.constants.setInfo.jsView.kind.url_kind_card_list,
							self.getForm().serializeArray(),
							$('#'+self.dataMap.categoryDiv));
					
					// if have setting
					Pos.constants.ctgryClickAttach.call();
			    }
			);
		}
	});
	
};

//setPostData
CtgryCardList.prototype.setPostData = function(selectCtgryId, selectCtgryName){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_CTGRY_ID).val(selectCtgryId);
	self.getObject(self.ID.ITEM_SELECT_CTGRY_NAME).val(selectCtgryName);
};
//----------------------------------------------------------------------------]
