//-------------------------------------------------------------------------------------------
//-----------------ReceiptImgUpload.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ReceiptImgUpload = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.formId = '#receipt_img_upload_form';
	this.form = $(this.formId);
	
};
ShaUtil.other.inherits(ReceiptImgUpload, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ReceiptImgUpload.prototype.ID = {
		
	BTN_ADD_ITEM		: 'btnAddUploadItem',
	BTN_MINUS_ITEM		: 'btnMinusUploadItem',
	BTN_SHOW_IMG		: 'btnShowImg',
	BTN_UPLOAD_IMG		: 'btnUploadImg',
	
	ITEM_SELECT_STORE	: 'storeId',
	
	ITEM_SELECT_STORE_NAME	: 'storeName',
	
	ITEM_SELECT_CATEGORY	: 'categoryId',
	
	ITEM_SELECT_CATEGORY_NAME	: 'categoryName',
	
	ITEM_SHOW_FLAG		: 'showFlag',
	
	ITEM_IMG_DIV		: 'imgDiv',
	
	ITEM_FILE_IMG		: 'fileName',
	
	ITEM_PREVIEW_IMG	: 'filePreview',
	
	SHOW_NEXT_INDEX 	: 2,
	
	FILE_CHANGE_FLAG	: 'fileChangeFlag',
};
//------------------------------------------]

//---------------method define--------------[
//init 
ReceiptImgUpload.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.disabled(self.getObject(self.ID.BTN_MINUS_ITEM));
	
	self.getObject(self.ID.ITEM_SELECT_CATEGORY_NAME).val(
			self.getObject(self.ID.ITEM_SELECT_CATEGORY).find(":selected").attr('data'));
	
	self.getObject(self.ID.ITEM_SELECT_STORE_NAME).val(
			self.getObject(self.ID.ITEM_SELECT_STORE).find(":selected").text());

	//init bond event to btn
	self.initEvent();

	//init click to view
};

// init event
ReceiptImgUpload.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_SELECT_STORE), 
		function(event) {
			self.getObject(self.ID.ITEM_SELECT_STORE_NAME).val(
					self.getObject(self.ID.ITEM_SELECT_STORE).find(":selected").text());
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_SELECT_CATEGORY), 
		function(event) {
			self.getObject(self.ID.ITEM_SELECT_CATEGORY_NAME).val(
					self.getObject(self.ID.ITEM_SELECT_CATEGORY).find(":selected").attr('data'));
	    }
	);
	
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD_ITEM), 
		function(event) {
			self.getObject(self.ID.ITEM_IMG_DIV + self.ID.SHOW_NEXT_INDEX).show();
			self.getObject(self.ID.ITEM_SHOW_FLAG + self.ID.SHOW_NEXT_INDEX).val("true");
			self.ID.SHOW_NEXT_INDEX++;
			if (self.ID.SHOW_NEXT_INDEX == 6) {
				ShaInput.obj.disabled(self.getObject(self.ID.BTN_ADD_ITEM));
			}
			ShaInput.obj.enabled(self.getObject(self.ID.BTN_MINUS_ITEM));
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_MINUS_ITEM), 
		function(event) {
			self.ID.SHOW_NEXT_INDEX--;
			self.getObject(self.ID.ITEM_IMG_DIV + (self.ID.SHOW_NEXT_INDEX)).hide();
			self.getObject(self.ID.ITEM_SHOW_FLAG + self.ID.SHOW_NEXT_INDEX).val("false");
			if (self.ID.SHOW_NEXT_INDEX == 2) {
				ShaInput.obj.disabled(self.getObject(self.ID.BTN_MINUS_ITEM));
			}
			ShaInput.obj.enabled(self.getObject(self.ID.BTN_ADD_ITEM));
		}
	);
	
	self.getForm().find('[id^='+self.ID.ITEM_IMG_DIV+']').each(function(i, elem){
		if (i > 0) {
			$(elem).hide();
		}
		
		ShaInput.button.onChange(self.getObject(self.ID.ITEM_FILE_IMG+(i+1)), 
			function(event) {
				self.getObject(self.ID.FILE_CHANGE_FLAG).val("true"); //変更有
				ShaInput.img.preview(self.getForm(), self.ID.ITEM_FILE_IMG+(i+1), self.ID.ITEM_PREVIEW_IMG+(i+1));
		    }
		);
		
		ShaInput.button.onClick(self.getObject(self.ID.BTN_SHOW_IMG+(i+1)), 
			function(event) {
				Pos.constants.showImgOrgObj = self.getObject(self.ID.ITEM_PREVIEW_IMG+(i+1));
				ShaAjax.pop.postSubDialogMiddleCenter(
						self.getJsContext().jsView.commonUtil.showImg_title,
						self.getJsContext().jsView.commonUtil.url_showImg, 
						{}
		            );
		    }
		);
	});
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_UPLOAD_IMG), 
		function(event) {
			if(self.check()) {
	            return;
	        }
		
			ShaAjax.pop.postSubDialogMiddleCenterWithUploadFile(
				self.getJsContext().jsView.receipt.receipt_img_analysis_list_title,
				self.getJsContext().jsView.receipt.url_receipt_img_analysis_list, 
				self.formId
            );
			self.getObject(self.ID.FILE_CHANGE_FLAG).val("false"); //変更無しにする
	    }
	);
	
};

//check
ReceiptImgUpload.prototype.check = function(){
	//alert(JSON.stringify(postData));
	//keep self instance for call back
	var self = this;
	
    var inputCheckItemList = [
    	[ self.getJsContext().jsView.receipt.label_receipt_img_name, self.getObject(self.ID.ITEM_FILE_IMG+"1")],
    ];
    
    if(ShaCheck.check.checkNotBlank(inputCheckItemList)){
    	return true;
    }
    
    return false;
};
//----------------------------------------------------------------------------]
