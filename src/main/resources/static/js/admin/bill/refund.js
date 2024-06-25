//-------------------------------------------------------------------------------------------
//-----------------Refund.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
Refund = function(dataMap){
	this.fatherForm = $('#AdminRefundListForm');
    this.form = $('#RefundForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(Refund, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
Refund.prototype.ID = {
	
	SEARCH_BTN_ID                : "searchBtn",
	
	BTN_CLOSE : "btnClose",
    BTN_ADD   : "btnAdd",
    
    PREFIX_NAME                   : "bill_",
	ITEM_BILL_AMOUNT             : "billAmount",
	ITEM_BILL_PHOTO              : "billPhotoFile",
	BTN_BILL_PHOTO_OPEN          : "billPhotoFileOpen",
	ITEM_BILL_PHOTO_LBL          : "billPhotoFileLbl",
	ITEM_BILL_PHOTO_NAME         : "billPhotoFileName",

};
//------------------------------------------]

//---------------method define--------------[
//init 
Refund.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
Refund.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_BILL_PHOTO_NAME));
	
	//init event to BTN_EDUCATIONAL_AT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BILL_PHOTO_OPEN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_BILL_PHOTO).val() === "") {
				return;
			}
			
    		ShaInput.img.previewInDialog(
				self.getForm(), 
    			self.ID.ITEM_BILL_PHOTO,
    			function(imgData) {
					var newImg = new Image();

				    newImg.onload = function() {
				    	var imgH = newImg.height;
				    	var imgW = newImg.width;
				    	var title = self.i18n["m_image.bill_photo"];
						var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
							"ShaDialog.dialogs.subSubDialogClose();", imgData);
						ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
				    }
					newImg.src = imgData;
				}
    		);
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_BILL_PHOTO), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_BILL_PHOTO).prop('files');
    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_BILL_PHOTO).val("");
	        } else if(files[0].size > (1024*1024*5)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_BILL_PHOTO).val("");
			} else {
				self.getObject(self.ID.ITEM_BILL_PHOTO_NAME).val(files[0].name);
			}
	    }
	);
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        ShaDialog.dialogs.confirm(
				self.i18n["dialogs.confirm.add.title"], 
				self.i18n["dialogs.confirm.add.msg"], 
				function () {
	        		self.getObject(self.ID.BTN_CLOSE).click();
					ShaAjax.ajax.postWithUploadFile(
						self.jsContext.adminJsView.adminRefund.url_refund_add, 
						"RefundForm", 
						function () {
							ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
							self.getObjectInForm(self.fatherForm, self.ID.SEARCH_BTN_ID).click();
						}
					);
				}
			);
	    }
	);
	
	
    //init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);

};

//checkValue
Refund.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["m_image.bill_photo"], 	self.getObject(self.ID.ITEM_BILL_PHOTO_NAME)], 
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};


//----------------------------------------------------------------------------]
