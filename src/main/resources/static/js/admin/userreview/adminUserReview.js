//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserReview = function(dataMap){
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.form = $('#ReviewOrderForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminUserReview, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserReview.prototype.ID = {
    
	BTN_BACK                 : "btnBack",
	SHOW_ORDER_IMG_BTN_ID    : "showOrderImg",
	ORDER_IMG_ID             : "orderImg",
	
	ITEM_ID                  : "id",
	
	BTN_REVIEW_OK            : "btnReviewOk",
	BTN_REVIEW_NG            : "btnReviewNg",
	
    //div
    DIV_MAINBODY             : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserReview.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
AdminUserReview.prototype.initEvent = function(){
    
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
	    
    ShaInput.button.onClick(self.getObject(self.ID.BTN_REVIEW_OK), 
		function(event) {
			ShaAjax.pop.postDialogLargeCenter(
					self.i18n["admin.order.btn.reviewOk"],
					self.jsContext.adminJsView.adminReviewOk.url_init,  
					[{name:"id", value:self.getObject(self.ID.ITEM_ID).val()}]);
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REVIEW_NG), 
		function(event) {
			ShaAjax.pop.postDialogLargeCenter(
					self.i18n["admin.order.btn.reviewNg"],
					self.jsContext.adminJsView.adminReviewNg.url_init,  
					[{name:"id", value:self.getObject(self.ID.ITEM_ID).val()}]);
		}
	);
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
			ShaAjax.ajax.post(
                self.jsContext.adminJsView.adminOrderSearch.url_order_list_review, 
                null, 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                }
            ); 
	    }
	);
};

//----------------------------------------------------------------------------]
