//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
OrderDetail = function(dataMap){
    this.form = $('#OrderDetailForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(OrderDetail, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
OrderDetail.prototype.ID = {
	
	TAB_ID                   : "detailTab",
	TAB_TITLE_ORDER_ID       : "orderTab",
	TAB_TITLE_BILL_ID        : "billTab",
	TAB_TITLE_BILL_DETAIL_ID : "billDetailTab",
	TAB_BODY_ORDER_ID        : "orderTabBody",
    TAB_BODY_BILL_ID         : "billTabBody",
    TAB_BODY_BILL_DETAIL_ID  : "billDetailTabBody",
    
	BTN_CLOSE                : "btnClose",
	SHOW_ORDER_IMG_BTN_ID    : "showOrderImg",
	ORDER_IMG_ID             : "orderImg",
	SHOW_BILL_IMG_BTN_ID     : "showBillImg",
    BILL_IMG_ID              : "billImg",

};
//------------------------------------------]

//---------------method define--------------[
//init 
OrderDetail.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
OrderDetail.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
    
    
    ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_ORDER_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_ORDER_ID, self.ID.TAB_BODY_ORDER_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_BILL_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_BILL_ID, self.ID.TAB_BODY_BILL_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_BILL_DETAIL_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_BILL_DETAIL_ID, self.ID.TAB_BODY_BILL_DETAIL_ID);
		}
	);
	
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_ORDER_IMG_BTN_ID), 
		function(event) {
			var newImg = new Image();

		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_image.order_photo"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.ORDER_IMG_ID).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
		    newImg.src = self.getObject(self.ID.ORDER_IMG_ID).attr("src");
			
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.SHOW_BILL_IMG_BTN_ID), 
		function(event) {
			var newImg = new Image();

		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_image.bill_photo"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.BILL_IMG_ID).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
		    newImg.src = self.getObject(self.ID.BILL_IMG_ID).attr("src");
			
		}
	);
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
};

//----------------------------------------------------------------------------]
