//-------------------------------------------------------------------------------------------
//-----------------messageList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
MessageList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#messageListForm');
};
ShaUtil.other.inherits(MessageList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
MessageList.prototype.ID = {

		//tab
	TABLE_LIST						: 'messageListTable',
	ITEM_PAGELINKIDPREFIX 			: 'message_pageLinkIdPrefix',

};
//------------------------------------------]

//---------------method define--------------[
//init 
MessageList.prototype.init = function(){
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
MessageList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
	    	var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectMsgId', tr.attr("rowDataKey"));
    		ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.message.message_show_title,
				self.getJsContext().jsView.message.url_message_show, 
				sendData
            );
        	return;
    	}
    );

};

//initPageLink
MessageList.prototype.initPageLink = function(){
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
MessageList.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			Pos.constants.setInfo.jsView.message.url_message_list_search,
			self.getForm().serializeArray());
};

//----------------------------------------------------------------------------]
