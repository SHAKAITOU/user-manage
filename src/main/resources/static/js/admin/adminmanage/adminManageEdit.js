//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminManageEdit = function(dataMap){
	this.fatherForm = $('#AdminManageSearchForm');
    this.form = $('#AdminManageEditForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.PREFIX_ID = "user_";
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminManageEdit, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminManageEdit.prototype.ID = {
	SEARCH_BTN_ID 				  :"searchBtn",
	PREFIX_ID                     : "userInfo_user_",
	ITEM_USER_ID                  : "id",
	ITEM_USER_TYPE                : "userType",
	ITEM_USER_NAME                : "name",
	ITEM_USER_PHONE               : "phone",
	ITEM_USER_MAIL                : "mail",
	ITEM_USER_PW  				  : 'password',
	ITEM_USER_PWREP  			  : 'passwordRep',
	HID_MODE  			          : 'hid_mode',
	
	MODE_EIDT  			          : '1',
	MODE_ADD  			          : '2',
	
	ITEM_PW_SHOW_BTN  		      : 'passwordShow',
	ITEM_PWREP_SHOW_BTN   	      : 'passwordRepShow',
	
	ITEM_PHOTO                    : "photoFile",
	BTN_PHOTO_OPEN                : "photoFileOpen",
	ITEM_PHOTO_LBL                : "photoFileLbl",
	ITEM_PHOTO_NAME               : "photoFileName",
	
	BTN_OPEN_PHOTO                : "btnOpenPhoto",
	BTN_OK              		  : "btnOk",
	BTN_CLOSE              		  : "btnClose",
	
	DIV_MAINBODY                  : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminManageEdit.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
AdminManageEdit.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to ITEM_PW_SHOW_BTN
	ShaInput.button.onClick(self.getObject(self.ID.PREFIX_ID+self.ID.ITEM_PW_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.PREFIX_ID+self.ID.ITEM_USER_PW);
			var btn  = self.getObject(self.ID.PREFIX_ID+self.ID.ITEM_PW_SHOW_BTN);
    		if (iptObj.attr('type') === "text") {
				iptObj.attr('type', "password");
				btn.removeClass("fa fa-eye");
				btn.addClass("fa fa-eye-slash");
			} else {
				iptObj.attr('type', "text");
				btn.removeClass("fa fa-eye-slash");
				btn.addClass("fa fa-eye");
			}
	    }
	);
	
	//init event to ITEM_PWREP_SHOW_BTN
	ShaInput.button.onClick(self.getObject(self.ID.PREFIX_ID+self.ID.ITEM_PWREP_SHOW_BTN), 
		function(event) {
			var iptObj = self.getObject(self.ID.PREFIX_ID+self.ID.ITEM_USER_PWREP);
			var btn  = self.getObject(self.ID.PREFIX_ID+self.ID.ITEM_PWREP_SHOW_BTN);
    		if (iptObj.attr('type') === "text") {
				iptObj.attr('type', "password");
				btn.removeClass("fa fa-eye");
				btn.addClass("fa fa-eye-slash");
			} else {
				iptObj.attr('type', "text");
				btn.removeClass("fa fa-eye-slash");
				btn.addClass("fa fa-eye");
			}
	    }
	);
    
    //init event to BTN_PHOTO_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PHOTO_OPEN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_ORDER_PHOTO).val() === "") {
				return;
			}
    		ShaInput.img.previewInDialog(
				self.getForm(), 
    			self.ID.ITEM_PHOTO,
    			function(imgData) {
					var title = self.i18n["m_admin.photo"];
					var html = ShaInput.img.previewImgCardHtml(260, 250, "ShaDialog.dialogs.subSubDialogClose();", imgData);
					ShaDialog.dialogs.subSubDialogSmallCenter(title,html);
				}
    		);
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_PHOTO), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_PHOTO).prop('files');
    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_PHOTO).val("");
	        } else if(files[0].size > (1024*1024)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_PHOTO).val("");
			} else {
				self.getObject(self.ID.ITEM_PHOTO_NAME).val(files[0].name);
			}
	    }
	);
	
	//init event to BTN_OK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if (self.isAddMode()){//add
		        ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.add.title"], 
					self.i18n["dialogs.confirm.add.msg"], 
					function () {
						ShaAjax.ajax.postWithUploadFile(
							self.jsContext.adminJsView.adminManageEdit.url_edit,
							"AdminManageEditForm", 
							function (data) {
								ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
								ShaDialog.dialogs.dialogClose();
								self.getObjectInForm(self.fatherForm, self.ID.SEARCH_BTN_ID).click();
							}
						);
					}
				);
			}else{//edit
				ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.edit.title"], 
					self.i18n["dialogs.confirm.edit.msg"], 
					function () {
						
						ShaAjax.ajax.postWithUploadFile(
							self.jsContext.adminJsView.adminManageEdit.url_edit,
							"AdminManageEditForm", 
							function (data) {
								ShaDialog.dialogs.success(self.i18n["dialogs.edit.success.msg"]);
								ShaDialog.dialogs.dialogClose();
								self.getObjectInForm(self.fatherForm, self.ID.SEARCH_BTN_ID).click();
							}
						);
					}
				);
			}
	    }
	);
	
	//init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE),
	   	function(event) {
			ShaDialog.dialogs.dialogClose(); 
		}
	);

};

