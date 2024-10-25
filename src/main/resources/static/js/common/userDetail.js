//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
UserDetail = function(dataMap){
	this.mainForm = $('#main_form');
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
	ITEM_PHONE                    : "phone",
	ITEM_MAIL                     : "mail",
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
	BTN_PHOTO_DOWNLOAD            : "photoFileDownload",
	ITEM_PHOTO_LBL                : "photoFileLbl",
	ITEM_PHOTO_NAME               : "photoFileName",
	IMG_PHOTO_OLD                 : "photoFileOld",
	
	ITEM_EDUCATIONAL_AT           : "educationalAtFile",
	BTN_EDUCATIONAL_AT_OPEN       : "educationalAtFileOpen",
	BTN_EDUCATIONAL_AT_DOWNLOAD   : "educationalAtFileDownload",
	ITEM_EDUCATIONAL_AT_LBL       : "educationalAtFileLbl",
	ITEM_EDUCATIONAL_AT_NAME      : "educationalAtFileName",
	IMG_EDUCATIONAL_AT_OLD        : "educationalAtFileOld",
		
	ITEM_BACHELOR_AT              : "bachelorAtFile",
	BTN_BACHELOR_AT_OPEN          : "bachelorAtFileOpen",
	BTN_BACHELOR_AT_DOWNLOAD      : "bachelorAtFileDownload",
	ITEM_BACHELOR_AT_LBL          : "bachelorAtFileLbl",
	ITEM_BACHELOR_AT_NAME         : "bachelorAtFileName",
	IMG_BACHELOR_AT_OLD           : "bachelorAtFileOld",
			
	ITEM_VOCATIONAL_AT            : "vocationalAtFile",
	BTN_VOCATIONAL_AT_OPEN        : "vocationalAtFileOpen",
	BTN_VOCATIONAL_AT_DOWNLOAD    : "vocationalAtFileDownload",
	ITEM_VOCATIONAL_AT_LBL        : "vocationalAtFileLbl",
	ITEM_VOCATIONAL_AT_NAME       : "vocationalAtFileName",
	IMG_VOCATIONAL_AT_OLD         : "vocationalAtFileOld",
	
	ITEM_MAJOR                    : "major",
	ITEM_RESEARCH_DIR             : "researchDir",
	ITEM_LEARN_EXPERIENCE         : "learnExperience",
	ITEM_WORK_EXPERIENCE          : "workExperience",
	ITEM_PAPERS                   : "papers",
	ITEM_HONORS                   : "honors",
	
	ITEM_APPLICATION_FORM         : "applicationFormFile",
	BTN_APPLICATION_FORM_OPEN     : "applicationFormFileOpen",
	BTN_APPLICATION_FORM_PRINT    : "applicationFormFilePrint",
	BTN_APPLICATION_FORM_DOWNLOAD : "applicationFormFileDownload",
	ITEM_APPLICATION_FORM_LBL     : "applicationFormFileLbl",
	ITEM_APPLICATION_FORM_NAME    : "applicationFormFileName",
	IMG_APPLICATION_FORM_OLD      : "applicationFormFileOld",
	
	PREFIX_USER_CARD_ID           : "userInfo_userCard_",
	ITEM_USER_CODE                : "userCode",
	
	SHOW_BASE_PANEL_BTN_ID        : "showBasePanelBtn",
    HIDE_BASE_PANEL_BTN_ID        : "hideBasePanelBtn",
    SHOW_SELF_PANEL_BTN_ID        : "showSelfPanelBtn",
    HIDE_SELF_PANEL_BTN_ID        : "hideSelfPanelBtn",
	
	HIDE_ITEM_PAGE_MODE_TYPE      : "mode_type",
	PAGE_MODE_TYPE_VIEW           : "view",
	PAGE_MODE_TYPE_EDIT_BY_USER   : "edit_by_user",
	PAGE_MODE_TYPE_EDIT_BY_ADMIN  : "edit_by_admin",
	PAGE_MODE_TYPE_INSERT_BY_ADMIN: "insert_by_admin",
	
	BTN_OPEN_EDIT_BASE            : "btnOpenEditBase",
	BTN_CLOSE_EDIT_BASE           : "btnCloseEditBase",
	BTN_OPEN_EDIT_EXTEND          : "btnOpenEditExtend",
	BTN_CLOSE_EDIT_EXTEND         : "btnCloseEditExtend",
	BTN_OPEN_PHOTO                : "btnOpenPhoto",
	BTN_TOP              		  : ".btnTop",
	BTN_OK              		  : ".btnOk",
	BTN_BACK              		  : "btnBack",
	
	TAB_ID                        : "detailTab",
	TAB_TITLE_USER_ID             : "userTab",
	TAB_TITLE_ORDER_ID            : "orderTab",
	TAB_TITLE_USER_CARD_ID        : "userCardTab",
	TAB_BODY_USER_ID              : "userTabBody",
	TAB_BODY_ORDER_ID             : "orderTabBody",
    TAB_BODY_USER_CARD_ID         : "userCardTabBody",
	
	BTN_DOWNLOAD_CERT             : "btnDownloadCert",
	
	DIV_MAINBODY                  : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
UserDetail.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	$(document).ready(function () {
	    $('.selectpicker').selectpicker({
		        "width":100, 'maxOptions':60
		    });
	});
};

