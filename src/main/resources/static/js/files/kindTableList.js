//-------------------------------------------------------------------------------------------
//-----------------kindTableList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
KindTableList = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(KindTableList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
KindTableList.prototype.ID = {

	BTN_BID_BC						: 'bidBC',
	
	BTN_CTGRY_BC					: 'ctgryBC',
	
	KIND_ADD_INIT_ID				: 'btnAddKindInit',
	
	
	ITEM_SELECT_BID_ID				: 'selectBidId',

	ITEM_SELECT_BID_NAME			: 'selectBidName',
	
	ITEM_SELECT_CTGRY_ID			: 'selectCtgryId',

	ITEM_SELECT_CTGRY_NAME			: 'selectCtgryName',
	
	ITEM_SELECT_KIND_ID				: 'selectKindId',
	
	TABLE_LIST						: 'kindListTable',

};
//------------------------------------------]

//---------------method define--------------[
//init 
KindTableList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();
	
	Pos.constants.kindTableList = self;

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
KindTableList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BID_BC), 
		function(event) {
			Pos.constants.bidTableList.refresh();
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CTGRY_BC), 
		function(event) {
			Pos.constants.ctgryTableList.refresh();
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.KIND_ADD_INIT_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.kind.kind_add_init_title,
				self.getJsContext().jsView.kind.url_kind_add_init, 
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
					ShaAjax.pop.postDialogMiddleCenter(
						self.getJsContext().jsView.kindManage.title_detail,
		        		self.getJsContext().jsView.kindManage.url_detail, 
		        		self.getForm().serializeArray()
		           	);
			    }
			);
		}else if($(elem).hasClass('edit')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem));
					ShaAjax.pop.postDialogMiddleCenter(
						self.getJsContext().jsView.kind.kind_edit_init_title,
						self.getJsContext().jsView.kind.url_kind_edit_init, 
						self.getForm().serializeArray()
		            );
			    }
			);
		}else if($(elem).hasClass('delete')){
			ShaInput.button.onClick($(elem), 
					function(event) {
						self.setPostData($(elem));
						ShaDialog.dialogs.confirm(
		            		self.getJsContext().jsView.kind.kind_delete_init_title,
		            		self.getJsContext().common.msg_dialogs_confirm_delete,
			            	function(){
		            			ShaAjax.ajax.post(
			            			self.getJsContext().jsView.kind.url_kind_delete_regist,
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
KindTableList.prototype.setPostData = function(elem){
	//keep self instance for call back
	var self = this;
	var selectKindId = ShaInput.table.getTrData(elem, 'rowDataKey');
	self.getObject(self.ID.ITEM_SELECT_KIND_ID).val(selectKindId);
};

//callBack
KindTableList.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	self.refresh();
};

KindTableList.prototype.refresh = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.postToMain(
			self.getJsContext().jsView.kind.url_kind_manage_list,
			self.getForm().serializeArray());
	
};


//----------------------------------------------------------------------------]