AdminManageEdit.prototype.isAddMode = function(){
	var self = this;
	return self.getObject(self.ID.HID_MODE).val() == self.ID.MODE_ADD;
};

//checkValue
AdminManageEdit.prototype.check = function(){
	var self = this;
	const checkMultiItemsMap = new Map();
	ShaCheck.check.setFirstItemFocus(true);
	var inputCheckItemList = [
       [ self.i18n["m_admin.name"], 	        self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_NAME)], 
       [ self.i18n["m_admin.phone"], 	        self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PHONE)], 
	   [ self.i18n["m_admin.mail"],       		self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_MAIL)], 
   ];
   
   if (self.isAddMode()){
		inputCheckItemList.push([ self.i18n["m_admin.password"], 	        self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PW)]);
		inputCheckItemList.push([ self.i18n["m_admin.passwordRep"], 	    self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PWREP)]);
   }
   
	
	checkMultiItemsMap.set('checkNotBlank', inputCheckItemList);
	
	if (!ShaCheck.check.isBlank(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PHONE).val())){
			checkMultiItemsMap.set('checkPhoneNumber', [[ self.i18n["m_admin.phone"], 	self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PHONE)]]);
	}
	if (!ShaCheck.check.isBlank(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_MAIL).val())){
		checkMultiItemsMap.set('checkEmail', [[ self.i18n["m_admin.mail"], 			self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_MAIL)]]);
	}
	
	if (ShaCheck.check.checkMultiItems(checkMultiItemsMap)){
		return true;
	}
	
	if (ShaCheck.check.checkMinLength(			[
				       [ self.i18n["m_admin.password"],self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PW), Pos.constants.setInfo.common.user_pw_min_l], 
					   [ self.i18n["m_admin.passwordRep"],self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PWREP), Pos.constants.setInfo.common.user_pw_min_l], ]) || 
		ShaCheck.check.checkMaxLength(			[
				       [ self.i18n["m_admin.password"],self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PW), Pos.constants.setInfo.common.user_pw_max_l], 
					   [ self.i18n["m_admin.passwordRep"],self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PWREP), Pos.constants.setInfo.common.user_pw_max_l],
				     ])){
		result = true;
	}else{
		inputCheckItemList = [
			       [ "",self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PW), self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_PWREP)], 
	    ];
		if (ShaCheck.check.checkConfirmPassword(inputCheckItemList)){
			result = true;
		}
	}
	
	return false;
};

//----------------------------------------------------------------------------]
