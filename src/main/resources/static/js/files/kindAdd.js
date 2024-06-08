//-------------------------------------------------------------------------------------------
//-----------------kindAdd.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
KindAdd = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.addForm);
};
ShaUtil.other.inherits(KindAdd, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
KindAdd.prototype.ID = {
	BTN_ADD				: "btnAddKind",
	//item
	ITEM_ADD_FLAG	 	: 'addFlag',
	ITEM_NAME 			: 'kind_name',
};
//------------------------------------------]

//---------------method define--------------[
//init 
KindAdd.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
KindAdd.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if(self.getObject(self.ID.ITEM_ADD_FLAG).val() == 'true'){
	            ShaDialog.dialogs.confirm(
	            	self.getJsContext().jsView.kind.kind_add_init_title,
	            	self.getJsContext().common.msg_dialogs_confirm_add,
	            	function(){
	            		ShaAjax.ajax.post(
	            			self.getJsContext().jsView.kind.url_kind_add_regist,
	            			self.getForm().serializeArray(),
	            			function(){self.callBackAdd();}
	            		);
	            	}
	            );
            }
            else{
            	ShaDialog.dialogs.confirm(
            		self.getJsContext().jsView.kind.kind_edit_init_title,
            		self.getJsContext().common.msg_dialogs_confirm_edit,
	            	function(){
            			ShaAjax.ajax.post(
	            			self.getJsContext().jsView.kind.url_kind_delete_regist,
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
KindAdd.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.kind.label_name, self.getObject(self.ID.ITEM_NAME)],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};

//callBackAdd
KindAdd.prototype.callBackAdd = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_add_success;
	self.callBack(msg);
};

// callBackEdit
KindAdd.prototype.callBackEdit = function(){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_edit_success;
	self.callBack(msg);
};

//callBack
KindAdd.prototype.callBack = function(msg){
	//keep self instance for call back
	var self = this;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.kindTableList.refresh();
};

//----------------------------------------------------------------------------]
