//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ReviewNg = function(dataMap){
    this.menuForm = $('#menu_form');
    this.fatherForm = $('#ReviewOrderForm');
    this.form = $('#ReviewNgForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(ReviewNg, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ReviewNg.prototype.ID = {
	
	BTN_BACK                 : "btnBack",
	
	BTN_OK                   : "btnOk",
	BTN_CLOSE                : "btnClose",
	
	ITEM_ID                  : "id",
	ITEM_ANS                 : "ans",
	
    //div
    DIV_MAINBODY             : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
ReviewNg.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
ReviewNg.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			ShaDialog.dialogs.confirm(
				self.i18n["admin.order.btn.reviewNg"],
				self.i18n["dialogs.confirm.add.msg"], 
				function () { 
					ShaAjax.ajax.post(
		                self.jsContext.adminJsView.adminReviewNg.url_review_commit, 
		                self.form.serializeArray(), 
		                function(data){
							ShaDialog.dialogs.dialogClose();
		                    ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
		                    ShaPage.pageCom.refreshOrderCnt(
								self.jsContext, 
								self.menuForm, 
								function(){
									self.getObjectInForm(self.fatherForm, self.ID.BTN_BACK).click();
								}
							);
		                    
		                }
		            );
				}
			);
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