// init event
UserDetail.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_USER_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_USER_ID, self.ID.TAB_BODY_USER_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_ORDER_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_ORDER_ID, self.ID.TAB_BODY_ORDER_ID);
		}
	);
	ShaInput.button.onClick(self.getObject(self.ID.TAB_TITLE_USER_CARD_ID), 
    	function(event) {
			ShaInput.tab.activeTab(self.getForm(), self.ID.TAB_ID, self.ID.TAB_TITLE_USER_CARD_ID, self.ID.TAB_BODY_USER_CARD_ID);
		}
	);
	
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
	/*
	ShaInput.button.onChange(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_TYPE), 
		function(event) {
			var userType = self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_TYPE).val();
			if (userType === "0101"){//甘肃协会会员
				self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE).val("");
				ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE));
			}else{
				ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE));
			}
		}
	);
	self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_TYPE).change();
	*/
	
	//self.setReadonlyBaseDetail();
	//self.setReadonlySelfDetail();
	//self.setReadonlyExtendDetail();
	//self.changeOkButtonEnable();
	
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
	
	//init event to ITEM_REGIST_DATE
    self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_REGIST_DATE).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		maxDate: 'now',
    });
		
	//init event to ITEM_VALID_END_DATE
    self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_VALID_END_DATE).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		maxDate: 'now',
    });
	
	//init event to ITEM_BIRTH
    self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BIRTH).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		maxDate: 'now',
    });
    
    //init event to BTN_PHOTO_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PHOTO_OPEN), 
		function(event) {
			/*if (self.getObject(self.ID.ITEM_ORDER_PHOTO).val() === "") {
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
    		);*/
			var newImg = new Image();
		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_user_extend.photo"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.IMG_PHOTO_OLD).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
			newImg.src = self.getObject(self.ID.IMG_PHOTO_OLD).attr("src");
	    }
	);
	
	//init event to BTN_PHOTO_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_PHOTO_DOWNLOAD), 
		function(event) {
			self.download("photo");
	    }
	);
	
	//init event to BTN_EDUCATIONAL_AT_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_EDUCATIONAL_AT_OPEN), 
		function(event) {
			/*if (self.getObject(self.ID.ITEM_EDUCATIONAL_AT).val() === "") {
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
    		);*/
			var newImg = new Image();
		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_user_extend.educational_at"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.IMG_EDUCATIONAL_AT_OLD).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
			newImg.src = self.getObject(self.ID.IMG_EDUCATIONAL_AT_OLD).attr("src");
	    }
	);
	
	//init event to BTN_EDUCATIONAL_AT_DOWNLOAD
	ShaInput.button.onClick(self.getObject(self.ID.BTN_EDUCATIONAL_AT_DOWNLOAD), 
		function(event) {
			self.download("educational");
	    }
	);
	
	//init event to BTN_BACHELOR_AT_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACHELOR_AT_OPEN), 
		function(event) {
			/*if (self.getObject(self.ID.ITEM_BACHELOR_AT).val() === "") {
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
    		);*/
			var newImg = new Image();
		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_user_extend.bachelor_at"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.IMG_BACHELOR_AT_OLD).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
			newImg.src = self.getObject(self.ID.IMG_BACHELOR_AT_OLD).attr("src");
	    }
	);
	
	//init event to BTN_BACHELOR_AT_DOWNLOAD
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACHELOR_AT_DOWNLOAD), 
		function(event) {
			self.download("bachelor");
	    }
	);
	
	//init event to BTN_VOCATIONAL_AT_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_VOCATIONAL_AT_OPEN), 
		function(event) {
			var newImg = new Image();
		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_user_extend.vocational_at"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.IMG_VOCATIONAL_AT_OLD).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
			newImg.src = self.getObject(self.ID.IMG_VOCATIONAL_AT_OLD).attr("src");
	    }
	);
	
	//init event to BTN_VOCATIONAL_AT_DOWNLOAD
	ShaInput.button.onClick(self.getObject(self.ID.BTN_VOCATIONAL_AT_DOWNLOAD), 
		function(event) {
			self.download("vocational");
	    }
	);
	
	//init event to BTN_APPLICATION_FORM_OPEN
	ShaInput.button.onClick(self.getObject(self.ID.BTN_APPLICATION_FORM_OPEN), 
		function(event) {
			var newImg = new Image();
		    newImg.onload = function() {
		    	var imgH = newImg.height;
		    	var imgW = newImg.width;
		    	var title = self.i18n["m_user_extend.application_form"];
				var html = ShaInput.img.previewOrigImgCardHtml(770, 450, imgW, imgH,
					"ShaDialog.dialogs.subSubDialogClose();", self.getObject(self.ID.IMG_APPLICATION_FORM_OLD).attr("src"));
				ShaDialog.dialogs.subSubDialogLargeCenter(title,html);
		    }
			newImg.src = self.getObject(self.ID.IMG_APPLICATION_FORM_OLD).attr("src");
	    }
	);
	
	//init event to BTN_APPLICATION_FORM_PRINT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_APPLICATION_FORM_PRINT), 
		function(event) {
			self.download("application_form_template");
	    }
	);
	
	//init event to BTN_APPLICATION_FORM_DOWNLOAD
	ShaInput.button.onClick(self.getObject(self.ID.BTN_APPLICATION_FORM_DOWNLOAD), 
		function(event) {
			self.download("application_form");
	    }
	);
	
	//init event to BTN_DOWNLOAD_CERT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_DOWNLOAD_CERT), 
		function(event) {
			self.download("user_certificate");
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
				const file = files[0];
				// 使用fileReader对象可以读取文件信息 ---- 将图片转换为 base64
			    const reader = new FileReader();
			    // 将选中的文件转换为base64 --- 异步操作
			    reader.readAsDataURL(file);
			    // 转换完成执行 this.result 就表示 转换成的结果
			    reader.onload = function () {
				  self.getObject(self.ID.IMG_PHOTO_OLD).attr("src",this.result);
			    }
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
				const file = files[0];
				// 使用fileReader对象可以读取文件信息 ---- 将图片转换为 base64
			    const reader = new FileReader();
			    // 将选中的文件转换为base64 --- 异步操作
			    reader.readAsDataURL(file);
			    // 转换完成执行 this.result 就表示 转换成的结果
			    reader.onload = function () {
				  self.getObject(self.ID.IMG_EDUCATIONAL_AT_OLD).attr("src",this.result);
			    }
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
				const file = files[0];
				// 使用fileReader对象可以读取文件信息 ---- 将图片转换为 base64
			    const reader = new FileReader();
			    // 将选中的文件转换为base64 --- 异步操作
			    reader.readAsDataURL(file);
			    // 转换完成执行 this.result 就表示 转换成的结果
			    reader.onload = function () {
				  self.getObject(self.ID.IMG_BACHELOR_AT_OLD).attr("src",this.result);
			    }
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
				const file = files[0];
				// 使用fileReader对象可以读取文件信息 ---- 将图片转换为 base64
			    const reader = new FileReader();
			    // 将选中的文件转换为base64 --- 异步操作
			    reader.readAsDataURL(file);
			    // 转换完成执行 this.result 就表示 转换成的结果
			    reader.onload = function () {
				self.getObject(self.ID.IMG_VOCATIONAL_AT_OLD).attr("src",this.result);
			    }
			}
	    }
	);
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_APPLICATION_FORM), 
			function(event) {
	    		var files = self.getObject(self.ID.ITEM_APPLICATION_FORM).prop('files');
	    		var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
		        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
		            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
		            self.getObject(self.ID.ITEM_APPLICATION_FORM).val("");
		        } else if(files[0].size > (1024*1024*5)) {
					ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
		            self.getObject(self.ID.ITEM_APPLICATION_FORM).val("");
				} else {
					self.getObject(self.ID.ITEM_APPLICATION_FORM_NAME).val(files[0].name);
					const file = files[0];
					// 使用fileReader对象可以读取文件信息 ---- 将图片转换为 base64
				    const reader = new FileReader();
				    // 将选中的文件转换为base64 --- 异步操作
				    reader.readAsDataURL(file);
				    // 转换完成执行 this.result 就表示 转换成的结果
				    reader.onload = function () {
					self.getObject(self.ID.IMG_APPLICATION_FORM_OLD).attr("src",this.result);
				    }
				}
		    }
		);
	
	//init event to BTN_TOP
	$topBtnList = self.getForm().find(self.ID.BTN_TOP);
	$topBtnList.each(function(i, elem){
	   	ShaInput.button.onClick($(elem),
				function(event) {
					scrollTo(0,0);
				}
			);
		}
	);
	/*ShaInput.button.onClick(self.getObject(self.ID.BTN_TOP),
	   	function(event) {
			scrollTo(0,0);
		}
	);*/
			
	//init event to BTN_OK
	$okBtnList = self.getForm().find(self.ID.BTN_OK);
	$okBtnList.each(function(i, elem){
		//check box init
	   	ShaInput.button.onClick($(elem),
		function(event) {
				if(self.check()) {
		            return;
		        }
				
				if (self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_INSERT_BY_ADMIN){
					ShaDialog.dialogs.confirm(
						self.i18n["dialogs.confirm.add.title"], 
						self.i18n["dialogs.confirm.add.msg"], 
						function () {
							ShaAjax.ajax.postWithUploadFile(
								url = self.jsContext.jsView.userDetail.url_user_detail_add,
								"userDetailForm", 
								function (data) {
									ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
									self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
									window.scrollTo(0, 0);
								}
							);
						}
					);
				}else{
			        ShaDialog.dialogs.confirm(
						self.i18n["dialogs.confirm.edit.title"], 
						self.i18n["dialogs.confirm.edit.msg"], 
						function () {
							ShaAjax.ajax.postWithUploadFile(
								self.jsContext.jsView.userDetail.url_user_detail_edit,
								"userDetailForm", 
								function (data) {
									ShaDialog.dialogs.success(self.i18n["dialogs.edit.success.msg"]);
									self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
									window.scrollTo(0, 0);
								}
							);
						}
					);
				}
		    }
	    );
	});
	/*ShaInput.button.onClick(self.getObject(self.ID.BTN_OK), 
		function(event) {
			if(self.check()) {
	            return;
	        }
			
			if (self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_INSERT_BY_ADMIN){
				ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.add.title"], 
					self.i18n["dialogs.confirm.add.msg"], 
					function () {
						ShaAjax.ajax.postWithUploadFile(
							url = self.jsContext.jsView.userDetail.url_user_detail_add,
							"userDetailForm", 
							function (data) {
								ShaDialog.dialogs.success(self.i18n["dialogs.add.success.msg"]);
								self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
								window.scrollTo(0, 0);
							}
						);
					}
				);
			}else{
		        ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.edit.title"], 
					self.i18n["dialogs.confirm.edit.msg"], 
					function () {
						ShaAjax.ajax.postWithUploadFile(
							self.jsContext.jsView.userDetail.url_user_detail_edit,
							"userDetailForm", 
							function (data) {
								ShaDialog.dialogs.success(self.i18n["dialogs.edit.success.msg"]);
								self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
								window.scrollTo(0, 0);
							}
						);
					}
				);
			}
	    }
	);*/
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_BACK),
	   	function(event) {
			/*ShaAjax.ajax.post(
	               self.jsContext.adminJsView.adminUserSearch.url_user_list, 
				   [{name:"selectedUserId", value:self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_ID).val()}],
	               function(data){
	                   self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                   $('[data-toggle="tooltip"]').tooltip();
	               }
	           ); */
			window.history.back();
		}
	);

};

