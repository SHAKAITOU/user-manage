//-------------------------------------------------------------------------------------------
//-----------------AdminUserSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserSearch = function(dataMap){
    this.form = $('#userSearchForm');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminUserSearch, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserSearch.prototype.ID = {
    
    REGIST_DATE_FROM_INPT    : "registDateFrom",
    REGIST_DATE_TO_INPT      : "registDateTo",
    VALID_END_DATE_FROM_INPT : "validEndDateFrom",
    VALID_END_DATE_TO_INPT   : "validEndDateTo",
    
    SEARCH_BTN_ID            : "searchBtn",
    SHOW_SEARCH_PANEL_BTN_ID : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID : "hideSearchPanelBtn",
    HID_HIDE_SEARCH          : "hideSearch",
	HID_SELECTED_USER_ID     : "selectedUserId",
	
	ADD_BTN_ID               : "addBtn",
	EXPORT_BTN_ID            : "exportBtn",
	IMPORT_BTN_ID            : "importBtn",
    
    USER_LIST_CHECK_ALL_ID   : "user_check_all",
    USER_CHECK_PREF_ID       : ".user_check_",
    TABLE_BTN_RESETPW        : ".restPw",
    TABLE_BTN_DETAIL         : ".detail",
	TABLE_BTN_DELETE         : ".delete",
    
    //div
    DIV_REFRESH_BODY         : "userListRefreshBody",
    SEARCH_PANEL_ID          : "searchPanel",
    USER_LIST_TABLE_ID       : "userListTable",
    
    PAGE_LINK_ID_PREFIX      : "userPageLinkIdPrefix",
    //div
    DIV_MAINBODY             : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserSearch.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
	//$('[data-toggle="tooltip"]').tooltip();
	$('[data-toggle="tooltip"]').tooltip({
	   trigger: 'manual'
	 }).on('mouseover', function () {
	   var $this = $(this);
	   console.log("offsetWidth="+this.offsetWidth + " scrollWidth="+this.scrollWidth);
	   if (this.offsetWidth < this.scrollWidth) {
	     $this.tooltip('show');
	   }
	 }).on('mouseout', function () {
	   var $this = $(this);
	   $this.tooltip('hide');
	 });
    
    //init bond event to btn
    self.initEvent();

	if (self.getObject(self.ID.HID_HIDE_SEARCH).val() === "true") {
		self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID).click();
	} else {
		self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID).click();
	}

};

// init event
AdminUserSearch.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
    self.getObject(self.ID.REGIST_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
    
    self.getObject(self.ID.REGIST_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
    
    self.getObject(self.ID.VALID_END_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
    
    self.getObject(self.ID.VALID_END_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn,
		todayHighlight:true
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("false");
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("true");
			self.getObject(self.ID.SEARCH_PANEL_ID).hide();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenHideSearch + "px");
		}
    );
    
    ShaInput.button.onChange(self.getObject(self.ID.USER_LIST_CHECK_ALL_ID),
    	function(event) {
			$checkBoxList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.USER_CHECK_PREF_ID);
			if (self.getObject(self.ID.USER_LIST_CHECK_ALL_ID).is(":checked")) {
				$checkBoxList.each(function(i, elem){
					$(elem).prop('checked', true);
					$(elem).change();
				});
			} else {
				$checkBoxList.each(function(i, elem){
					$(elem).prop('checked', false);
					$(elem).change();
				});
			}
		}
    );
    $checkBoxList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.USER_CHECK_PREF_ID);
    $checkBoxList.each(function(i, elem){
		//init
		self.setTableBtnStatus(i, false);
		//check box init
    	ShaInput.button.onChange($(elem),
	    	function(event) {
				if ($(elem).is(":checked")) {
					self.setTableBtnStatus(i, true);
				} else {
					self.setTableBtnStatus(i, false);
				}
			}
	    );
    	
    });
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.get(
                self.jsContext.adminJsView.adminUserSearch.url_user_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
	
	ShaInput.button.onClick(self.getObject(self.ID.ADD_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
	            self.jsContext.jsView.userDetail.url_user_detail_add_init, 
	            //[{name:"id",     value:$(elem).attr("data")}],
			    self.getForm().serializeArray(),  
	            function(data){
	                self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	            }
	        ); 
		}
    );
	
	ShaInput.button.onClick(self.getObject(self.ID.IMPORT_BTN_ID),
    	function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["userImport.title"],
				self.jsContext.adminJsView.adminUserImport.url_init, 
				null);
		}
    );
	
	ShaInput.button.onClick(self.getObject(self.ID.EXPORT_BTN_ID),
    	function(event) {
			ShaAjax.ajax.getWithDownloadFile(
		       self.jsContext.adminJsView.adminUserSearch.url_user_export, 
			   "userSearchForm",
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
		}
    );
	
    
    //initPageLink
    ShaPage.pageLink.initPageLink(self.ID.PAGE_LINK_ID_PREFIX,
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    ); 
	
	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL);
	$tableBtnList.each(function(i, elem){
		//check box init
	   	ShaInput.button.onClick($(elem),
	    	function(event) {
				self.getObject(self.ID.HID_SELECTED_USER_ID).val($(elem).attr("data"));
				ShaAjax.ajax.get(
	                self.jsContext.jsView.userDetail.url_user_detail_from_admin_init, 
	                [{name:"id",     value:$(elem).attr("data")}],
				    //self.getForm().serializeArray(),  
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
	
	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DELETE);
	$tableBtnList.each(function(i, elem){
	   	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaDialog.dialogs.confirm(
					self.i18n["dialogs.confirm.delete.title"], 
					self.i18n["dialogs.confirm.delete.msg"], 
					function () {
						ShaAjax.ajax.post(
							self.jsContext.jsView.userDetail.url_user_detail_delete, 
							[{name:"id",     value:$(elem).attr("data")}],
							function (data) {
								ShaDialog.dialogs.success(self.i18n["dialogs.delete.success.msg"]);
								self.getObject(self.ID.SEARCH_BTN_ID).click();
							}
						);
					}
				);
			}
	    );
	});
	
	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_RESETPW);
	$tableBtnList.each(function(i, elem){
		//check box init
	   	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaDialog.dialogs.confirm(
					self.i18n["PasswordReset.title"], 
					self.i18n["PasswordReset.confirm.reset.title"], 
					function () {
						ShaAjax.ajax.post(
							self.jsContext.jsView.passwordReset.url_user_reset,
							[{name:"id",     value:$(elem).attr("data")}],
							function (data) {
								if (data == Pos.constants.setInfo.common.executeReturnTypeOk) {
									ShaDialog.dialogs.success(self.i18n["PasswordReset.success.msg"]);
								}else{
									ShaDialog.dialogs.alert(self.i18n["PasswordReset.success.fail"]);
								}
							}
						);
					}
				);
			}
	    );
	});
    
};

//doPageLink
AdminUserSearch.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.get(
        self.jsContext.adminJsView.adminUserSearch.url_user_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    ); 
};

AdminUserSearch.prototype.setTableBtnStatus = function(rowIdx, enabled){
    //keep self instance for call back
    var self = this;
    if (enabled) {
		ShaInput.obj.enabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_RESETPW)[rowIdx]));
		ShaInput.obj.enabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL)[rowIdx]));
	} else {
		ShaInput.obj.disabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_RESETPW)[rowIdx]));
		ShaInput.obj.disabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL)[rowIdx]));
	}
};	
//----------------------------------------------------------------------------]
