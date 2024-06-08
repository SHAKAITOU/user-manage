//-------------------------------------------------------------------------------------------
//-----------------storeManage.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
StoreManage = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(StoreManage, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
StoreManage.prototype.ID = {
		
	BTN_STORE_ADD_INIT_ID				: 'btnAddStoreInit',
	BTN_STORE_EDIT_INIT_ID				: 'btnEditStoreInit',
	BTN_STORE_DELETE_ID					: 'btnDeleteStore',
	
	ITEM_SELECT_STORE_ID			: 'selectStoreId',

	//tab
	TABLE_LIST						: 'storeListTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
StoreManage.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.storeManage = self;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_STORE_EDIT_INIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_STORE_DELETE_ID));
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
StoreManage.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_STORE_ADD_INIT_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.store.store_add_init_title,
				self.getJsContext().jsView.store.url_store_add_init, 
				self.getForm().serializeArray()
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_STORE_EDIT_INIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectStoreId', 
					self.getObject(self.ID.ITEM_SELECT_STORE_ID).val());
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.store.store_edit_init_title,
				self.getJsContext().jsView.store.url_store_edit_init, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_STORE_DELETE_ID), 
		function(event) {
			ShaDialog.dialogs.confirm(
        		self.getJsContext().jsView.store.store_delete_init_title,
        		self.getJsContext().common.msg_dialogs_confirm_delete,
            	function(){
        			var sendData = ShaUtil.json.create();
        			ShaUtil.json.setJsonData(sendData, 'selectStoreId', 
        					self.getObject(self.ID.ITEM_SELECT_STORE_ID).val());
        			ShaAjax.ajax.post(
            			self.getJsContext().jsView.store.url_store_delete_regist,
            			sendData,
            			function(){self.callBackDelete();}
            		);
            	}
            );
		}
	);
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.getObject(self.ID.ITEM_SELECT_STORE_ID).val(tr.attr("rowDataKey"));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_STORE_EDIT_INIT_ID));
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_STORE_DELETE_ID));
        	return;
    	}
    );
    
    $imgList = self.getObject(self.ID.TABLE_LIST).find('img');
    $imgList.each(function(i, elem){
		ShaInput.button.onClick($(elem), 
			function(event) {
				Pos.constants.showImgOrgObj = $(elem);
				ShaAjax.pop.postSubDialogMiddleCenter(
						self.getJsContext().jsView.commonUtil.showImg_title,
						self.getJsContext().jsView.commonUtil.url_showImg, 
						{}
	            );
		    }
		);
	});
    
};

//callBack
StoreManage.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

StoreManage.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			Pos.constants.setInfo.jsView.store.url_store_manage_init,
        	{});
	
};
//----------------------------------------------------------------------------]
