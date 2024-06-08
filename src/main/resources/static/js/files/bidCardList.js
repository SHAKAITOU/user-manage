//-------------------------------------------------------------------------------------------
//-----------------bidCardList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BidCardList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(BidCardList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BidCardList.prototype.ID = {

	BTN_MENU_BC						: 'menuBC',
	
	ITEM_SELECT_BID_ID				: 'selectBidId',
	
	ITEM_SELECT_BID_NAME			: 'selectBidName',

};
//------------------------------------------]

//---------------method define--------------[
//init 
BidCardList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	if (self.dataMap.categoryFromManage) {
		Pos.constants.bidClickAttach.ctrlClass = self;
		Pos.constants.bidClickAttach.callBackFunc = null;
		Pos.constants.ctgryClickAttach.ctrlClass = self;
		Pos.constants.ctgryClickAttach.callBackFunc = null;
		Pos.constants.kindClickAttach.ctrlClass = self;
		Pos.constants.kindClickAttach.callBackFunc = null;
	}
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
BidCardList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	$buttonList = self.getForm().find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('btn_30h_card')){
			var id = $(elem).attr('id');
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem).attr('id'), $(elem).attr('data-toolip'));
					ShaAjax.ajax.postToDiv(
							Pos.constants.setInfo.jsView.ctgry.url_ctgry_card_list,
							self.getForm().serializeArray(),
							$('#'+self.dataMap.categoryDiv));
					
					// if have setting
					Pos.constants.bidClickAttach.call();
			    }
			);
		}
	});
	
};

//setPostData
BidCardList.prototype.setPostData = function(selectBidId, selectBidName){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_BID_ID).val(selectBidId);
	self.getObject(self.ID.ITEM_SELECT_BID_NAME).val(selectBidName);
	
};
//----------------------------------------------------------------------------]
