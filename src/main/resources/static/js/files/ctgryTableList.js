//-------------------------------------------------------------------------------------------
//-----------------ctgryTableList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
CtgryTableList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(CtgryTableList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
CtgryTableList.prototype.ID = {

	BTN_BID_BC						: 'bidBC',
	
	CTGRY_ADD_INIT_ID				: 'btnAddCtgryInit',
	
	
	ITEM_SELECT_BID_ID				: 'selectBidId',

	ITEM_SELECT_BID_NAME			: 'selectBidName',
	
	ITEM_SELECT_CTGRY_ID			: 'selectCtgryId',

	ITEM_SELECT_CTGRY_NAME			: 'selectCtgryName',
	
	TABLE_LIST						: 'ctgryListTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
CtgryTableList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
	
	Pos.constants.ctgryTableList = self;

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
CtgryTableList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BID_BC), 
		function(event) {
			Pos.constants.bidTableList.refresh();
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.CTGRY_ADD_INIT_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.ctgry.ctgry_add_init_title,
				self.getJsContext().jsView.ctgry.url_ctgry_add_init, 
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
		if($(elem).hasClass('toKind')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem));
					ShaAjax.ajax.postToMain(
							self.getJsContext().jsView.kind.url_kind_manage_list,
							self.getForm().serializeArray());
			    }
			);
		}else if($(elem).hasClass('edit')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem));
					ShaAjax.pop.postDialogMiddleCenter(
						self.getJsContext().jsView.ctgry.ctgry_edit_init_title,
						self.getJsContext().jsView.ctgry.url_ctgry_edit_init, 
						self.getForm().serializeArray()
		            );
			    }
			);
		}else if($(elem).hasClass('delete')){
			ShaInput.button.onClick($(elem), 
					function(event) {
						self.setPostData($(elem));
						ShaDialog.dialogs.confirm(
		            		self.getJsContext().jsView.ctgry.ctgry_delete_init_title,
		            		self.getJsContext().common.msg_dialogs_confirm_delete,
			            	function(){
		            			ShaAjax.ajax.post(
			            			self.getJsContext().jsView.ctgry.url_ctgry_delete_regist,
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
CtgryTableList.prototype.setPostData = function(elem){
	//keep self instance for call back
	var self = this;
	var selectCtgryId = ShaInput.table.getTrData(elem, 'rowDataKey');
	var selectCtgryName = ShaInput.table.getTrData(elem, 'ctgryName');
	self.getObject(self.ID.ITEM_SELECT_CTGRY_ID).val(selectCtgryId);
	self.getObject(self.ID.ITEM_SELECT_CTGRY_NAME).val(selectCtgryName);
};

//callBack
CtgryTableList.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

CtgryTableList.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			self.getJsContext().jsView.ctgry.url_ctgry_manage_list,
			self.getForm().serializeArray());
	
};


//----------------------------------------------------------------------------]
