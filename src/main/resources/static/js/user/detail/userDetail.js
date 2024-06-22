//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
UserDetail = function(dataMap){
    this.form = $('#userDetailForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.PREFIX_ID = "user_";
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(UserDetail, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
UserDetail.prototype.ID = {
	BASE_PANEL_CARD_ID            : "basePanelCard",
    SELF_PANEL_CARD_ID            : "selfPanelCard",
	
	PREFIX_ID                     : "userInfo_user_",
	ITEM_USER_ID                  : "id",
	ITEM_USER_ID_SHOW             : "idShow",
	ITEM_USER_TYPE                : "userType",
	ITEM_REGIST_DATE              : "registDate",
	ITEM_VALID_END_DATE           : "validEndDate",
	ITEM_USER_NAME                : "name",
	ITEM_USER_SEX                 : "sex",
	ITEM_BIRTH                    : "birth",
	ITEM_NATIONALITY              : "nationality",
	ITEM_POLITICAL                : "political",
	ITEM_EDU_DEGREE               : "eduDegree",
	ITEM_BACHELOR                 : "bachelor",
	ITEM_POSITION                 : "position",
	ITEM_EMPLOYER                 : "employer",
	ITEM_EMPLOYER_TYPE            : "employerType",
	ITEM_JOB_TITLE                : "jobTitle",
	ITEM_CERTIFICATE_TYPE         : "certificateType",
	ITEM_CERTIFICATE_CODE         : "certificateCode",
	ITEM_AREA                     : "area",
	ITEM_POSTAL_CODE              : "postalCode",
	ITEM_ADDRESS                  : "address",
	ITEM_MEMBERSHIP_PATH          : "membershipPath",
	ITEM_FOCUS_ON                 : "focusOn",
	
	PREFIX_EXTEND_ID              : "userInfo_userExtend_",
	ITEM_INTRODUCER1              : "introducer1",
	ITEM_INTRODUCER2              : "introducer2",
	
	ITEM_PHOTO                    : "photoFile",
	BTN_PHOTO_OPEN                : "photoFileOpen",
	ITEM_PHOTO_LBL                : "photoFileLbl",
	ITEM_PHOTO_NAME               : "photoFileName",
	
	ITEM_EDUCATIONAL_AT           : "educationalAtFile",
	BTN_EDUCATIONAL_AT_OPEN       : "educationalAtFileOpen",
	ITEM_EDUCATIONAL_AT_LBL       : "educationalAtFileLbl",
	ITEM_EDUCATIONAL_AT_NAME      : "educationalAtFileName",
		
	ITEM_BACHELOR_AT              : "bachelorAtFile",
	BTN_BACHELOR_AT_OPEN          : "bachelorAtFileOpen",
	ITEM_BACHELOR_AT_LBL          : "bachelorAtFileLbl",
	ITEM_BACHELOR_AT_NAME         : "bachelorAtFileName",
			
	ITEM_VOCATIONAL_AT            : "vocationalAtFile",
	BTN_VOCATIONAL_AT_OPEN        : "vocationalAtFileOpen",
	ITEM_VOCATIONAL_AT_LBL        : "vocationalAtFileLbl",
	ITEM_VOCATIONAL_AT_NAME       : "vocationalAtFileName",
	
	ITEM_MAJOR                    : "major",
	ITEM_RESEARCH_DIR             : "researchDir",
	ITEM_LEARN_EXPERIENCE         : "learnExperience",
	ITEM_WORK_EXPERIENCE          : "workExperience",
	ITEM_PAPERS                   : "papers",
	ITEM_HONORS                   : "honors",
	
	SHOW_BASE_PANEL_BTN_ID        : "showBasePanelBtn",
    HIDE_BASE_PANEL_BTN_ID        : "hideBasePanelBtn",
    SHOW_SELF_PANEL_BTN_ID        : "showSelfPanelBtn",
    HIDE_SELF_PANEL_BTN_ID        : "hideSelfPanelBtn",
	
	BTN_OPEN_EDIT_BASE            : "btnOpenEditBase",
	BTN_CLOSE_EDIT_BASE           : "btnCloseEditBase",
	BTN_OPEN_EDIT_EXTEND          : "btnOpenEditExtend",
	BTN_CLOSE_EDIT_EXTEND         : "btnCloseEditExtend",
	BTN_OPEN_PHOTO                : "btnOpenPhoto",

};
//------------------------------------------]

//---------------method define--------------[
//init 
UserDetail.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
UserDetail.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.SHOW_BASE_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.BASE_PANEL_CARD_ID).find(".card-body").show();
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_BASE_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.BASE_PANEL_CARD_ID).find(".card-body").hide();
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SELF_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.SELF_PANEL_CARD_ID).find(".card-body").show();
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SELF_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.SELF_PANEL_CARD_ID).find(".card-body").hide();
		}
    );
	
	self.setReadonlyBaseDetail();
	self.setReadonlySelfDetail();
	self.setReadonlyExtendDetail();
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OPEN_EDIT_BASE), 
		function(event) {
    		self.setEditableSelfDetail();
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE_EDIT_BASE), 
		function(event) {
    		self.setReadonlySelfDetail();
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OPEN_EDIT_EXTEND), 
		function(event) {
    		self.setEditableExtendDetail();
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE_EDIT_EXTEND), 
		function(event) {
    		self.setReadonlyExtendDetail();
	    }
	);
	
	//init event to BTN_BACK
    self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BIRTH).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
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
					var title = self.i18n["m_user_extend.photo"];
					var html = ShaInput.img.previewImgCardHtml(260, 250, "ShaDialog.dialogs.subSubDialogClose();", imgData);
					ShaDialog.dialogs.subSubDialogSmallCenter(title,html);
				}
    		);
	    }
	);
	
	//init event to BTN_EDUCATIONAL_AT_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_EDUCATIONAL_AT_OPEN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_EDUCATIONAL_AT).val() === "") {
				return;
			}
    		ShaInput.img.previewInDialog(
				self.getForm(), 
    			self.ID.ITEM_EDUCATIONAL_AT,
    			function(imgData) {
					var title = self.i18n["m_user_extend.educational_at"];
					var html = ShaInput.img.previewImgCardHtml(260, 250, "ShaDialog.dialogs.subSubDialogClose();", imgData);
					ShaDialog.dialogs.subSubDialogSmallCenter(title,html);
				}
    		);
	    }
	);
	
	//init event to BTN_BACHELOR_AT_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACHELOR_AT_OPEN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_BACHELOR_AT).val() === "") {
				return;
			}
    		ShaInput.img.previewInDialog(
				self.getForm(), 
    			self.ID.ITEM_BACHELOR_AT,
    			function(imgData) {
					var title = self.i18n["m_user_extend.bachelor_at"];
					var html = ShaInput.img.previewImgCardHtml(260, 250, "ShaDialog.dialogs.subSubDialogClose();", imgData);
					ShaDialog.dialogs.subSubDialogSmallCenter(title,html);
				}
    		);
	    }
	);
	
	//init event to BTN_VOCATIONAL_AT_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_VOCATIONAL_AT_OPEN), 
		function(event) {
			if (self.getObject(self.ID.ITEM_VOCATIONAL_AT).val() === "") {
				return;
			}
    		ShaInput.img.previewInDialog(
				self.getForm(), 
    			self.ID.ITEM_VOCATIONAL_AT,
    			function(imgData) {
					var title = self.i18n["m_user_extend.vocational_at"];
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
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_EDUCATIONAL_AT), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_EDUCATIONAL_AT).prop('files');
    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_EDUCATIONAL_AT).val("");
	        } else if(files[0].size > (1024*1024*5)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_EDUCATIONAL_AT).val("");
			} else {
				self.getObject(self.ID.ITEM_EDUCATIONAL_AT_NAME).val(files[0].name);
			}
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_BACHELOR_AT), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_BACHELOR_AT).prop('files');
    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_BACHELOR_AT).val("");
	        } else if(files[0].size > (1024*1024*5)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_BACHELOR_AT).val("");
			} else {
				self.getObject(self.ID.ITEM_BACHELOR_AT_NAME).val(files[0].name);
			}
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_VOCATIONAL_AT), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_VOCATIONAL_AT).prop('files');
    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_VOCATIONAL_AT).val("");
	        } else if(files[0].size > (1024*1024*5)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_VOCATIONAL_AT).val("");
			} else {
				self.getObject(self.ID.ITEM_VOCATIONAL_AT_NAME).val(files[0].name);
			}
	    }
	);

};
UserDetail.prototype.setReadonlyBaseDetail = function(){
	var self = this;
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_ID_SHOW));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_TYPE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_REGIST_DATE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_VALID_END_DATE));
};

