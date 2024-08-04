//-------------------------------------------------------------------------------------------
//-----------------AdminUserReviewMulti.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserReviewMulti = function(dataMap){
	this.fatherForm = $('#userReviewSearchForm');
    this.form = $('#AdminUserReviewMultiForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminUserReviewMulti, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserReviewMulti.prototype.ID = {
	
	BTN_CLOSE : "btnClose",
    BTN_OK    : "btnOk",
	SEARCH_BTN_ID :"searchBtn",
    
	CHECK_STATUS_PASS        : "02",
	ITEM_CHECKSTATUS         : "checkStatus",
	ITEM_MEMO                : "memo",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserReviewMulti.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
AdminUserReviewMulti.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.checkValue()) {
	            return;
	        }
			if (self.getObject(self.ID.ITEM_CHECKSTATUS).val() === Pos.constants.setInfo.common.check_status_type_pass){//审核通过
				self.getObject(self.ID.ITEM_MEMO).val("");
			}
	        ShaDialog.dialogs.confirm(
				self.i18n["dialogs.confirm.add.title"], 
				self.i18n["dialogs.confirm.add.msg"], 
				function () {
					ShaAjax.ajax.post(
			            self.jsContext.adminJsView.adminUserReviewMulti.url_review_multi, 
			            self.form.serializeArray(), 
			            function(data){
							if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
								ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
								ShaDialog.dialogs.dialogClose();
								self.getObjectInForm(self.fatherForm, self.ID.SEARCH_BTN_ID).click();
							}else{
								ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
							}
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

	ShaInput.button.onChange(self.getObject(self.ID.ITEM_CHECKSTATUS), 
		function(event) {
			var status = self.getObject(self.ID.ITEM_CHECKSTATUS).val();
			if (status === Pos.constants.setInfo.common.check_status_type_pass){//审核通过
				ShaInput.obj.disabled(self.getObject(self.ID.ITEM_MEMO));
			}else{
				ShaInput.obj.enabled(self.getObject(self.ID.ITEM_MEMO));
			}
		}
	);
	
	self.getObject(self.ID.ITEM_CHECKSTATUS).change();
};

//checkValue
AdminUserReviewMulti.prototype.checkValue = function(){
	//keep self instance for call back
	var self = this;
	ShaCheck.check.setFirstItemFocus(true);
	var status = self.getObject(self.ID.ITEM_CHECKSTATUS).val();
	if (status != Pos.constants.setInfo.common.check_status_type_pass && 
		ShaCheck.check.checkNotBlank([[self.i18n["admin.userReview.review.opinion"], 	self.getObject(self.ID.ITEM_MEMO)]])){
		return true;
	}

    return false;
};

//----------------------------------------------------------------------------]
