//-------------------------------------------------------------------------------------------
//-----------------storeAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
StoreAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.formId = '#'+this.dataMap.addForm;
	this.form = $(this.formId);
};
ShaUtil.other.inherits(StoreAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
StoreAdd.prototype.ID = {
	BTN_ADD				: "btnAddStore",
	BTN_SEARCH_POST_CODE			: 'btnSearchPostCode',
	BTN_SHOW_IMG					: 'btnShowImg',
	BTN_MAP							: 'btnShowGoogleMap',
	//item
	ITEM_ADD_FLAG	 	: 'addFlag',
	ITEM_POST_CODE   	: 'storeResult_storeExtend_postCode',
	ITEM_NAME 			: 'storeResult_store_name',
	ITEM_ADDRESS1		: 'storeResult_storeExtend_address1',
	ITEM_FILE_IMG		: 'fileName',
	ITEM_PREVIEW_IMG	: 'filePreview',
};
//------------------------------------------]

//---------------method define--------------[
//init 
StoreAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
StoreAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_SEARCH_POST_CODE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SEARCH_POST_CODE), 
		function(event) {
			if(self.checkPostCode()) {
                return;
            }
			
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'postCode', self.getObject(self.ID.ITEM_POST_CODE).val());
			ShaAjax.ajax.post(
    			self.getJsContext().jsView.commonUtil.url_search_addr,
    			sendData,
    			function(data){self.callBackSearchAddr(data);}
    		);

        }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_FILE_IMG), 
		function(event) {
			ShaInput.img.preview(self.getForm(), self.ID.ITEM_FILE_IMG, self.ID.ITEM_PREVIEW_IMG);
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SHOW_IMG), 
		function(event) {
			Pos.constants.showImgOrgObj = self.getObject(self.ID.ITEM_PREVIEW_IMG);
			ShaAjax.pop.postSubDialogMiddleCenter(
					self.getJsContext().jsView.commonUtil.showImg_title,
					self.getJsContext().jsView.commonUtil.url_showImg, 
					{}
	            );
		
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_MAP), 
		function(event) {
			window.open("https://www.google.co.jp/maps");
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if(self.getObject(self.ID.ITEM_ADD_FLAG).val() == 'true'){
	            ShaDialog.dialogs.confirm(
	            	self.getJsContext().jsView.store.store_add_init_title,
	            	self.getJsContext().common.msg_dialogs_confirm_add,
	            	function(){
	            		ShaAjax.ajax.postWithUploadFile(
	            			self.getJsContext().jsView.store.url_store_add_regist,
	            			self.formId,
	            			function(){self.callBackAdd();}
	            		);
	            	}
	            );
            }
            else{
            	ShaDialog.dialogs.confirm(
            		self.getJsContext().jsView.store.store_edit_init_title,
            		self.getJsContext().common.msg_dialogs_confirm_edit,
	            	function(){
            			ShaAjax.ajax.postWithUploadFile(
	            			self.getJsContext().jsView.store.url_store_edit_regist,
	            			self.formId,
	            			function(){self.callBackEdit();}
	            		);
	            	}
	            );
            }
		}
	);
	
	
};

//check
StoreAdd.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.store.label_name, self.getObject(self.ID.ITEM_NAME)],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};

//callBackAdd
StoreAdd.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	self.callBack(msg);
};

// callBackEdit
StoreAdd.prototype.callBackEdit = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	self.callBack(msg);
};

//callBack
StoreAdd.prototype.callBack = function(msg){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.storeManage.refresh();
};

StoreAdd.prototype.checkPostCode = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.store.label_postCode, self.getObject(self.ID.ITEM_POST_CODE)],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    return false;
};

StoreAdd.prototype.callBackSearchAddr = function(data){
	//keep self instance for call back
	var self = this;
	if(data.yubinCode == ''){
		ShaDialog.dialogs.alert(self.getJsContext().jsView.store.msg_noPostCode);
	}else{
		var addr = data.todoufukenNm + data.cityNm + data.streetNm;
		self.getObject(self.ID.ITEM_ADDRESS1).val(addr);
	}
};

//----------------------------------------------------------------------------]