UserDetail.prototype.setEditableSelfDetail = function(){
	var self = this;
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_NAME));
	$radioList = self.getObjectList(self.ID.PREFIX_ID + self.ID.ITEM_USER_SEX);
	$radioList.each(function(i, elem){
		ShaInput.obj.enabled($(elem));
	});
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BIRTH));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_NATIONALITY));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POLITICAL));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EDU_DEGREE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BACHELOR));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POSITION));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EMPLOYER));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EMPLOYER_TYPE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_JOB_TITLE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_CERTIFICATE_TYPE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_CERTIFICATE_CODE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_AREA));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POSTAL_CODE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_ADDRESS));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MEMBERSHIP_PATH));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_FOCUS_ON));
	
};

UserDetail.prototype.setReadonlySelfDetail = function(){
	var self = this;
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_NAME));
	$radioList = self.getObjectList(self.ID.PREFIX_ID + self.ID.ITEM_USER_SEX);
	$radioList.each(function(i, elem){
		ShaInput.obj.disabled($(elem));
	});
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BIRTH));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_NATIONALITY));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POLITICAL));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EDU_DEGREE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BACHELOR));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POSITION));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EMPLOYER));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EMPLOYER_TYPE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_JOB_TITLE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_CERTIFICATE_TYPE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_CERTIFICATE_CODE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_AREA));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POSTAL_CODE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_ADDRESS));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MEMBERSHIP_PATH));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_FOCUS_ON));
	
};

