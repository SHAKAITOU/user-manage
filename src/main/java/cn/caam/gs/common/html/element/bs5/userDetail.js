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
	
	PREFIX_ID                     : "user_",
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
	
	PREFIX_EXTEND_NAME            : "userExtend.",
	
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
	
	self.setReadonlyBaseDetail();
	self.setReadonlySelfDetail();
	
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
    
    //init event to BTN_NEXT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_OPEN_PHOTO), 
		function(event) {
    		ShaAjax.pop.postDialogMiddleCenter(
    			self.i18n["m_user_extend.photo"],
    			Pos.constants.setInfo.jsView.common.url_com_show_photo, 
    			self.getForm().serializeArray()); 
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
};

UserDetail.prototype.setReadonlyExtendDetail = function(){
	var self = this;	
};
//----------------------------------------------------------------------------]
