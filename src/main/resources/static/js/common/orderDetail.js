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
    
	BTN_CLOSE                : "btnClose",
	SHOW_ORDER_IMG_BTN_ID    : "showOrderImg",
	ORDER_IMG_ID             : "orderImg"

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
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
};

//----------------------------------------------------------------------------]