UserDetail.prototype.setEditableExtendDetail = function(){
	var self = this;
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_INTRODUCER1));	
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_INTRODUCER2));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_PHOTO));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_PHOTO_LBL));
	ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_PHOTO_OPEN));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_EDUCATIONAL_AT));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_EDUCATIONAL_AT_LBL));	
	ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_EDUCATIONAL_AT_OPEN));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_BACHELOR_AT));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_BACHELOR_AT_LBL));
	ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_BACHELOR_AT_OPEN));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_VOCATIONAL_AT));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_VOCATIONAL_AT_LBL));
	ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_VOCATIONAL_AT_OPEN));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_MAJOR));	
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_RESEARCH_DIR));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_LEARN_EXPERIENCE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_WORK_EXPERIENCE));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_PAPERS));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_HONORS));
};

UserDetail.prototype.setReadonlyExtendDetail = function(){
	var self = this;
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_INTRODUCER1));	
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_INTRODUCER2));	
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_PHOTO));	
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_PHOTO_LBL));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_PHOTO_NAME));
	ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_PHOTO_OPEN));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_EDUCATIONAL_AT));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_EDUCATIONAL_AT_LBL));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_EDUCATIONAL_AT_NAME));	
	ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_EDUCATIONAL_AT_OPEN));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_BACHELOR_AT));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_BACHELOR_AT_LBL));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_BACHELOR_AT_NAME));
	ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_BACHELOR_AT_OPEN));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_VOCATIONAL_AT));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_VOCATIONAL_AT_LBL));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_VOCATIONAL_AT_NAME));
	ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_VOCATIONAL_AT_OPEN));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_MAJOR));	
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_RESEARCH_DIR));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_LEARN_EXPERIENCE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_WORK_EXPERIENCE));	
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_PAPERS));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_HONORS));			
};
//----------------------------------------------------------------------------]
