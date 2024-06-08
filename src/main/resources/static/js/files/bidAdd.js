//-------------------------------------------------------------------------------------------
//-----------------bidAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BidAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.addForm);
};
ShaUtil.other.inherits(BidAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BidAdd.prototype.ID = {
	BTN_ADD				: "btnAddBid",
	//item
	ITEM_ADD_FLAG	 	: 'addFlag',
	ITEM_NAME 			: 'bid_name',
};
//------------------------------------------]

//---------------method define--------------[
//init 
BidAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
BidAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if(self.getObject(self.ID.ITEM_ADD_FLAG).val() == 'true'){
	            ShaDialog.dialogs.confirm(
	            	self.getJsContext().jsView.bid.bid_add_init_title,
	            	self.getJsContext().common.msg_dialogs_confirm_add,
	            	function(){
	            		ShaAjax.ajax.post(
	            			self.getJsContext().jsView.bid.url_bid_add_regist,
	            			self.getForm().serializeArray(),
	            			function(){self.callBackAdd();}
	            		);
	            	}
	            );
            }
            else{
            	ShaDialog.dialogs.confirm(
            		self.getJsContext().jsView.bid.bid_edit_init_title,
            		self.getJsContext().common.msg_dialogs_confirm_edit,
	            	function(){
            			ShaAjax.ajax.post(
	            			self.getJsContext().jsView.bid.url_bid_edit_regist,
	            			self.getForm().serializeArray(),
	            			function(){self.callBackEdit();}
	            		);
	            	}
	            );
            }
		}
	);
	
	
};

//check
BidAdd.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.bid.label_name, self.getObject(self.ID.ITEM_NAME)],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};

//callBackAdd
BidAdd.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	self.callBack(msg);
};

// callBackEdit
BidAdd.prototype.callBackEdit = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	self.callBack(msg);
};

//callBack
BidAdd.prototype.callBack = function(msg){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.bidTableList.refresh();
};

//----------------------------------------------------------------------------]
