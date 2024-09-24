//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserReview = function(dataMap){
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.form = $('#AdminUserReviewForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminUserReview, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserReview.prototype.ID = {
	TAB_ID                   : "detailTab",
	TAB_TITLE_REVIEW_ID      : "reviewTab",
	TAB_TITLE_USER_DETAIL_ID : "userDetailTab",
    TAB_BODY_REVIEW_ID       : "reviewTabBody",
    TAB_BODY_USER_DETAIL_ID  : "userDetailTabBody",
    
	BTN_OK                   : "btnOk",
	BTN_BACK                 : "btnBack",
	
	CHECK_STATUS_PASS        : "02",
	ITEM_CHECKSTATUS         : "checkStatus",
	ITEM_MEMO                : "memo",

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
	
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_REVIEW_ID), 
	   	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_REVIEW_ID, self.ID.TAB_BODY_REVIEW_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_USER_DETAIL_ID), 
	   	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_USER_DETAIL_ID, self.ID.TAB_BODY_USER_DETAIL_ID);
		}
	);
	
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
			            self.jsContext.adminJsView.adminUserReview.url_review, 
			            self.form.serializeArray(), 
			            function(data){
							if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
								ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
								ShaAjax.ajax.get(
					                self.jsContext.adminJsView.adminUserReviewSearch.url_user_list, 
									null, 
					                function(data){
					                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
					                }
					            );
							}else{
								ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
							}
			            }
			        );
				}
			);
	    }
	);
	
    //init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK), 
		function(event) {
			ShaAjax.ajax.get(
                self.jsContext.adminJsView.adminUserReviewSearch.url_user_list, 
                //[{name:"searchMode",     value:"waitList"}],
				null, 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                }
            );
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
AdminUserReview.prototype.checkValue = function(){
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
