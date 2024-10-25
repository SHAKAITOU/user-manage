//-------------------------------------------------------------------------------------------
//-----------------Order.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
Order = function(dataMap){
	this.fatherForm = $('#userOrderListForm');
    this.form = $('#OrderForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(Order, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
Order.prototype.ID = {
	
	BTN_PAY_QRCODE : "btnPayQrCode",
	BTN_PAY_BANK : "btnPayBank",
	BTN_CLOSE : "btnClose",
    BTN_ADD   : "btnAdd",
	SEARCH_BTN_ID :"searchBtn",
    
    PREFIX_NAME                   : "order_",
    ITEM_ORDER_METHOD             : "orderMethod",
    ITEM_ORDER_TYPE               : "orderType",
	ITEM_PAY_TYPE                 : "payType",
	ITEM_ORDER_AMOUNT             : "orderAmount",
	ITEM_INVOICE_TYPE             : "invoiceType",
	ITEM_INVOICE_TITLE            : "invoiceTitle",
	ITEM_CREDIT_CODE              : "creditCode",
	ITEM_MAIL                     : "mail",
	
    PREFIX_IMAGE_NAME             : "image_",
	ITEM_ORDER_PHOTO              : "orderPhotoFile",
	BTN_ORDER_PHOTO_OPEN          : "orderPhotoFileOpen",
	ITEM_ORDER_PHOTO_LBL          : "orderPhotoFileLbl",
	ITEM_ORDER_PHOTO_NAME         : "orderPhotoFileName",

};
//------------------------------------------]

//---------------method define--------------[
//init 
Order.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
Order.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	var selectObj = self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_ORDER_METHOD);
	ShaInput.obj.readonly(selectObj);
	selectObj.find('option:not(:selected)').attr('disabled', true);
	
	//selectObj = self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_ORDER_TYPE);
	//ShaInput.obj.readonly(selectObj);
	//selectObj.find('option:not(:selected)').attr('disabled', true);
	
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_ORDER_PHOTO_NAME));
	
	self.getObject(self.ID.BTN_PAY_BANK).hide();
	
	//init event to BTN_EDUCATIONAL_AT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ORDER_PHOTO_OPEN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_ORDER_PHOTO).val() === "") {
				return;
			}
    		ShaInput.img.previewInDialog(
				self.getForm(), 
    			self.ID.ITEM_ORDER_PHOTO,
    			function(imgData) {
					var title = self.i18n["m_image.order_photo"];
					var html = ShaInput.img.previewImgCardHtml(260, 250, "ShaDialog.dialogs.subSubDialogClose();", imgData);
					ShaDialog.dialogs.subSubDialogSmallCenter(title,html);
				}
    		);
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_ORDER_PHOTO), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_ORDER_PHOTO).prop('files');
    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_ORDER_PHOTO).val("");
	        } else if(files[0].size > (1024*1024*5)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_ORDER_PHOTO).val("");
			} else {
				self.getObject(self.ID.ITEM_ORDER_PHOTO_NAME).val(files[0].name);
			}
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TYPE), 
		function(event) {
			if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TYPE).val() === '01') {//个人
				 self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE).closest('.row').hide();
			   } else {
				 self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE).closest('.row').show();
			   }
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_PAY_TYPE), 
		function(event) {
			if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_PAY_TYPE).val() === '01') {//在线支付
					self.getObject(self.ID.BTN_PAY_QRCODE).show();
					self.getObject(self.ID.BTN_PAY_BANK).hide();
			   } else {
					self.getObject(self.ID.BTN_PAY_QRCODE).hide();
					self.getObject(self.ID.BTN_PAY_BANK).show();
			   }
	    }
	);
	
	//init event to BTN_PAY_QRCODE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PAY_QRCODE), 
		function(event) {
			ShaAjax.pop.postSubDialogMiddleCenter(
				self.i18n["order.payInfo.title"],
				self.jsContext.jsView.orderPayInfo.url_init, 
				[{name:"payType", value:self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_PAY_TYPE).val()}]);
	    }
	);
	
	//init event to BTN_PAY_BANK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PAY_BANK), 
		function(event) {
			self.getObject(self.ID.BTN_PAY_QRCODE).click();
	    }
	);
	
	//init event to BTN_ADD
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
						self.jsContext.jsView.order.url_order_add, 
						"OrderForm", 
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
Order.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["m_order.order_amount"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_ORDER_AMOUNT)], 
        [ self.i18n["m_image.order_photo"], 	self.getObject(self.ID.ITEM_ORDER_PHOTO_NAME)], 
		//[ self.i18n["m_order.invoice_title"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TITLE)], 
		//[ self.i18n["m_order.mail"], 	        self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_MAIL)], 
    ];
	/*if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TYPE).val() !== '01') {//个人
		inputCheckItemList.push([ self.i18n["m_order.credit_code"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE)]);
	}*/
    
    if (ShaCheck.check.checkNotBlank(inputCheckItemList)) {
		return true;
	}
		
	inputCheckItemList = [
        [ self.i18n["m_order.order_amount"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_ORDER_AMOUNT)]
    ];
    
	if (ShaCheck.check.checkNotNumber(inputCheckItemList)) {
		return true;
	}
	
	/*inputCheckItemList = [
        [ self.i18n["m_order.order_amount"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_ORDER_AMOUNT), ShaConstants.constants.MIN_BILL_AMOUNT, ShaConstants.constants.MAX_BILL_AMOUNT]
    ];
	if (ShaCheck.check.checkNumberRange(inputCheckItemList)) {
		return true;
	}*/
	
	if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TYPE).val() == '02'){
		if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TITLE).val() != '' && self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE).val() == ''){
			if (ShaCheck.check.checkNotBlank([[self.i18n["m_order.credit_code"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE)]])) {
				return true;
			}
		}
		
		if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TITLE).val() == '' && self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE).val() != ''){
			if (ShaCheck.check.checkNotBlank([[self.i18n["m_order.invoice_title"], 	self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_INVOICE_TITLE)]])) {
				return true;
			}
		}
			
		if(self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE).val() != '' &&
				ShaCheck.check.checkCreditCode([[ self.i18n["m_order.credit_code"], self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_CREDIT_CODE)]])){
			return true;
		}
	}
	
	if (self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_MAIL).val() != '' && 
			ShaCheck.check.checkEmail([[ self.i18n["m_order.mail"], self.getObject(self.ID.PREFIX_NAME + self.ID.ITEM_MAIL)]])){
		return true;
	}
		
	return false;
};


//----------------------------------------------------------------------------]
