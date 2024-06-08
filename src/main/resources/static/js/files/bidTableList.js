//-------------------------------------------------------------------------------------------
//-----------------bidTableList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BidTableList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(BidTableList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BidTableList.prototype.ID = {

	BTN_MENU_BC						: 'menuBC',
	
	BID_ADD_INIT_ID					: 'btnAddBidInit',
	
	ITEM_SELECT_BID_ID				: 'selectBidId',
	
	ITEM_SELECT_BID_NAME			: 'selectBidName',
	
	TABLE_LIST						: 'bidListTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
BidTableList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
	
	Pos.constants.bidTableList = self;

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
BidTableList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BID_ADD_INIT_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.bid.bid_add_init_title,
				self.getJsContext().jsView.bid.url_bid_add_init, 
				self.getForm().serializeArray()
            );
		}
	);
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
        	return;
    	}
    );
    

	$buttonList = self.getObject(self.ID.TABLE_LIST).find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('toCtgry')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem));
					ShaAjax.ajax.postToMain(
							self.getJsContext().jsView.ctgry.url_ctgry_manage_list,
							self.getForm().serializeArray());
			    }
			);
		}else if($(elem).hasClass('edit')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem));
					ShaAjax.pop.postDialogMiddleCenter(
						self.getJsContext().jsView.bid.bid_edit_init_title,
						self.getJsContext().jsView.bid.url_bid_edit_init, 
						self.getForm().serializeArray()
		            );
			    }
			);
		}else if($(elem).hasClass('delete')){
			ShaInput.button.onClick($(elem), 
					function(event) {
						self.setPostData($(elem));
						ShaDialog.dialogs.confirm(
		            		self.getJsContext().jsView.bid.bid_delete_init_title,
		            		self.getJsContext().common.msg_dialogs_confirm_delete,
			            	function(){
		            			ShaAjax.ajax.post(
			            			self.getJsContext().jsView.bid.url_bid_delete_regist,
			            			self.getForm().serializeArray(),
			            			function(){self.callBackDelete();}
			            		);
			            	}
			            );
				    }
				);
			}
	});
	
};

//setPostData
BidTableList.prototype.setPostData = function(elem){
	//keep self instance for call back
	var self = this;
	var selectBidId = ShaInput.table.getTrData(elem, 'rowDataKey');
	var selectBidName = ShaInput.table.getTrData(elem, 'bidName');
	self.getObject(self.ID.ITEM_SELECT_BID_ID).val(selectBidId);
	self.getObject(self.ID.ITEM_SELECT_BID_NAME).val(selectBidName);
};

//callBack
BidTableList.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

BidTableList.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			self.getJsContext().jsView.bid.url_bid_manage_list,
	    	{});
	
};


//----------------------------------------------------------------------------]