UserDetail.prototype.isAdminEdit = function(){
	var self = this;
	return self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_EDIT_BY_ADMIN ||
			self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_INSERT_BY_ADMIN;
};

UserDetail.prototype.changeOkButtonEnable = function(){
	var self = this;
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_TYPE)) ||
		ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_NAME)) ||
		ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_MAJOR))){
		ShaInput.obj.enabled(self.getObject(self.ID.BTN_OK));
	}else{
		ShaInput.obj.disabled(self.getObject(self.ID.BTN_OK));
	}
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
	if (self.isAdminEdit() ){
		ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE));
	}
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MEMBERSHIP_PATH));
	ShaInput.obj.enabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_FOCUS_ON));
	
	self.changeOkButtonEnable();
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
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MEMBERSHIP_PATH));
	ShaInput.obj.disabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_FOCUS_ON));

	self.changeOkButtonEnable();
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
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_APPLICATION_FORM));
	ShaInput.obj.enabled(self.getObject(self.ID.ITEM_APPLICATION_FORM_LBL));	
	ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_APPLICATION_FORM_OPEN));
	//ShaInput.obj.enabledBtn(self.getObject(self.ID.BTN_APPLICATION_FORM_PRINT));

	self.changeOkButtonEnable();
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
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_APPLICATION_FORM));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_APPLICATION_FORM_LBL));
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_APPLICATION_FORM_NAME));
	ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_APPLICATION_FORM_OPEN));
	//ShaInput.obj.disabledBtn(self.getObject(self.ID.BTN_APPLICATION_FORM_PRINT));

	self.changeOkButtonEnable();		
};
//----------------------------------------------------------------------------]
UserDetail.prototype.download = function(fileType){
	var self = this;
	ShaAjax.ajax.getWithDownloadFile(
       self.jsContext.jsView.userDetail.url_user_detail_download+"/"+self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_ID).val()+"/"+fileType,
	   null,
       function(data, filename){
			if (data.size == 0){
				ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
				return;
			}
            if (ShaUtil.other.isChrome() || ShaUtil.other.isSafari()){
              // chrome
              const link = document.createElement('a');
              link.href = window.URL.createObjectURL(data);
              link.download = filename;
              link.click();
			  window.URL.revokeObjectURL(link.href);
            } else if (ShaUtil.other.isIE()) {
              // IE
              //const blob = new Blob([data], {type: 'application/force-download'});
              window.navigator.msSaveBlob(data, filename);
            } else {
              // Firefox
              const file = new File([data], filename, {type: 'application/force-download'});
              window.open(URL.createObjectURL(file));
            }
       }
   ); 
};
//checkValue
UserDetail.prototype.check = function(){
	var self = this;
	var isAdminInsert = self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_INSERT_BY_ADMIN;
	var inputCheckItemList = [];
	ShaCheck.check.setFirstItemFocus(true);
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE))){
		inputCheckItemList = inputCheckItemList.concat([
	       [ self.i18n["m_user_card.user_code"], 	self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE)], 
	   ]);
	}
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE))){
		inputCheckItemList = inputCheckItemList.concat([
	       [ self.i18n["m_user.phone"], 	        self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE)], 
	   ]);
	}
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_NAME))){
		inputCheckItemList = inputCheckItemList.concat([
	       [ self.i18n["m_user.name"], 	            self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_NAME)], 
	   ]);
	}
	
	/*if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL))){
		inputCheckItemList = inputCheckItemList.concat([
	       [ self.i18n["m_user.mail"], 	            self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL)], 
	   ]);
	}*/
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BIRTH)) && 
		self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_EDIT_BY_USER){
			inputCheckItemList = inputCheckItemList.concat([
	       [ self.i18n["m_user.birth"], 	        self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_BIRTH)], 
		   [ self.i18n["m_user.employer"],       	self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_EMPLOYER)], 
		   [ self.i18n["m_user.certificate_code"], 	self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_CERTIFICATE_CODE)], 
		   [ self.i18n["m_user.postal_code"], 	    self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_POSTAL_CODE)], 
		   [ self.i18n["m_user.address"], 	        self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_ADDRESS)], 
	   ]);
	}
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_MAJOR)) &&
			self.getObject(self.ID.HIDE_ITEM_PAGE_MODE_TYPE).val() == self.ID.PAGE_MODE_TYPE_EDIT_BY_USER){
		inputCheckItemList = inputCheckItemList.concat([
	       [ self.i18n["m_user_extend.major"], 	                                    self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_MAJOR)], 
	      // [ self.i18n["m_user_extend.learn_experience"].replace(/(<br\/>)*/g,""), 	self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_LEARN_EXPERIENCE)], 
		  // [ self.i18n["m_user_extend.work_experience"].replace(/(<br\/>)*/g,""),   self.getObject(self.ID.PREFIX_EXTEND_ID + self.ID.ITEM_WORK_EXPERIENCE)], 
	   ]);
	}
	
	if (inputCheckItemList.length > 0 && ShaCheck.check.checkNotBlank(inputCheckItemList, true)) {
		return true;
	}
	
	const checkMultiItemsMap = new Map();
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE))){
		checkMultiItemsMap.set('checkUserCode', [[ self.i18n["m_user_card.user_code"], 	self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE)]]);
	}
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE))){
		checkMultiItemsMap.set('checkPhoneNumber', [[ self.i18n["m_user.phone"], 	self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE)]]);
	}
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL)) && self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL).val() !=''){
		checkMultiItemsMap.set('checkEmail', [[ self.i18n["m_user.mail"], 	self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_MAIL)]]);
	}
	
	if (ShaCheck.check.checkMultiItems(checkMultiItemsMap)){
		return true;
	}
		
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE))){
		checkMultiItemsMap.set('checkUserCodeExisted', [[ self.i18n["m_user_card.user_code"], 	self.getObject(self.ID.PREFIX_USER_CARD_ID + self.ID.ITEM_USER_CODE)]]);
	}
	
	if (ShaInput.obj.isEnabled(self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE))){
		checkMultiItemsMap.set('checkPhoneNumberExisted', [[ self.i18n["m_user.phone"], 	
			self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_PHONE), true, self.getObject(self.ID.PREFIX_ID + self.ID.ITEM_USER_ID).val()]]);
	}
	
	if (ShaCheck.check.checkMultiItems(checkMultiItemsMap)){
		return true;
	}
	
	return false;
};
