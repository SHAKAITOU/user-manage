//-------------------------------------------------------------------------------------------
//-----------------ProductManageListDiv.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ProductManageListDiv = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#'+this.dataMap.mainForm);
};
ShaUtil.other.inherits(ProductManageListDiv, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ProductManageListDiv.prototype.ID = {

	BTN_PRODUCT_FILTER_ID	: "btnProductFilter",
	BTN_PRODUCT_ADD_ID		: "btnAddProductInit",
	BTN_PRODUCT_EDIT_ID		: "btnEditProductInit",
	BTN_PRODUCT_DELETE_ID	: "btnDeleteProduct",
	BTN_PRODUCT_STORE_SETTING_ID	: "btnSettingProduct",
	
	ITEM_PRODUCT_FILTER		: "filterProductName",
	
	TABLE_LIST						: 'productListTable',
	ITEM_SELECT_BID_ID				: 'selectBidId',
	
	ITEM_SELECT_CTGRY_ID			: 'selectCtgryId',
	
	ITEM_SELECT_KIND_ID				: 'selectKindId',
	
	ITEM_SELECT_PRODUCT_ID			: 'selectProductId',

};
//------------------------------------------]

//---------------method define--------------[
//init 
ProductManageListDiv.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	if (self.getObject(self.ID.ITEM_SELECT_KIND_ID) != undefined && 
			self.getObject(self.ID.ITEM_SELECT_KIND_ID).val() > 0) {
		ShaInput.obj.enabled(self.getObject(self.ID.BTN_PRODUCT_ADD_ID));
	} else {
		ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_ADD_ID));
	}
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_EDIT_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_DELETE_ID));
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_PRODUCT_STORE_SETTING_ID));
	
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
ProductManageListDiv.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_PRODUCT_FILTER), 
		function(event) {
			var filterValue = self.getObject(self.ID.ITEM_PRODUCT_FILTER).val();
			ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", filterValue);
		}
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.BTN_PRODUCT_FILTER_ID), 
		function(event) {
			var filterValue = self.getObject(self.ID.ITEM_PRODUCT_FILTER).val();
			ShaInput.table.filter(self.getForm(), self.ID.TABLE_LIST, "filterKey", filterValue);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT_ADD_ID), 
		function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.productManage.product_add_init_title,
				self.getJsContext().jsView.productManage.url_product_add_init, 
				self.getForm().serializeArray()
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT_EDIT_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectProductId', 
					self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val());
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.productManage.product_edit_init_title,
				self.getJsContext().jsView.productManage.url_product_edit_init, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT_DELETE_ID), 
		function(event) {
			ShaDialog.dialogs.confirm(
        		self.getJsContext().jsView.productManage.product_delete_init_title,
        		self.getJsContext().common.msg_dialogs_confirm_delete,
            	function(){
        			var sendData = ShaUtil.json.create();
        			ShaUtil.json.setJsonData(sendData, 'selectProductId', 
        					self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val());
        			ShaAjax.ajax.post(
            			self.getJsContext().jsView.productManage.url_product_delete_regist,
            			sendData,
            			function(){self.callBackDelete();}
            		);
            	}
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PRODUCT_STORE_SETTING_ID), 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectProductId', 
					self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val());
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.productManage.product_store_setting_init_title,
				self.getJsContext().jsView.productManage.url_product_store_setting_init, 
				sendData
            );
		}
	);
	
	
	//add table list event
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
	    	ShaInput.obj.enabled(self.getObject(self.ID.BTN_PRODUCT_EDIT_ID));
	    	ShaInput.obj.enabled(self.getObject(self.ID.BTN_PRODUCT_DELETE_ID));
	    	ShaInput.obj.enabled(self.getObject(self.ID.BTN_PRODUCT_STORE_SETTING_ID));
	    	
	    	self.setPostData(tr.attr("rowDataKey"));
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
ProductManageListDiv.prototype.callBackDelete = function(msg){
	//keep self instance for call back
	var self = this;
	var msg = self.getJsContext().common.msg_dialogs_delete_success;
	ShaDialog.dialogs.dialogClose();
	ShaDialog.dialogs.success(msg);
	Pos.constants.productManage.searchProduct();
};

//setPostData
ProductManageListDiv.prototype.setPostData = function(selectProductId){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_PRODUCT_ID).val(selectProductId);
	
};
//----------------------------------------------------------------------------]
